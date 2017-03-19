/*************************************************
<P>Copyright Guangzhou Ming Heng Software Co., Ltd.
<P>File name: IShiyanshiInfoDAO.java
<P>Author:Alex_Cheung        Version :1.0.1          Date:2013/03/01
<P>Description: 实验室 DAO
<P>Others:
<P>Function List:
<P>History:
       <author>  		<time>    <version >    <desc>
      Alex_Cheung    2013/03/01      1.0.1     build this moudle  

 *************************************************/
package com.gzmh.dao;

import java.util.List;

import com.gzmh.common.ConditionBuild;
import com.gzmh.domain.ShiyanshiInfo;
import com.gzmh.exception.HibernateDAOException;

/**
 * 实验室 DAO
 * 
 * @author Alex_Cheung
 * 
 */
public interface IShiyanshiInfoDAO {

	public ShiyanshiInfo findEntityInDAO(String id)
			throws HibernateDAOException;
	public ShiyanshiInfo findByName(String name) throws HibernateDAOException;

	public List<ShiyanshiInfo> findAll() throws HibernateDAOException;
	public int findCounts(ConditionBuild conditionBuild)
			throws HibernateDAOException;
	public List<ShiyanshiInfo> findByPages(ConditionBuild conditionBuild,
			int pageNum, int pageSize) throws HibernateDAOException;

	public int findCounts() throws HibernateDAOException;
}
