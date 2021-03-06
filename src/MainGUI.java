import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import vendingmachine.VendingMachine;
import vendingmachine.gui.ViewController;
import vendingmachine.io.InputController;

/**
 * MainGUI.java - Main Driver class for the GUI VendingMachineApplication.  The ViewController binds to the IO controls
 * of the VendingMachine.  Output from the VendingMachine is presented with System.out.println();
 *
 * @author Andrew McGuiness
 * @version 9/24/2017
 */
public class MainGUI extends Application {
    public static void main( String[] args ) {
        launch( args );
    }

    @Override
    public void start( Stage primaryStage ) throws Exception {
        FXMLLoader loader = new FXMLLoader( getClass().getResource( "vendingmachine/gui/View.fxml" ) );
        Parent root = loader.load();

        //Create VM
        VendingMachine vendingMachine = new VendingMachine();
        InputController inputController = vendingMachine.getInputController();

        //Bind Controller to VM
        ViewController viewController = loader.getController();
        viewController.init( inputController.getKeyPad(), inputController.getCoinSlot(), inputController.getOutputDisplay() );

        //Display
        Scene scene = new Scene( root );
        primaryStage.setScene( scene );
        primaryStage.show();
    }
}
