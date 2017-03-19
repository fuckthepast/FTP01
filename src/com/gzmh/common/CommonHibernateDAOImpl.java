/*************************************************
<P>Copyright Guangzhou Ming Heng Software Co., Ltd.
<P>File name: CommonHibernateDAOImpl.java
<P>Author:Alex_Cheung        Version :1.0.1          Date:2013/03/01
<P>Description: DAO层“增删改查” 通用接口的Hibernate实现，包括分页。
<P>Others:
<P>Function List:
<P>History:
       <author>  		<time>    <version >    <desc>
      Alex_Cheung    2013/04/01      1.0.1     build this moudle  

 *************************************************/
package com.gzmh.common;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.gzmh.exception.HibernateDAOException;

/**
 * DAO层“增删改查” 通用接口的Hibernate实现，包括分页。
 * 
 * @author Alex_Cheung
 * 
 * @param <T>
 *            业务实体对象
 */
public class CommonHibernateDAOImpl<T> extends HibernateDaoSupport {


	private static Logger logger =Logger.getLogger(CommonHibernateDAOImpl.class);
	
	@SuppressWarnings("unchecked")
	public T findEntityInDAO(Class clazz, String id)
			throws HibernateDAOException
	{
		T c =null;
		if (id==null||"".equals(id)){
			throw new NullPointerException("Err"+clazz.getName()
					+" findById err||EntityID is null;||"
					+this.getClass().getName());
		}
		try{
			c =(T) super.getHibernateTemplate().get(clazz, id);
		} catch (Exception e){
			HibernateDAOException se =new HibernateDAOException("Err"
					+clazz.getName()+" findById err||"
					+this.getClass().getName()+"||"+e.getMessage());
			se.initCause(e);
			throw se;
		}
		return c;
	}

	@SuppressWarnings( {"unchecked", "unchecked"})
	private List<T> findByPages(Class clazz, String hql, int pageNum,
			int pageSize) throws HibernateDAOException
	{
		if (hql==null||"".equals(hql)){
			throw new RuntimeException("Err:"+clazz.getName()
					+" findByPages err||queryString is null;||"
					+this.getClass().getName());
		}
		try{
			Query query =this.getSession().createQuery(hql);
			query.setFirstResult((pageNum-1)*pageSize);
			query.setMaxResults(pageSize);
			return query.list();
		} catch (Exception e){
			HibernateDAOException se =new HibernateDAOException("Err:List<"
					+clazz.getName()+"> findByPages with arg:"+hql+"pageNum:"
					+pageNum+" pageSize:"+pageSize+"||"
					+this.getClass().getName()+"||"+e.getMessage());
			se.initCause(e);
			throw se;
		}
	}

	@SuppressWarnings("unchecked")
	public List<T> findByPages(Class clazz, ConditionBuild conditionBuild,
			int pageNum, int pageSize) throws HibernateDAOException
	{
		if (conditionBuild==null)
			throw new NullPointerException(this.getClass().getName()
					+"||查询条件为空！");
		if (pageNum<0||pageSize<0)
			throw new RuntimeException(this.getClass().getName()
					+"||size或num不能为负数！");
		String hql =BuildHQL.buildingHql(conditionBuild);
		return findByPages(clazz, hql, pageNum, pageSize);
	}

	public int findCounts(ConditionBuild conditionBuild)
			throws HibernateDAOException
	{
		if (conditionBuild==null)
			throw new NullPointerException(this.getClass().getName()
					+"||查询条件为空！");
		String hql =BuildHQL.buildingCountHql(conditionBuild);
		return findCounts(hql);
	}

	private int findCounts(String hql) throws HibernateDAOException
	{
		if (hql==null||"".equals(hql)){
			throw new RuntimeException("Err:err||queryString is null;||"
					+this.getClass().getName());
		}
		Object obj =this.getSession().createQuery(hql).uniqueResult();
		if (obj!=null)
			return Integer.parseInt(obj.toString());
		return 0;
	}

	@SuppressWarnings("unchecked")
	public List findBy(ConditionBuild conditionBuild)
			throws HibernateDAOException
	{
		if (conditionBuild==null)
			throw new NullPointerException(this.getClass().getName()
					+"||查询条件为空！");
		String hql =BuildHQL.buildingHql(conditionBuild);
		try{
			Query query =this.getSession().createQuery(hql);
			return query.list();
		} catch (Exception e){
			HibernateDAOException se =new HibernateDAOException(
					"Err:List findByPages with queryString:"+hql+"||"
							+this.getClass().getName()+"||"+e.getMessage());
			se.initCause(e);
			throw se;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getListByExecuteHQL(String hql) throws HibernateDAOException{
		try{
			return super.getHibernateTemplate().find(hql.toString());
		}catch(DataAccessException e){
			throw new HibernateDAOException("Err:findCounts with hql:"
					+hql+"||"+this.getClass().getName()+"||"
					+e.getMessage());
		}
	}
	
	public T getOneByExecuteHQL(String hql) throws HibernateDAOException{
		List<T> targetList =this.getListByExecuteHQL(hql);
		if (targetList==null||targetList.isEmpty()){
			return null;
		}
		return targetList.get(0);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getListByExecuteHQLWithArray(String hql, Object[] values) throws HibernateDAOException{
		try{
			return super.getHibernateTemplate().find(hql.toString(),values);
		}catch(DataAccessException e){
			StringBuffer errValues =new StringBuffer();
			for(Object temp: values){
				errValues.append(temp);
				errValues.append(";");
			}
			throw new HibernateDAOException("Err:findCounts with hql:"
					+hql+"||argus:"+errValues+this.getClass().getName()+"||"
					+e.getMessage());
		}
	}
	
	@SuppressWarnings("unchecked")
	public T executeHQL(String hql, Object[] values) throws HibernateDAOException{
		List<T> targetList =this.getListByExecuteHQLWithArray(hql, values);
		if(targetList==null||targetList.isEmpty()){
			return null;
		}
		return targetList.get(0);
	}
}
