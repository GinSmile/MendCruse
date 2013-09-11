package com.teamir.mendcurse.game.ctrls;

import java.util.*;

import com.teamir.mendcurse.R;
import com.teamir.mendcurse.game.*;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;

public class GameController
{
	//全局对象
	int currCorrectOption = 0;
	int tolScore = 0;
	int Times = 3;
	float RemainTimes = 3;
	int Quess = 3;
	int RemainQues = 3;
	int Status = 0;
	int correctc = 0;
	int incorrectc = 0;
	public boolean QuesLocked = false;
	boolean timerflag = false;
	Activity ac = null;
	Question currQues = null;
	Handler handler = new Handler();
	GameViews gameviews = null;
	Stack<Question> Questions = null;
	
	protected Runnable timeProgress = new Runnable()    //计时runnable
	{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			GameController.this.Status +=5;
			GameController.this.RemainTimes = (float)(100-GameController.this.Status)/10;
			if(GameController.this.Status <100)
			{
				GameController.this.updateStatus();
			}
			//更新状态
			if(GameController.this.Status ==100)
			{
				GameController.this.QuesLocked = true;				
				GameController.this.CommitOption(0, false);
				return;
			//	GameController.this.updateStatus();
			}
			if(GameController.this.Status >100)
			{
				GameController.this.RemainTimes = GameController.this.Times;
				if(GameController.this.Questions.size()>0)
					GameController.this.gotoNextQues();
				else{
					GameController.this.gameOver();
					return;
				}
					
			}
			GameController.this.handler.postDelayed(this,500L);
		}
		
	};
	protected Runnable nextQuesProgress = new Runnable()   //下一个问题
	{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			if(GameController.this.Questions.size()>0)
				GameController.this.gotoNextQues();
			else
				GameController.this.gameOver();
		}
		
	};
	//参数分别为 view组件，倒计时间，问题总数，调用此controller的activity
	public GameController(GameViews gv,int t,int rq,Activity ac)
	{
		this.gameviews = gv;
		this.Times = t;
		this.RemainTimes = (float)t;
		this.Quess = this.RemainQues = rq;
		this.ac = ac;
		this.Questions = new QuestionsGenerate(this.ac,this.Quess).getQuestions();	
		TextView[] optionViews = gameviews.getOptions();
		for(int i = 0; i<4;i++)
			optionViews[i].setOnClickListener(new GameOptionListener(i,this));
	}
	public GameController()
	{		
	}
	public void CommitOption(int thisoption,boolean ischoice)
	{
		this.stopTimer();
		this.timerflag = true;
		this.QuesLocked = true;
		this.showAnswer(this.currCorrectOption, thisoption, ischoice);
		this.updateStatus();
		this.handler.postDelayed(nextQuesProgress, 500L);
	}
	public void StartGame()
	{
		updateStatus();
//		for(int i = 0;i<4;i++)
//			this.gameviews.getOptions()[i].setBackgroundResource(R.color.blue);
		this.currQues = this.Questions.pop();
		this.currCorrectOption = this.currQues.getCorrectoption();
		this.showCurrQues(currQues);
		this.handler.postDelayed(timeProgress, 500L);
	}
	public void PlayAgain()
	{
		GameController pa = new GameController(this.gameviews,this.Times,this.Quess,this.ac);
		pa.StartGame();
	}
	public void showCurrQues(Question q)
	{
		this.gameviews.getCenterQues().setText(q.getQuestionText());
		(this.gameviews.getOptions())[0].setText(q.getOption0());
		(this.gameviews.getOptions())[1].setText(q.getOption1());
		(this.gameviews.getOptions())[2].setText(q.getOption2());
		(this.gameviews.getOptions())[3].setText(q.getOption3());
	}
	public void updateSore()
	{
		TextView score = gameviews.getScore();
		String text = "总分: "+ tolScore;
		score.setText(text);
	}
	public void updateRemainQues()
	{
		TextView rq = gameviews.getRemainQuesv();
		String text = "问题: "+ RemainQues + "/"+Quess;
		rq.setText(text);
	}
	public void updateRemainTimes()
	{
		TextView rt = gameviews.getRemainTimesv();
		String text = "时间: "+RemainTimes+"/"+Times;
		rt.setText(text);
	}
	public void updateTimeBar()
	{
		ProgressBar pb = gameviews.getTimeBar();
		pb.setProgress(Status);
	}
	public void updateStatus()
	{
		updateSore();
		updateRemainQues();
		updateRemainTimes();
		updateTimeBar();
	}
	public void gotoNextQues()
	{
		this.Status = 0;
		GameController.this.RemainQues--;
		this.updateStatus();
		for(int i = 0;i<4;i++)
			this.gameviews.getOptions()[i].setBackgroundResource(R.drawable.button_gradient_blue);
		this.QuesLocked = false;
		this.nextQues();
		if(timerflag)
			this.startTimer();
	}
	public void nextQues()
	{
		this.currQues = this.Questions.pop();
		this.currCorrectOption = this.currQues.getCorrectoption();
		this.showCurrQues(currQues);
	}
	public void showAnswer(int correctoption,int curroption,boolean ischoice)
	{
		if(ischoice)
		{
			if(correctoption == curroption)
			{
				this.gameviews.getOptions()[correctoption].setBackgroundResource(R.drawable.button_gradient_green);
				this.tolScore +=5;
				this.correctc++;
			}
			else
			{
	        		for(int i = 0;i<4;i++){
						int color = (i == correctoption)?R.drawable.button_gradient_green:R.drawable.button_gradient_red;
						this.gameviews.getOptions()[i].setBackgroundResource(color);
				}
				this.tolScore -=3;
				this.incorrectc++;
			}
		}
		else{
			for(int i = 0;i<4;i++){
				int color = (i == correctoption)?R.drawable.button_gradient_green:R.drawable.button_gradient_red;
				this.gameviews.getOptions()[i].setBackgroundResource(color);
		}
			this.tolScore -=3;
			this.incorrectc++;
		}
	}
	public void startTimer()
	{
		this.timerflag = false;
		this.handler.postDelayed(this.timeProgress, 500L);
	}
	public void stopTimer()
	{
		this.handler.removeCallbacks(this.timeProgress);
	}
	public void gameOver()
	{
		this.stopTimer();
		Intent i = new Intent(this.ac,GameResult.class);
		Bundle res = new Bundle();
		res.putInt("score", this.tolScore);
		res.putInt("correctc", this.correctc);
		res.putInt("incorrectc",this.incorrectc);
		i.putExtras(res);
		this.ac.startActivityForResult(i,0);
	}
}