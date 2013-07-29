package com.teamir.mendcurse;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;


import com.agimind.widget.SlideHolder;
import com.teamir.mendcurse.search.SearchElement;
import com.teamir.mendcurse.table.Table;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mSlideHolder = (SlideHolder) findViewById(R.id.slideHolder);
		
		//去掉状态栏
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);	
		
		
		
		/***********************************下面是菜单按钮事件**********************************************/

		//元素周期表按钮
		Button allElement = (Button)findViewById(R.id.all_element);
		allElement.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this, Table.class);
				startActivity(intent);
				
			}
			
		});
		
		//速查
		Button searchElement = (Button)findViewById(R.id.search);
		searchElement.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub						
							
				Intent intent = new Intent(MainActivity.this, SearchElement.class);
				startActivity(intent);
			}
			
		});		
		
		//游戏
		Button game = (Button)findViewById(R.id.game);
		game.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub						
				Intent intent = new Intent(MainActivity.this, Game.class);
				startActivity(intent);
			}
			
		});		
		
		//分子量计算
		Button story = (Button)findViewById(R.id.calc);
		story.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub						
				//Intent intent = new Intent(MainActivity.this, SearchElement.class);
				//startActivity(intent);
			}
			
		});		
		
		
		/************************************侧滑菜单键*******************************/
		Button about = (Button)findViewById(R.id.about);
		about.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub						
				mSlideHolder.close();
			}
			
		});	
		
		Button setting = (Button)findViewById(R.id.setting);
		setting.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub						
				mSlideHolder.close();
			}
			
		});
		
	}
	
	
	

	/*************************************监听菜单键*******************************/

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	     if(keyCode == KeyEvent.KEYCODE_MENU) {
	        // 监控菜单键
	        mSlideHolder.toggle();
	        return false;
	    }
	    return super.onKeyDown(keyCode, event);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.

		return true;
	}	
	
	private SlideHolder mSlideHolder;

}
