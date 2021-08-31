package com.ktx.ddep.dto.member;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Address {

	private int no;
	private String sido, gugun, dong, detailAddress;
	private Double lat, lng;
	private Timestamp regdate;


	public void setNo(int no) {
		this.no = no;
	}

	public void setSido(String sido) {
		this.sido = sido;
	}


	public void setGugun(String gugun) {
		this.gugun = gugun;
	}



	public void setDong(String dong) {
		this.dong = dong;
	}


	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}


	public void setLat(Double lat) {
		this.lat = lat;
	}


	public void setLng(Double lng) {
		this.lng = lng;
	}


	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}
	
	//210127 양 전체 주소 불러오기 함수
	public String getTotalAddress(){
		//21-02-04 gugun이 null일 경우 추가 
		if(getGugun()!=null) {
			return getSido()+" "+getGugun()+" "+getDong()+" "+getDetailAddress();
		}else {
			return getSido()+" "+getDong()+" "+getDetailAddress();
		}//if else end
	}
	
	// sign up
	@Builder
	public Address(String sido, String gugun, String dong, Double lat, Double lng) {
		this.sido = sido;
		this.gugun = gugun;
		this.dong = dong;
		this.lat = lat;
		this.lng = lng;
	}
	
	
	// used for getDong
	@Builder
	public Address(String sido, String gugun) {
		this.sido = sido;
		this.gugun = gugun;
	}
	
	// used for getMarketList
	@Builder
	public Address(String sido, String gugun, String dong) {
		this.sido = sido;
		this.gugun = gugun;
		this.dong = dong;
	}
	
	
}//Address end
