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
        String val;

        index = getPositiveIndex(lst);
        val = lst.get(index);
        stack.push(val);
        hist.add(val);
        return val;
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
