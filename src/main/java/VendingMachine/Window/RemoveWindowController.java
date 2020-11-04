package VendingMachine.Window;

import VendingMachine.MainProcessor;
import VendingMachine.User;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
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
    private TableView<UserTableEntry> table;

    public RemoveWindowController(MainProcessor processor, TableView<UserTableEntry> table) {
        this.processor = processor;
        this.table = table;

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

        users.getSelectionModel().select(0);

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

        remove.setOnAction(event -> remove());
    }

    public void remove() {
        String[] selectedString = users.getSelectionModel().getSelectedItem().split(",");
        int selected = Integer.parseInt(selectedString[0]);
        try {
            if(processor.removeUser(selected)) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Successfully removed");
                alert.show();

                initCombox();

                for (User user : processor.getUsers()) {
                    table.getItems().clear();
                }

                for (User user : processor.getUsers()) {
                    table.getItems().add(new UserTableEntry(Integer.toString(user.getId()),
                            user.getUsername(),
                            user.getPassword(),
                            user.getTypeString()));
                }
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "User selected is not existed");
            alert.show();
        }
    }


}
