package hospitalDemandManagement;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginWindow implements Initializable {
    
    @FXML
    private Button btLogin;

    @FXML
    private PasswordField pfPassword;

    @FXML
    private TextField tfUsername;

    @FXML
    private Label errorLogin;

    private String errorMessage = "";

    private Parent root;

    private Stage loginStage;

    public void show() {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/hospitalDemandManagement/logInpage.fxml"));
            loader.setController(this);
            root = loader.load();
            loginStage = new Stage();
            Scene scene = new Scene(root, 650, 500);
            loginStage.setTitle("Hospital Demand Management");
            loginStage.setScene(scene);
            loginStage.setResizable(false);
            loginStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    } // end show()

    private boolean isFieldFilled() {
        boolean isFilled = true;

        if (tfUsername.getText().isEmpty()) {
            isFilled = false;
            errorMessage = "Username is empty!";
        }

        if (pfPassword.getText().isEmpty()) {
            isFilled = false;
            if (errorMessage.isEmpty())
                errorMessage = "Password is empty!";
            else
                errorMessage += "\nPassword is empty!";
        }

        errorLogin.setText(errorMessage);

        return isFilled;

    }// end of isField()

    private boolean isValid() {
        boolean isValid = true;

        if (!tfUsername.getText().equals(Main.USERNAME)) {
            isValid = false;
            errorMessage = "Invalid Username";
        }

        if (!pfPassword.getText().equals(Main.PASSWORD)) {
            isValid = false;
            if (errorMessage.isEmpty())
                errorMessage = "Invalid Password!";
            else
                errorMessage += "\nInvalid Password!";
        }

        errorLogin.setText(errorMessage);

        return isValid;
    }

    public void initialize(URL url, ResourceBundle resource) {
        btLogin.setOnAction(event -> handleLoginAction());
    }
    

    private void handleLoginAction() {

        if (isFieldFilled() && isValid()) {
            // close the login page
            loginStage.close();
            // show the main window
            MainWindow mainWindow = new MainWindow(this);
            mainWindow.show();
        } else {
            errorLogin.setText(errorMessage);
        }
    } // end handleLoginAction

}

