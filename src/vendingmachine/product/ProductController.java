package vendingmachine.product;

import vendingmachine.io.InputException;
import vendingmachine.money.Currency;

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

        addTestProducts();
    }

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
        productSlots[1][1].setCurrentProduct( new Product( "Cool Ranch Doritos") );

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
