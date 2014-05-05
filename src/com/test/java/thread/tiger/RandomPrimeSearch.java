package com.test.java.thread.tiger;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class RandomPrimeSearch implements Callable<BigInteger> {
	private static final Random prng = new SecureRandom();
	private int bitsize;

	public RandomPrimeSearch(int bitsize) {
		this.bitsize = bitsize;
	}

	@Override
	public BigInteger call() throws Exception {
		return BigInteger.probablePrime(bitsize, prng);
	}

	public static void main(String[] args) {
		ExecutorService service = Executors.newFixedThreadPool(4);
		Future<BigInteger> prime1 = service.submit(new RandomPrimeSearch(512));
		Future<BigInteger> prime2 = service.submit(new RandomPrimeSearch(512));
		Future<BigInteger> prime3 = service.submit(new RandomPrimeSearch(512));

		try {
			BigInteger bigger = prime1.get().multiply(prime2.get())
					.multiply(prime3.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

		FutureTask<BigInteger> task = new FutureTask<BigInteger>(
				new RandomPrimeSearch(512));
		new Thread(task).start();
		try {
			BigInteger result = task.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

		// Runnable 实现 无返回结果 的情况
		Runnable r = new Runnable() {

			@Override
			public void run() {

			}
		};

		FutureTask<String> myTask = new FutureTask<String>(r, "success");
		FutureTask<Boolean> myTask1 = new FutureTask<Boolean>(r, true);
		FutureTask<Object> myTask2 = new FutureTask<Object>(r, null);

	}
}
