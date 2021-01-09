package licence.projet.datatypes;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Stack;

public interface Callback {
    /**
     * Returns the element in lst at the position given by the attribute 'index'
     * Adds this element in the stack and history.
     * @param lst   Can either be the stack or the history
     * @param stack The stack used by the whole program to store and use values
     * @param hist  The history used by the whole program to store and load values
    */
    String callback(AbstractList<String> lst, Stack<String> stack, ArrayList<String> hist);
}
