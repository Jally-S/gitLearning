<%@ page language="java" import="java.util.*" import="java.text.*" import="cap.bean.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
//User u = (User)request.getSession().getAttribute("user");
//List<Category> cgList = (List<Category>)request.getAttribute("cgList");
//List<Article> artList = (List<Article>)request.getAttribute("artList");

String q = (String)request.getAttribute("q");
%>
<jsp:include page="frame/Header.jsp"></jsp:include>

  <body>
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <a class="navbar-brand" href="index.html">JSP 博客</a>
        </div>

        <div class="collapse navbar-collapse navbar-ex1-collapse">
          <ul class="nav navbar-nav">
            <li><a href="<%=basePath%>index.html">网站首页</a></li>
          </ul>
          <c:if test="${user!=null }">
          
          
          <ul class="nav navbar-nav">
            <li><a href="<%=basePath%>user?action=myblog&userId=${user.id}">我的博客</a></li>
          </ul>
          
          <ul class="nav navbar-nav">
            <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">博客管理<b class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li><a href="<%=basePath%>article?action=manage&userId=${user.id}"><i class="icon-cog"></i> 博文管理</a></li>
                    <li class="divider"></li>
                    <li><a href="<%=basePath%>category?action=manage&userId=${user.id}"><i class="icon-cog"></i> 分类管理</a></li>
                    <li class="divider"></li>
                    <li><a href="<%=basePath%>comment.html?action=manage&userId=${user.id}"><i class="icon-cog"></i> 评论管理</a></li>
                </ul>
            </li>
          </ul>
         
          </c:if>
          <c:choose>
          <c:when test="${user!=null }">
         
          <ul class="nav navbar-nav navbar-right">
          	<li><a href="Login.jsp" target="_blank">登录</a></li>
          	<li><a href="Register.jsp" target="_blank">注册</a></li>
          </ul>
          </c:when>
          <c:otherwise>
        
          <div class="pull-right">
                <ul class="nav pull-right">
                    <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">欢迎，${user.userName} <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="<%=basePath%>user?action=profile&id=${user.id}"><i class="icon-cog"></i> 编辑个人信息</a></li>
                            <c:if test="${user.isApplied==1 }">                            
                            <li class="divider"></li>
                            <li><a href="<%=basePath%>user?action=bloginfo&userId=${user.id}"><i class="icon-cog"></i> 编辑博客信息</a></li>
                            
                            </c:if>
                            <li class="divider"></li>
                            <li><a href="<%=basePath%>user?action=logout"><i class="icon-off"></i> 登出</a></li>
                        </ul>
                    </li>
                </ul>
          </div>
          </c:otherwise>
          </c:choose>
        </div>
      </div>
    </nav>

    <div class="container">

      <div class="row">
        <div class="col-lg-8">

          <p class="lead">
			搜索关键字：
			<c:choose>
			<c:when test="${q!=null }">			
			<b><i>	${q }</i></b>
			</c:when>
			<c:otherwise>
			<c:out value="没有输入搜索内容"/>			
		    </c:otherwise>
			</c:choose>
          </p>
          <hr>
          <p class="lead">
          	搜索结果列表：
          </p>
          <c:choose>
          <c:when test="${artList!=null && fn:length(artList) >0 }">
          <c:forEach items="${artList}" var="art">         
 		  <h4>
 		  <a href="<%=basePath%>comment.html?action=post&artId=${art.id }&userId=${user.id}" target="_blank">${art.title }</a>
 		  </h4>
 		  </c:forEach>
          </c:when>
          <c:otherwise>
          <c:out value="对不起，没有搜索到"></c:out>
          </c:otherwise>
          </c:choose>

        </div>
      </div>
    </div>
    
<jsp:include page="frame/Footer.jsp"></jsp:include>
