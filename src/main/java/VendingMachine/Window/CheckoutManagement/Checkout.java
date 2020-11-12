package VendingMachine.Window.CheckoutManagement;

import VendingMachine.Window.ProductManagement.ProductTableEntry;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;

public class Checkout {
    private Stage stage;
    private Scene scene;
    private AnchorPane pane;

    private List<ProductTableEntry> purchaseList;
    private Button card;
    private Button cash;
    private Button cancel;

    private Text total;

    public Checkout(List<ProductTableEntry> purchaseList ) {
        this.purchaseList = purchaseList;

        if(purchaseList.size() < 1) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please purchase an item.");
            alert.show();
            return;
        }

        stage = new Stage();
        pane = new AnchorPane();
        scene = new Scene(pane, 250, 200);
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
        }));

    }

    private void intiText() {
        int purchaseNum = 0;

        double money = 0;
        for(int i = 0; i < purchaseList.size(); i++) {
            purchaseNum += Integer.parseInt(purchaseList.get(i).getQuantity());
            money += Double.parseDouble(purchaseList.get(i).getPrice()) * Integer.parseInt(purchaseList.get(i).getQuantity());
        }
        total = new Text();
        total.setX(50);
        total.setY(50);
        total.setText("Total items: " + purchaseNum + "\n" + "Total money: " + money);
        pane.getChildren().add(total);
    }

    private void initButtons() {
        card = new Button();
        cash = new Button();
        cancel = new Button();

        Button[] btns = {card, cancel, cash};
        String[] names = {"Card", "Cancel", "Cash"};
        for(int i = 0; i < btns.length; i++) {
            btns[i].setLayoutY(150);
            btns[i].setLayoutX(15 + i * 80);
            btns[i].setPrefWidth(60);
            btns[i].setText(names[i]);
            pane.getChildren().add(btns[i]);
        }
    }

    public List<ProductTableEntry> getPurchaseList() {
      return this.purchaseList;
    }


}
