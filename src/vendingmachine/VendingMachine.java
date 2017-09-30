package vendingmachine;

import vendingmachine.gui.Messages;
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
    private static final String BANK_OP = "0";
    private static final String STOCK_OP = "1";

    private static final String PASSCODE = "1234";

    enum SessionState{
        IDLE, CUSTOMER, OPERATOR, RESTOCKING, BANKING
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

    public void coinInsertMessage( Coin c ){
        switch( currentState ){
            case IDLE: case CUSTOMER:
                customerSessionCoin(c);
                break;

            case BANKING:
                bankingAddCoin(c);
                break;

            case OPERATOR: case RESTOCKING:
                rejectCoin( c );
                break;
        }
    }

    private void customerSessionCoin(Coin c){
        if( currentState == IDLE )
            setCurrentState( CUSTOMER );

        currencyController.addCoinToBalance( c );
        inputController.display( currencyController.getCurrentBalance().toString() );
    }

    private void bankingAddCoin( Coin c){
        currencyController.addCoinToBank( c );
        setCurrentState( BANKING );
    }


    private void rejectCoin( Coin c){
        currencyController.addCoinToBalance( c );
        currencyController.refundCurrentBalance();
    }

    public void finishMessage(){
        switch ( currentState ){
            case CUSTOMER:
                customerFinishSession();
                break;

            case OPERATOR:
                operatorFinishSession();
                break;

            case RESTOCKING: case BANKING:
                setCurrentState( OPERATOR );
                break;
        }
    }

    private void customerFinishSession(){
        Currency bal = currencyController.getCurrentBalance();
        Currency bill = orderList.getTotalCost();

        if( bill.lessThanOrEqual( bal ) ){
            currencyController.completeTransaction( orderList.completeOrder() );
            inputController.display( "Product order finished." );
            setCurrentState( CUSTOMER );
        }
        else{
            inputController.display( "Insufficient Change:" +
                    "\n\tHave: " + bal +
                    "\n\tNeed: " + bill);
        }
    }

    private void operatorFinishSession(){
        setCurrentState( IDLE );
    }



    public void cancelMessage(){
        switch( currentState ){
            case IDLE:
                setCurrentState( IDLE );
                break;

            case CUSTOMER:
                cancelCustomerSession();
                break;

            case OPERATOR:
                cancelOperatorSession();
                break;

            case RESTOCKING: case BANKING:
                setCurrentState( OPERATOR );
                break;
        }
    }

    private void cancelCustomerSession(){
        currencyController.refundCurrentBalance();

        if( orderList.getItemCount() > 0)
            orderList.cancelOrder();

        setCurrentState( IDLE );
    }

    private void cancelOperatorSession(){
        setCurrentState( IDLE );
    }

    public void inputMessage( String message ){
        switch( currentState ){
            case IDLE:
                if( message.length() == 4)
                    operatorLogin( message );
                else
                    attemptToAddProduct( message );
                break;

            case CUSTOMER:
                attemptToAddProduct( message );
                break;

            case OPERATOR:
                opCode( message );
                break;

            case BANKING:
                bankCode( message );
                break;

            case RESTOCKING:
                stockCode(message);
                break;
        }
    }

    private void attemptToAddProduct( String message ) {
        boolean success = true;
        try {
            ProductSlot slot = productController.getSlot( message );
            addProductToOrder( slot );
        }
        catch( Exception e ){
            success = false;
        }

        if( success ){
            if( currentState != CUSTOMER)
                setCurrentState( CUSTOMER );

            inputController.display( "Product added to order." );
        }
        else{
            inputController.display( "Invalid Input" );
        }
    }

    private void opCode( String code ){
        switch ( code ){
            case BANK_OP:
                setCurrentState( BANKING );
                break;
            case STOCK_OP:
                setCurrentState( RESTOCKING );
                break;
            default:
                inputController.display( Messages.OPERATOR_MSG );
        }
    }

    private void bankCode( String code ){
        switch ( code ){
            case "0":
                currencyController.getBank().clearBank();
                inputController.display( Messages.BANKING_MSG );
        }
        inputController.display( Messages.BANKING_MSG );
    }

    private void stockCode( String code ){

    }

    private boolean operatorLogin( String code ){
        boolean success = false;

        if( code.equals( PASSCODE )){
            setCurrentState( OPERATOR );
            success = true;
        }
        else
            inputController.clear();

        return success;
    }

    private void addProductToOrder( ProductSlot slot ){
        if( slot.getProductStock() <= 0){
            inputController.display( "Product Out of Stock" );
        }
        else{
            orderList.addProduct( slot );
        }
    }

    private void setCurrentState( SessionState state ){
        currentState = state;

        handleOperatorStates();
        handleCustomerStates();
    }

    private void handleCustomerStates(){
        switch ( currentState ) {
            case IDLE:
                inputController.clear();
                break;

            case CUSTOMER:
                inputController.clear();
                break;
        }
    }

    private void handleOperatorStates() {
        switch ( currentState ){
            case OPERATOR:
                inputController.display( Messages.OPERATOR_MSG );
                break;

            case BANKING:
                inputController.display(
                        currencyController.getBank() +
                        Messages.BANKING_MSG
                );
                break;

            case RESTOCKING:
                inputController.display(Messages.RESTOCKING_MSG);
                break;
        }
    }


}
