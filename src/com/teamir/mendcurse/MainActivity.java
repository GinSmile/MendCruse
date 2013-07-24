package com.teamir.mendcurse;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.agimind.widget.SlideHolder;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//去掉状态栏
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		mSlideHolder = (SlideHolder) findViewById(R.id.slideHolder);
		
		
		
//		View toggleView = findViewById(R.id.textView);
//		toggleView.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				mSlideHolder.toggle();
//			}
//		});
		
		//利用xmlParse处理类来获取所有元素对象。
		XmlResourceParser xrp = this.getResources().getXml(R.xml.elements);
		ArrayList<Element> elements = XmlParse.createData(xrp);
		
		
		
		StringBuilder sb = new StringBuilder();
		for(Element e:elements){
			sb.append(e.toString()+"\n" + "---------------" + "\n");
		}
		
		TextView text = (TextView)findViewById(R.id.textView);
		text.setText(sb);
		
		
		
		
		
		
		
		
		
		/***********************************下面是菜单按钮事件**********************************************/

		//元素周期表按钮
		Button allElement = (Button)findViewById(R.id.all_element);
		allElement.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(MainActivity.this, MainActivity.class);
				startActivity(i);
			}
			
		});
		
	}
	
	
	
	
	
	

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	
	private SlideHolder mSlideHolder;

}
