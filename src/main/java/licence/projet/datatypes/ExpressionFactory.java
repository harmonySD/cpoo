package licence.projet.datatypes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Stack;

//Factory Pattern
public class ExpressionFactory {
    private final CallbackFactory cllbckFact;
    
    public ExpressionFactory() {
        this.cllbckFact = new CallbackFactory();
    }
    
    /**Checks using pattern matching if input is a call back (from hist, stack or variable)**/
    private boolean isCallback(String input) {
        //Checking if user input is a callback (either from stack or history)
        String regex = "^hist[(](-?)[0-9]+[)]$|^pile[(](-?)[0-9]+[)]$|^!|^\\?";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }
    
    /**Checks using pattern matching if input is an operator**/
    private boolean isBinaryOp(String input) {
        return BinaryOperatorExpr.isOperator(input);
    }
    
    /**Checks using pattern matching if input is a symbolic number ($x)**/
    private static boolean isSymb(String input) {
    	String regex = "^\\$";
    	Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
    	Matcher matcher = pattern.matcher(input);
    	return matcher.find();
    } 
    
    /**Checks using pattern matching if input is a substitution (subst)**/
    private static boolean isSubst(String input) {
    	String regex = "^subst";
    	Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
    	Matcher matcher = pattern.matcher(input);
    	return matcher.find();
    }
    
    /** Returns an instance of class which implements Expression according to user input
     * it can be : a real number
     * 			   a symbolic number
     * 			   an operator
     * 			   a substitution
    */
    public Expression getExprFromString(String exprString, Stack<String> stack) {
        Expression expr;
        try {
            double value = Double.parseDouble(exprString);
            expr = new RealNumber(value);

        } catch (NumberFormatException nfe) {
            if (isCallback(exprString)) {
                // The callback factory returns a type that extends SimpleCallback (which implements Callback and Expression)
                // It's either a callback from stack, history or from a variable
                expr = cllbckFact.getCallback(exprString, stack);

            } else if(isSymb(exprString)) {
            	expr = new SymbNumber(exprString.charAt(1));
            	
            }else if(isSubst(exprString)) {
            	expr = new Substitution();
            	
            } else if (isBinaryOp(exprString)) {
                expr = new BinaryOperatorExpr(exprString);

            } else {
                throw new IllegalArgumentException();
            }
        }
        return expr;
    }

}
