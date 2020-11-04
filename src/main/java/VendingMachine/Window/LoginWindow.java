package VendingMachine.Window;

import VendingMachine.MainProcessor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginWindow {
    private MainProcessor processor;
    private Stage stage;
    private Scene scene;
    private AnchorPane pane;
    private TextField inputUsername;
    private TextField inputPassword;
    private Button signInButton;
    private Button signUpButton;
    private Button loginButton;
    private Label username;
    private Label password;
    private Button returnMainButton;

    public LoginWindow(MainProcessor processor, Button loginButton) {
        this.processor = processor;
        this.loginButton = loginButton;

        stage = new Stage();
        pane = new AnchorPane();
        scene = new Scene(pane, 600, 170);
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.show();

        initLabels();
        initInputFields();
        initButtons();
    }

    private void initLabels() {
        username = new Label("Username");
        username.setLayoutX(50);
        username.setLayoutY(15);
        pane.getChildren().add(username);
        password = new Label("Password");
        password.setLayoutX(350);
        password.setLayoutY(15);
        pane.getChildren().add(password);
    }

    private void initInputFields() {
        inputUsername = new TextField();
        inputUsername.setLayoutX(50);
        inputUsername.setLayoutY(50);
        inputUsername.setPrefWidth(200);
        inputUsername.setPromptText("Username");
        pane.getChildren().add(inputUsername);

        inputPassword = new TextField();
        inputPassword.setLayoutX(350);
        inputPassword.setLayoutY(50);
        inputPassword.setPrefWidth(200);
        inputPassword.setPromptText("password");
        pane.getChildren().add(inputPassword);
    }

    private void initButtons() {
        signInButton = new Button();
        signInButton.setLayoutX(75);
        signInButton.setLayoutY(105);
        signInButton.setPrefWidth(100);
        signInButton.setText("Sign In");
        signInButton.requestFocus();
        pane.getChildren().add(signInButton);
        signInButton.setOnAction(event -> signInAction());

        signUpButton = new Button();
        signUpButton.setLayoutX(400);
        signUpButton.setLayoutY(105);
        signUpButton.setPrefWidth(100);
        signUpButton.setText("Sign Up");
        signUpButton.requestFocus();
        pane.getChildren().add(signUpButton);
        signUpButton.setOnAction(event -> signUpAction());
    }


    public boolean signInAction() {

        String usernameInp = inputUsername.getText();
        String passwordInp = inputPassword.getText();
        if (this.processor.verifyUser(usernameInp, passwordInp)) {
            Alert alert = new Alert(AlertType.INFORMATION, "Sign in successfully.");
            alert.show();
            loginButton.setText("Logout");
            stage.close();
            return true;
        } else {
            ButtonType tryAgain = new ButtonType("Try Again", ButtonBar.ButtonData.CANCEL_CLOSE);
            ButtonType returnMain = new ButtonType("Return Main", ButtonBar.ButtonData.OK_DONE);
            Alert alert = new Alert(AlertType.ERROR, "Wrong Username/Password or Account is not exists", tryAgain, returnMain);
            returnMainButton = (Button) alert.getDialogPane().lookupButton(returnMain);
            alert.setResizable(true);
            alert.getDialogPane().setPrefSize(400, 150);
            alert.showAndWait();

            if (alert.getResult() == returnMain) {
                stage.close();
            }
            return false;
        }
    }

    public boolean signUpAction() {

        String usernameInp = inputUsername.getText();
        String passwordInp = inputPassword.getText();

        if (!(this.processor.hasUser(usernameInp))) {
            try {
                this.processor.addUser(usernameInp, passwordInp);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Alert alert = new Alert(AlertType.INFORMATION, "Sign up successfully.");
            alert.show();
            stage.close();
            return true;
        } else {
            ButtonType tryAgain = new ButtonType("Try Again", ButtonBar.ButtonData.CANCEL_CLOSE);
            ButtonType returnMain = new ButtonType("Return Main", ButtonBar.ButtonData.OK_DONE);
            Alert alert = new Alert(AlertType.ERROR, "The Account is exists", tryAgain, returnMain);
            returnMainButton = (Button) alert.getDialogPane().lookupButton(returnMain);
            alert.setResizable(true);
            alert.getDialogPane().setPrefSize(400, 150);
            alert.showAndWait();

            if (alert.getResult() == returnMain) {
                stage.close();
            }
            return false;
        }
    }

}
