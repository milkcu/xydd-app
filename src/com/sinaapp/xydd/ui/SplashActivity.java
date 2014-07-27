package com.sinaapp.xydd.ui;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import com.sinaapp.xydd.R;

import com.sinaapp.xydd.ui.base.BaseActivity;
import com.umeng.update.UmengUpdateAgent;

public class SplashActivity extends BaseActivity {

	private Handler mHandler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		View view=View.inflate(this, R.layout.start_activity, null);
		setContentView(view);
		Animation animation=AnimationUtils.loadAnimation(this, R.anim.alpha);
		view.startAnimation(animation);
		animation.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation arg0) {}
			@Override
			public void onAnimationRepeat(Animation arg0) {}
			
			@Override
			public void onAnimationEnd(Animation arg0) {
				mHandler.postDelayed(new Runnable() {
					@Override
					public void run() {
						goHome();
					}
				}, 500);
			}
		});
		UmengUpdateAgent.setUpdateOnlyWifi(false);
		UmengUpdateAgent.update(this);
		
		
	}

	protected void onResume() {
		super.onResume();
	}

	private void goHome() {
		openActivity(MainActivity.class);
		defaultFinish();
	};

}
