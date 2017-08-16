/*function checkUser(){
		var name = $("#userName").val();
		var password = $("#userPassword").val();
		if (name==null||name==""||name==undefined) {
			$("#chens").text("请输入账号");
		}else if (password==null||password==""||password==undefined) {
			$("#chens").text("密码不能为空");
		}else{
		$.ajax({
			type:"post",
			url:"/ssh_movie/test!toLogin.action",
			data:{"user.userName":name,"user.userPassword":password},
			dataType:"json",
			success:function(result){
				if (result.status==1) {
					location.href="/ssh_movie/test!toShow.action";
				}
				if (result.status==2) {
					$("#chens").text("密码错误，请重新登录");
				}
				if (result.status==3) {
					$("#chens").text("用户名不存在，请重新登录");
				}
			}
		})
		}
	}*/