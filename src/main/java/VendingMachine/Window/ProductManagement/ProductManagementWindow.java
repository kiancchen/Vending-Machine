package VendingMachine.Window.ProductManagement;

import VendingMachine.Data.Product;
import VendingMachine.Processor.MainProcessor;
import VendingMachine.Processor.ProductProcessor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ProductManagementWindow {

    private Stage stage;
    private Scene scene;
    private AnchorPane pane;
    private Button addButton;
    private Button changeButton;
    private Button removeButton;
    private TextField codeField;
    private TextField nameField;
    private ComboBox<String> categoryCombo;
    private TextField priceField;
    private TextField quantityField;
    private int selectedId;
    private String originCategory;
    private ProductTable productTable;
    private ProductTable mainTable;

    public ProductManagementWindow(ProductTable mainTable) {
        stage = new Stage();
        pane = new AnchorPane();
        scene = new Scene(pane, 600, 500);
        stage.setScene(scene);
        stage.setTitle("Product Management");
        stage.show();
        this.mainTable = mainTable;
        this.productTable = new ProductTable();
        pane.getChildren().add(productTable.getTable());
        setTableAction();
        initButton();
        initButtonActions();
        initCombobox();
        initLabels();
        initTextFields();
        selectedId = -1;
    }

    private void setTableAction() {
        this.productTable.setTableAction(event -> {
            if (!this.productTable.selectIsEmpty()) {
                ProductTableEntry selected = this.productTable.getSelectedItem();
                selectedId = Integer.parseInt(selected.getCode());
                codeField.setText(selected.getCode());
                nameField.setText(selected.getName());
                originCategory = selected.getCategory();
                categoryCombo.getSelectionModel().select(originCategory);
                priceField.setText(selected.getPrice());
                quantityField.setText(selected.getQuantity());
            }
        });
    }


    private void initButton() {
        addButton = new Button();
        changeButton = new Button();
        removeButton = new Button();

        Button[] buttons = {addButton, changeButton, removeButton};
        String[] texts = {"Add", "Change", "Remove"};

        for (int i = 0; i < buttons.length; i++) {
            Button button = buttons[i];
            button.setLayoutX(60 + 160 * i);
            button.setLayoutY(450);
            button.setPrefWidth(130);
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
        categoryCombo.setLayoutX(400);
        categoryCombo.setLayoutY(410);
        categoryCombo.setPrefWidth(110);

        for (Product.Category c : Product.Category.values()) {
            categoryCombo.getItems().add(c.toString());
        }

        pane.getChildren().add(categoryCombo);
    }

    private void initTextFields() {
        codeField = new TextField();
        codeField.setLayoutX(60);
        codeField.setLayoutY(410);
        codeField.setPrefWidth(60);

        nameField = new TextField();
        nameField.setLayoutX(130);
        nameField.setLayoutY(410);
        nameField.setPrefWidth(120);

        priceField = new TextField();
        priceField.setLayoutX(260);
        priceField.setLayoutY(410);
        priceField.setPrefWidth(60);

        quantityField = new TextField();
        quantityField.setLayoutX(330);
        quantityField.setLayoutY(410);
        quantityField.setPrefWidth(60);

        pane.getChildren().addAll(codeField, nameField, priceField, quantityField);
    }

    private void initLabels() {
        Label codeLabel = new Label("Code");
        codeLabel.setLayoutX(60);
        codeLabel.setLayoutY(390);

        Label nameLabel = new Label("Name");
        nameLabel.setLayoutX(130);
        nameLabel.setLayoutY(390);

        Label priceLabel = new Label("Price");
        priceLabel.setLayoutX(260);
        priceLabel.setLayoutY(390);

        Label quantityLabel = new Label("Quantity");
        quantityLabel.setLayoutX(330);
        quantityLabel.setLayoutY(390);

        Label categoryLabel = new Label("Category");
        categoryLabel.setLayoutX(400);
        categoryLabel.setLayoutY(390);

        pane.getChildren().addAll(codeLabel, nameLabel, priceLabel, quantityLabel, categoryLabel);
    }


    private void addAction() {
        String category = categoryCombo.getSelectionModel().getSelectedItem();

        if (!validateInput()) {
            return;
        } else if (Integer.parseInt(quantityField.getText()) > 15) {
            alert(Alert.AlertType.WARNING, "Quantity exceed.");
            return;
        }

        try {
            if (MainProcessor.getProductProcessor().addProduct(category, nameField.getText(), Double.parseDouble(priceField.getText()), Integer.parseInt(quantityField.getText()))) {
                alert(Alert.AlertType.INFORMATION, "Successfully add.");
                this.productTable.updateTableData();
                this.mainTable.updateTableData();
                selectedId = -1;
            } else {
                alert(Alert.AlertType.WARNING, "Product exists.");
            }
        } catch (Exception e) {
            alert(Alert.AlertType.WARNING, "Fail to add the product.");
        }
    }

    private void removeAction() {
        ProductTableEntry selectedItem = productTable.getSelectedItem();
        if (selectedItem == null) {
            alert(Alert.AlertType.WARNING, "You don't select any product.");
            return;
        }

        String category = selectedItem.getCategory();
        int code = Integer.parseInt(selectedItem.getCode());
        try {
            if (MainProcessor.getProductProcessor().removeProduct(category, code)) {
                alert(Alert.AlertType.INFORMATION, "Successfully removed");
                this.productTable.updateTableData();
                this.mainTable.updateTableData();
            }
        } catch (Exception e) {
            alert(Alert.AlertType.WARNING, "Product selected does not exist");
        }
    }

    private void changeAction() {
        ProductProcessor productProcessor = MainProcessor.getProductProcessor();
        if (selectedId < 0) {
            alert(Alert.AlertType.WARNING, "You don't select any product.");
            return;
        } else if (!validateInput()) {
            return;
        } else if (Integer.parseInt(quantityField.getText()) > 15) {
            alert(Alert.AlertType.WARNING, "Quantity exceed.");
            return;
        }

        String category = categoryCombo.getSelectionModel().getSelectedItem();

        try {
            productProcessor.setProductCode(category, selectedId, Integer.parseInt(codeField.getText()));
            productProcessor.setProductName(category, selectedId, nameField.getText());
            productProcessor.setProductCategory(originCategory, selectedId, category);
            productProcessor.setProductPrice(category, selectedId, Double.parseDouble(priceField.getText()));
            productProcessor.setProductQuantity(category, selectedId, Integer.parseInt(quantityField.getText()));
            alert(Alert.AlertType.INFORMATION, "Change successfully.");
        } catch (Exception e) {
            alert(Alert.AlertType.WARNING, "Change failed.");
        }

        codeField.setText("");
        nameField.setText("");
        priceField.setText("");
        quantityField.setText("");
        categoryCombo.getSelectionModel().clearSelection();
        this.productTable.updateTableData();
        this.mainTable.updateTableData();
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
