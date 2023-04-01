package com.jmc.mavenfx;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;


public class TableViewDemo extends Application {
	
	private double startX, startY; //use to handle mouse dragging in TableView.
	
	
	@SuppressWarnings("unchecked")
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		VBox root = new VBox();
		TableView<User> usersTable = new TableView<>(UserUtility.getUserList());
		usersTable.setEditable(true);
		//multiple selection using key combinations. Ctrl or Shift then click individual cells.
		usersTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		
		//Handle MousePress and Drag
		
		usersTable.setOnMousePressed((MouseEvent event) -> {
            startX = event.getX();
            startY = event.getY();
        });
		
		usersTable.setOnMouseDragged((MouseEvent event) -> {
	            double endX = event.getX();
	            double endY = event.getY();
	            if (Math.abs(endX - startX) > 10 || Math.abs(endY - startY) > 10) {
	            	usersTable.getSelectionModel().clearSelection();
	                for (int i = 0; i < usersTable.getColumns().size(); i++) {
	                    TableColumn<User, String> col = (TableColumn<User, String>) usersTable.getColumns().get(i);
	                    for (int j = 0; j < usersTable.getItems().size(); j++) {
	                        if (isInSelectionRange(usersTable, col, j, startX, startY, endX, endY)) {
	                        	usersTable.getSelectionModel().select(j, col);
	                        }
	                    }
	                }
	            }
	        });
		
		
		
		usersTable.getColumns().addAll(
				UserUtility.getFirstNameCol(),
				UserUtility.getLastNameCol(),
				UserUtility.getBirthdateCol(),
				UserUtility.getDeleteUserColumn()
				);
		
		
		
		
		
		root.getChildren().addAll(usersTable);
		
		
		Scene scene = new Scene(root, 500, 450);
		primaryStage.setTitle("TableView Demo");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
	}
	
	
    private boolean isInSelectionRange(TableView<User> table, TableColumn<User, String> col, int rowIndex, double startX, double startY, double endX, double endY) {
        double cellX = table.localToScene(table.getLayoutBounds()).getMinX() + col.getWidth() + col.getWidth() * table.getColumns().indexOf(col);
        double cellY = table.localToScene(table.getLayoutBounds()).getMinY() + table.lookup(".table-row-cell").getBoundsInParent().getHeight() * rowIndex;
        return startX <= cellX && cellX <= endX && startY <= cellY && cellY <= endY;
    }
	
	

	public static void main(String[] args) {
		launch();
	}
	

}
