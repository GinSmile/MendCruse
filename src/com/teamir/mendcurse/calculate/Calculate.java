package com.teamir.mendcurse.calculate;

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

public class Calculate extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calculate);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		final ArrayList<Element> elements = ((AppData)getApplication()).getElements();
		final EditText calEdit = (EditText)findViewById(R.id.calculate_attr);
		final Button calBtn = (Button)findViewById(R.id.calculate_btn);
		calBtn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub				
				Log.v("imp", "press btn search");				
				
				String molecule = calEdit.getText().toString().trim();
				getBack(molecule);
			}
			private void getBack(String text){
				
			}
			private String textFormate(String text){
				String newtext="";
				int len = text.length();
				for(int x=0;x<len;x++){
					char ccode=text.charAt(x);
					if(ccode >= 65 && ccode <= 90  ||
							ccode >= 97 && ccode <= 122 ||
							ccode >= 46 && ccode <= 57)
						newtext+=ccode;
					if (ccode == 44)
						newtext += ".";
					if (ccode == 40 || ccode == 91 || ccode == 123)
						newtext += "(";
					if (ccode == 41 || ccode == 93 || ccode == 125)
						newtext += ")";
				}
				return newtext;
			}
			
	    });
	}
}
