package com.teamir.mendcurse;

import java.util.ArrayList;

import android.app.Application;
import android.content.res.XmlResourceParser;

public class AppData extends Application {

	private ArrayList<Element> elements;//元素对象集合
	private int score;//记录游戏分数
	
	@Override
    public void onCreate()
    {
        super.onCreate();
        
		//利用xmlParse处理类来获取所有元素对象。
		XmlResourceParser xrp = this.getResources().getXml(R.xml.elements);
		elements = XmlParse.createData(xrp);
		
		score = 0;
    }
	
	
	public Element getElement(int index){
		return elements.get(index - 1);
	}
	public Element getElement(String index){
		return elements.get(Integer.parseInt(index) - 1);
	}
	
	public ArrayList<Element> getElements() {
		return elements;
	}


	public void setElements(ArrayList<Element> elements) {
		this.elements = elements;
	}
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
}
