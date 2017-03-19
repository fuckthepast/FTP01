package com.gzmh.common;

import java.io.Serializable;

public class FieldDataBuild implements Serializable {

	private static final long serialVersionUID = 3642966390632803210L;

	private String fieldName; // 字段名称

	private Object fieldValue; // 过滤条件值

	private String fieldType;// 过滤操作符类型

	public FieldDataBuild() {
	}

	/**
	 * @param fieldName
	 *            过滤字段名称，不允许为空字符及null
	 * @param fieldValue
	 *            过滤条件值，当为null时，fieldType必须指定为 CommonPropertys.SELECT_TYPE_NULL或CommonPropertys.SELECT_TYPE_NOTNULL
	 * @param fieldType
	 *            过滤操作符类型，但为null时， 如果fieldValue为String，则fieldType为LIKE 如果fieldValue为Integer,则fieldType为=
	 *            如果fieldType为CommonPropertys.SELECT_TYPE_OR, fieldName=字段名1|字段名2；fieldValue=值1|值2
	 */
	public FieldDataBuild(String fieldName, Object fieldValue, String fieldType) {
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
		this.fieldType = fieldType;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public Object getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(Object fieldValue) {
		this.fieldValue = fieldValue;
	}

	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}
}
