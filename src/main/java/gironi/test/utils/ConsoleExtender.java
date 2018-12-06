package gironi.test.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Give access to some Console method to read values and select options froma simple menu
 */
public final class ConsoleExtender {

    /**
     * Buffer to read from console
     */
    private final static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    /**
     *
     * @param options the list of options user can choose, numeric selection from 1 to options.length
     * @param prompt the prompt to show to the user
     * @return the selected id
     */
    public static String getOptions(String[] options, String prompt) {
        while (true) {
            for(String option : options) {
                System.out.println(option);
            }
            System.out.print(prompt);
            try {
                String choice = reader.readLine();
                if (choice.length() == 0)
                    break;

                long id = Long.parseLong(choice);
                if (id > 0 && id <= options.length) {
                    return choice;
                } else {
                    System.out.println("Invalid option id");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                System.out.println("Option must be numeric from 1 to " + options.length);
            }

        }

        return "";
    }

    /**
     * Return a string from console containing a Numeric value
     * @param reader the BufferedReader from console
     * @return the Numeric value or empty string if it is not valid
     */
    public static String getNumericValue(String prompt) {
        try {
            System.out.print(prompt);
            String retVal = reader.readLine();

            if (retVal.length() > 0) {
                // Check if account is a valid number
                try {
                    Double.parseDouble(retVal);
                } catch (NumberFormatException e) {
                    System.out.println(String.format("Value entered (%s) is not numeric", retVal));
                    return "";
                }
            }
            return retVal;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Return a String from Console and perform some checks
     * @param reader the BufferedReader from console
     * @return the input string or empty string if it is not valid
     */
    public static String getStringValue(String prompt, int maxLength) {
        try {
            System.out.print(prompt);
            String retVal = reader.readLine();
            if (retVal.length() > maxLength) {
                System.out.println(String.format("Value entered (%s) is too long, max lenght is %d", retVal, maxLength));
                return "";
            }
            return retVal;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Return a string from console containing a Date value with DD/MM/YYYY format
     * @param reader the BufferedReader from console
     * @return the input string or empty string if it is not valid
     */
    public static String getDateValue(String prompt) {
        try {
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            System.out.print(prompt);
            String retVal = reader.readLine();
            if (retVal.length() > 0) {
                try {
                    df.parse(retVal);
                } catch (ParseException e) {
                    System.out.println(String.format("Value entered (%s) has not the valid format DD/MM/YYYY\n", retVal));
                    return "";
                }
            }
            return retVal;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

}
