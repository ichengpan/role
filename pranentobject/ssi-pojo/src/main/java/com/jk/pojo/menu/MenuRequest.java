package com.jk.pojo.menu;

/**
 * 
 * <pre>项目名称：ssi-pojo    
 * 类名称：MenuRequest    
 * 类描述：    
 * 创建人：徐叶  
 * 创建时间：2017年7月27日 下午3:21:42    
 * 修改人：徐叶    
 * 修改时间：2017年7月27日 下午3:21:42    
 * 修改备注：       
 * @version </pre>
 */
public class MenuRequest extends Menu {
	
	private int roleID;
	
	private int userID;

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getRoleID() {
		return roleID;
	}

	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}

}
