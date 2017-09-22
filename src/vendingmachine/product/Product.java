package vendingmachine.product;

/**
 * vendingmachine.product.Product.java - Description
 *
 * @author Andrew McGuiness
 * @version 9/12/2017
 */
public class Product {
    private String name;

    public Product( String name ) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    protected Product clone() {
        return new Product( name );
    }
}
