package VendingMachine.Window.ProductManagement;

import VendingMachine.Data.Product;
import VendingMachine.Processor.ProductProcessor;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.Map;

public class ProductTable {
    private TableView<ProductTableEntry> table;

    public ProductTable(int x, int y, int width, int height) {
        initTable(x, y, width, height);
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
            String quantity = Integer.toString(v.getStock());
            String category = v.getCategory().toString();
            table.getItems().add(new ProductTableEntry(code, name, category, price, quantity, v.getId()));
        });
    }

    public boolean selectIsEmpty() {
        return this.table.getSelectionModel().isEmpty();
    }

    private void initTable(int x, int y, int width, int height) {
        table = new TableView<>();
        table.setLayoutX(x);
        table.setLayoutY(y);
        table.setPrefWidth(width);
        table.setPrefHeight(height);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // create table
        String[] colNames = {"Category", "Name", "Code", "Price ($)", "Quantity"};
        String[] properties = {"category", "name", "code", "price", "quantity"};
        for (int i = 0; i < colNames.length; i++) {
            String colName = colNames[i];
            TableColumn<ProductTableEntry, String> column = new TableColumn<>(colName);
            column.setSortable(false);
            column.setPrefWidth(118);
            column.setStyle("-fx-alignment: CENTER;");
            column.setCellValueFactory(new PropertyValueFactory<>(properties[i]));
            table.getColumns().add(column);
        }
        updateTableData();
    }

    public void setTableAction(EventHandler<MouseEvent> eventHandler) {
        table.setOnMouseClicked(eventHandler);
    }

    public ProductTableEntry getSelectedItem() {
        return this.table.getSelectionModel().getSelectedItem();
    }

    public TableView<ProductTableEntry> getTable() {
        return table;
    }
}
