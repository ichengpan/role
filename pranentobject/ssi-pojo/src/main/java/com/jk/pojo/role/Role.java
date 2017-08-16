/** 
 * <pre>项目名称:ssi-pojo-01 
 * 文件名称:Role.java 
 * 包名:com.jk.pojo.role 
 * 创建日期:2017年7月25日下午12:15:25 
 * Copyright (c) 2017, chenjh123@gmail.com All Rights Reserved.</pre> 
 */  
package com.jk.pojo.role;

import java.io.Serializable;

import common.util.PageUtil;

/** 
 * <pre>项目名称：ssi-pojo-01    
 * 类名称：Role    
 * 类描述：    
 * 创建人：陈教授 chenjh123@gmail.com    
 * 创建时间：2017年7月25日 下午12:15:25    
 * 修改人：陈教授 chenjh123@gmail.com     
 * 修改时间：2017年7月25日 下午12:15:25    
 * 修改备注：       
 * @version </pre>    
 */
public class Role extends PageUtil implements Serializable {

	private Integer roleID;
	
	private String roleName;
	
	private String roleDesc;

	public Integer getRoleID() {
		return roleID;
	}

	public void setRoleID(Integer roleID) {
		this.roleID = roleID;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	@Override
	public String toString() {
		return "Role [roleID=" + roleID + ", roleName=" + roleName + ", roleDesc=" + roleDesc + "]";
	}

	

	
	
}
