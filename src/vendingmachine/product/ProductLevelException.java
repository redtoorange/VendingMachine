package vendingmachine.product;

/**
 * ProductLevelException.java - A specialized {@link Exception} that is thrown if there is a problem with the level of
 * stock in a {@link ProductSlot}.  If a {@link ProductSlot}'s stock level goes negative, this should be thrown.
 *
 * @author Andrew McGuiness
 * @version 9/29/2017
 */
public class ProductLevelException extends Exception {
    /**
     * Create a new {@link ProductLevelException} with a message of what happened.
     *
     * @param message {@link String} explaining what fault occurred.
     */
    public ProductLevelException( String message ) {
        super( message );
    }
}
