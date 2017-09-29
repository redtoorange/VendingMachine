package vendingmachine.io;

/**
 * KeyPad.java - Description
 *
 * @author  Andrew McGuiness
 * @version 15/Sep/2017
 */
public class KeyPad {
    public InputController inputController;
    private String currentInput = "";

    public KeyPad( InputController inputController ) {
        this.inputController = inputController;
    }

    public void pressedButton( String key ) {
        switch ( key ) {
            case "CANCEL":
                inputController.cancelSession();
                currentInput = "";
                break;
            case "ENTER":
                inputController.processInput( currentInput );
                currentInput = "";
                break;
            case "DONE":
                inputController.finishSession();
                currentInput = "";
                break;
            default:
                currentInput += key;
        }
    }
}
