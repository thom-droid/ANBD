package com.ktx.ddep.dto.member;

import java.sql.Timestamp;

import com.ktx.ddep.security.UserRoleEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberRole {

	private int no;
	private int memberNo;
	private String email;
	private String roleName;
	private Timestamp regdate;
	
	// return UserRoleEntity
	public UserRoleEntity toEntity() {
		return UserRoleEntity.builder()
				.email(email)
				.roleName(roleName)
				.build();
	}

	@Builder
	public MemberRole(int memberNo, String roleName) {
		super();
		this.memberNo = memberNo;
		this.roleName = roleName;
	}
	
}
