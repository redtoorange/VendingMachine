package vendingmachine.money;

/**
 * Currency.java - The {@link Currency} class is designed to represent a sum of money.  This is abstracted away from the
 * {@link Coin}s that might make up the value, it is a purely numeric value.  This is used by the
 * {@link vendingmachine.VendingMachine} to avoid needing to track all the Coins the user inserted and when.
 *
 * @author Andrew McGuiness
 * @version 9/12/2017
 */
public class Currency {
    private int dollars = 0;
    private int cents = 0;

    public Currency( int dollars, int cents ) {
        this.dollars = dollars;
        addCents( cents );
    }

    public Currency( int cents ) {
        addCents( cents );
    }

    /**
     * Get to total value of this {@link Currency} in the form of an int representation (cents).
     *
     * @return Total Cent value of this {@link Currency} (including dollars).
     */
    public int totalCentValue() {
        return ( dollars * 100 ) + cents;
    }

    /**
     * Compare the value of this {@link Currency} to another instance to see if they are equal.
     *
     * @param other {@link Currency} to use to check for equality
     * @return True of the total value of both {@link Currency} instances are equal.
     */
    public boolean equals( Currency other ) {
        boolean equal = false;

        if ( other.dollars == this.dollars && other.cents == this.cents )
            equal = true;

        return equal;
    }

    /**
     * Add another {@link Currency} to this {@link Currency}
     *
     * @param other {@link Currency} to add.
     */
    public void add( Currency other ) {
        addCents( other.totalCentValue() );
    }

    /**
     * Subtract another {@link Currency} from this {@link Currency}
     *
     * @param other {@link Currency} to subtract.
     */
    public void sub( Currency other ) {
        subCents( other.totalCentValue() );
    }

    /**
     * Add cents to the value of this {@link Currency}
     *
     * @param centsToAdd number of cents to add.
     */
    public void addCents( int centsToAdd ) {
        cents += centsToAdd;

        while ( cents >= 100 ) {
            cents -= 100;
            dollars++;
        }
    }

    /**
     * Subtract cents from the value of this {@link Currency}.  This can cause it to go negative.
     *
     * @param centsToSub number of cents to subtract.
     */
    public void subCents( int centsToSub ) {
        cents -= centsToSub;

        while ( cents < 0 && dollars > 0 ) {
            cents += 100;
            dollars--;
        }
    }

    /**
     * Compare the value of this {@link Currency} to another {@link Currency} instance, if this one is less than or
     * equal to the other, return true.
     *
     * @param other {@link Currency} instance to compare to.
     * @return True of this currency is less than or equal to the other
     */
    public boolean lessThanOrEqual( Currency other ) {
        return totalCentValue() <= other.totalCentValue();
    }

    /**
     * Convert the value of this {@link Currency} into a formatted String.
     *
     * @return {@link Currency} as a String
     */
    @Override
    public String toString() {
        int d = Math.abs( dollars );
        int c = Math.abs( cents );

        StringBuilder sb = new StringBuilder( ( isPositive() ? "" : "-" ) + "$" + d + "." );

        if ( c == 0 )
            sb.append( "00" );

        else if ( c < 10 ){
            sb.append( "0" );
            sb.append( c );
        }

        else
            sb.append( c );

        return sb.toString();
    }


    private boolean isPositive() {
        return cents >= 0 && dollars >= 0;
    }
}
