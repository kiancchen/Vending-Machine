package VendingMachine.Window;

import VendingMachine.Data.Product;
import VendingMachine.Data.User;
import VendingMachine.Processor.MainProcessor;
import VendingMachine.Processor.UserProcessor;
import VendingMachine.Window.CashManagement.CashManagementWindow;
import VendingMachine.Window.CashManagement.CashTableEntry;
import VendingMachine.Window.UserManagement.UserManagementWindow;
import VendingMachine.Window.UserManagement.UserTableEntry;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MainWindow {
    private Scene scene;
    private AnchorPane pane;
    private Button accountBtn;
    private Button userManagementBtn;
    private Button cashierManageBtn;
    private Text currentUserInfo;
    private TableView<ProductTableEntry> allProductTable;

    public MainWindow() {
        pane = new AnchorPane();
        scene = new Scene(pane, 800, 480);
        initButtons();
        initBtnActions();
        initText();
        updateCurrencyUserInfo();
        initAllProductTable();
    }

    private void initAllProductTable() {
        allProductTable = new TableView<>();
        allProductTable.setLayoutX(10);
        allProductTable.setLayoutY(40);
        allProductTable.setPrefWidth(500);
        allProductTable.setPrefHeight(340);
        allProductTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        pane.getChildren().add(allProductTable);

        //create table
        String[] colNames = {"CATEGORY","CODE", "NAME", "PRICE", "Qty."};
        String[] properties = {"category", "code", "name", "price", "quantity"};
        for (int i = 0; i < colNames.length; i++) {
            String colName = colNames[i];
            TableColumn<ProductTableEntry, String> column = new TableColumn<>(colName);
            column.setSortable(false);
            column.setPrefWidth(118);
            column.setStyle("-fx-alignment: CENTER;");
            column.setCellValueFactory(new PropertyValueFactory<>(properties[i]));
            allProductTable.getColumns().add(column);
        }

        setAllProductTableData();
    }

    private void setAllProductTableData() {

        //set data to table
        allProductTable.getItems().clear();
        Map<Product.Category, List<Product>> productMap = MainProcessor.getProductProcessor().getProductMap();

//        List<String> categories = new ArrayList<>();
//        List<String> names = new ArrayList<>();
//        List<String> codes = new ArrayList<>();
//        List<String> quantities = new ArrayList<>();
//        List<String> prices = new ArrayList<>();

        for (List<Product> products: productMap.values()){
            for (Product product : products) {
                allProductTable.getItems().add(new ProductTableEntry(
                        Integer.toString(product.getCode()),
                        product.getCategory().name(), product.getName(),
                        Double.toString(product.getPrice()),
                        Integer.toString(product.getQuantity())));

//                categories.add(products.get(i).getCategory().name());
//                names.add(products.get(i).getName());
//                codes.add(Integer.toString(products.get(i).getCode()));
//                quantities.add(Integer.toString(products.get(i).getQuantity()));
//                prices.add(Double.toString(products.get(i).getPrice()));
            }
//            for (int i = 0; i < products.size(); i++) {
//                allProductTable.getItems().add(new ProductTableEntry(codes.get(i),
//                        categories.get(i), names.get(i),
//                        prices.get(i), quantities.get(i)));
//            }
        }


    }

    private void initButtons() {
        accountBtn = new Button();
        userManagementBtn = new Button();
        cashierManageBtn = new Button();

        Button[] buttons = {accountBtn, userManagementBtn, cashierManageBtn};
        String[] texts = {"Account", "Manage User", "Manage Cash"};

        for (int i = 0; i < buttons.length; i++) {
            Button button = buttons[i];
            button.setLayoutX(20 + 150 * i);
            button.setLayoutY(400);
            button.setPrefWidth(100);
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


    }

    private void initText() {
        currentUserInfo = new Text();
        currentUserInfo.setLayoutX(10);
        currentUserInfo.setLayoutY(20);
        this.pane.getChildren().add(currentUserInfo);
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

    public Scene getScene() {
        return scene;
    }
}
