package com.gzmh.common;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * 
 * @author xlxin
 */
@SuppressWarnings("unchecked")
public class CommonDAOImpl extends HibernateDaoSupport {

	/**
	 * 获取实体对象
	 * 
	 * @param clazz
	 *            实体对象的class类型
	 * @param id
	 *            标识值
	 * @return
	 */
	public Object get(Class clazz, Serializable id)
	{
		Object ret =super.getHibernateTemplate().get(clazz, id);
		return ret;
	}

	/**
	 * 根据HQL查询数据
	 * 
	 * @return 实体对象集
	 */
	public List findByHql(String hql)
	{
		return super.getHibernateTemplate().find(hql);
	}

	/**
	 * 获取实体对象集
	 * 
	 * @param hql
	 *            hql语句
	 * @param param
	 *            条件参数值
	 * @return 实体对象集
	 */
	public List list(String hql, Object[] param)
	{
		List ret =super.getHibernateTemplate().find(hql, param);
		return ret;
	}

	/**
	 * 获取所有实体对象
	 * 
	 * @return
	 */
	public List finAll(Class entityClass)
	{
		StringBuffer sb =new StringBuffer("FROM ");
		sb.append(entityClass.getName());
		sb.append(" entity");
		return super.getHibernateTemplate().find(sb.toString());
	}

	/**
	 * 获取数据总数
	 * 
	 * @param entityClass
	 * @return
	 */
	public int findCount(Class entityClass)
	{
		StringBuffer sb =new StringBuffer("SELECT COUNT(*) FROM ");
		sb.append(entityClass.getName());
		sb.append(" entity");
		Object obj =this.getSession().createQuery(sb.toString()).uniqueResult();
		if (obj!=null)
			return Integer.parseInt(obj.toString());
		return 0;
	}

	/**
	 * 根据条件Hql获取数据总数
	 * 
	 * @param entityClass
	 * @return
	 */
	public int findCount(String hql)
	{
		try{
			Object obj =this.getSession().createQuery(hql).uniqueResult();
			if (obj!=null)
				return Integer.parseInt(obj.toString());
		} catch (RuntimeException re){
			re.printStackTrace();
		}
		return 0;
	}

	/**
	 * 获取所有实体对象
	 * 
	 * @param entityName
	 *            实体类名称
	 * @param sort
	 *            排序字段名称
	 * @param dir
	 *            排序方式
	 * @return
	 */
	public List finAll(Class entityClass, String sort, String dir)
	{
		StringBuffer sb =new StringBuffer("FROM ");
		sb.append(entityClass.getName());
		sb.append(" entity order by entity.");
		sb.append(sort);
		sb.append(" ");
		sb.append(dir);
		return super.getHibernateTemplate().find(sb.toString());
	}

	/**
	 * 根据条件组装HQL语句
	 * 
	 * @param conditionList
	 *            过滤条件集
	 * @param pageNum
	 *            页码
	 * @param pageSize
	 *            页数
	 * @return
	 */
	public List findByPages(String hql, int pageNum, int pageSize)
	{
		if (hql!=null){
			try{
				Query query =this.getSession().createQuery(hql);
				if (query!=null){
					query.setFirstResult((pageNum-1)*pageSize);
					query.setMaxResults(pageSize);
					return query.list();
				}
			} catch (Exception e){
				e.printStackTrace();
			}
		}
		return null;
	}

	public int execHqlForUpdOrDel(String hql)
	{
		return this.getSession().createQuery(hql).executeUpdate();
	}

	public Object execHql(String hql)
	{
		return this.getSession().createQuery(hql).uniqueResult();
	}
}
