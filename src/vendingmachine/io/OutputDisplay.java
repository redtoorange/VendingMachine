package vendingmachine.io;

import javafx.scene.control.TextArea;

/**
 * ${FILE_NAME}.java - Description
 *
 * @author
 * @version 25/Sep/2017
 */
public class OutputDisplay {
    private TextArea textDisplay = null;

    public void setTextDisplay( TextArea field ){
        this.textDisplay = field;
    }

    public void displayText( String text ){
        if( textDisplay == null )
            System.out.println( text );
        else
            textDisplay.setText( text );
    }
}
