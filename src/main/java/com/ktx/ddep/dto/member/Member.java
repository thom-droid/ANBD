package com.ktx.ddep.dto.member;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;

import com.ktx.ddep.security.UserEntity;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Member {
	private int no, currPoints, accuPoints, addressNo, loginCount, /* 210122 양 랭킹 순위 */ rankNum,
			/* 210126 양 주간 랭킹 합산포인트 */sumPoint;

	private String email, password, name, nickname, phoneNumber, profileImg,sido, gugun, dong;
	private char marketkeeperStep, warning, tutorial, grade, rcpWarning;
	private Date startDay;
	private Timestamp regdate;
	private Double lat, lng;
	private int nowYear;
	private Calendar calendar;

	public Calendar getCalendar() {
		return calendar;
	}

	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
	}

	public int getNowYear() {
		return nowYear;
	}

	public void setNowYear(int nowYear) {
		this.nowYear = nowYear;
	}

	public int getRankNum() {
		return rankNum;
	}

	public char getRcpWarning() {
		return rcpWarning;
	}

	public void setRcpWarning(char rcpWarning) {
		this.rcpWarning = rcpWarning;
	}

	public void setRankNum(int rankNum) {
		this.rankNum = rankNum;
	}

	public char getMarketkeeperStep() {
		return marketkeeperStep;
	}

	public void setMarketkeeperStep(char marketkeeperStep) {
		this.marketkeeperStep = marketkeeperStep;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getCurrPoints() {
		return currPoints;
	}

	public void setCurrPoints(int currPoints) {
		this.currPoints = currPoints;
	}

	public int getAccuPoints() {
		return accuPoints;
	}

	public void setAccuPoints(int accuPoints) {
		this.accuPoints = accuPoints;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(char grade) {
		this.grade = grade;
	}

	public int getAddressNo() {
		return addressNo;
	}

	public void setAddressNo(int addressNo) {
		this.addressNo = addressNo;
	}

	public int getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(int loginCount) {
		this.loginCount = loginCount;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getProfileImg() {
		return profileImg;
	}

	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}

	public char getMarketkepperStep() {
		return marketkeeperStep;
	}

	public void setMarketkepperStep(char marketkepperStep) {
		this.marketkeeperStep = marketkepperStep;
	}

	public char getWarning() {
		return warning;
	}

	public void setWarning(char warning) {
		this.warning = warning;
	}

	public char getTutorial() {
		return tutorial;
	}

	public void setTutorial(char tutorial) {
		this.tutorial = tutorial;
	}

	public Date getStartDay() {
		return startDay;
	}

	// 2021-01-24 이소현
	public int getYear() {
		return calendar.get(Calendar.YEAR);
	}

	// 2021-01-24 이소현
	public int getMonth() {
		return calendar.get(Calendar.MONTH) + 1;
	}

	// 2021-01-24 이소현
	public int getDate() {
		return calendar.get(Calendar.DATE);
	}

	public void setStartDay(Date startDay) {
		this.startDay = startDay;
	}

	public Timestamp getRegdate() {
		return regdate;
	}

	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}

	public int getSumPoint() {
		return sumPoint;
	}

	public void setSumPoint(int sumPoint) {
		this.sumPoint = sumPoint;
	}
	
	

	public String getSido() {
		return sido;
	}

	public void setSido(String sido) {
		this.sido = sido;
	}

	public String getGugun() {
		return gugun;
	}

	public void setGugun(String gugun) {
		this.gugun = gugun;
	}

	public String getDong() {
		return dong;
	}

	public void setDong(String dong) {
		this.dong = dong;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	// 210122 양 등급 이미지를 불러오는 함수
	public String gradeImg() {

		String iconName = "";

		// 210125 양 등급 char로 바뀌면 사용
		if (grade == 'f') {
			iconName = "Family";
		} else if (grade == 's') {
			iconName = "Silver";
		} else if (grade == 'g') {
			iconName = "Gold";
		} else if (grade == 'p') {
			iconName = "Platinum";
		} else if (grade == 'd') {
			iconName = "Diamond";
		} else if (grade == 'h') {
			iconName = "Heritage";
		} else if (grade == 'r') {
			iconName = "Royal";
		}

		return "<img src=\"img/icon" + iconName + "Small.png\" alt=\"" + iconName + "\">";

	}

	// 210125 양 전체 랭킹 등급 이미지를 불러오는 함수
	public String gradeBigImg() {

		String iconName = "";

		// 210125 양 등급 char로 바뀌면 사용
		if (grade == 'f') {
			iconName = "Family";
		} else if (grade == 's') {
			iconName = "Silver";
		} else if (grade == 'g') {
			iconName = "Gold";
		} else if (grade == 'p') {
			iconName = "Platinum";
		} else if (grade == 'd') {
			iconName = "Diamond";
		} else if (grade == 'h') {
			iconName = "Heritage";
		} else if (grade == 'r') {
			iconName = "Royal";
		}

		return "<img src=\"img/icon" + iconName + ".png\" alt=\"" + iconName + "\">";

	}
	
	// return as entity
	public UserEntity toEntity() {
		return UserEntity.builder()
				.loginUserId(email)
				.password(password)
				.build();
	}

	// constructor with no args but with calendar instance
	public Member() {
		calendar = Calendar.getInstance();
		nowYear = calendar.get(Calendar.YEAR);
	}
	
	// constructor for sign up
	@Builder
	public Member(int addressNo, String email, String password, String name, String nickname, String phoneNumber) {
		this.addressNo = addressNo;
		this.email = email;
		this.password = password;
		this.name = name;
		this.nickname = nickname;
		this.phoneNumber = phoneNumber;
	}
	
}
