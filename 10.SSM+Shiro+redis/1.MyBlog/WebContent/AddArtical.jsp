<%@ page language="java" import="java.util.*" import="cap.bean.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%

//List<Article> artList = (List<Article>)request.getAttribute("artList");
//List<Category> cgList = (List<Category>)request.getAttribute("cgList");
//List<SysCategory> scgList = (List<SysCategory>)request.getAttribute("scgList");
%>

<jsp:include page="frame/Header.jsp"></jsp:include>

<body>
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <a class="navbar-brand" href="index.html">JSP 博客</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse navbar-ex1-collapse">
          <ul class="nav navbar-nav">
            <li><a href="index.html">首页</a></li>
          </ul>
          <c:if test="${user!=null }">
          <ul class="nav navbar-nav">
            <li><a href="<%=basePath %>user?action=myblog&userId=${user.id}">我的博客</a></li>
          </ul>
          
          <ul class="nav navbar-nav">
            <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">博客管理<b class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li><a href="<%=basePath %>article?action=manage&userId=${user.id}"><i class="icon-cog"></i> 博文管理</a></li>
                    <li class="divider"></li>
                    <li><a href="<%=basePath %>category?action=manage&userId=${user.id}"><i class="icon-cog"></i> 分类管理</a></li>
                    <li class="divider"></li>
                    <li><a href="<%=basePath %>comment.html?action=manage&userId=${user.id}"><i class="icon-cog"></i> 评论管理</a></li>
                </ul>
            </li>
          </ul>
          </c:if>
          <c:choose>
          <c:when test="${user==null}">        
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
                            <li><a href="<%=basePath %>user?action=profile&id=${user.id}"><i class="icon-cog"></i> 编辑个人信息</a></li>
                            <c:if test="${user.isApplied==1 }">                          
                            <li class="divider"></li>
                            <li><a href="<%=basePath %>user?action=bloginfo&userId=${user.id}"><i class="icon-cog"></i> 编辑博客信息</a></li>                          
                            </c:if>
                            <li class="divider"></li>
                            <li><a href="<%=basePath %>user?action=logout"><i class="icon-off"></i> 登出</a></li>
                        </ul>
                    </li>
                </ul>
          </div>
         
          </c:otherwise>
          </c:choose>
        </div>
      </div>
    </nav>
    <c:if test="${user!=null }">  
   
	<div class="container">
		
		<div class="row col-md-12">
			<div class="col-md-12">
			   <div class="col-md-12">
					<ol class="breadcrumb">
		              <li><a href="<%=basePath%>article?action=manage&userId=${user.id}">博文管理</a></li>
		              <li class="active">新建文章</li>
		            </ol>
	            </div>
	            
	            <form class="form-horizontal" name="add_artical_form" action="<%=basePath %>article?action=save&userId=${user.id}" method="post">
	                <div class="col-md-6">
	                    <div class="form-group">
	                    <label for="title">标题</label>
	                    <input class="form-control" id="name" name="title" type="text" >
	                    </div>
	                    <div class="form-group">
	                    <label for="sys_category">系统分类</label>
	                        <select class="form-control"  id="subject" name="sys_category">
	                    	<c:choose>
	                    	<c:when test="${scgList!=null && fn:length(scgList)>0 }">
	                    	<c:forEach items="${scgList }" var="scg">
	                    	<option value="${scg.id }">${scg.categoryName }</option>
	                    	</c:forEach>
	                    	</c:when>	                    	
                            <c:otherwise>                        
                                <c:out value="获取系统分类失败"></c:out>
                            </c:otherwise>
                            </c:choose>
                            </select>
                         </div> 
                         <div class="form-group">  
                        <label for="category">个人分类</label>
                        	<select class="form-control" id="subject" name="category">
                        	<c:choose>
                        	<c:when test="${cgList!=null && fn:length(cgList)>0 }">
                        	<c:forEach items="${cgList }" var="cg">
                        	<option value="${cg.id }">${cg.categoryName}</option>
                        	</c:forEach>
                        	</c:when>                          
                            <c:otherwise>
                            <c:out value="无分类"></c:out>
                            </c:otherwise>
                            </c:choose>
                        	</select>
                        </div>
	                
	                <div class="form-group">
	                    <textarea class="form-control" id="message" name="summary"  placeholder="摘要" rows="5"></textarea>
	                </div>
	                
	                <div class="form-group">
	                    <textarea class="form-control" id="message" name="content"  placeholder="文章内容" rows="5"></textarea>
	                </div>
	                  
	                <div class="form-group">
	                    <button id="contact-submit" type="submit" class="btn btn-primary input-medium pull-right">保存</button>
	                </div>
	                </div>
	            </form>
	        </div>
		</div>
	</div>
	
	</c:if>  
<jsp:include page="frame/Footer.jsp"></jsp:include>