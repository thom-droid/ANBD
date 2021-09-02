package com.ktx.ddep.service;

import com.ktx.ddep.dao.market.MarketTimesDAO;
import com.ktx.ddep.dao.market.MarketsDAO;
import com.ktx.ddep.dao.member.AddressDAO;
import com.ktx.ddep.dao.member.MemberRoleDAO;
import com.ktx.ddep.dao.member.MembersDAO;
import com.ktx.ddep.dao.member.PointsDAO;
import com.ktx.ddep.dto.member.Address;
import com.ktx.ddep.dto.member.Member;
import com.ktx.ddep.dto.member.MemberRole;
import com.ktx.ddep.dto.member.SessionUser;
import com.ktx.ddep.security.UserEntity;
import com.ktx.ddep.security.UserRoleEntity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class MembersServiceImpl implements MembersService {
	
    private final MembersDAO membersDao;
    private final AddressDAO addressDao;
    private final MemberRoleDAO memberRoleDao;
    private final MarketsDAO marketsDao;
    private final MarketTimesDAO marketTimesDao;
    private final PointsDAO pointsDAO;

    @Override
    public Member memberInfo(int no) {
    	
        return membersDao.selectOne(no);
    }
    
    // retrieve user data for security
    @Override
    public UserEntity getUserForSecurity(String email) {
    	
    	Member member = membersDao.selectById(email);
    	
    	return member.toEntity();
    }
    
    // retrieve user role data for security
    @Override
    public List<UserRoleEntity> getUserRolesForSecurity(String email) {
    	
    	List<MemberRole> mr = membersDao.selectRoleById(email);
    	List<UserRoleEntity> list = new ArrayList<UserRoleEntity>();
    	
    	for(MemberRole role: mr) {
    		list.add(role.toEntity());
    	}
    	
    	return list;
    }
    
    
 	@Override
 	public int checkEmailDuplication(String email) {
 		
 		return membersDao.selectCheckEmail(email);
 	}
 	
 	@Override
	public int checkNicknameDuplication(String nickname) {
 		
		return membersDao.selectCheckNickname(nickname);
	}

	// insert member
	@Override
	@Transactional
	public int addMember(Member member, Address address) {
		
		// 1) insert address
		int addressResult = addressDao.insertAddress(address);
		
		log.debug("{} insert address result", addressResult);
		
		// 2) when insertAddress() succeeds, call insertJoinMember()
		member.setAddressNo(address.getNo());
		membersDao.insertJoinMember(member);
		
		log.debug("address No : {}", address.getNo());
		
		// 3) with member's number recently added, call insertMemberRole()
		return memberRoleDao.insertMemberRole(MemberRole.builder()
				.memberNo(member.getNo())
				.roleName("ROLE_USER")
				.build());
	}

	// member info after sign up
	@Override
	public Member getMemberById(String email) {
		return membersDao.selectById(email);
	}

	// mypage information
	@Override
	public Map<String, Object> getMypageInfoByMember(SessionUser user) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		int memberNo = user.getNo();
		int memberAddrNo = user.getAddressNo();
		
		map.put("memberInfo", membersDao.selectOne(memberNo));
		map.put("market", marketsDao.selectOne(memberNo));
		map.put("addr", addressDao.selectOne(memberAddrNo));
		map.put("marketTime", marketTimesDao.selectTime(memberNo));
		map.put("memberRank", membersDao.selectTotalRankerOne(memberNo));
		
		return map;
	}

	@Override
	public Integer updateProfileImage(Member member) {
		
		return membersDao.updateProfileImg(member);
	}

	
	
	
	
}
