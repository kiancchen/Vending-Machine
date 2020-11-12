package VendingMachine.Window;

import VendingMachine.Data.User;
import VendingMachine.Processor.MainProcessor;
import VendingMachine.Processor.UserProcessor;
import VendingMachine.Window.CashManagement.CashManagementWindow;
import VendingMachine.Window.CheckoutManagement.CheckoutWindow;
import VendingMachine.Window.ProductManagement.ProductManagementWindow;
import VendingMachine.Window.ProductManagement.ProductTable;
import VendingMachine.Window.ProductManagement.ProductTableEntry;
import VendingMachine.Window.UserManagement.UserManagementWindow;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class MainWindow {
    private Scene scene;
    private AnchorPane pane;
    private Button accountBtn;
    private Button userManagementBtn;
    private Button cashManagementBtn;
    private Button productManageBtn;
    private Text currentUserInfo;
    private ProductTable productTable;
    private Text selectedItemText;
    private ComboBox<Integer> selectedQuantityCombo;

    private Button addToCartBtn;
    private Button checkout;
    private TableView<ProductTableEntry> purchaseTable;
    private UserProcessor userProcessor = MainProcessor.getUserProcessor();
    private List<ProductTableEntry> purchaseList = new ArrayList<>();
    private Button removePurchase;
    private ComboBox<Integer> removeQuantityCombo;

    public MainWindow() {
        pane = new AnchorPane();
        scene = new Scene(pane, 1150, 500);
        initButtons();
        initBtnActions();
        initLabels();
        initText();
        updateCurrencyUserInfo();
        initPurchaseNodes();
        initPurchaseTable();

        this.productTable = new ProductTable(50, 50, 500, 350);
        pane.getChildren().add(productTable.getTable());
        setProductTableAction();
    }

    public void updateCurrencyUserInfo() {
        UserProcessor userProcessor = MainProcessor.getUserProcessor();
        currentUserInfo.setText("Current User with type: "
                + userProcessor.getCurrentUser().getUsername()
                + "   "
                + userProcessor.getCurrentUser().getType()
        );
    }

    public void changeAccountButtonText(String text) {
        this.accountBtn.setText(text);
    }

    private void initLabels() {
        Label productTableLabel = new Label("Product Table");
        productTableLabel.setLayoutX(250);
        productTableLabel.setLayoutY(30);

        Label shoppingCartLabel = new Label("Shopping Cart");
        shoppingCartLabel.setLayoutX(810);
        shoppingCartLabel.setLayoutY(30);

        pane.getChildren().addAll(productTableLabel, shoppingCartLabel);
    }

    private void initButtons() {
        accountBtn = new Button();
        userManagementBtn = new Button();
        cashManagementBtn = new Button();
        productManageBtn = new Button();

        Button[] buttons = {accountBtn, userManagementBtn, cashManagementBtn, productManageBtn};
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
        cashManagementBtn.setOnAction(event -> {
            if (userProcessor.getCurrentUser().getPermission(User.Permission.MANAGE_CASH)) {
                new CashManagementWindow();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "You don't have the permission " +
                        "to do this action.");
                alert.show();
            }
        });
        productManageBtn.setOnAction(event -> {
            new ProductManagementWindow(this.productTable);
//            if (userProcessor.getCurrentUser().getPermission(User.Permission.MANAGE_ITEM)) {
//                new ProductManagementWindow(this.productTable);
//            } else {
//                Alert alert = new Alert(Alert.AlertType.WARNING, "You don't have the permission " +
//                        "to do this action.");
//                alert.show();
//            }
        });
    }

    private void initText() {
        currentUserInfo = new Text();
        currentUserInfo.setLayoutX(10);
        currentUserInfo.setLayoutY(20);
        this.pane.getChildren().add(currentUserInfo);
    }

    private void setProductTableAction() {
        this.productTable.setTableAction(event -> {
            selectedQuantityCombo.getItems().clear();
            if (!productTable.selectIsEmpty()) {
                ProductTableEntry selected = productTable.getSelectedItem();
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

    private void initPurchaseTable() {
        purchaseTable = new TableView<>();
        purchaseTable.setLayoutX(600);
        purchaseTable.setLayoutY(50);
        purchaseTable.setPrefWidth(500);
        purchaseTable.setPrefHeight(350);
        purchaseTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        pane.getChildren().add(purchaseTable);

        //create table
        String[] colNames = {"Ccategory", "Code", "Name", "Pice($)", "Quantity"};
        String[] properties = {"category", "code", "name", "price", "quantity"};
        for (int i = 0; i < colNames.length; i++) {
            String colName = colNames[i];
            TableColumn<ProductTableEntry, String> column = new TableColumn<>(colName);
            column.setSortable(false);
            column.setPrefWidth(118);
            column.setStyle("-fx-alignment: CENTER;");
            column.setCellValueFactory(new PropertyValueFactory<>(properties[i]));
            purchaseTable.getColumns().add(column);
        }

        purchaseTable.setOnMouseClicked(event -> {
            removeQuantityCombo.getItems().clear();
            if(!purchaseTable.getSelectionModel().isEmpty()) {
                ProductTableEntry selected = purchaseTable.getSelectionModel().getSelectedItem();
                for (int i = 1; i <= Integer.parseInt(selected.getQuantity()); i++) {
                    removeQuantityCombo.getItems().add(i);
                }
                removeQuantityCombo.getSelectionModel().select(0);
            } else {
                removeQuantityCombo.setPromptText("Quantity");
            }
        });
    }

    private void initPurchaseNodes() {
        selectedItemText = new Text();
        selectedItemText.setLayoutX(50);
        selectedItemText.setLayoutY(430);
        selectedItemText.setFont(Font.font(16));
        selectedItemText.setText("Selected Item");
        pane.getChildren().add(selectedItemText);

        selectedQuantityCombo = new ComboBox<>();
        selectedQuantityCombo.setLayoutX(170);
        selectedQuantityCombo.setLayoutY(410);
        selectedQuantityCombo.setPrefWidth(120);
        selectedQuantityCombo.setPromptText("Quantity");
        pane.getChildren().add(selectedQuantityCombo);

        removeQuantityCombo = new ComboBox<>();
        removeQuantityCombo.setLayoutX(730);
        removeQuantityCombo.setLayoutY(410);
        removeQuantityCombo.setPrefWidth(120);
        removeQuantityCombo.setPromptText("Quantity");
        pane.getChildren().add(removeQuantityCombo);

        addToCartBtn = new Button();
        addToCartBtn.setLayoutX(300);
        addToCartBtn.setLayoutY(410);
        addToCartBtn.setPrefWidth(120);
        addToCartBtn.setText("Add to Cart");
        addToCartBtn.setOnAction(event -> addToCart());
        pane.getChildren().add(addToCartBtn);

        checkout = new Button();
        checkout.setLayoutX(430);
        checkout.setLayoutY(410);
        checkout.setPrefWidth(120);
        checkout.setText("Checkout");
        checkout.setOnAction(event -> new CheckoutWindow(purchaseList));
        pane.getChildren().add(checkout);

        removePurchase = new Button();
        removePurchase.setLayoutX(600);
        removePurchase.setLayoutY(410);
        removePurchase.setPrefWidth(120);
        removePurchase.setText("Remove");
        removePurchase.setOnAction(event -> removeFromCart());
        pane.getChildren().add(removePurchase);
    }

    private void removeFromCart() {
        if(purchaseTable.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please select an item.");
            alert.show();
            return;
        }

        String name = purchaseTable.getSelectionModel().getSelectedItem().getName();
        String category = purchaseTable.getSelectionModel().getSelectedItem().getCategory();
        String code = purchaseTable.getSelectionModel().getSelectedItem().getCode();
        String price = purchaseTable.getSelectionModel().getSelectedItem().getPrice();

        if(removeQuantityCombo.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Not enough quantity.");
            alert.show();
            return;
        }

        int rmQuant = removeQuantityCombo.getSelectionModel().getSelectedItem();
        int purQuant = 0;
        int index = 0;
        for(int i = 0; i < purchaseList.size(); i++) {
            if(name.equals(purchaseList.get(i).getName())) {
                index = i;
                purQuant = Integer.parseInt(purchaseList.get(i).getQuantity());
                break;
            }
        }

        try {
            int stock = MainProcessor.getProductProcessor().getProduct(category, Integer.parseInt(code)).getQuantity();
            MainProcessor.getProductProcessor().setProductQuantity(category, Integer.parseInt(code), stock + rmQuant);
        } catch (Exception e ) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Not a valid quantity.");
            alert.show();
        }

        purQuant -= rmQuant;
        String quantity = Integer.toString(purQuant);
        ProductTableEntry n = new ProductTableEntry(code, name, category, price, quantity);

        purchaseList.set(index, n);
        userProcessor.getCurrentUser().setItemInCart(category, Integer.parseInt(code), purQuant);

        for(int i = 0; i < purchaseList.size(); i++) {
            if(Integer.parseInt(purchaseList.get(i).getQuantity()) < 1) {
                purchaseList.remove(i);
            }
        }

        purchaseTable.getItems().clear();
        for (ProductTableEntry productTableEntry : purchaseList) {
            purchaseTable.getItems().add(productTableEntry);
        }
        removeQuantityCombo.getItems().clear();
        this.productTable.updateTableData();
    }

    private void addToCart() {
        if(this.productTable.getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please select an item.");
            alert.show();
            return;
        }

        int stock = Integer.parseInt(this.productTable.getSelectedItem().getQuantity());

        String name = this.productTable.getSelectedItem().getName();
        String category = this.productTable.getSelectedItem().getCategory();
        String code = this.productTable.getSelectedItem().getCode();
        String price = this.productTable.getSelectedItem().getPrice();

        if(selectedQuantityCombo.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Not enough quantity.");
            alert.show();
            return;
        }
        int q = selectedQuantityCombo.getSelectionModel().getSelectedItem();
        int purQuant = 0;
        int index = -1;
        for(int i = 0; i < purchaseList.size(); i++) {
            if(name.equals(purchaseList.get(i).getName())) {
                index = i;
                purQuant = Integer.parseInt(purchaseList.get(i).getQuantity());
                break;
            }
        }

        try {
            MainProcessor.getProductProcessor().setProductQuantity(category, Integer.parseInt(code), stock - q);
        } catch (Exception e ) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Not a valid quantity.");
            alert.show();
        }
        purQuant += q;
        String quantity = Integer.toString(purQuant);
        ProductTableEntry n = new ProductTableEntry(code, name, category, price, quantity);
        if(index > -1) {
            purchaseList.set(index, n);
            userProcessor.getCurrentUser().setItemInCart(category, Integer.parseInt(code), purQuant);
        } else {
            purchaseList.add(n);
            userProcessor.getCurrentUser().addToCart(category, Integer.parseInt(code), purQuant);
        }

        purchaseTable.getItems().clear();
        for (ProductTableEntry productTableEntry : purchaseList) {
            purchaseTable.getItems().add(productTableEntry);
        }
        selectedQuantityCombo.getItems().clear();
        this.productTable.updateTableData();
    }

    public Scene getScene() {
        return scene;
    }
}
