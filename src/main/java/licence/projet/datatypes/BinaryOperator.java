package licence.projet.datatypes;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Stack;

public class BinaryOperator implements Expression {
    private final String op;

    public BinaryOperator(String op) {
        Objects.requireNonNull(op);
        this.op = op;
    }
    
    private String compute(String val1,String val2) {
    	try {
    		double op1;
    		double op2;
    		op1=Double.parseDouble(val1);
    		op2=Double.parseDouble(val2);
    		return computeAux(op1, op2);
    	}
    	catch(NumberFormatException e){
    		return "( "+val1+" "+op+" "+val2+" )";
    	}
    }

    private String computeAux(double val1, double val2) {
        switch (op) {
            case "+":
                return Double.toString(val1 + val2) ;
            case "-":
                return Double.toString(val1 - val2) ;
            case "*":
                return Double.toString(val1 * val2);
            case "/":
                return Double.toString(val1 / val2) ;
            case "^":
                return Double.toString(Math.pow(val1,val2));
            default:
                throw new IllegalStateException("Operator " + op + " was not recognized!");
        }
    }

    public String getValue(Stack<String> stack, ArrayList<String> hist) {
        if (!(stack.empty()) && stack.size() > 1) {
            String val1, val2, res;
            int size = stack.size();
            val2 = stack.get(size - 1);
            val1 = stack.get(size - 2);
            res = compute(val1, val2); //on compute d'abord pour verifier que l'operateur rentre est correct
            stack.pop(); //on supprime de la pile val2
            stack.pop(); //on supprime de la pile val1
            stack.push(res);
            hist.add(res);
            return res;
        } else {
            throw new IllegalArgumentException("Not enough operand in stack!");
        }
    }

    @Override
    public String toString() {
        return op;
    }
    
}
