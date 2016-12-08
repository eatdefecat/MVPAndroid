package com.dongze.mvpandroid.logic.manager;


import com.dongze.mvpandroid.application.BaseApplication;
import com.dongze.mvpandroid.common.GlobalVar;
import com.dongze.mvpandroid.logic.network.factory.GsonDConverterFactory;
import com.dongze.mvpandroid.logic.network.interceptor.HttpCacheInterceptor;
import com.dongze.mvpandroid.logic.network.service.HttpService;
import java.io.File;
import java.util.concurrent.TimeUnit;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class HttpManager {

    public static final String SERVER = GlobalVar.SERVER;//服务器根地址

    private HttpService mHttpService;
    private Retrofit mAdapter;
    private static HttpManager instance;

    private HttpManager() {
        mAdapter = new Retrofit.Builder()
                .baseUrl(SERVER)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonDConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(getBuilder().build())
                .build();
    }

    public static HttpManager getInstance() {
        if (instance == null) {
            instance = new HttpManager();
        }
        return instance;
    }

    public HttpService sendRequest() {
        if (mHttpService == null) {
            mHttpService = mAdapter.create(HttpService.class);
        }
        return mHttpService;
    }

    private OkHttpClient.Builder getBuilder() {
        File cacheFile = new File(BaseApplication.getContext().getCacheDir().getAbsolutePath(), "ShopHttpCache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(new HttpCacheInterceptor());
        builder.cache(cache);
        builder.readTimeout(GlobalVar.READ_TIMEOUT, TimeUnit.SECONDS);
        builder.connectTimeout(GlobalVar.CONNECT_TIMEOUT, TimeUnit.SECONDS);
        builder.writeTimeout(GlobalVar.WRITE_TIMEOUT, TimeUnit.SECONDS);
        builder.retryOnConnectionFailure(true);
        return builder;
    }
}
