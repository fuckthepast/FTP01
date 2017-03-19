/*************************************************
<P>Copyright Guangzhou Ming Heng Software Co., Ltd.
<P>File name: IShiyanshiInfoService.java
<P>Author:Alex_Cheung        Version :1.0.1          Date:2013/03/01
<P>Description: 关于“实验室”的BIZ(SERVICE)
<P>Others:
<P>Function List:
<P>History:
       <author>  		<time>    <version >    <desc>
      Alex_Cheung    2013/04/01      1.0.1     build this moudle  

 *************************************************/
package com.gzmh.service;

import com.gzmh.domain.ShiyanshiInfo;

/**
 * 实验室 BIZ(SERVICE)
 * 
 * @author Alex_Cheung
 * 
 */
public interface IShiyanshiInfoService {

	/**name是唯一时才用此方法；*/
	public ShiyanshiInfo findByName(String name);
	
	public ShiyanshiInfo findById(String ID);

}
