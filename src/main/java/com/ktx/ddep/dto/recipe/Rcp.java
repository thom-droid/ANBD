package com.ktx.ddep.dto.recipe;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Rcp {
	
	private int no,memberNo,rcpsAvg, viewCount,
				scrapCount,//21-02-08 06:38 양 스크랩수 추가
				itemCount;//21-02-09 10:09 양 재료수 추가				
	private String title,img,content,ribbonItem;					
	private Timestamp regdate;
	private char status;
	// 방,김 내가 쓴 레시피에서 내 정보 출력..
    private String nickname, profileImg;
    
    //김
    private int price;
        
    // 0204 방 요리후기 업데이트 위한 생성자
   	public Rcp(int no, int rcpsAvg) {
   		super();
   		this.no = no;
   		this.rcpsAvg = rcpsAvg;
   	}

   	@Builder
	public Rcp(int no, int memberNo, int rcpsAvg, int viewCount, int scrapCount, int itemCount, String title,
			String img, String content, String ribbonItem, Timestamp regdate, char status, String nickname,
			String profileImg, int price) {
		super();
		this.no = no;
		this.memberNo = memberNo;
		this.rcpsAvg = rcpsAvg;
		this.viewCount = viewCount;
		this.scrapCount = scrapCount;
		this.itemCount = itemCount;
		this.title = title;
		this.img = img;
		this.content = content;
		this.ribbonItem = ribbonItem;
		this.regdate = regdate;
		this.status = status;
		this.nickname = nickname;
		this.profileImg = profileImg;
		this.price = price;
	}
	

   	
	
	

}
