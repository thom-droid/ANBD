package com.ktx.ddep.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.ktx.ddep.dto.member.Member;
import com.ktx.ddep.service.MembersService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequiredArgsConstructor
@Controller
public class MemberController {
	
	private final MembersService membersService;
	
	private final PasswordEncoder passwordEncoder;
	
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
		return "members/signup";
	}
	
	// sign up 
	@PostMapping("/signup")
	public int signup(@RequestBody Member member) {
		
		//encode password
		member.setPassword(passwordEncoder.encode(member.getPassword()));
		
		return membersService.addMember(member);
		
	}
	
	// sign up - email duplication check
	@GetMapping("/signup/ajax/email_duplication_check")
	@ResponseBody
	public int checkEmailDuplication(String email) {
		
		return membersService.checkEmailDuplication(email);
		
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

}
