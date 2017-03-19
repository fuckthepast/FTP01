/*************************************************
<P>Copyright Guangzhou Ming Heng Software Co., Ltd.
<P>File name: ShiyanshiDAOImpl.java
<P>Author:Alex_Cheung        Version :1.0.1          Date:2013/03/01
<P>Description: 关于“实验室”的DAO实现类
<P>Others:
<P>Function List:
<P>History:
       <author>  		<time>    <version >    <desc>
      Alex_Cheung    2013/03/01      1.0.1     build this moudle  

 *************************************************/
package com.gzmh.dao.impl;

import java.util.List;

import com.gzmh.common.CommonHibernateDAOImpl;
import com.gzmh.common.ConditionBuild;
import com.gzmh.dao.IShiyanshiInfoDAO;
import com.gzmh.domain.ShiyanshiInfo;
import com.gzmh.exception.HibernateDAOException;

/**
 * 关于“实验室”的DAO实现类
 * 
 * @author Alex_Cheung
 * 
 */
public class ShiyanshiDAOImpl extends CommonHibernateDAOImpl<ShiyanshiInfo>
		implements IShiyanshiInfoDAO {

	public ShiyanshiInfo findEntityInDAO(String id)
			throws HibernateDAOException
	{
		return super.findEntityInDAO(ShiyanshiInfo.class, id);
	}

	@SuppressWarnings("unchecked")
	public ShiyanshiInfo findByName(String name) throws HibernateDAOException
	{
		if (name==null||"".equals(name)){
			throw new NullPointerException(
					"Err:ShiyanshiInfo findByName err||query arg is null;||"
							+this.getClass().getName());
		}
		String hql ="";
		List<ShiyanshiInfo> list =null;
		try{
			hql ="FROM ShiyanshiInfo u WHERE u.sysname = ?";
			list =getHibernateTemplate().find(hql, new Object[] {name});
		} catch (Exception e){
			throw new HibernateDAOException("Err:Shebei findByName with arg:"
					+name+"||"+this.getClass().getName()+"||"+e.getMessage());
		}
		if (list==null||list.isEmpty()){
			return null;
		}
		return (ShiyanshiInfo) list.get(0);
	}

	@SuppressWarnings("unchecked")
	public List<ShiyanshiInfo> findAll() throws HibernateDAOException
	{
		List<ShiyanshiInfo> list =null;
		StringBuffer sb =new StringBuffer("FROM ");
		sb.append("ShiyanshiInfo");
		sb.append(" entity");
		try{
			list =super.getHibernateTemplate().find(sb.toString());
		} catch (Exception e){
			HibernateDAOException se =new HibernateDAOException(
					"Err:List<ShiyanshiInfo> findAll() err||"
							+this.getClass().getName()+"||"+e.getMessage());
			se.initCause(e);
			throw se;
		}
		return list;
	}

	public int findCounts() throws HibernateDAOException
	{
		Object obj =null;
		StringBuffer sb =null;
		try{
			sb =new StringBuffer("SELECT COUNT(*) FROM ");
			sb.append("ShiyanshiInfo");
			sb.append(" entity");
			obj =this.getSession().createQuery(sb.toString()).uniqueResult();
		} catch (Exception e){
			HibernateDAOException te =new HibernateDAOException(
					"Err:findCounts with hql:"+sb.toString()+"||"
							+this.getClass().getName()+"||"+e.getMessage());
			te.initCause(e);
			throw te;
		}
		if (obj!=null)
			return Integer.parseInt(obj.toString());
		return 0;
	}

	public List<ShiyanshiInfo> findByPages(ConditionBuild conditionBuild,
			int pageNum, int pageSize) throws HibernateDAOException
	{
		return super.findByPages(ShiyanshiInfo.class, conditionBuild, pageNum,
				pageSize);
	}

}
