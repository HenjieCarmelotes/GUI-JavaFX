package com.jmc.mavenfx;
import javafx.beans.property.*;

public class Task {
	
	private final StringProperty task;
	
	public Task(String task) {
		this.task = new SimpleStringProperty(this, "task", task);
	}
	
	public String getTask() {
		return task.get();
	}
	
	public StringProperty getTaskProperty() {
		return this.task;
	}
	
	public String toString() {
		return this.task.get();
	}

}
