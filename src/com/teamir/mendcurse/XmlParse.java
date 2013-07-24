package com.teamir.mendcurse;

import java.io.IOException;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import android.content.res.XmlResourceParser;

public class XmlParse {
	//从xml中提取数据，返回一个List<Element>类型的对象
	public static ArrayList<Element> createData(XmlResourceParser xrp){

		//XmlResourceParser xrp = this.getResources().getXml(R.xml.elements);
		
		Element element = null;
		ArrayList<Element> elements = new ArrayList<Element>();	

		try
		{
			
			//还没有到XML文档的结尾处
			while (xrp.getEventType() != XmlResourceParser.END_DOCUMENT) 
			{			
				
				//如果遇到了开始标签
				if(xrp.getEventType() == XmlResourceParser.START_TAG)
				{
					//获取该标签的标签名
					String tagName = xrp.getName();
					
					//如果遇到elementid标签
					if(tagName.equals("elementid"))
					{						
						String id = xrp.nextText();
						element = new Element(id);	
						
					}
					else if(tagName.equals("elementname"))
					{									
						element.setElementname(xrp.nextText());
					}
					else if(tagName.equals("symbol"))
					{									
						element.setSymbol(xrp.nextText());
					}
					else if(tagName.equals("position")){
						element.setPosition(xrp.nextText());
					}
					else if(tagName.equals("atomicmass"))
					{									
						element.setAtomicmass(xrp.nextText());
					}
					else if(tagName.equals("englishname"))
					{									
						element.setEnglishname(xrp.nextText());
					}
				}	
				
				
				else if(xrp.getEventType() == XmlPullParser.END_TAG){
					if(xrp.getName().equals("element") && element != null){
						elements.add(element);
						element = null;
					}
				}				
				
				
				//获取解析器的下一个事件
				xrp.next();
			}
			
							
		}
		catch (XmlPullParserException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return elements;
			
		
		
	
	
	}




}
