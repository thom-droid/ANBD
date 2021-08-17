<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>로그인페이지</title>
    <link rel="stylesheet" href="resources/static/css/login.css" />
    <%@ include file="/WEB-INF/template/link.jsp" %>
        
</head>
<body>
<div id="contents">
    <div id="logo">
        <img src="resources/static/img/anbd_resources/logo.png"/>
    </div>
    <form action="/authenticate" method="post">
    	<!-- msg가 있으면 출력 -->
        <p id="resultLogin"></p>
        <p id="loginId">
            <input type="text" id="id" name="email" placeholder="ktganzi@gmail.com"/><br/>
            <span class="check_id check"></span>
        </p>
        <p>
            <input id="password" name="password" placeholder="비밀번호를 입력해주세요"/><br />
            <span class="check_pass check"></span>
        </p>
        <p class="check">
        	
        </p>
        <p class="login" >
            <span id="logIng"><input type="checkbox" id="logging"/>로그인 상태 유지</span>
            <a href="passwordSearch.html">비밀번호 찾기</a>
        </p>
        
        
        <p class="btn"  >
            <button id="logIn" type="submit" class="btn btn-info" >로그인</button>
        </p>
        <p class="btn" >
            <button id="register" type="submit" class="btn btn-info" >회원가입</button>
        </p>
    </form>
</div>
<script src="resources/static/js/jquery.js"></script>
<script src="resources/static/js/login.js"></script> 
</body>
</html>