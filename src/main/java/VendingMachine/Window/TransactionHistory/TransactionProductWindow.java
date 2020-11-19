package VendingMachine.Window.TransactionHistory;

import VendingMachine.Data.Product;
import VendingMachine.Data.Transaction;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.Map;

public class TransactionProductWindow {

    private Stage stage;
    private Scene scene;
    private AnchorPane pane;
    private TableView<TransactionProductTableEntry> table;

    public TransactionProductWindow() {
        stage = new Stage();
        pane = new AnchorPane();
        scene = new Scene(pane, 600, 480);
        stage.setScene(scene);
        stage.setTitle("Transaction Product History");
        stage.show();
        initTable();
    }

    private void initTable() {
        table = new TableView<>();
        table.setLayoutX(50);
        table.setLayoutY(15);
        table.setPrefWidth(500);
        table.setPrefHeight(400);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        pane.getChildren().add(table);

        String[] colNames = {"Name", "Category", "Price", "Quantity"};
        String[] properties = {"name", "category", "price", "quantity"};
        for (int i = 0; i < colNames.length; i++) {
            String colName = colNames[i];
            TableColumn<TransactionProductTableEntry, String> column = new TableColumn<>(colName);
            column.setSortable(false);
            column.setPrefWidth(100);
            column.setStyle("-fx-alignment: CENTER;");
            column.setCellValueFactory(new PropertyValueFactory<>(properties[i]));
            table.getColumns().add(column);
        }
        setTableDate();
    }

    public void setTableDate() {
        table.getItems().clear();
        for (Transaction transaction : Transaction.getTransactionList()) {
            transaction.getShoppingList().forEach((k, v) -> {
                table.getItems().add(new TransactionProductTableEntry(k.getName(),k.getCategory().toString(),
                        Double.toString(k.getPrice()),Integer.toString(k.getSold())));
            });
        }
    }
}
