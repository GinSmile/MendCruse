package com.teamir.mendcurse.game;

import com.teamir.mendcurse.R;

import android.os.Bundle;
import android.view.WindowManager;
import android.app.Activity;

public class Headsup extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.headsup);
		
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
	}
}