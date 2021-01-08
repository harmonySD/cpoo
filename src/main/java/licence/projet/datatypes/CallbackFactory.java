package licence.projet.datatypes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Stack;
import java.util.Map;
import java.util.HashMap;

//Factory Pattern
public class CallbackFactory {
    private final Map<String, VarCallback> variables;

    public CallbackFactory() {
        this.variables = new HashMap<>();
    }

    public SimpleCallback getCallback(String callbckType, Stack<String> stack) {
        //On verifie pour les rappels avec des variables
        Pattern pattern = Pattern.compile("^!|^\\?");
        Matcher matcher = pattern.matcher(callbckType);
        if (matcher.find()) {
            String name, accessType;
            name = callbckType.substring(1);
            accessType = matcher.group(0);
            if (accessType.equals("!")) {
                if (!variables.keySet().contains(name)) {
                    String val = stack.pop();
                    double value = Double.parseDouble(val);
                    VarCallback varCallback = new VarCallback(name, value);
                    variables.put(name, varCallback);
                    return varCallback;
                } else {
                    System.out.println("The value of this variable is already set!\n");
                    throw new IllegalArgumentException();
                }

            } else if (accessType.equals("?")) {
                //Si la variable n'est pas dans la map de variables sauvegardees, alors on throw
                VarCallback varCallback = variables.get(name);
                if (varCallback == null) {
                    System.out.println("The value of this variable is not set!\n");
                    throw new IllegalArgumentException();
                } else {
                    //Sinon on peut recuperer la variable grace a la map
                    return varCallback;
                }

            } else {
                throw new IllegalStateException("Access type is unvalid!");
            }
        } else {
            //On verifie pour les rappels de la forme hist(x) ou pile(x)
            // avec x un entier compris entre 0 et (stack.size - 1)
            String type, indexString;
            int index;
            type = callbckType.substring(0,4);

            pattern = Pattern.compile("(-?)[0-9]+");
            matcher = pattern.matcher(callbckType);
            if (matcher.find()) {
                indexString = matcher.group(0);
                try {
                    index = Integer.parseInt(indexString);
                    if (type.equalsIgnoreCase("hist")) {
                        return new HistCallback(index);
                    } else if (type.equalsIgnoreCase("pile")) {
                        return new StackCallback(index);
                    }

                } catch (NumberFormatException nfe) {
                    throw new IllegalArgumentException("Wrong argument for callback! " +
                            "Callbacks must be done with the following format:\n'hist(x)'\t'pile(x)'\t where x is an integer");
                }
            } else {
                throw new IllegalArgumentException("Wrong argument for callback! " +
                        "Callbacks must be done with the following format:\n'hist(x)'\t'pile(x)'\t where x is an integer");
            }
        }
        //Impossible d'arriver ici : soit on throw, soit on return avant
        System.out.println("Si ce message s'affiche, vous avez rencontre un bug!\n");
        return null;
    }
}
