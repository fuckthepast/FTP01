package com.gzmh.service;

import java.util.List;

import com.gzmh.common.ConditionBuild;
import com.gzmh.domain.Code;

public interface ICodeService {
	/**
	 * 获取数据总数量
	 * 
	 * @return
	 */
	public int findCount();

	/**
	 * 根据条件获取总数
	 * 
	 * @param build
	 *            过滤条件集
	 * @return
	 */
	public int findCount(ConditionBuild conditionBuild);

	/**
	 * 分页查询
	 * 
	 * @param conditionList
	 *            过滤条件集
	 * @param pageNum
	 *            页码
	 * @param pageSize
	 *            页数
	 * @return
	 */
	public List<Code> findPages(ConditionBuild conditionBuild, int pagenum, int pagesize);

	/**
	 * 根据代码类型获取代码集
	 * 
	 * @param codetype
	 * @return
	 */
	public List<Code> findByCodeType(String codetype);

	public Code findById(String id);

	/**
	 * 根据实验室Id获取对应的代码组
	 * 
	 * @param codeType
	 *            代码组类型
	 * @param sysId
	 *            所属实验室
	 * @return
	 */
	public List<Code> findBySysId(String codeType, String sysId);

}
