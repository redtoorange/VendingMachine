package vendingmachine.money;

/**
 * ${FILE_NAME}.java - Description
 *
 * @author
 * @version 15/Sep/2017
 */
public class CurrencyController {
    private Bank bank;
    private Currency currentBalance;

    public CurrencyController() {
        bank = new Bank();
        currentBalance = new Currency( 0 );
    }

    public void addCoinToBalance( Coin coinToAdd ) {
        currentBalance.addCents( coinToAdd.value );
        bank.addCoin( coinToAdd );
    }

    public Currency getCurrentBalance() {
        return currentBalance;
    }

    public void refundCurrentBalance(){
        bank.refund( currentBalance );
        currentBalance = new Currency( 0 );
    }

    public void completeTransaction( Currency cost ){
        currentBalance.sub( cost );
        refundCurrentBalance();
    }
}
