package com.dongze.mvpandroid.logic.network.callback;

import android.util.Log;

import com.dongze.mvpandroid.bean.HttpErrResult;
import com.dongze.mvpandroid.bean.HttpResult;
import com.dongze.mvpandroid.common.GlobalVar;
import com.dongze.mvpandroid.logic.network.factory.ResultException;
import com.google.gson.Gson;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import rx.Subscriber;

public abstract class HttpResultCallBack<M> extends Subscriber<HttpResult<M>> {

    /**
     * 请求返回
     */
    public abstract void onResponse(M m, int status);
    public abstract void onErr(String msg, int status);

    /**
     * 请求完成
     */
    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        if(e != null){
            if(e instanceof ResultException){
                ResultException err = (ResultException) e;
                onErr(err.getErrMsg(), GlobalVar.RESULT_UNLOGIN);
            }else{
                onErr("网络异常，请检查网络", GlobalVar.RESULT_UNLOGIN);
                Log.d("HttpManager","解析失败==：" + e.getMessage());
            }
        }
        onCompleted();
    }

    /**
     * Http请求失败
     */
    private void onHttpFail(String msg, int status){
        onErr(msg, status);
    }

    @Override
    public void onNext(HttpResult<M> result) {
        String jsonResponse = new Gson().toJson(result);
        Log.d("HttpManager", "返回ok==：" + jsonResponse);
        if (result.getErrNum() == GlobalVar.RESULT_OK) {
            onResponse(result.getRetData(), GlobalVar.RESULT_OK);
        } else {
            onHttpFail(result.getErrMsg(), GlobalVar.RESULT_UNLOGIN);
        }
    }
}
