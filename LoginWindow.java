package testgui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class LoginWindow {

    public void show() {
        Stage loginStage = new Stage();
        StackPane root = new StackPane();
        Button loginButton = new Button("Login");
        root.getChildren().add(loginButton);
        loginStage.setScene(new Scene(root, 200, 200));

        // set an event handler on the login button to handle login event
        loginButton.setOnAction(event -> {
            // check login credentials and if successful, close login page and show main window
            if (loginSuccessful()) {
                // close the login page
                loginStage.close();

                // show the main window
                MainWindow mainWindow = new MainWindow(this);
                mainWindow.show();
            } else {
                // display an error message to the user
            }
        });

        // show the login page
        loginStage.show();
    }

    private boolean loginSuccessful() {
        // check login credentials and return true if successful
        return true;
    }
}