package com.test.java.annotation;

/**
 * 注解使用者
 * @author yuanhuan
 *
 */
@AnnotationType(typeValue = "user Type value")
public class AnnotationUser {

	/** 字段属性 */
	@AnnotationField(fieldValue = "user Field value")
	private String userField;

	/**
	 * 使用者方法
	 */
	@AnnotationMethod(methodValue = "user method value")
	public String userMethod() {
		return "user default method value";
	}

	/**
	 * 做事
	 */
	public void doIt() {
		System.out.println(userField);
	}

	public String getUserField() {
		return userField;
	}

	public void setUserField(String userField) {
		this.userField = userField;
	}

}