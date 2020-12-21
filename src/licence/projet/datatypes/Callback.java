package licence.projet.datatypes;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Stack;

public interface Callback {
    double callback(AbstractList<Double> lst, Stack<Double> stack, ArrayList<Double> hist);
}
