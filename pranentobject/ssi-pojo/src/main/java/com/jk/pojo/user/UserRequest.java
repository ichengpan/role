package com.jk.pojo.user;

public class UserRequest extends User {
	
	private String userImgCode;
	
	private String sysImgCode;
	
	private String phoneNumber;

	public String getSysImgCode() {
		return sysImgCode;
	}

	public void setSysImgCode(String sysImgCode) {
		this.sysImgCode = sysImgCode;
	}

	public String getUserImgCode() {
		return userImgCode;
	}

	public void setUserImgCode(String userImgCode) {
		this.userImgCode = userImgCode;
	}

	@Override
	public String toString() {
		return "UserRequest [userImgCode=" + userImgCode + ", sysImgCode=" + sysImgCode + ", phoneNumber=" + phoneNumber
				+ "]";
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	
}
