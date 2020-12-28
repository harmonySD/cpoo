package licence.projet.datatypes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Factory Pattern
public class ExpressionFactory {

    private boolean isCallback(String input) {
        //Checking if user input is a callback (either from stack or history)
        String regex = "hist[(](-?)[0-9]+[)]|pile[(](-?)[0-9]+[)]";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }

    public Expression getExprFromString(String exprString) {
        Expression expr;
        try {
            double value = Double.parseDouble(exprString);
            expr = new RealNumber(value);

        } catch (NumberFormatException nfe) {
            if (isCallback(exprString)) {
                CallbackFactory factory = new CallbackFactory();
                expr = factory.getCallback(exprString);

            } else {
                expr = new BinaryOperator(exprString);
            }
        }
        return expr;
    }

}
