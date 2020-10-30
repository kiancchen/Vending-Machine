package VendingMachine;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class MainWindow {
    private MainProcessor processor;
    private Scene scene;
    private AnchorPane pane;


    public MainWindow(MainProcessor processor) {
        this.processor = processor;
        pane = new AnchorPane();
        scene = new Scene(pane, 500, 300);
        initButtons();
    }

    private void initButtons() {
        Button btn1 = new Button();
        btn1.setLayoutX(20);
        btn1.setLayoutY(20);
        btn1.setPrefWidth(50);
        btn1.setPrefHeight(50);
        pane.getChildren().add(btn1);
        btn1.setOnAction(event -> new LoginWindow(processor));
    }

    public Scene getScene() {
        return scene;
    }
}
