package com.teamir.mendcurse.game.ctrls;

import com.teamir.mendcurse.game.InvertedTextView;
import android.widget.TextView;

public class hGameViews extends GameViews
{
	//headsup中特有的对象
	TextView ScoreA;
	InvertedTextView ScoreB;
	TextView QuesA;
	InvertedTextView QuesB;
	TextView[] optionsA = new TextView[4];
	InvertedTextView[] optionsB = new InvertedTextView[4];
	
	public void hGameView()
	{	
	}
	public void setScoreA(TextView Score)
	{
		this.ScoreA = Score;
	}
	public TextView getScoreA()
	{
		return this.ScoreA;
	}
	public void setScoreB(InvertedTextView Score)
	{
		this.ScoreB = Score;
	}
	public InvertedTextView getScoreB()
	{
		return this.ScoreB;
	}
	public void setOptionsA(TextView[] op)
	{
		this.optionsA = op;
	}
	public TextView[] getOptionsA()
	{
		return this.optionsA;
	}
	public void setOptionsB(InvertedTextView[] op)
	{
		this.optionsB = op;
	}
	public InvertedTextView[] getOptionsB()
	{
		return this.optionsB;
	}
	public void setQuesA(TextView cq)
	{
		this.QuesA = cq;
	}
	public TextView getQuesA()
	{
		return this.QuesA;
	}
	public void setQuesB(InvertedTextView cq)
	{
		this.QuesB = cq;
	}
	public InvertedTextView getQuesB()
	{
		return this.QuesB;
	}
}