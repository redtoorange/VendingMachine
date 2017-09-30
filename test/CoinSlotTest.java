import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import vendingmachine.VendingMachine;
import vendingmachine.io.CoinSlot;
import vendingmachine.money.Coin;
import vendingmachine.money.Currency;

/**
 * CoinSlot Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Sep 29, 2017</pre>
 */
public class CoinSlotTest {


    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: insertCoin(Coin c)
     */
    @Test
    public void testInsertCoint() throws Exception {
        VendingMachine vm = new VendingMachine();
        CoinSlot cs = vm.getInputController().getCoinSlot();
        cs.insertCoin( Coin.Penny );
        Currency bal = vm.getCurrencyController().getCurrentBalance();
        Assert.assertTrue( "Penny Failed to be added to Balance.", bal.totalCentValue() == 1 );

        cs.insertCoin( Coin.Nickel );
        bal = vm.getCurrencyController().getCurrentBalance();
        Assert.assertTrue( "Nickel Failed to be added to Balance.", bal.totalCentValue() == 6 );

        cs.insertCoin( Coin.Dime );
        bal = vm.getCurrencyController().getCurrentBalance();
        Assert.assertTrue( "Dime Failed to be added to Balance.", bal.totalCentValue() == 16 );

        cs.insertCoin( Coin.Quarter );
        bal = vm.getCurrencyController().getCurrentBalance();
        Assert.assertTrue( "Quarter Failed to be added to Balance.", bal.totalCentValue() == 41 );
    }
} 
