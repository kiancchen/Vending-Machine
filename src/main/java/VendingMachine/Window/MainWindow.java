package VendingMachine.Window;

import VendingMachine.Data.User;
import VendingMachine.Processor.UserProcessor;
import VendingMachine.Window.CashManagement.CashManagementWindow;
import VendingMachine.Window.CheckoutManagement.CheckoutWindow;
import VendingMachine.Window.ProductManagement.ProductManagementWindow;
import VendingMachine.Window.ProductManagement.ProductTable;
import VendingMachine.Window.ProductManagement.ProductTableEntry;
import VendingMachine.Window.UserManagement.UserManagementWindow;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.IOException;

public class MainWindow {
    private static MainWindow mainWindow;
    private Scene scene;
    private AnchorPane pane;
    private Button accountBtn;
    private Button userManagementBtn;
    private Button cashManagementBtn;
    private Button productManageBtn;
    private Button reportBtn;
    private Text currentUserInfo;
    private ProductTable productTable;
    private Text selectedItemText;
    private ComboBox<Integer> selectedQuantityCombo;
    private TableView<ProductTableEntry> shoppingCartTable;
    private UserProcessor userProcessor;
    private ComboBox<Integer> setCartQtyCombo;

    private MainWindow() {
        pane = new AnchorPane();
        scene = new Scene(pane, 1150, 500);
        try {
            userProcessor = UserProcessor.getInstance();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Can't get the user processor.");
            alert.show();
        }
        initButtons();
        initBtnActions();
        initLabels();
        initText();
        updateCurrencyUserInfo();
        initPurchaseNodes();
        initShoppingCart();

        this.productTable = new ProductTable(50, 50, 500, 350);
        pane.getChildren().add(productTable.getTable());
        setProductTableAction();
    }

    public static MainWindow getInstance() {
        if (mainWindow == null) {
            mainWindow = new MainWindow();
        }
        return mainWindow;
    }

    public void updateCurrencyUserInfo() {
        currentUserInfo.setText("Current User with type: "
                + userProcessor.getCurrentUser().getUsername()
                + "   "
                + userProcessor.getCurrentUser().getType()
        );
    }

    public void changeAccountButtonText(String text) {
        this.accountBtn.setText(text);
    }

    public void setShoppingCartData() {
        this.shoppingCartTable.getItems().clear();
        userProcessor.getCurrentUser().getShoppingList().forEach((k, v) ->
                this.shoppingCartTable.getItems().add(new ProductTableEntry(k.getCode(), k.getName(),
                        k.getCategory().toString(), Double.toString(k.getPrice()),
                        Integer.toString(v), k.getId())));
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

        reportBtn = new Button("Generate Report");
        reportBtn.setLayoutX(960);
        reportBtn.setLayoutY(10);
        reportBtn.setPrefWidth(140);
        pane.getChildren().add(reportBtn);
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
            if (userProcessor.getCurrentUser().getPermission(User.Permission.MANAGE_ITEM)) {
                new ProductManagementWindow(this.productTable);
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "You don't have the permission " +
                        "to do this action.");
                alert.show();
            }
        });
        reportBtn.setOnAction(event -> {
            try {
                new ReportWindow();
            } catch (IOException e) {
                e.printStackTrace();
            }
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

    private void initShoppingCart() {
        shoppingCartTable = new TableView<>();
        shoppingCartTable.setLayoutX(600);
        shoppingCartTable.setLayoutY(50);
        shoppingCartTable.setPrefWidth(500);
        shoppingCartTable.setPrefHeight(350);
        shoppingCartTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        pane.getChildren().add(shoppingCartTable);

        //create table
        String[] colNames = {"Category", "Code", "Name", "Pice($)", "Quantity"};
        String[] properties = {"category", "code", "name", "price", "quantity"};
        for (int i = 0; i < colNames.length; i++) {
            String colName = colNames[i];
            TableColumn<ProductTableEntry, String> column = new TableColumn<>(colName);
            column.setSortable(false);
            column.setPrefWidth(118);
            column.setStyle("-fx-alignment: CENTER;");
            column.setCellValueFactory(new PropertyValueFactory<>(properties[i]));
            shoppingCartTable.getColumns().add(column);
        }

        shoppingCartTable.setOnMouseClicked(event -> {
            setCartQtyCombo.getItems().clear();
            if (!shoppingCartTable.getSelectionModel().isEmpty()) {
                ProductTableEntry selected = shoppingCartTable.getSelectionModel().getSelectedItem();
                for (int i = 0; i <= Integer.parseInt(selected.getQuantity()); i++) {
                    setCartQtyCombo.getItems().add(i);
                }
                setCartQtyCombo.getSelectionModel().select(Integer.parseInt(selected.getQuantity()));
            } else {
                setCartQtyCombo.setPromptText("Quantity");
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

        setCartQtyCombo = new ComboBox<>();
        setCartQtyCombo.setLayoutX(750);
        setCartQtyCombo.setLayoutY(410);
        setCartQtyCombo.setPrefWidth(120);
        setCartQtyCombo.setPromptText("Quantity");
        pane.getChildren().add(setCartQtyCombo);

        Button addToCartBtn = new Button();
        addToCartBtn.setLayoutX(300);
        addToCartBtn.setLayoutY(410);
        addToCartBtn.setPrefWidth(120);
        addToCartBtn.setText("Add to Cart");
        addToCartBtn.setOnAction(event -> addToCart());
        pane.getChildren().add(addToCartBtn);

        Button checkout = new Button();
        checkout.setLayoutX(920);
        checkout.setLayoutY(410);
        checkout.setPrefWidth(120);
        checkout.setText("Checkout");
        checkout.setOnAction(event -> new CheckoutWindow());
        pane.getChildren().add(checkout);

        Button setQtyInCartBtn = new Button();
        setQtyInCartBtn.setLayoutX(620);
        setQtyInCartBtn.setLayoutY(410);
        setQtyInCartBtn.setPrefWidth(120);
        setQtyInCartBtn.setText("Set Qty");
        setQtyInCartBtn.setOnAction(event -> setQtyInCart());
        pane.getChildren().add(setQtyInCartBtn);
    }

    private void setQtyInCart() {
        ProductTableEntry selectedItem = shoppingCartTable.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please select an item.");
            alert.show();
            return;
        }

        if (setCartQtyCombo.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Not enough quantity.");
            alert.show();
            return;
        }

        userProcessor.getCurrentUser().setItemInCart(selectedItem.getId(),
                setCartQtyCombo.getSelectionModel().getSelectedItem());

        setCartQtyCombo.getItems().clear();
        this.setShoppingCartData();
        this.productTable.updateTableData();
    }

    private void addToCart() {
        ProductTableEntry selectedItem = this.productTable.getSelectedItem();
        if (selectedItem == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please select an item.");
            alert.show();
            return;
        }

        if (selectedQuantityCombo.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Not enough quantity.");
            alert.show();
            return;
        }

        if (!userProcessor.getCurrentUser().addToCart(selectedItem.getId(),
                selectedQuantityCombo.getSelectionModel().getSelectedItem())) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Not enough quantity.");
            alert.show();
        }

        setShoppingCartData();
        selectedQuantityCombo.getItems().clear();
        this.productTable.updateTableData();
    }

    public Scene getScene() {
        return scene;
    }
}
