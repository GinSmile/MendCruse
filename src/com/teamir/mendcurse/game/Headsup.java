package com.teamir.mendcurse.game;

import com.teamir.mendcurse.R;
import com.teamir.mendcurse.game.ctrls.*;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;

public class Headsup extends Activity {
	
	hGameViews hgv = null;
	hGameController hgc;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.headsup);
		
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		ProgressBar  tb = (ProgressBar)findViewById(R.id.headsup_timebar);
		InvertedTextView ScoreB = (InvertedTextView)findViewById(R.id.headsup2_score);
		InvertedTextView quesb = (InvertedTextView)findViewById(R.id.headsup2_ques);
		InvertedTextView[] optionsB = new InvertedTextView[4];
		optionsB[0] = (InvertedTextView)findViewById(R.id.headsup2_option0);
		optionsB[1] = (InvertedTextView)findViewById(R.id.headsup2_option1);
		optionsB[2] = (InvertedTextView)findViewById(R.id.headsup2_option2);
		optionsB[3] = (InvertedTextView)findViewById(R.id.headsup2_option3);
		TextView ScoreA = (TextView)findViewById(R.id.headsup1_score);
		TextView quesa = (TextView)findViewById(R.id.headsup1_ques);
		TextView[] optionsA = new TextView[4];
		optionsA[0] = (TextView)findViewById(R.id.headsup1_option0);
		optionsA[1] = (TextView)findViewById(R.id.headsup1_option1);
		optionsA[2] = (TextView)findViewById(R.id.headsup1_option2);
		optionsA[3] = (TextView)findViewById(R.id.headsup1_option3);
		
		hgv = new hGameViews();
		Player playerA = new Player("anonymous");
		Player playerB = new Player("anonymous");
		
		hgv.setOptionsB(optionsB);
		hgv.setScoreB(ScoreB);
		hgv.setQuesB(quesb);
		hgv.setTimeBar(tb);
		hgv.setOptionsA(optionsA);
		hgv.setQuesA(quesa);
		hgv.setScoreA(ScoreA);
		playerA.setMyViews(optionsA);
		playerB.setMyViews(optionsB);
		
		
		hgc = new hGameController(hgv,playerA,playerB,10,10,this);
	    hgc.StartGame();
	}
	
	@Override
	public void onActivityResult(int requestCode , int resultCode, Intent intent)
	{
		//play again
		if (requestCode == 0 && resultCode == 0)
		{
			if(intent != null)
			{
				hgc.PlayAgain();
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
		hgc.stopTimer();
		finish();
		System.exit(0);
	}

}