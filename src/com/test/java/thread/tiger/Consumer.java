package com.test.java.thread.tiger;

import java.io.PrintStream;
import java.util.concurrent.BlockingQueue;

public class Consumer extends Thread {
	private BlockingQueue q;
	private PrintStream out;

	public Consumer(String name, BlockingQueue q, PrintStream out) {
		this.q = q;
		this.out = out;
		setName(name);
	}

	@Override
	public void run() {
		try {
			while (true) {
				process(q.take());
			}
		} catch (InterruptedException e) {

		}
	}

	private void process(Object take) {
		out.printf("%s processing object: '%s' %n", getName(),
				take.toString());
	}
}
