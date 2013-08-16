package com.teamir.mendcurse.game.ctrls;

import java.util.ArrayList;

import com.teamir.mendcurse.R;

import android.util.Log;
import android.widget.TextView;

public class Player
{
	int playerID;
	String name;
	int Score = 0;    //历史最高分
	int myScore = 0;   //本次游戏得分
	TextView[] myViews = null;
	
	public ArrayList<optionLog> optionLogs = new ArrayList<optionLog>();
	
	public Player()
	{
	}
	public Player(int pid,String n)
	{
		this.playerID = pid;
		this.name = n;
	}
	public Player(String n)
	{
		this.name = n;
	}
	public void setPlayerID(int id)
	{
		this.playerID = id;
	}
	public int getPlayerID()
	{
		return this.playerID;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getName()
	{
		return this.name;
	}
	public void setScore(int s)
	{
		this.Score = s;
	}
	public int getScore()
	{
		return this.Score;
	}
	public void setMyViews(TextView[] tv)
	{
		this.myViews = tv;
	}
	public TextView[] getMyViews()
	{
		return this.myViews;
	}
	public void commit(int myselect)
	{
		
	}
	public boolean showResult(int myselect,int corrselect,boolean ischoice)
	{
		Log.v("enter player.showresult", "enter player.showresult");
		optionLog myOption = new optionLog();
		myOption.setMySelection(myselect);
		myOption.setCorrSelection(corrselect);
		
		if(ischoice)
		{
			if(myselect == corrselect)
			{
				this.myViews[corrselect].setBackgroundResource(R.color.green);
				myOption.setResult(true);
				optionLogs.add(myOption);
	            return true;
			}
			else
			{
	        		for(int i = 0;i<4;i++){
						int color = (i == corrselect)?R.color.green:R.color.red;
						this.myViews[i].setBackgroundResource(color);
				}
	        	myOption.setResult(false);
	        	optionLogs.add(myOption);
				return false;
			}
		}
		else{
			for(int i = 0;i<4;i++){
				int color = (i == corrselect)?R.color.green:R.color.red;
				this.myViews[i].setBackgroundResource(color);
			}
			myOption.setResult(false);
			optionLogs.add(myOption);
			return false;
		}
		
	}
	//在headsup模块中由于对方提交致自己分数变化
	public boolean showResult(int corrselect,boolean invert)
	{
		//对方打错，自己加分
		if(invert)
		{
			optionLog myOption = new optionLog();
			myOption.setMySelection(corrselect);
			myOption.setCorrSelection(corrselect);
			myOption.setResult(true);
			optionLogs.add(myOption);
			for(int i = 0;i<4;i++){
				this.myViews[i].setBackgroundResource(R.color.green);
			}
            return true;
		}
		//对方答对自己减分
		else
		{
			optionLog myOption = new optionLog();
			myOption.setMySelection(0);
			myOption.setCorrSelection(corrselect);
			myOption.setResult(false);
			optionLogs.add(myOption);
			for(int i = 0;i<4;i++){
				this.myViews[i].setBackgroundResource(R.color.red);
			}
			return false;
		}
		
	}
	public void ready()
	{
		for(int i = 0;i<4;i++)
			this.myViews[i].setBackgroundResource(R.color.lightblue);
	}
	
	@Override
	public boolean equals(Object p)
	{
		Player player = (Player)p;
		return (this.getMyViews()[0].getId() == player.getMyViews()[0].getId());
	}
}
//记录player答题情况的数据结构
class optionLog
{
	Question question = null;
	int mySelection;
	int corrSelection;
	boolean result = false;
	
	public optionLog()
	{
		
	}
	public optionLog(Question ques,int ms,int cs)
	{
		this.question = ques;
		this.mySelection = ms;
		this.corrSelection = cs;
	}
	public void setQuestion(Question q)
	{
		this.question = q;
	}
	public Question getQuestion()
	{
		return this.question;
	}
	public void setMySelection(int s)
	{
		this.mySelection = s;
	}
	public int getMySelection()
	{
		return this.mySelection;
	}
	public void setCorrSelection(int s)
	{
		this.corrSelection = s;
	}
	public int getCorrSelection()
	{
		return this.corrSelection;
	}
	public void setResult(boolean r)
	{
		this.result = r;
	}
	public boolean getResult()
	{
		return this.result;
	}
}