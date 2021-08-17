package com.ktx.ddep.service;

import com.ktx.ddep.dao.member.MembersDAO;
import com.ktx.ddep.dto.member.Member;
import com.ktx.ddep.dto.member.MemberRole;
import com.ktx.ddep.security.UserEntity;
import com.ktx.ddep.security.UserRoleEntity;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Mapper
public class MembersServiceImpl implements MembersService {
	
    @Autowired
    MembersDAO membersDAO;
    
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Member memberInfo(int no) {
    	
        return membersDAO.selectOne(no);
        
    }
    
    // retrieve user data for security
    @Override
    public UserEntity getUserForSecurity(String email) {
    	Member member = membersDAO.selectById(email);
    	
    	return member.toEntity();
    }
    
    // retrieve user role data for security
    @Override
    public List<UserRoleEntity> getUserRolesForSecurity(String email) {
    	
    	List<MemberRole> mr = membersDAO.selectRoleById(email);
    	List<UserRoleEntity> list = new ArrayList<UserRoleEntity>();
    	
    	for(MemberRole role: mr) {
    		
    		list.add(role.toEntity());
    		
    	}
    	
    	return list;
    }

    
	@Override
	public void editPassword() {
		
		List<Member> list = membersDAO.selectPassword();
		
		if (list!=null) {
			for(Member m: list) {
				// retrieve pw and encode
				m.setPassword(passwordEncoder.encode(m.getPassword()));
				membersDAO.updatePassword(m);
			}
		}
		
		
	}
}
