import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import vendingmachine.VendingMachine;
import vendingmachine.gui.ViewController;
import vendingmachine.io.InputController;

/**
 * MainGUI.java - Description
 *
 * @author Andrew McGuiness
 * @version 9/24/2017
 */
public class MainGUI extends Application {
    private VendingMachine vendingMachine;
    public static void main( String[] args ) {
        launch( args );
    }

    @Override
    public void start( Stage primaryStage ) throws Exception {
        FXMLLoader loader = new FXMLLoader( getClass().getResource( "vendingmachine/gui/View.fxml" ) );
        Parent root = loader.load();

        vendingMachine = new VendingMachine();
        InputController inputController = vendingMachine.getInputController();

        ViewController viewController = loader.getController();
        viewController.init( inputController.getKeyPad(), inputController.getCoinSlot() );


        Scene scene = new Scene( root );
        primaryStage.setScene( scene );
        primaryStage.show();
    }
}
