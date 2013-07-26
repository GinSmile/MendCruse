package com.teamir.mendcurse;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import com.agimind.widget.SlideHolder;

public class Table extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.table);		
		mSlideHolder = (SlideHolder) findViewById(R.id.slideHolder);
		
		//去掉状态栏
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
			
	}
	
	private SlideHolder mSlideHolder;
}
