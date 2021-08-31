package com.ktx.ddep.dto.recipe;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RcpRv {

	private int no, rcpsOpenNo;
	private String img, content;
	private char rate;
	private Timestamp regdate;

	// 열람레시피 테이블과 조인 위한 멤버필드(후기 작성자 번호로 마이페이지 이동 및 레시피 번호)
	private int reviewerNo, rcpNo;

	// 레시피 정보 출력 위해 레시피 테이블과 조인
	private String rcpTitle, rcpImg, rcpContent;

	// 후기 작성자 정보 얻어오기 위해 회원 테이블과 조인 (내가 쓴 레시피에 달린 후기)
	private String reviewerNickname, reviewerProfileImg;

	// 레시피 작성자 정보 얻어오기 (내가 쓴 요리후기)
	private String writerNickname, writerProfileImg;

	// 0202 방 wrtierNo가 String으로 선언되어 있어서 int로 바꿈
	private int writerNo;

	// 0202 방 요리후기 쓰기 위한 레시피 정보를 불러오기 위해 멤버필드 선언.
	// 굳이 안만들어도 되지만 의미 전달을 명확히 하기 위해서 다 만듬
	private int openedRcpNo, memberNo;

	// 0202 방 위 내용 해당하는 생성자 오버로딩
	@Builder
	public RcpRv(int openedRcpNo, int memberNo) {
		super();
		this.openedRcpNo = openedRcpNo;
		this.memberNo = memberNo;
	}


}
