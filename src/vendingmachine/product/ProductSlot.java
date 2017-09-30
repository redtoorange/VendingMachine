package vendingmachine.product;

import vendingmachine.money.Currency;

/**
 * ProductSlot.java - Description
 *
 * @author Andrew McGuiness
 * @version 9/29/2017
 */
public class ProductSlot {
    private Currency cost;
    private Product currentProduct;
    private int productStock;

    public ProductSlot( Currency cost, Product currentProduct, int productStock ) {
        this.cost = cost;
        this.currentProduct = currentProduct;
        this.productStock = productStock;
    }

    public ProductSlot() {
        this.cost = null;
        this.currentProduct = null;
        this.productStock = 0;
    }

    public Currency getCost() {
        return cost;
    }

    public void setCost( Currency cost ) {
        this.cost = cost;
    }

    public Product getCurrentProduct() {
        return currentProduct;
    }

    public void setCurrentProduct( Product currentProduct ) {
        this.currentProduct = currentProduct;
    }

    public int getProductStock() {
        return productStock;
    }

    public void setProductStock( int productStock ) {
        this.productStock = productStock;
    }

    public void incrementStock(){
        productStock++;
    }

    public void decrementStock(){
        productStock--;
    }

    public void addProductStock( int amount ) throws ProductLevelException {
        if ( currentProduct == null )
            throw new ProductLevelException( "Not product set in product slot" );
        else
            productStock += amount;
    }

    public Product dispenseProduct() {
        System.out.println( "Dispensed " + currentProduct.getName() );

        return currentProduct.clone();
    }
}
