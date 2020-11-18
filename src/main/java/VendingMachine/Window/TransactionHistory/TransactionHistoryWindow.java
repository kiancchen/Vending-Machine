package VendingMachine.Window.TransactionHistory;

import VendingMachine.Data.Transaction;
import VendingMachine.Processor.UserProcessor;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.time.format.DateTimeFormatter;

public class TransactionHistoryWindow {

    private Stage stage;
    private Scene scene;
    private AnchorPane pane;
    private TableView<TransactionHistoryTableEntry> table;
    private UserProcessor userProcessor;

    public TransactionHistoryWindow() {
        stage = new Stage();
        pane = new AnchorPane();
        scene = new Scene(pane, 600, 480);
        stage.setScene(scene);
        stage.setTitle("Transaction History");
        stage.show();
        userProcessor = UserProcessor.getInstance();
        initTable();
    }

    private void initTable() {
        table = new TableView<>();
        table.setLayoutX(50);
        table.setLayoutY(15);
        table.setPrefWidth(500);
        table.setPrefHeight(440);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        pane.getChildren().add(table);

        String[] colNames = {"Time", "Item Sold", "Money Paid", "Changes", "Payment Method"};
        String[] properties = {"time", "itemSold", "moneyPaid", "changes", "paymentMethod"};
        for (int i = 0; i < colNames.length; i++) {
            String colName = colNames[i];
            TableColumn<TransactionHistoryTableEntry, String> column = new TableColumn<>(colName);
            column.setSortable(false);
            column.setPrefWidth(100);
            column.setStyle("-fx-alignment: CENTER;");
            column.setCellValueFactory(new PropertyValueFactory<>(properties[i]));
            table.getColumns().add(column);
        }
        setTableData();
    }

    private void setTableData() {
        table.getItems().clear();
        for (Transaction transaction : Transaction.getTransactionList()) {
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            transaction.getShoppingList().forEach((k, v) -> {
                String itemSold = k.getName();
                table.getItems().add(new TransactionHistoryTableEntry(transaction.getDate().format(fmt),
                        itemSold, Double.toString(transaction.getPaidAmount()),
                        Double.toString(userProcessor.getCurrentUser().getChange()),
                        transaction.getStatus().toString()));

            });

        }
    }
}
