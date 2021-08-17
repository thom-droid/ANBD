package com.ktx.ddep.service;

import com.ktx.ddep.dto.member.Member;
import com.ktx.ddep.security.UserDbService;

public interface MembersService extends UserDbService{

    public Member memberInfo(int no);

	public void editPassword();
    
    
}
