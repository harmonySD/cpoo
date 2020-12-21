package licence.projet.datatypes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Factory Pattern
public class CallbackFactory {

    public SimpleCallback getCallback(String callbckType) {
        String type, indexString;
        int index;
        type = callbckType.substring(0,4);

        Pattern pattern = Pattern.compile("(-?)[0-9]+");
        Matcher matcher = pattern.matcher(callbckType);
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
        return null;
    }
}
