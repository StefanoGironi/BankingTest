package gironi.test.banking;

import gironi.test.utils.HttpPlatfrIo;
import javax.json.JsonObject;
import javax.json.JsonString;
import java.util.HashMap;

/**
 * Manage Account operations
 */
public final class AccountManager {
    /**
     * URL to get the AccountManager Balance
     */
    private static final String balanceURL = "https://sandbox.platfr.io/api/gbs/banking/v2/accounts/%s/balance";
    /**
     * URL to post the SCT Order
     */
    private static final String sctorderURL = "https://sandbox.platfr.io/api/gbs/banking/v2.1/accounts/%s/payments/sct/orders";

    /**
     * Shows the balance of an AccountManager
     * @param reader
     */
    public static void getBalance(Account account) {
        if (!account.IsValid())
            return;

        System.out.println("\nGetting Balance for");
        System.out.println("\tAccount: " + account.getAccountID());
        System.out.println();

        try {
            // Get the balance JSON object
            JsonObject resp = HttpPlatfrIo.getHTML(String.format(balanceURL, account.getAccountID()));
            // Get the payload data
            JsonObject payloadData = resp.getJsonArray("payload").getJsonObject(0);
            // Get the balance data
            JsonString date = payloadData.getJsonString("date");
            JsonString balance = payloadData.getJsonString("balance");
            JsonString availableBalance = payloadData.getJsonString("availableBalance");

            System.out.println(String.format("Cash for %s:\n\tDate: %s\n\tBalance: %s\n\tAvailable balance: %s\n", account.getAccountID(), date.getString(), balance.getString(), availableBalance.getString()));
            System.out.println();
        } catch (Exception e) {
            System.out.println("ERROR reading the balance for " + account.getAccountID());
            System.out.println(e.toString());
        }

    }

    /**
     * Make a SCT Order
     * @param reader
     */
    public static void makeSCTOrder(AccountSCTOrder accountOrder) {
        if (!accountOrder.IsValid())
            return;

        System.out.println("\nSending SCT Order for");
        System.out.println("\tAccount: " + accountOrder.getAccountID());
        System.out.println("\tReceiver: " + accountOrder.getReceiver());
        System.out.println("\tDescription: " + accountOrder.getDescription());
        System.out.println("\tCurrency: " + accountOrder.getCurrency());
        System.out.println("\tAmount: " + accountOrder.getAmount());
        System.out.println("\tExecution Date: " + accountOrder.getExecutionDate());
        System.out.println();

        HashMap<String, String> body = accountOrder.getBodyParamaters();

        try {
            // Get the balance JSON object
            JsonObject resp = HttpPlatfrIo.postHTML(String.format(sctorderURL, accountOrder.getAccountID()), body);
            // Get the status object
            JsonObject statusData = resp.getJsonObject("status");
            // Get the results
            JsonString code = statusData.getJsonString("code");
            JsonString dsc = statusData.getJsonString("description");

            System.out.println(String.format("SCT Order for %s: %s - %s\n", accountOrder.getAccountID(), code.getString(), dsc.getString()));
            System.out.println();
        } catch (Exception e) {
            System.out.println("ERROR making SCT Order for " + accountOrder.getAccountID());
            System.out.println(e.toString());
        }

    }

}
