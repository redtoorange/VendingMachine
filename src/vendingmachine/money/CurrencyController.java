package vendingmachine.money;

/**
 * CurrencyController.java - The {@link CurrencyController} handles all communication from the
 * {@link vendingmachine.VendingMachine} to the {@link Bank}.  It maintains a running balance for the {@link Coin}s the
 * Customer has put in.  It can add to the balance, add to the {@link Bank}, refund the balance and refund the balance
 * less some given {@link Currency}.
 *
 * @author Andrew McGuiness
 * @version 15/Sep/2017
 */
public class CurrencyController {
    private Bank bank;
    private Currency currentBalance;

    public CurrencyController() {
        bank = new Bank();
        currentBalance = new Currency( 0 );
    }

    /**
     * Add the given {@link Coin}'s value to the current balance and add the {@link Coin} to the {@link Bank}.
     *
     * @param coinToAdd {@link Coin} to add.
     */
    public void addCoinToBalance( Coin coinToAdd ) {
        currentBalance.addCents( coinToAdd.getCentValue() );
        bank.addCoin( coinToAdd );
    }

    /**
     * Get the Customer's {@link Currency} Balance.
     *
     * @return {@link Currency} Value of the Customer's balance.
     */
    public Currency getCurrentBalance() {
        return currentBalance;
    }

    /**
     * Refund the Customer's current balance.
     */
    public void refundCurrentBalance() {
        bank.refund( currentBalance );
        currentBalance = new Currency( 0 );
    }

    /**
     * Subtract the {@link vendingmachine.product.OrderList} total cost from the Customer Balance, then refund the
     * remainder.
     *
     * @param cost {@link Currency} cost of the Customer's {@link vendingmachine.product.OrderList}.
     */
    public void completeTransaction( Currency cost ) {
        currentBalance.sub( cost );
        refundCurrentBalance();
    }

    /**
     * Add the given {@link Coin} directly to the {@link Bank} without adding it to the Customer's Balance.
     *
     * @param coinToAdd {@link Coin} to put in the {@link Bank}.
     */
    public void addCoinToBank( Coin coinToAdd ) {
        bank.addCoin( coinToAdd );
    }

    /**
     * Return this {@link CurrencyController}'s {@link Bank} Instance
     *
     * @return Current {@link Bank} Instance
     */
    public Bank getBank() {
        return bank;
    }
}