package com.ktx.ddep.controller;

import java.security.Principal;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ktx.ddep.dao.member.MembersDAO;
import com.ktx.ddep.dto.member.Address;
import com.ktx.ddep.dto.member.Member;
import com.ktx.ddep.dto.member.SessionUser;
import com.ktx.ddep.security.LoginUser;
import com.ktx.ddep.service.MembersService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@RequiredArgsConstructor
@Controller
public class MemberController {
	
	private final MembersService membersService;
	private final PasswordEncoder passwordEncoder;
	
	// index
	@GetMapping(path="/")
	public String main(Principal principal, HttpSession session) {
		if(principal != null) {
			String userId = principal.getName();
			Member memberInfo = membersService.getMemberById(userId);
			
			//set session with SessionUser
			session.setAttribute("loggedInUser", SessionUser.builder()
															.no(memberInfo.getNo())
															.addressNo(memberInfo.getAddressNo())
															.name(memberInfo.getName())
															.nickname(memberInfo.getNickname())
															.profileImg(memberInfo.getProfileImg())
															.email(memberInfo.getEmail())
															.marketkeeperStep(memberInfo.getMarketkeeperStep())
															.build());
			
			log.info("user {} is logged", userId);
		}
		return "members/index";
	}
	
	//login page
	@GetMapping("/login")
	public String login_page() {
		return "members/login";
	}
	
	// login failure
	@RequestMapping(path="/loginerror")
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
					Member memberInfo = membersService.getMemberById(member.getEmail());
					session.setAttribute("loggedInUser", SessionUser.builder()
															.no(memberInfo.getNo())
															.addressNo(memberInfo.getAddressNo())
															.name(memberInfo.getName())
															.nickname(memberInfo.getNickname())
															.profileImg(memberInfo.getProfileImg())
															.email(memberInfo.getEmail())
															.marketkeeperStep(memberInfo.getMarketkeeperStep())
															.build());
					return addMemberResult;
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return 0;
	}
	
	//mypage user
	@GetMapping("/mypage")
	public String mypage(ModelMap map, @LoginUser SessionUser user) {
		
		map.addAllAttributes(membersService.getMypageInfoByMember(user));
		
		return "members/mypage";
	}
	
	
	
	
	
	// security role test
	@GetMapping("/secured/test")
	public String secured() {
		return "users/secured_page";
	}

}
