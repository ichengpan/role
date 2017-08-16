/** 
 * <pre>项目名称:ssi-service 
 * 文件名称:RoleServiceImpl.java 
 * 包名:com.jk.service.role.impl 
 * 创建日期:2017年7月27日下午9:11:31 
 * Copyright (c) 2017, ixuye@hotmail.com All Rights Reserved.</pre> 
 */  
package com.jk.service.role.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jk.dao.role.RoleDao;
import com.jk.pojo.menu.MenuRequest;
import com.jk.pojo.menu.MenuResponse;
import com.jk.pojo.role.RoleRequest;
import com.jk.pojo.role.RoleResponse;
import com.jk.service.role.RoleService;

/** 
 * <pre>项目名称：ssi-service    
 * 类名称：RoleServiceImpl    
 * 类描述：    
 * 创建人：徐叶  
 * 创建时间：2017年7月27日 下午9:11:31    
 * 修改人：徐叶    
 * 修改时间：2017年7月27日 下午9:11:31    
 * 修改备注：       
 * @version </pre>    
 */
@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleDao roleDao;
	
	/* (non-Javadoc)    
	 * @see com.jk.service.role.RoleService#insertRole(com.jk.pojo.role.RoleRequest)    
	 */
	@Override
	public void insertRole(RoleRequest roleRequest) {
		if(roleRequest.getRoleID() != null){
			roleDao.updateRole(roleRequest);
		}else{
			roleDao.insertRole(roleRequest);
		}
	}
	
	/* (non-Javadoc)    
	 * @see com.jk.service.role.RoleService#queryRoleByRoleID(com.jk.pojo.role.RoleRequest)    
	 */
	@Override
	public RoleResponse queryRoleByRoleID(RoleRequest roleRequest) {
		return roleDao.queryRoleByRoleID(roleRequest);
	}
	
	/* (non-Javadoc)    
	 * @see com.jk.service.role.RoleService#insertRoleMenuList(java.util.List)    
	 */
	@Override
	public void insertRoleMenuList(List<MenuRequest> menuRequestList) {
		roleDao.deleteAllRoleMenuByRoleID(menuRequestList.get(0));
		roleDao.insertRoleMenuList(menuRequestList);
	}
	
	/* (non-Javadoc)    
	 * @see com.jk.service.role.RoleService#selectRoleMenuListJson(com.jk.pojo.menu.MenuRequest)    
	 */
	@Override
	public List<MenuResponse> selectRoleMenuListJson(MenuRequest menuRequest) {
		return roleDao.selectRoleMenuListJson(menuRequest);
	}
	
	/* (non-Javadoc)    
	 * @see com.jk.service.role.RoleService#selectRoleCount(com.jk.pojo.role.RoleRequest)    
	 */
	@Override
	public int selectRoleCount(RoleRequest roleRequest) {
		return roleDao.selectRoleCount(roleRequest);
	}
	
	/* (non-Javadoc)    
	 * @see com.jk.service.role.RoleService#selectRoleList(com.jk.pojo.role.RoleRequest)    
	 */
	@Override
	public List<RoleResponse> selectRoleList(RoleRequest roleRequest) {
		return roleDao.selectRoleList(roleRequest);
	}
}
