package com.test.java.thread.tiger;

import java.io.PrintStream;
import java.util.Date;
import java.util.concurrent.BlockingQueue;

public class Producer extends Thread {
	private BlockingQueue q;
	private PrintStream out;

	public Producer(BlockingQueue q, PrintStream out) {
		setName("Producer");
		this.q = q;
		this.out = out;
	}

	@Override
	public void run() {
		try {
			while (true) {
				q.put(produce());
			}
		} catch (InterruptedException e) {
			out.printf("%s interrupted: %s", getName(), e.getMessage());
		}
	}

	private Object produce() {
		while(true) {
			double r = Math.random();
			if ((r*100) < 10) {
				String s = String.format("Inserted at %tc", new Date());
				return s;
			}
		}
	}
}
