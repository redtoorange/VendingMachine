package vendingmachine.product;

import vendingmachine.money.Currency;

/**
 * ProductSlot.java - A {@link ProductSlot} contains a single {@link Product} type, a {@link Currency} cost and a stock
 * level.  When the Customer adds a {@link Product} to his {@link OrderList}, really they are adding the
 * {@link ProductSlot} to a queue inside the {@link OrderList}.  This queue will be used to dispense
 * {@link Product}s when the {@link OrderList} is completed.
 * <p>
 * When the {@link ProductSlot} dispenses a {@link Product}, the local {@link Product} instance will clone itself and
 * produce a new {@link Product} object.
 *
 * @author Andrew McGuiness
 * @version 9/29/2017
 */
public class ProductSlot {
    private Currency cost;
    private Product currentProduct;
    private int productStock;

// --Commented out by Inspection START (9/30/2017 12:09 AM):
//    /**
//     * Create a new {@link ProductSlot} with the supplied price, {@link Product} and stock level.
//     *
//     * @param cost           {@link Currency} cost of the {@link Product} in this {@link ProductSlot}
//     * @param currentProduct {@link Product} that this {@link ProductSlot} will dispense
//     * @param productStock   stock level of this {@link ProductSlot}.
//     */
//    public ProductSlot( Currency cost, Product currentProduct, int productStock ) {
//        this.cost = cost;
//        this.currentProduct = currentProduct;
//        this.productStock = productStock;
//    }
// --Commented out by Inspection STOP (9/30/2017 12:09 AM)

    /**
     * Create a new empty {@link ProductSlot}.
     */
    public ProductSlot() {
        this.cost = null;
        this.currentProduct = null;
        this.productStock = 0;
    }

    /**
     * Get the {@link Currency} cost of the {@link Product} in this {@link ProductSlot}.
     *
     * @return {@link Currency} cost of {@link Product}.
     */
    public Currency getCost() {
        return cost;
    }

    /**
     * Set the {@link Currency} cost of the {@link Product} in this {@link ProductSlot}.
     *
     * @param cost new {@link Currency} cost of the {@link Product}.
     */
    public void setCost( Currency cost ) {
        this.cost = cost;
    }

    /**
     * Change the {@link Product} that this {@link ProductSlot} should dispense.
     *
     * @param currentProduct new {@link Product} to hold.
     */
    public void setCurrentProduct( Product currentProduct ) {
        this.currentProduct = currentProduct;
    }

    /**
     * Get the current stock level of this {@link ProductSlot}.
     *
     * @return number of {@link Product} in this slot.
     */
    public int getProductStock() {
        return productStock;
    }

    /**
     * Set the current stock level of this {@link ProductSlot}.
     *
     * @param productStock number to set the stock level to.
     */
    protected void setProductStock( int productStock ) {
        this.productStock = productStock;
    }

    /**
     * Increase the stock level of this {@link ProductSlot}.
     */
    protected void incrementStock() {
        productStock++;
    }

    /**
     * Decrease the stock level of this {@link ProductSlot}.
     */
    protected void decrementStock() {
        productStock--;
    }

    /**
     * Dispense a clone of the {@link Product} contained in this {@link ProductSlot}.
     *
     * @return {@link Product} clone.
     */
    public Product dispenseProduct() {
        System.out.println( "Dispensed " + currentProduct.getName() );

        return currentProduct.clone();
    }
}
