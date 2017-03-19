<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>成员介绍</title>
<link href="membersdetail.css" rel="stylesheet" type="text/css"/>
<link href="css/nav.css" rel="stylesheet" type="text/css"/>
<script src="js/navSelect.js" type="text/javascript"></script><!-- 导航栏选择效果实现 -->
</head>

<body onload="javascript:qiehuan(4,4)">
<div id="wrap">
  <div id="head">
    <div class="logo">
     <img src="img/logo.jpg" alt="中山大学" style="float:left;margin-top:15px;margin-left:20px;"/>
      <img src="img/text.jpg" alt="中山大学" style="float:left;margin-top:10px;"/>
      </div>
   </div>
  <div id=menu_out>
<div id=menu_in>
<div id="menu">
<ul id="nav" onmouseout="javascript:defaultCheck(4)">
<li><a class="nav_off" id="mynav0" onmouseover=javascript:qiehuan(0,4) href="home"><span>首 页</span></a></li>
<li class="menu_line"></li><li><a href="information" onmouseover="javascript:qiehuan(1,4)" id="mynav1" class="nav_off"><span>实验室概况</span></a></li>
<li class="menu_line"></li><li><a href="Notice?type=1" onmouseover="javascript:qiehuan(2,4)" id="mynav2" class="nav_off"><span>公告发布</span></a></li>
<li class="menu_line"></li><li><a href="research?type=2" onmouseover="javascript:qiehuan(3)" id="mynav3" class="nav_off"><span>科学研究</span></a></li>
<li class="menu_line"></li><li><a href="members?type=3" onmouseover="javascript:qiehuan(4,4)" id="mynav4" class="nav_on"><span>成员介绍</span></a></li>
<li class="menu_line"></li><li><a href="guiZhangZhiDu?type=4" onmouseover="javascript:qiehuan(5,4)" id="mynav5" class="nav_off"><span>规章制度</span></a></li>
<li class="menu_line"></li><li><a href="xueShuJiaoLiu?type=5" onmouseover="javascript:qiehuan(6,4)" id="mynav6" class="nav_off"><span>学术交流</span></a></li>
<li class="menu_line"></li><li><a href="papers?type=6" onmouseover="javascript:qiehuan(7,4)" id="mynav7" class="nav_off"><span>论文成果</span></a></li>

</ul>

<div id=menu_con>

<div id=qh_con0 style="DISPLAY: block">
<UL></UL>
</div> 

<div id=qh_con1 style="DISPLAY: none">
<UL></UL>
</div> 

<div id=qh_con2 style="DISPLAY: none">
<UL></UL>
</div> 

<div id=qh_con3 style="DISPLAY: none"><UL>
  <LI><a href="research?type=2"><span>研究方向</span></a></LI> <LI class=menu_line2></LI>
  <LI><A href="subject?type=7"><SPAN>承担课题</SPAN></A></LI>
</UL>
</div> 

<div id=qh_con4 style="DISPLAY: none"><UL>
	<c:forEach var="type" items="${memberTypes}">
  		<li><a href="members?type=3&memberType=${type.id }"><span>${type.codeName }</span></a></li><li class=menu_line2></li>
	</c:forEach>
</UL>
</div>

<div id=qh_con5 style="DISPLAY: none">
<UL></UL>
</div>

<div id=qh_con6 style="DISPLAY: none">
<UL></UL>
</div>

<div id=qh_con7 style="DISPLAY: none">
<UL></UL>
</div>

</div>
</div>
</div>
</div>

  <!-- 内容部分-->
  <div id="main">
    <h1 class="mainTitle">
       <img src="img/chengyuanjieshao_11.jpg" alt="成员介绍"/>
    </h1>
    <div class="divMain">
      <!--begin-->
     <div class="sideBar">
			<ul>
				<c:forEach var="type" items="${memberTypes}">
			  		<LI><a href="members?type=3&memberType=${type.id }">${type.codeName }</a></LI>
				</c:forEach>
			</ul>
    </div>
      <div class="mainRight">
       	 <h1 class="mTitle">
  			<span>${currentMemberType }</span>
       	 </h1>
       <div class="dlList">			
         <dl>
	       <dt><img src="${website.photo }"/></dt>
	       <dd><b>${website.title }</b></dd>
	       <dd class="txt">
	       <!--begin-->
	       <p><b>${website.subhead }</b></p>
<p align="left"><span>${website.neirong }</span></p>	<!--end-->
	</dd>
	<div class="clearDiv"></div>
</dl>
<div class="close"><a href="members?type=3" >[ 关闭 ]</a></div>

			</div>
      </div>
      <div class="clearDiv"></div>
      <!--end-->
    </div>
    
  </div>
   <!-- footer开始-->
   <div id="footer">
	<div class="footerTxt">
	<span>Copyright © 广州明恒软件有限公司. All Rights Reserved.版权所有</span><br/>
	
    <p>
	<span > 电话：020-39358631,39358632</span> </p>
	</div>
</div>
   <!-- footer结束-->
</div>
 
</body>
</html>

