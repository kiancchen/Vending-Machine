package VendingMachine.Window;

import VendingMachine.MainProcessor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class UserManagementWindow {
    private MainProcessor processor;
    private Stage stage;
    private Scene scene;
    private AnchorPane pane;
    private TextField username_text;
    private TextField password_text;
    private TableView<UserTableEntry> table;

    public UserManagementWindow(MainProcessor processor) {
        this.processor = processor;
        stage = new Stage();
        pane = new AnchorPane();
        scene = new Scene(pane, 600, 480);
        stage.setScene(scene);
        stage.setTitle("User Management");
        stage.show();

        initLabel();
        initTable();
    }

    private void initLabel() {
        Label username_label = new Label("User Name");
        username_label.setLayoutX(20);
        username_label.setLayoutY(80);
        pane.getChildren().add(username_label);

        Label password_label = new Label("Password");
        password_label.setLayoutX(20);
        password_label.setLayoutY(180);
        pane.getChildren().add(password_label);
    }

    private void initTable() {
        table = new TableView<>();
        table.setLayoutX(200);
        table.setLayoutY(15);
        table.setPrefWidth(380);
        table.setPrefHeight(300);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        pane.getChildren().add(table);

        String[] colNames = {"ID", "User Name", "Password", "Type"};
//        String[] properties = {"id, "username", "password"};
        for (int i = 0; i < colNames.length; i++) {
            String colName = colNames[i];
            TableColumn<UserTableEntry, String> column = new TableColumn<>(colName);
            column.setSortable(false);
            column.setPrefWidth(118);
            column.setStyle("-fx-alignment: CENTER;");
//            column.setCellValueFactory(new PropertyValueFactory<>(properties[i]));
            table.getColumns().add(column);
        }
    }
}
