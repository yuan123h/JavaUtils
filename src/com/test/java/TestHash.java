package com.test.java;

import java.util.HashSet;
import java.util.Set;

import org.apache.hadoop.util.bloom.Key;
import org.apache.hadoop.util.hash.Hash;
import org.junit.Test;

public class TestHash {

	@Test
	public void testHash() {
		Hash hashFunction = Hash.getInstance(Hash.JENKINS_HASH);
		int[] result = TestHash
				.hash(new Key(
						"JD1000001249    31598780        送妈妈，没有自己用      4       瓜瓜鱼- 1324536000000   1325648087000   5"
								.getBytes()), 10, 160000000, hashFunction);
		for (int i : result) {
			System.out.println(i);
		}
	}

	public static int[] hash(Key k, int nbHash, int maxValue, Hash hashFunction) {
		byte[] b = k.getBytes();
		if (b == null) {
			throw new NullPointerException("buffer reference is null");
		}
		if (b.length == 0) {
			throw new IllegalArgumentException("key length must be > 0");
		}
		int[] result = new int[nbHash];
		for (int i = 0, initval = 0; i < nbHash; i++) {
			initval = hashFunction.hash(b, initval);
			result[i] = Math.abs(initval % maxValue);
		}
		return result;
	}
	
	@Test
	public void testSet() {
		Set<String> urls = new HashSet<String>();
		urls.add("aaa");
		urls.add("bbb");
		urls.add("aaa");
		for (String s : urls) {
			System.out.println(s);
		}
	}
	
	
}
