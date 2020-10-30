package VendingMachine;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginWindow {
    private MainProcessor processor;
    private Stage stage;
    private Scene scene;
    private AnchorPane pane;

    public LoginWindow(MainProcessor processor) {
        this.processor = processor;
        stage = new Stage();
        pane = new AnchorPane();
        scene = new Scene(pane, 600, 450);
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.show();
    }


}
