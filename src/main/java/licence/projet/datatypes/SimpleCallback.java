package licence.projet.datatypes;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Stack;

public abstract class SimpleCallback implements Callback, Expression {
    private final int index;

    public SimpleCallback (int index) {
        this.index = index;
    }

    public String callback(AbstractList<String> lst, Stack<String> stack, ArrayList<String> hist) {
        int index;
        double val;

        index = getPositiveIndex(lst);
        if (index >= lst.size() || index < 0)
            throw new IllegalArgumentException();

        val = Double.parseDouble(lst.get(index));
        stack.push(Double.toString(val));
        hist.add(Double.toString(val));
        return Double.toString(val);
    }

    private int getPositiveIndex(AbstractList<String> lst) {
        if (index >= 0) {
            return index;
        } else {
            int size;
            size = lst.size();
            return size + index;
        }
    }
}
