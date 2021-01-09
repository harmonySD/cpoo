package licence.projet.datatypes;

import java.util.ArrayList;
import java.util.Stack;

public class HistCallback extends SimpleCallback {

    public HistCallback(int index) {
        super(index);
    }

    public String getValue(Stack<String> stack, ArrayList<String> hist) {
        //We use history as the first param because we want the callback to be from the history
        return callback(hist, stack, hist);
    }
}
