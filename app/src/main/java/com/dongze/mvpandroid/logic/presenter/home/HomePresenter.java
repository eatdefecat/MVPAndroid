package com.dongze.mvpandroid.logic.presenter.home;

import com.dongze.mvpandroid.bean.PhoneLocalBean;
import com.dongze.mvpandroid.logic.manager.HttpManager;
import com.dongze.mvpandroid.logic.network.callback.HttpResultCallBack;
import java.util.HashMap;
import java.util.Map;

public class HomePresenter extends HomeContract.Presenter {

    public HomePresenter(HomeContract.View view) {
        super(view);
    }

    /**
     * 查询天气
     */
    @Override
    public void queryWeather(String phone) {
        Map<String, String> options = new HashMap<>();
        options.put("phone", phone);
        addMainSubscription(HttpManager.getInstance().sendRequest().queryWeather(options),
                new HttpResultCallBack<PhoneLocalBean>() {

                    @Override
                    public void onResponse(PhoneLocalBean bean, int status) {
                        if (bean != null) {
                            mView.onUserLoadCompleted(bean);
                        }else{
                            mView.onUserLoadError();
                        }
                    }

                    @Override
                    public void onErr(String err, int status) {
                        mView.showToast(err);
                        mView.onUserLoadError();
                    }
                });
    }
}
