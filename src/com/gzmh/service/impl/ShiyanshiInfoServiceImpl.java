/*************************************************
<P>Copyright Guangzhou Ming Heng Software Co., Ltd.
<P>File name: ShiyanshiInfoServiceImpl.java
<P>Author:Alex_Cheung		 	Version :1.0.1          Date:2013/03/01
<P>Description: 关于“实验室”的BIZ(SERVICE)实现类
<P>Others:
<P>Function List:
<P>History:
       <author>  		<time>    <version >    <desc>
      Alex_Cheung    2013/04/01      1.0.1     build this moudle  

 *************************************************/
package com.gzmh.service.impl;

import java.util.List;

import com.gzmh.common.ConditionBuild;
import com.gzmh.dao.IShiyanshiInfoDAO;
import com.gzmh.domain.ShiyanshiInfo;
import com.gzmh.exception.HibernateDAOException;
import com.gzmh.service.IShiyanshiInfoService;

/**
 * 关于“实验室”的BIZ(SERVICE)实现类
 * 
 * @author Alex_Cheung
 * 
 */
public class ShiyanshiInfoServiceImpl implements IShiyanshiInfoService {

	private IShiyanshiInfoDAO shiyanshiInfoDAO;



	public ShiyanshiInfo findById(String id)
	{
		if (id==null||"".equals(id)){
			System.out.println("Err:. err||EntityID is null;||"
					+this.getClass().getName());
			return null;
		}
		ShiyanshiInfo c =null;
		try{
			c =shiyanshiInfoDAO.findEntityInDAO(id);
		} catch (HibernateDAOException e){
			System.out.println(this.getClass().getName()+"||"+e.getMessage());
		}
		return c;
	}
	public ShiyanshiInfo findByName(String name)
	{
		if (name==null){
			System.out.println("warn:sysname is null. ");
			return null;
		}
		ShiyanshiInfo targetEntity =null;
		try{
			targetEntity =shiyanshiInfoDAO.findByName(name);
		} catch (HibernateDAOException e){
			System.out.println(this.getClass().getName()+"||"+e.getMessage());
		}
		return targetEntity;
	}

	public List<ShiyanshiInfo> findAll()
	{
		List<ShiyanshiInfo> list =null;
		try{
			list =shiyanshiInfoDAO.findAll();
		} catch (HibernateDAOException e){
			System.out.println(this.getClass().getName()+"||"+e.getMessage());
		}
		return list;
	}
	
	public List<ShiyanshiInfo> findByPage(ConditionBuild conditionBuild,
			int pageSize, int pageIndex)
	{
		if (conditionBuild==null){
			System.out.println("Err:. err||查询条件conditionBuild is null;||"
					+this.getClass().getName());
			return null;
		}
		List<ShiyanshiInfo> targetList =null;
		try{
			targetList =shiyanshiInfoDAO.findByPages(conditionBuild, pageIndex,
					pageSize);
		} catch (HibernateDAOException e){
			System.out.println(this.getClass().getName()+"||"+e.getMessage());
		}
		if (targetList==null||targetList.isEmpty()){
			return null;
		}

		return targetList;
	}

	public int findTotalNum()
	{
		int i =0;
		try{
			i =shiyanshiInfoDAO.findCounts();
		} catch (HibernateDAOException e){
			System.out.println(this.getClass().getName()+"||"+e.getMessage());
		}
		return i;
	}
	public int findTotalNum(ConditionBuild conditionBuild)
	{
		if (conditionBuild==null){
			System.out.println(this.getClass().getName()
					+"||warn:Conditon is null!");
			return findTotalNum();
		}
		int i =0;
		try{
			i =shiyanshiInfoDAO.findCounts(conditionBuild);
		} catch (HibernateDAOException e){
			System.out.println(this.getClass().getName()+"||"+e.getMessage());
		}
		return i;
	}

	public void setShiyanshiInfoDAO(IShiyanshiInfoDAO shiyanshiInfoDAO)
	{
		this.shiyanshiInfoDAO =shiyanshiInfoDAO;
	}

}
