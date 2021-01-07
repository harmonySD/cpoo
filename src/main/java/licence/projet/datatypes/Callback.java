package licence.projet.datatypes;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Stack;

public interface Callback {
    String callback(AbstractList<String> lst, Stack<String> stack, ArrayList<String> hist);
}
