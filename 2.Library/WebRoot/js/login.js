$(function(){
	$("#submit").click(function(){
		var username = $("#username").val();
		var password = $("#password").val();
		if(username == ""){
			alert("用户名不能为空!");
			return false;
		}
		if(password == ""){
			alert("密码不能为空!");
			return false;
		}
		var type=$('input:radio[name="type"]:checked').val();
		$.post(
				"servlet/LoginServlet",
				{
					username:username,
					password:password,
					type:type
				},
				function(data,status){
					if(data == "false"){
						alert("用户名或密码错误!");
						return false;
					}
					if(data == "true"){
						if(type=="reader"){
							window.location.href="servlet/BookServlet?method=getAll";
						}
						if(type=="admin"){
							window.location.href="servlet/BookServlet?method=getExamineBorrow";
						}
					}
				}
		);
	});
	$("#reset").click(function(){
		$("input").attr("value","");
	});
})