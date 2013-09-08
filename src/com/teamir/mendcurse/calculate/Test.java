package com.teamir.mendcurse.calculate;

import java.util.Stack;

public class Test {
 
 /**
  * define the priority of the operator.
  */
 private int getPriority(char c){
  switch(c){
  case '+' :
  case '-' :
   return 3;
  case '*' :
  case '/' :
   return 4;
  case ')' :
   return 2;
  case '(' :
   return 1;
  case '#' :   //define a lowest priority characte '#'.
   return 0;
  default:
   return -1;
  }
 }
 
 /**
  * return Inverse Poland expression of the infix expression.
  * @param s
  * @return
  */
 private String getRPN(String str){
  if(str == null || "".equals(str))
   return null;
  Stack<Character> s = new Stack<Character>();
  s.push('#'); 
  StringBuilder sb = new StringBuilder();
  
  int i = 0;
  int len = str.length();
  while(i < len){
   char c = str.charAt(i);
   String temp="";
   if(c >= '.' && c <= '9'){
	   while(i<len && c >= '.' && c <= '9'){
		   temp+=str.charAt(i);
		   i++;
		   if(i==len)break;
		   c = str.charAt(i);
	   }
	   i--;
	   sb.append(temp);
	   sb.append(" ");
   }
   else if(c=='(' || c==')'){
    if(c=='(')
     s.push(c);
    else if(c==')'){
     char c3 = s.pop();
     while(c3 != '('){
      sb.append(c3);
      sb.append(" ");
      c3 = s.pop();
     }
    }
   }
   else if(c=='+' || c=='-' || c=='*' || c=='/'){
    if(getPriority(c) > getPriority(s.peek())){
     s.push(c);
    }
    else{
     char c2 = s.peek();
     while(getPriority(c2) >= getPriority(c)){
       sb.append(s.pop());
       sb.append(" ");
      c2 = s.peek();
     }
     s.push(c);
    }
   }
   if(i == len-1){
    char c4 = s.pop();
    while(c4 != '#'){
     sb.append(c4);
     sb.append(" ");
     c4 = s.pop();
    }
   }
   i++;
  }
  
  System.out.println(sb.toString());
  return sb.toString();
 }
 
 private double calculateValue(String postfix){
  Stack<Character> s = new Stack<Character>();
  int i = 0;
  int len = postfix.length();
  char c1;
  double d1,d2;
  double r = 0;
  while( i < len){
   c1 = postfix.charAt(i);
   if(c1 >= '0' && c1 <= '9')
    s.push(c1);
   else if(c1=='+' || c1=='-' || c1=='*' || c1=='/'){
    d2 = s.pop() - 48;
    d1 = s.pop() - 48;
    r = result(c1,d1,d2);
    s.push((char)(r+48));
   }
   i++;
  }
  return s.pop()-48;
  
 }
 
 private double result(char op,double d1,double d2){
  switch(op){
  case '-' : 
   return d1 - d2;
  case '+' :
   return d1 + d2;
  case '*' :
   return d1 * d2;
  case '/' :
   return d1 / d2;
  default :
   return -1;
  }
 }
 public double getExpressionValue(String s){
  return calculateValue(getRPN(s));
 }
 public static void main(String [] args){
  Test ev = new Test();
  String expr = "(2.5+2*2)*2";
  System.out.println(ev.getExpressionValue(expr));
 }
}
