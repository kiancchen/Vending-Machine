package VendingMachine.Window;

import VendingMachine.MainProcessor;
import VendingMachine.User;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.List;

public class RemoveWindowController {

    private MainProcessor processor;
    private Stage stage;
    private Scene scene;
    private AnchorPane pane;

    private Button remove;

    private ComboBox<String> users;

    public RemoveWindowController(MainProcessor processor) {
        this.processor = processor;

        stage = new Stage();
        pane = new AnchorPane();
        scene = new Scene(pane, 440, 150);
        stage.setScene(scene);
        stage.setTitle("Remove User");
        stage.show();

        initButtons();
        initCombox();
    }

    private void initCombox() {
        users = new ComboBox<>();
        users.setLayoutX(200);
        users.setLayoutY(40);

        List<User> Users = processor.getUsers();

        for(User u : Users) {
            users.getItems().add(u.getString());
        }

        pane.getChildren().add(users);
    }

    private void initButtons() {
        remove = new Button();

        remove.setLayoutX(40);
        remove.setLayoutY(40);
        remove.setPrefWidth(100);
        remove.setPrefHeight(30);
        remove.setText("Remove");
        pane.getChildren().add(remove);
    }


}
