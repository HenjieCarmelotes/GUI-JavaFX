package com.jmc.mavenfx;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleObjectProperty;
import java.time.LocalDate;


public class User {
	
	private final StringProperty firstName;
	private final StringProperty lastName;
	private final ObjectProperty<LocalDate> birthDate;
	
	public User(String firstName, String lastName, LocalDate birthdate) {
		this.firstName = new SimpleStringProperty(this, "firstName", firstName);
		this.lastName = new SimpleStringProperty(this, "lastName", lastName);
		this.birthDate = new SimpleObjectProperty<>(this, "birthDate", birthdate);
	
		
	}
	
	public String getFirstName() {
		return firstName.get();
		
	}
	
	public String getLastName() {
		return lastName.get();
	}
	
	public LocalDate getBirthDate() {
		return birthDate.get();
	}
	
	public void setFirstName(String firstName) {
		this.firstName.set(firstName);
	}
	
	public void setBirthDate(LocalDate birthdate) {
		this.birthDate.set(birthdate);
	}
	
	public String toString() {
		return this.getFirstName() + " " + this.getLastName() + " "  + this.getBirthDate();
	}
	

}
