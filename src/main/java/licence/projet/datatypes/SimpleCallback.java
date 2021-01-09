package licence.projet.datatypes;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Stack;

public abstract class SimpleCallback implements Callback, Expression {
    private final int index;

    public SimpleCallback (int index) {
        this.index = index;
    }

    /**
     * Returns the element in lst at the position given by the attribute 'index'
     * Adds this element in the stack and history.
     * @param lst   Can either be the stack or the history
     * @param stack The stack used by the whole program to store and use values
     * @param hist  The history used by the whole program to store and load values
    */
    public String callback(AbstractList<String> lst, Stack<String> stack, ArrayList<String> hist) {
        int index;
        String val;

        index = getPositiveIndex(lst);
        if (index >= lst.size() || index < 0)
            throw new IllegalArgumentException();

        val = lst.get(index); 
        stack.push(val);
        hist.add(val);
        return val;
    }

    /**
     * Returns the as is index if index >= 0, 
     * else returns lst.size + index (-1 -> top of lst, -2 -> second after the top...)
     * @param lst   The list from which we get the size if index is negative
    */
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
