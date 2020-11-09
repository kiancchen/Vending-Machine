package VendingMachine.Window;

import VendingMachine.Processor.MainProcessor;
import VendingMachine.Processor.UserProcessor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.skin.TextFieldSkin;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginWindow {
    private Stage stage;
    private Scene scene;
    private AnchorPane pane;
    private TextField inputUsername;
    private PasswordField inputPassword;
    private MainWindow mainWindow;

    public LoginWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;

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
        Label username = new Label("Username");
        username.setLayoutX(50);
        username.setLayoutY(15);
        pane.getChildren().add(username);
        Label password = new Label("Password");
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


        inputPassword = new PasswordField();
        inputPassword.setSkin(new TextFieldSkin(inputPassword) {
            // Hide the password and show as *
            @Override
            protected String maskText(String txt) {
                return "*".repeat(txt.length());
            }
        });
        inputPassword.setLayoutX(350);
        inputPassword.setLayoutY(50);
        inputPassword.setPrefWidth(200);
        inputPassword.setPromptText("password");
        pane.getChildren().add(inputPassword);
    }

    private void initButtons() {
        Button signInButton = new Button();
        signInButton.setLayoutX(75);
        signInButton.setLayoutY(105);
        signInButton.setPrefWidth(100);
        signInButton.setText("Sign In");
        signInButton.requestFocus();
        pane.getChildren().add(signInButton);
        signInButton.setOnAction(event -> signInAction());

        Button signUpButton = new Button();
        signUpButton.setLayoutX(400);
        signUpButton.setLayoutY(105);
        signUpButton.setPrefWidth(100);
        signUpButton.setText("Sign Up");
        signUpButton.requestFocus();
        pane.getChildren().add(signUpButton);
        signUpButton.setOnAction(event -> signUpAction());
    }


    public void signInAction() {
        String usernameInp = inputUsername.getText();
        String passwordInp = inputPassword.getText();
        if (MainProcessor.getUserProcessor().verifyUser(usernameInp, passwordInp)) {
            Alert alert = new Alert(AlertType.INFORMATION, "Sign in successfully.");
            alert.show();
            this.mainWindow.changeAccountButtonText("Logout");
            this.mainWindow.updateCurrencyUserInfo();
            stage.close();
        } else {
            Alert alert = new Alert(AlertType.WARNING, "Fail to sign in. Wrong username or " +
                    "password.");
            alert.show();
        }
    }

    public void signUpAction() {
        String usernameInp = inputUsername.getText();
        String passwordInp = inputPassword.getText();
        UserProcessor userProcessor = MainProcessor.getUserProcessor();
        try {
            if (userProcessor.addUser(usernameInp, passwordInp)) {

                Alert alert = new Alert(AlertType.INFORMATION, "Sign up successfully.");
                alert.show();
                userProcessor.verifyUser(usernameInp, passwordInp);
                this.mainWindow.changeAccountButtonText("Logout");
                this.mainWindow.updateCurrencyUserInfo();
                stage.close();
            } else {
                Alert alert = new Alert(AlertType.WARNING, "User exists");
                alert.show();
            }
        } catch (IOException e) {
            Alert alert = new Alert(AlertType.WARNING, "Something went wrong.");
            alert.show();
        }
    }

}
