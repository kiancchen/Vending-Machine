package VendingMachine.Window.CheckoutManagement;

import VendingMachine.Processor.MainProcessor;
import VendingMachine.Window.ProductManagement.ProductTableEntry;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;

public class CheckoutWindow {
    private Stage stage;
    private AnchorPane pane;
    private Button cash;
    private Button cancel;
    private double amount;

    public CheckoutWindow() {
        this.amount = MainProcessor.getUserProcessor().getCurrentUser().getShoppingCart().getAmount();
        if (amount == 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please purchase an item.");
            alert.show();
            return;
        }

        stage = new Stage();
        pane = new AnchorPane();
        Scene scene = new Scene(pane, 250, 200);
        stage.setScene(scene);
        stage.setTitle("Checkout");
        stage.show();

        initButtons();
        intiText();
        initButtonActions();
    }

    private void initButtonActions() {
        // create actions here(New Window)
        cash.setOnAction((event -> {
            new CashPaymentWindow(this);
            stage.close();
        }));
    }

    private void intiText() {
        Text total = new Text();
        total.setX(50);
        total.setY(50);
        total.setText("Total amount: " + this.amount);
        pane.getChildren().add(total);
    }

    private void initButtons() {
        Button card = new Button();
        cash = new Button();
        cancel = new Button();

        Button[] btns = {card, cancel, cash};
        String[] names = {"Card", "Cancel", "Cash"};
        for (int i = 0; i < btns.length; i++) {
            btns[i].setLayoutY(150);
            btns[i].setLayoutX(15 + i * 80);
            btns[i].setPrefWidth(60);
            btns[i].setText(names[i]);
            pane.getChildren().add(btns[i]);
        }
    }
}
