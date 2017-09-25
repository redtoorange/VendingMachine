package vendingmachine.io;

import javafx.scene.control.TextField;

/**
 * ${FILE_NAME}.java - Description
 *
 * @author
 * @version 25/Sep/2017
 */
public class OutputDisplay {
    private TextField textField = null;

    public void setTextField( TextField field ){
        this.textField = field;
    }

    public void displayText( String text ){
        if( textField == null )
            System.out.println( text );
        else
            textField.setText( text );
    }
}
