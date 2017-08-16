package com.jk.pojo.user;

public class UserResponse extends User {
	
	
	private long loginFailDate;

	public long getLoginFailDate() {
		return loginFailDate;
	}

	public void setLoginFailDate(long loginFailDate) {
		this.loginFailDate = loginFailDate;
	}

}
