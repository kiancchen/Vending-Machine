package VendingMachine;

import VendingMachine.Processor.MainProcessor;
import VendingMachine.Window.MainWindow;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    public static void main(String[] args) throws IOException {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        MainProcessor processor = new MainProcessor();
        MainWindow mainWindow = new MainWindow(processor);
        primaryStage.setScene(mainWindow.getScene());
        primaryStage.setTitle("Vending Machine");
        primaryStage.show();
    }
}
