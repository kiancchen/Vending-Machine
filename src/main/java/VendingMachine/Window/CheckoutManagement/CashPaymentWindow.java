package VendingMachine.Window.CheckoutManagement;

import VendingMachine.DatabaseHandler;
import VendingMachine.Data.User;
import VendingMachine.Processor.MainProcessor;
import VendingMachine.Processor.CashProcessor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.collections.*;

import java.util.*;

public class CashPaymentWindow {
    private Stage stage;
    private Scene scene;
    private AnchorPane pane;
    private TableView<CashTableEntry> table;
    private Map<String, String> userCashMap;
    private Checkout checkout;
    private ComboBox<String> valueCombo;
    private Button addButton;
    private Button payButton;
    private TextField numberField;
    //private int selectedId;

    public CashPaymentWindow(Checkout checkout) {
        stage = new Stage();
        pane = new AnchorPane();
        scene = new Scene(pane, 480, 600);
        stage.setScene(scene);
        stage.setTitle("Cash Payment");
        stage.show();

        this.userCashMap = new HashMap<String, String>();
        this.checkout = checkout;

        initTable();
        initButtons();
        initButtonActions();
        initCombobox();
        initTextFields();

        //selectedId = -1;
    }


    private void initButtons() {
        addButton = new Button();
        payButton = new Button();

        Button[] buttons = {addButton, payButton};
        String[] texts = {"Add", "Pay"};

        for (int i = 0; i < buttons.length; i++) {
            Button button = buttons[i];
            button.setLayoutX(180);
            button.setLayoutY(60 + 470 * i);
            button.setPrefWidth(120);
            button.setPrefHeight(30);
            button.setText(texts[i]);
            pane.getChildren().add(button);
        }
    }

    private void initCombobox() {
        valueCombo = new ComboBox<>();
        valueCombo.setLayoutX(70);
        valueCombo.setLayoutY(20);

        Map<Double, Integer> cashMap = MainProcessor.getCashProcessor().getCashMap();
        for (Double values : cashMap.keySet()) {
          valueCombo.getItems().add(values.toString());
        }
        pane.getChildren().add(valueCombo);

    }

    private void initTextFields() {
        numberField = new TextField();
        numberField.setLayoutX(250);
        numberField.setLayoutY(20);
        numberField.setPrefWidth(120);
        numberField.setPromptText("Number");

        pane.getChildren().add(numberField);
    }

    private void initButtonActions() {
        addButton.setOnAction((event -> addAction()));
        payButton.setOnAction((event -> {
            new PayWindow(this);
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
          alert(Alert.AlertType.INFORMATION, "Successfully add.");
          setTableData();
          //selectedId = -1;
        } catch (Exception e) {
            alert(Alert.AlertType.WARNING, "Can not add cash.");
        }

    }


    private void initTable() {
        table = new TableView<>();
        table.setLayoutX(40);
        table.setLayoutY(110);
        table.setPrefWidth(400);
        table.setPrefHeight(400);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        pane.getChildren().add(table);

        // create table
        String[] colNames = {"Value", "Number"};
        String[] properties = {"cashType", "amount"};
        for (int i = 0; i < colNames.length; i++) {
            String colName = colNames[i];
            TableColumn<CashTableEntry, String> column = new TableColumn<>(colName);
            column.setSortable(false);
            column.setPrefWidth(118);
            column.setStyle("-fx-alignment: CENTER;");
            column.setCellValueFactory(new PropertyValueFactory<>(properties[i]));
            table.getColumns().add(column);
        }
        /*
        table.setOnMouseClicked(event -> {
            if (!table.getSelectionModel().isEmpty()) {
                CashTableEntry selected = table.getSelectionModel().getSelectedItem();
                selectedCashType = Double.parseDouble(selected.getCashType());
                originCash = selected.getCashType();
                typeCombo.getSelectionModel().select(originCash);
                amountField.setText(selected.getAmount());
            }
        });
        */

        setTableData();
    }

    private void alert(Alert.AlertType warning, String s) {
        Alert alert = new Alert(warning, s);
        alert.show();
    }

    private boolean validateInput() {
        if (numberField.getText().trim().isEmpty() && valueCombo.getSelectionModel().isEmpty()){
            alert(Alert.AlertType.WARNING, "Nothing to add");
            return false;
        } else if (numberField.getText().trim().isEmpty()) {
            alert(Alert.AlertType.WARNING, "Amount of cash needed");
            return false;
        } else if (valueCombo.getSelectionModel().isEmpty()) {
            alert(Alert.AlertType.WARNING, "Cash type needed.");
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

    public Map<String, String> getUserCashMap() {
      return this.userCashMap;
    }

    public Checkout getCheckout() {
      return this.checkout;
    }




}
