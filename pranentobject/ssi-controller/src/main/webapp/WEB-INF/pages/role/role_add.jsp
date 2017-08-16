<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form id="add_roles_form">
		<input type="hidden" name="roleID" value="${role.roleID }">
		<table>
			<tr>
				<td>角色名称：</td>
				<td><input name="roleName" value="${role.roleName }"></td>
			</tr>
			<tr>
				<td>角色描述：</td>
				<td><input name="roleDesc" value="${role.roleDesc }"></td>
			</tr>
		</table>
	</form>
</body>
</html>