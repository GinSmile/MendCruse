package com.teamir.mendcurse.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.teamir.mendcurse.R;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;

public class Game extends Activity {
	
	Intent i_h =null;
	Intent i_s = null;
	String playername = "anonymous"+new Random().nextInt(100000);
	int quescount = 20;
	SharedPreferences preference;
	boolean opensound = false;
	Bundle bundle;
	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View scoreview = this.getLayoutInflater().inflate(R.layout.game_testui, null);
		TableLayout scorerank = (TableLayout)scoreview.findViewById(R.id.game_testui_score_rankview);
		setContentView(scoreview);
		
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		Button singleplayer = (Button)findViewById(R.id.game_testui_singleplayer);
		Button headsup = (Button)findViewById(R.id.game_testui_headsup);
		Button options = (Button)findViewById(R.id.game_testui_option);
		i_h = new Intent(this,Headsup.class);
		i_s = new Intent(this,SinglePlayer.class);
		bundle = new Bundle();
		
		//处理排行榜信息显示
		preference = getSharedPreferences("data",MODE_PRIVATE);
		Map<String,Integer> scoresmap = (Map<String,Integer>)preference.getAll(); 
		Set scoresset = scoresmap.entrySet();
		ArrayList scoreslist = new ArrayList(scoresset);
		Collections.sort(scoreslist, new Comparator<Map.Entry>(){   //定义排序算法
			@Override
			public int compare(Map.Entry arg0, Map.Entry arg1) {
				// TODO Auto-generated method stub
				int one = 0,two = 0;
				one = (arg0.getValue()==null || arg0.getValue() == "")?0:(Integer)arg0.getValue();
				two = (arg1.getValue()==null || arg1.getValue() == "")?0:(Integer)arg1.getValue();
				return two-one;
			}
			
		});
		Iterator iterator = scoreslist.iterator();
		String text ="";
		int irank = 1;
		while(iterator.hasNext())
		{
			Map.Entry mapen = (Map.Entry)iterator.next();
			String n = (String)(mapen.getKey());
			String s  = ""+(Integer)(mapen.getValue());	
			this.addRows(""+irank, n, s, scorerank);
		}
		
		singleplayer.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				bundle.putString("playername", playername);
				bundle.putInt("quescount", quescount);
				bundle.putBoolean("opensound", opensound);
				i_s.putExtras(bundle);
				startActivity(Game.this.i_s);
			}
			
		});
		
		headsup.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(Game.this.i_h);
			}
			
		}
		);
		options.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivityForResult(new Intent(Game.this,GameOptionsView.class),0);
			}
			
		});
	}
	
	@Override
	public void onActivityResult(int requestCode , int resultCode, Intent intent)
	{
		if (requestCode == 0 && resultCode == 0)
		{
			if(intent != null)
			{
				Bundle bundle = intent.getExtras();
				String pn = bundle.getString("playername");
				String count = bundle.getString("quescount");
				opensound = bundle.getBoolean("opensound");
				playername = pn.equals("")?"anonymous"+new Random().nextInt(100000):pn;
				quescount = Integer.parseInt(count.equals("")?"20":count);
				
			}
		}
	
	}
	
	//排行榜增加一条记录的方法
		public void addRows(String rank,String name,String score,TableLayout tl)
		{
			TableRow tablerow = new TableRow(this);
			TextView rrank = new TextView(this);
			rrank.setText(rank);
			TextView rname = new TextView(this);
			rname.setText(name);
			TextView rscore = new TextView(this);
			rscore.setText(score);
			tablerow.addView(rrank);
			tablerow.addView(rname);
			tablerow.addView(rscore);	
			tl.addView(tablerow);
		}
}