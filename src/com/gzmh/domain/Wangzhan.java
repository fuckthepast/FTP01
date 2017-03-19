/*************************************************
<P>Copyright Guangzhou Ming Heng Software Co., Ltd.
<P>File name: Changsuo.java
<P>Author:Alex_Cheung        Version :1.0.1          Date:2013/03/01
<P>Description: 网站
<P>Others: 该对象参照表：wangzhan
<P>Function List:
<P>History:
       <author>  		<time>    <version >    <desc>
      Alex_Cheung    2013/03/01      1.0.1     build this moudle  

 *************************************************/
package com.gzmh.domain;

import java.util.Date;

/**
 * 网站
 * 
 * @author Alex_Cheung
 * 
 */
public class Wangzhan implements java.io.Serializable {


	private static final long serialVersionUID =1L;
	/**网站ID*/
	private String id;
	/**标题*/
	private String title;
	/**分类：公告发布、科学研究、成员介绍，规章制度，学术交流，论文成果*/
	private String type;
	/**发布时间*/
	private Date time;
	/**发布时间*/
	private String timeStr;
	/**内容*/
	private String neirong;
	/**图片路径*/
	private String photo;
	/**成员类型*/
	private Code renyuantype;
	/**所属实验室*/
	private ShiyanshiInfo shiyanshiInfo;
	/**副标题（成员介绍模块需要）*/
	private String subhead;



	
	
	
	public String getSubhead()
	{
		return subhead;
	}



	
	public void setSubhead(String subhead)
	{
		this.subhead =subhead;
	}



	public ShiyanshiInfo getShiyanshiInfo()
	{
		return shiyanshiInfo;
	}


	
	public void setShiyanshiInfo(ShiyanshiInfo shiyanshiInfo)
	{
		this.shiyanshiInfo =shiyanshiInfo;
	}


	public Code getRenyuantype()
	{
		return renyuantype;
	}

	
	public void setRenyuantype(Code renyuantype)
	{
		this.renyuantype =renyuantype;
	}

	/** default constructor */
	public Wangzhan()
	{
	}

	/** full constructor */
	public Wangzhan(String id, String title, String type, Date time,
			String neirong, String photo, String renyuantype)
	{
		this.id =id;
		this.title =title;
		this.type =type;
		this.time =time;
		this.neirong =neirong;
		this.photo =photo;
	}

	// Property accessors

	public String getId()
	{
		return this.id;
	}

	public void setId(String id)
	{
		this.id =id;
	}

	public String getTitle()
	{
		return this.title;
	}

	public void setTitle(String title)
	{
		this.title =title;
	}

	public String getType()
	{
		return this.type;
	}

	public void setType(String type)
	{
		this.type =type;
	}

	public Date getTime()
	{
		return this.time;
	}

	public void setTime(Date time)
	{
		this.time =time;
	}

	public String getNeirong()
	{
		return this.neirong;
	}

	public void setNeirong(String neirong)
	{
		this.neirong =neirong;
	}

	public String getPhoto()
	{
		return this.photo;
	}

	public void setPhoto(String photo)
	{
		this.photo =photo;
	}

	public boolean equals(Object other)
	{
		if ((this==other))
			return true;
		if ((other==null))
			return false;
		if (!(other instanceof Wangzhan))
			return false;
		Wangzhan castOther =(Wangzhan) other;

		return ((this.getId()==castOther.getId())||(this.getId()!=null
				&&castOther.getId()!=null&&this.getId().equals(
				castOther.getId())))
				&&((this.getTitle()==castOther.getTitle())||(this.getTitle()!=null
						&&castOther.getTitle()!=null&&this.getTitle().equals(
						castOther.getTitle())))
				&&((this.getType()==castOther.getType())||(this.getType()!=null
						&&castOther.getType()!=null&&this.getType().equals(
						castOther.getType())))
				&&((this.getTime()==castOther.getTime())||(this.getTime()!=null
						&&castOther.getTime()!=null&&this.getTime().equals(
						castOther.getTime())))
				&&((this.getNeirong()==castOther.getNeirong())||(this
						.getNeirong()!=null
						&&castOther.getNeirong()!=null&&this.getNeirong()
						.equals(castOther.getNeirong())))
				&&((this.getPhoto()==castOther.getPhoto())||(this.getPhoto()!=null
						&&castOther.getPhoto()!=null&&this.getPhoto().equals(
						castOther.getPhoto())))
				;
	}

	public int hashCode()
	{
		int result =17;

		result =37*result+(getId()==null ? 0 : this.getId().hashCode());
		result =37*result+(getTitle()==null ? 0 : this.getTitle().hashCode());
		result =37*result+(getType()==null ? 0 : this.getType().hashCode());
		result =37*result+(getTime()==null ? 0 : this.getTime().hashCode());
		result =37*result
				+(getNeirong()==null ? 0 : this.getNeirong().hashCode());
		result =37*result+(getPhoto()==null ? 0 : this.getPhoto().hashCode());
		return result;
	}



	
	public String getTimeStr()
	{
		return timeStr;
	}



	
	public void setTimeStr(String timeStr)
	{
		this.timeStr =timeStr;
	}

}
