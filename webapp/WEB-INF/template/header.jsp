<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <div id="header">
        <ul>
            <li><a href="" class="menu active" title="장터" href="">장터</a></li>
            <li><a href="/recipeListPage.jsp" class="menu" title="레시피" href="">레시피</a></li>
            <li><a href="/rankingWeekly.jsp" class="menu" title="랭킹" href="">랭킹</a></li>
            <li><a href="" title="장터지기" class="menu" href="">장터지기</a></li>
		<div class="right">
			
		<c:choose>		
		
		<c:when test="${sessionScope.loggedInUser eq null }">
				<a id="login" href="/login">로그인</a>
		</c:when>
		<c:otherwise>
				<a class="circle recipe_regist" title="레시피 작성하기"
				href="/recipeRegisterPage.jsp"><i class="fas fa-pen"></i></a> <span
				class="circle user"><img class="circle_user_img"
				src="resources/static/img/profileImg/profile.jpg"></span>
		</c:otherwise>		

		</c:choose>
		</div>
		</ul>
        <span class="logo"><a href="/"><img src="resources/static/img/anbd_resources/logo.png"/></a></span>
        
        <div class="right user_menu hidden">
        <c:if test="${sessionScope.loggedInUser ne null }" >
        	<div class="user_profile">${sessionScope.loggedInUser.name} 님</div>
        </c:if>
            <div class="user_menu_item"><a href="/mypage">마이페이지</a></div>
            <div class="user_menu_item"><a href="/logout">로그아웃</a></div>
        </div>
        
    </div><!-- header end-->

