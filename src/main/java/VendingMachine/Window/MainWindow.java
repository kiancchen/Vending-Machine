package VendingMachine.Window;

import VendingMachine.Processor.MainProcessor;
import VendingMachine.User;
import VendingMachine.Window.CashManagement.CashManagementWindow;
import VendingMachine.Window.UserManagement.UserManagementWindow;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class MainWindow {
    private MainProcessor processor;
    private Scene scene;
    private AnchorPane pane;
    private Button accountBtn;
    private Button userManagementBtn;
    private Button cashierManageBtn;
    private Text currentUserInfo;

    public MainWindow(MainProcessor processor) {
        this.processor = processor;
        pane = new AnchorPane();
        scene = new Scene(pane, 600, 480);
        initButtons();
        initBtnActions();
        initText();
        updateCurrencyUserInfo();
    }

    private void initButtons() {
        accountBtn = new Button();
        userManagementBtn = new Button();
        cashierManageBtn = new Button();

        Button[] buttons = {accountBtn, userManagementBtn,cashierManageBtn};
        String[] texts = {"Account", "Manage User","Manage Cash"};

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
        accountBtn.setOnAction((event -> {
            if (processor.getCurrentUser().getType() == User.UserType.ANONYMOUS) {
                // If the currency user is the Anonymous
                new LoginWindow(processor, this);
            } else {
                processor.logoutUser();
                accountBtn.setText("Account");
                updateCurrencyUserInfo();
            }
        }));
        userManagementBtn.setOnAction(event -> {
            if (processor.getCurrentUser().getPermission(User.Permission.MANAGE_USER)) {
                new UserManagementWindow(processor);
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "You don't have the permission " +
                        "to do this action.");
                alert.show();
            }
        });
        cashierManageBtn.setOnAction(event -> {
            if (processor.getCurrentUser().getPermission(User.Permission.MANAGE_CASH)){
                new CashManagementWindow(processor);
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
        currentUserInfo.setText(
                this.processor.getCurrentUser().getUsername()
                        + "   "
                        + this.processor.getCurrentUser().getType()
        );
    }

    public void changeAccountButtonText(String text) {
        this.accountBtn.setText(text);
    }

    public Scene getScene() {
        return scene;
    }
}
