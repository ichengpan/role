package com.jk.dao.user;

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

public interface UserDao {

	UserResponse login(UserRequest userRequest);

	void updateLoginFailNum(UserRequest userRequest);

	List<User> loadTree();

	List<Tree> findTree(int i);

	int querySonCount(Integer id);

	List<Tree> selectUserList(UserRequest userRequest);

	void deleteMuchUser(UserRequest user);

	int selectUserCount(UserRequest userRequest);

	List<RoleResponse> selectUserRoleListJson(RoleRequest roleRequest);

	void deleteAllRolesByUserID(RoleRequest roleRequest);

	void insertUserRoleList(List<RoleRequest> roleRequestList);

	UserResponse queryUserByUserID(UserRequest userRequest);

	void updateUser(UserRequest userRequest);

	/** <pre>insertUser(这里用一句话描述这个方法的作用)   
	 * 创建人：徐叶     
	 * 创建时间：2017年7月27日 上午10:57:48    
	 * 修改人：徐叶       
	 * 修改时间：2017年7月27日 上午10:57:48    
	 * 修改备注： 
	 * @param userRequest</pre>    
	 * @return 
	 */
	Integer insertUser(UserRequest userRequest);

	/** <pre>selectTreeListJson(这里用一句话描述这个方法的作用)   
	 * 创建人：徐叶     
	 * 创建时间：2017年7月27日 下午3:12:38    
	 * 修改人：徐叶       
	 * 修改时间：2017年7月27日 下午3:12:38    
	 * 修改备注： 
	 * @param menuRequest
	 * @return</pre>    
	 */
	List<Map<String, Object>> selectTreeListJson(MenuRequest menuRequest);

	Integer insertUserPhoto(FingerprintRequest fingerprint);

	void insertUserFileMid(FingerprintRequest fingerprint);

	UserResponse registerCheckAccount(UserRequest userRequest);

	/** <pre>saveCode(这里用一句话描述这个方法的作用)   
	 * 创建人：徐叶     
	 * 创建时间：2017年8月8日 上午9:15:39    
	 * 修改人：徐叶       
	 * 修改时间：2017年8月8日 上午9:15:39    
	 * 修改备注： 
	 * @param map</pre>    
	 */
	void saveCode(Map<String, Object> map);

	/** <pre>CheckCodeNumber(这里用一句话描述这个方法的作用)   
	 * 创建人：徐叶     
	 * 创建时间：2017年8月8日 上午10:44:35    
	 * 修改人：徐叶       
	 * 修改时间：2017年8月8日 上午10:44:35    
	 * 修改备注： 
	 * @param userRequest
	 * @return</pre>    
	 */
	String queryCodeNumber(UserRequest userRequest);

	/** <pre>registerUser(这里用一句话描述这个方法的作用)   
	 * 创建人：徐叶     
	 * 创建时间：2017年8月8日 下午12:05:41    
	 * 修改人：徐叶       
	 * 修改时间：2017年8月8日 下午12:05:41    
	 * 修改备注： 
	 * @param userRequest</pre>    
	 */
	void registerUser(UserRequest userRequest);

}
