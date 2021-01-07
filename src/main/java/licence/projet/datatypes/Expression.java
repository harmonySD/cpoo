package licence.projet.datatypes;

import java.util.ArrayList;
import java.util.Stack;

public interface Expression {
    public String getValue(Stack<String> stack, ArrayList<String> hist);
   
}
