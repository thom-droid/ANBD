package com.ktx.ddep.dao.recipe;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.ktx.ddep.dto.PageVO;
import com.ktx.ddep.dto.recipe.Rcp;

@Mapper
@Repository
public interface RcpsDAO {


	// 210129 양 레시피 페이지로 모든 목록 불러오기
	public List<Rcp> selectList(PageVO pageVO);

	// 0129 방 내가쓴 레시피
	public List<Rcp> selectMyRcps(int memberNo);

	// 0130 방 임저레
	public List<Rcp> selectTmpRcps(int memberNo);

	// 0130 방 임저레 삭제
	public int deleteTmpRcps(int tmpRcpNo);

	// 김 0201
	public Rcp selectOne(int no);

	// 0204 방 요리후기 평점 업데이트
	public int updateRcpAvg(Rcp rcp);

	// 0204 방 스크랩 레시피
	public List<Rcp> selectSavedRcps(int memberNo);

	// 0204 방 레시피 스크랩 횟수
	public int selectScrapCount(int rcpNo);

	// 0204 방 스크랩 레시피 삭제

	public int deleteSavedRcp(int rcpNo);

	// 21-02-09 07:58 양 레시피 스크랩 순으로 모든 목록 불러오기
	public List<Rcp> selectListByScrap(PageVO pageVO);

	// 21-02-09 07:59 양 레시피 스크랩 순으로 모든 목록 불러오기
	public List<Rcp> selectListByView(PageVO pageVO);

	// 21-02-13 15:59 양 키워드로 레시피 리스트 들고오기
	public List<Rcp> selectListByKeyword(PageVO pageVO);

	// 21-02-13 18:11 양 검색 레시피 수 불러오기
	public int countRcps(String keyword);

	//21-01-29 양 레시피 모든 목록 불러오기
	public int countRcps();

	/* 21-02-14 19:34 양 조회수 업데이트 */
	public int updateViewCount(int no);
	
	

}
