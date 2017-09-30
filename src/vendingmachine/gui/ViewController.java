package vendingmachine.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import vendingmachine.io.CoinSlot;
import vendingmachine.io.KeyPad;
import vendingmachine.io.OutputDisplay;
import vendingmachine.money.Coin;

/**
 * ViewController.java - {@link ViewController} is used by JavaFX to bind the GUI to the
 * {@link javafx.application.Application}.  Each element of the GUI is bound to the corresponding piece of the IO
 * Controls.  Messages are received from the GUI here, then dispatched into the IO Pipeline up to the
 * {@link vendingmachine.VendingMachine}.
 *
 * @author Andrew McGuiness
 * @version 9/24/2017
 */
public class ViewController {
    @FXML
    private TextArea textDisplay;

    @FXML
    private Button pennyButton, nickelButton, dimeButton, quarterButton;

    private StringBuilder currentInput = new StringBuilder();
    private KeyPad keyPad;
    private CoinSlot coinSlot;

    /**
     * Initialize the {@link ViewController} to work with the internal IO Pipeline of the
     * {@link vendingmachine.VendingMachine}.  This effectively Glues this {@link ViewController} to a particular
     * Instance of a {@link vendingmachine.VendingMachine}.
     *
     * @param keyPad            {@link KeyPad} instance to send button messages to.
     * @param coinSlot          {@link CoinSlot} to send insertCoin messages to.
     * @param outputDisplay     {@link OutputDisplay} that will be sending output to the GUI.
     */
    public void init( KeyPad keyPad, CoinSlot coinSlot, OutputDisplay outputDisplay ) {
        this.keyPad = keyPad;
        this.coinSlot = coinSlot;

        outputDisplay.setTextDisplay( textDisplay );
    }

    /**
     * A number {@link Button} was pressed, determine the value of the {@link Button} then add it to the current input
     * buffer.  Display the current buffer on the {@link OutputDisplay}.
     *
     * @param event {@link ActionEvent} sent by JavaFX that contains the source {@link Button}
     */
    @FXML
    public void numeralClicked( ActionEvent event ) {
        Button source = ( Button ) event.getSource();
        currentInput.append( source.getText() );
        textDisplay.setText( currentInput.toString() );
        keyPad.pressedButton( source.getText() );
    }

    /** Called by JavaFX when the "Enter" {@link Button} is clicked. */
    @FXML
    public void enterClicked() {
        keyPad.pressedButton( "ENTER" );
        currentInput = new StringBuilder();
    }

    /** Called by JavaFX when the "Finish" {@link Button} is clicked. */
    @FXML
    public void finishClicked() {
        keyPad.pressedButton( "FINISH" );
        currentInput = new StringBuilder();
    }

    /** Called by JavaFX when the "Cancel" {@link Button} is clicked. */
    @FXML
    public void cancelClicked() {
        keyPad.pressedButton( "CANCEL" );
        currentInput = new StringBuilder();
    }

    /**
     * Called by JavaFX when a coin {@link Button} is clicked.
     *
     * @param event {@link ActionEvent} used to determine which {@link Button} was clicked.
     */
    @FXML
    public void coinInserted( ActionEvent event ) {
        Button source = ( Button ) event.getSource();

        //Send insertCoin message
        if ( source == pennyButton )
            coinSlot.insertCoin( Coin.Penny );

        else if ( source == nickelButton )
            coinSlot.insertCoin( Coin.Nickel );

        else if ( source == dimeButton )
            coinSlot.insertCoin( Coin.Dime );

        else if ( source == quarterButton )
            coinSlot.insertCoin( Coin.Quarter );
    }
}
