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

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
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
import com.gzmh.common.ConditionBuild;
import com.gzmh.common.FieldDataBuild;
import com.gzmh.domain.Code;
import com.gzmh.domain.ShiyanshiInfo;
import com.gzmh.domain.Wangzhan;
import com.gzmh.service.ICodeService;
import com.gzmh.service.IShiyanshiInfoService;
import com.gzmh.service.IWangzhanService;

/**
 * 首页
 * 
 * @author Alex_Cheung
 * 
 */
public class HomeServlet extends HttpServlet {

	private static final long serialVersionUID =1L;

	private Logger logger =Logger.getLogger(HomeServlet.class);

	private IShiyanshiInfoService iShiyanshiInfoService;
	private IWangzhanService iWangzhanService;
	private ICodeService iCodeService;

	private String currentSelectedMemberTypeStr;

	public HomeServlet()
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
		iWangzhanService =(IWangzhanService) ctx.getBean("wangzhanService");
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
				CommonPropertys.CODETYPE_CYLX, getSthId());
		req.setAttribute("memberTypes", typesOfMember);

		/* 绑定选中的成员类型，如无选择，默认绑定第一个成员类型 */
		currentSelectedMemberTypeStr =req.getParameter("memberType");
		if (currentSelectedMemberTypeStr==null
				||"".equals(currentSelectedMemberTypeStr)){
			if (typesOfMember==null||typesOfMember.isEmpty()||typesOfMember.get(0)==null){
				logger.error("成员介绍无成员类型！");
				req.setAttribute("currentMemberType", null);
			} else{
				currentSelectedMemberTypeStr =typesOfMember.get(0).getId();
				req.setAttribute("currentMemberType", iCodeService.findById(
						currentSelectedMemberTypeStr).getCodeName());
			}
		}
		
		/* 处理实验室简介 */
		req =dealWithTheShortContentOfLaboratory(req, resp);

		/* 处理最新动态 */
		req =dealWithTheNewestMesg(req, resp);

		/* 处理研究方向 */
		req =dealWithTheReasearchForward(req, resp);

		this.getServletContext().getRequestDispatcher("/home.jsp").forward(req,
				resp);
	}

	private HttpServletRequest dealWithTheReasearchForward(
			HttpServletRequest req, HttpServletResponse resp)
	{
		ConditionBuild conditionBuild =null;
		conditionBuild =new ConditionBuild(Wangzhan.class,
				new ArrayList<FieldDataBuild>(),
				new String[] {"time", "title"}, new String[] {"DESC", "ASC"});
		conditionBuild.setFieldObj(new FieldDataBuild("shiyanshiInfo_id",
				getSthId(), null));
		conditionBuild.setFieldObj(new FieldDataBuild("type",
				CommonPropertys.KEXUEYANJIU_SOUTH_TITLE, "="));
		List<Wangzhan> websites =iWangzhanService.findByPage(conditionBuild,
				CommonPropertys.MOST_RESEARCH_AMOUNT, 1);
		if(websites==null||websites.isEmpty()){
			req.setAttribute("researchForwards", websites);
			return req;
		}
		/* 绑定关联应用的图片存放文件夹的绝对路径 */
		String realPathOfPhoto =setTheRealPathOfPhoto(
				CommonPropertys.RELATIVE_WEB_APPLICATION, req);
		websites =setTheRealPathOfPhoto(websites, realPathOfPhoto);
		req.setAttribute("researchForwards", websites);
		return req;
	}

	private HttpServletRequest dealWithTheShortContentOfLaboratory(
			HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		String introduction =getSth().getJieshao();
		if (introduction==null||"".equals(introduction)){
			this.getServletContext().getRequestDispatcher("/home.jsp").forward(
					req, resp);
		}
		introduction =splitAndFilterString(introduction, 140);
		req.setAttribute("introduction", introduction);
		return req;
	}

	private HttpServletRequest dealWithTheNewestMesg(HttpServletRequest req,
			HttpServletResponse resp)
	{
		ConditionBuild conditionBuild =null;
		conditionBuild =new ConditionBuild(Wangzhan.class,
				new ArrayList<FieldDataBuild>(),
				new String[] {"time", "title"}, new String[] {"DESC", "ASC"});
		conditionBuild.setFieldObj(new FieldDataBuild("shiyanshiInfo_id",
				getSthId(), null));
		conditionBuild.setFieldObj(new FieldDataBuild("type",
				CommonPropertys.GONGGONGFABU_SOUTH_TITLE, "="));
		List<Wangzhan> websites =iWangzhanService.findByPage(conditionBuild,
				CommonPropertys.MOST_NEWEST_AMOUNT, 1);
		if(websites==null||websites.isEmpty()){
			return req;
		}
		for (Wangzhan temp : websites){
			if (temp.getTitle().length()>20){
				temp.setTitle(temp.getTitle().substring(0, 20)+"……");
			}
		}
		req.setAttribute("websites", websites);
		return req;
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

	private String getSthId()
	{
		ShiyanshiInfo specifiedLab =iShiyanshiInfoService
				.findById("00000000000000000000000000000001");
		if (specifiedLab==null){
			System.out.println("FATAL:实验室数据无效,请检查数据库！||"
					+this.getClass().getName());
			return null;
		}
		return specifiedLab.getId();
	}

	private String setTheRealPathOfPhoto(String relativeWebPath,
			HttpServletRequest req)
	{
		String relativeWebApplicationServerAdd =null;
		try{
			InetAddress localhost =InetAddress.getByName("localhost");
//			InetAddress localhost =InetAddress.getByName(CommonPropertys.SERVERADDRESS);
			relativeWebApplicationServerAdd =localhost.getLocalHost().getHostAddress();
		} catch (UnknownHostException e){
			logger.fatal("主机IP无法识别", e);
		}
		return req.getScheme()+"://"+relativeWebApplicationServerAdd+":"+req.getLocalPort()+
		File.separator+relativeWebPath+File.separator+CommonPropertys.PIC_ROOT_PATH+File.separator+CommonPropertys.PIC_PATH_WEBSITE+File.separator;
	}

	private List<Wangzhan> setTheRealPathOfPhoto(List<Wangzhan> websites,
			String realPathOfPhoto)
	{
		for (Wangzhan website : websites){
			if (website.getPhoto()==null||"".equals(website.getPhoto())){
				continue;
			}
			website.setPhoto(realPathOfPhoto+website.getPhoto());
		}
		return websites;
	}

	private String splitAndFilterString(String input, int length)
	{
		if (input==null||input.trim().equals("")){
			return "";
		}
		// 去掉所有html元素,
		String str =input.replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll(
				"<[^>]*>", "");
		str =str.replaceAll("[(/>)<]", "");
		int len =str.length();
		if (len<=length){
			return str;
		} else{
			str =str.substring(0, length);
			str +="......";
		}
		return str;
	}
}
