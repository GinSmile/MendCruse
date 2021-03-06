package com.teamir.mendcurse.game;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.teamir.mendcurse.R;
import com.teamir.mendcurse.game.ctrls.GameViews;
import com.teamir.mendcurse.game.ctrls.Player;
import com.teamir.mendcurse.game.ctrls.sGameController;


public class SinglePlayer extends Activity {
	//全局对象
	private TextView tolsco;
	private TextView ques;
	private TextView time;
	private ProgressBar timebar;
	private TextView cenques;
	private TextView[] options = new TextView[4];   //4个选项view
	
	private String playername = null;
	private int quescount = 20;
	private boolean opensound = false;
	
	GameViews gv = null;
	sGameController sgc = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.singleplayer_testui);
		
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		Intent i = getIntent();
		Bundle bundle = i.getExtras();
		playername = bundle.getString("playername");
		quescount = bundle.getInt("quescount");
		opensound = bundle.getBoolean("opensound");
		
		//获取各view
		tolsco = (TextView)findViewById(R.id.tgame_tolsco);
		ques = (TextView)findViewById(R.id.tgame_ques);
		time = (TextView)findViewById(R.id.tgame_time);
		timebar = (ProgressBar)findViewById(R.id.tgame_timebar);
		cenques = (TextView)findViewById(R.id.tgame_cenques);
		options[0] = (TextView)findViewById(R.id.tgame_option0);
		options[1] = (TextView)findViewById(R.id.tgame_option1);
		options[2] = (TextView)findViewById(R.id.tgame_option2);
		options[3] = (TextView)findViewById(R.id.tgame_option3);
		gv = new GameViews();
		gv.setCenterQues(cenques);
		gv.setScore(tolsco);
		gv.setRemainQuesv(ques);
		gv.setRmainTimesv(time);
		gv.setTimeBar(timebar);
		gv.setOptions(options);
		Player player = new Player(playername);
		player.setMyViews(options);
		sgc = new sGameController(gv,player,10,quescount,this,opensound);
		sgc.StartGame();	
		
		}
	
	@Override
	public void onActivityResult(int requestCode , int resultCode, Intent intent)
	{
		//play again
		if (requestCode == 0 && resultCode == 0)
		{
			if(intent != null)
			{
				sgc.PlayAgain();
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
		sgc.stopTimer();
		finish();
		System.exit(0);
	}

}