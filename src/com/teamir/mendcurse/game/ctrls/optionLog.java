package com.teamir.mendcurse.game.ctrls;

import java.io.Serializable;

public class optionLog implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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