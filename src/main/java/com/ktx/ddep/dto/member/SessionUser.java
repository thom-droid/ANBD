package com.ktx.ddep.dto.member;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SessionUser {

	private int no, addressNo;
	private String email, name, nickname, profileImg;
	
	@Builder
	public SessionUser(int no, int addressNo, String email, String name, String nickname, String profileImg) {
		super();
		this.no = no;
		this.addressNo = addressNo;
		this.email = email;
		this.name = name;
		this.nickname = nickname;
		this.profileImg = profileImg;
	}
	
}
