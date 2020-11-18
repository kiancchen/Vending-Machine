package VendingMachine.Window.SoldHistory;


import VendingMachine.Data.Product;
import VendingMachine.Processor.ProductProcessor;
import VendingMachine.Window.SoldHistory.SoldTableEntry;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Map;

public class SoldHistoryWindow {
    private Stage stage;
    private Scene scene;
    private AnchorPane pane;
    private TableView<SoldTableEntry> table;
    private ProductProcessor productProcessor;
    public SoldHistoryWindow() {
        stage = new Stage();
        pane = new AnchorPane();
        scene = new Scene(pane, 600, 480);
        stage.setScene(scene);
        stage.setTitle("Sold History");
        stage.show();
        try {
            productProcessor = ProductProcessor.getInstance();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Can't get the cash processor.");
            alert.show();
        }
        initTable();
    }

    private void initTable() {
        table = new TableView<>();
        table.setLayoutX(50);
        table.setLayoutY(40);
        table.setPrefWidth(500);
        table.setPrefHeight(350);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        pane.getChildren().add(table);

        // create table
        String[] colNames = {"Category", "Name", "Code", "Price ($)", "Sold"};
        String[] properties = {"category", "name", "code", "price", "sold"};
        for (int i = 0; i < colNames.length; i++) {
            String colName = colNames[i];
            TableColumn<SoldTableEntry, String> column = new TableColumn<>(colName);
            column.setSortable(false);
            column.setPrefWidth(118);
            column.setStyle("-fx-alignment: CENTER;");
            column.setCellValueFactory(new PropertyValueFactory<>(properties[i]));
            table.getColumns().add(column);
        }
        updateTableData();
    }

    public void updateTableData() {
        // set data to table
        table.getItems().clear();
        Map<Integer, Product> productMap =
                null;
        try {
            productMap = ProductProcessor.getInstance().getProductMap();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Can't get the product processor.");
            alert.show();
            return;
        }
        productMap.forEach((k, v) -> {
            String code = v.getCode();
            String name = v.getName();
            String price = Double.toString(v.getPrice());
            String sold = Integer.toString(v.getSold());
            String category = v.getCategory().toString();
            table.getItems().add(new SoldTableEntry(code, name, category, price, sold, v.getId()));
        });
    }
}

