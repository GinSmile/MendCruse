package com.teamir.mendcurse.game;



import com.teamir.mendcurse.game.ctrls.*;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;


public class GameOptionListener implements OnClickListener
{
	int thisoption;
	GameController gc = null;
	sGameController pgc = null;
	hGameController hgc = null;
	Player player = null;
	Player anotherPlayer = null;
	
	public GameOptionListener(int option,sGameController sgc,Player p)
	{
		this.thisoption = option;
		this.pgc = pgc;
		this.player = p;
	}
	public GameOptionListener(int option,hGameController hgc,Player p,Player anp)
	{
		this.thisoption = option;
		this.hgc = hgc;
		this.player = p;
		this.anotherPlayer = anp;
	}
	public GameOptionListener(int option,GameController gc)
	{
		this.thisoption = option;
		this.gc = gc;
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Log.v("onClick", "onClick");
		if(this.player == null){
			if(!gc.QuesLocked){
				Log.v("gc commit", "gc commit");
				gc.CommitOption(thisoption,true);
			}
		}
		else if(pgc != null){
			if(!pgc.QuesLocked){
				Log.v("pgc commit", "pgc commit");
				this.pgc.CommitOption(thisoption);
			}
		}
		else if(hgc != null){
			if(!hgc.QuesLocked){
				Log.v("hgc commit", "hgc commit");
				this.hgc.CommitOption(thisoption,this.player,this.anotherPlayer);
			}
		}
			
	}
}