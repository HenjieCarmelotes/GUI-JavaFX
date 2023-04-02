package com.jmc.mavenfx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTreeCell;

public class TreeViewDemo extends Application {

	@SuppressWarnings("unchecked")
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
		VBox root = new VBox();
		TreeView<String> treeView = new TreeView<>();
		
		
		TreeItem<String> classGroup = new TreeItem<>("Class Group");
		TreeItem<String> classOne = new TreeItem<>("Group One"); //not to be confused with the nodes in scenegraph.
		classOne.getChildren().addAll(
				new TreeItem<>("Leader: Jhon"),
				new TreeItem<>("Member: Peter"),
				new TreeItem<>("Member: Jake"),
				new TreeItem<>("Member: Mary"),
				new TreeItem<>("Member: Celize")
		);
		classOne.setExpanded(true);
		TreeItem<String> classTwo = new TreeItem<>("Group Two"); //not to be confused with the nodes in scenegraph.
		classTwo.getChildren().addAll(
				new TreeItem<>("Leader: Tracy"),
				new TreeItem<>("Member: Jupiter"),
				new TreeItem<>("Member: Sheila"),
				new TreeItem<>("Member: Ariel"),
				new TreeItem<>("Member: Dominic")
		);
		//classTwo.setExpanded(true);
		
		
		classTwo.addEventHandler(TreeItem.branchExpandedEvent(), e -> System.out.print(e.getTreeItem().getValue()));

		classGroup.getChildren().addAll(classOne, classTwo);
		treeView.setRoot(classGroup);
		//treeView.setShowRoot(false);
		
		treeView.setCellFactory(e -> {
			TreeCell<String> cell = new TreeCell<>() {
				public void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					setText(null);
					setGraphic(null);
					if (!empty) {
						if(this.getTreeItem().getValue().equals("Member: Sheila")) {
							setText(item);
							setGraphic(new Circle(8, Color.MAGENTA));
							
						} else {
							setText(item);
							setGraphic(new Rectangle(15,15, Color.RED));
						}
					}
				} 
			};
			return cell;
		});
		treeView.setEditable(true); //if this is true, then the drawings would disappear.
		treeView.setCellFactory(TextFieldTreeCell.forTreeView());
		treeView.setOnEditCommit(e ->{
			e.getTreeItem().setValue(e.getNewValue());
		});
		
		treeView.getRoot().setExpanded(true); //use this to expand at once during the load time.
		
		
		root.getChildren().addAll(treeView); // This is the real node in the scene graph.
		
		
		
		
		Scene scene = new Scene(root, 500, 450);
		primaryStage.setTitle("TreeView Demo");
		primaryStage.setScene(scene);
		primaryStage.show();
	
	
	}
	
	
	
	
	public static void main(String[] args) {
		launch();
	}
	

}
