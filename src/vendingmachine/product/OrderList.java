package vendingmachine.product;

import vendingmachine.money.Currency;

import java.util.Stack;

/**
 * ${FILE_NAME}.java - Description
 *
 * @author
 * @version 25/Sep/2017
 */
public class OrderList {
    private int itemCount = 0;
    private Stack<ProductSlot> productSlots = new Stack<ProductSlot>();
    private Currency totalCost = new Currency( 0 );

    public int getItemCount(){
        return itemCount;
    }

    public void addProduct( ProductSlot slot ){
        itemCount++;
        productSlots.push( slot );
        totalCost.add( slot.getCost() );

        slot.decrementStock();
    }

    public void cancelOrder(){
        for( ProductSlot ps : productSlots)
            ps.incrementStock();

        totalCost = new Currency( 0 );
        productSlots.clear();
        itemCount = 0;
    }

    public Currency getTotalCost(){
        return totalCost;
    }

    public Currency completeOrder(){
        Currency temp = totalCost;

        for( ProductSlot ps : productSlots)
            ps.dispenseProduct();

        totalCost = new Currency( 0 );
        productSlots.clear();
        itemCount = 0;

        return temp;
    }
}
