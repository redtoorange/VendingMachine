package vendingmachine;

import vendingmachine.gui.Messages;
import vendingmachine.io.InputController;
import vendingmachine.money.Coin;
import vendingmachine.money.Currency;
import vendingmachine.money.CurrencyController;
import vendingmachine.product.OrderList;
import vendingmachine.product.ProductController;
import vendingmachine.product.ProductSlot;

import static vendingmachine.VendingMachine.MachineState.*;

/**
 * VendingMachine.java - Description
 *
 * @author Andrew McGuiness
 * @version 9/29/2017
 */
public class VendingMachine {
    private static final String BANK_OP = "0";
    private static final String STOCK_OP = "1";
    private static final String BANK_EMPTY_CODE = "0";

    private static final String PASSCODE = "1234";
    private MachineState currentState = IDLE;
    private OrderList orderList;
    private ProductController productController;
    private InputController inputController;
    private CurrencyController currencyController;

    /**
     * Create a new standard {@link VendingMachine}.  This creates the {@link VendingMachine} that goes with our current
     * {@link vendingmachine.gui.ViewController}, but it could be easily modified.
     */
    public VendingMachine() {
        productController = new ProductController( 3, 3 );

        inputController = new InputController( this );
        currencyController = new CurrencyController();
        orderList = new OrderList();
    }

    /**
     * Get the {@link VendingMachine}'s {@link ProductController}
     *
     * @return current {@link ProductController}
     */
    public ProductController getProductController() {
        return productController;
    }

    /**
     * Get the {@link VendingMachine}'s {@link InputController}
     *
     * @return current {@link InputController}
     */
    public InputController getInputController() {
        return inputController;
    }

    /**
     * Get the {@link VendingMachine}'s {@link CurrencyController}
     *
     * @return current {@link CurrencyController}
     */
    public CurrencyController getCurrencyController() {
        return currencyController;
    }

    /**
     * Handle a {@link Coin} being inserted based on the {@link VendingMachine}'s current state.
     *
     * @param insertedCoin {@link Coin} that was inserted.
     */
    public void coinInsertMessage( Coin insertedCoin ) {
        switch ( currentState ) {
            case IDLE:
            case CUSTOMER:
                customerSessionCoin( insertedCoin );
                break;

            case BANKING:
                bankingAddCoin( insertedCoin );
                break;

            case OPERATOR:
            case RESTOCKING:
                rejectCoin( insertedCoin );
                break;
        }
    }

    private void customerSessionCoin( Coin insertedCoin ) {
        if ( currentState == IDLE )
            setCurrentState( CUSTOMER );

        currencyController.addCoinToBalance( insertedCoin );
        inputController.display( currencyController.getCurrentBalance().toString() );
    }

    private void bankingAddCoin( Coin insertedCoin ) {
        currencyController.addCoinToBank( insertedCoin );
        setCurrentState( BANKING );
    }

    private void rejectCoin( Coin insertedCoin ) {
        currencyController.addCoinToBalance( insertedCoin );
        currencyController.refundCurrentBalance();
    }

    /**
     * Handle the "Finish" {@link javafx.scene.control.Button} being pressed based on the {@link VendingMachine}'s
     * current state.
     */
    public void finishMessage() {
        switch ( currentState ) {
            case CUSTOMER:
                customerFinishSession();
                break;

            case OPERATOR:
                operatorFinishSession();
                break;

            case RESTOCKING:
            case BANKING:
                setCurrentState( OPERATOR );
                break;
        }
    }

    private void customerFinishSession() {
        Currency bal = currencyController.getCurrentBalance();
        Currency bill = orderList.getTotalCost();

        if ( bill.lessThanOrEqual( bal ) ) {
            currencyController.completeTransaction( orderList.completeOrder() );
            inputController.display( Messages.ORDER_FINISHED );
            setCurrentState( CUSTOMER );
        } else {
            inputController.display( "Insufficient Change:" +
                    "\n\tHave: " + bal +
                    "\n\tNeed: " + bill );
        }
    }

    private void operatorFinishSession() {
        setCurrentState( IDLE );
    }

    /**
     * Handle the "Cancel" {@link javafx.scene.control.Button} being pressed based on the {@link VendingMachine}'s current state.
     */
    public void cancelMessage() {
        switch ( currentState ) {
            case IDLE:
                setCurrentState( IDLE );
                break;

            case CUSTOMER:
                cancelCustomerSession();
                break;

            case OPERATOR:
                cancelOperatorSession();
                break;

            case RESTOCKING:
            case BANKING:
                setCurrentState( OPERATOR );
                break;
        }
    }

    private void cancelCustomerSession() {
        currencyController.refundCurrentBalance();

        if ( orderList.getItemCount() > 0 )
            orderList.cancelOrder();

        setCurrentState( IDLE );
    }

    private void cancelOperatorSession() {
        setCurrentState( IDLE );
    }

    /**
     * Handle the all other messages based on the {@link VendingMachine}'s current state.
     *
     * @param message {@link String} message to process.
     */
    public void inputMessage( String message ) {
        switch ( currentState ) {
            case IDLE:
                if ( message.length() == 4 )
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
                stockCode( message );
                break;
        }
    }

    private void attemptToAddProduct( String message ) {
        boolean success = true;
        try {
            ProductSlot slot = productController.getSlot( message );
            addProductToOrder( slot );
        } catch ( Exception e ) {
            success = false;
        }

        if ( success ) {
            if ( currentState != CUSTOMER )
                setCurrentState( CUSTOMER );

            inputController.display( Messages.PRODUCT_ADDED );
        } else {
            inputController.display( Messages.BAD_INPUT );
        }
    }

    private void opCode( String code ) {
        switch ( code ) {
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

    private void bankCode( String code ) {
        switch ( code ) {
            case BANK_EMPTY_CODE:
                currencyController.getBank().clearBank();
                inputController.display( Messages.BANKING_MSG );
        }
        inputController.display( Messages.BANKING_MSG );
    }

    private void stockCode( String code ) {

    }

    private void operatorLogin( String code ) {
        if ( code.equals( PASSCODE ) )
            setCurrentState( OPERATOR );
        else
            inputController.clear();
    }

    private void addProductToOrder( ProductSlot slot ) {
        if ( slot.getProductStock() <= 0 ) {
            inputController.display( Messages.OUT_OF_STOCK );
        } else {
            orderList.addProduct( slot );
        }
    }

    private void setCurrentState( MachineState state ) {
        currentState = state;

        handleOperatorStates();
        handleCustomerStates();
    }

    private void handleCustomerStates() {
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
        switch ( currentState ) {
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
                inputController.display( Messages.RESTOCKING_MSG );
                break;
        }
    }

    /** Possible Machine States */
    protected enum MachineState {
        IDLE, CUSTOMER, OPERATOR, RESTOCKING, BANKING
    }
}
