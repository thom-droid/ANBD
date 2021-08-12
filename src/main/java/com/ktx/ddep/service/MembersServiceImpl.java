package com.ktx.ddep.service;

import com.ktx.ddep.dao.MembersDAO;
import com.ktx.ddep.vo.Member;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Mapper
public class MembersServiceImpl implements MembersService {

    @Autowired
    MembersDAO membersDAO;

    @Override
    public Member memberInfo(int no) {
    	System.out.println("서비스호출");
        return membersDAO.selectOne(no);
    }
}
