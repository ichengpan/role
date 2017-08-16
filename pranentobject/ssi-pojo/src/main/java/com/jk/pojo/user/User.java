
package com.jk.pojo.user;

import java.io.Serializable;

import com.google.gson.annotations.Expose;

import common.util.PageUtil;

public class User extends PageUtil implements Serializable {
	private String ids;

	private Integer userID;
	
	private String phoneCode;
	
	private String userAccount;
	
	private String photoURL;
	
	private String email;
	
	private String userPwd;
	
	private int loginFailNum;

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public int getLoginFailNum() {
		return loginFailNum;
	}

	public void setLoginFailNum(int loginFailNum) {
		this.loginFailNum = loginFailNum;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public String getPhotoURL() {
		return photoURL;
	}


	public void setPhotoURL(String photoURL) {
		this.photoURL = photoURL;
	}

	public String getPhoneCode() {
		return phoneCode;
	}

	public void setPhoneCode(String phoneCode) {
		this.phoneCode = phoneCode;
	}

	@Override
	public String toString() {
		return "User [ids=" + ids + ", userID=" + userID + ", phoneCode=" + phoneCode + ", userAccount=" + userAccount
				+ ", photoURL=" + photoURL + ", userPwd=" + userPwd + ", loginFailNum=" + loginFailNum + "]";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


}
