package com.dongze.mvpandroid.logic.presenter.home;

import com.dongze.mvpandroid.bean.PhoneLocalBean;
import com.dongze.mvpandroid.logic.presenter.base.BasePresenter;
import com.dongze.mvpandroid.logic.presenter.base.IBaseView;

public interface HomeContract {
    interface View extends IBaseView {
        void onUserLoadCompleted(PhoneLocalBean bean);
        void onUserLoadError();
    }

    abstract class Presenter extends BasePresenter<View> {
        Presenter(View view) {
            super(view);
        }
        public abstract void queryWeather(String phone);
    }
}
