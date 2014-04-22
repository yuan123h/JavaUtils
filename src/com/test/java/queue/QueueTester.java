package com.test.java.queue;

import java.io.PrintStream;
import java.util.LinkedList;
import java.util.Queue;

public class QueueTester {
	public Queue q;

	public QueueTester() {
		this.q = new LinkedList();
	}

	public void testFIFO(PrintStream out) {
		q.offer("First");
		q.offer("Second");
		q.offer("Third");

		Object o;
		while ((o = q.poll()) != null) {
			out.println(o);
		}
	}

	public static void main(String[] args) {
		QueueTester tester = new QueueTester();
		tester.testFIFO(System.out);
	}

}
