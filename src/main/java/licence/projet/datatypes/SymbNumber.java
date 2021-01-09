package licence.projet.datatypes;

import java.util.ArrayList;
import java.util.Stack;

public class SymbNumber implements Expression{
    private final String value;

    /**constructor **/
    public SymbNumber(char value) {
        this.value = Character.toString(value);
    }
    
    /**return value add in stack and history **/
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
