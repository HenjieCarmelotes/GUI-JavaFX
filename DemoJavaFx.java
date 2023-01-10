package guijavafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.text.Font;

public class DemoJavaFx extends Application {
	
	public void start(Stage stage) { //Stage represents the window on computer screen. This is the main window of this program.
			
			Label message = new Label("JAVA FX DEMONSTRATION :)");
			message.setFont( new Font(30) );
			
			/* Pushbuttons */
			Button helloButton = new Button("Click to see 1st message!");
			helloButton.setOnAction( e -> message.setText("Hello World!") );
			
			
			Button welcomeButton = new Button("Click to see 2nd message");
			welcomeButton.setOnAction( e -> message.setText("Welcome to Java!") );
			
			
			Button quitButton = new Button("Click to quit");
			quitButton.setOnAction( e -> Platform.exit() );
			
			/* Button Containers and containers of other containers. */
			HBox buttonBar = new HBox( 20, helloButton, welcomeButton, quitButton );
			buttonBar.setAlignment(Pos.CENTER);
			
			
			/*Containers of containers*/
			BorderPane root = new BorderPane();
			root.setCenter(message);
			root.setTop(buttonBar);
			
			/* Scene is the container of GUI Components (the content area of the stage) */
			Scene scene = new Scene(root, 450, 200); //the root here contains all the containers in the content area.
			stage.setScene(scene);
			stage.setTitle("JavaFX Demo");
			stage.show(); //make the window visible.
		} // end start();
		
	public static void main(String[] demojavafx) {
				launch(demojavafx); // Run this Application.
	 }// end of main()
			

} //end of DemoJavaFx class
