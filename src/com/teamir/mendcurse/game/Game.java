package com.teamir.mendcurse.game;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.teamir.mendcurse.game.*;
import com.teamir.mendcurse.game.ctrls.GameController;
import com.teamir.mendcurse.game.ctrls.GameViews;
import com.teamir.mendcurse.AppData;
import com.teamir.mendcurse.Element;
import com.teamir.mendcurse.R;


public class Game extends Activity {
	//全局对象
	private TextView tolsco;
	private TextView ques;
	private TextView time;
	private ProgressBar timebar;
	private TextView cenques;
	private TextView[] options = new TextView[4];   //4个选项view
	
	GameViews gv = null;
	GameController gc = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game);
		
		//获取各view
		tolsco = (TextView)findViewById(R.id.game_tolsco);
		ques = (TextView)findViewById(R.id.game_ques);
		time = (TextView)findViewById(R.id.game_time);
		timebar = (ProgressBar)findViewById(R.id.game_timebar);
		cenques = (TextView)findViewById(R.id.game_cenques);
		options[0] = (TextView)findViewById(R.id.game_option0);
		options[1] = (TextView)findViewById(R.id.game_option1);
		options[2] = (TextView)findViewById(R.id.game_option2);
		options[3] = (TextView)findViewById(R.id.game_option3);
		gv = new GameViews();
		gv.setCenterQues(cenques);
		gv.setScore(tolsco);
		gv.setRemainQuesv(ques);
		gv.setRmainTimesv(time);
		gv.setTimeBar(timebar);
		gv.setOptions(options);
		gc = new GameController(gv,10,10,this);
		gc.StartGame();	
		
		}
	
	@Override
	public void onActivityResult(int requestCode , int resultCode, Intent intent)
	{
		//play again
		if (requestCode == 0 && resultCode == 0)
		{
			if(intent != null)
			{
				gc.PlayAgain();
			}
		}
		//finish
		if(requestCode == 0 && resultCode == 1)
		{
			if(intent != null)
			{
				finish();
				System.exit(0);
			}
		}
	}
	//按下返回键
	public void onBackPressed()
	{
		gc.stopTimer();
		finish();
		System.exit(0);
	}

}