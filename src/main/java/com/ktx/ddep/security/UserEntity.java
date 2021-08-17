package com.ktx.ddep.security;

public class UserEntity {

	private String loginUserId;
    private String password;
    
	public String getLoginUserId() {
		return loginUserId;
	}
	public void setLoginUserId(String loginUserId) {
		this.loginUserId = loginUserId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public UserEntity(String loginUserId, String password) {
		super();
		this.loginUserId = loginUserId;
		this.password = password;
	}
	
	
    
}
