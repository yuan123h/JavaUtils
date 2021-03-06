package com.test.java.thread;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

public class MulThread {
	public static void main(String[] args) throws InterruptedException,
			ExecutionException {
		String resultStr = null;

		int[] typeIds = new int[5];

		BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(20);
		ThreadPoolExecutor executor = new ThreadPoolExecutor(20, 45, 10,
				java.util.concurrent.TimeUnit.MINUTES, queue,
				new ThreadPoolExecutor.CallerRunsPolicy());
		ArrayList<Future<JSONObject>> futureList = new ArrayList<Future<JSONObject>>();
		for (final Integer typeId : typeIds) {
			Future<JSONObject> future = executor
					.submit(new Callable<JSONObject>() {
						@Override
						public JSONObject call() throws Exception {
							JSONObject jsonObject = null;
							return jsonObject;
						}
					});
			futureList.add(future);
		}
		if (null != futureList) {
			JSONArray allArray = new JSONArray();
			for (Future<JSONObject> future : futureList) {
				try {
					JSONObject json = future.get();
					allArray.add(json);
				} catch (RuntimeException e) {
					// log.error("insert reportData error");
					e.printStackTrace();
				}
			}
			resultStr = allArray.toString();
		}

		System.out.println(resultStr);
	}

	// hbase 多线程实现
	ExecutorService readPool;

	public void t1() {
		readPool = Executors.newFixedThreadPool(3, new ThreadFactoryBuilder()
				.setNameFormat("demon").setDaemon(true).build());
		Thread featch = new Thread();
		readPool.execute(featch);
		
//		Executor e =  Executors.newFixedThreadPool(5);
//		e.execute(featch);
	}

}
