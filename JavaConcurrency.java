package com.jmc.mavenfx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.Scene;
import javafx.concurrent.Service;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.collections.ObservableList;



public class JavaConcurrency extends Application {
	boolean started;
	Button startBtn = new Button("Start");
	Button resetBtn = new Button("Reset");
	Button cancelBtn = new Button("Cancel");
	Button exitBtn = new Button("Exit");
	
	
	//EvenTask task = new EvenTask(1,20,1000);
	ScheduledService<ObservableList<Integer>> service = new ScheduledService<>() {
		protected Task<ObservableList<Integer>> createTask() {
			return new EvenTask(1,20,100);
			
		}
	};

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		service.setPeriod(Duration.seconds(5));
	//	service.setMaximumCumulativePeriod(Duration.seconds(5));
		//service.setMaximumFailureCount(5);
		//service.setRestartOnFailure(started);
		startBtn.setOnAction(e ->{
			if (started)
				service.restart();
			else
				service.start();
		});
		
		cancelBtn.setOnAction(e -> {
			service.cancel();
			started = true;
			startBtn.setText("Restart");
		});
		
		exitBtn.setOnAction(e -> {
			Platform.exit();
		});
		
		resetBtn.setOnAction(e -> {
			service.reset();
		});
		
		GridPane pane = new WorkerUI(service);
		HBox box = new HBox(5, startBtn, resetBtn, cancelBtn, exitBtn);
		
		BorderPane root = new BorderPane();
		root.setCenter(pane);
		root.setBottom(box);
		
		Scene scene = new Scene(root, 500, 450);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
		
	}
	
	public static void main(String[] args) {
		launch();
	}
	

} //end class JavaConcurrency