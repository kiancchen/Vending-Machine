package VendingMachine;

import VendingMachine.Window.MainWindow;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        MainWindow mainWindow = MainWindow.getInstance();
        primaryStage.setScene(mainWindow.getScene());
        primaryStage.setTitle("Vending Machine");
        primaryStage.show();
    }
}
