package com.jk.service.user.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.jk.dao.user.UserDao;
import com.jk.pojo.fingerprint.FingerprintRequest;
import com.jk.pojo.menu.MenuRequest;
import com.jk.pojo.role.RoleRequest;
import com.jk.pojo.role.RoleResponse;
import com.jk.pojo.tree.Tree;
import com.jk.pojo.user.User;
import com.jk.pojo.user.UserRequest;
import com.jk.pojo.user.UserResponse;
import com.jk.service.user.UserService;

import common.constant.Constant;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	
	/* (non-Javadoc)    
	 * @see com.jk.service.user.UserService#registerUser(com.jk.pojo.user.UserRequest)    
	 */
	@Override
	public void registerUser(UserRequest userRequest) {
		userDao.registerUser(userRequest);
	}
	
	/* (non-Javadoc)    
	 * @see com.jk.service.user.UserService#CheckCodeNumber(com.jk.pojo.user.UserRequest)    
	 */
	@Override
	public Integer CheckCodeNumber(UserRequest userRequest) {
		String phoneCode = userDao.queryCodeNumber(userRequest);
		if(phoneCode != null){
			if(phoneCode.equals(userRequest.getPhoneCode())){
				return Constant.CODE_SUCESS;
			}
		}
		return Constant.CODE_ERROR;
	}
	
	/* (non-Javadoc)    
	 * @see com.jk.service.user.UserService#saveCode(java.util.Map)    
	 */
	@Override
	public void saveCode(Map<String, Object> map) {
		userDao.saveCode(map);
	}
	
	@Override
	public Integer registerCheckAccount(UserRequest userRequest) {
		UserResponse user = userDao.registerCheckAccount(userRequest);
		if(user != null){
			return Constant.USERNAME_EXISTS;
		}
		return Constant.USERNAME_AVAILABLE;
	}
	
	/* (non-Javadoc)    
	 * @see com.jk.service.UserService#selectTreeListJson(com.jk.pojo.MenuRequest)    
	 */
	@Override
	public void updateUser(UserRequest userRequest,FingerprintRequest fingerprint) {
		if(userRequest.getUserID() != null){
			userDao.updateUser(userRequest);
		} else {
			Integer userID = userDao.insertUser(userRequest);
			fingerprint.setUserID(userID);
			userDao.insertUserFileMid(fingerprint);
		}
	}
	
	@Override
	public Integer insertUserPhoto(FingerprintRequest fingerprint) {
		return userDao.insertUserPhoto(fingerprint);
	}
	
	@Override
	public List<Map<String, Object>> selectTreeListJson(MenuRequest menuRequest) {
		//从redis缓存中获取权限列表
		
				//如果没有获取到，则查询数据库，如果查询到了结果，直接返回
				
				//把查询的结果存一份到redis上
				List<Map<String, Object>> treeList = userDao.selectTreeListJson(menuRequest);
				if (null != treeList && 0 < treeList.size()) {
					//开始调用递归
					treeList = queryTreeListNodes(treeList, menuRequest);
				} else {
					treeList = new ArrayList<Map<String,Object>>();
				}
				return treeList;
			}
			
			//递归查询树菜单
			private List<Map<String, Object>> queryTreeListNodes(List<Map<String, Object>> treeList, MenuRequest menuRequest) {
				for (Map<String, Object> map : treeList) {
					if ("0".equals(map.get("pid").toString())) {
						//取出ID作为下次查询的pid
						int pid = Integer.valueOf(map.get("id").toString());
						menuRequest.setPid(pid);
						List<Map<String, Object>> queryTreeListNodes = 
								queryTreeListNodes(userDao.selectTreeListJson(menuRequest), menuRequest);
						map.put("nodes", queryTreeListNodes);
					}
				}
				return treeList;
			}
	
	@Override
	public UserResponse queryUserByUserID(UserRequest userRequest) {
		return userDao.queryUserByUserID(userRequest);
	}
	
	@Override
	public void insertUserRoleList(List<RoleRequest> roleRequestList) {
		// 1、删除用户之前的所有角色（mid）
		userDao.deleteAllRolesByUserID(roleRequestList.get(0));
		// 2、添加用户勾选的所有角色（mid）
		userDao.insertUserRoleList(roleRequestList);
	}
	
	@Override
	public List<RoleResponse> selectUserRoleListJson(RoleRequest roleRequest) {
		return userDao.selectUserRoleListJson(roleRequest);
	}
	
	@Override
	public int selectUserCount(UserRequest userRequest) {
		return userDao.selectUserCount(userRequest);
	}
	
	@Override
	public void deleteMuchUser(UserRequest user) {
		userDao.deleteMuchUser(user);
	}
	
	@Override
	public List<Tree> selectUserList(UserRequest userRequest) {
		return userDao.selectUserList(userRequest);
	}
	
	@Override
	public int querySonCount(Integer id) {
		return userDao.querySonCount(id);
	}
	
	@Override
	public List<Tree> findTree(int i) {
		return userDao.findTree(i);
	}
	
	
	@Override
	public List<User> loadTree() {
		List<User> list = userDao.loadTree();
		JsonObject jsonObject = new JsonObject();
		return list;
	}
	
	@Override
	public Map<String, Object> login(UserRequest userRequest) {
		Map<String, Object> map = new HashMap<String, Object>();
		//默认设置密码错误
		map.put("flag", Constant.LOGIN_PWD_ERROR);
		map.put("userInfo", null);
		
		//判断验证码
		if (null == userRequest.getUserImgCode() || "".equals(userRequest.getUserImgCode())) {
			//验证码为空
			map.put("flag", Constant.LOGIN_CODE_NULL);
		} else if (userRequest.getUserImgCode().equals(userRequest.getSysImgCode())) {
			
			
			
			 //连接数据库，查询用户信息
			UserResponse userResponse = userDao.login(userRequest);
			
			//判断是否被锁定（小于连续3次失败并且距离最近一次失败大于5分钟）
			if (null == userResponse) {
				//用户不存在
				map.put("flag", Constant.LOGIN_ACCOUNT_ERROR);
			} else if (0 == userResponse.getLoginFailNum()
					|| 0 < (userResponse.getLoginFailNum() % 3)
					|| userResponse.getLoginFailDate() > 300000) {
				if (userResponse.getUserPwd().equals(userRequest.getUserPwd())) {
					//登陆成功
					map.put("flag", Constant.LOGIN_SUCCESS);
					//用户信息放入map中
					map.put("userInfo", userResponse);
					userRequest.setLoginFailNum(0);
				} else {
					//密码错误
					//连接数据库，修改登陆失败的次数
					int loginFailNum = 1;
					if (userResponse.getLoginFailDate() > 300000) {
						userRequest.setLoginFailNum(1);
					} else {
						userRequest.setLoginFailNum(userResponse.getLoginFailNum() + 1);
						loginFailNum = userResponse.getLoginFailNum() + 1;
					}
					map.put("loginfailnum", loginFailNum);
				}
				//修改登陆失败次数
				userDao.updateLoginFailNum(userRequest);
			} else {
				//锁定
				map.put("flag", Constant.ACCOUNT_LOCKED);
			}
		} else {
			//验证码错误
			map.put("flag", Constant.LOGIN_CODE_ERROR);
		}
		return map;
	}
}
