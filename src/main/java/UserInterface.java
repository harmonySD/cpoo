import licence.projet.datatypes.ExpressionFactory;
import licence.projet.datatypes.Expression;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

public class UserInterface {

    public static String getInput() {
        //On cree un BufferedReader sur l'entree standard (ligne extraite d'un ancien projet de DUT)
        BufferedReader reader =  new BufferedReader(new InputStreamReader(System.in));

        try {
            String input;
            //On affichage l'invite de commande
            System.out.print("> ");
            //On demande a l'utilisateur de rentrer qqchose
            input = reader.readLine();
            return input;

        } catch (IOException ioe) {
            throw new IllegalStateException(ioe);
        }
    }

    private static void parseInput(String input, ExpressionFactory exprFact, Stack<Double> stack, ArrayList<Double> hist) {
        String[] innerExpressions = input.split(" ");
        if (innerExpressions.length < 1) {
            System.out.println("An expression shouldn't be empty!");
            return;
        }

        for (String exprString : innerExpressions) {
            try {
                Expression lastExprEntered = exprFact.getExprFromString(exprString, stack);
                // On passe a l'expression la pile et l'historique pour que le resultat y soit ajoute et que les operandes y soient extraits
                // (on pourrait tout aussi bien ajouter le resultat a la pile et a l'historique ici)
                lastExprEntered.getValue(stack, hist);
                int index = stack.size() - 1;
                System.out.println(stack.get(index));

            } catch (IllegalArgumentException illegalArgumentException) {
                System.out.println("Not able to parse your input. Please enter a valid expression! (" + stack.size() + " element(s) currently in stack)\n");
                break;
            }
        }
    }

    public static void main(String[] args) {
        boolean readyToQuit = false;
        Stack<Double> stack = new Stack<>();
        ArrayList<Double> hist = new ArrayList<>();
        String input;
        ExpressionFactory exprFact = new ExpressionFactory();

        while (!readyToQuit) {
            input = getInput();

            //On verifie que l'utilisateur ne demande pas a quitter avant de traiter quoi que ce soit
            if (input.equals("q") || input.equals("quit")) {
                readyToQuit = true;
                continue;
            }

            // Methode ou on s'effectue tous les traitements et operations necessaires
            // a la mise a jour de la pile et de l'historique
            parseInput(input, exprFact, stack, hist);
        }
    }
}
