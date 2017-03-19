/*************************************************
<P>Copyright Guangzhou Ming Heng Software Co., Ltd.
<P>File name: WangzhanDAOImpl.java
<P>Author:Alex_Cheung        Version :1.0.1          Date:2013/03/01
<P>Description: 关于“网站”的DAO实现类
<P>Others:
<P>Function List:
<P>History:
       <author>  		<time>    <version >    <desc>
      Alex_Cheung    2013/04/01      1.0.1     build this moudle  

 *************************************************/
package com.gzmh.dao.impl;

import java.util.List;

import com.gzmh.common.CommonHibernateDAOImpl;
import com.gzmh.common.ConditionBuild;
import com.gzmh.dao.IWangzhanDAO;
import com.gzmh.domain.Wangzhan;
import com.gzmh.exception.HibernateDAOException;

/**
 * 关于“网站”的DAO实现类
 * 
 * @author Alex_Cheung
 * 
 */
@SuppressWarnings("unchecked")
public class WangzhanDAOImpl extends CommonHibernateDAOImpl<Wangzhan> implements
		IWangzhanDAO {

	public Wangzhan findEntityInDAO(String id) throws HibernateDAOException
	{
		return super.findEntityInDAO(Wangzhan.class, id);
	}

	public List<Wangzhan> findByTitle(String title, String type,
			String shiyanshiId) throws HibernateDAOException
	{
		String hql ="from Wangzhan entity where entity.title like '%"+title
				+"%' and entity.shiyanshiInfo.id = '"+shiyanshiId
				+"' and entity.type ='"+type+"'";
		List targetList =null;
		try{
			targetList =super.getHibernateTemplate().find(hql);
		} catch (Exception e){
			HibernateDAOException se =new HibernateDAOException(
					"Err:List<Wangzhan> findByTitle with arg:"+title+"||"
							+this.getClass().getName()+"||"+e.getMessage());
			se.initCause(e);
			throw se;
		}
		return targetList;
	}

	public List<Wangzhan> findByPages(ConditionBuild conditionBuild,
			int pageNum, int pageSize) throws HibernateDAOException
	{
		return super.findByPages(Wangzhan.class, conditionBuild, pageNum,
				pageSize);
	}

	public List<Wangzhan> findAll(String sysId)
	{
		List<Wangzhan> targetList =null;
		String hql ="FROM Wangzhan sb WHERE sb.shiyanshiInfo.id=? order by sb.type, sb.title";
		try{
			targetList =super.getHibernateTemplate().find(hql, sysId);
		} catch (Exception e){
			RuntimeException se =new RuntimeException(
					"Err:findCounts with hql:"+hql+"||"
							+this.getClass().getName()+"||"+e.getMessage());
			se.initCause(e);
			throw se;
		}
		return targetList;
	}
}
