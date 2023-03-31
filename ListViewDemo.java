package com.jmc.mavenfx;

import javafx.geometry.Pos;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ListViewDemo extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		VBox root = new VBox();
		root.setAlignment(Pos.CENTER);
		ListView<Task> taskListView = new ListView<>();
		ObservableList<Task> tasks = FXCollections.observableArrayList(
				new Task("Create Java Project"),
				new Task("Create Bug Report"),
				new Task ("Create Changes to Repository")
				
				);
		
		taskListView.setCellFactory(view -> new CustomTaskCell());
		taskListView.getItems().addAll(tasks);
		root.getChildren().addAll(taskListView);
		
		Scene scene = new Scene(root, 500,450);
		primaryStage.setTitle("Array");
		primaryStage.setScene(scene);
		primaryStage.show();
	
		
		
	}
	
	public static void main(String[] args) {
		launch();
	}
	
	

}
