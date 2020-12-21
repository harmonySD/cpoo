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

    private double compute(double val1, double val2) {
        switch (op) {
            case "+":
                return val1 + val2;
            case "-":
                return val1 - val2;
            case "*":
                return val1 * val2;
            case "/":
                return val1 / val2;
            default:
                throw new IllegalArgumentException("Operator " + op + " was not recognized!");
        }
    }

    public double getValue(Stack<Double> stack, ArrayList<Double> hist) {
        if (!(stack.empty()) && stack.size() > 1) {
            double val1, val2, res;
            val2 = stack.pop();
            val1 = stack.pop();
            res = compute(val1, val2);
            stack.push(res);
            hist.add(res);
            return res;
        } else {
            throw new IllegalStateException("Not enough operand in stack!");
        }
    }
}
