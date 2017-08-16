<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/js/ztree/css/zTreeStyle/zTreeStyle.css">
<script type="text/javascript" src="<%=request.getContextPath() %>/js/ztree/js/jquery.ztree.all.js"></script>
</head>
<body>
	<div id="treeDemo" class="ztree"></div>
	<script type="text/javascript">
		//定义一个ztree对象
		var zTreeObj;
		// zTree 的参数配置
		var setting = {
				check: {
					//chkStyle: "radio",
					enable: true
				},
				data: {
					simpleData: {
						enable: true,
						idKey: "id",
						pIdKey: "pid",
						rootPId: 0
					}
				}
	   };
	   
	   $(function() {
		   loadRole();
		 //   zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
	   })
	   
	   function loadRole(){
		 //ajax请求从后台获取树
		   $.ajax({
			   type:"post",
			   url:"<%=request.getContextPath() %>/loadRole.jhtml",
			   data:{userID:'${userID}'},
			   dataType:"json",
			   success:function(data) {
				   zNodes = data;
				   zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
			   }
		   });
	   }
	   
	   //获取被选中的树节点
	   function get_selection_tree_nodes() {
		   var role_json_array = [];
		   var nodes = zTreeObj.getCheckedNodes(true);
		   for (var i = 0; i < nodes.length; i++) {
			   var role_obj = {userID:"${userID}", roleID:nodes[i].id};
			   role_json_array.push(role_obj);
			   //alert(nodes[i].name + ">>" + nodes[i].id);
		   }
		   return role_json_array;
	   }
	</script>
</body>
</html>