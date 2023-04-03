package com.jmc.mavenfx;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.concurrent.Worker;
import javafx.beans.binding.*;
import javafx.scene.control.*;
import javafx.collections.*;


public class WorkerUI extends GridPane {
	
	private final Label title = new Label("");
	private final Label message = new Label("");
	private final Label running = new Label("");
	private final Label state = new Label("");
	private final Label totalWork = new Label("");
	private final Label workDone = new Label("");
	private final Label progress = new Label("");
	private final ProgressBar progressBar = new ProgressBar();
	private final TextArea value = new TextArea();
	private final TextArea exception = new TextArea();
	
	public WorkerUI(Worker<ObservableList<Integer>> worker) {
		
		addUI();
		bindToWorker(worker);
		
		
	}
	
	public void addUI() {
		value.setPrefColumnCount(20);
		value.setPrefRowCount(4);
		value.setWrapText(true);
		exception.setPrefColumnCount(20);
		exception.setPrefRowCount(5);
		this.setHgap(5);
		this.setVgap(5);
		addRow(0,new Label("Title: "), title);
		addRow(1,new Label("Message: "), message);
		addRow(2,new Label("Running: "), running);
		addRow(3,new Label("State"), state);
		addRow(4,new Label("Total Work: "), totalWork);
		addRow(5,new Label("Work Done: "), workDone);
		addRow(6,new Label("Progress: "), new HBox(2,progressBar,progress));
		addRow(7,new Label("value: "), value);
		addRow(8,new Label("Exception: "), exception);
		
	}
	
	private void bindToWorker(Worker<ObservableList<Integer>> worker) {
		title.textProperty().bind(worker.titleProperty());
		message.textProperty().bind(worker.messageProperty());
		running.textProperty().bind(worker.runningProperty().asString());
		state.textProperty().bind(worker.stateProperty().asString());
		totalWork.textProperty().bind(new When(worker.totalWorkProperty().isEqualTo(-1))
				.then("Unknown")
				.otherwise(worker.totalWorkProperty().asString())
				);
		workDone.textProperty().bind(new When(worker.workDoneProperty().isEqualTo(-1))
				.then("Unknown")
				.otherwise(worker.workDoneProperty().asString()));
		
		progress.textProperty().bind(new When(worker.progressProperty().isEqualTo(-1))
				.then("Unknown ")
				.otherwise(worker.progressProperty().multiply(100).add(5).asString("%.2f%%")));
		
		progressBar.progressProperty().bind(worker.progressProperty());
		
		value.textProperty().bind(worker.valueProperty().asString());
		
		//exception.textProperty().bind(worker.exceptionProperty().asString());
		worker.exceptionProperty().addListener((obs,old,newVal) -> {
			if (newVal != null) {
				exception.setText(newVal.getMessage());
			} else {
				exception.setText(""); 
			}
		});
	}
	
	
	
	

}
