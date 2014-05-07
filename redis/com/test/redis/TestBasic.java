package com.test.redis;

import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.Jedis;

public class TestBasic {
	public static void test1(String[] args) {
		// 连接redis服务
		Jedis jedis = new Jedis("10.124.60.11", 6379);
/*		String value = jedis.hget("mystock3", "grade");
		if (value == null)
			value = "0";
		jedis.hset("mystock3", "grade",
				String.valueOf(Integer.parseInt(value) + 1));
		value = jedis.hget("mystock3", "grade");

		System.out.println(value);*/
		
		System.out.println("==============");
		jedis.hincrBy("tj:taobao", "grade1",1);
		String value = jedis.hget("tj:taobao", "grade1");
		System.out.println(value);
		

	}
	
	@Test
	public void testDelete() {
		Jedis jedis = new Jedis("localhost", 6379);
		Set<String> keys = jedis.keys("tj:*");
		for (String s : keys) {
			jedis.del(s);
		}
	}
}
