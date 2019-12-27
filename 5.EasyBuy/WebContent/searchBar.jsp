<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
</head>
<body>
<div class="top">
    <div class="logo">
        <a href="goodsHandler/getAll"><img src="images/logo.png"></a>
    </div>
    <div class="search">
        <form action="goodsList_queryByName?page=1" method="post">
            <input type="text" value="${keyWord}" name="keyWord" class="s_ipt">
            <input type="submit" value="搜索" class="s_btn">
        </form>
        <!--推荐最热商品-->
    </div>
    <div class="i_car">
        <div class="car_t">
            购物车 [
            <span>
                <c:if test="${sessionScope.cart!=null && sessionScope.cart.size()>0}">
                    ${sessionScope.cart.size()}
                </c:if>
                <c:if test="${sessionScope.cart==null || sessionScope.cart.size()<=0}">
                    空
                </c:if>
            </span>]
        </div>
        <div class="car_bg">
            <!--Begin 购物车未登录 Begin-->
           <%--  <c:if test="${sessionScope.user==null}">
                <div class="un_login">还未登录！<a href="login.jsp" style="color:#ff4e00;">马上登录</a></div>
            </c:if> --%>
            <%-- <c:if test="${sessionScope.user!=null}"> --%>
                <div class="un_login">我的购物车</div>
           <%--  </c:if> --%>
            <!--End 购物车未登录 End-->
            <!--Begin 购物车已登录 Begin-->
            <ul class="cars">
                <c:forEach items="${sessionScope.cart}" var="product">
                    <li>
                        <div class="img"><a href="goodsList_getProductById?id=${product.value.id}"><img src="images/${product.value.fileName }" width="58" height="58" /></a></div>
                        <div class="name"><a href="goodsList_getProductById?id=${product.value.id}">${product.value.name}</a></div>
                        <div class="price"><font color="#ff4e00">￥${product.value.price}</font> X${product.value.quantity}</div>
                    </li>
                </c:forEach>
            </ul>
            <div class="price_sum">共计&nbsp;<font color="#ff4e00">￥</font><span>${sessionScope.count}</span></div>
           <%--  <c:if test="${sessionScope.user==null}">
                <div class="price_a"><a href="login.jsp">去登录</a></div>
            </c:if> --%>
            <%-- <c:if test="${sessionScope.user!=null}"> --%>
                <div class="price_a"><a href="toSettlement.jsp">去结算</a></div>
           <%--  </c:if> --%>
            <!--End 购物车已登录 End-->
        </div>
    </div>
</div>

</body>

</html>

