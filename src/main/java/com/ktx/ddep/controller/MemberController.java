package com.ktx.ddep.controller;

import java.security.Principal;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ktx.ddep.dto.member.Address;
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
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping(path="/")
	public String main(Principal principal, HttpSession session) {
		if(principal != null) {
			String userId = principal.getName();
			session.setAttribute("loggedInUser", membersService.getMemberById(userId));
		}
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
		
		return "members/signupform";
		
	}
	
	// sign up - email duplication check
	@GetMapping("/signup/ajax/email_duplication_check")
	@ResponseBody
	public int checkEmailDuplication(String email) {
		
		return membersService.checkEmailDuplication(email);
		
	}
	
	// sign up - nickname duplication check
	@GetMapping("/signup/ajax/nickname_duplication_check")
	@ResponseBody
	public int checkNicknameDuplication(String nickname) {
		
		return membersService.checkNicknameDuplication(nickname);
	}
	
	// sign up 
	@PostMapping("/signup")
	@ResponseBody
	public int signup(@RequestBody String json, HttpSession session) throws JsonMappingException, JsonProcessingException {
		
		ObjectMapper mapper = new ObjectMapper();
		
		// deserialize from json to java object which is Map
		Map<String, Object> map = mapper.readValue(json, new TypeReference<Map<String, Object>>(){});
		
		log.debug("{}", map);
		
		if(map != null) {
			try {
				// convert from map to required objs
				Member member = mapper.convertValue(map.get("Member"), Member.class);
				Address address = mapper.convertValue(map.get("Address"), Address.class);
				
				log.debug("{}, {} is converted", member, address);
				
				// encode password
				member.setPassword(passwordEncoder.encode(member.getPassword()));
				
				int addMemberResult = membersService.addMember(member, address);
				
				if(addMemberResult ==1) {
					session.setAttribute("loggedInUser", membersService.getMemberById(member.getEmail()));
					return addMemberResult;
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		return 0;
		
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
