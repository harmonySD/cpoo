package licence.projet.datatypes;

import java.util.ArrayList;
import java.util.Stack;

public class StackCallback extends SimpleCallback {

    public StackCallback(int index) {
        super(index);
    }

    public String getValue(Stack<String> stack, ArrayList<String> hist) {
        return callback(stack, stack, hist);
    }
}
