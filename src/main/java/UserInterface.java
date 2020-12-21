import licence.projet.datatypes.GeneralExpression;
import licence.projet.datatypes.Expression;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

            Expression lastExprEntered = new GeneralExpression(input);
            System.out.println(lastExprEntered.getValue(stack, hist));

        }
    }
}
