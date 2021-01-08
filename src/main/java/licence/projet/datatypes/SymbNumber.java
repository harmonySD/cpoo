package licence.projet.datatypes;

import java.util.ArrayList;
import java.util.Stack;

public class SymbNumber implements Expression{
    private final String value;

    public SymbNumber(char value) {
        this.value = Character.toString(value);
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
