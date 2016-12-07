package com.dongze.mvpandroid.logic.network.service;

import com.dongze.mvpandroid.bean.HttpResult;
import com.dongze.mvpandroid.bean.PhoneLocalBean;
import com.dongze.mvpandroid.common.GlobalVar;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

public interface HttpService {

    @GET(GlobalVar.NetPorts.WEATHER)
    Observable<HttpResult<PhoneLocalBean>> queryWeather(@QueryMap Map<String, String> options);
}
