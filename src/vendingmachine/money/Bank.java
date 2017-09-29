package vendingmachine.money;

import java.util.Stack;

/**
 * Bank.java - The Bank Class contains four Collections of {@link Coin}s, one for each type.  The bank can
 * add coins to the collections, total the value of all {@link Coin}s in the bank and Dispense change as needed.  An Operator
 * may choose to Empty the entire Bank or to add coins back to the Bank to help create better change for the Customer.
 * If a Customer requires change, but there are not enough coins, then an error message is displayed in the "Log".
 *
 * @author Andrew McGuiness
 * @version 9/12/2017
 */
public class Bank {
    private Stack< Coin > quarters;
    private Stack< Coin > dimes;
    private Stack< Coin > nickels;
    private Stack< Coin > pennies;

    public Bank() {
        quarters = new Stack< Coin >();
        dimes = new Stack< Coin >();
        nickels = new Stack< Coin >();
        pennies = new Stack< Coin >();

        //TODO: Remove this, debug code only.
        for ( int i = 0; i < 10; i++ ) {
            quarters.push( Coin.Quarter );
            dimes.push( Coin.Dime );
            nickels.push( Coin.Nickel );
            pennies.push( Coin.Penny );
        }
    }

    /**
     * Add a coin directly to the Bank's coin collections.
     *
     * @param coin {@link Coin} to add.
     */
    public void addCoin( Coin coin ) {
        switch ( coin ) {
            case Quarter:
                quarters.push( coin );
                break;
            case Dime:
                dimes.push( coin );
                break;
            case Nickel:
                nickels.push( coin );
                break;
            case Penny:
                pennies.push( coin );
        }
    }

    /**
     * Get the {@link Currency} value of the entire Bank.
     *
     * @return {@link Currency} containing the full value of the Bank, this include coins inserted by the user.
     */
    public Currency getBalance() {
        int total = 0;

        total += Coin.Quarter.getCentValue() * quarters.size();
        total += Coin.Dime.getCentValue() * dimes.size();
        total += Coin.Nickel.getCentValue() * nickels.size();
        total += Coin.Penny.getCentValue() * pennies.size();

        return new Currency( total );
    }


    /**
     * Dispense the given amount of {@link Currency} in {@link Coin}s from the Bank's Collections.  The most efficient way to dispense
     * change (fewest coins) is used to dispense change.
     *
     * @param amount The amount of change to dispense.
     */
    public void refund( Currency amount ) {
        int cents = amount.totalCentValue();

        while ( cents > 0 && cents / 25 > 0 && !quarters.empty() ) {
            cents -= 25;
            quarters.pop();
            System.out.println( "Dispense Quarter" );
        }
        while ( cents > 0 && cents / 10 > 0 && !dimes.empty() ) {
            cents -= 10;
            dimes.pop();
            System.out.println( "Dispense Dime" );
        }
        while ( cents > 0 && cents / 5 > 0 && !nickels.empty() ) {
            cents -= 5;
            nickels.pop();
            System.out.println( "Dispense Nickel" );
        }
        while ( cents > 0 && !pennies.empty() ) {
            cents -= 1;
            pennies.pop();
            System.out.println( "Dispense Penny" );
        }

        if ( cents > 0 ) {
            System.out.println( "Insufficient coins to dispense complete change." );
        }

        System.out.println( toString() );
    }

    /**
     * Convert the {@link Coin} Collections into a nicely formatted String.
     *
     * @return The String form of the Bank's {@link Coin}s
     */
    public String toString() {
        StringBuilder b = new StringBuilder();
        b.append( "Quarters: " );
        b.append( quarters.size() );
        b.append( "\nDimes: " );
        b.append( dimes.size() );
        b.append( "\nNickels: " );
        b.append( nickels.size() );
        b.append( "\nPennies: " );
        b.append( pennies.size() );
        b.append( "\n" );

        return b.toString();
    }

    /**
     * Clear all the collections of the Bank, this "dispenses" all of the {@link Coin}s.
     */
    public void clearBank() {
        refund( getBalance() );
    }
}
