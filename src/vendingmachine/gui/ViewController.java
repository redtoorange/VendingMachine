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
 * ViewController.java - Description
 *
 * @author Andrew McGuiness
 * @version 9/24/2017
 */
public class ViewController {
    @FXML
    private TextArea textDisplay;

    @FXML
    private Button pennyButton, nickelButton, dimeButton, quarterButton;

    private StringBuilder currentInput = new StringBuilder(  );
    private KeyPad keyPad;
    private CoinSlot coinSlot;


    public void init( KeyPad keyPad, CoinSlot coinSlot, OutputDisplay display){
        this.keyPad = keyPad;
        this.coinSlot = coinSlot;
        display.setTextDisplay( textDisplay );
    }

    @FXML
    public void numeralClicked( ActionEvent event){
        Button source = (Button) event.getSource();
        currentInput.append( source.getText() );
        textDisplay.setText( currentInput.toString() );
        keyPad.pressedButton( source.getText()  );
    }

    @FXML
    public void enterClicked( ActionEvent event ){
        keyPad.pressedButton( "ENTER" );
        currentInput = new StringBuilder(  );
    }

    @FXML
    public void doneClicked(ActionEvent event ){
        keyPad.pressedButton( "DONE" );
        currentInput = new StringBuilder(  );
    }

    @FXML
    public void cancelClicked( ActionEvent event ){
        keyPad.pressedButton( "CANCEL" );
        currentInput = new StringBuilder(  );
    }

    @FXML
    public void coinInserted( ActionEvent event){
        Button source = (Button) event.getSource();
        if( source == pennyButton ){
            coinSlot.insertCoint( Coin.Penny );
        }
        else if( source == nickelButton ){
            coinSlot.insertCoint( Coin.Nickel  );
        }
        else if( source == dimeButton ){
            coinSlot.insertCoint( Coin.Dime  );
        }
        else if( source == quarterButton ){
            coinSlot.insertCoint( Coin.Quarter  );
        }
    }
}
