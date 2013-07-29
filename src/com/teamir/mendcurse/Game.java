package com.teamir.mendcurse;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Game extends Activity {
	//全局对象
	private TextView tolsco;
	private TextView ques;
	private TextView time;
	private ProgressBar timebar;
	private TextView cenques;
	private TextView[] options = new TextView[4];   //4个选项view
	public  int rightoption = 0;    //记录正确选项位置
	private int remainQues = 10;    //记录剩余问题个数
	private int rightc = 0;         //答对个数
	private int wrongc = 0;        //答错个数
	private int status = 0;       //进度条百分比
	private int score = 0;        //记录得分
	private Timer timer = new Timer();   //更新进度条Timer线程
	private TimerTask timertask = null;  //Timer任务
	private Handler timeHandler;         //处理消息Handler主线程
	private boolean tmpflag = false;    //最后一题的界面控制

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game);
		
		//获取各view
		tolsco = (TextView)findViewById(R.id.game_tolsco);
		ques = (TextView)findViewById(R.id.game_ques);
		time = (TextView)findViewById(R.id.game_time);
		timebar = (ProgressBar)findViewById(R.id.game_timebar);
		cenques = (TextView)findViewById(R.id.game_cenques);
		options[0] = (TextView)findViewById(R.id.game_option0);
		options[1] = (TextView)findViewById(R.id.game_option1);
		options[2] = (TextView)findViewById(R.id.game_option2);
		options[3] = (TextView)findViewById(R.id.game_option3);
		
		//首次运行
		setquesAndoption();
		updateques();
		timeHandler = new Handler()
		{
			@Override
			public void handleMessage(Message msg)
			{
				//用户没有做出选择
				if(msg.what == 0x111)
				{
					if(status<100){
						updatetime(status);
						timebar.setProgress(status);
					}
					else if(status == 100){
						judge(rightoption,0,false);  //显示结果
						updatetime(status);          //更新得分
						timebar.setProgress(status);
					}
					else{	//用户未选择,准备进入下一题		
						try{
							timer.wait();
						}catch(Exception e){
							e.printStackTrace();
						}	
						resetListener(false);
						//暂停让用户看到结果
						try {
							Thread.sleep(800);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						//获取timer对象锁
						synchronized(timer)
						{
							timer.notify();
						}
						status = 0;
						timebar.setProgress(status);
						updateques();
						updateScore(false);
						gameover();
						if(!tmpflag)
							setquesAndoption();
					}
				}
				//时间未到用户作出选择
				if(msg.what == 0x112)
				{
					try{
						timer.wait();
					}catch(Exception e){
						e.printStackTrace();
					}
					//暂停让用户看到结果
					try {
						Thread.sleep(800);						
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}					
					//开始下一题
					//获取timer对象锁
					synchronized(timer)
					{
						timer.notify();
					}
					status = 0;
					timebar.setProgress(status);
					updateques();
					gameover();
					if(!tmpflag)
						setquesAndoption();
				}
			}
		};
		
		//计时任务
		timertask = new TimerTask()
		{

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Message msg = new Message();
				msg.what = 0x111;
				status +=5;
				timeHandler.sendMessage(msg);
			};
		};
		timer.schedule(timertask, 0, 500);
		
	}
	
	//判断对错并显示,ischoice控制用户选择还是自动到时间
	public void judge(int rightoption,int curroption,boolean ischoice)
	{
		if(ischoice)
		{
			if(rightoption == curroption)
			{
				options[rightoption].setBackgroundResource(R.color.green);
				rightc++;
			}
			else
			{
	        		for(int i = 0;i<4;i++){
						int color = (i == rightoption)?R.color.green:R.color.red;
						options[i].setBackgroundResource(color);
				}
				wrongc++;
			}
		}
		else{
			for(int i = 0;i<4;i++){
				int color = (i == rightoption)?R.color.green:R.color.red;
				options[i].setBackgroundResource(color);
		}
			wrongc++;
		}
		
			
	}
	
	//更新得分
	public void updateScore(boolean iscrrect)
	{
		score = iscrrect?(score+10):(score-10);
		String text = "总分: "+score;
		tolsco.setText(text);
	}
	//发送选择信息
	public void sendOptmess()
	{
		Message msg = new Message();
		msg.what = 0x112;
		timeHandler.sendMessage(msg);
	}
	//更新剩余时间
	public void updatetime(int status)
	{
		String remaintime = "时间: "+(float)(100-status)/10;
		time.setText(remaintime);
	}
	//更新剩余问题状态
	public void updateques()
	{		
		String text = "问题: "+remainQues+"/"+10;
		ques.setText(text);
		remainQues -=1;
	}
	//判断游戏是否结束
	public void gameover()
	{
		if(remainQues<0)
		{	
			timertask.cancel();
			cenques.setText("game over!");
			tmpflag = true;
			//启动显示结果的activity
			Intent i = new Intent(Game.this,GameResult.class);
			Bundle res = new Bundle();
			res.putInt("score", score);
			res.putInt("rightc", rightc);
			res.putInt("wrongc",wrongc);
			i.putExtras(res);
			startActivityForResult(i,0);
			
		}
	}
	//reset选项监听器，防止用户在暂停期间刷选项
	public void resetListener(boolean isset)
	{
		if(isset)
			for (int i = 0;i<4;i++)
			{
				options[i].setOnClickListener(new GameOptionListener(i,Game.this));
				options[i].setClickable(true);
				options[i].setBackgroundResource(R.color.lightblue);
			}
		else
			for (int i = 0;i<4;i++)
				options[i].setOnClickListener(null);	
	}

	//等待结果activity返回重新开始还是结束
	@Override
	public void onActivityResult(int requestCode , int resultCode
		, Intent intent)
	{
		//play again
		if (requestCode == 0 && resultCode == 0)
		{
			if(intent != null)
			{
				//移除所有已取消的任务
				timer.purge();
				//重新初始化数据
				tmpflag = false;
				remainQues = 10;
				score = status = rightc = wrongc = 0;
				updateques();
				setquesAndoption();
				tolsco.setText("总分: 0");
				//重新建立计时任务
				timertask = new TimerTask()
				{

					@Override
					public void run() {
						// TODO Auto-generated method stub
						Message msg = new Message();
						msg.what = 0x111;
						status +=5;
						timeHandler.sendMessage(msg);
					};
				};
				timer.schedule(timertask, 0, 500);
			}
		}
		//finish
		if(requestCode == 0 && resultCode == 1)
		{
			if(intent != null)
			{
				finish();
				System.exit(0);
			}
		}
	}
	
	//设置问题与选项
	public void setquesAndoption()
	{
		final ArrayList<Element> elements = ((AppData)getApplication()).getElements();
		//随机获取元素
		Random ra = new Random();
		int currEleId = ra.nextInt(118);
		String currEleSym = elements.get(currEleId).getSymbol();
		String currEleName = elements.get(currEleId).getElementname();
		String currEleEngName = elements.get(currEleId).getEnglishname();
		//设置正确选项
		rightoption = ra.nextInt(4);
		options[rightoption].setText(currEleSym);
		//设置问题
		String text = "以下选项哪一个是 "+currEleName+" 的化学式?";
		cenques.setText(text);
		//获取所有首字母相同的元素
		char initial = currEleEngName.charAt(0);
		ArrayList<Element> others = new ArrayList<Element>();
		for(Element e:elements)
		{
			if((e.getEnglishname().charAt(0) == initial) && !e.getEnglishname().equals(currEleEngName))
				others.add(e);
		}
		int othersc = others.size();
		if(othersc == 3)
		{
			int tmpo = 0;
			for(int i = 0;i<4;i++){
				if(i!=rightoption)
					options[i].setText(others.get(tmpo++).getSymbol());
			}
		}
		//其余选项不足3个,随机获取element补足选项
		if(othersc < 3)
		{
			int tmpc = 3-othersc;
			while(tmpc>0)
			{
				Element e;
				if(!(e = elements.get(ra.nextInt(118))).getEnglishname().equals(currEleEngName)){
					others.add(e);
					tmpc--;
				}
				else
					continue;
			}
			int tmpo = 0;
			for(int i = 0;i<4;i++){
				if(i!=rightoption){
					options[i].setText(others.get(tmpo++).getSymbol());
				}
			}
		}
		//其余选项大于3个，随机剔除多余选项
		if(othersc > 3)
		{
			int tmpc = othersc-3;
			while(tmpc>0)
			{
				others.remove(ra.nextInt(othersc--));
				tmpc--;
			}
			int tmpo = 0;
			for(int i = 0;i<4;i++){
				if(i!=rightoption)
					options[i].setText(others.get(tmpo++).getSymbol());
			}
		}
		
		//最后设置监听器和背景
		resetListener(true);
/*		for (int i = 0;i<4;i++)
		{
			options[i].setOnClickListener(new GameOptionListener(i,Game.this));
			options[i].setClickable(true);
			options[i].setBackgroundResource(R.color.lightblue);
		}*/
	}
	//重写返回键事件
		public void onBackPressed()
		{
			timer.cancel();
			finish();
			System.exit(0);
		}

}