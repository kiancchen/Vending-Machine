package VendingMachine.Window.CheckoutManagement;

import VendingMachine.Data.CreditCard;
import VendingMachine.Data.Transaction;
import VendingMachine.Data.User;
import VendingMachine.Processor.CardProcessor;
import VendingMachine.Processor.UserProcessor;
import VendingMachine.Window.MainWindow;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.skin.TextFieldSkin;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class CardPaymentWindow {
    private final Stage stage;
    private final AnchorPane pane;
    private final User currentUser;
    private TextField nameField;
    private TextField numberField;
    private CheckBox checkBox;

    public CardPaymentWindow() {
        pane = new AnchorPane();
        Scene scene = new Scene(pane, 370, 100);
        stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Card Payment");
        stage.show();
        currentUser = UserProcessor.getInstance().getCurrentUser();
        initTextField();
        initBtn();
        initCheckBox();
    }

    private void initTextField() {
        nameField = new TextField();
        nameField.setPromptText("Name");
        nameField.setLayoutX(20);
        nameField.setLayoutY(20);
        nameField.setPrefWidth(150);

        numberField = new TextField();
        numberField.setPromptText("Number");
        numberField.setLayoutX(200);
        numberField.setLayoutY(20);
        numberField.setPrefWidth(150);
        numberField.setSkin(new TextFieldSkin(numberField) {
            // Hide the password and show as *
            @Override
            protected String maskText(String txt) {
                return "*".repeat(txt.length());
            }
        });

        pane.getChildren().add(nameField);
        pane.getChildren().add(numberField);

        if (currentUser.getType() != User.UserType.ANONYMOUS && currentUser.getCard() != null) {
            nameField.setText(currentUser.getCard().getName());
            numberField.setText(currentUser.getCard().getNumber());
        }
    }

    private void initBtn() {
        Button checkBtn = new Button();
        checkBtn.setText("Check");
        checkBtn.setLayoutX(150);
        checkBtn.setLayoutY(60);
        pane.getChildren().add(checkBtn);
        checkBtn.setOnAction(event -> checkAction());
    }

    private void initCheckBox() {
        checkBox = new CheckBox();
        checkBox.setText("Save card");
        checkBox.setLayoutX(20);
        checkBox.setLayoutY(60);
        pane.getChildren().add(checkBox);
        checkBox.setOnAction(event -> {
            if (currentUser.getType() == User.UserType.ANONYMOUS) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Anonymous user can't save " +
                        "card");
                alert.show();
                checkBox.setSelected(false);
            }
        });
    }

    private void checkAction() {
        String name = nameField.getText();
        String number = numberField.getText();
        CardProcessor cardProcessor;

        cardProcessor = CardProcessor.getInstance();

        CreditCard card = cardProcessor.validateCard(name, number);
        if (card != null) {
                if (currentUser.pay(currentUser.getTotalPrice(), Transaction.Payment.CARD)) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Successful!");
                    alert.show();
                    if (checkBox.isSelected()) {
                        currentUser.setCard(card);
                    }
                    UserProcessor.getInstance().logoutUser();
                    MainWindow.getInstance().update();
                    stage.close();
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Fail to pay.");
                    alert.show();
                }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Card does not exist.");
            alert.show();
        }
    }
}
