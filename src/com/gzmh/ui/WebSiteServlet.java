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
 * 
 * @author Alex_Cheung
 * 
 */
public class WebSiteServlet extends HttpServlet {

	private static final long serialVersionUID =1L;

	private Logger logger =Logger.getLogger(WebSiteServlet.class);

	private int pageSize;
	private int pageNum;
	private ConditionBuild conditionBuild;

	private String currentSelectedMemberTypeStr;

	private IWangzhanService iWangzhanService;
	private IShiyanshiInfoService iShiyanshiInfoService;
	private ICodeService iCodeService;



	public WebSiteServlet()
	{
		pageSize =CommonPropertys.PAGESIZE;
		pageNum =CommonPropertys.PAGENUM;
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

		/* 获取当前访问的二级模块名称，如“成员介绍” */
		String currentType =initialTypeOfRequest(req);

		/* 对需要成员类型对象的req进行特殊处理，这里是所有请求都需要 */
		List<Code> typesOfMember =iCodeService.findBySysId(
				CommonPropertys.CODETYPE_CYLX, getSthId());
		req.setAttribute("memberTypes", typesOfMember);

		/* 绑定选中的成员类型，如无选择，默认绑定第一个成员类型 */
		currentSelectedMemberTypeStr =req.getParameter("memberType");
		if (currentSelectedMemberTypeStr==null
				||"".equals(currentSelectedMemberTypeStr)){
			if (typesOfMember.isEmpty()){
				logger.error("成员介绍无成员类型！");
			} else{
				currentSelectedMemberTypeStr =typesOfMember.get(0).getId();
				req.setAttribute("currentMemberType", iCodeService.findById(
						currentSelectedMemberTypeStr).getCodeName());
			}
		}

		/* 绑定关联应用的图片存放文件夹的绝对路径 */
		String realPathOfPhoto =setTheRealPathOfPhoto(CommonPropertys.RELATIVE_WEB_APPLICATION, req);

		/* 根据是显示列表还是显示具体某条数据来决定绑定的参数是websites还是website */
		req =initialTheRequestAttributeBeforeConfirmNextJSP(req, currentType,
				realPathOfPhoto);

		/* 根据是显示列表还是显示具体某条数据来决定返回的uri路径 */
		String jspUri =confirmNextJSPUri(getTheSelectedID(req), req
				.getParameter("type"));

		/* 转发请求 */
		this.getServletContext().getRequestDispatcher(jspUri)
				.forward(req, resp);
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

	private String confirmNextJSPUri(String theSelectedID, String currentType)
	{
		if (theSelectedID==null||"".equals(theSelectedID)){
			return confirmTheNextJSPUriToShowDatasByType(currentType);
		}
		return confirmTheNextJSPUriToShowDataByType(currentType);
	}

	private HttpServletRequest initialTheRequestAttributeBeforeConfirmNextJSP(
			HttpServletRequest req, String currentType, String realPathOfPhoto)
	{
		String selectedID =getTheSelectedID(req);
		if (selectedID==null||"".equals(selectedID)){
			return initialRequestAttributeWithWebsites(req, currentType,
					realPathOfPhoto);
		} else{
			return initialRequestAttributeWithWebsite(req, selectedID,
					realPathOfPhoto);
		}
	}

	private HttpServletRequest initialRequestAttributeWithWebsite(
			HttpServletRequest req, String selectedID, String realPathOfPhoto)
	{
		Wangzhan website =iWangzhanService.findById(selectedID);
		if (realPathOfPhoto==null||"".equals(realPathOfPhoto)){
		} else{
			website.setPhoto(realPathOfPhoto+website.getPhoto());
		}
		req.setAttribute("website", website);
		return req;
	}

	private HttpServletRequest initialRequestAttributeWithWebsites(
			HttpServletRequest req, String currentType, String realPathOfPhoto)
	{
		List<Wangzhan> websites =null;

		/* 由于每次访问的类型，如“成员介绍”、“规章制度”不会都一样，所以这里要确保清除之前的访问内容类型参数，空的话直接创建不包含访问类型的对象，若非空，则移除访问类型对象 */
		if (conditionBuild==null){
			conditionBuild =new ConditionBuild(Wangzhan.class,
					new ArrayList<FieldDataBuild>(), new String[] {"time",
							"title"}, new String[] {});
			conditionBuild.setFieldObj(new FieldDataBuild("shiyanshiInfo_id",
					getSthId(), "="));
		} else{
			conditionBuild.removeFieldObj("type");
		}

		/* 设置当前的访问类型 */
		conditionBuild
				.setFieldObj(new FieldDataBuild("type", currentType, "="));

		/* 分页部分 */
		int pageNumber =initialPagingArgs(req);
		int pageSize =this.pageSize;
		int totalPosts =iWangzhanService.findTotalNum(conditionBuild);
		int totalPages =totalPosts/pageSize+((totalPosts%pageSize)>0 ? 1 : 0); // 计算得出的总页数

		req.setAttribute("pageSize", pageSize);
		req.setAttribute("totalPosts", totalPosts);
		req.setAttribute("pageNumber", pageNumber);
		req.setAttribute("totalPages", totalPages);

		if (CommonPropertys.TYPE_WANGZHANGUANLI_CHENGYUANJIESHAO
				.equals(currentType)){
			conditionBuild.setFieldObj(new FieldDataBuild("renyuantype",
					currentSelectedMemberTypeStr, "="));
		} else{
			conditionBuild.removeFieldObj("renyuantype");
		}

		websites =iWangzhanService.findByPage(conditionBuild, pageSize,
				pageNumber);
		if (websites==null||websites.isEmpty()){
			return req;
		}
		websites =setTheMaxNumOfWebsiteContent(websites,
				CommonPropertys.MOST_CONTENT_AMOUNT);
		websites =setTheRealPathOfPhoto(websites, realPathOfPhoto);
		req.setAttribute("websites", websites);
		return req;
	}

	private List<Wangzhan> setTheRealPathOfPhoto(List<Wangzhan> websites,
			String realPathOfPhoto)
	{
		if(websites==null||websites.isEmpty()){
			return null;
		}
		for (Wangzhan website : websites){
			if (website.getPhoto()==null||"".equals(website.getPhoto())){
				continue;
			}
			website.setPhoto(realPathOfPhoto+website.getPhoto());
		}
		return websites;
	}

	/**
	 * 删除input字符串中的html格式
	 * 
	 * @param input
	 * @param length
	 * @return
	 */
	private String splitAndFilterString(String input, int length)
	{
		if (input==null||input.trim().equals("")){
			return "";
		}
		/* 去掉所有html元素, */
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

	private List<Wangzhan> setTheMaxNumOfWebsiteContent(
			List<Wangzhan> websites, int maxNumOfWebsiteContent)
	{
		for (Wangzhan website : websites){

			website.setNeirong(splitAndFilterString(website.getNeirong(),
					maxNumOfWebsiteContent));
		}
		return websites;
	}

	private String getTheSelectedID(HttpServletRequest req)
	{
		return req.getParameter("id");
	}

	private String confirmTheNextJSPUriToShowDatasByType(String type)
	{
		switch (Integer.parseInt(type)) {
		case CommonPropertys.TYPE_IS_GONGGAOFABU:
			return "/Notice.jsp";
		case CommonPropertys.TYPE_IS_KEXUEYANJIU:
			return "/research.jsp";
		case CommonPropertys.TYPE_IS_CHENGYUANJIESHAO:
			return "/members.jsp";
		case CommonPropertys.TYPE_IS_GUIZHANGZHIDU:
			return "/guiZhangZhiDu.jsp";
		case CommonPropertys.TYPE_IS_XUESHUJIAOLIU:
			return "/xueShuJiaoLiu.jsp";
		case CommonPropertys.TYPE_IS_LUNWENCHENGGUO:
			return "/papers.jsp";
		case CommonPropertys.TYPE_IS_CHENGDANKETI:
			return "/subject.jsp";
		default:
			return null;
		}
	}

	private String confirmTheNextJSPUriToShowDataByType(String type)
	{
		switch (Integer.parseInt(type)) {
		case CommonPropertys.TYPE_IS_GONGGAOFABU:
			return "/noticedetail.jsp";
		case CommonPropertys.TYPE_IS_KEXUEYANJIU:
			return "/researchdetail.jsp";
		case CommonPropertys.TYPE_IS_CHENGYUANJIESHAO:
			return "/membersdetail.jsp";
		case CommonPropertys.TYPE_IS_GUIZHANGZHIDU:
			return "/guiZhangZhiDuDetail.jsp";
		case CommonPropertys.TYPE_IS_XUESHUJIAOLIU:
			return "/xueShuJiaoLiuDetail.jsp";
		case CommonPropertys.TYPE_IS_LUNWENCHENGGUO:
			return "/papersdetail.jsp";
		case CommonPropertys.TYPE_IS_CHENGDANKETI:
			return "/subjectDetail.jsp";
		default:
			return null;
		}
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

	private String initialTypeOfRequest(HttpServletRequest req)
	{
		if (req.getParameter("type")==null){
			return null;
		}
		switch (Integer.parseInt(req.getParameter("type"))) {
		case CommonPropertys.TYPE_IS_GONGGAOFABU:
			return CommonPropertys.TYPE_WANGZHANGUANLI_GONGGAOFABU;
		case CommonPropertys.TYPE_IS_KEXUEYANJIU:
			return CommonPropertys.TYPE_WANGZHANGUANLI_KEXUEYANJIU;
		case CommonPropertys.TYPE_IS_CHENGYUANJIESHAO:
			return CommonPropertys.TYPE_WANGZHANGUANLI_CHENGYUANJIESHAO;
		case CommonPropertys.TYPE_IS_GUIZHANGZHIDU:
			return CommonPropertys.TYPE_WANGZHANGUANLI_GUIZHANGZHIDU;
		case CommonPropertys.TYPE_IS_XUESHUJIAOLIU:
			return CommonPropertys.TYPE_WANGZHANGUANLI_XUESHUJIAOLIU;
		case CommonPropertys.TYPE_IS_LUNWENCHENGGUO:
			return CommonPropertys.TYPE_WANGZHANGUANLI_LUNWENCHENGGUO;
		case CommonPropertys.TYPE_IS_CHENGDANKETI:
			return CommonPropertys.TYPE_WANGZHANGUANLI_CHENGDANKETI;
		default:
			return null;
		}
	}

	private int initialPagingArgs(HttpServletRequest req)
	{
		String pageNumStr =req.getParameter("pageNumber");
		if (pageNumStr==null||"".equals(pageNumStr)){
			return 1;
		} else{
			return Integer.parseInt(pageNumStr);
		}

	}
}
