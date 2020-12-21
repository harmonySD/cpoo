package licence.projet.datatypes;

import java.util.ArrayList;
import java.util.Stack;

public interface Expression {
    public double getValue(Stack<Double> stack, ArrayList<Double> hist);
}
