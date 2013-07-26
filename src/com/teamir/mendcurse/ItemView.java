package com.teamir.mendcurse;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

public class ItemView  extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(new MyView(this));
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.pop_view);
		
		//从AppData类中取数据
		AppData appData = (AppData)getApplication();
		String index = getIntent().getStringExtra("index");
		Element element = appData.getElement(Integer.parseInt(index));
		
		//将数据写到TextView	
		TextView text = (TextView)findViewById(R.id.item_detail);
		text.setText(element.toString());
		
	}
}
