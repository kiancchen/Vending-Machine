package VendingMachine.Window.CashManagement;

import VendingMachine.Processor.CashProcessor;
import VendingMachine.Processor.MainProcessor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class CashManagementWindow {
    private Stage stage;
    private Scene scene;
    private AnchorPane pane;
    private TableView<CashTableEntry> table;
//    private ComboBox<String> typeCombo;
    private Button changeButton;
    private double selectedCashType;
    private TextField amountField;

    public CashManagementWindow() {
        stage = new Stage();
        pane = new AnchorPane();
        scene = new Scene(pane, 600, 480);
        stage.setScene(scene);
        stage.setTitle("Cash Management");
        stage.show();

        initTable();
        initButton();
        initTextFields();
        initButtonActions();
        selectedCashType = -1;
    }

    private void initTable() {
        table = new TableView<>();
        table.setLayoutX(95);
        table.setLayoutY(15);
        table.setPrefWidth(400);
        table.setPrefHeight(300);
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

        table.setOnMouseClicked(event -> {
            if (!table.getSelectionModel().isEmpty()) {
                CashTableEntry selected = table.getSelectionModel().getSelectedItem();
                selectedCashType = Double.parseDouble(selected.getCashType());
                amountField.setText(selected.getAmount());
            }
        });

        setTableData();
    }


    private void initButton() {
        changeButton = new Button();
        Button[] buttons = {changeButton};
        String[] texts = {"Change amount"};
        for (int i = 0; i < buttons.length; i++) {
            Button button = buttons[i];
            button.setLayoutX(200);
            button.setLayoutY(400);
            button.setPrefWidth(200);
            button.setPrefHeight(30);
            button.setText(texts[i]);
            pane.getChildren().add(button);
        }
    }

    private void initTextFields() {
        amountField = new TextField();
        amountField.setLayoutX(240);
        amountField.setLayoutY(350);
        amountField.setPrefWidth(120);
        amountField.setPromptText("Amount");

        pane.getChildren().add(amountField);

    }

    private void setTableData() {
        // set data to table
        table.getItems().clear();
        Map<Double, Integer> cashMap = MainProcessor.getCashProcessor().getCashMap();
        Collection<Double> keySet = cashMap.keySet();
        List<Double> list = new ArrayList<Double>(keySet);
        Collections.sort(list);
        for (int i = 0; i < list.size(); i++) {
            String cashType = list.get(i).toString();
            String amount = cashMap.get(list.get(i)).toString();
            table.getItems().add(new CashTableEntry(cashType, amount));
        }
    }

    private void initButtonActions() {
        changeButton.setOnAction((event -> changeAction()));
    }

    private void changeAction() {
        CashProcessor cashProcessor = MainProcessor.getCashProcessor();
        if (selectedCashType < 0) {
            alert(Alert.AlertType.WARNING, "You don't select any cash type.");
            return;
        } else if (!validateInput()) {
            return;
        }
        try {
            cashProcessor.setCashNumber(selectedCashType, Integer.parseInt(amountField.getText()));
//            productProcessor.setProductCategory(category, selectedId, ??)
            alert(Alert.AlertType.INFORMATION, "Change successfully.");
        } catch (Exception e) {
            alert(Alert.AlertType.WARNING, "Change failed.");
        }

        amountField.setText("");
        setTableData();
        selectedCashType = -1;
    }

    private boolean validateInput() {
        if (amountField.getText().trim().isEmpty()) {
            alert(Alert.AlertType.WARNING, "Amount needed");
            return false;
        }
        return true;
    }

    private void alert(Alert.AlertType warning, String s) {
        Alert alert = new Alert(warning, s);
        alert.show();
    }
}