package com.ktx.ddep.controller;

import com.ktx.ddep.vo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.ktx.ddep.service.MembersService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MemberController {
	
	
	@Autowired
	private MembersService membersService;
	
	@GetMapping(path="/")
	public String main() {
		return "index";
	}
	
	//login page
	@GetMapping("/login")
	public String login_page() {
		return "login";
	}
	
	@GetMapping("/testjson/{no}")
	@ResponseBody
	public Member member_info(@PathVariable int no){

		Member responseDto = membersService.memberInfo(no);
		System.out.println("컨트롤러 호출");
		
		return responseDto;
	}

}
