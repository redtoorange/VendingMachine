package vendingmachine.money;

/**
 * Coin.java - A Simple enum representation of a coin.  Separating the implementation of {@link Coin}s from {@link Currency} allows for
 * the implementation of simplified Collections to handle Banking.  All {@link Coin}s have an centValue that represents it's
 * coin value as an int.
 * <p>
 * The centValue of the {@link Coin} should be preferred when using it as a piece of a total, as it will allow the object to
 * remain as a piece of a {@link Currency} Total.
 *
 * @author Andrew McGuiness
 * @version 15/Sep/2017
 */
public enum Coin {
    Quarter( 25 ), Dime( 10 ), Nickel( 5 ), Penny( 1 );

    private final int centValue;

    Coin( int cents ) {
        this.centValue = cents;
    }

    /**
     * Get the cent value of the {@link Coin} as an int.  This should be used most of the time, since typically you would ADD
     * the centValue of a {@link Coin} to a total.  Rarely is the {@link Coin} itself the total.
     *
     * @return centValue of the {@link Coin}
     */
    public int getCentValue() {
        return centValue;
    }
}
