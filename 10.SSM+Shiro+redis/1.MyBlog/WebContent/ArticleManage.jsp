<%@ page language="java" import="java.util.*" import="cap.bean.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
session.setAttribute("basePath", basePath);
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
					<li><a href="index.html">首页</a></li>
				</ul>
				<c:if test="${user!=null }">				
				<ul class="nav navbar-nav">
					<li><a
						href="<%=basePath%>user?action=myblog&userId=${user.id}">我的博客</a></li>
				</ul>

				<ul class="nav navbar-nav">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">博客管理<b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a
								href="<%=basePath%>article?action=manage&userId=${user.id}"><i
									class="glyphicon glyphicon-cog"></i> 博文管理</a></li>
							<li class="divider"></li>
							<li><a
								href="<%=basePath%>category?action=manage&userId=${user.id}"><i
									class="glyphicon glyphicon-cog"></i> 分类管理</a></li>
							<li class="divider"></li>
							<li><a
								href="<%=basePath%>comment?action=manage&userId=${user.id}"><i
									class="glyphicon glyphicon-cog"></i> 评论管理</a></li>
						</ul></li>
				</ul>
				
                </c:if>
                <c:choose>
                <c:when test="${user==null }">
				
				<ul class="nav navbar-nav navbar-right">
					<li><a href="Login.jsp" target="_blank">登录</a></li>
					<li><a href="Register.jsp" target="_blank">注册</a></li>
				</ul>
				</c:when>
				<c:otherwise>
				
				<div class="pull-right">
					<ul class="nav pull-right">
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown">欢迎，${user.userName } <b
								class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a
									href="<%=basePath%>user?action=profile&id=${user.id}"><i
										class="glyphicon glyphicon-cog"></i> 编辑个人信息</a></li>
								<c:if test="${user.isApplied==1 }">
								
								<li class="divider"></li>
								<li><a
									href="<%=basePath%>user?action=bloginfo&userId=${user.id}"><i
										class="glyphicon glyphicon-cog"></i> 编辑博客信息</a></li>
								
								</c:if>
								<li class="divider"></li>
								<li><a href="<%=basePath%>user?action=logout"><i
										class="glyphicon glyphicon-off"></i> 登出</a></li>
							</ul></li>
					</ul>
				</div>
				
				</c:otherwise>
             </c:choose>
			</div>
			
		</div>
		
	</nav>
    <c:if test="${succMsg!=null }">
	<div class="container">
		<div class="alert alert-success">${succMsg }</div>
	</div>
	<c:remove var="succMsg"/>
    </c:if>
	<c:if test="${errorMsg!=null }">
	<div class="container">
		<div class="alert alert-error">${errorMsg }</div>
	</div>
	<c:remove var="errorMsg"/>
    </c:if>

	<c:if test="${deleSuccMsg!=null }">
	<div class="container">
		<div class="alert alert-success">${deleSuccMsg }</div>
	</div>
	<c:remove var="deleSuccMsg"/>
	</c:if>
	<c:if test="${deleErrorMsg!=null }">
	<div class="container">
		<div class="alert alert-error">${deleErrorMsg }</div>
	</div>
    <c:remove var="deleErrorMsg"/>
    </c:if>
	
	<c:if test="${user!=null }">
	<div class="container">
		<div class="btn-toolbar">
			<a class="btn btn-primary"
				href="<%=basePath%>article?action=add&userId=${user.id}">新建文章</a>
		</div>
		<div class="well">
			<table class="table">
				<thead>
					<tr>
						<th>标题</th>
						<th>系统分类</th>
						<th>个人分类</th>
						<th>最近一次修改时间</th>
						<th style="width: 50px;">操作</th>
					</tr>
				</thead>
				<tbody>
				<c:choose>
				<c:when test="${artList!=null }">
				<c:forEach items="${artList }" var="art">									
					<tr>
						<td><a
							href="<%=basePath %>article?action=update&artId=${art.id}">${art.title }</a></td>
						<td>${art.scName }</td>
						<td>${art.categoryName }</td>
						<td>${art.publishTime }</td>
						<td><a href="<%=basePath %>article?action=update&artId=${art.id}"><i
								class="glyphicon glyphicon-pencil"></i></a> 
						</td>
					    <td><a href="${basePath }article?action=delete&artId=${art.id }&userId=${user.id }"><i
								class="glyphicon glyphicon-remove"></i></a>
						</td>
														
					</tr>
										
					</c:forEach>
					</c:when>
					<c:otherwise>
					<c:out value="暂时没有文章"></c:out>
					</c:otherwise>
				</c:choose>
				</tbody>
			</table>
		</div>
		<div>
			<ul class="pager">
			<c:if test="${curPage>1 }">
				
				<li class="previous"><a
					href="<%=basePath%>article?action=manage&userId=${user.id }&curPage=${curPage-1}">&larr;
						上一页</a></li>
				
            </c:if>
            <c:if test="${curPage<totalPages }">				
				<li class="next"><a
					href="<%=basePath%>article?action=manage&userId=${user.id }&curPage=${curPage+1}">下一页
						&rarr;</a></li>
			</c:if>
			</ul>
		</div>


	</div>	
	</c:if>
	<jsp:include page="frame/Footer.jsp"></jsp:include>	