package com.jmc.mavenfx;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;


public class GridPaneDemo extends Application {
	

	public void start(Stage primaryStage) throws Exception {
		GridPane root = new GridPane();
		//root.setGridLinesVisible(true);
		Label label = new Label("First name:");
		TextField txtfield = new TextField("Input your name");
		Button btn = new Button("Send");
		TextArea txtarea = new TextArea();
		txtarea.setPromptText("Enter your message here");
		
		txtfield.textProperty().bindBidirectional(txtarea.textProperty());
		
		
		
	
		
		root.addRow(0,label, txtfield, btn);
		root.add(txtarea,0,1,GridPane.REMAINING,1);
		
		root.setHgap(10);
		root.setVgap(10);
		
		
		Scene scene = new Scene(root);
		root.requestFocus();
		primaryStage.setTitle("GridPane Demo");
		
		primaryStage.setScene(scene);
		
		primaryStage.show();
		
	}
	
	
	
	
	
	

	public static void main(String[] args) {
	launch();
}

}
