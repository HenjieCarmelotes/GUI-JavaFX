package testgui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainWindow {

    private LoginWindow loginWindow;

    public MainWindow(LoginWindow loginWindow) {
        this.loginWindow = loginWindow;
    }

    public void show() {
        Stage mainStage = new Stage();
        StackPane root = new StackPane();
        Button logoutButton = new Button("Logout");
        root.getChildren().add(logoutButton);
        mainStage.setScene(new Scene(root, 400, 400));

        // set an event handler on the logout button to handle logout event
        logoutButton.setOnAction(event -> {
            // close the main window
            mainStage.close();

            // show the login window
            loginWindow.show();
        });

        mainStage.show();
    }
    
}