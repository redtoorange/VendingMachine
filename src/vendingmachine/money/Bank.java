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

//    public List<vendingmachine.money.Currency> dispenseChange( float amount ) throws vendingmachine.money.CurencyException{
//        List<vendingmachine.money.Currency> change = new ArrayList<vendingmachine.money.Currency>(  );
//
//        amount = makeChange( amount, vendingmachine.money.Currency.Quarter, quarters, change );
//        amount = makeChange( amount, vendingmachine.money.Currency.Dime, dimes, change );
//        amount = makeChange( amount, vendingmachine.money.Currency.Nickel, nickels, change );
//        makeChange( amount, vendingmachine.money.Currency.Penny, pennies, change );
//
//        if( amount > 0 )
//            throw new vendingmachine.money.CurencyException( "Insufficient coins to make change." );
//
//        return  change;
//    }
//
//    private float makeChange(float amount, vendingmachine.money.Currency coin, Stack<vendingmachine.money.Currency>coins, List<vendingmachine.money.Currency> change ){
//        float remaining = amount;
//
//        while( remaining / coin.value > 0 && !coins.isEmpty()){
//            remaining /= coin.value;
//            change.add( coins.pop() );
//        }
//
//        return remaining;
//    }
}
