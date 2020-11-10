package VendingMachine.Window;

import VendingMachine.Data.Product;
import VendingMachine.Data.User;
import VendingMachine.Processor.MainProcessor;
import VendingMachine.Processor.UserProcessor;
import VendingMachine.Window.CashManagement.CashManagementWindow;
import VendingMachine.Window.ProductManagement.ProductManagementWindow;
import VendingMachine.Window.ProductManagement.ProductTableEntry;
import VendingMachine.Window.UserManagement.UserManagementWindow;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.List;
import java.util.Map;

public class MainWindow {
    private Scene scene;
    private AnchorPane pane;
    private Button accountBtn;
    private Button userManagementBtn;
    private Button cashierManageBtn;
    private Button productManageBtn;
    private Text currentUserInfo;
    private TableView<ProductTableEntry> productTable;
    private Text selectedItemText;
    private ComboBox<Integer> selectedQuantityCombo;

    public MainWindow() {
        pane = new AnchorPane();
        scene = new Scene(pane, 800, 500);
        initButtons();
        initBtnActions();
        initText();
        updateCurrencyUserInfo();
        initProductTable();
        initPurchaseNodes();
    }

    public void updateCurrencyUserInfo() {
        UserProcessor userProcessor = MainProcessor.getUserProcessor();
        currentUserInfo.setText(
                userProcessor.getCurrentUser().getUsername()
                        + "   "
                        + userProcessor.getCurrentUser().getType()
        );
    }

    public void changeAccountButtonText(String text) {
        this.accountBtn.setText(text);
    }

    private void initButtons() {
        accountBtn = new Button();
        userManagementBtn = new Button();
        cashierManageBtn = new Button();
        productManageBtn = new Button();

        Button[] buttons = {accountBtn, userManagementBtn, cashierManageBtn, productManageBtn};
        String[] texts = {"Account", "Manage User", "Manage Cash", "Manage Product"};

        for (int i = 0; i < buttons.length; i++) {
            Button button = buttons[i];
            button.setLayoutX(40 + 130 * i);
            button.setLayoutY(450);
            button.setPrefWidth(120);
            button.setPrefHeight(30);
            button.setText(texts[i]);
            pane.getChildren().add(button);
        }
    }

    private void initBtnActions() {
        UserProcessor userProcessor = MainProcessor.getUserProcessor();
        accountBtn.setOnAction((event -> {
            if (userProcessor.getCurrentUser().getType() == User.UserType.ANONYMOUS) {
                // If the currency user is the Anonymous
                new LoginWindow(this);
            } else {
                userProcessor.logoutUser();
                accountBtn.setText("Account");
                updateCurrencyUserInfo();
            }
        }));
        userManagementBtn.setOnAction(event -> {
            if (userProcessor.getCurrentUser().getPermission(User.Permission.MANAGE_USER)) {
                new UserManagementWindow();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "You don't have the permission " +
                        "to do this action.");
                alert.show();
            }
        });
        cashierManageBtn.setOnAction(event -> {
            if (userProcessor.getCurrentUser().getPermission(User.Permission.MANAGE_CASH)) {
                new CashManagementWindow();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "You don't have the permission " +
                        "to do this action.");
                alert.show();
            }
        });
        productManageBtn.setOnAction(event -> {
            if (userProcessor.getCurrentUser().getPermission(User.Permission.MANAGE_ITEM)) {
                new ProductManagementWindow();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "You don't have the permission " +
                        "to do this action.");
                alert.show();
            }
        });

    }

    private void initText() {
        currentUserInfo = new Text();
        currentUserInfo.setLayoutX(10);
        currentUserInfo.setLayoutY(20);
        this.pane.getChildren().add(currentUserInfo);
    }

    private void initProductTable() {
        productTable = new TableView<>();
        productTable.setLayoutX(10);
        productTable.setLayoutY(40);
        productTable.setPrefWidth(500);
        productTable.setPrefHeight(340);
        productTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        pane.getChildren().add(productTable);

        //create table
        String[] colNames = {"CATEGORY", "CODE", "NAME", "PRICE", "STOCK"};
        String[] properties = {"category", "code", "name", "price", "quantity"};
        for (int i = 0; i < colNames.length; i++) {
            String colName = colNames[i];
            TableColumn<ProductTableEntry, String> column = new TableColumn<>(colName);
            column.setSortable(false);
            column.setPrefWidth(118);
            column.setStyle("-fx-alignment: CENTER;");
            column.setCellValueFactory(new PropertyValueFactory<>(properties[i]));
            productTable.getColumns().add(column);
        }
        setProductTableData();

        productTable.setOnMouseClicked(event -> {
            selectedQuantityCombo.getItems().clear();
            if (!productTable.getSelectionModel().isEmpty()) {
                ProductTableEntry selected = productTable.getSelectionModel().getSelectedItem();
                selectedItemText.setText(selected.getName());
                for (int i = 1; i <= Integer.parseInt(selected.getQuantity()); i++) {
                    selectedQuantityCombo.getItems().add(i);
                }
                selectedQuantityCombo.getSelectionModel().select(0);
            } else {
                selectedItemText.setText("Selected Item");
                selectedQuantityCombo.setPromptText("Quantity");
            }
        });
    }

    private void initPurchaseNodes() {
        selectedItemText = new Text();
        selectedItemText.setLayoutX(50);
        selectedItemText.setLayoutY(420);
        selectedItemText.setFont(Font.font(16));
        selectedItemText.setText("Selected Item");
        pane.getChildren().add(selectedItemText);

        selectedQuantityCombo = new ComboBox<>();
        selectedQuantityCombo.setLayoutX(170);
        selectedQuantityCombo.setLayoutY(400);
        selectedQuantityCombo.setPrefWidth(120);
        selectedQuantityCombo.setPromptText("Quantity");
        pane.getChildren().add(selectedQuantityCombo);

        Button addToCartBtn = new Button();
        addToCartBtn.setLayoutX(300);
        addToCartBtn.setLayoutY(400);
        addToCartBtn.setPrefWidth(120);
        addToCartBtn.setText("Add to Cart");
        pane.getChildren().add(addToCartBtn);

        Button checkout = new Button();
        checkout.setLayoutX(430);
        checkout.setLayoutY(400);
        checkout.setPrefWidth(120);
        checkout.setText("Checkout");
        pane.getChildren().add(checkout);
    }

    private void setProductTableData() {
        //set data to table
        productTable.getItems().clear();
        Map<Product.Category, List<Product>> productMap = MainProcessor.getProductProcessor().getProductMap();

        for (List<Product> products : productMap.values()) {
            for (Product product : products) {
                productTable.getItems().add(new ProductTableEntry(
                        Integer.toString(product.getCode()),
                        product.getCategory().name(), product.getName(),
                        Double.toString(product.getPrice()),
                        Integer.toString(product.getQuantity())));

            }
        }


    }

    public Scene getScene() {
        return scene;
    }
}
