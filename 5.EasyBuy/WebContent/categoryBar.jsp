<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="nav">
	<div class="nav_t">全部商品分类</div>
	<div class="leftNav none" style="display: none;">
		<ul>
			<c:forEach items="${list }" var="products">
				<li>
					<div class="fj">
						<span class="n_img"><span></span> <img src="" /></span> <span
							class="fl">${products.name }</span>
					</div>
					<div class="zj">
						<div class="zj_l">

							<c:forEach items="${list2 }" var="products2">
								<c:if test="${products.id == products2.parentId }">
									<div class="zj_l_c">
										<h2>
											<a
												href="goodsList_queryGoodsBylevel?level=2&id=${products2.id }&page=1">${products2.name
												}</a>
										</h2>
										<c:forEach items="${list3}" var="products3">
											<c:if test="${products2.id == products3.parentId }">
												<a href="goodsList_queryGoodsBylevel?level=3&id=${products3.id }&page=1">${products3.name }</a>|							
														</c:if>
										</c:forEach>
									</div>
								</c:if>
							</c:forEach>
						</div>
					</div>
				</li>
			</c:forEach>
		</ul>
	</div>
</div>
<ul class="menu_r">
	<li><a href="easybuy_queryAll">首页</a></li>
	<c:forEach items="${list }" var="goods">
		<li><a
			href="goodsList_queryGoodsBylevel?level=1&id=${goods.id }&page=1">${goods.name
				}</a></li>
	</c:forEach>
</ul>
<div class="m_ad">中秋送好礼！</div>