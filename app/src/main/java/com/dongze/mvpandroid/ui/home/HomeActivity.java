package com.dongze.mvpandroid.ui.home;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dongze.mvpandroid.R;
import com.dongze.mvpandroid.bean.PhoneLocalBean;
import com.dongze.mvpandroid.logic.presenter.home.HomeContract;
import com.dongze.mvpandroid.logic.presenter.home.HomePresenter;
import com.dongze.mvpandroid.ui.base.BasicActivity;

public class HomeActivity extends BasicActivity<HomePresenter> implements HomeContract.View {

    private EditText mPhoneEt;
    private TextView mAddressTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitleCenter("首页");

        mPhoneEt = (EditText) findViewById(R.id.main_phones_et);
        mAddressTv = (TextView) findViewById(R.id.main_address_tv);
        Button queryBt = (Button) findViewById(R.id.main_query_bt);
        queryBt.setOnClickListener(this);
    }

    @Override
    protected HomePresenter attachView() {
        return new HomePresenter(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.main_query_bt:
                String phone = mPhoneEt.getText().toString().trim();
                if(TextUtils.isEmpty(phone)) return;

                showProgressDialog();
                mPresenter.queryWeather(phone);
                break;
        }
    }

    @Override
    public void onUserLoadCompleted(PhoneLocalBean bean) {
        dismissProgressDialog();
        mAddressTv.setText("归属地：" + bean.getCity());
    }

    @Override
    public void onUserLoadError() {
        dismissProgressDialog();
        mAddressTv.setText("获取失败");
    }
}
