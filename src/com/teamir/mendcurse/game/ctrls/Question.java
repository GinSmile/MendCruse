package com.teamir.mendcurse.game.ctrls;

import java.io.Serializable;

public class Question implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int correctoption = 0;
	String questionText = null;
	String option0 = null;
	String option1 = null;
	String option2 = null;
	String option3 = null;
	
	public Question()
	{
		
	}
	
	public void setCorrectoption(int r)
	{
		this.correctoption = r;
	}
	public int getCorrectoption()
	{
		return this.correctoption;
	}
	public void setQuestionText(String s)
	{
		this.questionText = s;
	}
	public String getQuestionText()
	{
		return this.questionText;
	}
	public void setOption0(String o)
	{
		this.option0 = o;
	}
	public String getOption0()
	{
		return this.option0;
	}
	public void setOption1(String o)
	{
		this.option1 = o;
	}
	public String getOption1()
	{
		return this.option1;
	}
	public void setOption2(String o)
	{
		this.option2 = o;
	}
	public String getOption2()
	{
		return this.option2;
	}
	public void setOption3(String o)
	{
		this.option3= o;
	}
	public String getOption3()
	{
		return this.option3;
	}
	public void setOptoin(int which,String str)
	{
		switch(which)
		{
		case 0:this.setOption0(str);break;
		case 1:this.setOption1(str);break;
		case 2:this.setOption2(str);break;
		case 3:this.setOption3(str);break;
		}
	}
	public String getOption(int which)
	{
		String str = null;
		switch(which)
		{
		case 0:str = this.option0;break;
		case 1:str = this.option1;break;
		case 2:str = this.option2;break;
		case 3:str = this.option3;break;
		}
		return str;
	}
}