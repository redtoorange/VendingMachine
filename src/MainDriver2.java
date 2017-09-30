import vendingmachine.VendingMachine;
import vendingmachine.io.CoinSlot;
import vendingmachine.io.InputController;
import vendingmachine.io.KeyPad;
import vendingmachine.money.Coin;

/**
 * MainDriver2.java - Description
 *
 * @author  Andrew McGuiness
 * @version 9/15/2017
 */
public class MainDriver2 {
    public static void main( String[] args ) {
        VendingMachine vm = new VendingMachine();
        InputController ic = vm.getInputController();
        CoinSlot cs = ic.getCoinSlot();
        KeyPad kp = ic.getKeyPad();

        System.out.println( vm.getCurrencyController().getCurrentBalance() );
        cs.insertCoin( Coin.Quarter );
        cs.insertCoin( Coin.Quarter );
        cs.insertCoin( Coin.Quarter );
        System.out.println( vm.getCurrencyController().getCurrentBalance() );

        kp.pressedButton( "00" );
        kp.pressedButton( "ENTER" );
        kp.pressedButton( "CANCEL" );
    }
}
