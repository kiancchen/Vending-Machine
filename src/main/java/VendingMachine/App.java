package VendingMachine;

import VendingMachine.Data.Product;
import VendingMachine.Processor.MainProcessor;
import VendingMachine.Processor.ProductProcessor;
import VendingMachine.Window.MainWindow;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        MainWindow mainWindow = new MainWindow();
        primaryStage.setScene(mainWindow.getScene());
        primaryStage.setTitle("Vending Machine");
        primaryStage.show();
    }
}
