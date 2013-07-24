package com.teamir.mendcurse;


public class Element {
	public Element(String elementid){
		this.elementid = elementid;
	}
	
	public String getElementid() {
		return elementid;
	}
	public void setElementid(String elementid) {
		this.elementid = elementid;
	}
	public String getElementname() {
		return elementname;
	}
	public void setElementname(String elementname) {
		this.elementname = elementname;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getAtomicmass() {
		return atomicmass;
	}
	public void setAtomicmass(String atomicmass) {
		this.atomicmass = atomicmass;
	}
	public String getEnglishname() {
		return englishname;
	}
	public void setEnglishname(String englishname) {
		this.englishname = englishname;
	}
	
	
	@Override
	public String toString(){
		return "原子序号：" + elementid + "\n元素名：" + elementname + "\n元素简写：" + symbol + "\n位置：" 
				+ position + "\n原子量：" + atomicmass + "\n英文全称：" + englishname;
		
	}	
	
	
	public String elementid;
	public String elementname;
	public String symbol;
	public String position;
	public String atomicmass;
	public String englishname;
}

