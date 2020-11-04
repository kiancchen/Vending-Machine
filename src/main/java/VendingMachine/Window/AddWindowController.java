package VendingMachine.Window;

import VendingMachine.MainProcessor;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AddWindowController {

    private MainProcessor processor;
    private Stage stage;
    private Scene scene;
    private AnchorPane pane;

    public AddWindowController(MainProcessor processor) {
        this.processor = processor;

        stage = new Stage();
        pane = new AnchorPane();
        scene = new Scene(pane, 540, 400);
        stage.setScene(scene);
        stage.setTitle("Add User");
        stage.show();
    }
}
