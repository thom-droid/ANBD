package com.ktx.ddep.dao.member;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ktx.ddep.dto.PageVO;
import com.ktx.ddep.dto.member.Address;
import com.ktx.ddep.dto.member.Member;
import com.ktx.ddep.dto.member.MemberRole;
import com.ktx.ddep.dto.member.Point;
import com.ktx.ddep.security.UserRoleEntity;

@Mapper
public interface MembersDAO {

	
	// 20210122 양 전체 랭킹 페이지 처리
		public List<Member> selectTotalRankerList(PageVO PageVO);

		// 20210126 양 주간 랭킹 받아오기
		public List<Member> selectWeeklyRankList(Point point);

		// 20210122 양 전체 랭킹 페이지처리를 위한 총 member수
		public int selectTotal();

		// 210125 양 로그인
		public Member selectLogin(Member member);

		// 210127 양 no로 member정보 얻어오기
		public Member selectOne(int no);

		// 210127 양 no로 주간 랭킹 정보 얻어오기
		public Member selectWeeklyRankOne(Point point);

		// 210127 양 no로 전체 랭킹 정보 얻어오기
		public Member selectTotalRankerOne(int no);

		// 2021-01-23 이소현
		public int selectCheckEmail(String email);// selectCheckEmail() end

		// 2021-01-23 이소현
		public int selectCheckNickname(String nickname);// selectCheckNickname() end

		// 2021-01-24 이소현
		public int insertJoinMember(Member member);// insertJoinMember() end

		// 2021-01-31 02:23 이소현
		public List<Address> selectUserLocation(int no);// selectCheckEmail() end

		/* ■□■□■□■□■□■□ 0202부터 신규 작성 □■□■□■□■□■□■ */

		// 2021-02-02 13:48 양 프로필 이미지 업데이트
		public int updateProfileImg(Member member);// updateProfileImg() end

		// 2021-02-02 13:49 양 프로필 기본이미지로 업데이트
		public int updateProfileImgDefault(int no);// updateProfileImgDefault() end

		// 2021-02-02 15:11 양 닉네임 변경
		public int updateNickname(Member member);// updateNickname() end
		
		// data for security
		
		// check user data
		public Member selectById(String loginId);
		
		// check user role
		public List<MemberRole> selectRoleById(String email);

		public List<Member> selectPassword();

		public void updatePassword(Member m);
		
		
		
}
