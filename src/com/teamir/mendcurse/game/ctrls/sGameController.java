package com.teamir.mendcurse.game.ctrls;

import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.widget.TextView;

import com.teamir.mendcurse.R;
import com.teamir.mendcurse.game.GameOptionListener;
import com.teamir.mendcurse.game.GameResult;

public class sGameController extends GameController
{
	private boolean opensound = false;
	private final String ANONYMOUS = "anonymous";
	Player myPlayer;
	public SoundPool soundPool;
	public HashMap<Integer , Integer> soundMap 
		= new HashMap<Integer , Integer>();

	public sGameController(GameViews gv,int t,int rq,Activity ac)
	{
		this.gameviews = gv;
		this.Times = t;
		this.RemainTimes = (float)t;
		this.Quess = this.RemainQues = rq;
		this.ac = ac;
		this.Questions = new QuestionsGenerate(this.ac,this.Quess).getQuestions();
		TextView[] optionViews = gameviews.getOptions();
		this.loadSound();
//		for(int i = 0; i<4;i++)
//			optionViews[i].setOnClickListener(new GameOptionListener(i,this));
	}
	public sGameController(GameViews gv,Player myPlayer,int t,int rq,Activity ac,boolean opensound)
	{
		this(gv, t, rq,ac);
		this.myPlayer = myPlayer;
		for(int i = 0;i<4;i++)
			this.gameviews.getOptions()[i].setOnClickListener(new GameOptionListener(i,this,this.myPlayer));
		myPlayer.ready(true);
		this.opensound = opensound;
		if(myPlayer.getName().equals(ANONYMOUS))
		{
//			StartGame();
		}
		
	}
	//重载方法,下同
	public void CommitOption(int thisoption)
	{
		this.stopTimer();
		this.timerflag = true;
		this.QuesLocked = true;
		this.showAnswer(this.myPlayer,this.currCorrectOption, thisoption, true);
		this.updateStatus();
		this.handler.postDelayed(nextQuesProgress, 500L);
	}
	public void CommitOption(int thisoption,Player p)
	{
		this.stopTimer();
		this.timerflag = true;
		this.QuesLocked = true;
		this.showAnswer(p,this.currCorrectOption, thisoption, true);
		this.updateStatus();
		this.handler.postDelayed(nextQuesProgress, 500L);
	}
	//重载方法,判断对错，并且更新相关变量
	public void showAnswer(Player p,int correctoption,int curroption,boolean ischoice)
	{
		//正确，加分
		if(p.showResult(this.currQues,curroption, correctoption, ischoice))
		{
			this.tolScore +=5;
			this.correctc++;
			if(this.opensound)
				this.playRightSound();
		}
		else{
			this.tolScore -=3;
			this.incorrectc++;
			if(this.opensound)
				this.playErrorSound();
		}
		
	}
	@Override
	public void gameOver()
	{
		this.stopTimer();
		Intent i = new Intent(this.ac,GameResult.class);
		Bundle res = new Bundle();
		res.putSerializable("resultlog", this.myPlayer.getOptionLogs());
		res.putSerializable("playername", this.myPlayer.getName());
/*		res.putInt("score", this.tolScore);
		res.putInt("correctc", this.correctc);
		res.putInt("incorrectc",this.incorrectc);    */
		i.putExtras(res);
		this.ac.startActivityForResult(i,0);
	}
	@Override
	public void PlayAgain()
	{
		sGameController spa = new sGameController(this.gameviews,this.myPlayer,this.Times,this.Quess,this.ac,this.opensound);
		spa.StartGame();
	}
	
	public void loadSound()
	{
		soundPool = new SoundPool(2, AudioManager.STREAM_SYSTEM, 5);
		soundMap.put(1 , soundPool.load(this.ac, R.raw.answer_error, 1));
		soundMap.put(2 , soundPool.load(this.ac, R.raw.answer_right, 1));
	}
	public  void playRightSound()
	{
		soundPool.play(soundMap.get(2), 1, 1, 0, 0, 1);
	}
	public void playErrorSound()
	{
		soundPool.play(soundMap.get(1), 1, 1, 0, 0, 1);
	}
}