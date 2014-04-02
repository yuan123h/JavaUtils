package com.test.java.lock;

public class RobotController extends Thread{
	private Robot robot1;
	private Robot robot2;
	
	public static void main(String[] args) throws InterruptedException {
		RobotController rc = new RobotController();
		rc.loadCommands( "abc".getBytes());
		rc.start();
		Thread.sleep(30*1000);
	}
	
	@Override
	public void run() {
		robot1 = new Robot(this);
		robot1.start();
		robot2 = new Robot(this);
		robot2.start();
	}
	
	public void loadCommands(byte[] b) {
		synchronized (this) {
			Robot.storeCommands(b);
			notifyAll();
		}
	}
	
}
