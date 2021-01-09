package licence.projet.datatypes;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Stack;
import java.util.function.BinaryOperator;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;

public class BinaryOperatorExpr implements Expression {
    private static final Map<String, BinaryOperator<Double>> operations;
    static {
        Map<String, BinaryOperator<Double>> operationsMap = new HashMap<>();
        BinaryOperator<Double> add, minus, mult, div, pow;
        add = (x, y) -> x + y;
        minus = (x, y) -> x - y;
        mult = (x, y) -> x * y;
        div = (x, y) -> x / y;
        pow = (x, y) -> Math.pow(x, y);

        operationsMap.put("+", add);
        operationsMap.put("-", minus);
        operationsMap.put("*", mult);
        operationsMap.put("/", div);
        operationsMap.put("^", pow);
        operations = Collections.unmodifiableMap(operationsMap);
    }
    private final String op;

    public BinaryOperatorExpr(String op) {
        Objects.requireNonNull(op);
        this.op = op;
    }

    /**
     * Return true if the string is in the Map containing all the operators as string and their associated function
     * Return false if the string is not in the Map
     * @param opString  the string that needs to be checked
    */
    public static boolean isOperator(String opString) {
        return operations.keySet().contains(opString);
    }


    /**
     * Returns the value returned by the operator associated function
     * Checks if the user isn't doing something not intented (such as division by 0)
     * @param val1  the first operand
     * @param val2 the second operand
     */
    private double compute(double val1, double val2) {
        if (isOperator(op)) {
            if (val2 == 0 && op.equals("/")) {
                System.out.println("Can't divide by zero!\n");
                throw new IllegalArgumentException();
            }

            BinaryOperator<Double> operation = operations.get(op);
            return operation.apply(val1, val2);

        } else {
            throw new IllegalStateException("Operator " + op + " was not recognized!");
        }
    }
    private String computeSymb(String symb1, String symb2) {
    	if(isOperator(op)) {
    		return ("( "+symb1+" "+op+" "+symb2+" )");
    	}
    	throw new IllegalStateException();
    }

    
    public String getValue(Stack<String> stack, ArrayList<String> hist) {
        //On verifie que la pile n'est pas vide et qu'elle contient assez d'operandes
        if (!(stack.empty()) && stack.size() > 1) {
            double val1, val2, res;
            String symb1, symb2;
            String resSymb;
            //On recupere sans pop pour ne pas les enlever de la pile au cas ou on ne terminerait pas le calcul (divsion par 0 par exemple)
            int size = stack.size();
            //Cas du try : cas "classique" ou les deux operandes sont des nombres
            try {
                //On utilise get au lieu de pop au cas ou le try echoue
            	val2 = Double.parseDouble(stack.get(size-1));
                val1 = Double.parseDouble(stack.get(size-2));
                res = compute(val1, val2);
                //Une fois qu'on est a l'abri que le try echoue, on pop
                stack.pop();
            	stack.pop();
                
                stack.push(Double.toString(res));
                hist.add(Double.toString(res));
                return Double.toString(res);
            
            //Cas du catch : un des deux operandes est symbolique  
            }catch(NumberFormatException e) {
            	symb2=(stack.get(size-1));
            	symb1=(stack.get(size-2));
            	resSymb=computeSymb(symb1,symb2);
            	stack.pop();
            	stack.pop();
            	stack.add(resSymb);
            	return resSymb;
            	
            }

        } else {
            throw new IllegalArgumentException("Not enough operand in stack!");
        }
    }

    @Override
    public String toString() {
        return op;
    }
}
