package com.gzmh.service.impl;

import java.util.List;

import com.gzmh.common.BuildHQL;
import com.gzmh.common.ConditionBuild;
import com.gzmh.dao.impl.CodeDAOImpl;
import com.gzmh.domain.Code;
import com.gzmh.service.ICodeService;

@SuppressWarnings("unchecked")
public class CodeServiceImpl implements ICodeService {

	private CodeDAOImpl codeDao;



	public void setCodeDao(CodeDAOImpl codeDao)
	{
		this.codeDao =codeDao;
	}

	public Code findById(String id)
	{
		return (Code) codeDao.get(Code.class, id);
	}

	public int findCount()
	{
		return codeDao.findCount(Code.class);
	}

	public int findCount(ConditionBuild build)
	{
		build.relevanceFieldHandle("codeType", "codeTypeName");
		build.relevanceFieldHandle("codeType", "codeTypeNum");
		String hql =BuildHQL.buildingCountHql(build);
		return codeDao.findCount(hql);
	}

	public List<Code> findPages(ConditionBuild build, int pageNum, int pageSize)
	{
		build.relevanceFieldHandle("codeType", "codeTypeName");
		build.relevanceFieldHandle("codeType", "codeTypeNum");
		String hql =BuildHQL.buildingHql(build);
		return codeDao.findByPages(hql, pageNum, pageSize);
	}

	public List<Code> findByCodeType(String codeType)
	{
		return codeDao.findBy("codeType.codeTypeNum", codeType);
	}

	public List<Code> findBySysId(String codeType, String sysId)
	{
		return codeDao.findBy("codeType.codeTypeNum", codeType, sysId);
	}
}
