package gironi.test.banking;

import gironi.test.utils.ConsoleExtender;
import java.util.HashMap;

/**
 * Implements the base Account information
 */
public class Account {
    private String accountID;

    /**
     * Create an empty Account
     */
    public Account() {
    }

    /**
     * Create an Account with the specified accountID
     * @param accountid
     */
    public Account(String accountid) {
        accountID = accountid;
    }

    /**
     * Initializa the Account using input from Console
     * @return true if the AccountID is valid
     */
    public boolean InitFromConsole() {
        accountID = ConsoleExtender.getNumericValue("Please enter your AccountManager ID (empty to exit): ");
        return accountID.length() > 0;
    }

    /**
     * Return the AccountID
     * @return
     */
    public String getAccountID() {
        return accountID;
    }

    /**
     * Return true if the Account is valid
     * @return
     */
    public boolean IsValid() {
        return accountID.length() > 0;
    }

    /**
     * Return the map of the parameters we need to send to the POST HTTP request
     * @return an empty map
     */
    public HashMap<String, String> getBodyParamaters() {
        return null;
    }
}
