package com.ktx.ddep.service;

import java.util.List;
import java.util.Map;

import com.ktx.ddep.dto.recipe.Rcp;
import com.ktx.ddep.dto.recipe.RcpRv;

public interface RecipesService {
	
	public Map<String,Object> getRecipes(int page, String choice, String keyword);

	public List<Rcp> getRecipesByMemberNo(int memberNo);

	public List<Rcp> getTmpRecipesByMemberNo(int memberNo);

	public int removeTmpRecipesByRecipeNo(int no);

	public List<RcpRv> getOpenedRecipesByMemberNo(int memberNo);

	public RcpRv getOpenedRecipesForReview(RcpRv rcpRv);

}
