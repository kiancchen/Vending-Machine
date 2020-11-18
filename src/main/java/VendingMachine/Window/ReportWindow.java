package VendingMachine.Window;

import VendingMachine.Data.User;
import VendingMachine.Processor.UserProcessor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReportWindow {

    private Stage stage;
    private Scene scene;
    private AnchorPane pane;
    Button userReportBtn;
    Button cancelReportBtn;
    Button transactionReportBtn;

    public ReportWindow() {
        stage = new Stage();
        pane = new AnchorPane();
        scene = new Scene(pane, 400, 200);
        stage.setScene(scene);
        stage.setTitle("Report");
        stage.show();

        initButtons();
        initBtnActions();
    }

    private void initButtons() {
        userReportBtn = new Button("Generate User Report");
        cancelReportBtn = new Button("Generate Cancel Transaction Report");
        transactionReportBtn = new Button("Generate Transaction Report");

        Button[] buttons = {userReportBtn, cancelReportBtn, transactionReportBtn};

        for (int i = 0; i < buttons.length; i++) {
            Button button = buttons[i];
            button.setLayoutX(50);
            button.setLayoutY(20 + 50 * i);
            button.setPrefWidth(300);
            button.setPrefHeight(30);
            pane.getChildren().add(button);
        }
    }

    private void initBtnActions() {
        userReportBtn.setOnAction(event -> {
            try {
                if (UserProcessor.getInstance().getCurrentUser().getType() == User.UserType.OWNER) {
                    generateUserReport();
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "You don't have the permission " +
                            "to do this action.");
                    alert.show();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void generateUserReport() {
        try {
            FileWriter csvWriter = new FileWriter("UserReport.csv");

            List<String> title = Arrays.asList("ID", "Username", "Password", "Type");
            csvWriter.append(String.join(",", title));
            csvWriter.append("\n");


            for (User user: UserProcessor.getInstance().getUsers()) {
                List<String> texts = Arrays.asList(Integer.toString(user.getId()),
                        user.getUsername(), user.getPassword(), user.getType().toString());
                csvWriter.append(String.join(",", texts));
                csvWriter.append("\n");
            }

            csvWriter.flush();
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
