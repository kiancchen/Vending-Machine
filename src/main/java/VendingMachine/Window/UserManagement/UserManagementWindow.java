package VendingMachine.Window.UserManagement;

import VendingMachine.Processor.MainProcessor;
import VendingMachine.Processor.UserProcessor;
import VendingMachine.User;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class UserManagementWindow {
    private Stage stage;
    private Scene scene;
    private AnchorPane pane;
    private TableView<UserTableEntry> table;
    private ComboBox<String> typeCombo;
    private Button addButton;
    private Button changeButton;
    private Button removeButton;
    private TextField usernameField;
    private TextField passwordField;
    private int selectedId;


    public UserManagementWindow() {
        stage = new Stage();
        pane = new AnchorPane();
        scene = new Scene(pane, 600, 480);
        stage.setScene(scene);
        stage.setTitle("User Management");
        stage.show();

        initTable();
        initButton();
        initButtonActions();
        initCombobox();
        initTextFields();
        selectedId = -1;
    }

    private void initTable() {
        table = new TableView<>();
        table.setLayoutX(95);
        table.setLayoutY(15);
        table.setPrefWidth(400);
        table.setPrefHeight(300);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        pane.getChildren().add(table);

        // create table
        String[] colNames = {"ID", "User Name", "Password", "Type"};
        String[] properties = {"id", "username", "password", "type"};
        for (int i = 0; i < colNames.length; i++) {
            String colName = colNames[i];
            TableColumn<UserTableEntry, String> column = new TableColumn<>(colName);
            column.setSortable(false);
            column.setPrefWidth(118);
            column.setStyle("-fx-alignment: CENTER;");
            column.setCellValueFactory(new PropertyValueFactory<>(properties[i]));
            table.getColumns().add(column);
        }
        table.setOnMouseClicked(event -> {
            if (!table.getSelectionModel().isEmpty()) {
                UserTableEntry selected = table.getSelectionModel().getSelectedItem();
                selectedId = Integer.parseInt(selected.getId());
                usernameField.setText(selected.getUsername());
                passwordField.setText(selected.getPassword());
                typeCombo.getSelectionModel().select(selected.getType());
            }
        });

        setTableData();
    }

    private void initButton() {
        addButton = new Button();
        changeButton = new Button();
        removeButton = new Button();

        Button[] buttons = {addButton, changeButton, removeButton};
        String[] texts = {"Add", "Change", "Remove"};

        for (int i = 0; i < buttons.length; i++) {
            Button button = buttons[i];
            button.setLayoutX(90 + 150 * i);
            button.setLayoutY(400);
            button.setPrefWidth(120);
            button.setPrefHeight(30);
            button.setText(texts[i]);
            pane.getChildren().add(button);
        }
    }

    private void initButtonActions() {
        addButton.setOnAction((event -> addAction()));
        removeButton.setOnAction((event -> removeAction()));
        changeButton.setOnAction((event -> changeAction()));
    }

    private void initCombobox() {
        typeCombo = new ComboBox<>();
        typeCombo.setLayoutX(390);
        typeCombo.setLayoutY(350);


        for (User.UserType s : User.UserType.values()) {
            if (s != User.UserType.ANONYMOUS) {
                typeCombo.getItems().add(s.toString());
            }
        }

        pane.getChildren().add(typeCombo);
    }

    private void initTextFields() {
        usernameField = new TextField();
        usernameField.setLayoutX(90);
        usernameField.setLayoutY(350);
        usernameField.setPrefWidth(120);
        usernameField.setPromptText("Username");

        passwordField = new TextField();
        passwordField.setLayoutX(240);
        passwordField.setLayoutY(350);
        passwordField.setPrefWidth(120);
        passwordField.setPromptText("Password");

        pane.getChildren().add(usernameField);
        pane.getChildren().add(passwordField);
    }

    private void setTableData() {
        // set data to table
        table.getItems().clear();
        for (User user : MainProcessor.getUserProcessor().getUsers()) {
            table.getItems().add(new UserTableEntry(Integer.toString(user.getId()),
                    user.getUsername(),
                    user.getPassword(),
                    user.getType().toString()));
        }
    }

    private void addAction() {
        String type = typeCombo.getSelectionModel().getSelectedItem();

        if (!validateInput()) {
            return;
        }

        try {
            if (MainProcessor.getUserProcessor().addUser(usernameField.getText(), passwordField.getText(), type)) {
                alert(Alert.AlertType.INFORMATION, "Successfully add.");

                setTableData();
                selectedId = -1;
            } else {
                alert(Alert.AlertType.INFORMATION, "User exists.");
            }
        } catch (Exception e) {
            alert(Alert.AlertType.WARNING, "Can not add user.");
        }

    }

    private void removeAction() {
        int id = Integer.parseInt(table.getSelectionModel().getSelectedItem().getId());
        try {
            if (MainProcessor.getUserProcessor().removeUser(id)) {
                alert(Alert.AlertType.INFORMATION, "Successfully removed");
                setTableData();
            }
        } catch (Exception e) {
            alert(Alert.AlertType.WARNING, "User selected is not existed");
        }
    }

    private void changeAction() {
        UserProcessor userProcessor = MainProcessor.getUserProcessor();
        if (selectedId < 0) {
            alert(Alert.AlertType.WARNING, "You don't select any user.");
            return;
        } else if (selectedId == 0) {
            alert(Alert.AlertType.WARNING, "You can't remove the anonymous user" +
                    ".");
            return;
        } else if (!validateInput()) {
            return;
        }

        try {
            userProcessor.changeUsername(selectedId, usernameField.getText());
            userProcessor.changePassword(selectedId, passwordField.getText());
            userProcessor.changeType(selectedId, typeCombo.getSelectionModel().getSelectedItem());
            alert(Alert.AlertType.WARNING, "Change successfully.");
        } catch (Exception e) {
            alert(Alert.AlertType.WARNING, "Change failed.");
        }
        usernameField.setText("");
        passwordField.setText("");
        typeCombo.getSelectionModel().clearSelection();
        setTableData();
        selectedId = -1;
    }

    private boolean validateInput() {
        if (usernameField.getText().trim().isEmpty()) {
            alert(Alert.AlertType.WARNING, "Username needed");
            return false;
        } else if (passwordField.getText().trim().isEmpty()) {
            alert(Alert.AlertType.WARNING, "Password needed");
            return false;
        } else if (typeCombo.getSelectionModel().isEmpty()) {
            alert(Alert.AlertType.WARNING, "User type needed.");
            return false;
        }
        return true;
    }

    private void alert(Alert.AlertType warning, String s) {
        Alert alert = new Alert(warning, s);
        alert.show();
    }

}
