package vendingmachine.money;

import java.util.Stack;

/**
 * vendingmachine.money.Bank.java - Description
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
    }

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

    public Currency getBalance() {
        int total = 0;

        for ( Coin c : quarters )
            total += c.value;

        for ( Coin c : dimes )
            total += c.value;

        for ( Coin c : nickels )
            total += c.value;

        for ( Coin c : pennies )
            total += c.value;

        return new Currency( total );
    }

    public void refund( Currency amount ){
        int cents = amount.totalCentValue();

        while( cents > 0 && cents % 25 == 0 && !quarters.empty()){
            cents -= 25;
            quarters.pop();
            System.out.println( "Dispense Quarter" );
        }
        while( cents > 0 && cents % 10 == 0 && !dimes.empty()){
            cents -= 10;
            dimes.pop();
            System.out.println( "Dispense Dime" );
        }
        while( cents > 0 && cents % 5 == 0 && !nickels.empty()){
            cents -= 5;
            nickels.pop();
            System.out.println( "Dispense Nickel" );
        }
        while( cents > 0 && cents % 1 == 0 && !pennies.empty()){
            cents -= 1;
            pennies.pop();
            System.out.println( "Dispense Penny" );
        }

        if( cents > 0){
            System.out.println( "Insufficient coins to dispense complete change." );
        }

        System.out.println( toString() );
    }

    public String toString(){
        StringBuilder b = new StringBuilder(  );
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
}
