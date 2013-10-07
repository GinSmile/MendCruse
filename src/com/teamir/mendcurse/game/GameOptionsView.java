package com.teamir.mendcurse.game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.teamir.mendcurse.R;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class GameOptionsView extends Activity
{
	SharedPreferences preference;
	SharedPreferences.Editor editor;
	boolean opensound = false;
	AutoCompleteTextView username;
	AutoCompleteTextView count;
	RadioGroup music;
	Intent i;
	String[] counts = new String[]{
			"10","20","25","30","35","40"
	};
	String[] users;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.game_options_view);
		i = getIntent();
		Button btnok = (Button)findViewById(R.id.gov_ok);
		username = (AutoCompleteTextView)findViewById(R.id.gov_username);
		count = (AutoCompleteTextView)findViewById(R.id.gov_count);
		music = (RadioGroup)findViewById(R.id.gov_music);
		
		//获取sharepreferences中的用户以便在下拉框中显示
		preference = getSharedPreferences("data",MODE_PRIVATE);
		Map<String,Integer> scoresmap = (Map<String,Integer>)preference.getAll(); 
		Set tmpset = scoresmap.entrySet();
		users = new String[tmpset.size()];
		Iterator iterator = tmpset.iterator();
		int ii=0;
		while(iterator.hasNext())
		{
			Map.Entry mapen = (Map.Entry)iterator.next();
			users[ii++] = (String)mapen.getKey();
		}
		//设置下拉提示
		ArrayAdapter<String> cou = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,counts);
		ArrayAdapter<String> userr = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,users);
		count.setAdapter(cou);
		username.setAdapter(userr);
		
		music.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup arg0, int arg1) {
				// TODO Auto-generated method stub
				if(arg1 == R.id.gov_open)
					opensound = true;
				else
					opensound = false;
			}
		});
		btnok.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub			
				String user = username.getText().toString();
				String co = count.getText().toString();
				Bundle bundle = new Bundle();
				bundle.putString("playername", user);
				bundle.putString("quescount", co);
				bundle.putBoolean("opensound", opensound);
				i.putExtras(bundle);
				setResult(0,i);
			//	Log.v("info:", user+co+openmusic);
				finish();
			}			
		});
		
	}
}