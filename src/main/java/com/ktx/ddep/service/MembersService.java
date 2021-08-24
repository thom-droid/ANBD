package com.ktx.ddep.service;

import com.ktx.ddep.dto.member.Address;
import com.ktx.ddep.dto.member.Member;
import com.ktx.ddep.security.UserDbService;

public interface MembersService extends UserDbService{

    public Member memberInfo(int no);

	public int addMember(Member member, Address address);

	public int checkEmailDuplication(String email);

	public int checkNicknameDuplication(String nickname);
    
}
