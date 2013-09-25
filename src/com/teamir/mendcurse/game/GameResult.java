package com.teamir.mendcurse.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import com.teamir.mendcurse.*;
import com.teamir.mendcurse.game.ctrls.*;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class GameResult extends Activity
{
	TextView score;
	TextView rank;
	TextView rigques;
	TextView wroques;
	TextView playagain;
	TextView back;
	Intent i;
	int tolscore = 0,correctc = 0,incorrectc  = 0;
	SharedPreferences preference;
	SharedPreferences.Editor editor;
	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View scoreview = this.getLayoutInflater().inflate(R.layout.game_result_testui, null);
		TableLayout scorerank = (TableLayout)scoreview.findViewById(R.id.game_result_testui_score_rankview);
		setContentView(scoreview);
		
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		//获取各view
		score = (TextView)scoreview.findViewById(R.id.game_result_testui_tolsco);
		rank = (TextView)scoreview.findViewById(R.id.game_result_testui_rank);
		rigques = (TextView)scoreview.findViewById(R.id.game_result_testui_corrques);
		wroques = (TextView)scoreview.findViewById(R.id.game_result_testui_incorrques);
		playagain = (TextView)scoreview.findViewById(R.id.game_result_testui_playagain);
		back = (TextView)scoreview.findViewById(R.id.game_result_testui_back);
		//处理前一个activity传来的数据
		i = getIntent();
		Bundle data = i.getExtras();
		
		@SuppressWarnings("unchecked")
		ArrayList<optionLog> result = (ArrayList<optionLog>)data.getSerializable("resultlog");
		String playername = (String)data.getSerializable("playername");
		
	//	Log.v("result size: ",""+result.size());
		for(optionLog ol:result)
		{
			if(ol.getResult()){
				this.correctc++;
				this.tolscore +=5;
			}
			else{
				incorrectc++;
				this.tolscore -=3;
			}
		}
		String scoreText = "总分: "+ this.tolscore;
		String rankText = "排名："+ "";
		String rightcText = "答对个数: " + this.correctc;
		String wrongcText = "答错个数: " + this.incorrectc;
		//处理sharedPreferences
		preference = getSharedPreferences("data",MODE_PRIVATE);
		editor = preference.edit();
		int lastscore = 0;
		if(preference.contains(playername))
			lastscore = preference.getInt(playername, 0);		
		if(this.tolscore > lastscore || lastscore == 0)
		{
			editor.putInt(playername, this.tolscore);
			editor.commit();
		}
		//处理排行榜数据
		Map<String,Integer> scoresmap = (Map<String,Integer>)preference.getAll(); 
		Set scoresset = scoresmap.entrySet();
//		Log.v("set size", scoresset.size()+"");
		@SuppressWarnings("unchecked")
		ArrayList scoreslist = new ArrayList(scoresset);
		Collections.sort(scoreslist, new Comparator<Map.Entry>(){
			@Override
			public int compare(Map.Entry arg0, Map.Entry arg1) {
				// TODO Auto-generated method stub
				int one = 0,two = 0;
				one = (arg0.getValue()==null || arg0.getValue() == "")?0:(Integer)arg0.getValue();
				two = (arg1.getValue()==null || arg1.getValue() == "")?0:(Integer)arg1.getValue();
				return one-two;
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
			irank++;
		}
		Log.v("Scores result", text);
		//显示信息
		score.setText(scoreText);
		rigques.setText(rightcText);
		wroques.setText(wrongcText);    
		
		//绑定消息
		back.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				GameResult.this.setResult(1, i);
				finish();
			}
			
		});
		
		playagain.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GameResult.this.setResult(0, i);
				finish();
			}		
		});
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
	
	//按下返回键
	public void onBackPressed()
	{
		GameResult.this.setResult(1, i);
		finish();
	}

}