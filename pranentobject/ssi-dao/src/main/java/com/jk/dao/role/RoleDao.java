/** 
 * <pre>项目名称:ssi-dao 
 * 文件名称:RoleDao.java 
 * 包名:com.jk.dao.role 
 * 创建日期:2017年7月27日下午9:12:16 
 * Copyright (c) 2017, ixuye@hotmail.com All Rights Reserved.</pre> 
 */  
package com.jk.dao.role;

import java.util.List;

import com.jk.pojo.menu.MenuRequest;
import com.jk.pojo.menu.MenuResponse;
import com.jk.pojo.role.RoleRequest;
import com.jk.pojo.role.RoleResponse;

/** 
 * <pre>项目名称：ssi-dao    
 * 类名称：RoleDao    
 * 类描述：    
 * 创建人：徐叶  
 * 创建时间：2017年7月27日 下午9:12:16    
 * 修改人：徐叶    
 * 修改时间：2017年7月27日 下午9:12:16    
 * 修改备注：       
 * @version </pre>    
 */
public interface RoleDao {

	/** <pre>selectRoleCount(这里用一句话描述这个方法的作用)   
	 * 创建人：徐叶     
	 * 创建时间：2017年7月27日 下午9:39:50    
	 * 修改人：徐叶       
	 * 修改时间：2017年7月27日 下午9:39:50    
	 * 修改备注： 
	 * @param roleRequest
	 * @return</pre>    
	 */
	int selectRoleCount(RoleRequest roleRequest);

	/** <pre>selectRoleList(这里用一句话描述这个方法的作用)   
	 * 创建人：徐叶     
	 * 创建时间：2017年7月27日 下午9:40:02    
	 * 修改人：徐叶       
	 * 修改时间：2017年7月27日 下午9:40:02    
	 * 修改备注： 
	 * @param roleRequest
	 * @return</pre>    
	 */
	List<RoleResponse> selectRoleList(RoleRequest roleRequest);

	/** <pre>selectRoleMenuListJson(这里用一句话描述这个方法的作用)   
	 * 创建人：徐叶     
	 * 创建时间：2017年7月27日 下午10:12:07    
	 * 修改人：徐叶       
	 * 修改时间：2017年7月27日 下午10:12:07    
	 * 修改备注： 
	 * @param menuRequest
	 * @return</pre>    
	 */
	List<MenuResponse> selectRoleMenuListJson(MenuRequest menuRequest);

	/** <pre>deleteAllRoleMenuByRoleID(这里用一句话描述这个方法的作用)   
	 * 创建人：徐叶     
	 * 创建时间：2017年7月27日 下午10:51:47    
	 * 修改人：徐叶       
	 * 修改时间：2017年7月27日 下午10:51:47    
	 * 修改备注： 
	 * @param menuRequest</pre>    
	 */
	void deleteAllRoleMenuByRoleID(MenuRequest menuRequest);

	/** <pre>insertRoleMenuList(这里用一句话描述这个方法的作用)   
	 * 创建人：徐叶     
	 * 创建时间：2017年7月27日 下午10:51:52    
	 * 修改人：徐叶       
	 * 修改时间：2017年7月27日 下午10:51:52    
	 * 修改备注： 
	 * @param menuRequestList</pre>    
	 */
	void insertRoleMenuList(List<MenuRequest> menuRequestList);

	/** <pre>queryRoleByRoleID(这里用一句话描述这个方法的作用)   
	 * 创建人：徐叶     
	 * 创建时间：2017年7月28日 下午4:46:05    
	 * 修改人：徐叶       
	 * 修改时间：2017年7月28日 下午4:46:05    
	 * 修改备注： 
	 * @param roleRequest
	 * @return</pre>    
	 */
	RoleResponse queryRoleByRoleID(RoleRequest roleRequest);

	/** <pre>updateRole(这里用一句话描述这个方法的作用)   
	 * 创建人：徐叶     
	 * 创建时间：2017年7月28日 下午5:06:11    
	 * 修改人：徐叶       
	 * 修改时间：2017年7月28日 下午5:06:11    
	 * 修改备注： 
	 * @param roleRequest</pre>    
	 */
	void updateRole(RoleRequest roleRequest);

	/** <pre>insertRole(这里用一句话描述这个方法的作用)   
	 * 创建人：徐叶     
	 * 创建时间：2017年7月28日 下午5:06:17    
	 * 修改人：徐叶       
	 * 修改时间：2017年7月28日 下午5:06:17    
	 * 修改备注： 
	 * @param roleRequest</pre>    
	 */
	void insertRole(RoleRequest roleRequest);

}
