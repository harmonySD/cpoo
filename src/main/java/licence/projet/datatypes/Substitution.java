package licence.projet.datatypes;

import java.util.ArrayList;
import java.util.Stack;


public class Substitution implements Expression{
	
	

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
						System.out.println(in.charAt(0));
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
