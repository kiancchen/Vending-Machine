package VendingMachine.Window.ProductManagement;

import VendingMachine.Data.Product;
import VendingMachine.Processor.MainProcessor;
import VendingMachine.Processor.ProductProcessor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.List;
import java.util.Map;

public class ProductManagementWindow {

    private Stage stage;
    private Scene scene;
    private AnchorPane pane;
    private TableView<ProductTableEntry> table;
    private Button addButton;
    private Button changeButton;
    private Button removeButton;
    private TextField nameField;
    private ComboBox<String> categoryCombo;
    private TextField priceField;
    private TextField quantityField;
    private int selectedId;
    private String originCategory;

    public ProductManagementWindow() {
        stage = new Stage();
        pane = new AnchorPane();
        scene = new Scene(pane, 600, 480);
        stage.setScene(scene);
        stage.setTitle("Product Management");
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
        table.setLayoutX(50);
        table.setLayoutY(15);
        table.setPrefWidth(500);
        table.setPrefHeight(300);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        pane.getChildren().add(table);

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
        table.setOnMouseClicked(event -> {
            if (!table.getSelectionModel().isEmpty()) {
                ProductTableEntry selected = table.getSelectionModel().getSelectedItem();
                selectedId = Integer.parseInt(selected.getCode());
                nameField.setText(selected.getName());
                originCategory = selected.getCategory();
                categoryCombo.getSelectionModel().select(originCategory);
                priceField.setText(selected.getPrice());
                quantityField.setText(selected.getQuantity());
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
        categoryCombo = new ComboBox<>();
        categoryCombo.setLayoutX(390);
        categoryCombo.setLayoutY(350);

        for (Product.Category c : Product.Category.values()) {
            categoryCombo.getItems().add(c.toString());
        }

        pane.getChildren().add(categoryCombo);
    }

    private void initTextFields() {
        nameField = new TextField();
        nameField.setLayoutX(90);
        nameField.setLayoutY(350);
        nameField.setPrefWidth(120);
        nameField.setPromptText("Name");

        priceField = new TextField();
        priceField.setLayoutX(240);
        priceField.setLayoutY(350);
        priceField.setPrefWidth(50);
        priceField.setPromptText("Price");

        quantityField = new TextField();
        quantityField.setLayoutX(310);
        quantityField.setLayoutY(350);
        quantityField.setPrefWidth(50);
        quantityField.setPromptText("Quantity");

        pane.getChildren().add(nameField);
        pane.getChildren().add(priceField);
        pane.getChildren().add(quantityField);
    }

    private void setTableData() {
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

    private void addAction() {
        String category = categoryCombo.getSelectionModel().getSelectedItem();

        if (!validateInput()) {
            return;
        }

        try {
            if (MainProcessor.getProductProcessor().addProduct(category, nameField.getText(), Double.parseDouble(priceField.getText()), Integer.parseInt(quantityField.getText()))) {
                alert(Alert.AlertType.INFORMATION, "Successfully add.");
                setTableData();
                selectedId = -1;
            } else {
                alert(Alert.AlertType.INFORMATION, "Product exists.");
            }
        } catch (Exception e) {
            alert(Alert.AlertType.WARNING, "Can not add product.");
        }
    }

    private void removeAction() {
        if (table.getSelectionModel().getSelectedItem() == null) {
            alert(Alert.AlertType.WARNING, "You don't select any user.");
            return;
        }

        String category = table.getSelectionModel().getSelectedItem().getCategory();
        int code = Integer.parseInt(table.getSelectionModel().getSelectedItem().getCode());
        try {
            if (MainProcessor.getProductProcessor().removeProduct(category, code)) {
                alert(Alert.AlertType.INFORMATION, "Successfully removed");
                setTableData();
            }
        } catch (Exception e) {
            alert(Alert.AlertType.WARNING, "Product selected is not existed");
        }
    }

    private void changeAction() {
        ProductProcessor productProcessor = MainProcessor.getProductProcessor();
        if (selectedId < 0) {
            alert(Alert.AlertType.WARNING, "You don't select any product.");
            return;
        } else if (!validateInput()) {
            return;
        }

        String category = categoryCombo.getSelectionModel().getSelectedItem();

        try {
            productProcessor.setProductName(category, selectedId, nameField.getText());
            productProcessor.setProductCategory(originCategory, selectedId, category);
            productProcessor.setProductPrice(category, selectedId, Double.parseDouble(priceField.getText()));
            productProcessor.setProductQuantity(category, selectedId, Integer.parseInt(quantityField.getText()));
            alert(Alert.AlertType.WARNING, "Change successfully.");
        } catch (Exception e) {
            alert(Alert.AlertType.WARNING, "Change failed.");
        }

        nameField.setText("");
        priceField.setText("");
        quantityField.setText("");
        categoryCombo.getSelectionModel().clearSelection();
        setTableData();
        selectedId = -1;
    }

    private boolean validateInput() {
        if (nameField.getText().trim().isEmpty()) {
            alert(Alert.AlertType.WARNING, "Product name needed");
            return false;
        } else if (priceField.getText().trim().isEmpty()) {
            alert(Alert.AlertType.WARNING, "Product price needed");
            return false;
        } else if (quantityField.getText().trim().isEmpty()) {
            alert(Alert.AlertType.WARNING, "Product quantity needed");
            return false;
        } else if (categoryCombo.getSelectionModel().isEmpty()) {
            alert(Alert.AlertType.WARNING, "Product category needed.");
            return false;
        }
        return true;
    }

    private void alert(Alert.AlertType warning, String s) {
        Alert alert = new Alert(warning, s);
        alert.show();
    }
}
