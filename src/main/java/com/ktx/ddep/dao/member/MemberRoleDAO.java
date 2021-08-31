package com.ktx.ddep.dao.member;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.ktx.ddep.dto.member.MemberRole;

@Mapper
@Repository
public interface MemberRoleDAO {

	// insert memberRole for ROLE_USER
	public int insertMemberRole(MemberRole memberRole);

	
}
