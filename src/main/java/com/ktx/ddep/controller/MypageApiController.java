package com.ktx.ddep.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ktx.ddep.dto.member.SessionUser;
import com.ktx.ddep.dto.recipe.Rcp;
import com.ktx.ddep.dto.recipe.RcpRv;
import com.ktx.ddep.security.LoginUser;
import com.ktx.ddep.service.RecipesService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
public class MypageApiController {

	private final RecipesService recipesService; 
	
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
	
}
