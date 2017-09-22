package vendingmachine.io;

import vendingmachine.money.Coin;

/**
 * ${FILE_NAME}.java - Description
 *
 * @author
 * @version 15/Sep/2017
 */
public class CoinSlot {
    private InputController inputController;

    public CoinSlot( InputController inputController ) {
        this.inputController = inputController;
    }

    public void insertCoint( Coin c ) {
        inputController.insertCoin( c );
    }
}
