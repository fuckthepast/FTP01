package com.gzmh.common;

public class CommonPropertys {

	/* 默认分页 */

	/** 分页页码 */
	public static final int PAGENUM =1;

	/** 每页页数 */
	public static final int PAGESIZE =10;

	/* 代码类型 */

	/** 成员类型 */
	public static final String CODETYPE_CYLX ="CYLX";

	/* 过滤类封装条件 */

	/** 当查询条件为null时值前面要加入标识 */
	public static final String SELECT_TYPE_NULL ="IS NULL";

	/** 当查询条件为not null时值前面要加入标识 */
	public static final String SELECT_TYPE_NOTNULL ="IS NOT NULL";

	/** 当查询条件为in时值前面要加入标识 */
	public static final String SELECT_TYPE_IN ="IN";

	/** 当查询条件为not in时值前面要加入标识 */
	public static final String SELECT_TYPE_NOTIN ="NOT IN";

	/** 当查询条件为=时值前面要加入标识 */
	public static final String SELECT_TYPE_EQUAL ="=";

	/** 当查询条件为<>时值前面要加入标识 */
	public static final String SELECT_TYPE_UNEQUAL ="<>";

	/** 当查询条件为<>时值前面要加入标识 */
	public static final String SELECT_TYPE_LIKE ="LIKE";

	/** 当查询条件为or时值前面要加入标识 */
	public static final String SELECT_TYPE_OR ="OR";

	/** 当查询框内容为空时出现的提示信息/占位符。 */
	public static final String SELECT_HINT_WHEN_EMPTY ="可输入对应内容的条件进行查询";

	/* 是否字段中的字符 */

	/** 查询的时候数据的状态为有效的 */
	public static final String SELECT_STATE_YEE ="是";

	/** 查询的时候数据的状态为有效的 */
	public static final String SELECT_STATE_NO ="否";

	/* 公共发布及下级模块 */

	/** 公共发布 */
	public static final String GONGGONGFABU_SOUTH_TITLE ="公共发布";
	/** 研究方向 */
	public static final String KEXUEYANJIU_SOUTH_TITLE ="研究方向";
	/** 承担课题 */
	public static final String CHENGDANKETI_SOUTH_TITLE ="承担课题";
	/** 成员介绍 */
	public static final String CHENGYUANJIESHAO_SOUTH_TITLE ="成员介绍";
	/** 规章制度 */
	public static final String GUIZHANGZHIDU_SOUTH_TITLE ="规章制度";
	/** 学术交流 */
	public static final String XUESHUJIAOLIU_SOUTH_TITLE ="学术交流";
	/** 论文成果 */
	public static final String LUNWENCHENGGUO_SOUTH_TITLE ="论文成果";

	/* “网站管理” 的类型*/

	/** 公共发布 */
	public static final String TYPE_WANGZHANGUANLI_GONGGAOFABU =GONGGONGFABU_SOUTH_TITLE;

	/** 研究方向 */
	public static final String TYPE_WANGZHANGUANLI_KEXUEYANJIU =KEXUEYANJIU_SOUTH_TITLE;

	/** 承担课题 */
	public static final String TYPE_WANGZHANGUANLI_CHENGDANKETI =CHENGDANKETI_SOUTH_TITLE;

	/** 成员介绍 */
	public static final String TYPE_WANGZHANGUANLI_CHENGYUANJIESHAO =CHENGYUANJIESHAO_SOUTH_TITLE;

	/** 规章制度 */
	public static final String TYPE_WANGZHANGUANLI_GUIZHANGZHIDU =GUIZHANGZHIDU_SOUTH_TITLE;

	/** 学术交流 */
	public static final String TYPE_WANGZHANGUANLI_XUESHUJIAOLIU =XUESHUJIAOLIU_SOUTH_TITLE;

	/** 论文成果 */
	public static final String TYPE_WANGZHANGUANLI_LUNWENCHENGGUO =LUNWENCHENGGUO_SOUTH_TITLE;

	/* “网站管理” 的类型*/

	/** 公共发布 1 */
	public static final int TYPE_IS_GONGGAOFABU =1;

	/** 研究方向 2 */
	public static final int TYPE_IS_KEXUEYANJIU =2;

	/** 承担课题 7 */
	public static final int TYPE_IS_CHENGDANKETI =7;

	/** 成员介绍 3 */
	public static final int TYPE_IS_CHENGYUANJIESHAO =3;

	/** 规章制度 4 */
	public static final int TYPE_IS_GUIZHANGZHIDU =4;

	/** 学术交流 5 */
	public static final int TYPE_IS_XUESHUJIAOLIU =5;

	/** 论文成果 6 */
	public static final int TYPE_IS_LUNWENCHENGGUO =6;
	
	/*附件存放路径文件夹名称*/
	
	/**图片上传根路径*/
	public static final String PIC_ROOT_PATH = "uploadMeterial";
	
	/**在同个tomcat下关联的应用（非本应用）部署名称*/
	public static final String RELATIVE_WEB_APPLICATION = "mhzksh";
	
	/**在关联的内部系统应用中（如mhzksh）存放网站管理模块的文件夹名称*/
	public static final String PIC_PATH_WEBSITE = "website";
	
	/**简介内容字数显示限制*/
	public static final int MOST_CONTENT_AMOUNT =120;
	
	/**首页最新动态显示信息限制*/
	public static final int MOST_NEWEST_AMOUNT =6;
	
	/**首页研究方向显示信息限制*/
	public static final int MOST_RESEARCH_AMOUNT =3;

	public static final String SERVERADDRESS ="192.168.2.101";
}
