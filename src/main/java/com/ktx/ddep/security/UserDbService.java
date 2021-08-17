package com.ktx.ddep.security;

import java.util.List;

public interface UserDbService {
	
	public UserEntity getUserForSecurity(String email);
	public List<UserRoleEntity> getUserRolesForSecurity(String email);
	
}
