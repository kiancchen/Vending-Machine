package VendingMachine.Window.CheckoutManagement;

import VendingMachine.Processor.MainProcessor;
import VendingMachine.Window.ProductManagement.ProductTableEntry;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.*;
import java.util.stream.Collectors;

public class CashPaymentWindow {
    private Stage stage;
    private Scene scene;
    private AnchorPane pane;
    private TableView<CashTableEntry> table;
    private Map<String, String> userCashMap;
    private CheckoutWindow checkout;
    private ComboBox<String> valueCombo;
    private Button addButton;
    private Button payButton;
    private TextField numberField;

    public CashPaymentWindow(CheckoutWindow checkout) {
        stage = new Stage();
        pane = new AnchorPane();
        scene = new Scene(pane, 480, 600);
        stage.setScene(scene);
        stage.setTitle("Cash Payment");
        stage.show();

        this.userCashMap = new HashMap<>();
        this.checkout = checkout;

        initLabel();
        initTable();
        initButtons();
        initButtonActions();
        initCombobox();
        initTextFields();

    }

    private void initLabel() {
        Label inputNoteLabel = new Label("Please enter amount you pay");
        inputNoteLabel.setLayoutX(130);
        inputNoteLabel.setLayoutY(50);
        inputNoteLabel.setFont(Font.font(20));
        pane.getChildren().add(inputNoteLabel);
    }

    private void initButtons() {
        addButton = new Button();
        payButton = new Button();

        Button[] buttons = {addButton, payButton};
        String[] texts = {"Add", "Pay"};

        for (int i = 0; i < buttons.length; i++) {
            Button button = buttons[i];
            button.setLayoutX(180);
            button.setLayoutY(100 + 420 * i);
            button.setPrefWidth(120);
            button.setPrefHeight(30);
            button.setText(texts[i]);
            pane.getChildren().add(button);
        }
    }

    private void initCombobox() {
        valueCombo = new ComboBox<>();
        valueCombo.setLayoutX(80);
        valueCombo.setLayoutY(150);
        valueCombo.setPrefWidth(120);
        valueCombo.setPromptText("Select value");

        Map<Double, Integer> cashMap = MainProcessor.getCashProcessor().getCashMap();

        List<Double> values = cashMap.keySet().stream().sorted().collect(Collectors.toList());
        for (double value : values) {
            valueCombo.getItems().add(Double.toString(value));
        }
        pane.getChildren().add(valueCombo);

    }

    private void initTextFields() {
        numberField = new TextField();
        numberField.setLayoutX(280);
        numberField.setLayoutY(150);
        numberField.setPrefWidth(120);
        numberField.setPromptText("Number");

        pane.getChildren().add(numberField);
    }

    private void initButtonActions() {
        addButton.setOnAction((event -> addAction()));
        payButton.setOnAction((event -> {
            if (table.getItems().isEmpty()) {
                alert(Alert.AlertType.WARNING, "You don't pay any cashes.");
                return;
            } else if (calPayAmount() < calPurchaseAmount()) {
                alert(Alert.AlertType.WARNING, "You don't have enough money.");
                return;
            }
            new ChangeWindow(this);
            stage.close();
        }));
    }


    private void addAction() {
        String value = valueCombo.getSelectionModel().getSelectedItem();
        String number = numberField.getText();

        if (!validateInput()) {
            return;
        }

        try {
            this.userCashMap.put(value, number);
            setTableData();
        } catch (Exception e) {
            alert(Alert.AlertType.WARNING, "Fail to add cash.");
        }
    }


    private void initTable() {
        table = new TableView<>();
        table.setLayoutX(40);
        table.setLayoutY(200);
        table.setPrefWidth(400);
        table.setPrefHeight(300);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        pane.getChildren().add(table);

        // create table
        String[] colNames = {"Value", "Number"};
        String[] properties = {"value", "number"};
        for (int i = 0; i < colNames.length; i++) {
            String colName = colNames[i];
            TableColumn<CashTableEntry, String> column = new TableColumn<>(colName);
            column.setSortable(false);
            column.setPrefWidth(118);
            column.setStyle("-fx-alignment: CENTER;");
            column.setCellValueFactory(new PropertyValueFactory<>(properties[i]));
            table.getColumns().add(column);
        }

        setTableData();
    }

    private void alert(Alert.AlertType warning, String s) {
        Alert alert = new Alert(warning, s);
        alert.show();
    }

    private boolean validateInput() {
        if (numberField.getText().trim().isEmpty() && valueCombo.getSelectionModel().isEmpty()) {
            alert(Alert.AlertType.WARNING, "Nothing to add");
            return false;
        } else if (numberField.getText().trim().isEmpty()) {
            alert(Alert.AlertType.WARNING, "Number of cash needed");
            return false;
        } else if (valueCombo.getSelectionModel().isEmpty()) {
            alert(Alert.AlertType.WARNING, "Cash value needed.");
            return false;
        }
        return true;
    }


    private void setTableData() {
        // set data to table
        table.getItems().clear();

        Collection<String> keySet = userCashMap.keySet();
        List<String> list = new ArrayList<>(keySet);
        Collections.sort(list);
        for (String value : list) {
            table.getItems().add(new CashTableEntry(value, userCashMap.get(value)));
        }
    }

    private double calPayAmount() {
        double payAmount = 0.0;
        for (String key : userCashMap.keySet()) {
            payAmount += Double.parseDouble(key) * Double.parseDouble(userCashMap.get(key));
        }
        return payAmount;
    }

    private double calPurchaseAmount() {
        double purchaseAmount = 0.0;
        List<ProductTableEntry> purchaseList = checkout.getShoppingCart();
        for (ProductTableEntry productTableEntry : purchaseList) {
            purchaseAmount += Double.parseDouble(productTableEntry.getPrice()) * Integer.parseInt(productTableEntry.getQuantity());
        }
        return purchaseAmount;
    }

    public Map<String, String> getUserCashMap() {
        return this.userCashMap;
    }

    public CheckoutWindow getCheckout() {
        return this.checkout;
    }
}
