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
import javafx.stage.Stage;
import javafx.scene.control.TextField;

public class BorderPaneDemo2 extends Application {
	
	public void start(Stage primaryStage) throws Exception {
		BorderPane root = new BorderPane();
		ListView <String> fruits = new ListView<>();
		ObservableList<String> items = FXCollections.observableArrayList();
		//left node
		items.addAll("apple", "papaya", "watermelon");
		fruits.setItems(items);
		//top node
		Label top = new Label("Fruit List Application");
		top.setPadding(new Insets(10));
		//bottom node
		Label bot = new Label ("Newly Added Fruit: None");
		bot.setPadding(new Insets(10));
		//right node
		Label right = new Label("Total Number of Fruit: 3");
		right.setRotate(90);
		right.setPadding(new Insets(0,0,0,60));
		//center node
		TextField tf = new TextField("Input your fruits");
		Button btn = new Button("Click to add");
		btn.setOnAction(e -> {
			if (!tf.getText().isEmpty()) {
				String newItem = tf.getText();
				items.add(newItem);
				bot.setText("Newly Added Fruit: " + newItem);
				right.setText("Total Number of Fruit: " + fruits.getItems().size());
				tf.clear();
				tf.setFocusTraversable(true);
			}
			else {
				bot.setText("Newly Added Fruit: None");
				right.setText("Total Number of Fruit: " + fruits.getItems().size());
			}
			
		});
		HBox hbox = new HBox(10,tf,btn);
		
		//Setting the BorderPane Layout
		root.setTop(top);
		root.setBottom(bot);
		root.setCenter(hbox);
		root.setLeft(fruits);
		root.setRight(right);
		Scene scene = new Scene(root, 700,650);
		primaryStage.setTitle("BorderPane Demo2");
		
		primaryStage.setScene(scene);
		
		primaryStage.show();
		
		
	}
	
	public static void main(String[] args) {
		launch();
	}
	

}
