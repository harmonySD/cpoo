package licence.projet.datatypes;

import java.util.ArrayList;
import java.util.Stack;
import java.util.Objects;

public class VarCallback extends SimpleCallback {
    private final String name;
    private final double value;
    private boolean set;

    public VarCallback(String name, double value) {
        super(0);
        Objects.requireNonNull(name);
        this.name = name;
        this.value = value;
        set = false;
    }

    public double getValue(Stack<Double> stack, ArrayList<Double> hist) {
        if (set) {
            stack.push(value);
            hist.add(value);
            return value;
        } else {
            set = true;
            return value;
        }
    }
}
