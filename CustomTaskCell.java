package com.jmc.mavenfx;

import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.scene.layout.*;



public class CustomTaskCell extends ListCell<Task> {
	//override the method in the list cell.
	public void updateItem(Task item, boolean empty) {
		super.updateItem(item, empty);
		
		if (empty) {
			setText(null);
			setGraphic(null);
		} else {
			CheckBox cb = new CheckBox();
			Text txt = new Text(item.getTask());
			HBox cell = new HBox(10, cb, txt); //use for grouping
			cb.selectedProperty().addListener(e -> {
				if (cb.isSelected()) {
					txt.setStyle("-fx-strikethrough: true");
				} else {
					txt.setStyle("-fx-strikethrough: false");
				}
			});
			setText(null);
			setGraphic(cell);
			
		}
	}
	

}
