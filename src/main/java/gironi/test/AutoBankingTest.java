package gironi.test;

import gironi.test.banking.*;

/**
 * A simple automated application to get the Balance for an account and make a SCTOrder
 * No user input is required.
 */
public class AutoBankingTest {

    public static void main(String[] args) {
        System.out.println("Automatic Banking Test");
        System.out.print("by Gironi Stefano\n");

        Account a = new Account("12345678");
        AccountManager.getBalance(a);

        AccountSCTOrder o = new AccountSCTOrder("87654321", "Receiver Test", "Automatic SCT Order description", "EUR", "3450", "");
        AccountManager.makeSCTOrder(o);
    }

}
