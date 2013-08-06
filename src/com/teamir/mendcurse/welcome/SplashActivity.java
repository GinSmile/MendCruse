package com.teamir.mendcurse.welcome;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.teamir.mendcurse.MainActivity;
import com.teamir.mendcurse.R;

//欢迎界面
public class SplashActivity extends Activity {
	boolean isFirstIn = false;
	private static final int GO_HOME = 1000;
	private static final int GO_GUIDE = 1001;
	private static final long SPLASH_DELAY_MILLIS = 3000;
	private static final String SHAREDPREFERENCES_NAME = "first_pref";

	private Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case GO_HOME:
				goHome();
				break;
			case GO_GUIDE:
				goGuide();
				break;
			}
			super.handleMessage(msg);
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);

		init();
	}

	private void init() {
		SharedPreferences preferences = getSharedPreferences(
				SHAREDPREFERENCES_NAME, MODE_PRIVATE);
		Editor editor = preferences.edit();
		isFirstIn = preferences.getBoolean("isFirstIn", true);

		Log.v("是否第一次进入应用", isFirstIn+"");
		if (!isFirstIn) {
			mHandler.sendEmptyMessageDelayed(GO_HOME, SPLASH_DELAY_MILLIS);
		} else {
			editor.putBoolean("isFirstIn", false);
			editor.commit();
			mHandler.sendEmptyMessageDelayed(GO_GUIDE, SPLASH_DELAY_MILLIS);
		}

	}

	private void goHome() {
		Intent intent = new Intent(SplashActivity.this, MainActivity.class);
		SplashActivity.this.startActivity(intent);
		SplashActivity.this.finish();
	}

	private void goGuide() {
		Intent intent = new Intent(SplashActivity.this, GuideActivity.class);
		SplashActivity.this.startActivity(intent);
		SplashActivity.this.finish();
	}
}
