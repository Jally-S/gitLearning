$(function(){
	$("#submit").click(function(){
		var username = $("#username").val();
		var password = $("#password").val();
		var repassword = $("#repassword").val();
		var name = $("#name").val();
		var cardid = $("#cardid").val();
		var tel = $("#tel").val();
		
		if(username==""){
			alert("用户名不能为空!");
			return false;
		}
		if(password==""){
			alert("密码不能为空!");
			return false;
		}
		if(repassword==""){
			alert("确认密码不能为空!");
			return false;
		}
		if(name==""){
			alert("姓名不能为空!");
			return false;
		}
		if(tel==""){
			alert("联系电话不能为空!");
			return false;
		}
		if(cardid==""){
			alert("证件号不能为空!");
			return false;
		}
		if(password!=repassword){
			alert("两次密码不一致!");
			return false;
		}
		
		var nameReg = /^[a-zA-Z0-9_]{0,}$/;  
		if (!nameReg.test(username)) {  
			alert("用户名不能含有中文或特殊字符!");
		    return false;  
		}  
		
		var numReg = /^[0-9]*$/;
		if(!numReg.test(cardid)){
			alert("请输入正确的证件号!");
		    return false;  
		}
		
		if(!numReg.test(tel)){
			alert("请输入正确的电话号码!");
		    return false;  
		}
		
		$.post(
				"servlet/RegisterServlet",
				{
					method:"isReaderExist",
					username:username
				},
				function (data,stauts){
					if(data == "true"){
						alert("用户名已存在!");
						return false;
					}
					else{
						$("#registerForm").submit();
					}
				}
		);
		
	});
	
	$("#reset").click(function(){
		$("input").attr("value","");
	});
});