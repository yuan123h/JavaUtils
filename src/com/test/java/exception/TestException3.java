package com.test.java.exception;

/**
 * throw 的使用
 * @author yuanhuan
 *
 */
public class TestException3 {
	
	public static void throwChecked(int a) throws Exception {
		if (a < 0) {
			/**
			 * 自行抛出Exception异常 改代码必须处于try块里，或处于带throws声明的方法中
			 */
			throw new Exception("a的值大于0，不符合要求");
		}
	}

	public static void throwRuntime(int a) {
		if (a < 0) {
			/**
			 * 自行抛出RuntimeException异常，既可以显式捕获该异常 也可以完全不用理会该异常，把该异常交给方法的调用者处理
			 */
			throw new RuntimeException("a的值大于0，不符合要求");
		} else {
			System.out.println("a的值为：" + a);
		}
	}

	public static void main(String[] args) {
		try {
			/**
			 * 此处调用了带throws声明的方法，必须显示捕获该异常（使用try...catch）
			 *  否则，要在main方法中再次声明抛出
			 */
			throwChecked(-3);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		throwRuntime(3);
	}
}