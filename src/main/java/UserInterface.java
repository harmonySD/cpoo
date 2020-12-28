import licence.projet.datatypes.ExpressionFactory;
import licence.projet.datatypes.Expression;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

public class UserInterface {

    public static String getInput() {
        BufferedReader reader =  new BufferedReader(new InputStreamReader(System.in));
        try {
            String input;
            System.out.print("> ");
            input = reader.readLine();
            return input;

        } catch (IOException ioe) {
            throw new IllegalStateException(ioe);
        }
    }

    public static void main(String[] args) {
        boolean readyToQuit = false;
        Stack<Double> stack = new Stack<>();
        ArrayList<Double> hist = new ArrayList<>();
        String input;

        while (!readyToQuit) {
            input = getInput();

            if (input.equals("q") || input.equals("quit")) {
                readyToQuit = true;
                continue;
            }

            String[] innerExpressions = input.split(" ");
            if (innerExpressions.length < 1) {
                throw new IllegalStateException("An expression shouldn't be empty!");
            }

            ExpressionFactory exprFact = new ExpressionFactory();
            for (String exprString : innerExpressions) {
                try {
                    Expression lastExprEntered = exprFact.getExprFromString(exprString);
                    lastExprEntered.getValue(stack, hist);
                    int index = stack.size() - 1;
                    System.out.println(stack.get(index));
                } catch (IllegalArgumentException illegalArgumentException) {
                    illegalArgumentException.printStackTrace();
                }
            }
        }
    }
}
