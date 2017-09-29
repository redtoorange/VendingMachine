package vendingmachine.io;

import vendingmachine.VendingMachine;
import vendingmachine.money.Coin;

/**
 * ${FILE_NAME}.java - Description
 *
 * @author
 * @version 15/Sep/2017
 */
public class InputController {
    private VendingMachine vendingMachine;

    private KeyPad keyPad;
    private CoinSlot coinSlot;
    private OutputDisplay outputDisplay;

    public InputController( VendingMachine vendingMachine ) {
        this.vendingMachine = vendingMachine;

        keyPad = new KeyPad( this );
        coinSlot = new CoinSlot( this );
        outputDisplay = new OutputDisplay();
    }

    public void insertCoin( Coin coin ) {
        vendingMachine.coinInserted( coin );
    }

    public void processInput( String input ) {
        boolean validCode = false;

        if( input.length() == 4){
            validCode = vendingMachine.operatorLogin( input );

            if( validCode )
                displayText( "Operator Logged In" );
        }
        else if( input.length() == 2){
            validCode = vendingMachine.productCode( input );
        }
        else if( input.length() == 1){
            validCode = vendingMachine.opCode( input );

            if( validCode )
                displayText( "Opcode processed" );
        }

        if( !validCode ){
            displayText( "Invalid Input" + input );
        }
    }

    public void finishSession(){
        vendingMachine.finishSession();
    }

    public void cancelSession() {
        displayText( "Session Cancelled!" );

        vendingMachine.cancelSession();
    }

    public KeyPad getKeyPad() {
        return keyPad;
    }

    public CoinSlot getCoinSlot() {
        return coinSlot;
    }

    public void displayText( String text ){
        outputDisplay.displayText( text );
    }

    public OutputDisplay getOutputDisplay() {
        return outputDisplay;
    }
}
