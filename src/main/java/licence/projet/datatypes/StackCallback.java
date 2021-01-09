package licence.projet.datatypes;

import java.util.ArrayList;
import java.util.Stack;

public class StackCallback extends SimpleCallback {

    public StackCallback(int index) {
        super(index);
    }

    public String getValue(Stack<String> stack, ArrayList<String> hist) {
        //We use stack as the first param bc we want the callback to be from the stack
        return callback(stack, stack, hist);
    }
}
