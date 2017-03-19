package com.gzmh.common;

import java.io.Serializable;

@SuppressWarnings("unchecked")
public class BuildHQL implements Serializable {

	private static final long serialVersionUID = 5074741816334908443L;

	/**
	 * 构建获取数据集语句
	 * 
	 * @return
	 */
	public static String buildingHql(ConditionBuild build) {
		if (build.getEntityClass() != null) {
			StringBuffer hql = new StringBuffer("SELECT entity FROM ");
			hql.append(build.getEntityClass().getName());
			hql.append(" entity where 1 = 1 ");
			buildCondition(build, hql);
			if (build.getSort() != null && build.getSort().length > 0) {
				hql.append(" order by");
				for (int i = 0; i < build.getSort().length; i++) {
					String field = build.getSort()[i];
					if (field != null && field.indexOf("_") != -1)
						field = field.replaceAll("\\_", ".");
					hql.append(" entity.");
					hql.append(field);
					hql.append(" ");
					if (build.getDir().length > i)
						hql.append(build.getDir()[i]);
					if (i < build.getSort().length - 1)
						hql.append(",");
				}
			}
			return hql.toString();
		}
		return null;
	}

	/**
	 * 构建查询总数语句
	 * 
	 * @param build
	 * @return
	 */
	public static String buildingCountHql(ConditionBuild build) {
		if (build.getEntityClass() != null) {
			StringBuffer hql = new StringBuffer("SELECT COUNT(*) FROM ");
			hql.append(build.getEntityClass().getName());
			hql.append(" entity where 1 = 1 ");
			buildCondition(build, hql);
			return hql.toString();
		}
		return null;
	}

	/**
	 * 根据build封装的过滤条件组装hql
	 * 
	 * @param build
	 * @param hql
	 */
	private static void buildCondition(ConditionBuild build, StringBuffer hql) {
		if (build.getFieldList() != null && build.getFieldList().size() > 0) {
			for (FieldDataBuild fieldData : build.getFieldList()) {
				String field = fieldData.getFieldName();
				if (field != null && field.indexOf("_") != -1)
					field = field.replaceAll("\\_", ".");
				if (!"OR".equals(fieldData.getFieldType())) {
					hql.append(" AND entity.");
					hql.append(field);
					hql.append(" ");
					if (fieldData.getFieldType() != null) {
						if (field != null) {
							hql.append(fieldData.getFieldType());
							if (fieldData.getFieldValue() != null) {
								if (fieldData.getFieldValue() instanceof String) {
									if (fieldData.getFieldType().indexOf("IN") != -1) {
										hql.append(" (");
										hql.append(fieldData.getFieldValue());
										hql.append(") ");
									} else if (fieldData.getFieldType().equals("LIKE")) {
										hql.append("'%");
										hql.append(fieldData.getFieldValue());
										hql.append("%' ");
									} else {
										hql.append("'");
										hql.append(fieldData.getFieldValue());
										hql.append("' ");
									}
								} else if (fieldData.getFieldValue() instanceof Integer) {
									hql.append(fieldData.getFieldValue());
									hql.append(" ");
								}
							}
						}
					} else {
						if (fieldData.getFieldValue() instanceof String) {
							hql.append(" LIKE '%");
							hql.append(fieldData.getFieldValue());
							hql.append("%' ");
						} else if (fieldData.getFieldValue() instanceof Integer) {
							hql.append("=");
							hql.append(fieldData.getFieldValue());
							hql.append(" ");
						}
					}
				} else {
					if ((!"".equals(field)) && fieldData.getFieldValue() != null) {
						if (field.indexOf(",") != -1 && fieldData.getFieldValue().toString().indexOf(",") != -1) {
							String[] fields = field.split(",");
							String[] values = fieldData.getFieldValue().toString().split(",");
							hql.append(" AND (");
							for (int i = 0; i < fields.length; i++) {
								hql.append(" entity.");
								hql.append(fields[i]);
								hql.append(" LIKE '%");
								hql.append(values[i]);
								if (i == fields.length - 1)
									hql.append("%') ");
								else
									hql.append("%' OR ");
							}
						}
					}
				}
			}
		}
	}
}
