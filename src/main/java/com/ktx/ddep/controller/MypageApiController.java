package com.ktx.ddep.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ktx.ddep.dto.ImageUploadType;
import com.ktx.ddep.dto.member.Address;
import com.ktx.ddep.dto.member.Member;
import com.ktx.ddep.dto.member.SessionUser;
import com.ktx.ddep.dto.recipe.Rcp;
import com.ktx.ddep.dto.recipe.RcpRv;
import com.ktx.ddep.security.LoginUser;
import com.ktx.ddep.service.MembersService;
import com.ktx.ddep.service.RecipesService;
import com.ktx.ddep.util.ImageUploadUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
public class MypageApiController {

	private final RecipesService recipesService;
	private final MembersService membersService;
	
	@PostMapping("/mypage/ajax/update_profile")
	public String updateProfileImage(MultipartFile profileImage, HttpServletRequest request, @LoginUser SessionUser user) throws IllegalStateException, IOException {
		
		try {
			
			String uploadPath = ImageUploadUtil.getImagePath(request, ImageUploadType.PROFILE);
			
			String fileName = ImageUploadUtil.uploadFileAndReturnFileName(profileImage, uploadPath);
			
			if(fileName != null) {
				Member member = new Member();
				member.setNo(user.getNo());
				member.setProfileImg(fileName);
				
				log.info("no: {}, profileimg : {}", member.getNo(), member.getProfileImg());
				
				membersService.updateProfileImage(member);
				
				return "{\"profileImage\":\""+fileName+"\"}";
			}
			 
			// if update is failed, then file must be deleted.
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return null;
	}

	
	@GetMapping("/mypage/ajax/myrecipes")
	public List<Rcp> getMyRecipes(@LoginUser SessionUser user) {
		
		if(user!=null) {
			int memberNo = user.getNo();
		
			return recipesService.getRecipesByMemberNo(memberNo);
		}
		
		return null;
		
	}
	
	@GetMapping("/mypage/ajax/tmp_recipes")
	public List<Rcp> getTmpRecipes(@LoginUser SessionUser user){
		
		if(user!=null) {
			int memberNo = user.getNo();
			
			return recipesService.getTmpRecipesByMemberNo(memberNo);
			
		}
		
		return null;
	}
	
	@DeleteMapping("/mypage/ajax/delete_tmp_rcp/{recipeNo}")
	public int deleteTmpRecipe(@PathVariable("recipeNo") int no) {
		
		if(no!=0) {
			return recipesService.removeTmpRecipesByRecipeNo(no);
		}
		
		return 0;
	}
	
	@GetMapping("/mypage/ajax/opened_recipes")
	public List<RcpRv> getOpenedRecipes(@LoginUser SessionUser user){
		if(user!= null) {
			int memberNo = user.getNo();
			
			return recipesService.getOpenedRecipesByMemberNo(memberNo); 
		}
		
		return null;
	}
	
	@GetMapping("/mypage/ajax/recipes_for_review/{openedRcpNo}")
	public RcpRv getOpenedRecipeForReview(@PathVariable("openedRcpNo") int openedRcpNo, @LoginUser SessionUser user) {
		if(openedRcpNo != 0 || user!=null) {
			
			int memberNo = user.getNo();
			RcpRv rcpRv = RcpRv.builder()
					.openedRcpNo(openedRcpNo)
					.memberNo(memberNo)
					.build();
			
			return recipesService.getOpenedRecipesForReview(rcpRv);
		}
		
		
		return null;
	}
	
	@PostMapping("/mypage/ajax/upload_review_image")
	public String uploadReviewImage(MultipartFile reviewImage, HttpServletRequest request) throws IllegalStateException, IOException {
		
		String uploadPath = ImageUploadUtil.getImagePath(request, ImageUploadType.RECIPE_REVIEW);
		
		String fileName = ImageUploadUtil.uploadFileAndReturnFileName(reviewImage, uploadPath);
		
		log.info("filename is {}", fileName);
		
		return "{\"imgName\":\""+fileName+"\"}";
	}
	
	@PostMapping("/mypage/ajax/post_review")
	public Integer postReview(@RequestBody RcpRv rcpRv, @LoginUser SessionUser user) {
		return recipesService.postReview(rcpRv, user.getNo());
	}
	
	@PutMapping("/mypage/ajax/update_address")
	public Integer updateAddress(@RequestBody Address address, @LoginUser SessionUser user) {
		address.setNo(user.getAddressNo());
		membersService.updateAddress(address);
		
		return user.getAddressNo();
	}
	
}
