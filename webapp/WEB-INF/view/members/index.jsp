<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ANBD :: 아나바다</title>

<%@ include file="/WEB-INF/template/link.jsp" %>

<link rel="stylesheet" href="resources/static/css/paginate.css">
<link rel="stylesheet" href="resources/static/css/index.css">


</head>
<body>

<%@ include file="/WEB-INF/template/header.jsp" %>
<!-- ========================히어로 이미지 + 소개글 part==================================== -->
    <div class="contents_wrap">
        <div class="hero_img_content">
            <img id="heroImg" src="resources/static/img/anbd_resources/hero3.jpg">

            <div class="introduction_wrap">
                <p><span class="introduction_point_text">아</span>까운 재료</p>
                <p>남은 재료는 <span class="introduction_point_text">나</span>눠 갖고</p>
                <p>포인트는 <span class="introduction_point_text">바</span>꿔 쓰고</p>
                <p>요리는 <span class="introduction_point_text">다</span>양하게 해 먹자</p>
            </div><!-- .introduction end-->
        </div><!-- .hero_img_content end-->

    </div><!-- .contents_wrap end-->

    <!-------------------------장터 네임카드 part-------------------------------->
    <div class="market_wrap">
        <button class="btn market prev_btn"><i class="fas fa-chevron-left"></i></button>
        <button class="btn market next_btn"><i class="fas fa-chevron-right"></i></button>

        <div class="market_contents">
            <div class="market_content_head">
                <div class="content_title">장터</div>
                <div class="market view_more" onclick="location.href='/marketList.jsp';">더보기</div>
            </div>

            <div class="market_content_body">

            	<!-- 장터 네임 카드 -->
           	 	<script type="text/template" id="marketCardTmpl">
        		<@ _.each(marketCardList,function(marketCard) { @>
				<div class="market_card">
					<div class="market_card_container"> 
						<input type="hidden" id="marketNo" value="<@=marketCard.MARKETNO@>"/>
               	 		<img class="market_card_img" src="img/marketImg/<@=marketCard.IMG@>"/>
                		<div class="market_card_name"><@=marketCard.NAME@></div>
						<div class="market_card_market_time_wrap">
							<@ _.each(marketCard.TIMES,function(time) { @>
                			<div class="market_card_market_time"><@=time.perfectTime@> </div>
							<@})@>
						</div><!-- .market_card_market_time_wrap end-->
                		<div class="market_card_marketkeeper">
                    		<img src="img/<@=marketCard.PROFILEIMG@>" class="user_profile_img">
                    		<span class="user_nick_name"><@=marketCard.NICKNAME@></span>
                    		<dl class="marketkeeper_service_idx">
                        		<dt class="service_idx_name">서비스 지수</dt>
                        		<dd class="service_idx_value"><@=marketCard.SERVICEIDX@></dd>
					 		</dl><!-- .marketkeeper_service_idx end-->
                		</div><!-- .market_card_marketkeeper end-->  
					</div><!--.market_card_container end--> 	
				</div><!--.market_card_a end-->			
   			<@ })@>             
        	</script>
        	
	 		</div><!-- .market_content_body end-->
        </div><!-- .market_contents end-->
    </div><!-- .market_wrap end-->


	<!-- =========================================레시피 part======================================== -->

    <div class="recipe_wrap">
     	<button class="btn recipe prev_btn"><i class="fas fa-chevron-left"></i></button>
        <button class="btn recipe next_btn"><i class="fas fa-chevron-right"></i></button>
    	<div class="recipe_contents">
	        <div class="recipe_content_head">
	            <div class="content_title">레시피</div>
	            <div class="recipe view_more" onclick="location.href='/recipeListPage.jsp';">더보기</div>
	        </div><!-- .recipe_content_head end-->
	        <div class="recipe_content_body">
	        		
			<%-- <%
				for(Rcp rcp : rcps){
				    //210129 레시피 작성자 정보 불러오기
					Member writer= MembersDAO.selectOne(rcp.getMemberNo());
				    //210130 레시피 요리후기 갯수 불러오기
				    int recipeRv = RcpRvsDAO.countSelectRv(rcp.getNo());
			%> --%>
				<%-- <div class="recipe_box">
					<a href="/recipeDetail.jsp?no=<%=rcp.getNo()%>">
						<img class="recipe_img" src="/img/recipeImg/<%=rcp.getImg() %>" />
						<div class="recipe_title_wrap">					
							<div class="title_area">
								<p class="subtitle">8200p로 만드는</p>
								<h3 class="title"><%=rcp.getTitle() %></h3>
							</div><!--title_area end-->								
							<div class="rate_area">
								<img src="/img/golden_egg.png" />
								<span class="rate_point"><%=rcp.getRcpsAvg() %>%</span>
							</div><!--rate_area end-->
						</div><!--.recipe_title_wrap end-->
					</a>					
					<div class="profile_wrap">
						<div class="profile_area">
							<a href="/mypageHeader.jsp?no=<%=rcp.getMemberNo()%>">
								<img src="/img/profileImg/<%=writer.getProfileImg()%>" class="profile_img"> 
								<span class="profile_name"><%=writer.getNickname() %></span>
							</a>
						</div><!-- profile_area end -->
						<div class="view_area">
							<i class="fas fa-eye"></i><span><%=rcp.getViewCount() %></span>
						</div><!-- view_area end -->
						<div class="scrap_area">
							<i class="fas fa-bookmark"> <%=RcpsSavedDAO.count(rcp.getNo()) %></i>
						</div><!-- scrap_area end -->
					</div><!-- profile_wrap end -->
					<div class="ribbon_wrap">
						<div class="easy ribbon">
						<% if(rcp.getItemCount()<4) { %>
							<img src="img/ribbon4Easy3.png">						
						<% } else if(rcp.getItemCount()<6) { %>
							<img src="img/ribbon5Easy2.png">
						<% } else if(rcp.getItemCount()<8) { %>
							<img src="img/ribbon6Easy1.png">						
						<% } //if~else if~else if end %>
	                     </div><!--easy ribbon end-->
	                 </div><!--ribbon_wrap end-->				
				</div><!-- .recipe_box end -->
				<% } //for end %>
	         --%>
	        </div><!-- .recipe_content_body end-->
        </div><!-- .recipe_contents end-->
    </div><!-- .recipe_wrap end-->

    <!-- ============================================랭킹 part=============================================== -->
    <div class="ranking_wrap">
    	<button class="btn ranking prev_btn"><i class="fas fa-chevron-left"></i></button>
        <button class="btn ranking next_btn"><i class="fas fa-chevron-right"></i></button>
    	<div class="ranking_contents">
	        <div class="ranking_content_head">
	            <div class="content_title">랭킹</div>
	            <div class="ranking view_more" onclick="location.href='/rankingWeekly.jsp';">더보기</div>
	        </div><!-- .ranking_content_head end-->
	        <div class="ranking_content_body">
				
				<%-- <% for (int j = 0; j < lastRanking; j++) {	%>
				<a href="/mypageHeader.jsp?no=<%=members.get(j).getNo()%>">
					<div class="ranking_card">						
						<div class="ranking_num">
							<%=members.get(j).getRankNum()%>
						</div><!-- ranking_num end-->
						<div class="ranking_profile_img">
							<img src="/img/profileImg/<%=members.get(j).getProfileImg()%>"
								alt="<%=members.get(j).getRankNum()%>등">
						</div><!-- ranking_profile_img end-->
						<div class="member_info">
							<span class="member_grade"> <%=members.get(j).gradeImg()%></span>
							 <span class="member_nick"> <%=members.get(j).getNickname()%></span>
							 <span class="member_point"> <%=members.get(j).getSumPoint()%>p</span>							
						</div><!-- member_info end-->
					</div><!-- ranking_card end-->
				</a>
				<% } // for end %>
					 --%>
	        </div><!-- .ranking_content_body end-->
        </div><!-- .ranking_contents end-->
    </div><!-- .ranking_wrap end-->


<%@ include file="/WEB-INF/template/bottom.jsp"%>
<script src="resources/static/js/header.js"></script>
</body>
</html>