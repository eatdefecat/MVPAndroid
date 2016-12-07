package com.dongze.mvpandroid.logic.presenter.base;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @param <V> 示图
 */
public abstract class BasePresenter<V extends IBaseView> {

    protected V mView;

    // 绑定视图
    public BasePresenter(V v){
        mView = v;
    }

    public void onResume(){

    }

    public void onStop(){

    }

    public void onDestroy(){

    }

    public void unAttachView(){
        if (mView != null) {
            mView = null;
        }
    }

    protected <D> D addMainSubscription(Observable<D> observable, Subscriber<D> subscriber) {
        if(mView != null) {
            mView.addSubscription(observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(subscriber));
        }
        return null;
    }

    protected <D> D addThreadSubscription(Observable<D> observable, Subscriber<D> subscriber) {
        if(mView != null) {
            mView.addSubscription(observable.subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
                    .subscribe(subscriber));
        }
        return null;
    }
}
