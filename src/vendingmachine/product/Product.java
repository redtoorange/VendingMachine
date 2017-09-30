package vendingmachine.product;

/**
 * Product.java - Model representation of a physical {@link Product} you might buy from a {@link vendingmachine.VendingMachine}.
 * The only value that the {@link Product} itself contains is a name.  The price of a {@link Product} is associated with
 * the {@link ProductSlot}.
 *
 * @author Andrew McGuiness
 * @version 9/29/2017
 */
public class Product {
    private final String name;

    /**
     * Create a new {@link Product} with the given name.
     *
     * @param name {@link String} name of the {@link Product}.
     */
    public Product( String name ) {
        this.name = name;
    }

    /**
     * Get the {@link String} name of the {@link Product}.
     *
     * @return {@link String} name of {@link Product}
     */
    public String getName() {
        return name;
    }

    /**
     * Clone the {@link Product}, creates a new instance of a {@link Product} that has the same internal state as
     * this one.
     *
     * @return {@link Product}, exact copy of the current instance.
     */
    public Product clone() {
        return new Product( name );
    }
}
