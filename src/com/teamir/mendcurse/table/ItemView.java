package com.teamir.mendcurse.table;

import com.teamir.mendcurse.AppData;
import com.teamir.mendcurse.Element;
import com.teamir.mendcurse.R;
import com.teamir.mendcurse.R.id;
import com.teamir.mendcurse.R.layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

public class ItemView  extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(new MyView(this));
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.item_view);
		
		//从AppData类中取数据
		AppData appData = (AppData)getApplication();
		String index = getIntent().getStringExtra("index");
		final Element element = appData.getElement(Integer.parseInt(index));
		
		
		//更改MyView
		//MyView myView = (MyView)findViewById(R.id.element_left_pic);
		//myView.setElementId(element.getElementid());
		//myView.setElementSymbol(element.getSymbol());
		
		//将数据写到TextView	
		TextView text = (TextView)findViewById(R.id.element_attr);
		text.setText(element.toString());
		
		
		//点击进入wiki百科页面
		TextView wiki = (TextView)findViewById(R.id.wiki_btn);
		wiki.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {				
				// TODO Auto-generated method stub		
				
				final WebView web = (WebView) findViewById(R.id.wiki);
				String url = "http://zh.wikipedia.org/wiki/" + element.getElementname();
				web.loadUrl(url);
				
			}
			
		});
		
	}
}
