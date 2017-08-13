package com.sf.observer;

public class DesignPatternDemo {

	public static void main(String[] args) {
		Subject subject = new Subject();
		new HexOvserver(subject);
		new OctalOvserver(subject);

		System.out.println("��һ�α仯12");
		subject.setState(12);
		subject.nitifyAll();

		System.out.println("��һ�α仯10");
		subject.setState(10);
		subject.nitifyAll();
	}

}
