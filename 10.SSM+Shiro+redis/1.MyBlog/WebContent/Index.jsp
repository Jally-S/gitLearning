<%@ page language="java" import="java.util.*" import="cap.bean.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<jsp:include page="frame/Header.jsp"></jsp:include>

<body>
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="index.html">J2EE 博客</a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse navbar-ex1-collapse">
				<ul class="nav navbar-nav">
					<li><a href="<%=basePath%>index.html">网站首页</a></li>
				</ul>
				<c:if test="${user!=null && user.isApplied==1}">

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
									href="<%=basePath%>comment.html?action=manage&userId=${user.id}"><i
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
					<c:when test="${user!=null }">

						<div class="pull-right">
							<ul class="nav pull-right">
								<li class="dropdown"><a href="#" class="dropdown-toggle"
									data-toggle="dropdown">欢迎，${user.userName} <b class="caret"></b></a>
									<ul class="dropdown-menu">
										<li><a
											href="<%=basePath%>user?action=profile&id=${user.id}"><i
												class="glyphicon glyphicon-cog"></i> 编辑个人信息</a></li>
										<c:if test="${user.isApplied==1}">

											<li class="divider"></li>
											<li><a
												href="<%=basePath%>user?action=bloginfo&userId=${user.id}"><i
													class="glyphicon glyphicon-cog"></i> 编辑博客信息</a></li>
										</c:if>
										<li class="divider"></li>
										<li><a href="user?action=logout"><i
												class="glyphicon glyphicon-off"></i> 登出</a></li>
									</ul></li>
							</ul>
						</div>

					</c:when>
				</c:choose>
			</div>
		</div>
	</nav>

	<div class="container">

		<div class="row">
			<div id="blog" class="col-lg-8">
				<h1>
					<a href="user?action=index">轻博客——<small>基于JSP,
							Servlet技术构建</small></a>
				</h1>
				<br>
				<c:choose>
					<c:when test="${artList!=null && fn:length(artList)>0}">
						<c:forEach items="${artList}" var="art">

							<h3>
								<a
									href="<%=basePath%>comment.html?action=post&artId=${art.id }&userId=${art.userId }"
									target="_blank">${art.title }</a>
							</h3>
							<p>
								<i class="glyphicon glyphicon-user"></i> <a
									href="<%=basePath%>user?action=myblog&userId=${art.userId }"
									target="_blank">${art.username }</a> | <i
									class="glyphicon glyphicon-calendar"></i> ${art.publishTime } |
								阅读 ${art.count } 次
							</p>

							<p>${art.summary }</p>
							<br>
							<a class="btn btn-primary"
								href="<%=basePath%>comment.html?action=post&artId=${art.id }&userId=${art.userId }">Read
								More <span class="glyphicon glyphicon-chevron-right"></span>
							</a>
							<hr>
						</c:forEach>
					</c:when>

					<c:otherwise>
						<p>还没有写过文章哦，赶紧写吧~</p>

					</c:otherwise>
				</c:choose>
				<!-- pager -->
				<ul class="pager">
					<c:if test="${curPage > 1}">
						<li class="previous"><a
							href="<%=basePath%>user?action=index&curPage=${curPage-1}">&larr;
								上一页</a></li>
					</c:if>
					<c:if test="${curPage < totalPages}">
						<li class="next"><a
							href="<%=basePath%>user?action=index&curPage=${curPage+1}">下一页
								&rarr;</a></li>
					</c:if>
				</ul>

			</div>

			<div class="col-lg-4">
				<c:if test="${user!=null&&user.isApplied==0}">

					<div class="well" align="center">
						<a class="btn btn-primary" href="<%=basePath%>ApplyBlog.jsp"
							target="_blank">申请个人博客</a>
					</div>

				</c:if>
				<c:if test="user!=null&&user.isApplied==1">

					<div class="well" align="center">
						<a class="btn btn-primary"
							href="<%=basePath%>user?action=myblog&userId=${user.id}">进入我的博客</a>
					</div>

				</c:if>
				<div class="well">
					<h4>搜索站内文章</h4>
					<form name="search_form" action="<%=basePath%>user?action=search"
						method="post">
						<div class="input-group">
							<input type="text" class="form-control" name="q"> <span
								class="input-group-btn">
								<button class="btn btn-default" type="submit">
									<span class="glyphicon glyphicon-search"></span>
								</button>
							</span>
						</div>
					</form>
				</div>


				<form action="servlet/GetSysCategoryServlet" method="GET">
					<div class="well">
						<h4>网站分类</h4>
						<div class="row">
							<div class="col-lg-6">
								<ul class="list-unstyled">
									<c:choose>
										<c:when test="${scList!=null && fn:length(scList)>0}">
											<c:forEach items="${scList }" var="sc">
												<li><a href="#">${sc.categoryName }</a></li>
											</c:forEach>
										</c:when>
										<c:otherwise>
										<li>无分类</li>
										</c:otherwise>
									</c:choose>
								</ul>
							</div>
						</div>
					</div>
					<!-- /well -->
				</form>

				<div class="well">
					<h4>本周活跃博主</h4>
					<div class="row">
						<div class="">
							<ul class="list-unstyled">
							<c:choose>
							<c:when test="${uList!=null && fn:length(uList)>0}">
							<c:forEach items="${uList}" var="u" varStatus="status">
								<li><a
									href="<%=basePath%>user?action=myblog&userId=${u.id}"
									target="_blank"> ${status.index +1}. ${u.userName }</a></li>								
								</c:forEach>
								</c:when>
								<c:otherwise>
								<li>暂无排名，sorry......</li>
								
								</c:otherwise>
								</c:choose>
							</ul>
						</div>
					</div>
				</div>
				<!-- /well -->

				<div class="well">
					<h4>博文TOP10</h4>
					<div class="row">
						<div class="">
							<ul class="list-unstyled">
							<c:choose>
							<c:when test="${tenList!=null }">
							<c:forEach items="${tenList }" var="art" varStatus="status">							
								<li><a
									href="<%=basePath%>comment.html?action=post&artId=${art.id }&userId=${art.userId}"
									target="_blank">${status.index +1}. ${art.title }</a></li>								
								</c:forEach>
								</c:when>
								<c:otherwise>
								<li>暂无排名，sorry......</li>							
								</c:otherwise>
								</c:choose>
							</ul>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>


	<jsp:include page="frame/Footer.jsp"></jsp:include>