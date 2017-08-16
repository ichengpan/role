<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>




</head>
<body>
	<!-- 搜索面板 -->
    <div class="tab-content" style="padding: 4px">
	    <form id="search_user_form">
	    	<label>用户账号11：</label>
			<input id="query_user_account" class="form-control-sm" placeholder="请输入用户账号">
			<div>
				<!-- Single button -->
				<div class="btn-group">
				  <button type="button" class="btn btn-success glyphicon glyphicon-search" onclick="search_user()">搜索</button>
				</div>
				<div class="btn-group">
				  <button type="button" class="btn btn-danger glyphicon glyphicon-repeat" onclick="reset_search_user_form()">重置</button>
				</div>
			</div>
		</form>
    </div>

	<!-- datagrid -->
	<table id="user_dg"></table>
	<!-- toolbar -->
	<div id="user_tb">
		<!-- Single button -->
		<div class="btn-group">
		  <button type="button" class="btn btn-success" onclick="show_add_dialog()">新增showList</button>
		</div>
		<div class="btn-group">
		  <button type="button" class="btn btn-danger" onclick="delete_all_checked_book()">删除</button>
		</div>
	</div>

	<script type="text/javascript">
		
		$(function() {
			loadData();
		})
		
		function loadData(){
			//初始化数据表格
			$('#user_dg').bootstrapTable({
				url:"<%=request.getContextPath() %>/selectUserList.jhtml",
				dataType:"json",
				//请求方式
				method:"post",
				//必须的，操你大爷！！！！不然会造成中文乱码
				contentType: "application/x-www-form-urlencoded",
				//斑马线
				striped:true,
				//设置分页
				pagination:true,
				paginationLoop:true,
				pageNumber:1,
				pageSize:3,
				pageList:[3,5,8,10],
				//工具条
				toolbar:"#user_tb",
				//设置后台分页
				sidePagination:"server",
				//开启搜索框
				/* search:true, */
				//显示刷新按钮
				showRefresh:true,
				//拼接查询参数
				queryParams:function(params) {
					/* params.userAccount = $("#query_user_account").val();
					console.log(params); */
					return params;
				},
				queryParamsType:"",
			    columns: [{
			        checkbox:true
			    }, {
			        field: 'userID',
			        title: '主键'
			    }, {
			        field: 'userAccount',
			        title: '账号'
			    }, {
			        field: 'userPwd',
			        title: '密码'
			    }, {
			        field: 'photoURL',
			        title: '头像',
			        width:'80px',
			        formatter:function(value, row, index) {
			        	if(row.photoURL != null){
			        		var img = '<img alt="没有图片" style="width:80px" src="http://192.168.1.111:8088/'+row.photoURL+'">';
			        	}else{
			        		var img = '<img alt="没有图片">';
			        	}
			        	return img;
			        }
			    }, {
			        field: 'cz',
			        title: '操作',
			        formatter:function(value, row, index) {
			        	var zc_btn_group = '<div class="btn-group">'
			        	+ '<button type="button" class="btn btn-xs btn-success" onclick="show_update_dialog(\'' + row.userID + '\')">编辑</button>'
			        	+ '</div>&nbsp;&nbsp;'
			        	+ '<div class="btn-group">'
			        	+ '<button type="button" class="btn btn-xs btn-danger" onclick="delete_checked_user(\'' + row.userID + '\')">删除</button>'
			        	+ '</div>&nbsp;&nbsp;'
			        	+ '<div class="btn-group">'
			        	+ '<button type="button" class="btn btn-xs btn-success" onclick="edit_user_role(\'' + row.userID + '\')">角色操作</button>'
			        	+ '</div>';
			        	return zc_btn_group;
			        }
			    }]
			});
		}
		
		/* 去新增页面 */
		function show_add_dialog(){
			BootstrapDialog.show({
				title:"新增用户",
				message:$('<div></div>').load('<%=request.getContextPath() %>/toAddUserPage.jhtml'),
				buttons: [{
	                label: '确定',
	                cssClass:"btn btn-success",
	                action: function(dialogItself){
	                	//使用ajax保存结果
	                	$.ajax({
	                		url:"<%=request.getContextPath() %>/updateUser.jhtml",
	                		data:$("#add_user_form").serialize(),
	                		dataType:"json",
	                		type:"post",
	                		success:function(data) {
	                			//刷新表格
	                			$('#user_dg').bootstrapTable("refresh");
	                			//关闭对话框
	                			dialogItself.close();
	                		},
	                	});
	                }
	            }, {
	                label: '取消',
	                cssClass:"btn btn-danger",
	                action: function(dialogItself){
	                	dialogItself.close();
	                }
	            }]
			});
		}
		
		function show_update_dialog(id){
			BootstrapDialog.show({
				title:"编辑用户",
				message:$('<div></div>').load('<%=request.getContextPath() %>/toAddUserPage.jhtml?userID=' + id),
				buttons: [{
	                label: '确定',
	                cssClass:"btn btn-success",
	                action: function(dialogItself){
	                	//使用ajax保存结果
	                	$.ajax({
	                		url:"<%=request.getContextPath() %>/updateUser.jhtml",
	                		data:$("#add_user_form").serialize(),
	                		dataType:"json",
	                		type:"post",
	                		success:function(data) {
	                			//刷新表格
	                			$('#user_dg').bootstrapTable("refresh");
	                			//关闭对话框
	                			dialogItself.close();
	                		},
	                	});
	                }
	            }, {
	                label: '取消',
	                cssClass:"btn btn-danger",
	                action: function(dialogItself){
	                	dialogItself.close();
	                }
	            }]
			});
		}
		
		function edit_user_role(id){
			BootstrapDialog.show({
				title:"用户管理>>用户赋角色",
				message: $('<div></div>').load('<%=request.getContextPath() %>/toUserRolePage.jhtml?userID=' + id),
				buttons: [{
	                label: '确定',
	                cssClass:"btn btn-success",
	                action: function(dialogItself){
	                	var role_json_array = get_selection_tree_nodes();
	                	//使用ajax保存结果
	                	$.ajax({
	                		url:"<%=request.getContextPath() %>/insertUserRoleList.jhtml",
	                		data:JSON.stringify(role_json_array),
	                		dataType:"json",
	                		type:"post",
	                		success:function(data) {
	                			//关闭对话框
	                			dialogItself.close();
	                		},
	                		contentType:"application/json"
	                	});
	                }
	            }, {
	                label: '取消',
	                cssClass:"btn btn-danger",
	                action: function(dialogItself){
	                	dialogItself.close();
	                }
	            }]
			});
		}
		
		function delete_checked_user(id){
			$.ajax({
			   type:"post",
			   url:"<%=request.getContextPath() %>/deleteMuchUser.jhtml",
			   data:{ids:id},
			   dataType:"json",
			   success:function(data) {
				   $('#user_dg').bootstrapTable("refresh");
			   }
			})
		}
	</script>
</body>
</html>