package vendingmachine;

import vendingmachine.io.InputController;
import vendingmachine.money.Coin;
import vendingmachine.money.Currency;
import vendingmachine.money.CurrencyController;
import vendingmachine.product.OrderList;
import vendingmachine.product.ProductController;
import vendingmachine.product.ProductSlot;

import static vendingmachine.VendingMachine.SessionState.*;

/**
 * ${FILE_NAME}.java - Description
 *
 * @author
 * @version 15/Sep/2017
 */
public class VendingMachine {
    private static final String PASSCODE = "1234";

    enum SessionState{
        IDLE, CUSTOMER, OPERATOR
    }
    private SessionState currentState = IDLE;

    private OrderList orderList;
    private ProductController productController;
    private InputController inputController;
    private CurrencyController currencyController;

    public VendingMachine() {
        productController = new ProductController( 3, 3 );

        inputController = new InputController( this );
        currencyController = new CurrencyController();
        orderList = new OrderList();
    }

    public ProductController getProductController() {
        return productController;
    }

    public InputController getInputController() {
        return inputController;
    }

    public CurrencyController getCurrencyController() {
        return currencyController;
    }

    public void coinInserted( Coin c ){
        if( currentState == IDLE || currentState == CUSTOMER){
            if( currentState == IDLE )
                currentState = CUSTOMER;

            currencyController.addCoinToBalance( c );
            inputController.displayText( currencyController.getCurrentBalance().toString() );
        }
    }

    public void finishSession(){
        if( currentState == CUSTOMER ){

            Currency bal = currencyController.getCurrentBalance();
            Currency bill = orderList.getTotalCost();

            if( bill.lessThanOrEqual( bal ) ){
                currencyController.completeTransaction( orderList.completeOrder() );
                inputController.displayText( "Product order finished." );
                currentState = IDLE;
            }
            else{
                inputController.displayText( "Insufficient Change" );
            }
        }
    }

    public void cancelSession(){
        if( currentState == CUSTOMER ){
            currencyController.refundCurrentBalance();

            if( orderList.getItemCount() > 0){
                orderList.cancelOrder();
            }
        }

        currentState = IDLE;
    }

    public boolean productCode( String code ){
        boolean success = true;

        try {
            ProductSlot slot = productController.getSlot( code );
            addProductToOrder( slot );
        }
        catch( Exception e ){
            success = false;
        }

        if( success ){
            currentState = CUSTOMER;
        }

        return success;
    }

    public boolean opCode( String code ){
        boolean success = false;

        if( currentState == OPERATOR ){
            //process opcode
            success = true;
        }

        return success;
    }

    public boolean operatorLogin( String code ){
        boolean success = false;

        if( code.equals( PASSCODE )){
            currentState = OPERATOR;
            success = true;
        }

        return success;
    }

    private void addProductToOrder( ProductSlot slot ){
        if( slot.getProductStock() <= 0){
            inputController.displayText( "Product Out of Stock" );
        }
        else{
            orderList.addProduct( slot );
        }
    }
}
