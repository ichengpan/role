package com.jk.service.user;

import java.util.List;
import java.util.Map;

import com.jk.pojo.fingerprint.FingerprintRequest;
import com.jk.pojo.menu.MenuRequest;
import com.jk.pojo.role.RoleRequest;
import com.jk.pojo.role.RoleResponse;
import com.jk.pojo.tree.Tree;
import com.jk.pojo.user.User;
import com.jk.pojo.user.UserRequest;
import com.jk.pojo.user.UserResponse;

public interface UserService {

	Map<String, Object> login(UserRequest userRequest);

	List<User> loadTree();

	List<Tree> findTree(int i);

	int querySonCount(Integer id);

	List<Tree> selectUserList(UserRequest userRequest);

	void deleteMuchUser(UserRequest user);

	int selectUserCount(UserRequest userRequest);

	List<RoleResponse> selectUserRoleListJson(RoleRequest roleRequest);

	void insertUserRoleList(List<RoleRequest> roleRequestList);

	UserResponse queryUserByUserID(UserRequest userRequest);

	void updateUser(UserRequest userRequest, FingerprintRequest fingerprint);

	/** <pre>selectTreeListJson(这里用一句话描述这个方法的作用)   
	 * 创建人：徐叶     
	 * 创建时间：2017年7月27日 下午2:08:17    
	 * 修改人：徐叶       
	 * 修改时间：2017年7月27日 下午2:08:17    
	 * 修改备注： 
	 * @param menuRequest
	 * @return</pre>    
	 */
	List<Map<String, Object>> selectTreeListJson(MenuRequest menuRequest);
	
	/**
	 * <pre>insertUserPhoto(这里用一句话描述这个方法的作用)   
	 * 创建人：徐叶     
	 * 创建时间：2017年8月4日 下午3:05:33    
	 * 修改人：徐叶       
	 * 修改时间：2017年8月4日 下午3:05:33    
	 * 修改备注： 
	 * @param fingerprint</pre>
	 * @return 
	 */
	Integer insertUserPhoto(FingerprintRequest fingerprint);
	/**
	 * <pre>registerAccount(这里用一句话描述这个方法的作用)   
	 * 创建人：徐叶     
	 * 创建时间：2017年8月6日 下午8:50:32    
	 * 修改人：徐叶       
	 * 修改时间：2017年8月6日 下午8:50:32    
	 * 修改备注： 
	 * @param userRequest
	 * @return</pre>
	 */
	Integer registerCheckAccount(UserRequest userRequest);
	/**
	 * <pre>saveCode(这里用一句话描述这个方法的作用)   
	 * 创建人：徐叶     
	 * 创建时间：2017年8月8日 上午9:12:30    
	 * 修改人：徐叶       
	 * 修改时间：2017年8月8日 上午9:12:30    
	 * 修改备注： 
	 * @param map</pre>
	 */
	void saveCode(Map<String, Object> map);

	/** <pre>CheckCodeNumber(这里用一句话描述这个方法的作用)   
	 * 创建人：徐叶     
	 * 创建时间：2017年8月8日 上午10:41:57    
	 * 修改人：徐叶       
	 * 修改时间：2017年8月8日 上午10:41:57    
	 * 修改备注： 
	 * @param userRequest
	 * @return</pre>    
	 */
	Integer CheckCodeNumber(UserRequest userRequest);

	/** <pre>registerUser(这里用一句话描述这个方法的作用)   
	 * 创建人：徐叶     
	 * 创建时间：2017年8月8日 上午11:59:04    
	 * 修改人：徐叶       
	 * 修改时间：2017年8月8日 上午11:59:04    
	 * 修改备注： 
	 * @param userRequest</pre>    
	 */
	void registerUser(UserRequest userRequest);
	
}
