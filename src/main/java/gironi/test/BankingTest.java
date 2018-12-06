package gironi.test;

import gironi.test.banking.*;
import gironi.test.utils.ConsoleExtender;

/**
 * A simple Console application to get the Balance for an account and make a SCTOrder
 */
public class BankingTest {

    public static void main(String[] args) {

        System.out.println("Automatic Banking Test");
        System.out.print("by Gironi Stefano\n");

        // Options available for test
        final String [] options = {"1) Show Balance", "2) Crete SCT Order"};
        while (true) {
            String choice = ConsoleExtender.getOptions(options, "Please enter your choice (empty to exit): ");

            if (choice.equals("1")) {
                Account a = new Account();
                if (a.InitFromConsole()) {
                    AccountManager.getBalance(a);
                }
            } else if (choice.equals("2")) {
                AccountSCTOrder o = new AccountSCTOrder();
                if (o.InitFromConsole()) {
                    AccountManager.makeSCTOrder(o);
                }
            } else {
                break;
            }
        }
    }

}
