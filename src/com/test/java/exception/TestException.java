package com.test.java.exception;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 异常的测试 IOException 不会使程序退出。
 * <p>
 * 除非在try块或者catch块中调用了退出虚拟机的方法（即System.exit(1);），<br/>
 * 否则不管在try块、catch块中执行怎样的代码，出现怎样的情况，异常处理的<br/>
 * finally块总是会被执行的。不过，一般情况下，不要再finally块中使用<br/>
 * renturn或throw等导致方法终止的语句，因为一旦使用，将会导致try块、<br/>
 * catch块中的return、throw语句失效。<br/>
 * 
 * <p>
 * 尽量避免在finally块里使用return或throw等导致方法终止的语句，否则可能出现一些很奇怪的情况！
 * 
 * <p>
 * 虽然printStackTrace()方法可以很方便地追踪异常的发生状况，可以用它来调试，<br/>
 * 但是在最后发布的程序中， * 应该避免使用它。而应该对捕获的异常进行适当的处理，<br/>
 * 而不是简单的将信息打印出来。<br/>
 * 
 * @author yuanhuan
 * 
 */
public class TestException {
	/**
	 * 如果main 抛出异常的话，程序将会中断
	 * @param args
	 */
	public static void main(String[] args) {
		test01();

		try {
			test02();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("over..");
	}

	private static void test02() throws FileNotFoundException {
		FileInputStream fis = new FileInputStream("a.txt");
	}

	/**
	 * 异常的捕捉
	 */
	private static void test01() {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream("a.txt");
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
			// ioe.printStackTrace();
			// return语句强制方法返回
			return;
			// 使用exit来退出虚拟机，这样就不会执行后面的finally
			// System.exit(1);
		} finally {
			// 关闭磁盘文件，回收资源
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
			System.out.println("程序已经执行了finally里的资源回收");
		}
		System.out.println("end...");
	}
}
