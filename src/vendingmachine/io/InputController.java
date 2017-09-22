package vendingmachine.io;

import vendingmachine.VendingMachine;
import vendingmachine.money.Coin;

/**
 * ${FILE_NAME}.java - Description
 *
 * @author
 * @version 15/Sep/2017
 */
public class InputController {
    private VendingMachine vendingMachine;
    private KeyPad keyPad;
    private CoinSlot coinSlot;

    public InputController( VendingMachine vendingMachine ) {
        this.vendingMachine = vendingMachine;
        keyPad = new KeyPad( this );
        coinSlot = new CoinSlot( this );
    }

    public void insertCoin( Coin coin ) {
        vendingMachine.getCurrencyController().addCoinToBalance( coin );
    }

    public void processInput( String input ) {
        System.out.println( "Input processed: " + input );

    }

    public void cancelSession() {
        System.out.println( "Session Cancelled!" );
    }

    public KeyPad getKeyPad() {
        return keyPad;
    }

    public CoinSlot getCoinSlot() {
        return coinSlot;
    }
}
