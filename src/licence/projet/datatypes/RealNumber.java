package licence.projet.datatypes;

import java.util.Stack;

public class RealNumber implements Expression {
    private final double value;

    public RealNumber(double value) {
        this.value = value;
    }

    public double getValue(Stack<Double> stack) {
        stack.push(value);
        return value;
    }
}
