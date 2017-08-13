package com.sf.observer;

import java.util.ArrayList;
import java.util.List;

public class Subject {
	private int state;
	private List<Observer> observers = new ArrayList<Observer>();

	public void attach(Observer su) {
		observers.add(su);
	}

	public void nitifyAll() {
		for (Observer observer : observers) {
			observer.update();
		}
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

}
