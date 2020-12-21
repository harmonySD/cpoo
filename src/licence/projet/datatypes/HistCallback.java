package licence.projet.datatypes;

import java.util.ArrayList;
import java.util.Stack;

public class HistCallback extends SimpleCallback {

    public HistCallback(int index) {
        super(index);
    }

    public double getValue(Stack<Double> stack, ArrayList<Double> hist) {
        return callback(hist, stack, hist);
    }
}
