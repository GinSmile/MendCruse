package com.teamir.mendcurse.calculate;

public class CalculateTest {
	public static String input="Na3(OH)2";
	public static String textFormate(String text){
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
	public static String ReadFormula(String text){
		int len=text.length();
		String translated_formula="";
		translated_formula += text.charAt(0);
		for(int x=1;x<len;x++){
			char ccode = text.charAt(x);
			char ccodebef = text.charAt(x-1);
			if (ccode >= 46 && ccode <= 57){
				if (ccodebef < 46 || ccodebef > 57)
					translated_formula += "*";
			}
				if (ccode >= 65 && ccode <= 90 && ccodebef != 40)
					translated_formula += "+";
				if (ccode == 40 && ccodebef != 40)
						translated_formula += "+";
				translated_formula += input.charAt(x);
		}	
		return translated_formula;
	}
	public void Output(){
		
	}
	public static void main(String[] args){
		String text=textFormate(input);
		String a= ReadFormula(text);
		System.out.println(a);
	}
}
