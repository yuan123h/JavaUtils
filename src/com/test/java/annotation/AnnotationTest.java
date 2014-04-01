package com.test.java.annotation;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 注解测试类，获取注解关键在反射</br>
 * 通过获取注解中的参数给字段赋值
 * @author yuanhuan
 * 
 */
public class AnnotationTest {

	public static void main(String[] args) {
		AnnotationTest test = new AnnotationTest();
		test.resolve();
		test.testDoIt();
	}

	/**
	 * 解析如何获取注解对应注解中的值
	 */

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void resolve() {
		try {
			// 获取对应的类 www.2cto.com
			Class clazz = Class.forName("com.test.java.annotation.AnnotationUser");
			// 判断clazz是否存在FirstAnno.class注解
			if (clazz.isAnnotationPresent(AnnotationType.class)) {
				// 存在，则获取这个注解
				AnnotationType annoType = (AnnotationType) clazz
						.getAnnotation(AnnotationType.class);
				System.out.println("AnnotationType value： "
						+ annoType.typeValue());
			}

			// 获取该类的所有方法
			Method[] methods = clazz.getDeclaredMethods();
			// 解析方法注解
			for (Method method : methods) {
				if (method.isAnnotationPresent(AnnotationMethod.class)) {
					AnnotationMethod annoMethod = method
							.getAnnotation(AnnotationMethod.class);
					System.out.println("AnnotationMethod value： "
							+ annoMethod.methodValue());
				}
			}

			// 获取该类的所有属性字段
			Field[] fields = clazz.getDeclaredFields();
			// 解析字段注解
			for (Field field : fields) {
				if (field.isAnnotationPresent(AnnotationField.class)) {
					AnnotationField annoField = field
							.getAnnotation(AnnotationField.class);
					System.out.println("AnnotationField value： "
							+ annoField.fieldValue());
				}
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 解释注解中的值，并且赋值给相关属性或者方法
	 */
	public void testDoIt() {
		try {
			AnnotationUser user = new AnnotationUser();
			user.doIt();
			Field field = user.getClass().getDeclaredField("userField");
			if (field.isAnnotationPresent(AnnotationField.class)) {
				AnnotationField annoField = field
						.getAnnotation(AnnotationField.class);

				// getDeclaredMethod()返回一个 Method 对象，该对象反映此 Class
				// 对象所表示的类或接口的指定已声明方法。name 参数是一个
				// String，它指定所需方法的简称，parameterTypes 参数是 Class 对象的一个数组
				// Method doIt = user.getClass().getDeclaredMethod("doIt");

				// 属性必须要由set 或者get 方法，才能调用invoke方法
				PropertyDescriptor pd = new PropertyDescriptor(field.getName(),
						AnnotationUser.class);
				Method doIt = pd.getWriteMethod();

				if (null != doIt) {
					String value = annoField.fieldValue();
					doIt.invoke(user, value);
				}
			}
			user.doIt();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
