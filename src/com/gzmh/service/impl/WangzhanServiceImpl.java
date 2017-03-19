/*************************************************
<P>Copyright Guangzhou Ming Heng Software Co., Ltd.
<P>File name: WangzhanServiceImpl.java
<P>Author:Alex_Cheung		 	Version :1.0.1          Date:2013/03/01
<P>Description: 关于“网站”的BIZ(SERVICE)实现类
<P>Others:
<P>Function List:
<P>History:
       <author>  		<time>    <version >    <desc>
      Alex_Cheung    2013/04/01      1.0.1     build this moudle  

 *************************************************/
package com.gzmh.service.impl;

import java.util.List;

import com.gzmh.common.ConditionBuild;
import com.gzmh.common.DateFormat;
import com.gzmh.dao.IWangzhanDAO;
import com.gzmh.domain.Wangzhan;
import com.gzmh.exception.HibernateDAOException;
import com.gzmh.service.IWangzhanService;

/**
 * 关于“网站”的BIZ(SERVICE)实现类
 * 
 * @author Alex_Cheung
 * 
 */
public class WangzhanServiceImpl implements IWangzhanService {

	private IWangzhanDAO wangzhanDAO;



	public Wangzhan findById(String id)
	{
		if (id==null||"".equals(id)){
			System.out.println("Err:. err||EntityID is null;||"
					+this.getClass().getName());
			return null;
		}
		Wangzhan c =null;
		try{
			c =wangzhanDAO.findEntityInDAO(id);
		} catch (HibernateDAOException e){
			System.out.println(this.getClass().getName()+"||"+e.getMessage());
		}
		if(c.getTime()!=null){
			c.setTimeStr(DateFormat.get_dateFormat(c.getTime()));
		}
		return c;
	}


	public int findTotalNum(ConditionBuild conditionBuild)
	{
		int i =0;

		try{
			i =wangzhanDAO.findCounts(conditionBuild);
		} catch (HibernateDAOException e){
			System.out.println(this.getClass().getName()+"||"+e.getMessage());
		}
		return i;
	}

	public void setWangzhanDAO(IWangzhanDAO WangzhanDAO)
	{
		this.wangzhanDAO =WangzhanDAO;
	}

	public List<Wangzhan> findByPage(ConditionBuild conditionBuild,
			int pageSize, int pageNum)
	{
		List<Wangzhan> targetList =null;
		if (conditionBuild==null){
			System.out.println("Err:List<Wangzhan> findByPage err||查询条件conditionBuild is null;||"
					+this.getClass().getName());
		}
		try{
			targetList =wangzhanDAO.findByPages(conditionBuild, pageNum,
					pageSize);
		} catch (HibernateDAOException e){
			System.out.println(this.getClass().getName()+"||"+e.getMessage());
		}
		if (targetList==null||targetList.isEmpty()){
			return null;
		}
		
		targetList =convertTheTimeFormat(targetList);
		
		return targetList;
	}


	private List<Wangzhan> convertTheTimeFormat(List<Wangzhan> targetList)
	{
		for(Wangzhan temp:targetList){
			if(temp.getTime()==null){
				continue;
			}
			temp.setTimeStr(DateFormat.get_dateFormat(temp.getTime()));
		}
		return targetList;
	}

	public List<Wangzhan> findAll(String sysId)
	{
		if(sysId==null||"".equals(sysId)){
			System.out.println("Err:. err||sthID is null;||"+this.getClass().getName());
		}
		List<Wangzhan> wangzhans =null;
		try{
			wangzhans =wangzhanDAO.findAll(sysId);
		} catch (HibernateDAOException e){
			System.out.println(this.getClass().getName()+"||"+e.getMessage());
		}
		return wangzhans;
	}
}
