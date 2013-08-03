package com.teamir.mendcurse.game;


import com.teamir.mendcurse.game.ctrls.GameController;

import android.view.View;
import android.view.View.OnClickListener;


public class GameOptionListener implements OnClickListener
{
	int thisoption;
	GameController gc = null;
	public GameOptionListener(int option,GameController gc)
	{
		this.thisoption = option;
		this.gc = gc;
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		//显示结果
		if(!gc.QuesLocked)
			gc.CommitOption(thisoption);
	}
}