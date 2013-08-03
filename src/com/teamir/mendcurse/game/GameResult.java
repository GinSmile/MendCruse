package com.teamir.mendcurse.game;

import com.teamir.mendcurse.*;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class GameResult extends Activity
{
	TextView score;
	TextView rigques;
	TextView wroques;
	Button playagain;
	Button back;
	Intent i;
	int tolscore,rightc,wrongc;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_result);
		//获取各view
		score = (TextView)findViewById(R.id.gamres_tolsco);
		rigques = (TextView)findViewById(R.id.gamres_rigques);
		wroques = (TextView)findViewById(R.id.gamres_wroques);
		playagain = (Button)findViewById(R.id.gamres_playagain);
		back = (Button)findViewById(R.id.gamres_back);
		//处理前一个activity传来的数据
		i = getIntent();
		Bundle data = i.getExtras();
		tolscore = data.getInt("score");
		rightc = data.getInt("correctc");
		wrongc = data.getInt("incorrectc");
		String scoreText = "总分: "+tolscore;
		String rightcText = "答对个数: " + rightc;
		String wrongcText = "答错个数: " + wrongc;
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
	
	//按下返回键
	public void onBackPressed()
	{
		GameResult.this.setResult(1, i);
		finish();
	}

}