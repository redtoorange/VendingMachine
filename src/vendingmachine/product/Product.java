package vendingmachine.product;

/**
 * Product.java - Description
 *
 * @author Andrew McGuiness
 * @version 9/29/2017
 */
public class Product {
    private String name;

    public Product( String name ) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Product clone() {
        return new Product( name );

    }
}
