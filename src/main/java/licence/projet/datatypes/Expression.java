package licence.projet.datatypes;

import java.util.ArrayList;
import java.util.Stack;

public interface Expression {
    /** 
     * Returns a String as a result.
     * Adds in the stack and history the result that is returned
     * Eventually pops some operands from the stack if needed
     * @param stack the stack used in the whole program to store and use values
     * @param hist  the history used in the whole program to store and load values
    */
    public String getValue(Stack<String> stack, ArrayList<String > hist);
}
