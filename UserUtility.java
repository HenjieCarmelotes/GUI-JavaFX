package com.jmc.mavenfx;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.util.converter.LocalDateStringConverter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;


public class UserUtility {
	
	//no need to create objects for utility classes only static parts.
	
	public static ObservableList<User> getUserList() {
		return FXCollections.observableArrayList(
				new User("Sam", "Smith", LocalDate.of(1997, 10, 1)),
				new User("Jhon", "Doe", LocalDate.of(1947, 12, 23)),
				new User("Sheila", "Stane", LocalDate.of(1997, 1, 1))
				);
	}
	
	public static TableColumn<User, String> getFirstNameCol() {
		TableColumn <User, String> fNameCol = new TableColumn<>("First Name");
		fNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		fNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
		fNameCol.setOnEditCommit(e -> {
			//the cell will be update in the back.
			System.out.print(e.getNewValue());
			System.out.print(e.getRowValue());
		});
		
		
		
		return fNameCol;
		
	}
	
	public static TableColumn<User, String> getLastNameCol() {
		TableColumn <User, String> lNameCol = new TableColumn<>("Last Name");
		lNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		
		lNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
		lNameCol.setOnEditCommit(e -> {
			//set the cell value
			e.getRowValue().setFirstName(e.getNewValue());
			System.out.println(e.getRowValue());
		});
		
		return lNameCol;
		
	}
	
	public static TableColumn<User, LocalDate> getBirthdateCol() {
		
		TableColumn <User, LocalDate> birthDate = new TableColumn<>("Birth Date");
		birthDate.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
		String datePattern = "yyyy-MM-dd";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);
		//to me, creating a custom converter is better because this converter is not good
		//in handling, illegal input.
		LocalDateStringConverter converter = new LocalDateStringConverter(formatter,null);
		birthDate.setCellFactory(TextFieldTableCell.forTableColumn(converter));
		birthDate.setOnEditCommit(e -> {
			e.getRowValue().setBirthDate(e.getNewValue());
		});
		
		return birthDate;
		
	}
	
	
	
	
	public static TableColumn<User, Button> getDeleteUserColumn() {
		
		TableColumn <User, Button> deleteUser = new TableColumn<>("Delete");
		deleteUser.setCellFactory(col -> new TableCell<>() {
			public void updateItem(Button item, boolean empty) {
				super.updateItem(item, empty);
				setText(null);
				setGraphic(null);
				if(!empty) {
					Button bt = new Button ("Delete");
					bt.setOnAction(e -> {
						int rowIndex = this.getTableRow().getIndex();
						this.getTableView().getItems().remove(rowIndex);
					});
					setText(null);
					setGraphic(bt);
				}
			};
		});
		return deleteUser;
	}

}
