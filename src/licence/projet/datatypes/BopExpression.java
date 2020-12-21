package licence.projet.datatypes;

import java.util.Objects;
import java.util.Stack;

//Binary Operator Expression (ie "7 + 5 * 4.2")
public class BopExpression implements Expression {
    private final String[] innerExpressions;

    public BopExpression(String stringOfExpr) {
        Objects.requireNonNull(stringOfExpr);
        this.innerExpressions = stringOfExpr.split(" ");
    }

    private Expression getExprFromString(String exprString) {
        Expression expr;
        try {
            double value = Double.parseDouble(exprString);
            expr = new RealNumber(value);
        } catch (NumberFormatException nfe) {
            expr = new BinaryOperator(exprString);
        }
        return expr;
    }

    public double getValue(Stack<Double> stack) {
        if (innerExpressions.length < 1) {
            throw new IllegalStateException("An expression shouldn't be empty!");
        }

        int i;
        double res = 0;

        for (i = 0; i < innerExpressions.length; i++) {
            String exprString = innerExpressions[i];
            res = getExprFromString(exprString).getValue(stack);
        }
        return res;
    }
}
