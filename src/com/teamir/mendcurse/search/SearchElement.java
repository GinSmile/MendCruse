package com.teamir.mendcurse.search;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.agimind.widget.SlideHolder;
import com.teamir.mendcurse.AppData;
import com.teamir.mendcurse.Element;
import com.teamir.mendcurse.R;
import com.teamir.mendcurse.table.ItemView;

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
		
				
		final EditText seaEleSym = (EditText)findViewById(R.id.search_element_attr);
		final Button seaEleBtn = (Button)findViewById(R.id.search_element_btn);		
		seaEleBtn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub				
				Log.v("imp", "press btn search");				
				
//				Animation to_big = AnimationUtils.loadAnimation(SearchElement.this, R.anim.to_big);
//				seaEleBtn.startAnimation(to_big);		
				
				
				String elementAttr = seaEleSym.getText().toString().trim();
				findEle(elementAttr);
				
			}

			private void findEle(String text) {
				// TODO Auto-generated method stub
				
				for(Element e : elements){
					if(e.symbol.equals(text)){
						showItemDetail(e);
						return;
					}else if(e.elementid.equals(text)){
						showItemDetail(e);
						return;
					}else if(e.elementname.equals(text)){
						showItemDetail(e);
						return;
					}
				}			
							
				
				Animation shake = AnimationUtils.loadAnimation(SearchElement.this, R.anim.shake);
				seaEleSym.startAnimation(shake);
				seaEleSym.setText("");
				Toast.makeText(SearchElement.this, "错误，请重新填写：化学式/元素名/元素序号", Toast.LENGTH_SHORT).show();
				
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
