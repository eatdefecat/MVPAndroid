package com.dongze.mvpandroid.ui.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dongze.mvpandroid.R;
import com.dongze.mvpandroid.application.BaseApplication;
import com.dongze.mvpandroid.common.GlobalVar;
import com.dongze.mvpandroid.logic.presenter.base.BasePresenter;
import com.dongze.mvpandroid.logic.presenter.base.IBaseView;
import com.dongze.mvpandroid.util.NetUtils;
import com.dongze.mvpandroid.widget.ProgressDialog;

import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

public abstract class BasicActivity<P extends BasePresenter> extends AppCompatActivity implements IBaseView, View.OnClickListener {

    public ActionBar mActionBar;
    public ImageView mTitleLeft;
    public ImageView mTitleRightImage;
    public TextView mTitleCenter;
    public TextView mTitleRight;
    public TextView mTitleRight2;
    private ProgressDialog mPro;
    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = attachView();
        initView();

        getWindow().getDecorView().post(new Runnable() {
            @Override
            public void run() {
                onCreateView();
            }
        });
    }

    private void initView() {
        // 获取屏幕宽高
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        GlobalVar.SCREEN_WIDTH = dm.widthPixels;
        GlobalVar.SCREEN_HEIGHT = dm.heightPixels;

        mActionBar = getSupportActionBar();
        if (Build.VERSION.SDK_INT >= 21) {
            mActionBar.setElevation(0);
        }
        mActionBar.setCustomView(R.layout.base_title);
        View actionView = mActionBar.getCustomView();
        mActionBar.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.gray_bottom_line_home));
        mActionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        mTitleLeft = (ImageView) actionView.findViewById(R.id.base_title_tv_left);
        mTitleLeft.setVisibility(View.VISIBLE);
        mTitleRightImage = (ImageView) actionView.findViewById(R.id.base_title_img_right);
        mTitleRight = (TextView) actionView.findViewById(R.id.base_title_tv_right);
        mTitleRight2 = (TextView) actionView.findViewById(R.id.base_title_tv_right2);
        mTitleCenter = (TextView) actionView.findViewById(R.id.base_title_tv_center);
        mTitleLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTitleRight.setOnClickListener(this);
        mTitleRightImage.setOnClickListener(this);
    }

    /**
     * 视图绘制已经初始化完毕
     */
    protected void onCreateView(){

    }

    /**
     * 绑定Presenter
     */
    protected abstract P attachView();


    @Override
    protected void onResume() {
        super.onResume();
        if(mPresenter != null) mPresenter.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mPresenter != null) mPresenter.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mPresenter != null) {
            mPresenter.onDestroy();
        }
        if(mPresenter != null){
            mPresenter.unAttachView();
            mPresenter = null;
        }
    }

    /**
     * 设置标题
     */
    protected void setTitleCenter(String title) {
        mTitleCenter.setText(title);
    }

    /**
     * 设置标题头背景
     */
    protected void setActionBarBackGraound(int drawable) {
        mActionBar.setBackgroundDrawable(ContextCompat.getDrawable(this, drawable));
    }

    protected void setTitleRight(String textRight) {
        mTitleRight.setVisibility(View.VISIBLE);
        mTitleRight.setText(textRight);
    }

    protected void hideTitleRight() {
        mTitleRight.setVisibility(View.GONE);
    }

    protected void setTitleRight2(String textRight2) {
        mTitleRight2.setVisibility(View.VISIBLE);
        mTitleRight2.setText(textRight2);
    }

    protected void setLeftImage(int drawable) {
        mTitleLeft.setVisibility(View.VISIBLE);
        mTitleLeft.setImageResource(drawable);
    }

    protected void setRightImage(int drawable) {
        mTitleRightImage.setVisibility(View.VISIBLE);
        mTitleRightImage.setImageResource(drawable);
    }

    protected void hideLeftImage() {
        mTitleLeft.setVisibility(View.GONE);
    }

    /**
     * 隐藏标题头
     */
    protected void hideActionBar() {
        if (mActionBar != null && mActionBar.isShowing()) {
            mActionBar.setShowHideAnimationEnabled(false);
            mActionBar.hide();
        }
    }

    /**
     * 显示标题头
     */
    protected void showActionBar() {
        if (mActionBar != null){
            mActionBar.setShowHideAnimationEnabled(false);
            mActionBar.show();
        }
    }

    public void finishDelayed(long delay) {
        Observable.timer(delay, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).doOnNext(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                BasicActivity.super.finish();
            }
        }).subscribe();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void showToast(int id) {
        Toast.makeText(getContext(), id, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 判断是否有网络
     */
    public boolean hasNetWork() {
        return NetUtils.isConnected(getContext());
    }


    @Override
    public void showProgressDialog(String msg, boolean cancelable) {
        if(mPro == null) {
            mPro = new ProgressDialog(this);
        }
        mPro.setmCancelable(cancelable);
        mPro.setProgressMsg(msg == null ? "正在加载,请稍后..." : msg);
        if(!mPro.isShowing()){
            mPro.show();
        }
    }

    @Override
    public void showProgressDialog(String msg) {
        showProgressDialog(msg, true);
    }

    @Override
    public void showProgressDialog() {
        showProgressDialog(null);
    }

    @Override
    public void dismissProgressDialog() {
        if(mPro != null) {
            if(mPro.isShowing()){
                mPro.dismiss();
            }
        }
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError() {

    }

    @Override
    public Context getActivity() {
        return this;
    }

    @Override
    public Context getContext() {
        return BaseApplication.getContext();
    }

    public void startActivity(Class<? extends Activity> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    public void startActivity(Class<? extends Activity> clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void startActivityForResult(Class<? extends Activity> clazz, int requestCode) {
        Intent intent = new Intent(this, clazz);
        startActivityForResult(intent, requestCode);
    }
}
