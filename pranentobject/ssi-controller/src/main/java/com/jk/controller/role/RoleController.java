/** 
 * <pre>项目名称:ssi-controller 
 * 文件名称:RoleController.java 
 * 包名:com.jk.controller.role 
 * 创建日期:2017年7月27日下午9:09:00 
 * Copyright (c) 2017, ixuye@hotmail.com All Rights Reserved.</pre> 
 */  
package com.jk.controller.role;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jk.pojo.menu.MenuRequest;
import com.jk.pojo.menu.MenuResponse;
import com.jk.pojo.role.RoleRequest;
import com.jk.pojo.role.RoleResponse;
import com.jk.pojo.user.UserRequest;
import com.jk.pojo.user.UserResponse;
import com.jk.service.role.RoleService;

/** 
 * <pre>项目名称：ssi-controller    
 * 类名称：RoleController    
 * 类描述：    
 * 创建人：徐叶  
 * 创建时间：2017年7月27日 下午9:09:00    
 * 修改人：徐叶    
 * 修改时间：2017年7月27日 下午9:09:00    
 * 修改备注：       
 * @version </pre>    
 */
@Controller
@RequestMapping("role")
public class RoleController {
	@Autowired
	private RoleService roleService;
	
	@RequestMapping("selectRoleMenuListJson")
	@ResponseBody
	List<MenuResponse> selectRoleMenuListJson(MenuRequest menuRequest) {
		List<MenuResponse> menuList = roleService.selectRoleMenuListJson(menuRequest);
		return menuList;
	}
	
	@RequestMapping("insertRoleMenuList")
	@ResponseBody
	String insertRoleMenuList(@RequestBody List<MenuRequest> menuRequestList) {
		roleService.insertRoleMenuList(menuRequestList);
		return "{}";
	}
	
	
	/**
	 * <pre>insertRole(新增或修改单独的角色)   
	 * 创建人：徐叶     
	 * 创建时间：2017年7月28日 下午5:01:53    
	 * 修改人：徐叶       
	 * 修改时间：2017年7月28日 下午5:01:53    
	 * 修改备注： 
	 * @param roleRequest
	 * @return</pre>
	 */
	@RequestMapping("insertRole")
	@ResponseBody
	String insertRole(RoleRequest roleRequest) {
		roleService.insertRole(roleRequest);
		return "{}";
	}
	
	/**
	 * <pre>toRoleMenuPage(角色管理弹窗内容界面)   
	 * 创建人：徐叶     
	 * 创建时间：2017年7月27日 下午10:09:24    
	 * 修改人：徐叶       
	 * 修改时间：2017年7月27日 下午10:09:24    
	 * 修改备注： 
	 * @param m
	 * @param roleRequest
	 * @return</pre>
	 */
	@RequestMapping("toRoleMenuPage")
	String toRoleMenuPage(Model m, RoleRequest roleRequest) {
		m.addAttribute("roleID", roleRequest.getRoleID());
		return "pages/role/role_menu";
	}
	
	/**
	 * <pre>toUpdateRolePage(去修改角色界面)   
	 * 创建人：徐叶     
	 * 创建时间：2017年7月28日 下午4:39:45    
	 * 修改人：徐叶       
	 * 修改时间：2017年7月28日 下午4:39:45    
	 * 修改备注： 
	 * @param m
	 * @param roleRequest
	 * @return</pre>
	 */
	@RequestMapping("toUpdateRolePage")
	ModelAndView toUpdateRolePage(RoleRequest roleRequest) {
		ModelAndView mv = new ModelAndView();
		if(roleRequest.getRoleID() != null){
			RoleResponse roleInfo = roleService.queryRoleByRoleID(roleRequest);
			mv.addObject("role",roleInfo);
		}
		mv.setViewName("pages/role/role_add");
		return mv;
	}
	
	/**
	 * <pre>toRoleListPage(去角色管理的展示页面)   
	 * 创建人：徐叶     
	 * 创建时间：2017年7月27日 下午9:21:04    
	 * 修改人：徐叶       
	 * 修改时间：2017年7月27日 下午9:21:04    
	 * 修改备注： 
	 * @return</pre>
	 */
	@RequestMapping("toRoleListPage")
	public String toRoleListPage(){
		return "pages/role/roleList";
	}
	
	/**
	 * <pre>selectRoleListJson(加载角色数据)   
	 * 创建人：徐叶     
	 * 创建时间：2017年7月27日 下午9:37:03    
	 * 修改人：徐叶       
	 * 修改时间：2017年7月27日 下午9:37:03    
	 * 修改备注： 
	 * @param pageNumber
	 * @param roleRequest
	 * @return</pre>
	 */
	@RequestMapping("selectRoleListJson")
	@ResponseBody
	Map<String, Object> selectRoleListJson(String pageNumber, RoleRequest roleRequest) {
		//查询总条数
		int totalCount = roleService.selectRoleCount(roleRequest);
		roleRequest.setTotalCount(totalCount);
		if (null == pageNumber || "".equals(pageNumber.trim())) {
			pageNumber = "1";
		}
		roleRequest.setPageIndex(Integer.valueOf(pageNumber));
		//计算分页信息
		roleRequest.calculate();
		//查询列表
		List<RoleResponse> roleList = roleService.selectRoleList(roleRequest);
		//封装结果
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", totalCount);
		map.put("rows", roleList);
		return map;
	}
	
}
