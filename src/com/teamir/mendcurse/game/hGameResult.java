package com.teamir.mendcurse.game;

import java.util.*;
import com.teamir.mendcurse.R;
import com.teamir.mendcurse.game.InvertedTextView;
import com.teamir.mendcurse.game.ctrls.optionLog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.TextView;

public class hGameResult extends Activity
{
	TextView p1_tag;
	TextView p1_tolscore;
	TextView p1_corr;
	TextView p1_incorr;
	TextView p1_pa;
	InvertedTextView p2_tag;
	InvertedTextView p2_tolscore;
	InvertedTextView p2_corr;
	InvertedTextView p2_incorr;
	InvertedTextView p2_pa;
	Intent i = null;
	int tolscoA = 0,tolscoB = 0,correctcA = 0,incorrectcA = 0,correctcB = 0,incorrectcB = 0;
	String textA_tag = null;
	String textB_tag = null;
	String textA_tolscore = null;
	String textB_tolscore = null;
	String textA_correct = null;
	String textB_correct = null;
	String textA_incorrect = null;
	String textB_incorrect = null;
	String text_playagain = "Play again";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hgame_result);
		
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		p1_tag = (TextView)findViewById(R.id.hresult1_tag);
		p1_tolscore = (TextView)findViewById(R.id.hresult1_tolscore);
		p1_corr = (TextView)findViewById(R.id.hresult1_corr);
		p1_incorr = (TextView)findViewById(R.id.hresult1_incorr);
		p1_pa = (TextView)findViewById(R.id.hresult1_playagain);
		p2_tag = (InvertedTextView)findViewById(R.id.hresult2_tag);
		p2_tolscore = (InvertedTextView)findViewById(R.id.hresult2_tolscore);
		p2_corr = (InvertedTextView)findViewById(R.id.hresult2_corr);
		p2_incorr = (InvertedTextView)findViewById(R.id.hresult2_incorr);
		p2_pa = (InvertedTextView)findViewById(R.id.hresult2_playagain);
		//添加监听器
		this.p1_pa.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				hGameResult.this.setResult(0, i);
				finish();
			}
		});
		this.p2_pa.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				hGameResult.this.setResult(0, i);
				finish();
			}
		});
		//处理前一个Activity传来的信息
		i = getIntent();
		Bundle data = i.getExtras();

		@SuppressWarnings("unchecked")
		ArrayList<optionLog> pa = (ArrayList<optionLog>)data.getSerializable("playerA");
		@SuppressWarnings("unchecked")
		ArrayList<optionLog> pb = (ArrayList<optionLog>)data.getSerializable("playerB");
		Log.v("size of playerA", ""+pa.size());
		Log.v("size of playerB",""+pb.size());
		for(optionLog ol:pa){
			if(ol.getResult()){
				this.correctcA++;
				this.tolscoA +=10;
			}
			else{
				this.incorrectcA++;
				this.tolscoA -=10;
			}
		}
		for(optionLog ol:pb){
			if(ol.getResult()){
				this.correctcB++;
				this.tolscoB +=10;
			}
			else{
				this.incorrectcB++;
				this.tolscoB -=10;
			}
		}
		//更新界面相关信息
		if(this.tolscoA == this.tolscoB)
		{
			Log.v("result", "Draw");
			this.textA_tag = this.textB_tag = "Draw";
			setStatu();
			this.p1_pa.setVisibility(0);
			this.p2_pa.setVisibility(0);
		}
		else if(this.tolscoA>this.tolscoB){
			Log.v("result", "playerA Win!");
			textA_tag = "You Win!";
			textB_tag = "You Lost!";
			setStatu();
			this.p1_pa.setVisibility(0);
			this.p2_pa.setVisibility(View.GONE);
		}
		else{
			Log.v("result", "playerB Win!");
			textA_tag = "You Lost!";
			textB_tag = "You Win!";
			setStatu();
			this.p1_pa.setVisibility(View.GONE);
			this.p2_pa.setVisibility(0);
		}
		
	}
	
	public void setStatu()
	{
		this.textA_tolscore = "总分: "+this.tolscoA;
		this.textB_tolscore = "总分: "+this.tolscoB;
		this.textA_correct = "答对个数: " + this.correctcA;
		this.textB_correct = "答对个数: " + this.correctcB;
		this.textA_incorrect = "答错个数: " + this.incorrectcA;
		this.textB_incorrect  = "答错个数: " + this.incorrectcB;
		this.p1_tag.setText(textA_tag);
		this.p1_tolscore.setText(textA_tolscore);
		this.p1_corr.setText(textA_correct);
		this.p1_incorr.setText(textA_incorrect);
		this.p2_tag.setText(textB_tag);
		this.p2_tolscore.setText(textB_tolscore);
		this.p2_corr.setText(textB_correct);
		this.p2_incorr.setText(textB_incorrect);
		this.p1_pa.setText("Play again");
		this.p2_pa.setText("Play again");
	}
	
	//按下返回键
	public void onBackPressed()
	{
		hGameResult.this.setResult(1, i);
		finish();
	}

}