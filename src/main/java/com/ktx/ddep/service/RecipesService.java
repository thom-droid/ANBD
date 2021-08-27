package com.ktx.ddep.service;

import java.util.List;
import java.util.Map;

import com.ktx.ddep.dto.recipe.Rcp;

public interface RecipesService {
	
	public Map<String,Object> getRecipes(int page, String choice, String keyword);

	public List<Rcp> getRecipesByMemberNo(int memberNo);

}
