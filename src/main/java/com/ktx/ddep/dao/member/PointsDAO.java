package com.ktx.ddep.dao.member;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.ktx.ddep.dto.PageVO;
import com.ktx.ddep.dto.member.Member;
import com.ktx.ddep.dto.member.Point;

@Mapper
@Repository
public interface PointsDAO {
	
	public List<Member> selectWeeklyRankList(Point point);
	public List<Point> selectList(PageVO pageVO);
	public int insertRvPoint(Point point);
}
