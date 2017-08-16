/** 
 * <pre>项目名称:ssi-controller 
 * 文件名称:MenuController.java 
 * 包名:com.jk.controller.menu 
 * 创建日期:2017年7月27日下午10:57:55 
 * Copyright (c) 2017, ixuye@hotmail.com All Rights Reserved.</pre> 
 */  
package com.jk.controller.menu;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jk.pojo.menu.MenuRequest;
import com.jk.pojo.menu.MenuResponse;
import com.jk.service.menu.MenuService;

/** 
 * <pre>项目名称：ssi-controller    
 * 类名称：MenuController    
 * 类描述：    
 * 创建人：徐叶  
 * 创建时间：2017年7月27日 下午10:57:55    
 * 修改人：徐叶    
 * 修改时间：2017年7月27日 下午10:57:55    
 * 修改备注：       
 * @version </pre>    
 */
@Controller
@RequestMapping("menu")
public class MenuController {
	
	@Autowired
	private MenuService menuService;
	
	@RequestMapping("insertMenu")
	@ResponseBody
	String insertMenu(MenuRequest menuRequest) {
		menuService.insertMenu(menuRequest);
		return "{}";
	}
	
	@RequestMapping("toAddMenuPage")
	String toAddMenuPage(Model m) {
		//查询一级菜单列表，展示到添加页面
		List<MenuResponse> menuList = menuService.selectFirstMenuList(new MenuRequest());
		m.addAttribute("menuList", menuList);
		return "pages/menu/add_menu";
	}
	
	/**
	 * <pre>deleteMenu(这里用一句话描述这个方法的作用)   
	 * 创建人：徐叶     
	 * 创建时间：2017年7月28日 上午7:52:12    
	 * 修改人：徐叶       
	 * 修改时间：2017年7月28日 上午7:52:12    
	 * 修改备注： 
	 * @param menuRequest
	 * @return</pre>
	 */
	@RequestMapping("deleteMenu")
	@ResponseBody
	String deleteMenu(MenuRequest menuRequest) {
		menuService.deleteMenu(menuRequest);
		return "{}";
	}
	
	/**
	 * <pre>toMenuPage(去权限管理权限)   
	 * 创建人：徐叶     
	 * 创建时间：2017年7月27日 下午11:11:54    
	 * 修改人：徐叶       
	 * 修改时间：2017年7月27日 下午11:11:54    
	 * 修改备注： 
	 * @return</pre>
	 */
	@RequestMapping("toMenuPage")
	String toMenuPage() {
		return "pages/menu/menu_list";
	}
	
	/**
	 * <pre>selectMenuListJson(查询菜单表)   
	 * 创建人：徐叶     
	 * 创建时间：2017年7月27日 下午11:12:46    
	 * 修改人：徐叶       
	 * 修改时间：2017年7月27日 下午11:12:46    
	 * 修改备注： 
	 * @param menuRequest
	 * @return</pre>
	 */
	@RequestMapping("selectMenuListJson")
	@ResponseBody
	List<MenuResponse> selectMenuListJson(MenuRequest menuRequest) {
		List<MenuResponse> menuList = menuService.selectMenuListJson(menuRequest);
		return menuList;
	}
}
