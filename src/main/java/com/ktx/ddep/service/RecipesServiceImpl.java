package com.ktx.ddep.service;

import java.util.List;
import java.util.Map;

import com.ktx.ddep.dao.recipe.RcpsDAO;
import com.ktx.ddep.dto.recipe.Rcp;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RecipesServiceImpl implements RecipesService {
	
	private final RcpsDAO rcpsDAO;
	
	@Override
	public Map<String, Object> getRecipes(int page, String choice, String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public List<Rcp> getRecipesByMemberNo(int memberNo) {
		
		return rcpsDAO.selectMyRcps(memberNo);
		
	}


}
