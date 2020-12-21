package licence.projet.datatypes;

import java.util.ArrayList;
import java.util.Stack;

public class StackCallback extends SimpleCallback {

    public StackCallback(int index) {
        super(index);
    }

    public double getValue(Stack<Double> stack, ArrayList<Double> hist) {
        return callback(stack, stack, hist);
    }
}
