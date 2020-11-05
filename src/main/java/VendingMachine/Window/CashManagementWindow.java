package VendingMachine.Window;
import VendingMachine.DatabaseHandler;
import VendingMachine.Processor.MainProcessor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.util.Map;

public class CashManagementWindow {
    private MainProcessor processor;
    private Stage stage;
    private Scene scene;
    private AnchorPane pane;
    private TableView<CashTableEntry> table;

    private Button changeButton;


    public CashManagementWindow(MainProcessor processor)  {

        this.processor = processor;
        stage = new Stage();
        pane = new AnchorPane();
        scene = new Scene(pane, 600, 480);
        stage.setScene(scene);
        stage.setTitle("Cash Management");
        stage.show();
        initTable();
        initButton();
        initButtonActions();
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
        String[] colNames = {"Value","Number"};
        String[] properties = {"cashType","amount"};
        for (int i = 0; i < colNames.length; i++) {
            String colName = colNames[i];
            TableColumn<CashTableEntry, String> column = new TableColumn<>(colName);
            column.setSortable(false);
            column.setPrefWidth(118);
            column.setStyle("-fx-alignment: CENTER;");
            column.setCellValueFactory(new PropertyValueFactory<>(properties[i]));
            table.getColumns().add(column);
        }
        Map<Double,Integer> cashMap=processor.getCashMap();
        cashMap.forEach((k,v)->{
            table.getItems().add(new CashTableEntry(k,v));
        });

        }


    private void initButton() {

        changeButton = new Button();


        Button[] buttons = {changeButton};
        String[] texts = {"Change amount"};

        for (int i = 0; i < buttons.length; i++) {
            Button button = buttons[i];
            button.setLayoutX(200 + 150 * i);
            button.setLayoutY(400);
            button.setPrefWidth(200);
            button.setPrefHeight(30);
            button.setText(texts[i]);
            pane.getChildren().add(button);
        }
    }

    private void initButtonActions() {

//        changeButton.setOnAction((event -> new ChangeUserWindow;
    }
}
