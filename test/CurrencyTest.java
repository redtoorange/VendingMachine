import org.junit.Assert;
import org.junit.Test;
import vendingmachine.money.Currency;

/**
 * Currency Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Sep 29, 2017</pre>
 */
public class CurrencyTest {
    /**
     * Method: totalCentValue()
     */
    @Test
    public void testTotalCentValue() throws Exception {
        Currency c1 = new Currency( 100 );
        Assert.assertTrue( "C1 Failed", c1.totalCentValue() == 100 );

        Currency c2 = new Currency( 300 );
        Assert.assertTrue( "C2 Failed", c2.totalCentValue() == 300 );

        Currency c3 = new Currency( 1, 50 );
        Assert.assertTrue( "C3 Failed", c3.totalCentValue() == 150 );

        Currency c4 = new Currency( 2, 150 );
        Assert.assertTrue( "C4 Failed", c4.totalCentValue() == 350 );
    }

    /**
     * Method: add(Currency other)
     */
    @Test
    public void testAdd() throws Exception {
        Currency c1 = new Currency( 100 );
        c1.add( new Currency( 100 ) );
        Assert.assertTrue( "C1 Failed", c1.totalCentValue() == 200 );

        Currency c2 = new Currency( 300 );
        c2.add( new Currency( 1, 1 ) );
        Assert.assertTrue( "C2 Failed", c2.totalCentValue() == 401 );

        Currency c3 = new Currency( 1, 50 );
        c3.add( new Currency( 400 ) );
        Assert.assertTrue( "C3 Failed", c3.totalCentValue() == 550 );

        Currency c4 = new Currency( 2, 150 );
        c4.add( new Currency( 1 ) );
        Assert.assertTrue( "C4 Failed", c4.totalCentValue() == 351 );
    }

    /**
     * Method: sub(Currency other)
     */
    @Test
    public void testSub() throws Exception {
        Currency c1 = new Currency( 100 );
        c1.sub( new Currency( 100 ) );
        Assert.assertTrue( "C1 Failed", c1.totalCentValue() == 0 );

        Currency c2 = new Currency( 300 );
        c2.sub( new Currency( 1, 1 ) );
        Assert.assertTrue( "C2 Failed", c2.totalCentValue() == 199 );

        Currency c3 = new Currency( 1, 50 );
        c3.sub( new Currency( 400 ) );
        Assert.assertTrue( "C3 Failed " + c3.totalCentValue(), c3.totalCentValue() == -250 );

        Currency c4 = new Currency( 2, 150 );
        c4.sub( new Currency( 1 ) );
        Assert.assertTrue( "C4 Failed", c4.totalCentValue() == 349 );
    }

    /**
     * Method: addCents(int centsToAdd)
     */
    @Test
    public void testAddCents() throws Exception {
        Currency c1 = new Currency( 100 );
        c1.addCents( 100 );
        Assert.assertTrue( "C1 Failed", c1.totalCentValue() == 200 );

        Currency c2 = new Currency( 300 );
        c2.addCents( 101 );
        Assert.assertTrue( "C2 Failed", c2.totalCentValue() == 401 );

        Currency c3 = new Currency( 1, 50 );
        c3.addCents( 400 );
        Assert.assertTrue( "C3 Failed", c3.totalCentValue() == 550 );

        Currency c4 = new Currency( 2, 150 );
        c4.addCents( 1 );
        Assert.assertTrue( "C4 Failed", c4.totalCentValue() == 351 );
    }

    /**
     * Method: subCents(int centsToSub)
     */
    @Test
    public void testSubCents() throws Exception {
        Currency c1 = new Currency( 100 );
        c1.subCents( 100 );
        Assert.assertTrue( "C1 Failed", c1.totalCentValue() == 0 );

        Currency c2 = new Currency( 300 );
        c2.subCents( 101 );
        Assert.assertTrue( "C2 Failed", c2.totalCentValue() == 199 );

        Currency c3 = new Currency( 1, 50 );
        c3.subCents( 400 );
        Assert.assertTrue( "C3 Failed " + c3.totalCentValue(), c3.totalCentValue() == -250 );

        Currency c4 = new Currency( 2, 150 );
        c4.subCents( 1 );
        Assert.assertTrue( "C4 Failed", c4.totalCentValue() == 349 );
    }

    /**
     * Method: lessThanOrEqual(Currency other)
     */
    @Test
    public void testLessThanOrEqual() throws Exception {
        Currency c1 = new Currency( 100 );
        Currency c2 = new Currency( 1, 0 );
        Currency c3 = new Currency( 5 );

        Assert.assertTrue( "C1 <= C2", c1.lessThanOrEqual( c2 ) );
        Assert.assertTrue( "C2 <= C1", c2.lessThanOrEqual( c1 ) );
        Assert.assertTrue( "C2 <= C2", c2.lessThanOrEqual( c2 ) );

        Assert.assertFalse( "C1 > C3", c1.lessThanOrEqual( c3 ) );
        Assert.assertFalse( "C2 > C3", c2.lessThanOrEqual( c3 ) );

        Assert.assertTrue( "C3 <= C1", c3.lessThanOrEqual( c1 ) );
        Assert.assertTrue( "C3 <= C2", c3.lessThanOrEqual( c2 ) );
    }

    /**
     * Method: equals(Currency other)
     */
    @Test
    public void testEquals() throws Exception {
        Currency c1 = new Currency( 100 );
        Currency c2 = new Currency( 1, 0 );
        Currency c3 = new Currency( 5 );

        Assert.assertTrue( "C1 != C2", c1.equals( c2 ) );
        Assert.assertTrue( "C2 != C1", c2.equals( c1 ) );
        Assert.assertFalse( "C1 == C3", c1.equals( c3 ) );
        Assert.assertFalse( "C3 == C2", c3.equals( c2 ) );
        Assert.assertTrue( "C3 != C3", c3.equals( c3 ) );
    }

} 
