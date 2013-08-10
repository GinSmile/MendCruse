package com.teamir.mendcurse.game.ctrls;

import android.widget.ProgressBar;
import android.widget.TextView;

public class GameViews
{
	//ȫ�ֶ���
	TextView Score;
	TextView RemainQuesv;
	TextView RemainTimesv;
	ProgressBar TimeBar;
	TextView CenterQues;
	TextView[] options = new TextView[4];
	
	public void GameView()
	{	
	}
	public void setScore(TextView Score)
	{
		this.Score = Score;
	}
	public TextView getScore()
	{
		return this.Score;
	}
	public void setRemainQuesv(TextView rq)
	{
		this.RemainQuesv = rq;
	}
	public TextView getRemainQuesv()
	{
		return this.RemainQuesv;
	}
	public void setRmainTimesv(TextView rt)
	{
		this.RemainTimesv = rt;
	}
	public TextView getRemainTimesv()
	{
		return this.RemainTimesv;
	}
	public void setTimeBar(ProgressBar tb)
	{
		this.TimeBar = tb;
	}
	public ProgressBar getTimeBar()
	{
		return this.TimeBar;
	}
	public void setOptions(TextView[] op)
	{
		this.options = op;
	}
	public TextView[] getOptions()
	{
		return this.options;
	}
	public void setCenterQues(TextView cq)
	{
		this.CenterQues = cq;
	}
	public TextView getCenterQues()
	{
		return this.CenterQues;
	}
}