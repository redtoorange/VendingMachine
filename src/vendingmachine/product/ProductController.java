package vendingmachine.product;

import vendingmachine.io.InputException;

/**
 * vendingmachine.product.ProductController.java - Description
 *
 * @author Andrew McGuiness
 * @version 9/12/2017
 */
public class ProductController {
    private ProductSlot[][] productSlots;

    public ProductController( int cols, int rows ) {
        productSlots = new ProductSlot[cols][rows];
        for ( int x = 0; x < cols; x++ ) {
            for ( int y = 0; y < rows; y++ ) {
                productSlots[x][y] = new ProductSlot();
            }
        }
    }

    public ProductSlot getSlot( String code ) throws InputException {
        ProductSlot slot = null;
        if ( code.length() != 2 )
            throw new InputException( "Input incorrect length" );
        else {
            int col = code.charAt( 0 ) - '0';
            int row = code.charAt( 1 ) - '0';

            if ( col < 0 || col >= productSlots.length || row < 0 || row >= productSlots[col].length ) {
                throw new InputException( "Col,Row out of bounds." );
            } else
                slot = productSlots[col][row];
        }
        return slot;
    }
}
