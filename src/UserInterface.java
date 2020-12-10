import licence.projet.datatypes.BopExpression;
import licence.projet.datatypes.Expression;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
        String input;

        while (!readyToQuit) {
            input = getInput();
            Expression lastExprEntered = new BopExpression(input);
            System.out.println(lastExprEntered.getValue(stack));

            if (input.equals("q") || input.equals("quit")) {
                readyToQuit = true;
            }
        }
    }
}
