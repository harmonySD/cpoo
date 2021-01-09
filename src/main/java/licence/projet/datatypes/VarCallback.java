package licence.projet.datatypes;

import java.util.ArrayList;
import java.util.Stack;
import java.util.Objects;

public class VarCallback extends SimpleCallback {
    private final String name;
    //We don't allow the user to change the value of an already set variable
    private final String value;
    //set is used to know if the variable has already been set (with the cmd ![name])
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
        //if set = true and getValue has been called, then the user has entered the cmd ?[name]
        if (set) {
            stack.push(value);
            hist.add(value);
            return value;
        } else {
            //The first time getValue is called (meaning the user has entered the cmd ![name])
            set = true;
            //We don't add anything to the stack in this case
            return value;
        }
    }
}
