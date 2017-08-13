package com.sf.observer;

public class OctalOvserver extends Observer {

	public OctalOvserver(Subject subject) {
		this.subject = subject;
		this.subject.attach(this);
	}

	@Override
	public void update() {
		System.out.println("Octal String: "
				+ Integer.toOctalString(subject.getState()).toUpperCase());
	}

}
