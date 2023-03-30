package com.jmc.mavenfx;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.Scene;
import javafx.scene.control.*;

public class MenuDemo extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane root = new BorderPane();
		MenuBar mb = new MenuBar();
		Menu file = new Menu("File");
		MenuItem item1 = new MenuItem("Save");
		MenuItem item2 = new MenuItem("Exit");
		file.getItems().addAll(item1,item2);
		Menu edit = new Menu ("Edit");
		RadioMenuItem option1 = new RadioMenuItem("Option");
		RadioMenuItem option2 = new RadioMenuItem("Setting");
		
		Menu pref = new Menu("Preferences");
		MenuItem item3 = new MenuItem("jogging");
		MenuItem item4 = new MenuItem ("Swimming");
		pref.getItems().addAll(item3, item4);
		edit.getItems().addAll(option1, option2, new SeparatorMenuItem(), pref);
		
		TabPane tp = new TabPane();
		Tab one = new Tab("File One");
		Tab two = new Tab("File Two");
		two.setClosable(false);
		VBox cont1 = new VBox();
		cont1.setPrefHeight(100);
		cont1.setAlignment(Pos.CENTER);
		cont1.getChildren().addAll(new Label("Content for File 1"));
		VBox cont2 = new VBox();
		cont2.setAlignment(Pos.CENTER);
		cont2.getChildren().addAll(new Label("Content for File 2"));
		
		one.setContent(cont1);
		two.setContent(cont2);
		
		DatePicker dp = new DatePicker();
		//dp.isShowWeekNumbers();
		
		tp.getTabs().addAll(one,two);
		
		mb.getMenus().addAll(file,edit);
		TextArea ta = new TextArea("Write Something here! ");
		
		root.setTop(mb);
		root.setCenter(ta);
		root.setBottom(tp);
		root.setRight(dp);
		Scene scene = new Scene(root, 500,450);
		primaryStage.setTitle("Menu Demo");
		primaryStage.setScene(scene);
		primaryStage.show();
		mb.requestFocus();
		
		
	}
	
	public static void main(String[] args) {
		launch();
	}
	
	

}
