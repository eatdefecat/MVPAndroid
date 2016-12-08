package com.dongze.mvpandroid.widget;


import android.content.Context;
import android.view.KeyEvent;
import android.widget.TextView;

import com.dongze.mvpandroid.R;
import com.dongze.mvpandroid.ui.base.BaseDialog;


public class ProgressDialog extends BaseDialog {

	private boolean mCancelable;

	public ProgressDialog(Context context) {
		this(context, R.style.CustomProgressDialog);
	}

	public ProgressDialog(Context context, int theme) {
		super(context, theme);
		setContentView(R.layout.progress_layout);
	}

	public void setProgressMsg(String strMessage) {
		TextView tvMsg = (TextView) findViewById(R.id.text_progress);
		if (tvMsg != null) {
			tvMsg.setText(strMessage);
		}
	}

	public boolean ismCancelable() {
		return mCancelable;
	}

	public void setmCancelable(boolean mCancelable) {
		this.mCancelable = mCancelable;
		setCancelable(mCancelable);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_MENU) {
			// 屏蔽Menu键
			return true;
		}
		else if ((keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_SEARCH)
				&& !mCancelable) {
			// 如果是返回键或搜索键，并且设置了不可取消，则返回不再处理
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
