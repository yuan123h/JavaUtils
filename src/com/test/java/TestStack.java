package com.test.java;

import java.util.Stack;

import org.junit.Test;

public class TestStack {
	@Test
	public void test() {
		Stack<String> stack = new Stack<String>();
		stack.push("aaa");
		stack.push("bbb");
		stack.push("ccc");
		while (!stack.empty())
			System.out.println(stack.pop());
	}
}
