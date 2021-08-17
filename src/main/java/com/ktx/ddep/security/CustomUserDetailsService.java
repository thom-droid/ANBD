package com.ktx.ddep.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    // user data will be stored in an object that implements UserDbService
	// and those data will be used to verify authentication
    @Autowired
    UserDbService userdbService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserEntity customUser = userdbService.getUserForSecurity(email);
        
        if(customUser == null)
            throw new UsernameNotFoundException("사용자가 입력한 아이디에 해당하는 사용자를 찾을 수 없습니다.");

        List<UserRoleEntity> customRoles = userdbService.getUserRolesForSecurity(email);
        
        // this array will contain class SimpleGrantedAuthority that implements GrantedAuthority interface 
        List<GrantedAuthority> authorities = new ArrayList<>();
        
        // role must start with "ROLE_"
        if(customRoles != null) {
            for (UserRoleEntity customRole : customRoles) {
                authorities.add(new SimpleGrantedAuthority(customRole.getRoleName()));
            } 
        } 

        return CustomUserDetails.builder()
            	.username(customUser.getLoginUserId())
            	.password(customUser.getPassword())
            	.authorities(authorities)
            	.isEnabled(true)
            	.isAccountNonExpired(true)
            	.isAccountNonLocked(true)
            	.isCredentialsNonExpired(true)
            	.build();
    }
}