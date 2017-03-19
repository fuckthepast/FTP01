/*************************************************
<P>Copyright Guangzhou Ming Heng Software Co., Ltd.
<P>File name: ICommonService.java
<P>Author:Alex_Cheung        Version :1.0.1          Date:2013/03/01
<P>Description: BIZ(SERVICE)层“增删改查” 通用接口，包括分页。
<P>Others:
<P>Function List:
<P>History:
       <author>  		<time>    <version >    <desc>
      Alex_Cheung    2013/03/01      1.0.1     build this moudle  

 *************************************************/
package com.gzmh.common;

import java.util.List;

/**
 * BIZ(SERVICE)层“增删改查” 通用接口，包括分页。
 * 
 * @author Alex_Cheung
 * 
 * @param <T>
 *            业务实体对象
 */
public interface ICommonService<T> {

	/**
	 * 根据业务对象ID查询业务对象
	 * 
	 * @param id
	 *            业务对象ID
	 * @return 业务对象
	 */
	public T findById(String id);

	/**
	 * 根据实验室Id获取所有对象
	 */
	public List<T> findAll(String sysId);

	// BEGIN分页

	/**
	 * 分页查询
	 * 
	 * @param pageNum
	 *            页码
	 * @param pageSize
	 *            每页行数
	 * @return 根据分页条件查询出来的集合
	 */
	public List<T> findByPage(ConditionBuild conditionBuild, int pageSize,
			int pageNum);

	/**
	 * 根据查询条件返回结果数
	 * 
	 * @param condition
	 *            查询条件
	 * @return 结果数
	 */
	public int findTotalNum(ConditionBuild condition);

	// END分页
}
