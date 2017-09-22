package vendingmachine.money;

/**
 * ${FILE_NAME}.java - Description
 *
 * @author
 * @version 15/Sep/2017
 */
public enum Coin {
    Quarter( 25 ), Dime( 10 ), Nickel( 5 ), Penny( 1 );

    public int value;

    Coin( int cents ) {
        this.value = cents;
    }
}
