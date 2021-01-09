package licence.projet.datatypes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Stack;

//Factory Pattern
public class ExpressionFactory {
    private final CallbackFactory cllbckFact;
    
    /**Constructor***/
    public ExpressionFactory() {
        this.cllbckFact = new CallbackFactory();
    }
    /**Check if input is a call back to history or stack **/
    private boolean isCallback(String input) {
        //Checking if user input is a callback (either from stack or history)
        String regex = "^hist[(](-?)[0-9]+[)]$|^pile[(](-?)[0-9]+[)]$|^!|^\\?";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }
    /**Check if input has an operator**/
    private boolean isBinaryOp(String input) {
        return BinaryOperatorExpr.isOperator(input);
    }
    
    /**check if input is a symbolic number ($qqc)**/
    private static boolean isSymb(String input) {
    	String regex = "^\\$";
    	Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
    	Matcher matcher = pattern.matcher(input);
    	return matcher.find();
    } 
    /**Check if input is a substitution (subst)**/
    private static boolean isSubst(String input) {
    	String regex = "^subst";
    	Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
    	Matcher matcher = pattern.matcher(input);
    	return matcher.find();
    }
    /** return an instance of class which implements Expression in relation with user output
     * it can be : a real number
     * 			   a symbolic number
     * 			   an operator
     * 			   a substitution **/
    public Expression getExprFromString(String exprString, Stack<String> stack) {
        Expression expr;
        try {
            double value = Double.parseDouble(exprString);
            expr = new RealNumber(value);

        } catch (NumberFormatException nfe) {
            if (isCallback(exprString)) {
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
