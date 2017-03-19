/*************************************************
<P>Copyright Guangzhou Ming Heng Software Co., Ltd.
<P>File name: ICommonDAO.java
<P>Author:Alex_Cheung        Version :1.0.1          Date:2013/03/01
<P>Description: DAO层“增删改查” 通用接口，包括分页。
<P>Others:
<P>Function List:
<P>History:
       <author>  		<time>    <version >    <desc>
      Alex_Cheung    2013/03/01      1.0.1     build this moudle  

 *************************************************/
package com.gzmh.common;

import java.util.List;

import com.gzmh.exception.HibernateDAOException;

/**
 * DAO层“增删改查” 通用接口，包括分页。
 * 
 * @author Alex_Cheung
 * 
 * @param <T>
 *            业务实体对象
 */
public interface ICommonDAO<T> {
	
	public T findEntityInDAO(String id) throws HibernateDAOException;

	public List<T> findBy(ConditionBuild conditionBuild) throws HibernateDAOException;
	/**
	 * 根据实验室Id获取所有对象
	 */
	public List<T> findAll(String sysId) throws HibernateDAOException;

	// BEGIN分页需要
	public int findCounts(ConditionBuild conditionBuild)
			throws HibernateDAOException;
	public List<T> findByPages(ConditionBuild conditionBuild, int pageNum,
			int pageSize) throws HibernateDAOException;
	// END分页需要
}
