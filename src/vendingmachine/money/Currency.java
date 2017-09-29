package vendingmachine.money;

/**
 * vendingmachine.money.Currency.java - Description
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

    public int getDollars() {
        return dollars;
    }

    public int getCents() {
        return cents;
    }

    public int totalCentValue(){
        return (dollars * 100) + cents;
    }

    public boolean equals( Currency other ) {
        boolean equal = false;

        if ( other.dollars == this.dollars && other.cents == this.cents )
            equal = true;

        return equal;
    }

    public void add( Currency other ){

        addCents( other.totalCentValue() );
    }

    public void sub(Currency other ){
        subCents( other.totalCentValue() );
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

    public void subCents( int centsToSub ) {
        if ( cents >= centsToSub ) {
            cents -= centsToSub;
        } else if ( dollars > 0 ) {
            dollars--;
            cents += 100;
            subCents( centsToSub );
        }
    }





    @Override
    public String toString() {
        int d = Math.abs( dollars );
        int c = Math.abs( cents );

        StringBuilder sb = new StringBuilder( (isPositive() ? "" : "-" ) + "$" + d + "." );

        if ( c == 0 )
            sb.append( "00" );
        else if ( c < 10 )
            sb.append( "0" + c );
        else
            sb.append( c );

        return sb.toString();
    }

    public boolean lessThanOrEqual( Currency other ){
        return totalCentValue() <= other.totalCentValue();
    }

    public boolean isPositive(){
        return cents >= 0 && dollars >= 0;
    }
}
