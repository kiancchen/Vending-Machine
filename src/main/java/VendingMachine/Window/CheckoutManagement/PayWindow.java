package VendingMachine.Window.CheckoutManagement;

import VendingMachine.DatabaseHandler;
import VendingMachine.Data.User;
import VendingMachine.Processor.MainProcessor;
import VendingMachine.Processor.CashProcessor;
import VendingMachine.Window.ProductManagement.ProductTableEntry;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;

import java.util.*;

public class PayWindow {
    private Stage stage;
    private Scene scene;
    private AnchorPane pane;
    private CashPaymentWindow cashPaymentWindow;
    private TableView<CashTableEntry> table;
    private Map<String, String> userCashMap;
    private Button okeyButton;
    //private int selectedId;

    public PayWindow(CashPaymentWindow cashPaymentWindow) {
        stage = new Stage();
        pane = new AnchorPane();
        scene = new Scene(pane, 480, 600);
        stage.setScene(scene);
        stage.setTitle("Cash Changes");
        stage.show();

        this.cashPaymentWindow = cashPaymentWindow;
        this.userCashMap = cashPaymentWindow.getUserCashMap();

        initTable();
        initButton();
        initButtonAction();

    }

    private void initButton() {
        okeyButton = new Button();
        okeyButton.setLayoutX(180);
        okeyButton.setLayoutY(60 + 470 );
        okeyButton.setPrefWidth(120);
        okeyButton.setPrefHeight(30);
        okeyButton.setText("OKEY");
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
        setTableData();


    }

    private void setTableData() {
        // set data to table
        double pay_amount = 0.0;
        for (String key : this.userCashMap.keySet()) {
          pay_amount += Double.parseDouble(key) * Double.parseDouble(this.userCashMap.get(key));
        }

        double purchase_amount = 0.0;
        List<ProductTableEntry> purchaseList = cashPaymentWindow.getCheckout().getPurchaseList();
        for(int i = 0; i < purchaseList.size(); i++) {
            purchase_amount += Double.parseDouble(purchaseList.get(i).getPrice()) * Integer.parseInt(purchaseList.get(i).getQuantity());
        }

        double changes = pay_amount - purchase_amount;



        try {
          Map<Double, Integer> changesMap = MainProcessor.getCashProcessor().getChange(changes);
          System.out.println(changesMap);
          Collection<Double> keySet = changesMap.keySet();
          List<Double> list = new ArrayList<>(keySet);
          Collections.sort(list);
          for (Double value : list) {
              table.getItems().add(new CashTableEntry(value.toString(), changesMap.get(value).toString()));
          }
        } catch (IOException e){
          System.out.println(e);
        }
    }


}
