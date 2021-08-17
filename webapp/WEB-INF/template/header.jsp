<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <div id="header">
        <ul>
            <li><a href="" class="menu active" title="장터" href="">장터</a></li>
            <li><a href="/recipeListPage.jsp" class="menu" title="레시피" href="">레시피</a></li>
            <li><a href="/rankingWeekly.jsp" class="menu" title="랭킹" href="">랭킹</a></li>
            <li><a href="" title="장터지기" class="menu" href="">장터지기</a></li>
		<div class="right">
			<a class="circle recipe_regist" title="레시피 작성하기"
				href="/recipeRegisterPage.jsp"><i class="fas fa-pen"></i></a> <span
				class="circle user"><img class="circle_user_img"
				src="resources/img/profileImg/profile.jpg"></span>			
			<a id="login" href="/login.jsp">로그인</a>
		</div>
		</ul>
        <span class="logo"><a href="/ddep/"><img src="resources/img/logo.png"/></a></span>
        
        <div class="right user_menu hidden">
            <div class="user_menu_item"><a href="/mypageHeader.jsp?no=">마이페이지</a></div>
            <div class="user_menu_item"><a href="logout.ktx">로그아웃</a></div>
        </div>
        
    </div><!-- header end-->

