package vendingmachine.io;

import vendingmachine.VendingMachine;
import vendingmachine.money.Coin;

/**
 * InputController.java - Description
 *
 * @author  Andrew McGuiness
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
        vendingMachine.coinInsertMessage( coin );
    }

    public void processInput( String input ) {
        if( input.length() > 0)
            vendingMachine.inputMessage( input );
    }

    public void finishSession(){
        vendingMachine.finishMessage();
    }

    public void cancelSession() {
        vendingMachine.cancelMessage();
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

    public void clearDisplay(){
        outputDisplay.displayText( "" );
    }

    public OutputDisplay getOutputDisplay() {
        return outputDisplay;
    }
}
