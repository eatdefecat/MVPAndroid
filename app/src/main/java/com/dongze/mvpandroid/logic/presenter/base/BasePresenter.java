package com.dongze.mvpandroid.logic.presenter.base;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * @param <V> 示图
 */
public abstract class BasePresenter<V extends IBaseView> {

    protected V mView;
    private CompositeSubscription mCompositeSubscription;

    // 绑定视图
    public BasePresenter(V v){
        mView = v;
    }

    public void onResume(){

    }

    public void onStop(){

    }

    private void addSubscription(Subscription s) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(s);
    }

    protected <D> D addMainSubscription(Observable<D> observable, Subscriber<D> subscriber) {
        addSubscription(observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(subscriber));
        return null;
    }

    protected <D> D addThreadSubscription(Observable<D> observable, Subscriber<D> subscriber) {
        addSubscription(observable.subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
                    .subscribe(subscriber));
        return null;
    }

    public void onDestroy(){
        if (mCompositeSubscription != null  && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }

    public void unAttachView(){
        if (mView != null) {
            mView = null;
        }
    }
}
