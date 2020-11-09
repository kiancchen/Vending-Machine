package VendingMachine.Window.CashManagement;

import VendingMachine.Data.User;
import VendingMachine.Processor.MainProcessor;
import VendingMachine.Processor.UserProcessor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.List;

public class ChangeCashWindow {
    private Stage stage;
    private Scene scene;
    private AnchorPane pane;

    private ComboBox<String> usersCombo;
    private TextField username;
    private TextField password;
    private ComboBox<String> typesCombo;
    private Button changeButton;
    private TableView<CashTableEntry> table;


    public ChangeCashWindow(TableView<CashTableEntry> table) {
        this.table = table;

        stage = new Stage();
        pane = new AnchorPane();
        scene = new Scene(pane, 540, 300);
        stage.setScene(scene);
        stage.setTitle("Change User");
        stage.show();

        initLabels();
        initCombobox();
        initTextFields();
        initButtons();
    }

    private void initLabels() {
        Label label1 = new Label("Choose a user to change: ");
        label1.setLayoutX(50);
        label1.setLayoutY(50);
        pane.getChildren().add(label1);

        Label label2 = new Label("New Username: ");
        label2.setLayoutX(290);
        label2.setLayoutY(50);
        pane.getChildren().add(label2);

        Label label3 = new Label("New Password: ");
        label3.setLayoutX(290);
        label3.setLayoutY(130);
        pane.getChildren().add(label3);

        Label label4 = new Label("Choose user type: ");
        label4.setLayoutX(50);
        label4.setLayoutY(130);
        pane.getChildren().add(label4);
    }

    private void initCombobox() {
        usersCombo = new ComboBox<>();
        usersCombo.setLayoutX(50);
        usersCombo.setLayoutY(80);

        List<User> users = MainProcessor.getUserProcessor().getUsers();
        for (int i = 1; i < users.size(); i++) {
            User user = users.get(i);
            usersCombo.getItems().add(user.getString());
        }

        usersCombo.getSelectionModel().select(0);

        pane.getChildren().add(usersCombo);

        typesCombo = new ComboBox<>();
        typesCombo.setLayoutX(50);
        typesCombo.setLayoutY(160);

        for (User.UserType s : User.UserType.values()) {
            if (s != User.UserType.ANONYMOUS) {
                typesCombo.getItems().add(s.toString());
            }
        }

        typesCombo.getSelectionModel().select(0);

        pane.getChildren().add(typesCombo);
    }

    private void initTextFields() {
        username = new TextField();
        username.setLayoutX(290);
        username.setLayoutY(80);

        password = new TextField();
        password.setLayoutX(290);
        password.setLayoutY(160);

        pane.getChildren().add(username);
        pane.getChildren().add(password);
    }

    private void initButtons() {
        changeButton = new Button("Change");
        changeButton.setLayoutX(220);
        changeButton.setLayoutY(230);

        changeButton.setOnAction(event -> change());

        pane.getChildren().add(changeButton);
    }

    private void change() {
        UserProcessor userProcessor = MainProcessor.getUserProcessor();
        String[] selectedString = usersCombo.getSelectionModel().getSelectedItem().split(",");
        int selectedID = Integer.parseInt(selectedString[0]);

        if (username.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Username needed.");
            alert.show();
        } else if (password.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Password needed.");
            alert.show();
        } else {
            try {
                userProcessor.changeUsername(selectedID, username.getText());
                userProcessor.changePassword(selectedID, password.getText());
                userProcessor.changeType(selectedID, typesCombo.getSelectionModel().getSelectedItem());
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Change failed.");
                alert.show();
            }

            usersCombo.getItems().clear();
            List<User> users = userProcessor.getUsers();
            for (int i = 1; i < users.size(); i++) {
                User user = users.get(i);
                usersCombo.getItems().add(user.getString());
            }
            usersCombo.getSelectionModel().select(0);

            username.setText("");
            password.setText("");

            table.getItems().clear();
        }
    }
}
