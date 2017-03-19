package com.gzmh.dao.impl;

import java.util.List;

import com.gzmh.common.CommonDAOImpl;
import com.gzmh.domain.Code;

@SuppressWarnings("unchecked")
public class CodeDAOImpl extends CommonDAOImpl {

	public List<Code> findBy(String field, Object fieldValue)
	{
		StringBuffer hql =new StringBuffer("FROM Code c WHERE c.");
		hql.append(field);
		hql.append(" = ? order by c.codeNum");
		return getHibernateTemplate().find(hql.toString(),
				new Object[] {fieldValue});
	}

	public List<Code> findBy(String field, Object fieldValue, String sysId)
	{
		StringBuffer hql =new StringBuffer(
				"FROM Code c WHERE c.shiyanshiInfo.id=? and c.");
		hql.append(field);
		hql.append(" = ? order by c.codeNum");
		return getHibernateTemplate().find(hql.toString(),
				new Object[] {sysId, fieldValue});
	}
}
