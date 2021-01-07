package licence.projet.datatypes;

import java.util.ArrayList;
import java.util.Stack;

public class RealNumber implements Expression {
    private final String value;

    public RealNumber(String value) {
        this.value = value;
    }

    public String getValue(Stack<String> stack, ArrayList<String> hist) {
        stack.push(value);
        hist.add(value);
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
   
}
