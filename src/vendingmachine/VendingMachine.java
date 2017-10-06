package vendingmachine;

import vendingmachine.gui.Messages;
import vendingmachine.io.InputController;
import vendingmachine.io.InputException;
import vendingmachine.money.Coin;
import vendingmachine.money.Currency;
import vendingmachine.money.CurrencyController;
import vendingmachine.product.OrderList;
import vendingmachine.product.ProductController;
import vendingmachine.product.ProductLevelException;
import vendingmachine.product.ProductSlot;

import static vendingmachine.VendingMachine.MachineState.*;

/**
 * VendingMachine.java - Description
 *
 * @author Andrew McGuiness
 * @version 9/29/2017
 */
public class VendingMachine {
    //Named Constants to help parse Input
    private static final String BANK_OP = "0";
    private static final String STOCK_OP = "1";
    private static final String BANK_EMPTY_CODE = "0";
    private static final String PASSCODE = "1234";

    private MachineState currentState;      //  Used to direct messages based on the VendingMachine's state
    private OrderList orderList;            //  Customer's current list of selected products
    private ProductSlot currentSlot;        //  Cached reference to the most recently used slot

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

        setCurrentState( IDLE );
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

            default:
                rejectCoin( insertedCoin );
        }
    }

    //Customer inserted a Coin
    private void customerSessionCoin( Coin insertedCoin ) {
        if ( currentState == IDLE )
            setCurrentState( CUSTOMER );

        currencyController.addCoinToBalance( insertedCoin );
        inputController.display( currencyController.getCurrentBalance().toString() );
    }

    //Operator inserted a Coin while in Banking Mode
    private void bankingAddCoin( Coin insertedCoin ) {
        currencyController.addCoinToBank( insertedCoin );
        setCurrentState( BANKING );
    }

    //Any other time a Coin is inserted by the Operator
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

            case RESTOCK:
            case BANKING:
            case RESTOCKING:
                setCurrentState( OPERATOR );
                break;
        }
    }

    //Customer clicked "Finish", try to charge the balance or notify of insufficient funds.
    private void customerFinishSession() {
        Currency bal = currencyController.getCurrentBalance();
        Currency bill = orderList.getTotalCost();

        if ( bill.lessThanOrEqual( bal ) ) {
            currencyController.completeTransaction( orderList.completeOrder() );
            inputController.display( Messages.ORDER_FINISHED );
            setCurrentState( IDLE );
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

            case RESTOCK:
            case BANKING:
                setCurrentState( OPERATOR );
                break;

            case RESTOCKING:
                setCurrentState( RESTOCK );
                break;
        }
    }

    //Refund the customer's money and clear out the order
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

            case RESTOCK:
                stockCode( message );
                break;

            case RESTOCKING:
                restocking( message );
                break;
        }
    }


    //process input in the OPERATOR state
    private void opCode( String code ) {
        switch ( code ) {
            case BANK_OP:
                setCurrentState( BANKING );
                break;
            case STOCK_OP:
                setCurrentState( RESTOCK );
                break;
            default:
                inputController.display( Messages.BAD_INPUT + "\n" + Messages.OPERATOR_MSG );
        }
    }

    //process input in the BANKING state
    private void bankCode( String code ) {
        switch ( code ) {
            case BANK_EMPTY_CODE:
                currencyController.getBank().clearBank();
                setCurrentState( BANKING );
                break;

            default:
                inputController.display( Messages.BAD_INPUT + "\n" + Messages.BANKING_MSG );
        }
    }

    //process input in the RESTOCK state
    private void stockCode( String code ) {
        try {
            currentSlot = productController.getSlot( code );
            setCurrentState( RESTOCKING );

        } catch ( InputException e ) {
            inputController.display( Messages.BAD_INPUT + "\n" + Messages.RESTOCK_MSG );
        }
    }

    //process input in the RESTOCKING state
    private void restocking( String code ) {
        try {
            int level = Integer.parseInt( code );
            currentSlot.setProductStock( level );
            setCurrentState( RESTOCKING );
        } catch ( ProductLevelException ple ) {
            inputController.display( Messages.ILLEGAL_STOCK + "\n" + Messages.RESTOCKING_MSG );
        } catch ( NumberFormatException nfe ) {
            inputController.display( Messages.BAD_INPUT + "\n" + Messages.RESTOCKING_MSG );
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    private void operatorLogin( String code ) {
        if ( code.equals( PASSCODE ) )
            setCurrentState( OPERATOR );
        else
            inputController.clear();
    }

    //Try to add a product to the Customer's order
    private void attemptToAddProduct( String message ) {
        try {
            currentSlot = productController.getSlot( message );
            boolean added = addProductToOrder( currentSlot );

            if ( added && currentState != CUSTOMER )
                setCurrentState( CUSTOMER );

            inputController.display( Messages.PRODUCT_ADDED );
        } catch ( Exception e ) {
            inputController.display( Messages.BAD_INPUT );
        }
    }

    //Add the product
    private boolean addProductToOrder( ProductSlot slot ) {
        boolean added = false;

        if ( slot.getProductStock() <= 0 ) {
            inputController.display( Messages.OUT_OF_STOCK );
        } else {
            added = true;
            orderList.addProduct( slot );
        }

        return added;
    }

    //Handle branching logic for state changes.  This will effectively prime the OutputDisplay and allow the machine to
    //  track state
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

            case RESTOCK:
                inputController.display( Messages.RESTOCK_MSG );
                break;

            case RESTOCKING:
                inputController.display( "Current Stock: " + currentSlot.getProductStock() + "\n" + Messages.RESTOCKING_MSG );
                break;
        }
    }

    /** Possible Machine States */
    protected enum MachineState {
        IDLE, CUSTOMER, OPERATOR, RESTOCK, RESTOCKING, BANKING
    }
}
