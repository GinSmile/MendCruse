package com.teamir.mendcurse.search;

import java.util.ArrayList;

import com.agimind.widget.SlideHolder;
import com.teamir.mendcurse.AppData;
import com.teamir.mendcurse.Element;
import com.teamir.mendcurse.R;
import com.teamir.mendcurse.R.id;
import com.teamir.mendcurse.R.layout;
import com.teamir.mendcurse.table.ItemView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SearchElement extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_element);
		mSlideHolder = (SlideHolder) findViewById(R.id.slideHolder);
		
		//去掉状态栏
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		//得到数据
		final ArrayList<Element> elements = ((AppData)getApplication()).getElements();
		
		final TextView seaEleRes = (TextView)findViewById(R.id.search_element_result);			
		Button seaEleBtn = (Button)findViewById(R.id.search_element_btn);		
		seaEleBtn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub				
				Log.v("imp", "press btn search");				
				EditText seaEleSym = (EditText)findViewById(R.id.search_element_attr);
				if(seaEleSym.getText() == null){
					//检查格式不正确，这里先简写，以后再改
					seaEleRes.setText("格式错误");
				}
				else{
					String elementAttr = seaEleSym.getText().toString();
					for(Element e : elements){
						if(e.symbol.equals(elementAttr)){
							showItemDetail(e);
							break;
						}else if(e.elementid.equals(elementAttr)){
							showItemDetail(e);
							break;
						}else if(e.elementname.equals(elementAttr)){
							showItemDetail(e);
							break;
						}
					}					
				}
			}
			
		});
	}
	
	
	private void showItemDetail(Element e){
		Intent intent = new Intent(SearchElement.this, ItemView.class);
		intent.putExtra("index", e.getElementid());							
		startActivity(intent);
	}
	
	
	private SlideHolder mSlideHolder;
}
