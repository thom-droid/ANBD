package com.ktx.ddep.security;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserRoleEntity {

	private String email;
	private String roleName;
	
	@Builder
	public UserRoleEntity(String email, String roleName) {
		super();
		this.email = email;
		this.roleName = roleName;
	}
	 
	
	 
}
