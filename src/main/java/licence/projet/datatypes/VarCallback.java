package licence.projet.datatypes;

import java.util.ArrayList;
import java.util.Stack;
import java.util.Objects;

public class VarCallback extends SimpleCallback {
    private final String name;
    private final String value;
    private boolean set;

    public VarCallback(String name, String value) {
        super(0);
        Objects.requireNonNull(name);
        Objects.requireNonNull(value);
        this.name = name;
        this.value = value; 
        set = false;
    }

    public String getValue(Stack<String> stack, ArrayList<String> hist) {
        if (set) {
            stack.push(value);
            hist.add(value);
            return value;
        } else {
            set = true;
            return value;
        }
    }
}
