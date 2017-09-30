package vendingmachine.io;

import vendingmachine.VendingMachine;
import vendingmachine.money.Coin;

/**
 * InputController.java - The {@link InputController} provides an interface between the IO Controls and the
 * {@link VendingMachine} itself.  This layer of abstraction allows the actual implementation of the IO controls to
 * be based on what the {@link vendingmachine.gui.ViewController} needs.  The IO Controls are used as a Glue Layer to
 * bind to GUI Controls.  Then those IO Controls are translated with the {@link InputController} to interface with the
 * {@link VendingMachine}'s public interface.
 *
 * @author Andrew McGuiness
 * @version 9/29/2017
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

    /**
     * Notify the {@link VendingMachine} that a {@link Coin} was inserted.
     *
     * @param coin {@link Coin} that was inserted
     */
    protected void insertCoin( Coin coin ) {
        vendingMachine.coinInsertMessage( coin );
    }

    /**
     * Deliver some other {@link String} message to the {@link VendingMachine}, let it translate it based on the
     * {@link VendingMachine}'s current state.
     *
     * @param input {@link String} to deliver
     */
    protected void processInput( String input ) {
        if ( input.length() > 0 )
            vendingMachine.inputMessage( input );
    }

    /**
     * Deliver a "Finish Session" message to the {@link VendingMachine}
     */
    protected void finishSession() {
        vendingMachine.finishMessage();
    }

    /**
     * Deliver a "Cancel Session" message to the {@link VendingMachine}
     */
    protected void cancelSession() {
        vendingMachine.cancelMessage();
    }


    /**
     * Get the {@link VendingMachine}'s {@link KeyPad}
     *
     * @return current instance of {@link KeyPad}
     */
    public KeyPad getKeyPad() {
        return keyPad;
    }

    /**
     * Get the {@link VendingMachine}'s {@link CoinSlot}
     *
     * @return current instance of {@link CoinSlot}
     */
    public CoinSlot getCoinSlot() {
        return coinSlot;
    }

    /**
     * Write a {@link String} to the {@link OutputDisplay}.
     *
     * @param text {@link String} to write to the {@link OutputDisplay}.
     */
    public void display( String text ) {
        outputDisplay.displayText( text );
    }

    /**
     * Clear the {@link OutputDisplay} of all Text.
     */
    public void clear() {
        outputDisplay.clear();
    }

    /**
     * Get the {@link OutputDisplay} for the {@link VendingMachine}.  This is a Glue Layer that will translated String
     * messages from the {@link VendingMachine} onto the GUI's output display.
     *
     * @return {@link VendingMachine}'s {@link OutputDisplay}.
     */
    public OutputDisplay getOutputDisplay() {
        return outputDisplay;
    }
}
