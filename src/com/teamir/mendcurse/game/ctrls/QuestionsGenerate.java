package com.teamir.mendcurse.game.ctrls;

import java.util.*;

import com.teamir.mendcurse.Element;
import com.teamir.mendcurse.AppData;
import android.app.Activity;

public class QuestionsGenerate
{
	Activity ac = null;
	int quesc = 0;
	public QuestionsGenerate(Activity ac,int quesc)
	{
		this.ac = ac;
		this.quesc = quesc;
	}
	
	public Stack<Question> getQuestions()
	{
		final ArrayList<Element> elements = ((AppData)ac.getApplication()).getElements();
		Stack<Question> questions = new Stack<Question>();
		for(int j = 0;j<this.quesc;j++)
		{
			Question ques = new Question();
			//随机获取元素
			Random ra = new Random();
			int currEleId = ra.nextInt(118);
			String currEleSym = elements.get(currEleId).getSymbol();
			String currEleName = elements.get(currEleId).getElementname();
			String currEleEngName = elements.get(currEleId).getEnglishname();
	//		String quesText = "以下选项哪一个是 "+currEleName+" 的化学式";
			String quesText = currEleName+"";
			ques.setQuestionText(quesText);
			//设置正确选项
			int correctoption = ra.nextInt(4);		
			ques.setCorrectoption(correctoption);
			ques.setOptoin(correctoption, currEleSym);
			//设置其他选项
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
					if(i!= correctoption)
						ques.setOptoin(i,others.get(tmpo++).getSymbol());
						//options[i].setText(others.get(tmpo++).getSymbol());
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
					if(i!=correctoption){
						ques.setOptoin(i,others.get(tmpo++).getSymbol());
						//options[i].setText(others.get(tmpo++).getSymbol());
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
					if(i!=correctoption)
						ques.setOptoin(i,others.get(tmpo++).getSymbol());
						//options[i].setText(others.get(tmpo++).getSymbol());
				}
			}
			
			questions.push(ques);		
		}
		return questions;
	}
}