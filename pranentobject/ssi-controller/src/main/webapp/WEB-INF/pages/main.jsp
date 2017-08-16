<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>这是主显示界面</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/js/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/js/bootStrap-addTabs/bootstrap.addtabs.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/js/bootstrap-table/dist/bootstrap-table.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/js/bootstrap-dialog/dist/css/bootstrap-dialog.min.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/js/bootstrap-treeview/dist/bootstrap-treeview.min.css">
<!-- treegrid -->
<link rel="stylesheet" href="<%=request.getContextPath() %>/js/jquery-treegrid/css/jquery.treegrid.css">


<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/bootstrap-treeview/dist/bootstrap-treeview.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/bootstrap-table/dist/bootstrap-table.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/bootStrap-addTabs/bootstrap.addtabs.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/bootstrap-dialog/dist/js/bootstrap-dialog.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/bootstrap-table/dist/locale/bootstrap-table-zh-CN.min.js"></script>

<!-- treegrid -->
<script type="text/javascript" src="js/jquery-treegrid/js/jquery.treegrid.min.js"></script>
<script type="text/javascript" src="js/jquery-treegrid/js/jquery.treegrid.bootstrap3.js"></script>
<script type="text/javascript" src="js/jquery-treegrid/extension/jquery.treegrid.extension.js"></script>

</head>
<body>
	<script src="js/canvas-nest.js" count="200" zindex="-2" opacity="0.5" color="0,139,139" type="text/javascript"></script>
	
	<!-- 导航条 -->
	<nav class="navbar navbar-default">
		<h3 id="nav_h3" align="center">金科教育企业平台${userInfo.userID}</h3>
		<input type="button" align="right" class="btn btn-danger" value="退出" onclick="logout()">
	</nav>
	
	<!-- 流式布局 -->
	<div class="container-fluid">
		
		<div class="row">
			<!-- 添加列 -->
			<div class="col-xs-3 lie">
				<!-- 添加列表组（树菜单） -->
				<div id="tree" class="ztree"></div>
			</div>
			<div class="col-xs-9 lie">
				<div id="content-div">
					<!-- 选项卡 -->
					<div id="tabs">
                        <!-- Nav tabs -->
                        <ul class="nav nav-tabs" role="tablist">
                            <li role="presentation" class="active">
                                <a href="#home" aria-controls="home" role="tab" data-toggle="tab">主页</a>
                            </li>
                        </ul>
                        <!-- Tab panes（放置结果页面） -->
                        <div class="tab-content">
                        </div>
                    </div>
				</div>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
		$(function(){
			setTabsHeight();
			loadTree();
		})
		
		function logout(){
			location.href="<%=request.getContextPath() %>/logout.jhtml"
		}
		
		
		function setTabsHeight(){
			$.addtabs({iframeHeight:450});
		}
		
		function loadTree(){
			 //初始化树
	    	$('#tree').treeview({
	    				data:getTreeData(),
	    				onNodeSelected:function(event, node) {
	    				if (null != node.href && "" != node.href) {
		    				//发送ajax请求
		            		$.ajax({
		            			url:"/ssi-controller/"+node.href,
		            			success:function(data) {
		            				$.addtabs.add({id:node.text,title:node.text,content:data});
		            			}
		            		});
	    			}
	    		}
	    	});
		}
			
			//获取菜单数据
	    	function getTreeData() {
	    		var tree_data = [];
	    		//发送ajax请求
	    		$.ajax({
	    			async:false,//请求为同步
	    			url:"<%=request.getContextPath() %>/loadTree.jhtml",
	    			data:{userID:"${userInfo.userID }"},
	    			dataType:"json",
	    			success:function(data) {
	    				tree_data = data;
	    			}
	    		});
	    		return tree_data;
	    	}
	</script>
</body>
</html>