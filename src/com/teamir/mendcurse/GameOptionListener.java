package com.teamir.mendcurse;

import android.view.View;
import android.view.View.OnClickListener;


public class GameOptionListener implements OnClickListener
{
	int thisoption;
	Game ga = null;
	public GameOptionListener(int option,Game ga)
	{
		this.thisoption = option;
		this.ga = ga;
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		//显示结果
		ga.judge(ga.rightoption, thisoption,true);
		//更新得分
		ga.updateScore(ga.rightoption == thisoption);
		//发送信息
		ga.sendOptmess();
	}
}