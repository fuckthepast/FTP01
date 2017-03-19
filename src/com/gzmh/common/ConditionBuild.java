package com.gzmh.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class ConditionBuild implements Serializable {

	private static final long serialVersionUID = 2143185091698693370L;

	private Class entityClass; // 实体对象
	private List<FieldDataBuild> fieldList;
	private List<FieldDataBuild> fieldListTemp;
	private String[] sort; // 排序字段集
	private String[] dir; // 排序方式集

	public ConditionBuild() {
		fieldListTemp = new ArrayList<FieldDataBuild>();
	}

	/**
	 * 
	 * @param entityClass
	 *            实体对象
	 * @param fieldList
	 *            过滤条件集
	 * @param sort
	 *            实体对象字段名
	 * @param dir
	 *            排序方式
	 */
	public ConditionBuild(Class entityClass, List<FieldDataBuild> fieldList, String[] sort, String[] dir) {
		this.entityClass = entityClass;
		this.fieldList = fieldList;
		this.sort = sort;
		this.dir = dir;
	}

	/**
	 * 移除过滤条件对象
	 * 
	 * @param fieldName
	 */
	public void removeFieldObj(String fieldName) {
		if (fieldName != null) {
			if (fieldListTemp == null)
				fieldListTemp = new ArrayList<FieldDataBuild>();
			fieldListTemp.addAll(this.fieldList);
			for (FieldDataBuild fieldDataBuild : fieldListTemp) {
				String fn = fieldDataBuild.getFieldName();
				if (fn.indexOf(".") != -1) {
					fn = fn.substring(fn.indexOf(".") + 1);
				}
				if (fn.equals(fieldName))
					fieldList.remove(fieldDataBuild);
			}
		}
	}

	/**
	 * 添加过滤条件对象
	 * 
	 * @param field
	 */
	public void setFieldObj(FieldDataBuild field) {
		boolean b = true;
		if (this.fieldList != null) {
			for (FieldDataBuild fieldDataBuild : fieldList) {
				String fieldName = fieldDataBuild.getFieldName();
				if (fieldName.indexOf(".") != -1) {
					fieldName = fieldName.substring(fieldName.indexOf(".") + 1);
				}
				if (fieldName.equals(field.getFieldName())) {
					fieldDataBuild.setFieldValue(field.getFieldValue());
					b = false;
				}
			}
		}
		if (b)
			this.fieldList.add(field);
	}

	/**
	 * 关联属性对象处理
	 * 
	 * @param relevenceProp
	 *            关联对象属性名
	 * @param fieldName
	 *            关联对象中的属性名
	 */
	public void relevanceFieldHandle(String relevenceProp, String fieldName) {
		if (this.fieldList != null) {
			for (FieldDataBuild fieldDataBuild : fieldList) {
				if (fieldDataBuild.getFieldName().equals(fieldName)) {
					if (fieldName.length() > 2 && fieldName.indexOf("Id") != -1 && fieldName.indexOf(".") == -1)
						fieldName = "id";
					fieldDataBuild.setFieldName(relevenceProp + "." + fieldName);
				}
			}
		}
	}

	public Class getEntityClass() {
		return entityClass;
	}

	public void setEntityClass(Class entityClass) {
		this.entityClass = entityClass;
	}

	public List<FieldDataBuild> getFieldList() {
		return fieldList;
	}

	public void setFieldList(List<FieldDataBuild> fieldList) {
		this.fieldList = fieldList;
	}

	public String[] getSort() {
		return sort;
	}

	public void setSort(String[] sort) {
		this.sort = sort;
	}

	public String[] getDir() {
		return dir;
	}

	public void setDir(String[] dir) {
		this.dir = dir;
	}
}
