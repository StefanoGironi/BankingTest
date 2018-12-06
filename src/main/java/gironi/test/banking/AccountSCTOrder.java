package gironi.test.banking;

import gironi.test.utils.ConsoleExtender;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;

/**
 * Implement an SCT Order for an Account
 */
public class AccountSCTOrder extends Account {

    private String receiver;
    private String description;
    private String currency;
    private String amount;
    private String executionDate;

    /**
     * Create an empty SCT Order
     */
    public AccountSCTOrder() {
        super();
    }

    /**
     * Create an SCT Order. All attributes are mandatory
     * @param accountid
     * @param receiver
     * @param description
     * @param currency
     * @param amount
     * @param executionDate
     */
    public AccountSCTOrder(String accountid, String receiver, String description, String currency, String amount, String executionDate) {
        super(accountid);
        this.receiver = receiver;
        this.description = description;
        this.currency = currency.length() == 0 ? "EUR" : currency;
        this.amount = amount;
        this.executionDate = executionDate;
        checkExecutionDateDefault();
    }

    /**
     * Init an SCT Order using console input
     * @return true if all data are valid
     */
    @Override
    public boolean InitFromConsole() {
        if (!super.InitFromConsole())
            return false;

        receiver = ConsoleExtender.getStringValue("Receiver name (max 20, empty to exit): ", 20);
        if (receiver.length() == 0)
            return false;
        description = ConsoleExtender.getStringValue("Description (max 40, empty to exit): ", 40);
        if (description.length() == 0)
            return false;
        currency = ConsoleExtender.getStringValue("Currency (max 3, empty for EUR): ", 3).toUpperCase();
        if (currency.length() == 0) {
            currency = "EUR";
        }
        amount = ConsoleExtender.getNumericValue("Amount (empty to exit): ");
        if (amount.length() == 0)
            return false;
        executionDate = ConsoleExtender.getDateValue("Execution Date (DD/MM/YYYY format, empty for tomorrow): ");
        checkExecutionDateDefault();

        return true;
    }

    /**
     * Check the Execution date. If empty will be set to Tomorrow date
     */
    private void checkExecutionDateDefault() {
        if (executionDate.length() == 0) {
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            executionDate = df.format(Date.from(Instant.now().plusSeconds(86400)));
        }
    }

    /**
     * Get the Receiver
     * @return
     */
    public String getReceiver() {
        return receiver;
    }

    /**
     * Get the Description
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get the Currency
     * @return
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Get the Amount
     * @return
     */
    public String getAmount() {
        return amount;
    }

    /**
     * Get the Execution Date
     * @return
     */
    public String getExecutionDate() {
        return executionDate;
    }

    /**
     * Return true iof the SCR Order is valid
     * @return
     */
    @Override
    public boolean IsValid() {
        return super.IsValid() && receiver.length() > 0 && description.length() > 0 && currency.length() > 0 && amount.length() > 0 && executionDate.length() > 0;
    }

    /**
     * Return the map of the parameters we need to send to the POST HTTP request
     * @return the map with all the parameters
     */
    @Override
    public HashMap<String, String> getBodyParamaters() {

        HashMap<String, String> bodyParamaters = new HashMap<>();
        bodyParamaters.put("receiverName", receiver);
        bodyParamaters.put("description", description);
        bodyParamaters.put("currency", currency);
        bodyParamaters.put("amount", amount);
        bodyParamaters.put("executionDate", executionDate);

        return bodyParamaters;
    }
}
