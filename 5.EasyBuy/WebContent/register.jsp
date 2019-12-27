<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
   	<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="js/register.js"></script>
   	<%@ include file="header.jsp" %>
    <title>易买网</title>
</head>
<body>
<!--Begin Login Begin-->
<div class="log_bg">
    <div class="top">
        <div class="logo"><a href=""><img src="images/logo.png"/></a></div>
    </div>
    <div class="regist">
        <div class="log_img"><img src="images/l_img.png" width="611" height="425"/></div>
        <div class="reg_c">
            <form id="register" action="easybuy_regist" method="post">
                <table border="0" style="width:420px; font-size:14px; margin-top:20px;" cellspacing="0" cellpadding="0">
                    <tr height="50" valign="top">
                        <td width="95">&nbsp;</td>
                        <td>
                            <span class="fl" style="font-size:24px;">注册</span>
                            <span class="fr">已有商城账号，<a href="login.jsp" style="color:#ff4e00;">我要登录</a></span>
                        </td>
                    </tr>
                    <tr height="50">
                        <td align="right"><font color="#ff4e00">*</font>登录用户名 &nbsp;</td>
                        <td><input type="text" value="" name="user.loginName" class="l_user"  pattern="^[A-Za-z0-9]+$" title="用户名由字母和数字组成" required/></td>
                    </tr>
                    <tr height="50">
                        <td align="right"><font color="#ff4e00">*</font>&nbsp;密码 &nbsp;</td>
                        <td><input type="password" value="" name="user.password" class="l_pwd" pattern="^[a-zA-Z0-9]{6,10}$" title="密码由6-10位字母和数组组成" required/></td>
                    </tr>
                    <tr height="50">
                        <td align="right"><font color="#ff4e00">*</font>&nbsp;真实姓名 &nbsp;</td>
                        <td><input type="text" value="" name="user.userName" class="l_user" pattern="^[\u4e00-\u9fa5]+$" title="请输入正确的姓名" required/></td>
                    </tr>
                    <tr height="50">
                        <td align="right"><font color="#ff4e00">*</font>&nbsp;性别 &nbsp;</td>
                        <td>
                            <input type="radio" name="user.sex" value="1" checked="checked">&nbsp;男&nbsp;&nbsp;
                            <input type="radio" name="user.sex" value="0">&nbsp;女
                        </td>
                    </tr>

                    <tr height="50">
                        <td align="right">&nbsp;身份证号 &nbsp;</td>
                        <td><input type="text" value="" name="user.identityCode" class="l_user" pattern="(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)" title="请输入正确的身份证号" required/></td>
                    </tr>
                    <tr height="50">
                        <td align="right">&nbsp;邮箱 &nbsp;</td>
                        <td><input type="text" value="" name="user.email" class="l_email" pattern="^\w+@\w+(\.\w+){1,2}$" title="请输入正确的邮箱" required/></td>
                    </tr>
                    <tr height="50">
                        <td align="right">&nbsp;手机 &nbsp;</td>
                        <td><input type="text" value="" name="user.mobile" class="l_tel" pattern="^(13|15|18)\d{9}$" title="请输入正确的手机" required/></td>
                    </tr>
                    <tr height="60">
                        <td>&nbsp;</td>
                        <td><input type="submit" value="立即注册" class="log_btn" onclick="register()"/></td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>
<!--End Login End-->
<!--Begin Footer Begin-->
<div class="btmbg">
    <div class="btm">
        备案/许可证编号：蜀ICP备12009302号-1-www.dingguagua.com Copyright © 2015-2018 尤洪商城网 All Rights Reserved. 复制必究 , Technical
        Support: Dgg Group <br/>
        <img src="images/b_1.gif" width="98" height="33"/><img src="images/b_2.gif"  width="98" height="33"/><img
            src="images/b_3.gif" width="98" height="33"/><img src="images/b_4.gif"   width="98" height="33"/><img
            src="images/b_5.gif" width="98" height="33"/><img src="images/b_6.gif"   width="98" height="33"/>
    </div>
</div>
</body>
</html>