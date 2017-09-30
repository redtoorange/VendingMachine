package vendingmachine.io;

/**
 * InputException.java - A Specialized Exception that occurs when the input it outside of the expect range.
 *
 * @author Andrew McGuiness
 * @version 9/29/2017
 */
public class InputException extends Exception {
    public InputException( String message ) {
        super( message );
    }
}
