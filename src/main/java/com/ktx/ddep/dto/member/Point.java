package com.ktx.ddep.dto.member;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Point {

	private int no, memberNo, pointAmount, referenceKey,
				/*210125 양 주간랭킹 처리를 위해 추가함*/sumPoint,rankNum;
	private Date accuUseDate, 
	/* 210124 양 주간 랭킹 처리를 위해 추가함   */startDate, endDate;

	private Timestamp regdate;
	private char pointType, pointsStatus;
	
	
	// 0202 방 요리후기 포인트 적립 - 생성자 오버라이딩
	public Point(int memberNo, int referenceKey) {
		super();
		this.memberNo = memberNo;
		this.referenceKey = referenceKey;
	}
	
	public Point(String startDate ,String endDate) {
		this.startDate =Date.valueOf(startDate);
		this.endDate =Date.valueOf(endDate);
	}
}
