package com.gzmh.service.impl;

import com.gzmh.dao.impl.CodeTypeDAOImpl;
import com.gzmh.domain.CodeType;
import com.gzmh.service.ICodeTypeService;

@SuppressWarnings("unchecked")
public class CodeTypeServiceImpl implements ICodeTypeService {

	private CodeTypeDAOImpl codeTypeDao;



	public void setCodeTypeDao(CodeTypeDAOImpl codeTypeDao)
	{
		this.codeTypeDao =codeTypeDao;
	}

	public CodeType findById(String id)
	{
		return (CodeType) codeTypeDao.get(CodeType.class, id);
	}

}
