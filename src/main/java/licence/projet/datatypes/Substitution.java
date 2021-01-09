package licence.projet.datatypes;

import java.util.ArrayList;
import java.util.Stack;



public class Substitution implements Expression{
	
	/*test if there is a lettre in the string */
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
	
	/* !Don't working! compute the value of expression l */
	private String compute(String l,Stack<String> stack,ArrayList<String> hist) {
		final String esp= " ";
		int[]op=new int [100];
		int k=0;
		int paren=0;
		int parentrouver=0;
		String mots[]=l.split(esp);
		int j=mots.length;
		//Compter le nombre de parenthese 
		for(int i=0; i<mots.length;i++) {
			if(mots[i].equals("(")) {
				paren=paren+1;
			}
		}
		while(parentrouver!=paren) {
			if(mots[j].equals(")")){
				parentrouver=parentrouver+1;
				j--;
			}
			else {
				if (!(mots[j].equals("+")||mots[j].equals("-")||mots[j].equals("/")||mots[j].equals("*")||mots[j].equals("^")) ){
					stack.push(mots[j]);
					if (stack.size()==2) {
						BinaryOperatorExpr ope = new BinaryOperatorExpr(mots[op[k]]);
						ope.getValue(stack, hist);
					}
					j--;
				}else if (mots[j].equals("+")||mots[j].equals("-")||mots[j].equals("/")||mots[j].equals("*")||mots[j].equals("")){
					op[k]=j;
					k++;
					j--;
				}
				j--;
			}
		}
		return stack.pop();
		
		
	}
	
/* unstacking 3 times and verify if the first pop wasn't a symbolic variable
 * replace in the expression(3rd pop) value(2nd pop) of the symbolic varible (1st pop)*/
	public String getValue(Stack<String> stack, ArrayList<String> hist) {
		String s, in;
		String l;
		s=stack.pop();
		in=stack.pop();
		l=stack.pop();
		try {
			Double.parseDouble(s);	
		}catch(NumberFormatException e) {
			if(l.length()>1) {
				for(int i=0;i<l.length();i++) {
					if(l.charAt(i)==s.charAt(0)) {
						l=l.substring(0,i)+in.substring(0,in.length())+l.substring(i+1);
						//normalement regarde si l ne contient plus de variables symboliques et calcul l'expression
						//if(isAllReal(l)) {
							//stack.push(compute(l,stack,hist));
						//}
						stack.push(l);
						hist.add(l);
						return l;
						
					}
				}
			}else {
				stack.push(in);
				hist.add(l);
				return in;
			}
			
		}
		throw new IllegalArgumentException();
		
	}
	
}
