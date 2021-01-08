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

    private boolean isCallback(String input) {
        //Checking if user input is a callback (either from stack or history)
        String regex = "^hist[(](-?)[0-9]+[)]$|^pile[(](-?)[0-9]+[)]$|^!|^\\?";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }

    private boolean isBinaryOp(String input) {
        return BinaryOperatorExpr.isOperator(input);
    }

    public Expression getExprFromString(String exprString, Stack<String> stack) {
        Expression expr;
        try {
            double value = Double.parseDouble(exprString);
            expr = new RealNumber(value);

        } catch (NumberFormatException nfe) {
            if (isCallback(exprString)) {
                expr = cllbckFact.getCallback(exprString, stack);

            } else if (isBinaryOp(exprString)) {
                expr = new BinaryOperatorExpr(exprString);

            } else {
                throw new IllegalArgumentException();
            }
        }
        return expr;
    }

}
