package com.guava.test;

import org.junit.Test;

import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ComparisonChain;

/**
 * Guava-Libraries是google对java的一个扩展，主要涵盖集合、缓存、并发、I/O、反射等等。</br>
 * Guava是google封装以后的东西，肯定经过了很多实践和测试。但是有些东西用着还是比较奇怪，感觉封装以后更混乱了。大家挑选着合适的用吧。
 * 
 * @author yuanhuan
 * 
 */
public class TestBasic {
	@Test
	public void testBasic() {
		Optional possible = Optional.of(5);
		if (possible.isPresent())
			System.out.println(possible.get());

		Optional absentValue = Optional.absent();
		if (absentValue.isPresent()) {
			System.out.println(absentValue.get());
		}
		System.out.println(absentValue.or(-1));
		System.out.println(absentValue.orNull());
		System.out.println(absentValue.asSet());
	}

	/**
	 * 如果不满足，抛出错误
	 */
	@Test
	public void testCondition() {
		int i = Preconditions.checkNotNull(null);
		int i1 = Preconditions.checkNotNull(null, "错误");
		int i2 = Preconditions.checkNotNull(null, "错误：%s", "001");
	}

	@Test
	public void testObjects() {
		System.out.println("a equal a : " + Objects.equal("a", "a"));
		System.out.println("a equal b : " + Objects.equal("a", "b"));
		System.out.println("hascode : "
				+ Objects.hashCode("one", "two", 3, 4.0));
		System.out.println("Blog toString : "
				+ Objects.toStringHelper("Blog").add("name", "huang")
						.add("url", "http://htynkn.cnblogs.com/").toString());
		System.out.println(ComparisonChain.start().compare(1.0, 1)
				.compare("a", "a").compare(1.4411, 1.441).result());

	}
}
