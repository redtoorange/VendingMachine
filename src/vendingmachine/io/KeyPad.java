package vendingmachine.io;

/**
 * KeyPad.java - The {@link KeyPad} is a Software abstraction of what might be a piece of hardware that detects a
 * key input.  It is a Glue class to help bind the {@link vendingmachine.gui.ViewController} to the rest of
 * the program.
 *
 * @author Andrew McGuiness
 * @version 9/29/2017
 */
public class KeyPad {
    private InputController inputController;
    private String currentInput = "";

    /**
     * Create a {@link KeyPad} that will used to provide the {@link InputController} with input feedback.
     *
     * @param inputController Where to send input messages.
     */
    protected KeyPad( InputController inputController ) {
        this.inputController = inputController;
    }

    /**
     * A {@link javafx.scene.control.Button} was pressed, this is decoded here into which message should be sent to
     * the {@link InputController}.
     *
     * @param pressedKey {@link String} representation of the Key that was pressed.
     */
    public void pressedButton( String pressedKey ) {
        switch ( pressedKey ) {
            case "CANCEL":
                inputController.cancelSession();
                currentInput = "";
                break;

            case "ENTER":
                inputController.processInput( currentInput );
                currentInput = "";
                break;

            case "FINISH":
                inputController.finishSession();
                currentInput = "";
                break;

            default:
                //Keep a running tally of the Non-Function keys pressed.
                currentInput += pressedKey;
        }
    }
}
