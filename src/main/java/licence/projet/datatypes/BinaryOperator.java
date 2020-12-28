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
}
