package com.ktx.ddep.service;

import com.ktx.ddep.dao.member.AddressDAO;
import com.ktx.ddep.dao.member.MemberRoleDAO;
import com.ktx.ddep.dao.member.MembersDAO;
import com.ktx.ddep.dto.member.Address;
import com.ktx.ddep.dto.member.Member;
import com.ktx.ddep.dto.member.MemberRole;
import com.ktx.ddep.security.UserEntity;
import com.ktx.ddep.security.UserRoleEntity;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MembersServiceImpl implements MembersService {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
    private final MembersDAO membersDAO;
    private final AddressDAO addressDAO;
    private final MemberRoleDAO memberRoleDAO;

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
 	public int checkEmailDuplication(String email) {
 		
 		return membersDAO.selectCheckEmail(email);
 	}
 	
 	@Override
	public int checkNicknameDuplication(String nickname) {
 		
		return membersDAO.selectCheckNickname(nickname);
	}

	// insert member
	@Override
	@Transactional
	public int addMember(Member member, Address address) {
		
		// 1) insert address
		int addressResult = addressDAO.insertAddress(address);
		log.debug("{} insert address result", addressResult);
		
		// 2) when insertAddress() succeeds, call insertJoinMember()
		member.setAddressNo(address.getNo());
		log.debug("address No : {}", address.getNo());
		membersDAO.insertJoinMember(member);
		
		// 3) with member's number recently added, call insertMemberRole()
		return memberRoleDAO.insertMemberRole(MemberRole.builder()
				.memberNo(member.getNo())
				.roleName("ROLE_USER")
				.build());
	}

	
	
}
