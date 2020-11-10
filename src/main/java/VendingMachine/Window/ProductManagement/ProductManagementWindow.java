package VendingMachine.Window.ProductManagement;

import VendingMachine.Data.Product;
import VendingMachine.Processor.MainProcessor;
import VendingMachine.Processor.ProductProcessor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ProductManagementWindow {

    private Stage stage;
    private Scene scene;
    private AnchorPane pane;
    private Button addButton;
    private Button changeButton;
    private Button removeButton;
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
        initTextFields();
        selectedId = -1;
    }

    private void setTableAction() {
        this.productTable.setTableAction(event -> {
            if (!this.productTable.selectIsEmpty()) {
                ProductTableEntry selected = this.productTable.getSelectedItem();
                selectedId = Integer.parseInt(selected.getCode());
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
            button.setLayoutX(90 + 150 * i);
            button.setLayoutY(450);
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
        categoryCombo.setLayoutY(400);

        for (Product.Category c : Product.Category.values()) {
            categoryCombo.getItems().add(c.toString());
        }

        pane.getChildren().add(categoryCombo);
    }

    private void initTextFields() {
        nameField = new TextField();
        nameField.setLayoutX(90);
        nameField.setLayoutY(400);
        nameField.setPrefWidth(120);
        nameField.setPromptText("Name");

        priceField = new TextField();
        priceField.setLayoutX(240);
        priceField.setLayoutY(400);
        priceField.setPrefWidth(50);
        priceField.setPromptText("Price");

        quantityField = new TextField();
        quantityField.setLayoutX(310);
        quantityField.setLayoutY(400);
        quantityField.setPrefWidth(50);
        quantityField.setPromptText("Stock");

        pane.getChildren().add(nameField);
        pane.getChildren().add(priceField);
        pane.getChildren().add(quantityField);
    }


    private void addAction() {
        String category = categoryCombo.getSelectionModel().getSelectedItem();

        if (!validateInput()) {
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
        }

        String category = categoryCombo.getSelectionModel().getSelectedItem();

        try {
            productProcessor.setProductName(category, selectedId, nameField.getText());
            productProcessor.setProductCategory(originCategory, selectedId, category);
            productProcessor.setProductPrice(category, selectedId, Double.parseDouble(priceField.getText()));
            productProcessor.setProductQuantity(category, selectedId, Integer.parseInt(quantityField.getText()));
            alert(Alert.AlertType.INFORMATION, "Change successfully.");
        } catch (Exception e) {
            alert(Alert.AlertType.WARNING, "Change failed.");
        }

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
