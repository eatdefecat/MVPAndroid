package com.dongze.mvpandroid.logic.presenter.base;

import android.content.Context;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public interface IBaseView {
    // 只做局部变量使用
    Context getActivity();
    Context getContext();

    void showProgressDialog(String msg);
    void showProgressDialog(String msg, boolean cancelable);
    void showProgressDialog();
    void dismissProgressDialog();
    void showToast(String msg);
    void showToast(int id);

    void onCompleted();
    void onError();
}
