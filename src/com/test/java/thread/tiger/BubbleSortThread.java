package com.test.java.thread.tiger;

public class BubbleSortThread extends Thread {
	private int[] numbers;

	public BubbleSortThread(int[] numbers) {
		setName("Simple Thread");
		setUncaughtExceptionHandler(new SimpleThreadExceptionHandler());
		this.numbers = numbers;
	}
	
	@Override
	public void run() {
		int index = numbers.length;
		boolean finished = false;
		while (!finished) {
			index --;
			finished = true;
			for (int i=0; i<index; i++) {
				if (numbers[i+1] < 0) {
					throw new IllegalArgumentException("Can not pass negative numbers in this thread!");
				}
				
				if (numbers[i] > numbers[i+1]) {
					int temp = numbers[i];
					numbers[i] = numbers[i+1];
					numbers[i+1] = temp;
					finished = false;
				}
			}
		}
	}

	class SimpleThreadExceptionHandler implements
			Thread.UncaughtExceptionHandler {

		@Override
		public void uncaughtException(Thread t, Throwable e) {
			System.err.printf("%s: %s at line %d of %s%n", t.getName(),
					e.toString(), e.getStackTrace()[0].getLineNumber(),
					e.getStackTrace()[0].getFileName());
		}

	}
}
