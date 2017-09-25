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
    private Stack<ProductSlot> productSlots = new Stack<ProductSlot>();
    private Currency totalCost = new Currency( 0 );

    public void addProduct( ProductSlot slot ){
        productSlots.push( slot );
        totalCost.add( slot.getCost() );
    }

    public Currency cancelOrder(){
        Currency temp = totalCost;
        totalCost = new Currency( 0 );
        productSlots.clear();
        return temp;
    }
}
