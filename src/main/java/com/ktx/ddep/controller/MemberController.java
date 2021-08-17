package com.ktx.ddep.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.ktx.ddep.dto.member.Member;
import com.ktx.ddep.service.MembersService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MemberController {
	
	
	@Autowired
	private MembersService membersService;
	
	@GetMapping(path="/")
	public String main() {
		return "members/index";
	}
	
	//login page
	@GetMapping("/login")
	public String login_page() {
		return "members/login";
	}
	
	// login failure
	@GetMapping(path="/loginerror")
	public String loginerror(@RequestParam(name = "login_error") String loginError) {
		return "members/loginerror";
	}
	
	// signup page
	@GetMapping("/signup")
	public String getSignup() {
		return "signup";
	}
	
	// sign up 
	@PostMapping("/signup")
	public void signup(@RequestBody Member member) {
		
	}
	
	
	// security role test
	@GetMapping("/secured/test")
	public String secured() {
		return "users/secured_page";
	}
	
	
	
	// for test
	@GetMapping("/testjson/{no}")
	@ResponseBody
	public Member memberInfo(@PathVariable int no){

		Member responseDto = membersService.memberInfo(no);
		System.out.println("컨트롤러 호출");
		
		return responseDto;
	}
	
	//for encoding pw...
	@GetMapping("/update/pw")
	@ResponseBody
	public String encodePassword() {
		
		membersService.editPassword();
		
		return "updated";
	}

}
