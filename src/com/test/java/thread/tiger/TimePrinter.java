package com.test.java.thread.tiger;

import java.io.PrintStream;
import java.util.Date;

public class TimePrinter implements Runnable {
	private PrintStream out;
	
	public TimePrinter(PrintStream out) {
		this.out = out;
	}
	
	@Override
	public void run() {
		out.printf("Current time %tr%n", new Date());
	}

}
