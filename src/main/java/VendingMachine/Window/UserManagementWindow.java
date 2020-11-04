package VendingMachine.Window;

import VendingMachine.MainProcessor;
import VendingMachine.User;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class UserManagementWindow {
    private MainProcessor processor;
    private Stage stage;
    private Scene scene;
    private AnchorPane pane;
    private TableView<UserTableEntry> table;

    private Button addButton;
    private Button changeButton;
    private Button removeButton;


    public UserManagementWindow(MainProcessor processor) {
        this.processor = processor;
        stage = new Stage();
        pane = new AnchorPane();
        scene = new Scene(pane, 600, 480);
        stage.setScene(scene);
        stage.setTitle("User Management");
        stage.show();

        initTable();
        initButton();
        initButtonActions();
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

        // set data to table
        for (User user : processor.getUsers()) {
            table.getItems().add(new UserTableEntry(Integer.toString(user.getId()),
                    user.getUsername(),
                    user.getPassword(),
                    user.getTypeString()));
        }
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
            button.setPrefWidth(100);
            button.setPrefHeight(30);
            button.setText(texts[i]);
            pane.getChildren().add(button);
        }
    }

    private void initButtonActions() {
        addButton.setOnAction((event -> new AddWindowController(processor, table)));
        removeButton.setOnAction((event -> new RemoveWindowController(processor, table)));
        changeButton.setOnAction((event -> new ChangeWindowController(processor)));
    }

}
