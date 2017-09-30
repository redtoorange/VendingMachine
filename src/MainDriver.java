import vendingmachine.io.InputException;
import vendingmachine.money.Bank;
import vendingmachine.money.Coin;
import vendingmachine.money.Currency;
import vendingmachine.product.Product;
import vendingmachine.product.ProductController;
import vendingmachine.product.ProductSlot;

/**
 * MainDriver.java - Description
 *
 * @author Andrew McGuiness
 * @version 9/12/2017
 */
public class MainDriver {
    public static void main( String[] args ) {
        System.out.println( "##Starting vendingmachine.money.Bank Test##" );
        if ( bankTest() )
            System.out.println( "vendingmachine.money.Bank Test Passed" );
        else
            System.out.println( "!!!!vendingmachine.money.Bank Test FAILED!!!!" );

        System.out.println( "\n\n##Starting vendingmachine.product.ProductController Test##" );
        if ( productControllerTest() )
            System.out.println( "vendingmachine.product.ProductController Test Passed" );
        else
            System.out.println( "!!!!vendingmachine.product.ProductController Test FAILED!!!!" );

        System.out.println( "\n\n##Starting vendingmachine.product.ProductSlot Test##" );
        if ( productSlotTest() )
            System.out.println( "vendingmachine.product.ProductSlot Test Passed" );
        else
            System.out.println( "!!!!vendingmachine.product.ProductSlot Test FAILED!!!!" );

    }

    private static boolean bankTest() {
        boolean passed = true;

        String name = "Balance Test 1";
        Bank bank = new Bank();
        bank.addCoin( Coin.Quarter );
        bank.addCoin( Coin.Dime );
        bank.addCoin( Coin.Nickel );
        bank.addCoin( Coin.Penny );

        Currency balance = bank.getBalance();
        if ( !balance.equals( new Currency( 41 ) ) )
            passed = false;
        System.out.println( name + " passed: " + passed );
        System.out.println( "\t" + name + " " + balance );

        name = "Balance Test 2";
        bank.addCoin( Coin.Quarter );
        bank.addCoin( Coin.Dime );
        bank.addCoin( Coin.Nickel );
        bank.addCoin( Coin.Penny );
        balance = bank.getBalance();
        if ( !balance.equals( new Currency( 82 ) ) )
            passed = false;
        System.out.println( name + " passed: " + passed );
        System.out.println( "\t" + name + " " + balance );
        return passed;
    }

    private static boolean productControllerTest() {
        boolean passed = true;
        ProductController pCon = new ProductController( 3, 3 );

        try {
            System.out.println( "Testing '00'" );
            ProductSlot slot = pCon.getSlot( "00" );
            if ( slot != null )
                System.out.println( "\tTest passed, got slot" );
        } catch ( InputException ie ) {
            System.out.println( ie.getMessage() );
            passed = false;
        }

        try {
            System.out.println( "Testing '22'" );
            ProductSlot slot = pCon.getSlot( "22" );
            if ( slot != null )
                System.out.println( "\tTest passed, got slot" );
        } catch ( InputException ie ) {
            System.out.println( ie.getMessage() );
            passed = false;
        }

        try {
            System.out.println( "Testing '33'" );
            pCon.getSlot( "33" );
            passed = false;
        } catch ( InputException ie ) {
            System.out.println( "\tTest passed, exception thrown" );
        }

        try {
            System.out.println( "Testing '000'" );
            pCon.getSlot( "000" );
            passed = false;
        } catch ( InputException ie ) {
            System.out.println( "\tTest passed, exception thrown" );
        }

        try {
            System.out.println( "Testing '0'" );
            pCon.getSlot( "0" );
            passed = false;
        } catch ( InputException ie ) {
            System.out.println( "\tTest passed, exception thrown" );
        }

        try {
            System.out.println( "Testing 'a0'" );
            pCon.getSlot( "a0" );
            passed = false;
        } catch ( InputException ie ) {
            System.out.println( "\tTest passed, exception thrown" );
        }

        return passed;
    }

    private static boolean productSlotTest() {
        boolean passed = true;

        ProductController pCon = new ProductController( 3, 3 );

        try {
            ProductSlot slot = pCon.getSlot( "00" );
            slot.setCurrentProduct( new Product( "Test vendingmachine.product.Product" ) );
            slot.setCost( new Currency( 105 ) );
//            slot.addProductStock( 1 );

            Product p = slot.dispenseProduct();
            if ( p != null ) {
                System.out.println( "\tTest Passed" );
                System.out.println( "\tvendingmachine.product.Product name: " + p.getName() );
                System.out.println( "\tvendingmachine.product.Product cost: " + slot.getCost() );
            } else {
                System.out.println( "\tTest Failed" );
                passed = false;
            }
        } catch ( Exception e ) {
            System.out.println( "\tTest Failed" );
            passed = false;
            System.out.println( e.getMessage() );
        }

        return passed;
    }
}
