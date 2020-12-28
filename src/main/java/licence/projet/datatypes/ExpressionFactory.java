package licence.projet.datatypes;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Binary Operator Expression (ie "7 + 5 * 4.2")
public class GeneralExpression implements Expression {
    private final String[] innerExpressions;

    public GeneralExpression(String stringOfExpr) {
        Objects.requireNonNull(stringOfExpr);
        this.innerExpressions = stringOfExpr.split(" ");
    }

    private boolean isCallback(String input) {
        //Checking if user input is a callback (either from stack or history)
        String regex = "hist[(](-?)[0-9]+[)]|pile[(](-?)[0-9]+[)]";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }

    private Expression getExprFromString(String exprString) {
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

    public double getValue(Stack<Double> stack, ArrayList<Double> hist) {
        if (innerExpressions.length < 1) {
            throw new IllegalStateException("An expression shouldn't be empty!");
        }

        int i;
        double res = 0;

        for (i = 0; i < innerExpressions.length; i++) {
            String exprString = innerExpressions[i];
            res = getExprFromString(exprString).getValue(stack, hist);
        }
        return res;
    }
}
