package com.teamir.mendcurse.game;

import java.util.ArrayList;

import com.teamir.mendcurse.*;
import com.teamir.mendcurse.game.ctrls.*;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
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
	int tolscore = 0,correctc = 0,incorrectc  = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_result);
		
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		//获取各view
		score = (TextView)findViewById(R.id.gamres_tolsco);
		rigques = (TextView)findViewById(R.id.gamres_rigques);
		wroques = (TextView)findViewById(R.id.gamres_wroques);
		playagain = (Button)findViewById(R.id.gamres_playagain);
		back = (Button)findViewById(R.id.gamres_back);
		//处理前一个activity传来的数据
		i = getIntent();
		Bundle data = i.getExtras();
/*		tolscore = data.getInt("score");
		rightc = data.getInt("correctc");
		wrongc = data.getInt("incorrectc");    */
		
		@SuppressWarnings("unchecked")
		ArrayList<optionLog> result = (ArrayList<optionLog>)data.getSerializable("resultlog");
		Log.v("result size: ",""+result.size());
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
		String rightcText = "答对个数: " + this.correctc;
		String wrongcText = "答错个数: " + this.incorrectc;
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