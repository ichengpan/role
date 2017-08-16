<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>用户注册</title>
        <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
		
        <!-- 用于适应不同设备 -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
	
        <!-- bootstrap 样式表 -->
        <link rel="stylesheet" href="http://apps.bdimg.com/libs/bootstrap/3.2.0/css/bootstrap.min.css">
        <script src="http://apps.bdimg.com/libs/jquery/2.0.0/jquery.min.js"></script>
        <script src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>
		
        <!-- HTML5 Shim 和 Respond.js 用于让 IE8 支持 HTML5元素和媒体查询 -->
        <!-- 注意： 如果通过 file://  引入 Respond.js 文件，则该文件无法起效果 -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
        <![endif]-->
		<style type="text/css">
			.msg{
				font-weight: bold;
				font-size: 20px;
			}
			.success{
				color: green;
			}
			
			.error{
				color: red;
			}
			
		</style>
		<script> 
		function showtime(t){ 
			showCodeInput();
			var phoneNumber = $("#textTel").val();
			sendPhoneCode(phoneNumber);
		    document.myform.phone.disabled=true; 
		    for(i=1;i<=t;i++) { 
		        window.setTimeout("update_p(" + i + ","+t+")", i * 1000); 
		    } 
		 
		} 
		 
		function update_p(num,t) { 
		    if(num == t) { 
		        document.myform.phone.value =" 重新发送 "; 
		        document.myform.phone.disabled=false; 
		    } 
		    else { 
		        printnr = t-num; 
		        document.myform.phone.value = " (" + printnr +")秒后重新发送"; 
		    } 
		} 
	</script>
        <script type="text/javascript"> 
            var flag = {
                "email":false,
                "username":false,
                "password":false,
                "telephone":false,
            };

            $(function(){
                // email验证  
                $("#txtEmail").blur(function(){
                    var email = $(this).val();

                    var pattern=/\b(^['_A-Za-z0-9-]+(\.['_A-Za-z0-9-]+)*@([A-Za-z0-9-])+(\.[A-Za-z0-9-]+)*((\.[A-Za-z0-9]{2,})|(\.[A-Za-z0-9]{2,}\.[A-Za-z0-9]{2,}))$)\b/; 
                    if(!pattern.test(email)){
                        $("#email\\.info").html('<font class="msg error">Email格式不正确</font>');
                        flag.email = false;
                        return;
                    }else{
                        $("#email\\.info").html('<font class="msg success">√</font>');
                        flag.email = true;
                        return;
                    }

                    //  邮箱重复校验
                });

                $("#txtUserName").blur(function(){
                    // 用户名校验
                    var username = $(this).val();

                    // 校验规则，可调整
                    var pattern = /\b(^['A-Za-z0-9]{4,20}$)\b/;
                    if(!pattern.test(username)){
                        $("#username\\.info").html('<font class="msg error">用户名不合法</font>');
                        flag.username = false;
                        return;
                    }else{
                    	 $.ajax({
              			   type:"post",
              			   url:"<%=request.getContextPath() %>/registerCheckAccount.jhtml",
              			   data:{userAccount:username},
              			   dataType:"json",
              			   success:function(result) {
              				   console.log(result.status);
              				   if(result.status == 1){
              					 $("#username\\.info").html('<font class="msg error">用户名已存在</font>');
              					 flag.username = false;
              					 return;
              				   } else {
              					 flag.username = true;
              					 $("#username\\.info").html('<font class="msg success">√</font>');
              					 return;
              				   }
              			   }
              		   });
                       // $("#username\\.info").html('<font class="msg success">√</font>');
                        //flag.username = true;
                    }
                });
                
                
                
                

                // 密码校验
                $("#txtPassword").blur(function(){
                    var password=$(this).val(); 

                    var pattern = /\b(^['A-Za-z0-9]{4,20}$)\b/; 
                    if (!pattern.test(password)) { 
                        $("#password\\.info").html('<font class="msg error">密码格式不正确</font>'); 
                        flag.password = false;
                        return;    
                    }else{ 
                        $("#password\\.info").html('<font class="msg success">√</font>'); 
                        flag.password = false;
                        return; 
                    } 
                });


                // 密码重复校验
                $("#txtRepeatPass").blur(function(){
                    var repeatPass = $(this).val();

                    var pattern = /\b(^['A-Za-z0-9]{4,20}$)\b/; 
                    if ("" == repeatPass || null == repeatPass){
                    	$("#repeatPass\\.info").html('<font class="msg error">密码不能为空</font>');
                    	flag.password = false;
                    	return;
                    } else if (repeatPass!=$("#txtPassword").val()) { 
                        $("#repeatPass\\.info").html('<font class="msg error">两次密码输入不一致</font>'); 
                        flag.password = false;
                        return;
                    } else{
                        $("#repeatPass\\.info").html('<font class="msg success">√</font>'); 
                        flag.password = true;
                        return;
                    }
                });

              /*   $("#form").submit(function(){
                    var ok = flag.email&&flag.password;
                    if(ok==false){
                        alert("表单项正在检测或存在错误");
                        history.back();
                        return false;
                    }
                    return true;
                }); */
            })
            
            // 手机号码正则
            function checkTel(){
            	// 验证手机号码
                var contactTelValue = $("#textTel").val();
                checkPhoneNumber(contactTelValue);
            }
            /* 正则验证，检验手机号码的正确性 */
            function checkPhoneNumber(contactTelValue){
                	//验证电话   
                    if(contactTelValue!="" && null != contactTelValue){  
                        var pattern=/^(13[0-9]|15[0-9]|17[0-9]|18[8|9])\d{8}$/;   
                        var phoneflag = pattern.test(contactTelValue); 
                        if(!phoneflag){  
                        	flag.telephone = false;
                            $("#tel\\.info").html('<font class="msg error">手机号码格式不正确</font>'); 
                            return phoneflag;  
                        }else{
                        	checkResponseCode();
                        	$("#tel\\.info").html('<font class="msg success">√</font>');
                        	return phoneflag;  
                        }
                        
                    }
                }
            function registerUser(){
            	var ok = flag.email && flag.username && flag.password && flag.telephone;
	            if(ok){
	            	$.ajax({
	           			   type:"post",
	           			   url:"<%=request.getContextPath() %>/registerUser.jhtml",
	           			   data:$("#add_user_form").serialize(),
	           			   dataType:"json",
	           			   success:function(result) {
	           				   location.href="<%=request.getContextPath() %>/index.jsp";
	           			   }
	            	});
	            }else{
	            	alert("信息填写错误，需要重新更改");
	            }
            }
        </script> 
    </head>
	<script type="text/javascript">
		/* 去登录界面 */
		function toIndex(){
			location.href = "<%=request.getContextPath()%>/index.jsp";
		}
		
		//检查验证码
		function checkResponseCode(){
                // 检查输入的手机验证码
                var telCodeNum = $("#telCodeNum").val();
                var phoneNumber = $("#textTel").val();
                if(telCodeNum !=undefined && undefined != ""){
                	$.ajax({
           			   type:"post",
           			   url:"<%=request.getContextPath() %>/CheckCodeNumber.jhtml",
           			   data:{phoneCode:telCodeNum,PhoneNumber:phoneNumber},
           			   dataType:"json",
           			   success:function(result) {
           				   if(result.codeStatus == 1){
	           					$("#tel\\.info").html('<font class="msg success">验证码正确</font>');
	    	                	flag.telephone=true;
	    	                	return;
           				   }else{
	           					$("#tel\\.info").html('<font class="msg error">验证码错误</font>'); 
	    	                	flag.telephone=false;
	    	                	return;
           				   }
           			   }
                	});
                	
	                //发送的验证码
	               /*  var telCodeResponse = $("#telCodeResponse").val();
	                if(telCodeNum == telCodeResponse){
	                	$("#tel\\.info").html('<font class="msg success">√</font>');
	                	flag.telephone=true;
	                	return;
	                }else{
	                	$("#tel\\.info").html('<font class="msg error">验证码错误</font>'); 
	                	flag.telephone=false;
	                	return;
	                } */
            	}
		}
		
		function sendPhoneNum(){
			
			var contactTelValue = $("#textTel").val();
			if(checkPhoneNumber(contactTelValue)){
				showtime(60);
			}else{
				flag.telephone=false;
				$("#tel\\.info").html('<font class="msg error">手机号码格式不正确</font>');
			}
		}
		
		/* 给手机发送验证码 */
		function sendPhoneCode(phoneNumber){
        	$.ajax({
    			   type:"post",
    			   url:"<%=request.getContextPath() %>/registerCheckPhoneNumber.jhtml",
    			   data:{PhoneNumber:phoneNumber},
    			   success:function(result) {
    				   
    			   }
         	});
        }
		
		/**
		点击发送验证码，显示出验证码的输入框
		*/
		function showCodeInput(){
			$("#tel\\.codeInput").html('<input name="telNum" type="text"  onblur="checkResponseCode()" class="form-control" id="telCodeNum" placeholder="请输入验证码" required>');
			
		}
	</script>
    	<body style="padding-top:50px;">
		   <form class="form-horizontal" role="form" name="myform" method="post" id="add_user_form">
			   <div class="form-group">
			      <label for="uname" class="col-sm-2 control-label">用户名</label>
			      <div class="col-sm-5">
			         <input type="text" class="form-control" id="txtUserName" name="userAccount" placeholder="请输入用户名" required>
			      </div>
			      <div class="col-sm-2">
			          <span id="username.info"  ></span>
			      </div>
			   </div>
			
			   <div class="form-group">
			      <label for="lastname" class="col-sm-2 control-label">注册邮箱</label>
			      <div class="col-sm-5">
			         <input name="email" type="text" class="form-control" id="txtEmail" placeholder="请输入注册邮箱" required>
			      </div>
			      <div class="col-sm-2">
			          <span id="email.info"  ></span>
			      </div>   
			   </div>
			
			   <div class="form-group">
			      <label for="lastname" class="col-sm-2 control-label">手机号码</label>
			      <div class="col-sm-2">
			         <input name="phoneNumber" type="text" class="form-control" onblur="checkTel()" id="textTel" placeholder="请输入手机号码" required>
			      </div>
			      <div class="col-sm-1" >
			      	<!-- <input type="button" class="btn btn-info" id="btn" value="获取验证码" onclick="settime(this)" /> -->
			      	<input type="button" class="btn btn-info" value="获取验证码"  name="phone" onclick="sendPhoneNum()"> 
			      </div>
			      <div class="col-sm-2">
			      	<span id="tel.codeInput"></span>
			      </div>
			      <div class="col-sm-2">
			      	<span id="tel.info"></span>
			      </div>
			   </div>
			   
			   <div class="form-group">
			       <label for="passwd" class="col-sm-2 control-label">密码</label>  
			       <div class="col-sm-5">
			          <input type="password" class="form-control" name="userPwd" id="txtPassword" placeholder="请输入密码" required>
			       </div>
			       <div class="col-sm-2">
			           <span id="password.info"  ></span> 
			      </div>   
			   </div>
			
			   <div class="form-group">
			       <label for="repeatPass" class="col-sm-2 control-label">重复密码</label>  
			       <div class="col-sm-5">
			          <input type="password" class="form-control" name="repeatPass" id="txtRepeatPass" placeholder="请再次输入密码" required>
			       </div>
			       <div class="col-sm-2">
			           <span id="repeatPass.info"  ></span> 
			      </div>   
			   </div>
			
			   <div class="form-group">
			      <div class="col-sm-offset-2 col-sm-10">
			         <input type="button" value="注册" class="btn btn-primary" onclick="registerUser()">
			         <input type="button" value="登录" class="btn btn-success" onclick="toIndex()">
			      </div>
			   </div>
		</form>
    </body>
</html>