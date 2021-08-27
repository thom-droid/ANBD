package com.ktx.ddep.dto.member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class SessionUser {

	private int no, addressNo;
	private String email, name, nickname, profileImg;
	private char marketkeeperStep;
	
	@Builder
	public SessionUser(int no, int addressNo, String email, String name, String nickname, String profileImg, char marketkeeperStep) {
		super();
		this.no = no;
		this.addressNo = addressNo;
		this.email = email;
		this.name = name;
		this.nickname = nickname;
		this.profileImg = profileImg;
		this.marketkeeperStep = marketkeeperStep;
	}
	
}
