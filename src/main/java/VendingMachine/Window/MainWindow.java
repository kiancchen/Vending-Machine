package VendingMachine.Window;

import VendingMachine.MainProcessor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class MainWindow {
    private MainProcessor processor;
    private Scene scene;
    private AnchorPane pane;
    private Button adminBtn;
    private Button userManagementBtn;

    public MainWindow(MainProcessor processor) {
        this.processor = processor;
        pane = new AnchorPane();
        scene = new Scene(pane, 600, 480);
        initButtons();
        initBtnActions();
    }

    private void initButtons() {
        adminBtn = new Button();
        userManagementBtn = new Button();

        Button[] buttons = {adminBtn, userManagementBtn};
        String[] texts = {"Admin", "Manage User"};

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
        adminBtn.setOnAction(event -> new LoginWindow(processor));
        userManagementBtn.setOnAction(event -> new UserManagementWindow(processor));
    }

    public Scene getScene() {
        return scene;
    }
}
