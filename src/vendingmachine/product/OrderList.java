package vendingmachine.product;

import vendingmachine.money.Currency;

import java.util.Stack;

/**
 * OrderList.java - Represents a "Shopping List" of what the Customer wants.  When the Customer orders a
 * {@link Product}, the system will cache a reference to the {@link ProductSlot}.  The {@link ProductSlot}s won't
 * dispense their {@link Product} until the {@link OrderList} is completed, but the stock in each {@link ProductSlot}
 * is updated as they are added to the {@link OrderList} to prevent over ordering.
 *
 * @author Andrew McGuiness
 * @version 9/29/2017
 */
public class OrderList {
    private int itemCount;
    private Stack< ProductSlot > productSlots;
    private Currency totalCost;

    /** Create a new {@link OrderList} */
    public OrderList() {
        itemCount = 0;
        productSlots = new Stack< ProductSlot >();
        totalCost = new Currency( 0 );
    }

    /**
     * Get the number of items in this {@link OrderList}.
     *
     * @return count of items on {@link OrderList}.
     */
    public int getItemCount() {
        return itemCount;
    }

    /**
     * Add a {@link ProductSlot} to the {@link OrderList}.
     *
     * @param slot {@link ProductSlot} to pull the {@link Product} from.
     */
    public void addProduct( ProductSlot slot ) {
        itemCount++;
        productSlots.push( slot );
        totalCost.add( slot.getCost() );

        slot.decrementStock();
    }

    /**
     * Cancel the {@link OrderList}, this removes the {@link ProductSlot}s from the {@link OrderList} and zeroes out
     * the cost.
     */
    public void cancelOrder() {
        for ( ProductSlot ps : productSlots )
            ps.incrementStock();

        totalCost = new Currency( 0 );
        productSlots.clear();
        itemCount = 0;
    }

    /**
     * Get the total {@link Currency} cost of all the {@link Product}s on the {@link OrderList}.
     *
     * @return Current total cost of the {@link OrderList}.
     */
    public Currency getTotalCost() {
        return totalCost;
    }

    /**
     * Instruct all of the {@link ProductSlot}s to dispense their {@link Product}s.
     *
     * @return {@link Currency} cost of the order, which should be deducted from the Customer's Balance.
     */
    public Currency completeOrder() {
        Currency temp = totalCost;

        for ( ProductSlot ps : productSlots )
            ps.dispenseProduct();

        totalCost = new Currency( 0 );
        productSlots.clear();
        itemCount = 0;

        return temp;
    }
}
