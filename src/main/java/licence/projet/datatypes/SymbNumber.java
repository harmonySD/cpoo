package licence.projet.datatypes;

import java.util.ArrayList;
import java.util.Stack;

public class SymbNumber implements Expression {

	private final String val;
	
	public SymbNumber(String value) {
		String s=Character.toString(value.charAt(1));
		this.val=s;
	}
    public String getValue(Stack<String> stack, ArrayList<String> hist) {
        stack.push(val);
        hist.add(val);
        return val;
    }
    @Override
    public String toString() {
        return val;
    }

}
