package com.teamir.mendcurse.game.ctrls;

import com.teamir.mendcurse.game.*;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

public class hGameController extends GameController
{
	//新对象
	int ScoreA = 0;
	int ScoreB = 0;
	int correctcA = 0;
	int incorrectcA = 0;
	int correctcB = 0;
	int incorrectcB = 0;
	hGameViews hgameviews = null;
	Player playerA = null;
	Player playerB = null;
	
	public hGameController(hGameViews hgv,Player pa,Player pb,int t,int rq,Activity ac)
	{
		this.hgameviews = hgv;
		this.Times = t;
		this.RemainTimes = (float)t;
		this.Quess = this.RemainQues = rq;
		this.playerA = pa;
		this.playerB = pb;
		this.ac = ac;
		this.Questions = new QuestionsGenerate(this.ac,this.Quess).getQuestions();
		TextView[] optionViewsA = this.hgameviews.getOptionsA();
		InvertedTextView[] optionViewsB = this.hgameviews.getOptionsB();
		//为每个选项view注册监听器
		for(int i = 0; i<4;i++){
			optionViewsA[i].setOnClickListener(new GameOptionListener(i,this,this.playerA,this.playerB));
			optionViewsB[i].setOnClickListener(new GameOptionListener(i,this,this.playerB,this.playerA));
		}
		this.playerA.ready(true);
		this.playerB.ready(true
				);
	}
	public hGameController()
	{
		
	}
	//时间到双方都没有回答
	@Override
	public void CommitOption(int thisoption,boolean ischoice)
	{
		this.stopTimer();
		this.timerflag = true;
		this.QuesLocked = true;
		this.showAnswer(this.playerA,this.playerB,this.currCorrectOption, thisoption, false);
		this.updateStatus();
		this.handler.postDelayed(nextQuesProgress, 500L);
	}
	//其中一方提交回答
	public void CommitOption(int thisoption,Player p,Player anotherplayer)
	{
		Log.v("correcOption", ""+this.currCorrectOption);
		this.stopTimer();
		this.timerflag = true;
		this.QuesLocked = true;
		this.showAnswer(p,anotherplayer,this.currCorrectOption, thisoption, true);
		this.updateStatus();
		this.handler.postDelayed(nextQuesProgress, 500L);
	}
	//判断对错，并且更新相关变量
	public void showAnswer(Player p,Player anotherp,int correctoption,int curroption,boolean ischoice)
	{
		//面对提交者,正确，加分
		if(p.showResult(this.currQues,curroption, correctoption, ischoice))
		{
			if(p.equals(playerA)){
				Log.v("playerA", "正确,加分");
				this.ScoreA +=10;
				this.correctcA++;
				//另一个直接减分
				anotherp.showResult(this.currQues,this.currCorrectOption,false);
				this.ScoreB -=10;
				this.incorrectcB++;
			}
			else
			{
				Log.v("playerB", "正确,加分");
				this.ScoreB +=10;
				this.correctcB++;
				anotherp.showResult(this.currQues,this.currCorrectOption,false);
				this.ScoreA -=10;
				this.incorrectcA++;
			}
		}
		//面对提交者,错误,减分
		else{
			
			if(p.equals(playerA)){
				Log.v("playerA", "错误,减分");
				this.ScoreA -=10;
				this.incorrectcA++;
				if(ischoice){
					anotherp.showResult(this.currQues,this.currCorrectOption,true);
					this.ScoreB +=10;
					this.correctcB++;
				}
				else{
					anotherp.showResult(this.currQues,curroption, correctoption, false);
					this.ScoreB -=10;
					this.correctcB--;
				}
				
			}
			else
			{
				Log.v("playerB", "错误,减分");
				this.ScoreB -=10;
				this.incorrectcB++;
				if(ischoice){
					anotherp.showResult(this.currQues,this.currCorrectOption,true);
					this.ScoreB +=10;
					this.correctcB++;
				}
				else{
					anotherp.showResult(this.currQues,curroption, correctoption, false);
					this.ScoreB -=10;
					this.correctcB--;
				}
			}
		}
		
		
	}
	@Override
	public void updateRemainQues()
	{
	}
	@Override
	public void updateRemainTimes()
	{
	}
	@Override
	public void updateTimeBar()
	{
		ProgressBar pb = hgameviews.getTimeBar();
		if(pb != null)
			pb.setProgress(Status);
	}
	@Override
	public void updateSore()
	{
		TextView scoreA = hgameviews.getScoreA();
		InvertedTextView scoreB = hgameviews.getScoreB();
		String textA = ""+ this.playerA.getMyScore();
		String textB = ""+ this.playerB.getMyScore();
		scoreA.setText(textA);
		scoreB.setText(textB);
}
	@Override
	public void showCurrQues(Question q)
	{
		//AB都要设置
		this.hgameviews.getQuesA().setText(q.getQuestionText());
		(this.hgameviews.getOptionsA())[0].setText(q.getOption0());
		(this.hgameviews.getOptionsA())[1].setText(q.getOption1());
		(this.hgameviews.getOptionsA())[2].setText(q.getOption2());
		(this.hgameviews.getOptionsA())[3].setText(q.getOption3());
		this.hgameviews.getQuesB().setText(q.getQuestionText());
		(this.hgameviews.getOptionsB())[0].setText(q.getOption0());
		(this.hgameviews.getOptionsB())[1].setText(q.getOption1());
		(this.hgameviews.getOptionsB())[2].setText(q.getOption2());
		(this.hgameviews.getOptionsB())[3].setText(q.getOption3());
	}
	@Override
	public void gotoNextQues()
	{
		this.Status = 0;
		this.RemainQues--;
		this.updateStatus();
		this.playerA.ready(false);
		this.playerB.ready(false);
		this.QuesLocked = false;
		this.nextQues();
		if(timerflag)
			this.startTimer();
	}
	@Override
	public void gameOver()
	{
		Log.v("print playerB", this.playerB.getOptionLogs().toString());
		Log.v("size of playerB", this.playerB.getOptionLogs().size()+"");
		this.stopTimer();
		Intent i = new Intent(this.ac,hGameResult.class);
		Bundle res = new Bundle();
		res.putSerializable("playerA", this.playerA.getOptionLogs());
		res.putSerializable("playerB", this.playerB.getOptionLogs());
/*		res.putInt("score", this.tolScore);
		res.putInt("correctc", this.correctc);
		res.putInt("incorrectc",this.incorrectc);    */
		i.putExtras(res);
		this.ac.startActivityForResult(i,0);
	}
	@Override
	public void PlayAgain()
	{
		hGameController hpa = new hGameController(this.hgameviews,this.playerA,this.playerB,this.Times,this.Quess,this.ac);
		hpa.StartGame();
	}
}