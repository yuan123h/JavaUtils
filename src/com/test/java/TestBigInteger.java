package com.test.java;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

import org.junit.Test;

public class TestBigInteger {

	@Test
	public void testBasic() {
		BigInteger b1 = new BigInteger("11111111111111111111111111");
		BigInteger b2 = new BigInteger("22222222222222222222222222");
		System.out.println("加法运算:");
		System.out.println("b1:" + b1);
		System.out.println("b2:" + b2);
		System.out.println("相加结果值");
		System.out.println("相加后的值:" + b1.add(b2));
		
		//  生成素数
		Random prng = new SecureRandom();
		int bitsize = 1024;
		System.out.println(BigInteger.probablePrime(bitsize, prng));
	}
	
}
