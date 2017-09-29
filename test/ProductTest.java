import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import vendingmachine.product.Product;

/**
 * Product Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Sep 27, 2017</pre>
 */
public class ProductTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getName()
     */
    @Test
    public void testGetName() throws Exception {
        String n1 = "Hello";
        Product p1 = new Product( n1 );
        Assert.assertTrue( "Get name failed!", p1.getName().equals( n1 ) );
    }

    /**
     * Method: clone()
     */
    @Test
    public void testClone() throws Exception {
        Product p1 = new Product( "Hello" );
        Product p2 = p1.clone();
        Assert.assertFalse( "Objects point to same memory!", p1 == p2 );
        Assert.assertTrue( "Names do not match!", p1.getName().equals( p2.getName() ) );
    }


} 
