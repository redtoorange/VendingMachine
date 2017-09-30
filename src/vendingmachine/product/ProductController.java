package vendingmachine.product;

import vendingmachine.io.InputException;
import vendingmachine.money.Currency;

/**
 * ProductController.java - The {@link ProductController} supplies an interface between the
 * {@link vendingmachine.VendingMachine} and all of the {@link ProductSlot}s which hold the {@link Product}s that are
 * for sale.  The dimensions of the {@link ProductController} can be dynamically set to simulate any sized
 * {@link vendingmachine.VendingMachine}.
 *
 * @author Andrew McGuiness
 * @version 9/29/2017
 */
public class ProductController {
    private ProductSlot[][] productSlots;

    /**
     * Create a new {@link ProductController} with the supplied dimensions.
     *
     * @param cols number of cols the {@link vendingmachine.VendingMachine} has.
     * @param rows number of rows the {@link vendingmachine.VendingMachine} has.
     */
    public ProductController( int cols, int rows ) {
        productSlots = new ProductSlot[cols][rows];
        for ( int x = 0; x < cols; x++ ) {
            for ( int y = 0; y < rows; y++ ) {
                productSlots[x][y] = new ProductSlot();
            }
        }

        addTestProducts();
    }

    /** Initialize the {@link ProductController} with some data to allow testing. */
    private void addTestProducts() {
        productSlots[0][0].setProductStock( 10 );
        productSlots[0][0].setCost( new Currency( 75 ) );
        productSlots[0][0].setCurrentProduct( new Product( "Snickers" ) );

        productSlots[0][1].setProductStock( 10 );
        productSlots[0][1].setCost( new Currency( 75 ) );
        productSlots[0][1].setCurrentProduct( new Product( "Milky Way" ) );

        productSlots[0][2].setProductStock( 10 );
        productSlots[0][2].setCost( new Currency( 75 ) );
        productSlots[0][2].setCurrentProduct( new Product( "Hersheys" ) );

        productSlots[1][0].setProductStock( 10 );
        productSlots[1][0].setCost( new Currency( 75 ) );
        productSlots[1][0].setCurrentProduct( new Product( "Doritos" ) );

        productSlots[1][1].setProductStock( 10 );
        productSlots[1][1].setCost( new Currency( 75 ) );
        productSlots[1][1].setCurrentProduct( new Product( "Cool Ranch Doritos" ) );

        productSlots[1][2].setProductStock( 10 );
        productSlots[1][2].setCost( new Currency( 75 ) );
        productSlots[1][2].setCurrentProduct( new Product( "Fritos" ) );

        productSlots[2][0].setProductStock( 10 );
        productSlots[2][0].setCost( new Currency( 125 ) );
        productSlots[2][0].setCurrentProduct( new Product( "Pringles" ) );

        productSlots[2][1].setProductStock( 10 );
        productSlots[2][1].setCost( new Currency( 50 ) );
        productSlots[2][1].setCurrentProduct( new Product( "Peanut Butter Crackers" ) );

        productSlots[2][2].setProductStock( 10 );
        productSlots[2][2].setCost( new Currency( 50 ) );
        productSlots[2][2].setCurrentProduct( new Product( "Cheese Crackers" ) );
    }

    /**
     * Attempt to locate a {@link ProductSlot} at an X,Y position.
     *
     * @param code x,y position of the {@link ProductSlot}
     * @return {@link ProductSlot} at the x,y position that the input code represents.
     * @throws InputException thrown if the input {@link String} for the {@link ProductSlot} is malformed in any way.
     */
    public ProductSlot getSlot( String code ) throws InputException {
        ProductSlot slot;

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
