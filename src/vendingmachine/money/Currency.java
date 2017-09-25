package vendingmachine.money;

/**
 * vendingmachine.money.Currency.java - Description
 *
 * @author Andrew McGuiness
 * @version 9/12/2017
 */
public class Currency implements Comparable {
    private int dollars = 0;
    private int cents = 0;

    public Currency( int dollars, int cents ) {
        this.dollars = dollars;
        addCents( cents );
    }

    public Currency( int cents ) {
        addCents( cents );
    }

    public int getDollars() {
        return dollars;
    }

    public int getCents() {
        return cents;
    }

    public boolean equals( Currency other ) {
        boolean equal = false;

        if ( other.dollars == this.dollars && other.cents == this.cents )
            equal = true;

        return equal;
    }

    public void add( Currency other ){
        addDollars( other.getDollars() );
        addCents( other.getCents() );
    }

    public double getBalance() {
        return dollars + ( cents / 100.0 );
    }

    public void addCents( int centsToAdd ) {
        cents += centsToAdd;

        if ( cents >= 100 ) {
            cents -= 100;
            dollars++;
        }
    }

    public void addDollars( int dollarsToAdd){
        dollars += dollarsToAdd;
    }

    public void subCents( int centsToSub ) throws CurencyException {
        if ( cents > centsToSub ) {
            cents -= centsToSub;
        } else if ( dollars > 0 ) {
            dollars--;
            cents += 100;
            subCents( centsToSub );
        } else {
            throw new CurencyException( "Subbing too many cents, balance goes negative." );
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder( "$" + dollars + "." );

        if ( cents == 0 )
            sb.append( "00" );
        else if ( cents < 10 )
            sb.append( "0" + cents );
        else
            sb.append( cents );

        return sb.toString();
    }

    @Override
    public int compareTo( Object other ) {
        if ( other instanceof Currency ) {
            Currency otherCurr = ( Currency ) other;


        }

        return 0;
    }
}
