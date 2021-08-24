<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>ANBD 회원가입</title>
   <%@ include file="/WEB-INF/template/link.jsp" %>
   <link rel="stylesheet" href="resources/static/css/signup.css"/>
</head>
<body>
<div id="joinFormWrap">
    <h1><a href="/"><img id="joinFormLogoImg" src="resources/static/img/anbd_resources/logo.png"></a></h1>

    <form>
        <fieldset>
            <legend class="screen_out">회원가입폼</legend>
            <div class="join_form_email_contents">
                <label class="join_form_label" for="email">이메일</label>
                <input id="email" name="email" type="email" placeholder="이메일을 입력하세요."  autocomplete="off" required>
                <div class="email_regex"></div>
            </div><!--.join_form_email_contents end-->

            <div class="join_form_password_contents">
                <label class="join_form_label" for="pw">비밀번호</label>
                <input id="password" name="password" type="password" placeholder="비밀번호를 입력하세요." autocomplete="off" required>
                <div class="pw_regex"></div>
            </div><!--.join_form_password_contents end-->

            <div class="join_form_password_check_contents">
                <label class="join_form_label" for="pwCheck">비밀번호 확인</label>
                <input id="pwCheck" name="pwCheck" type="password" placeholder="비밀번호를 확인해주세요." autocomplete="off" required>
                <div class="pwCheck_regex"></div>
            </div><!--.join_form_password_check_contents end-->

            <div class="join_form_name_contents">
                <label class="join_form_label" for="name">이름</label>
                <input id="name" name="name" placeholder="이름을 입력하세요." autocomplete="off" maxlength="8" required>
                <div class="name_regex"></div>
            </div><!--.join_form_name_contents end-->

            <div class="join_form_nickname_contents">
                <label class="join_form_label" for="nickname">닉네임</label>
                <input id="nickname" name="nickname" maxlength="7" placeholder="닉네임을 입력하세요(최대 7글자)" autocomplete="off" required>
                <div class="nickname_regex"></div>
            </div><!--.join_form_nickname_contents end-->

            <div class="join_form_address_contents">
                <label class="join_form_label">주소찾기</label>
                 <button type="button" onclick="DaumPostcode()" class="join_form_btn" id="findAddressBtn" >
                    <span id="address">주소찾기</span>
                </button><br>
                <input type="hidden" name="sido" id="sido"/>
                <input type="hidden" name="gugun" id="gugun"/>
                <input type="hidden" name="dong" id="dong"/>
                <input type="hidden" name="lat" id="lat"/>
                <input type="hidden" name="lng" id="lng"/>
                <span id="guide" style="color:#999;display:none"></span>
            </div><!--.join_form_address_contents end-->

            <div class="join_form_phone_contents">
                <label class="join_form_label" for="phoneNum">전화번호</label>
                <input name="phoneNumber" id="phoneNumber" class="phone_num" maxlength="13" placeholder="전화번호를 입력해주세요." autocomplete="off" required>
                <button type="button" onclick="getRandomNum()" class="join_form_btn" id="certificationBtn">인증하기</button>
                <div class="phoneNum_regex"></div>
            </div><!--.join_form_phone_contents end-->

            <div class="join_form_certification_contents">
                <label class="join_form_label" for="certification">인증번호 받기</label>
                <input id="certification" placeholder="인증번호를 적어주세요." autocomplete="off" required>
                <span class="certification_regex"></span>
            </div><!--.join_form_certification_contents end-->

        </fieldset>
    </form>
<button type="submit" class="join_form_btn" id="signup-btn" >회원가입</button>
</div>

<script src="resources/static/js/jquery.js"></script>
<!-- 지도 API 라이브러리 -->
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9d5ced193edd27557afd086a710c1ebd&libraries=services"></script> 
<!-- daum 주소 API -->
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="resources/static/js/signup.js"></script>
</body>
</html>