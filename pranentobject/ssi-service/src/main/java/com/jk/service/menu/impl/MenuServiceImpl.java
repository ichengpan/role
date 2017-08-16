/** 
 * <pre>项目名称:ssi-service 
 * 文件名称:MenuServiceImpl.java 
 * 包名:com.jk.service.menu.impl 
 * 创建日期:2017年7月27日下午10:59:23 
 * Copyright (c) 2017, ixuye@hotmail.com All Rights Reserved.</pre> 
 */  
package com.jk.service.menu.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jk.dao.menu.MenuDao;
import com.jk.pojo.menu.MenuRequest;
import com.jk.pojo.menu.MenuResponse;
import com.jk.service.menu.MenuService;

/** 
 * <pre>项目名称：ssi-service    
 * 类名称：MenuServiceImpl    
 * 类描述：    
 * 创建人：徐叶  
 * 创建时间：2017年7月27日 下午10:59:23    
 * 修改人：徐叶    
 * 修改时间：2017年7月27日 下午10:59:23    
 * 修改备注：       
 * @version </pre>    
 */
@Service
public class MenuServiceImpl implements MenuService {
	
	@Autowired
	private MenuDao menuDao;
	
	/* (non-Javadoc)    
	 * @see com.jk.service.menu.MenuService#deleteMenu(com.jk.pojo.menu.MenuRequest)    
	 */
	@Override
	public void deleteMenu(MenuRequest menuRequest) {
		menuDao.deleteMenu(menuRequest);
	}
	
	/* (non-Javadoc)    
	 * @see com.jk.service.menu.MenuService#insertMenu(com.jk.pojo.menu.MenuRequest)    
	 */
	@Override
	public void insertMenu(MenuRequest menuRequest) {
		//判断是否是模块
		if (0 == menuRequest.getPid()) {
			menuRequest.setParent(true);
		}
		menuDao.insertMenu(menuRequest);
	}
	
	/* (non-Javadoc)    
	 * @see com.jk.service.menu.MenuService#selectFirstMenuList(com.jk.pojo.menu.MenuRequest)    
	 */
	@Override
	public List<MenuResponse> selectFirstMenuList(MenuRequest menuRequest) {
		return menuDao.selectFirstMenuList(menuRequest);
	}
	
	/* (non-Javadoc)    
	 * @see com.jk.service.menu.MenuService#selectMenuListJson(com.jk.pojo.menu.MenuRequest)    
	 */
	@Override
	public List<MenuResponse> selectMenuListJson(MenuRequest menuRequest) {
		return menuDao.selectMenuListJson(menuRequest);
	}
}
