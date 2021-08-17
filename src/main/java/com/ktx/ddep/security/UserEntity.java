package com.ktx.ddep.security;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserEntity {

	private String loginUserId;
    private String password;
    
	public void setLoginUserId(String loginUserId) {
		this.loginUserId = loginUserId;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Builder
	public UserEntity(String loginUserId, String password) {
		super();
		this.loginUserId = loginUserId;
		this.password = password;
	}
	
	
    
}
