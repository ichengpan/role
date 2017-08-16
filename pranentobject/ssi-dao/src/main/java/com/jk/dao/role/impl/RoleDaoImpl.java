/** 
 * <pre>项目名称:ssi-dao 
 * 文件名称:RoleDaoImpl.java 
 * 包名:com.jk.dao.role.impl 
 * 创建日期:2017年7月27日下午9:13:03 
 * Copyright (c) 2017, ixuye@hotmail.com All Rights Reserved.</pre> 
 */  
package com.jk.dao.role.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.jk.dao.role.RoleDao;
import com.jk.pojo.menu.MenuRequest;
import com.jk.pojo.menu.MenuResponse;
import com.jk.pojo.role.RoleRequest;
import com.jk.pojo.role.RoleResponse;

/** 
 * <pre>项目名称：ssi-dao    
 * 类名称：RoleDaoImpl    
 * 类描述：    
 * 创建人：徐叶  
 * 创建时间：2017年7月27日 下午9:13:03    
 * 修改人：徐叶    
 * 修改时间：2017年7月27日 下午9:13:03    
 * 修改备注：       
 * @version </pre>    
 */
@Repository
public class RoleDaoImpl extends SqlMapClientDaoSupport implements RoleDao {
	
	/**
	 * 修改角色
	 */
	@Override
	public void updateRole(RoleRequest roleRequest) {
		this.getSqlMapClientTemplate().update("role.updateRole", roleRequest);
	}
	
	/**
	 * 新增角色
	 */
	@Override
	public void insertRole(RoleRequest roleRequest) {
		this.getSqlMapClientTemplate().insert("role.insertRole", roleRequest);
	}
	
	/* (non-Javadoc)    
	 * @see com.jk.dao.role.RoleDao#queryRoleByRoleID(com.jk.pojo.role.RoleRequest)    
	 */
	@Override
	public RoleResponse queryRoleByRoleID(RoleRequest roleRequest) {
		return (RoleResponse) this.getSqlMapClientTemplate().queryForObject("role.queryRoleByRoleID", roleRequest);
	}
	
	/* (non-Javadoc)    
	 * @see com.jk.dao.role.RoleDao#deleteAllRoleMenuByRoleID(com.jk.pojo.menu.MenuRequest)    
	 */
	@Override
	public void deleteAllRoleMenuByRoleID(MenuRequest menuRequest) {
		this.getSqlMapClientTemplate().delete("role.deleteAllRoleMenuByRoleID", menuRequest);
	}
	
	/* (non-Javadoc)    
	 * @see com.jk.dao.role.RoleDao#insertRoleMenuList(java.util.List)    
	 */
	@Override
	public void insertRoleMenuList(final List<MenuRequest> menuRequestList) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback<Object>() {
			/* (non-Javadoc)    
			 * @see org.springframework.orm.ibatis.SqlMapClientCallback#doInSqlMapClient(com.ibatis.sqlmap.client.SqlMapExecutor)    
			 */
			@Override
			public Object doInSqlMapClient(SqlMapExecutor sqlMap) throws SQLException {
				sqlMap.startBatch();
				for (MenuRequest menuRequest : menuRequestList) {
					sqlMap.insert("role.insertRoleMenu", menuRequest);
				}
				sqlMap.executeBatch();
				return null;
			}
		});
	}
	
	/* (non-Javadoc)    
	 * @see com.jk.dao.role.RoleDao#selectRoleMenuListJson(com.jk.pojo.menu.MenuRequest)    
	 */
	@Override
	public List<MenuResponse> selectRoleMenuListJson(MenuRequest menuRequest) {
		return this.getSqlMapClientTemplate().queryForList("role.selectRoleMenuListJson", menuRequest);
	}
	
	/* (non-Javadoc)    
	 * @see com.jk.dao.role.RoleDao#selectRoleCount(com.jk.pojo.role.RoleRequest)    
	 */
	@Override
	public int selectRoleCount(RoleRequest roleRequest) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject("role.selectRoleCount", roleRequest);
	}
	
	/* (non-Javadoc)    
	 * @see com.jk.dao.role.RoleDao#selectRoleList(com.jk.pojo.role.RoleRequest)    
	 */
	@Override
	public List<RoleResponse> selectRoleList(RoleRequest roleRequest) {
		return this.getSqlMapClientTemplate().queryForList("role.selectRoleList", roleRequest);
	}
}
