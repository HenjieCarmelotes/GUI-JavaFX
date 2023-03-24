package testgui;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    private LoginWindow loginWindow;

    @Override
    public void start(Stage primaryStage) throws Exception {
        loginWindow = new LoginWindow();
        loginWindow.show();
    }

    public void showLoginWindow() {
        loginWindow.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}