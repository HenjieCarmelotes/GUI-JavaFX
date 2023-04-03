package com.jmc.mavenfx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

public class EvenTask extends Task<ObservableList<Integer>> {
	
	private final int lowerLimit;
	private final int upperLimit;
	private final long sleepMillis;
	
	public EvenTask(int lowerLimit, int upperLimit, long SleepMillis) {
		this.lowerLimit = lowerLimit;
		this.upperLimit = upperLimit;
		this.sleepMillis = SleepMillis;
	}
	
	protected ObservableList<Integer> call() {
		ObservableList<Integer> result = FXCollections.observableArrayList();
		this.updateTitle("Even Number Finder Task ");
		int totalWork = this.upperLimit - this.lowerLimit+1;
		int workDone = 0;
		
		for (int i = lowerLimit; i < upperLimit; i++) {
			if (this.isCancelled()) {
				break;
			}
			workDone++;
			this.updateMessage("Checking if " + i + "is an even number");
			try {
				Thread.sleep(this.sleepMillis);
			} catch (InterruptedException e) {
				if (this.isCancelled()) {
					break;
				}
			}
			if (EvenUtil.isEven(i)) {
				result.add(i);
				this.updateValue(FXCollections.observableArrayList(result));
			}
			
			this.updateProgress(workDone, totalWork);
				
		}
		
		return result;
	
	
	}
	
	protected void canceled() {
		super.cancelled();
		this.updateMessage("Task was Canceled");
		
	}
	
	protected void failed() {
		super.failed();
		this.updateMessage("Task Failed");
	
	}
	
	protected void succeeded() {
		super.succeeded();
		this.updateMessage("Task Succeeded");
	}
	
	

}
