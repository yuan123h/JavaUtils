package com.test.java.exception;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

public class ExceptionTest {
	public static void main(String[] args) {
		System.out.println("Entering main()");
		ExceptionTest test = new ExceptionTest();

		try {
			System.out.println("Calling m1()");
			test.m1();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			// e.printStackTrace(System.err);
		}

	}

	public void m1() throws FileNotFoundException {
		System.out.println("Entering m1()");
		File f = new File("1.txt");
		BufferedReader reader = new BufferedReader(new FileReader(f));
	}

	// try catch 在循环外要比在 循环内 效率要快

	@Test
	public void testTCException() {
		this.tryCacheMethod1(10000000);
		//this.tryCacheMethod2(10000000);
	}

	void tryCacheMethod1(int size) {
		int[] ia = new int[size];
		try {
			for (int i = 0; i < size; i++)
				ia[i] = i*i;
		} catch (Exception e1) {
		}
	}

	void tryCacheMethod2(int size) {
		int[] ia = new int[size];
		for (int i = 0; i < size; i++)
			try {
				ia[i] = i*i;
			} catch (Exception e1) {
			}
	}

}
