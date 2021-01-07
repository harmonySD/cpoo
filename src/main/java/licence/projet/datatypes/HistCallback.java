package licence.projet.datatypes;

import java.util.ArrayList;
import java.util.Stack;

public class HistCallback extends SimpleCallback {

    public HistCallback(int index) {
        super(index);
    }

    public String getValue(Stack<String> stack, ArrayList<String> hist) {
        return callback(hist, stack, hist);
    }
}
