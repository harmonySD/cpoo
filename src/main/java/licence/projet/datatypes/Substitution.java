package licence.projet.datatypes;

import java.util.ArrayList;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Substitution implements Expression{
	private boolean isAllReal(String l) {
		final String esp= " ";
		String mots[]=l.split(esp);
		for(int i=0; i<mots.length;i++) {
			if(mots[i].charAt(0)>='a' && mots[i].charAt(0)<='z') {
				return false;
			}
		}
		return true;
	}
	private String getOpe(String l) {
		final String esp=" ";
		String mots[]= l.split(esp);
		for(int i=0;i<mots.length;i++) {
			if(mots[i].equals("+")|mots[i].equals("-")|mots[i].equals("/")|mots[i].equals("*")|mots[i].equals("")) {
				return mots[i];
			}
		}
		return "r";
	}
	

	public String getValue(Stack<String> stack, ArrayList<String> hist) {
		String s, in;
		String l;
		s=stack.pop();
		in=stack.pop();
		l=stack.pop();
		try {
			Double.parseDouble(s);	
		}catch(Exception e) {
			if(l.length()>1) {
				for(int i=0;i<l.length();i++) {
					if(l.charAt(i)==s.charAt(0)) {
						l=l.substring(0,i)+in.substring(0,in.length())+l.substring(i+1);
						System.out.println(isAllReal(l));
						stack.push(l);
						return l;
						
					}
				}
			}else {
				stack.push(in);
				return in;
			}
			
		}
		throw new IllegalArgumentException();
		
	}
	
}
