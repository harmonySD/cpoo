package licence.projet.datatypes;

import java.util.ArrayList;
import java.util.Stack;

public class RealNumber implements Expression {
    private final double value;

    public RealNumber(double value) {
        this.value = value;
    }

    public String getValue(Stack<String> stack, ArrayList<String> hist) {
        stack.push(Double.toString(value));
        hist.add(Double.toString(value));
        return Double.toString(value);
    }

    @Override
    public String toString() {
        return Double.toString(value);
    }
}
