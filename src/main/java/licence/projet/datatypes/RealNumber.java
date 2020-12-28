package licence.projet.datatypes;

import java.util.ArrayList;
import java.util.Stack;

public class RealNumber implements Expression {
    private final double value;

    public RealNumber(double value) {
        this.value = value;
    }

    public double getValue(Stack<Double> stack, ArrayList<Double> hist) {
        stack.push(value);
        hist.add(value);
        return value;
    }

    @Override
    public String toString() {
        return Double.toString(value);
    }
}
