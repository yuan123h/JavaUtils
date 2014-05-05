package com.test.java.thread.tiger;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FtpServer {
	private ExecutorService service;
	
	public FtpServer(int port) throws IOException{
		openSocket(port);
		service  = Executors.newFixedThreadPool(100);
	}

	private void openSocket(int port) {
		// TODO Auto-generated method stub
		
	}
	
	public void go() {
		while(true){
			service.execute(new FtpHandler(getSocket()));
		}
	}

	private Socket getSocket() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void stop() {
		service.shutdown();
	}
	
	public class FtpHandler implements Runnable {
		private Socket socket;
		public FtpHandler(Socket socket){
			this.socket = socket;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub

		}

	}
}

