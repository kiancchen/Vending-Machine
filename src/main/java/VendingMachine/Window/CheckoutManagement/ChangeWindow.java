package VendingMachine.Window.CheckoutManagement;

import VendingMachine.Processor.MainProcessor;
import VendingMachine.Window.ProductManagement.ProductTableEntry;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

public class ChangeWindow {
    private Stage stage;
    private Scene scene;
    private AnchorPane pane;
    private CashPaymentWindow cashPaymentWindow;
    private TableView<CashTableEntry> table;
    private Map<String, String> userCashMap;
    private Button okeyButton;

    public ChangeWindow(CashPaymentWindow cashPaymentWindow) {
        stage = new Stage();
        pane = new AnchorPane();
        scene = new Scene(pane, 480, 600);
        stage.setScene(scene);
        stage.setTitle("Cash Changes");
        stage.show();

        this.cashPaymentWindow = cashPaymentWindow;
        this.userCashMap = cashPaymentWindow.getUserCashMap();

        initLabel();
        initTable();
        initButton();
        initButtonAction();
    }

    private void initLabel() {
        Label youReceiveLabel = new Label("Your changes");
        youReceiveLabel.setLayoutX(130);
        youReceiveLabel.setLayoutY(50);
        youReceiveLabel.setFont(Font.font(20));
        pane.getChildren().add(youReceiveLabel);
    }

    private void initButton() {
        okeyButton = new Button();
        okeyButton.setLayoutX(180);
        okeyButton.setLayoutY(60 + 470);
        okeyButton.setPrefWidth(120);
        okeyButton.setPrefHeight(30);
        okeyButton.setText("Okay");
        pane.getChildren().add(okeyButton);

    }

    private void initButtonAction() {
        okeyButton.setOnAction((event -> stage.close()));
    }

    private void initTable() {
        table = new TableView<>();
        table.setLayoutX(40);
        table.setLayoutY(100);
        table.setPrefWidth(400);
        table.setPrefHeight(400);
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

    private void setTableData() {
        // set data to table
        double payAmount = 0.0;
        for (String key : this.userCashMap.keySet()) {
            payAmount += Double.parseDouble(key) * Double.parseDouble(this.userCashMap.get(key));
        }

        double purchaseAmount = 0.0;
        List<ProductTableEntry> purchaseList = cashPaymentWindow.getCheckout().getShoppingCart();
        for (ProductTableEntry productTableEntry : purchaseList) {
            purchaseAmount += Double.parseDouble(productTableEntry.getPrice()) * Integer.parseInt(productTableEntry.getQuantity());
        }

        double changes = payAmount - purchaseAmount;

        try {
            Map<Double, Integer> changesMap = MainProcessor.getCashProcessor().getChange(changes);
            System.out.println(changesMap);
            Collection<Double> keySet = changesMap.keySet();
            List<Double> list = new ArrayList<>(keySet);
            Collections.sort(list);
            for (Double value : list) {
                table.getItems().add(new CashTableEntry(value.toString(), changesMap.get(value).toString()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}