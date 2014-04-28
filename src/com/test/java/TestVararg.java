package com.test.java;

import org.junit.Test;

public class TestVararg {
	@Test
	public void testBasic() {
		Guitar guitar = new Guitar("", "", "s1", "s2");
		Guitar guitar1 = new Guitar("", "");
		this.max(5, 6);
		print(5, "s");
		
		String[] names = {"liming", "lilei", "lili", "lucy"};
		System.out.printf("Description of object array: %s\n",(Object)names );
	}

	public static void max(int... nums) {
		if (nums.length == 0) {
			throw new IllegalArgumentException("No values supplied");
		}
		int max = Integer.MIN_VALUE;
		for (int i : nums) {
			if (i > max)
				max = i;
		}
		System.out.println(max);

	}

	public static void print(Object... objects) {
		StringBuffer sb = new StringBuffer();
		for (Object o : objects) {
			sb.append(o).append(" ");
		}
		System.out.println(sb.toString());
	}

	public class Guitar {
		private String builder;
		private String model;

		public Guitar(String builder, String model, String... features) {
			for (String s : features) {
				System.out.println(s);
			}
		}
	}
}
