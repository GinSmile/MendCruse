package com.teamir.mendcurse.calculate;

import java.util.ArrayList;
import java.util.Stack;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.teamir.mendcurse.AppData;
import com.teamir.mendcurse.Element;
import com.teamir.mendcurse.R;

public class Calculate extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calculate);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		final ArrayList<Element> elements = ((AppData)getApplication()).getElements();
		final EditText calEdit = (EditText)findViewById(R.id.calculate_attr);
		final Button calBtn = (Button)findViewById(R.id.calculate_btn);
		calBtn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub				
				Log.v("imp", "press btn search");				
				
				String molecule = calEdit.getText().toString().trim();
				getBack(molecule);
			}
			private void getBack(String text){
				if(check(text)){
					String newtext=textFormate(text);
					String a= ReadFormula(newtext);
					if(a=="error")
						Toast.makeText(Calculate.this,"找不到输入元素~ ",Toast.LENGTH_LONG).show();
					double re=getExpressionValue(a);
					Toast.makeText(Calculate.this,"分子量为： "+Double.toString(re),Toast.LENGTH_LONG).show();
				}
			}
			public boolean check(String text){
				int len=text.length();
				if(len==0){
					System.out.println("输入为空");
					return false;
				}
				else{
					int num=0;
					for(int i=0;i<len;i++){
						if(text.charAt(i)>='a'&&text.charAt(i)<='z'){
							if(i==0){
								Toast.makeText(Calculate.this,"输入非法！请注意元素大小写！ ",Toast.LENGTH_LONG).show();
								return false;
							}
							if(!(text.charAt(i-1)>='A'&&text.charAt(i-1)<='Z')){
								Toast.makeText(Calculate.this,"输入非法！请注意元素大小写！ ",Toast.LENGTH_LONG).show();
								return false;
							}
					    }
						else if(text.charAt(i)=='(')num++;
						else if(text.charAt(i)==')')num--;	
				    }
					if(num!=0){
						Toast.makeText(Calculate.this,"括号不匹配！ ",Toast.LENGTH_LONG).show();
						return false;
					}
				}
					return true;
			}
			private String textFormate(String text){
				String newtext="";
				int len = text.length();
				for(int x=0;x<len;x++){
					char ccode=text.charAt(x);
					if(ccode >= 65 && ccode <= 90  ||
							ccode >= 97 && ccode <= 122 ||
							ccode >= 46 && ccode <= 57)
						newtext+=ccode;
					if (ccode == 44)
						newtext += ".";
					if (ccode == 40 || ccode == 91 || ccode == 123)
						newtext += "(";
					if (ccode == 41 || ccode == 93 || ccode == 125)
						newtext += ")";
				}
				return newtext;
			}
			public String ReadFormula(String text){
				String translated_formula= "";
				String tem="";
				String name="";
				String mass="";
				int len = text.length();
				translated_formula+=text.charAt(0);
				for(int x=1;x<len;x++){
					char ccode = text.charAt(x);
					char ccodebef = text.charAt(x-1);
					if (ccode >= 46 && ccode <= 57)
						{if (ccodebef < 46 || ccodebef > 57)
							translated_formula += "*";}
					if (ccode >= 65 && ccode <= 90 && ccodebef != 40)
						 translated_formula += "+";
					if (ccode == 40 && ccodebef != 40)
						 translated_formula += "+";
					translated_formula += text.charAt(x);
				}
				int Len=translated_formula.length();
				for(int x=0;x<Len;x++){
					char temp=translated_formula.charAt(x);
					if(temp>=65&&temp<=90){
						if(x!=Len-1){
							char temp2=translated_formula.charAt(x+1);
							if(temp2>=97&&temp2<=122){
								name="";
								name+=temp;
								name+=temp2;
								mass=findEle(name);
								if(mass=="error")return "error";
								tem+=mass;
								x++;
							}
							else{
								name="";
								name+=temp;
								mass=findEle(name);
								if(mass=="error")return "error";
								tem+=mass;
							}
						}
						else{
							name="";
							name+=temp;
							mass=findEle(name);
							if(mass=="error")return "error";
							tem+=mass;
						}
					}
					else
						tem+=temp;
				}
				return tem;
			}
			private int getPriority(char c) {
				switch (c) {
				case '+':
				case '-':
					return 3;
				case '*':
				case '/':
					return 4;
				case ')':
					return 2;
				case '(':
					return 1;
				case '#': // define a lowest priority characte '#'.
					return 0;
				default:
					return -1;
				}
			}

			private Stack getRPN(String str) {
				if (str == null || "".equals(str))
					return null;
				Stack<Character> s = new Stack<Character>();
				s.push('#');
				int k = 0;
				Stack sb = new Stack();
				int i = 0;
				int len = str.length();
				while (i < len) {
					char c = str.charAt(i);
					String temp = "";
					if (c >= '.' && c <= '9') {
						while (i < len && c >= '.' && c <= '9') {
							temp += str.charAt(i);
							i++;
							if (i == len)
								break;
							c = str.charAt(i);
						}
						i--;
						sb.push(temp);
					} else if (c == '(' || c == ')') {
						if (c == '(')
							s.push(c);
						else if (c == ')') {
							char c3 = s.pop();
							while (c3 != '(') {
								sb.push(c3);
								c3 = s.pop();
							}
						}
					} else if (c == '+' || c == '-' || c == '*' || c == '/') {
						if (getPriority(c) > getPriority(s.peek())) {
							s.push(c);
						} else {
							char c2 = s.peek();
							while (getPriority(c2) >= getPriority(c)) {
								sb.push(s.pop());
								c2 = s.peek();
							}
							s.push(c);
						}
					}
					if (i == len - 1) {
						char c4 = s.pop();
						while (c4 != '#') {
							sb.push(c4);
							c4 = s.pop();
						}
					}
					i++;
				}

				System.out.println(sb.toString());
				return sb;
			}

			private double calculateValue(Stack post) {
				Stack postfix = new Stack();
				while (!post.empty()) {
					postfix.push(post.pop());
				}
				Stack<Double> s = new Stack();
				int i = 0;
				String c1;
				double d1, d2;
				double r = 0;
				while (!postfix.empty()) {
					c1 = String.valueOf(postfix.pop()) ;
					if (c1.charAt(0) >= '0' && c1.charAt(0) <= '9')
						s.push(Double.valueOf(c1));
					else if (c1.charAt(0) == '+' || c1.charAt(0) == '*') {
						d2 = (double) s.pop();
						d1 = (double) s.pop();
						if (c1.charAt(0) == '+')
							r = d1 + d2;
						else
							r = d1 * d2;
						s.push(r);
					}
					i++;
				}
				return s.pop();

			}

			private double result(char op, double d1, double d2) {
				switch (op) {
				case '-':
					return d1 - d2;
				case '+':
					return d1 + d2;
				case '*':
					return d1 * d2;
				case '/':
					return d1 / d2;
				default:
					return -1;
				}
			}

			public double getExpressionValue(String s) {
				return calculateValue(getRPN(s));
			}
			private String findEle(String text) {
				for(Element e : elements)
					if(e.symbol.equals(text))
						return e.getAtomicmass();
				return "error";
			}
			
	    });
	}
}
