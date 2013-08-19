package com.teamir.mendcurse.game;

import com.teamir.mendcurse.R;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;

public class Game extends Activity {
	
	Intent i_h =null;
	Intent i_s = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game);
		
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		Button singleplayer = (Button)findViewById(R.id.game_singleplayer);
		Button headsup = (Button)findViewById(R.id.game_headsup);
		i_h = new Intent(this,Headsup.class);
		i_s = new Intent(this,SinglePlayer.class);
		
		singleplayer.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(Game.this.i_s);
			}
			
		});
		
		headsup.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(Game.this.i_h);
			}
			
		}
		);
	}
}