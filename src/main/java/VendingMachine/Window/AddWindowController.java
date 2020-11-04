package VendingMachine.Window;

import VendingMachine.MainProcessor;
import VendingMachine.User;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AddWindowController {

    private MainProcessor processor;
    private Stage stage;
    private Scene scene;
    private AnchorPane pane;

    private Button add;
    private TextField username;
    private TextField password;
    private ComboBox<String> typeCombo;
    private TableView<UserTableEntry> table;

    public AddWindowController(MainProcessor processor, TableView<UserTableEntry> table) {
        this.table = table;
        this.processor = processor;

        stage = new Stage();
        pane = new AnchorPane();
        scene = new Scene(pane, 440, 280);
        stage.setScene(scene);
        stage.setTitle("Add User");
        stage.show();

        initLabels();
        initButtons();
        initTextFields();
        initCombobox();
    }

    private void initLabels() {
        Label label1 = new Label("Username: ");
        label1.setLayoutX(180);
        label1.setLayoutY(50);
        pane.getChildren().add(label1);

        Label label = new Label("Password: ");
        label.setLayoutX(180);
        label.setLayoutY(130);
        pane.getChildren().add(label);
    }

    private void initButtons() {
        add = new Button();

        add.setLayoutX(40);
        add.setLayoutY(80);
        add.setPrefWidth(100);
        add.setPrefHeight(30);
        add.setText("Add");
        pane.getChildren().add(add);

        add.setOnAction(event -> add());
    }

    private void initTextFields() {
        username = new TextField();
        username.setLayoutX(180);
        username.setLayoutY(80);


        password = new TextField();
        password.setLayoutX(180);
        password.setLayoutY(160);

        pane.getChildren().add(username);
        pane.getChildren().add(password);
    }

    private void initCombobox() {
        typeCombo = new ComboBox<>();
        typeCombo.setLayoutX(40);
        typeCombo.setLayoutY(160);

        for (User.UserType s : User.UserType.values()) {
            typeCombo.getItems().add(s.toString());
        }

        typeCombo.getSelectionModel().select(0);

        pane.getChildren().add(typeCombo);
    }

    private void add() {

        String type = typeCombo.getSelectionModel().getSelectedItem();

        if (username.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Username needed");
            alert.show();
        } else if (password.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Password needed");
            alert.show();
        } else {
            try {
                if (processor.addUserWithType(username.getText(), password.getText(), type)) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Successfully add.");
                    alert.show();

                    table.getItems().clear();

                    for (User user : processor.getUsers()) {
                        table.getItems().add(new UserTableEntry(Integer.toString(user.getId()),
                                user.getUsername(),
                                user.getPassword(),
                                user.getTypeString()));
                    }
                }else{
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Fail to add.");
                    alert.show();
                }
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Can not add user.");
                alert.show();
            }
        }
    }
}
