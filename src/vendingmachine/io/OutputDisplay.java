package vendingmachine.io;

import javafx.scene.control.TextArea;

/**
 * OutputDisplay.java - The {@link OutputDisplay} is a Software abstraction of what might be a piece of hardware that
 * display text to the user.  It is a Glue class to help bind the {@link vendingmachine.gui.ViewController}
 * to the rest of the program.
 *
 * @author Andrew McGuiness
 * @version 9/29/2017
 */
public class OutputDisplay {
    private TextArea textDisplay = null;

    /**
     * Set the {@link TextArea} the system should write output to.
     *
     * @param field {@link TextArea} to use for output.
     */
    public void setTextDisplay( TextArea field ) {
        this.textDisplay = field;
    }

    /**
     * Write a {@link String} to the designated output device.  The console is written to as a simulated log.
     *
     * @param text {@link String} to write to the display and log.
     */
    protected void displayText( String text ) {
        if ( textDisplay != null )
            textDisplay.setText( text );

        System.out.println( text );
    }

    /**
     * Clear the output device's screen.
     */
    protected void clear() {
        if ( textDisplay != null )
            textDisplay.setText( "" );
    }
}
