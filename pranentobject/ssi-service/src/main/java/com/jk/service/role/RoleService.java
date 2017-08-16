/** 
 * <pre>项目名称:ssi-service 
 * 文件名称:RoleService.java 
 * 包名:com.jk.service.role 
 * 创建日期:2017年7月27日下午9:10:52 
 * Copyright (c) 2017, ixuye@hotmail.com All Rights Reserved.</pre> 
 */  
package com.jk.service.role;

import java.util.List;

import com.jk.pojo.menu.MenuRequest;
import com.jk.pojo.menu.MenuResponse;
import com.jk.pojo.role.RoleRequest;
import com.jk.pojo.role.RoleResponse;

/** 
 * <pre>项目名称：ssi-service    
 * 类名称：RoleService    
 * 类描述：    
 * 创建人：徐叶  
 * 创建时间：2017年7月27日 下午9:10:52    
 * 修改人：徐叶    
 * 修改时间：2017年7月27日 下午9:10:52    
 * 修改备注：       
 * @version </pre>    
 */
public interface RoleService {

	/** <pre>selectRoleCount(这里用一句话描述这个方法的作用)   
	 * 创建人：徐叶     
	 * 创建时间：2017年7月27日 下午9:37:20    
	 * 修改人：徐叶       
	 * 修改时间：2017年7月27日 下午9:37:20    
	 * 修改备注： 
	 * @param roleRequest
	 * @return</pre>    
	 */
	int selectRoleCount(RoleRequest roleRequest);

	/** <pre>selectRoleList(这里用一句话描述这个方法的作用)   
	 * 创建人：徐叶     
	 * 创建时间：2017年7月27日 下午9:38:18    
	 * 修改人：徐叶       
	 * 修改时间：2017年7月27日 下午9:38:18    
	 * 修改备注： 
	 * @param roleRequest
	 * @return</pre>    
	 */
	List<RoleResponse> selectRoleList(RoleRequest roleRequest);

	/** <pre>selectRoleMenuListJson(这里用一句话描述这个方法的作用)   
	 * 创建人：徐叶     
	 * 创建时间：2017年7月27日 下午10:10:32    
	 * 修改人：徐叶       
	 * 修改时间：2017年7月27日 下午10:10:32    
	 * 修改备注： 
	 * @param menuRequest
	 * @return</pre>    
	 */
	List<MenuResponse> selectRoleMenuListJson(MenuRequest menuRequest);

	/** <pre>insertRoleMenuList(这里用一句话描述这个方法的作用)   
	 * 创建人：徐叶     
	 * 创建时间：2017年7月27日 下午10:50:41    
	 * 修改人：徐叶       
	 * 修改时间：2017年7月27日 下午10:50:41    
	 * 修改备注： 
	 * @param menuRequestList</pre>    
	 */
	void insertRoleMenuList(List<MenuRequest> menuRequestList);

	/** <pre>queryRoleByRoleID(这里用一句话描述这个方法的作用)   
	 * 创建人：徐叶     
	 * 创建时间：2017年7月28日 下午4:45:28    
	 * 修改人：徐叶       
	 * 修改时间：2017年7月28日 下午4:45:28    
	 * 修改备注： 
	 * @param roleRequest
	 * @return</pre>    
	 */
	RoleResponse queryRoleByRoleID(RoleRequest roleRequest);

	/** <pre>insertRole(这里用一句话描述这个方法的作用)   
	 * 创建人：徐叶     
	 * 创建时间：2017年7月28日 下午5:02:22    
	 * 修改人：徐叶       
	 * 修改时间：2017年7月28日 下午5:02:22    
	 * 修改备注： 
	 * @param roleRequest</pre>    
	 */
	void insertRole(RoleRequest roleRequest);

}
