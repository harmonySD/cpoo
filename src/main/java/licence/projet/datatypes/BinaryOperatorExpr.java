package licence.projet.datatypes;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Stack;
import java.util.function.BinaryOperator;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;

public class BinaryOperatorExpr implements Expression {
    private static final Map<String, BinaryOperator<Double>> operations;
    static {
        Map<String, BinaryOperator<Double>> operationsMap = new HashMap<>();
        BinaryOperator<Double> add, minus, mult, div, pow;
        add = (x, y) -> x + y;
        minus = (x, y) -> x - y;
        mult = (x, y) -> x * y;
        div = (x, y) -> x / y;
        pow = (x, y) -> Math.pow(x, y);

        operationsMap.put("+", add);
        operationsMap.put("-", minus);
        operationsMap.put("*", mult);
        operationsMap.put("/", div);
        operationsMap.put("^", pow);
        operations = Collections.unmodifiableMap(operationsMap);
    }
    private final String op;

    public BinaryOperatorExpr(String op) {
        Objects.requireNonNull(op);
        this.op = op;
    }

    public static boolean isOperator(String opString) {
        return operations.keySet().contains(opString);
    }

    private double compute(double val1, double val2) {
        if (operations.keySet().contains(op)) {
            if (val2 == 0 && op.equals("/")) {
                System.out.println("Can't divide by zero!\n");
                throw new IllegalArgumentException();
            }

            BinaryOperator<Double> operation = operations.get(op);
            return operation.apply(val1, val2);

        } else {
            throw new IllegalStateException("Operator " + op + " was not recognized!");
        }
    }

    public double getValue(Stack<Double> stack, ArrayList<Double> hist) {
        if (!(stack.empty()) && stack.size() > 1) {
            double val1, val2, res;
            //On recupere sans pop pour ne pas les enlever de la pile au cas ou on ne terminerait pas le calcul (divsion par 0 par exemple)
            int size = stack.size();
            val2 = stack.get(size-1);
            val1 = stack.get(size-2);
            res = compute(val1, val2);
            //Une fois le calcul effectue, on peut pop les operandes de la pile
            stack.pop();
            stack.pop();
            stack.push(res);
            hist.add(res);
            return res;
        } else {
            throw new IllegalArgumentException("Not enough operand in stack!");
        }
    }

    @Override
    public String toString() {
        return op;
    }
}
