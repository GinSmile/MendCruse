package com.teamir.mendcurse.table;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;

import com.teamir.mendcurse.R;

public class Table extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.table);		
		
		//去掉状态栏
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
			

	}

}
