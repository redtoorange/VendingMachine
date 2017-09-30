package vendingmachine.io;

import vendingmachine.money.Coin;

/**
 * CoinSlot.java - The {@link CoinSlot} is a Software abstraction of what might be a piece of hardware that detects a
 * {@link Coin} being inserted.  It is a Glue class to help bind the {@link vendingmachine.gui.ViewController}
 * to the rest of the program.
 *
 * @author Andrew McGuiness
 * @version 9/29/2017
 */
public class CoinSlot {
    private InputController inputController;

    /**
     * Create a {@link CoinSlot} that will used to provide the {@link InputController} with input feedback.
     *
     * @param inputController Where to send input messages.
     */
    protected CoinSlot( InputController inputController ) {
        this.inputController = inputController;
    }

    /**
     * When a {@link Coin} is inserted, inform the {@link InputController}.
     *
     * @param insertedCoin {@link Coin} that was inserted.
     */
    public void insertCoin( Coin insertedCoin ) {
        inputController.insertCoin( insertedCoin );
    }
}
