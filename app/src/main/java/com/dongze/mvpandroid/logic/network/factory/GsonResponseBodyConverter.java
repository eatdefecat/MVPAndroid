package com.dongze.mvpandroid.logic.network.factory;

import android.util.Log;
import com.dongze.mvpandroid.bean.HttpErrResult;
import com.dongze.mvpandroid.bean.HttpResult;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;
import java.io.IOException;
import okhttp3.ResponseBody;
import retrofit2.Converter;

final class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final Type type;

    GsonResponseBodyConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }

    /**
     * 针对数据返回成功、错误不同类型字段处理
     */
    @Override public T convert(ResponseBody value) throws IOException {
        String response = value.string();
        try {
            HttpResult result = gson.fromJson(response, HttpResult.class);
            int code = result.getErrNum();
            if (code == 0){
                return gson.fromJson(response, type);
            } else {
                Log.d("HttpManager", "返回err==：" + response);
                HttpErrResult errResponse = gson.fromJson(response, HttpErrResult.class);
                if(code == -1){
                    throw new ResultException(errResponse.getRetMsg(), code);
                }else{
                    throw new ResultException(errResponse.getErrMsg(), code);
                }
            }
        }finally {
            value.close();
        }
    }
}
