package com.test.java;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.junit.Test;

public class TestDouble {
	@Test
	public void testZero() {
		Double a = 0.0;
		if (a == 0)
			System.out.println("a");
	}

	@Test
	public void testDivide1() {
		DecimalFormat df = new DecimalFormat("#.00");
		System.out.println(df.format((float)10 / 3));
	}

	@Test
	public void testDivide2() {
		System.out.println(String.format("%.2f", new Double(10.0 / 7)));
	}

	@Test
	public void testDivide3() {
		NumberFormat nf = NumberFormat.getNumberInstance();
		nf.setMaximumFractionDigits(2);
		System.out.println(nf.format(20 / 3.0));
	}

	@Test
	public void testDivide4s() {
		BigDecimal bg = new BigDecimal(2/3.0);
		double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		System.out.println(f1);
	}
}
