package com.dongze.mvpandroid.ui.base;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;

import com.dongze.mvpandroid.common.GlobalVar;


public class BaseDialog extends Dialog {

	public BaseDialog(Context context) {
		super(context);
	}

	public BaseDialog(Context context, int theme){
	    super(context, theme);
	}
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

	@Override
	public void show() {
		super.show();
		
//		Window window = getWindow();
//	    WindowManager.LayoutParams params = window.getAttributes();
//	    params.height = LayoutParams.WRAP_CONTENT;
//	    params.width = (int) (GlobalVar.SCREEN_WIDTH * 0.8);
//	    window.setAttributes(params);
	}

	public interface PriorityListener {
		public void refreshPriorityUI(Object obj);
	}
}
