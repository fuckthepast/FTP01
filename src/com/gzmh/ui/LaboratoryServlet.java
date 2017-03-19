/*************************************************
<P>Copyright Guangzhou Ming Heng Software Co., Ltd.
<P>File name: WebSiteServlet.java
<P>Author:Alex_Cheung        Version :1.0.1          Date:2013/04/01
<P>Description:
<P>Others: 
<P>Function List:
<P>History:
       <author>  		<time>    <version >    <desc>
      Alex_Cheung    2013/04/01      1.0.1     build this moudle  

 *************************************************/
package com.gzmh.ui;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.gzmh.common.CommonPropertys;
import com.gzmh.domain.Code;
import com.gzmh.domain.ShiyanshiInfo;
import com.gzmh.service.ICodeService;
import com.gzmh.service.IShiyanshiInfoService;

/**
 * 实验室概况模块
 * 
 * @author Alex_Cheung
 * 
 */
public class LaboratoryServlet extends HttpServlet {

	private static final long serialVersionUID =1L;

	private Logger logger =Logger.getLogger(LaboratoryServlet.class);

	private IShiyanshiInfoService iShiyanshiInfoService;
	private ICodeService iCodeService;
	
	private String currentSelectedMemberTypeStr;

	public LaboratoryServlet()
	{
	}

	@Override
	public void init() throws ServletException
	{
		super.init();
		ServletContext servletContext =this.getServletContext();
		WebApplicationContext ctx =WebApplicationContextUtils
				.getWebApplicationContext(servletContext);
		iShiyanshiInfoService =(IShiyanshiInfoService) ctx
				.getBean("shiyanshiInfoService");
		iCodeService =(ICodeService) ctx.getBean("codeService");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		
		/* 对需要成员类型对象的req进行特殊处理，这里是所有请求都需要 */
		List<Code> typesOfMember =iCodeService.findBySysId(
				CommonPropertys.CODETYPE_CYLX, getSth().getId());
		if(typesOfMember!=null&&!typesOfMember.isEmpty()){
		
		req.setAttribute("memberTypes", typesOfMember);

		/* 绑定选中的成员类型，如无选择，默认绑定第一个成员类型 */
		currentSelectedMemberTypeStr =req.getParameter("memberType");
		if (currentSelectedMemberTypeStr==null
				||"".equals(currentSelectedMemberTypeStr)){
			if (typesOfMember.get(0)==null){
				logger.error("成员介绍无成员类型！");
			} else{
				currentSelectedMemberTypeStr =typesOfMember.get(0).getId();
			}
		}
		req.setAttribute("currentMemberType", iCodeService.findById(
				currentSelectedMemberTypeStr).getCodeName());
		}
		
		req.setAttribute("jieshao", getSth().getJieshao());
		this.getServletContext().getRequestDispatcher("/information.jsp")
				.forward(req, resp);
	}

	private ShiyanshiInfo getSth()
	{
		ShiyanshiInfo specifiedLab =iShiyanshiInfoService
				.findById("00000000000000000000000000000001");
		if (specifiedLab==null){
			logger.error("FATAL:实验室数据无效,请检查数据库！||"+this.getClass().getName());
			return null;
		}
		return specifiedLab;
	}
}
