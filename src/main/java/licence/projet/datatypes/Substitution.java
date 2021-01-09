package licence.projet.datatypes;

import java.util.ArrayList;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Substitution implements Expression{
	private boolean isAllReal(String l) {
		
		 String regex = "*[a-z]*";
	     Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
	     Matcher matcher = pattern.matcher(l);
	     return matcher.find();
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
		//String t;
		s=stack.pop();
		in=stack.pop();
		l=stack.pop();
		System.out.println("s "+s);
		System.out.println("in "+in);
		System.out.println("l "+l);
		try {
			Double.parseDouble(s);	
		}catch(Exception e) {
			if(l.length()>1) {
				for(int i=0;i<l.length();i++) {
					//regarder plutot avec des strings
					if(l.charAt(i)==s.charAt(0)) {
						l=l.substring(0,i)+in.charAt(0)+l.substring(i+1);
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
