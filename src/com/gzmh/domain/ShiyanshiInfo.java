/*************************************************
<P>Copyright Guangzhou Ming Heng Software Co., Ltd.
<P>File name: ShiyanshiInfo.java
<P>Author:Alex_Cheung        Version :1.0.1          Date:2013/03/01
<P>Description: 实验室
<P>Others: 该对象参照表：shiyanshiinfo
<P>Function List:
<P>History:
       <author>  		<time>    <version >    <desc>
      Alex_Cheung    2013/08/01      1.0.1     build this module  

 *************************************************/
package com.gzmh.domain;

/**
 * 实验室
 * 
 * @author Alex_Cheung
 * 
 */
public class ShiyanshiInfo implements java.io.Serializable, Cloneable {

	private static final long serialVersionUID =1L;

	/** 实验室ID */
	private String id;

	/** 实验室介绍 */
	private String jieshao;

	
	public String getId()
	{
		return id;
	}

	
	public void setId(String id)
	{
		this.id =id;
	}

	
	public String getJieshao()
	{
		return jieshao;
	}

	
	public void setJieshao(String jieshao)
	{
		this.jieshao =jieshao;
	}



}
