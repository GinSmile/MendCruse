package com.teamir.mendcurse.game.ctrls;

import java.util.ArrayList;
import com.teamir.mendcurse.R;
import android.widget.TextView;
import java.io.Serializable;

public class Player implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int playerID;
	String name;
	int Score = 0;    //历史最高分
	int myScore = 0;   //本次游戏得分
	int correctc = 0;   //答对的题数
	int incorrectc = 0;  //答错 的题数
	TextView[] myViews = null;
	
	protected ArrayList<optionLog> optionLogs = new ArrayList<optionLog>();  //记录答题情况
	
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
	public void setMyScore(int s)
	{
		this.myScore = s;
	}
	public int getMyScore()
	{
		return this.myScore;
	}
	public int getCorrectc()
	{
		return this.correctc;
	}
	public int getIncorrectc()
	{
		return this.incorrectc;
	}
	public ArrayList<optionLog> getOptionLogs()
	{
		return this.optionLogs;
	}
	public void commit(int myselect)
	{
		
	}
	//参数分别为问题、我的选择、正确选择、主动选择标记
	public boolean showResult(Question ques,int myselect,int corrselect,boolean ischoice)
	{
//		Log.v("enter player.showresult", "enter player.showresult");
		optionLog myOption = new optionLog();
		myOption.setMySelection(myselect);
		myOption.setCorrSelection(corrselect);
		myOption.setQuestion(ques);
		
		if(ischoice)
		{
			if(myselect == corrselect)
			{
				this.myViews[corrselect].setBackgroundResource(R.drawable.button_gradient_green);
				myOption.setResult(true);
				optionLogs.add(myOption);
				this.correctc++;
				this.myScore +=5;
	            return true;
			}
			else
			{
	        		for(int i = 0;i<4;i++){
						int color = (i == corrselect)?R.drawable.button_gradient_green:R.drawable.button_gradient_red;
						this.myViews[i].setBackgroundResource(color);
				}
	        	myOption.setResult(false);
	        	optionLogs.add(myOption);
	        	this.incorrectc++;
	        	this.myScore -=3;
				return false;
			}
		}
		else{
			for(int i = 0;i<4;i++){
				int color = (i == corrselect)?R.drawable.button_gradient_green:R.drawable.button_gradient_red;
				this.myViews[i].setBackgroundResource(color);
			}
			myOption.setResult(false);
			optionLogs.add(myOption);
			this.incorrectc++;
			this.myScore -=3;
			return false;
		}
		
	}
	//在headsup模块中由于对方提交致自己分数变化
	public boolean showResult(Question ques,int corrselect,boolean invert)
	{
		//对方打错，自己加分
		if(invert)
		{
			optionLog myOption = new optionLog();
			myOption.setQuestion(ques);
			myOption.setMySelection(corrselect);
			myOption.setCorrSelection(corrselect);
			myOption.setResult(true);
			optionLogs.add(myOption);
			for(int i = 0;i<4;i++){
				this.myViews[i].setBackgroundResource(R.color.green);
			}
			this.correctc++;
			this.myScore +=5;
            return true;
		}
		//对方答对自己减分
		else
		{
			optionLog myOption = new optionLog();
			myOption.setQuestion(ques);
			myOption.setMySelection(0);
			myOption.setCorrSelection(corrselect);
			myOption.setResult(false);
			optionLogs.add(myOption);
			for(int i = 0;i<4;i++){
				this.myViews[i].setBackgroundResource(R.color.red);
			}
			this.incorrectc++;
			this.myScore -=3;
			return false;
		}
		
	}
	public void ready(boolean isnewgame)
	{
		if(isnewgame){
			this.optionLogs = new ArrayList<optionLog>();
			this.correctc = this.incorrectc = this.myScore = 0;
		}
		for(int i = 0;i<4;i++)
			this.myViews[i].setBackgroundResource(R.drawable.button_gradient_blue);
	}
	
	@Override
	public boolean equals(Object p)
	{
		Player player = (Player)p;
		return (this.getMyViews()[0].getId() == player.getMyViews()[0].getId());
	}
}