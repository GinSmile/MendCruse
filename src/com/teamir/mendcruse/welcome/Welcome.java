package com.teamir.mendcruse.welcome;

import com.teamir.mendcurse.MainActivity;
import com.teamir.mendcurse.R;
import com.teamir.mendcurse.R.layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


//欢迎界面
public class Welcome extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
		setContentView(R.layout.welcome);
		//延迟1秒后执行run方法中的页面跳转
		new Handler().postDelayed(new Runnable() {			
			@Override
			public void run() {
				Intent intent = new Intent(Welcome.this, MainActivity.class);
				startActivity(intent);
				Welcome.this.finish();
			}
		}, 1000);
	}
}
