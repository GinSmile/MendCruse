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
		mSlideHolder = (SlideHolder) findViewById(R.id.slideHolder);
		
		//去掉状态栏
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

		
		//利用xmlParse处理类来获取所有元素对象。
		XmlResourceParser xrp = this.getResources().getXml(R.xml.elements);
		final ArrayList<Element> elements = XmlParse.createData(xrp);
		
		
		//将数据写到TextView
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
				mSlideHolder.close();
				
			}
			
		});
		
		//速查元素按钮
		Button searchElement = (Button)findViewById(R.id.search_element);
		searchElement.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mSlideHolder.close();			
							
				Intent intent = new Intent(MainActivity.this, SearchElement.class);
				startActivity(intent);
			}
			
		});		
		
		
		
		
	}


	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
	
		return true;
	}	
	
	private SlideHolder mSlideHolder;

}
