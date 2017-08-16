/** 
 * <pre>项目名称:ssi-dao 
 * 文件名称:MenuDaoImpl.java 
 * 包名:com.jk.dao.menu.impl 
 * 创建日期:2017年7月27日下午11:01:12 
 * Copyright (c) 2017, ixuye@hotmail.com All Rights Reserved.</pre> 
 */  
package com.jk.dao.menu.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.jk.dao.menu.MenuDao;
import com.jk.pojo.menu.MenuRequest;
import com.jk.pojo.menu.MenuResponse;

/** 
 * <pre>项目名称：ssi-dao    
 * 类名称：MenuDaoImpl    
 * 类描述：    
 * 创建人：徐叶  
 * 创建时间：2017年7月27日 下午11:01:12    
 * 修改人：徐叶    
 * 修改时间：2017年7月27日 下午11:01:12    
 * 修改备注：       
 * @version </pre>    
 */
@Repository
public class MenuDaoImpl extends SqlMapClientDaoSupport implements MenuDao {
	
	/* (non-Javadoc)    
	 * @see com.jk.dao.menu.MenuDao#deleteMenu(com.jk.pojo.menu.MenuRequest)    
	 */
	@Override
	public void deleteMenu(MenuRequest menuRequest) {
		this.getSqlMapClientTemplate().delete("menu.deleteMenu", menuRequest);
	}
	
	/* (non-Javadoc)    
	 * @see com.jk.dao.menu.MenuDao#insertMenu(com.jk.pojo.menu.MenuRequest)    
	 */
	@Override
	public void insertMenu(MenuRequest menuRequest) {
		this.getSqlMapClientTemplate().insert("menu.insertMenu", menuRequest);
	}
	
	/* (non-Javadoc)    
	 * @see com.jk.dao.menu.MenuDao#selectFirstMenuList(com.jk.pojo.menu.MenuRequest)    
	 */
	@Override
	public List<MenuResponse> selectFirstMenuList(MenuRequest menuRequest) {
		return this.getSqlMapClientTemplate().queryForList("menu.selectFirstMenuList", menuRequest);
	}
	
	/* (non-Javadoc)    
	 * @see com.jk.dao.menu.MenuDao#selectMenuListJson(com.jk.pojo.menu.MenuRequest)    
	 */
	@Override
	public List<MenuResponse> selectMenuListJson(MenuRequest menuRequest) {
		return this.getSqlMapClientTemplate().queryForList("menu.selectMenuListJson", menuRequest);
	}
}
