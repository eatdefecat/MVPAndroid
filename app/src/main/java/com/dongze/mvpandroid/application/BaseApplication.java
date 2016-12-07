package com.dongze.mvpandroid.application;

import android.content.Context;

public class BaseApplication extends android.app.Application{

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static Context getContext() {
        return mContext;
    }
}
