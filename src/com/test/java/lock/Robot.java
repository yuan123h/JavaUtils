package com.test.java.lock;

public class Robot extends Thread {
	private static byte[] commands;
	private RobotController controller;

	@Override
	public void run() {
		System.out.println("me.");
		byte[] cmds;
		while (true) {
			synchronized (controller) {

				// 这里叫做旋锁
				while (commands == null) { // 当条件满足的时候，还应当再检查一次
					// if (commands == null) { //这里用if 可能会出现异常
					try {
						System.out.print(".");
						controller.wait();
					} catch (InterruptedException e) {
					}
				}
				cmds = new byte[commands.length];
				for (int i = 0; i < commands.length; i++) {
					cmds[i] = commands[i];
				}
				commands = null; //这句话不能放在循环里面
			}
			int size = cmds.length;
			for (int i = 0; i < size; i++) {
				processCommand(cmds[i]);
			}

		}
	}

	public Robot(RobotController controller) {
		this.controller = controller;
	}

	public static void storeCommands(byte[] b) {
		commands = b;
	}

	public void processCommand(byte b) {
		System.out.println(b);
	}

}
