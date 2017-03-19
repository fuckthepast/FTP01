<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>首页</title>
<link href="home.css" rel="stylesheet" type="text/css"/>
<link href="css/nav.css" rel="stylesheet" type="text/css"/>
<script src="js/yu.js" type="text/javascript"></script>
<script src="js/tb.js" type="text/javascript"></script>
<script src="js/navSelect.js" type="text/javascript"></script><!-- 导航栏选择效果实现 -->
<script language="javascript">
	
	function DrawImage(ImgD,FitWidth,FitHeight){ 
    var image=new Image(); 
    image.src=ImgD.src; 
    if(image.width>0 && image.height>0){ 
        if(image.width/image.height>= FitWidth/FitHeight){ 
            if(image.width>FitWidth){ 
                ImgD.width=FitWidth; 
                ImgD.height=(image.height*FitWidth)/image.width; 
            }else{ 
                ImgD.width=image.width; 
                ImgD.height=image.height; 
            } 
        } else{ 
            if(image.height>FitHeight){ 
                ImgD.height=FitHeight; 
                ImgD.width=(image.width*FitHeight)/image.height; 
            }else{ 
                ImgD.width=image.width; 
                ImgD.height=image.height; 
            } 
        } 
    } }
    
   

</script> 



</head>

<body onload="javascript:qiehuan(0,0)">
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
<ul id="nav"  onmouseout="javascript:defaultCheck(0)" >
<li><a class="nav_on" id="mynav0" onmouseover=javascript:qiehuan(0,0) href="home"><span>首 页</span></a></li>
<li class="menu_line"></li><li><a href="information" onmouseover="javascript:qiehuan(1,0)" id="mynav1" class="nav_off"><span>实验室概况</span></a></li>
<li class="menu_line"></li><li><a href="Notice?type=1" onmouseover="javascript:qiehuan(2,0)" id="mynav2" class="nav_off"><span>公告发布</span></a></li>
<li class="menu_line"></li><li><a href="research?type=2" onmouseover="javascript:qiehuan(3,0)" id="mynav3" class="nav_off"><span>科学研究</span></a></li>
<li class="menu_line"></li><li><a href="members?type=3" onmouseover="javascript:qiehuan(4,0)" id="mynav4" class="nav_off"><span>成员介绍</span></a></li>
<li class="menu_line"></li><li><a href="guiZhangZhiDu?type=4" onmouseover="javascript:qiehuan(5,0)" id="mynav5" class="nav_off"><span>规章制度</span></a></li>
<li class="menu_line"></li><li><a href="xueShuJiaoLiu?type=5" onmouseover="javascript:qiehuan(6,0)" id="mynav6" class="nav_off"><span>学术交流</span></a></li>
<li class="menu_line"></li><li><a href="papers?type=6" onmouseover="javascript:qiehuan(7,0)" id="mynav7" class="nav_off"><span>论文成果</span></a></li>

</ul>

<div id=menu_con>

<div id=qh_con0 style="DISPLAY: block">
<ul></ul>
</div> 

<div id=qh_con1 style="DISPLAY: none">
<ul></ul>
</div> 

<div id=qh_con2 style="DISPLAY: none">
<ul></ul>
</div> 

<div id=qh_con3 style="DISPLAY: none"><UL>
  <LI><a href="research?type=2"><span>研究方向</span></a></LI> <LI class=menu_line2></LI>
  <LI><A href="subject?type=7"><SPAN>承担课题</SPAN></A></LI>
</UL>
</div> 

<div id=qh_con4 style="DISPLAY: none"><ul>
	<c:forEach var="type" items="${memberTypes}">
  		<li><a href="members?type=3&memberType=${type.id }"><span>${type.codeName }</span></a></li><li class=menu_line2></li>
	</c:forEach>
</ul>
</div>

<div id=qh_con5 style="DISPLAY: none">
<ul></ul>
</div>

<div id=qh_con6 style="DISPLAY: none">
<ul></ul>
</div>

<div id=qh_con7 style="DISPLAY: none">
<ul></ul>
</div>

</div>
</div>
</div>
</div>

  <!-- 内容部分开始-->
  <div id="main">
   <!-- part1开始-->
    <div class="part1">
      <div class="part1_left" >
      <div id="MainPromotionBanner">
       <div id="SlidePlayer">
        <ul class="Slides">
         <li><a target="_blank" href=""><img src="img/01.jpg"/></a></li>
          <li><a target="_blank" href=""><img src="img/02.jpg"/></a></li>
         <li><a target="_blank" href=""><img src="img/03.jpg"/></a></li>
         <li><a target="_blank" href=""><img src="img/04.jpg"/></a></li>
         <li><a target="_blank" href=""><img src="img/05.jpg"/></a></li>
         <li><a target="_blank" href=""><img src="img/06.jpg"/></a></li>
        </ul>
      </div>
      <script type="text/javascript">
			TB.widget.SimpleSlide.decoration('SlidePlayer', {eventType:'mouse', effect:'scroll'});
      </script>
    </div>

      </div>
      <div class="part1_right">
        <h1>
          <a href="Notice?type=1">
            <img src="img/more.gif" alt="更多" border="0"/>
          </a>
        </h1>
        <ul>
   		<c:forEach var="website" items="${websites}">
          <li><a href="noticedetail?id=${website.id }&type=1" target="_blank" title="${website.title} "><span>${website.timeStr }</span>${website.title}</a></li>
		</c:forEach>
        </ul>
      </div>
    </div>
    <!-- part1结束-->
    <!-- part2开始-->
    <div id="part2">
    <!-- part2_left开始-->
      <div id="part2_left">
        <h1 class="leftH1">
          <a href="information">
            <img class="img" src="img/more.gif" alt="更多" />
          </a>
        </h1>
        <p class="homeP" style="text-indent:2em">
			${introduction }
        </p>
      </div>
      <!-- part2_left结束-->
      <!-- part2_right开始-->
      <div id="part2_right">
        <h1 class="rightH1">
          <a href="research?type=2">  
            <img src="img/more.gif" alt="更多" border="0"/>
          </a>
        </h1>
        <div class="rightDL">
        <!--begin-->
        <c:forEach var="website" items="${researchForwards}">
          <dl>
            <dt><img src="${website.photo }" onload="javascript:DrawImage(this,67,67);"/></dt>
            <dd><a href="research?id=${website.id }&type=2" target="_blank">${website.title } </a></dd>
          </dl>
        </c:forEach>
        <!--end-->
        </div>
      </div>
    </div>
     <!-- part2结束-->
  </div>
  <!-- 内容部分结束-->
  <!-- footer开始-->
  <div id="footer">
    <div class="footerTxt">
      <span>Copyright © 广州明恒软件有限公司. All Rights Reserved.版权所有</span><br/>
      <p>
        <span > 电话：020-39358631,39358632</span> 
      </p>
    </div>
  </div>
   <!-- footer结束-->
</div>
</body>
</html>
