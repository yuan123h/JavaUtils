package com.test.java.annotation;

import java.io.PrintStream;
import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.hive.serde2.ByteStream.Input;
import org.junit.Test;

public class TestBasic {
	@Deprecated
	public void doSomething() {

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void do1() {
		List list = new ArrayList();
		list.add("sss");
	}

	@Documented
	@Target({ ElementType.METHOD, ElementType.FIELD })
	@Retention(RetentionPolicy.CLASS)
	public @interface TODO {
		String value();
	}

	public @interface GROUPTODO {
		String value();

		String assignedTo();
	}

	@TODO("a")
	public void test1() {

	}

	@GROUPTODO(assignedTo = "", value = "")
	public void test2() {

	}

	// /////////////////////////////////////

	@Documented
	@Inherited
	@Retention(RetentionPolicy.RUNTIME)
	public @interface InProgress {
		String mark();
	}

	@InProgress(mark="level2")
	public class Super {
		@InProgress(mark="level2")
		public void print() {
			System.out.println("super printing ...");
		}
	}

	public class Sub extends Super {
		@Override
		public void print() {
			super.print();
		}
	}

	@Test
	public void testAnnotationPresent() {
		Class c = Super.class;
		boolean inProgress = c.isAnnotationPresent(InProgress.class);
		if (inProgress) {
			System.out.println("Super is in progress.");
		} else {
			System.out.println("Super is not in progress.");
		}

	}

	@Test
	public void testAnnotationPresentInherited() {
		Class c = Sub.class;
		boolean inProgress = c.isAnnotationPresent(InProgress.class);
		if (inProgress) {
			System.out.println("Sub is in progress.");
		} else {
			System.out.println("Sub is not in progress.");
		}

	}

	@Test
	public void testGetAnnotation() throws SecurityException, NoSuchMethodException {
		Class c = Super.class;
		Method method = c.getMethod("print");
		InProgress inProgress = method.getAnnotation(InProgress.class);
		String mark = inProgress.mark();
		System.out.println("InProgress item on Annotation Tester is assigned to '"
				+ mark + "'");
	}
	
	@Test
	public void testGetAnnotation2() throws SecurityException, NoSuchMethodException {
		Class c = Super.class;
		Method method = c.getMethod("print");
		new TestBasic().printAnnotations(method, System.out);
	}
		
	public void printAnnotations(AnnotatedElement e, PrintStream out) {
		out.printf("print annotations for '%s'%n%n", e.toString());
		Annotation[] annotations = e.getAnnotations();
		for (Annotation a : annotations) {
			out.printf("  * Annotation '%s' fount %n", a.annotationType().getName());
		}
	}
}
