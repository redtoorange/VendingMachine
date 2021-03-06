package vendingmachine.gui;

/**
 * Messages.java - Central location that will contain all the {@link String}s used by the
 * {@link vendingmachine.VendingMachine}.  This could be swapped out for a different {@link Messages} class depending
 * on the language the {@link vendingmachine.VendingMachine} should use.
 *
 * @author Andrew McGuiness
 * @version 9/29/2017
 */
public class Messages {
    public static final String OPERATOR_MSG =
            "Operator Mode Enabled." +
                    "\nPress 0 to enter bank mode." +
                    "\nPress 1 to enter restock mode.";

    public static final String BANKING_MSG =
            "Add coins now to add to bank." +
                    "\nPress 0 to empty bank." +
                    "\nPress Cancel/Finish to go back.";

    public static final String RESTOCK_MSG =
            "Restock Mode:" +
                    "\nEnter slot number + Enter to restock." +
                    "\nPress Cancel/Finish to go back.";

    public static final String RESTOCKING_MSG =
            "Enter new stock level for slot + Enter." +
                    "\nPress Cancel/Finish to go back.";

    public static final String OUT_OF_STOCK = "Product Out of Stock";
    public static final String BAD_INPUT = "Invalid Input";
    public static final String PRODUCT_ADDED = "Product added to order.";
    public static final String ORDER_FINISHED = "Product order finished.";
    public static final String ILLEGAL_STOCK = "Stock level cannot be negative.";
}
