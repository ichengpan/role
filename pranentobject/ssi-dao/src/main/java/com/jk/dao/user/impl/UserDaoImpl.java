package com.jk.dao.user.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.jk.dao.user.UserDao;
import com.jk.pojo.fingerprint.FingerprintRequest;
import com.jk.pojo.menu.MenuRequest;
import com.jk.pojo.role.RoleRequest;
import com.jk.pojo.role.RoleResponse;
import com.jk.pojo.tree.Tree;
import com.jk.pojo.user.User;
import com.jk.pojo.user.UserRequest;
import com.jk.pojo.user.UserResponse;

import common.util.JedisUtil;
@Repository
public class UserDaoImpl extends SqlMapClientDaoSupport implements UserDao{
	/* (non-Javadoc)    
	 * @see com.jk.dao.user.UserDao#CheckCodeNumber(com.jk.pojo.user.UserRequest)    
	 */
	@Override
	public String queryCodeNumber(UserRequest userRequest) {
		try {
			String string = JedisUtil.getString(userRequest.getPhoneNumber());
			return string;
		} catch (Exception e) {
			return null;
		}
		 
	}
	
	/* (non-Javadoc)    
	 * @see com.jk.dao.user.UserDao#saveCode(java.util.Map)    
	 */
	@Override
	public void saveCode(Map<String, Object> map) {
		JedisUtil.setString(map.get("telNum").toString(), map.get("code").toString(), 900);
	}
	
	/* (non-Javadoc)    
	 * @see com.jk.dao.user.UserDao#registerUser(com.jk.pojo.user.UserRequest)    
	 */
	@Override
	public void registerUser(UserRequest userRequest) {
		this.getSqlMapClientTemplate().insert("user.registerUser",userRequest);
	}
	
	@Override
	public UserResponse registerCheckAccount(UserRequest userRequest) {
		return (UserResponse) this.getSqlMapClientTemplate().queryForObject("user.registerCheckAccount", userRequest);
	}
	
	@Override
	public void insertUserFileMid(FingerprintRequest fingerprint) {
		this.getSqlMapClientTemplate().insert("user.insertUserFileMid",fingerprint);
	}
	
	@Override
	public Integer insertUserPhoto(FingerprintRequest fingerprint) {
		return (Integer) this.getSqlMapClientTemplate().insert("user.insertUserPhoto", fingerprint);
	}
	/* (non-Javadoc)    
	 * @see com.jk.dao.UserDao#selectTreeListJson(com.jk.pojo.MenuRequest)    
	 */
	@Override
	public List<Map<String, Object>> selectTreeListJson(MenuRequest menuRequest) {
		return this.getSqlMapClientTemplate().queryForList("user.selectTreeListJson", menuRequest);
	}
	
	/* (non-Javadoc)    
	 * @see com.jk.dao.UserDao#insertUser(com.jk.pojo.UserRequest)    
	 */
	@Override
	public Integer insertUser(UserRequest userRequest) {
		return (Integer) this.getSqlMapClientTemplate().insert("user.insertUser",userRequest);
	}
	
	@Override
	public void updateUser(UserRequest userRequest) {
		this.getSqlMapClientTemplate().update("user.updateUser", userRequest);
	}
	
	@Override
	public UserResponse queryUserByUserID(UserRequest userRequest) {
		return (UserResponse) this.getSqlMapClientTemplate().queryForObject("user.queryUserByUserID", userRequest);
	}
	
	@Override
	public void deleteAllRolesByUserID(RoleRequest roleRequest) {
		this.getSqlMapClientTemplate().delete("user.deleteAllRolesByUserID", roleRequest);
	}
	
	@Override
	public void insertUserRoleList(final List<RoleRequest> roleRequestList) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback<Object>() {
			/* (non-Javadoc)    
			 * @see org.springframework.orm.ibatis.SqlMapClientCallback#doInSqlMapClient(com.ibatis.sqlmap.client.SqlMapExecutor)    
			 */
			@Override
			public Object doInSqlMapClient(SqlMapExecutor sqlMap) throws SQLException {
				//开启批量
				sqlMap.startBatch();
				//添加批量操作语句
				for (RoleRequest roleRequest : roleRequestList) {
					sqlMap.insert("user.insertUserRole", roleRequest);
				}
				//执行批量操作
				sqlMap.executeBatch();
				return null;
			}
		});
	}
	
	@Override
	public List<RoleResponse> selectUserRoleListJson(RoleRequest roleRequest) {
		return this.getSqlMapClientTemplate().queryForList("user.selectUserRoleListJson", roleRequest);
	}
	
	@Override
	public int selectUserCount(UserRequest userRequest) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject("user.selectUserCount", userRequest);
	}
	
	@Override
	public void deleteMuchUser(UserRequest user) {
		this.getSqlMapClientTemplate().delete("user.deleteMuchUser",user);
	}
	@Override
	public List<Tree> selectUserList(UserRequest userRequest) {
		return this.getSqlMapClientTemplate().queryForList("user.selectUserList", userRequest);
	}	
	@Override
	public int querySonCount(Integer id) {
		return (Integer) this.getSqlMapClientTemplate().queryForList("tree.querySonCount",id).size();
	}
	
	@Override
	public List<Tree> findTree(int i) {
		return this.getSqlMapClientTemplate().queryForList("tree.findTree",i);
	}
	
	@Override
	public List<User> loadTree() {
		return this.getSqlMapClientTemplate().queryForList("tree.loadTree");
	}
	
	@Override
	public UserResponse login(UserRequest userRequest) {
		return (UserResponse) this.getSqlMapClientTemplate().queryForObject("user.login", userRequest);
	}
	
	@Override
	public void updateLoginFailNum(UserRequest userRequest) {
		this.getSqlMapClientTemplate().update("user.updateLoginFailNum", userRequest);
	}
}
