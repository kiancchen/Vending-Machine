package VendingMachine.Window.ProductManagement;

import VendingMachine.Data.Product;
import VendingMachine.Processor.MainProcessor;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.util.List;
import java.util.Map;

public class ProductTable {
    private TableView<ProductTableEntry> table;

    public ProductTable(int x, int y, int width, int height) {
        initTable(x, y, width, height);
    }

    public void updateTableData() {
        // set data to table
        table.getItems().clear();
        Map<Product.Category, List<Product>> productMap = MainProcessor.getProductProcessor().getProductMap();
        for (Map.Entry<Product.Category, List<Product>> entry : productMap.entrySet()) {
            String category = entry.getKey().toString();
            for (Product product : entry.getValue()) {
                String code = Integer.toString(product.getCode());
                String name = product.getName();
                String price = Double.toString(product.getPrice());
                String quantity = Integer.toString(product.getQuantity());
                table.getItems().add(new ProductTableEntry(code, name, category, price, quantity));
            }
        }
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
        String[] colNames = {"Code", "Name", "Category", "Price ($)", "Quantity"};
        String[] properties = {"code", "name", "category", "price", "quantity"};
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
