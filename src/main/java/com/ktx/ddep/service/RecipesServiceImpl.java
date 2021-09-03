package com.ktx.ddep.service;

import java.util.List;
import java.util.Map;

import com.ktx.ddep.dao.member.MembersDAO;
import com.ktx.ddep.dao.member.PointsDAO;
import com.ktx.ddep.dao.recipe.RcpRvsDAO;
import com.ktx.ddep.dao.recipe.RcpsDAO;
import com.ktx.ddep.dao.recipe.RcpsOpenDAO;
import com.ktx.ddep.dto.member.Point;
import com.ktx.ddep.dto.recipe.Rcp;
import com.ktx.ddep.dto.recipe.RcpRv;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class RecipesServiceImpl implements RecipesService {
	
	private final RcpsDAO rcpsDao;
	private final RcpRvsDAO rcpRvsDao;
	private final PointsDAO pointsDao;
	private final RcpsOpenDAO rcpsOpenDao;
	
	@Override
	public Map<String, Object> getRecipes(int page, String choice, String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public List<Rcp> getRecipesByMemberNo(int memberNo) {
		try {
			return rcpsDao.selectMyRcps(memberNo);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Rcp> getTmpRecipesByMemberNo(int memberNo) {
		try {
			return rcpsDao.selectTmpRcps(memberNo);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}


	@Override
	public int removeTmpRecipesByRecipeNo(int no) {
		// by deleting tmp rcp, all child records in relevant table are deleted 
		return rcpsDao.deleteTmpRcps(no);
	}


	@Override
	public List<RcpRv> getOpenedRecipesByMemberNo(int memberNo) {
		try {
			return rcpsOpenDao.selectOpenedRcps(memberNo);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	@Override
	public RcpRv getOpenedRecipesForReview(RcpRv rcpRv) {
		try {
			return rcpsOpenDao.selectOpenedRcpForRv(rcpRv);
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	

	@Override
	@Transactional
	public Integer postReview(RcpRv rcpRv, int memberNo) {
		try {
			// insert recipe review
			int result1 = rcpRvsDao.insertRcpRv(rcpRv);
			
			// insert point for reviewing
			Point point = new Point(memberNo, rcpRv.getNo());
			int result2 = pointsDao.insertRvPoint(point);
			
			return result1 + result2;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}


}
