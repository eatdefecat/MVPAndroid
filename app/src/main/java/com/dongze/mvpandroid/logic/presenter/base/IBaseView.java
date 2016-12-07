package com.dongze.mvpandroid.logic.presenter.base;

import android.content.Context;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public interface IBaseView {

    Context getActivity();
    Context getContext();
    void showProgressDialog(String msg);
    void showProgressDialog(String msg, boolean cancelable);
    void showProgressDialog();
    void dismissProgressDialog();
    void showToast(String msg);
    void showToast(int id);

    CompositeSubscription getCompositeSubscription();
    void addSubscription(Subscription s);

    void onCompleted();
    void onError();
}
