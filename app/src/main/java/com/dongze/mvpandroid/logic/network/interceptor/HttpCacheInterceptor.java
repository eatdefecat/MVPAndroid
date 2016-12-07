package com.dongze.mvpandroid.logic.network.interceptor;

import android.util.Log;

import com.dongze.mvpandroid.application.BaseApplication;
import com.dongze.mvpandroid.util.NetUtils;
import com.google.gson.Gson;
import java.io.IOException;
import okhttp3.CacheControl;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HttpCacheInterceptor implements Interceptor {

    private static final String TAG = "HttpManager";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Request.Builder requestBuilder = original.newBuilder()
                .method(original.method(), original.body());

        Headers.Builder hb = new Headers.Builder();
        addHeader(hb);
        if (!NetUtils.isConnected(BaseApplication.getContext())) {
            requestBuilder.cacheControl(CacheControl.FORCE_CACHE);
            Log.d(TAG, "网络不可用请求拦截");
        } else {//网络可用
            requestBuilder.cacheControl(CacheControl.FORCE_NETWORK);
        }
        Request request = requestBuilder.headers(hb.build()).build();
        Log.d(TAG, "地址：" + request.url());
        String jsonRequest = new Gson().toJson(request.body());
        Log.d(TAG, "请求参数：" + jsonRequest);

        try {
            Response response = chain.proceed(request);
            String cookie = response.headers().get("Set-Cookie");

            if (NetUtils.isConnected(BaseApplication.getContext())) {//如果网络可用
                int maxAge = 60 * 3;
                response = response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .build();
            } else {
                int maxStale = 60 * 60 * 24;
                response = response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }
            return response;
        }catch (Exception err){
            Log.e("HttpManager","http=============" + err.getLocalizedMessage());
        }
        return null;
    }

    /**
     * 增加消息头
     */
    private void addHeader(Headers.Builder header) {
        header.add("apikey", "8ca4b101096587f725ff69a07ff4d188");
    }
}
