<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fileinput/js/fileinput.min.js"></script>
	<!--  引入fileinput的js -->
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/fileinput/js/locales/zh.js"></script>
	<!-- 引入fileinput的css -->
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/js/fileinput/css/fileinput.min.css" />
</head>
<body>
	<form id="add_user_form">
		<input type="hidden" id="fid" name="fid">
		<table>
			<tr>
				<td>账号：</td>
				<td><input name="userAccount" value="${user.userAccount }"></td>
			</tr>
			<tr>
				<td>密码：</td>
				<td><input name="userPwd" value="${user.userPwd }"></td>
			</tr>
			<tr>
				<td>头像</td>
				<td><input type="file" name="userPhoto"  id="userPhoto"></td>
			</tr>
		</table>
	</form>
	<script type="text/javascript">
		$(function (){
			$("#userPhoto").fileinput({
				//上传按钮
		    	showUpload:true,
		    	//移除按钮
			    showRemove : true,
			    //最大上传文件的个数，想要多选文件，input文件域必须加上multiple这个属性
			   // maxFileCount:2,
			    //主样式 
			    mainClass: "input-group-lg",
			    //按钮样式
			    browseClass: "btn btn-primary btn-block",
			    //语言设置：中文
			    language : 'zh',
			    //允许预览的文件类型
			    allowedPreviewTypes : [ 'image', 'text' ],
			    //允许选择的文件后缀名
			    allowedFileExtensions : [ 'jpg', 'png', 'gif' ],
			    //最大上传文件的大小（KB）
			    maxFileSize : 2000,
			    //是否显示路径文本框
			    showCaption:true,
			    //是否显示预览
			    showPreview:true,
			    //是否显示关闭预览框
			    showClose:false,
			    //是否显示文件选择按钮
			    showBrowse:true,
			  //文件上传的路径，设置可实现拖拽
				uploadUrl :'<%=request.getContextPath()%>/uploadUserPhoto.jhtml',
			    validateInitialCount:true,
			    previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
			    msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
			});
			
			//fileuploaded 上传文件之后的方法
			$('#userPhoto').on('fileuploaded', function(event, data, previewId, index) {
					// 打印到控制台
			 	console.log(data);
				/*//console.info(data.response.imgId);
				alert(data.response.fileFingerprint);
				alert(data.response.fileUrl); */
				//图片主键回显到隐藏域
				$("#fid").val(data.response.fileFingerprintID);
			});
			
		})

	</script>
</body>
</html>