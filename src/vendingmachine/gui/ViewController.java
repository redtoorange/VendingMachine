package vendingmachine.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
    private TextField textDisplay;

    private StringBuilder currentInput = new StringBuilder(  );
    private KeyPad keyPad;
    private CoinSlot coinSlot;


    public void init( KeyPad keyPad, CoinSlot coinSlot, OutputDisplay display){
        this.keyPad = keyPad;
        this.coinSlot = coinSlot;
        display.setTextField( textDisplay );
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
    public void cancelClicked( ActionEvent event ){
        keyPad.pressedButton( "CANCEL" );
        currentInput = new StringBuilder(  );
    }

    @FXML
    public void coinInserted( ActionEvent event){
        Button source = (Button) event.getSource();
        switch ( source.getText() ){
            case "penny":
                coinSlot.insertCoint( Coin.Penny );
                break;
            case "nickel":
                coinSlot.insertCoint( Coin.Nickel  );
                break;
            case "dime":
                coinSlot.insertCoint( Coin.Dime  );
                break;
            case "quarter":
                coinSlot.insertCoint( Coin.Quarter  );
                break;
        }
    }
}
