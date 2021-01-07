package licence.projet.datatypes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Factory Pattern
public class ExpressionFactory {

    private static boolean isCallback(String input) {
        //Checking if user input is a callback (either from stack or history)
        String regex = "hist[(](-?)[0-9]+[)]|pile[(](-?)[0-9]+[)]";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }
    
    private static boolean isSubst(String input) {
    	String regex = "susbt";
    	Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
    	Matcher matcher = pattern.matcher(input);
    	return matcher.find();
    }
    private static boolean isSymb(String input) {
    	String regex = "^\\$";
    	Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
    	Matcher matcher = pattern.matcher(input);
    	return matcher.find();
    }
    private static boolean isBinary(String input) {
    	String regex = "\\*|\\+|-|/|\\^";
    	Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
    	Matcher matcher = pattern.matcher(input);
    	return matcher.find();
    }

    public Expression getExprFromString(String exprString) {
        Expression expr;
        try {
            double value = Double.parseDouble(exprString);
            expr = new RealNumber(exprString);
            

        } catch (NumberFormatException nfe) {
            if (isCallback(exprString)) {
                CallbackFactory factory = new CallbackFactory();
                expr = factory.getCallback(exprString);
            } else if(isBinary(exprString)) {
            	expr = new BinaryOperator(exprString);
            }else if (isSymb(exprString)) {
            	expr = new SymbNumber(exprString);
            //} else if (isSubst(exprString)) {
            	
            	// expr = new SubstAction()
            	
            	

            } else {
                //expr = new BinaryOperator(exprString);
            	throw new IllegalArgumentException("TU T'ES TROMPER");
            }
        }
        return expr;
    }

}
