<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>마이페이지</title>
    <%@ include file="/WEB-INF/template/link.jsp" %>
    <link rel="stylesheet" href="resources/static/css/mypage.css">
    <link rel="stylesheet" href="resources/static/css/mypageRecipe.css">

</head>
<body>
<!--■■■■■■■■■■■■■■■■■■■■■■■■■■----mark up start---■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■-->
<%@ include file="/WEB-INF/template/header.jsp"%>

<div class="mypage_wrap">
    <div id="mypage">
        <div class="profile_wrap" >
            <div class="profile_img_area">
                    <div class="profile_img_and_label">
                        <img id="profileImage" class="profile_img_big" src="resources/static/img/members/profile/${sessionScope.loggedInUser.profileImg}"/>
                        <label class="profile_img_label" for="profileImg">이미지 변경</label>
                        <input type="file" accept="image/*" id="profileImg" class="filter_radio" />
                        <label class="set_default_img">기본 이미지</label>
                    </div>
            </div>
            <div class="user_info_area">
            	<c:if test="${sessionScope.loggedInUser.marketkeeperStep eq 109}">
                <div class="space marketkeeper">
                	<p>장터지기</p>
                </div>
                </c:if>
                <div class="nickname_title">
                    <h3>${sessionScope.loggedInUser.nickname }</h3>
                </div>
                <div class="user_addr">
                    <div id="userAddress">
                        <h3 id="roadAddress" onclick="sample4_execDaumPostcode()" class="hover"> ${addr.sido} ${addr.gugun} ${addr.dong} </h3>
                    </div>
                </div>
                <div class="point_and_rank">
                    <div class="user_curr_points">
                        <p> ${memberRank.currPoints } p</p>
                    </div>
                    <div class="user_rank">
                        <p> ${memberRank.rankNum } 위</p>
                    </div>
                </div>
            </div>
            
        </div><!--profile_wrap end-->


    </div><!--mypage end-->
	<ul id="mypageTab">
	    <li class="mypage_tab receipt_tab">
	        <i class="fas fa-receipt"></i><em class="tab_click">레시피</em><h4></h4>
	    </li>
	    <li class="mypage_tab market_tab">
	        <i class="fas fa-store"></i><em>장터</em><h4></h4>
	    </li>
	    <li class="mypage_tab review_tab">
	        <i class="fas fa-comment-alt"></i><em>요리후기</em><h4></h4>
	    </li>
	    <li class="mypage_tab question_tab">
	        <i class="fas fa-headset"></i><em>1:1 문의</em><h4></h4>
	    </li>
	    <li class="tab_under_bar"></li>
	</ul><!--mypageTab-->




</div><!-- mypage_wrap end-->




<!--■■■■■■■■■■■■■■■■■■■■■■■■■등급박스■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■-->

<div class="grade_info_box box hidden">
    <div class="close_btn"><i class="fas fa-times"></i></div>
    <h2>등급이란?</h2>
    <h3>누적 포인트에 따라 등급이 결정됩니다.</h3>
    <h3>등급을 높히고 추가 적립 혜택을 누리세요!</h3>


    <div id="grade_info_wrap">

        <div class="grade_info">
            <div class="grade_icon">
                <img src="resources/static/img/anbd_resources/ranks/iconRoyal.png" alt="royal">
            </div><!--grade_icon end-->

            <div class="grade_detail two_line">
                <p>400,001p~</p>
                <p>추가 적립 10%</p>
            </div><!-- grade_detatil end-->
        </div><!-- grade_info end-->

        <div class="grade_info">
            <div class="grade_icon">
                <img src="resources/static/img/anbd_resources/ranks/iconHeritage.png" alt="heritage">
            </div><!--grade_icon end-->

            <div class="grade_detail two_line">
                <p>150,001p~400,000p</p>
                <p>추가 적립 7%</p>
            </div><!-- grade_detatil end-->
        </div><!-- grade_info end-->

        <div class="grade_info">
            <div class="grade_icon">
                <img src="resources/static/img/anbd_resources/ranks/iconDiamond.png" alt="diamond">
            </div><!--grade_icon end-->

            <div class="grade_detail two_line">
                <p>70,001p ~150,000p</p>
                <p>추가 적립 5%</p>
            </div><!-- grade_detatil end-->
        </div><!-- grade_info end-->

        <div class="grade_info">
            <div class="grade_icon">
                <img src="resources/static/img/anbd_resources/ranks/iconPlatinum.png" alt="platinum">
            </div><!--grade_icon end-->

            <div class="grade_detail two_line">
                <p>30,001p ~70,000p</p>
                <p>추가 적립 3%</p>
            </div><!-- grade_detatil end-->
        </div><!-- grade_info end-->

        <div class="grade_info">
            <div class="grade_icon">
                <img src="resources/static/img/anbd_resources/ranks/iconGold.png" alt="gold">
            </div><!--grade_icon end-->

            <div class="grade_detail one_line">
                <p>10,001p ~30,000p</p>
            </div><!-- grade_detatil end-->
        </div><!-- grade_info end-->

        <div class="grade_info">
            <div class="grade_icon">
                <img src="resources/static/img/anbd_resources/ranks/iconSilver.png" alt="silver">
            </div><!--grade_icon end-->

            <div class="grade_detail one_line">
                <p>5,001p ~10,000p</p>
            </div><!-- grade_detatil end-->
        </div><!-- grade_info end-->

        <div class="grade_info">
            <div class="grade_icon">
                <img src="resources/static/img/anbd_resources/ranks/iconFamily.png" alt="family">
            </div><!--grade_icon end-->

            <div class="grade_detail one_line">
                <p>회원가입시 ~ 5,000p</p>
            </div><!-- point_detatil end-->
        </div><!-- point_use end-->



    </div><!--grade_info_wrap end-->

</div><!-- grade_info_box end -->

<!--■■■■■■■■■■■■■■■■■■■■■■■■■등급박스 end■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■-->



<!--■■■■■■■■■■■■■■■■■■■■■■■■■포인트내역박스 ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■-->

<div class="point_history_box box hidden">
    <div class="close_btn"><i class="fas fa-times"></i></div>
    <h2>포인트 적립/사용내역</h2>
    <h3>포인트 내역은 최근 1년까지만 조회 가능합니다.</h3>
    <div class="point_now">
        <span>보유 포인트 : </span>
        <span></span>
        <span>p</span>
    </div><!--point_now-->


    <div class="point_tab">
        <label for="pointAccumulationTab"><!--
            --><input type="radio" checked id="pointAccumulationTab" class="filter_radio" name="ranking"/><!--
            --><span class="point_accumulation_tab">적립 내역</span><!--
        --></label><!--

        --><label for="pointUseTab"><!--
            --><input type="radio" id="pointUseTab" class="filter_radio" name="ranking"/><!--
            --><span class="point_use_tab on">사용 내역</span>
    </label>
    </div><!--point_tab end-->

    <!-- point_tab end-->

    <div class="point_history_wrap">
        <ul class="point_history_menu">
            <li>날짜</li>
            <li>포인트</li>
            <li>내용</li>
        </ul>
        <div class="line"></div>


        <div class="point_history_accumulation">
            <ul class="point_history_data">
                <li>2020. 12. 11(금) 17:21</li>
                <li>52p</li>
                <li>출석</li>
            </ul>
            <ul class="point_history_data">
                <li>2020. 12. 10(목) 17:21</li>
                <li>52p</li>
                <li>출석</li>
            </ul>
            <ul class="point_history_data">
                <li>2020. 12. 10(금) 17:21</li>
                <li>52p</li>
                <li>출석</li>
            </ul>
            <ul class="point_history_data">
                <li>2020. 12. 09(목) 20:21</li>
                <li>52p</li>
                <li>출석</li>
            </ul>
            <ul class="point_history_data">
                <li>2020. 12. 09(목) 08:13</li>
                <li>52p</li>
                <li>출석</li>
            </ul>

        </div><!--point_history_accumulation end-->


        <div  class="point_history_use hidden">
            <ul class="point_history_data">
                <li>2020. 12. 11(금) 17:21</li>
                <li>52p</li>
                <li>출석</li>
            </ul>
            <ul class="point_history_data">
                <li>2020. 12. 11(금) 17:21</li>
                <li>52p</li>
                <li>출석</li>
            </ul>
            <ul class="point_history_data">
                <li>2020. 12. 11(금) 17:21</li>
                <li>52p</li>
                <li>출석</li>
            </ul>
            <ul class="point_history_data">
                <li>2020. 12. 11(금) 17:21</li>
                <li>52p</li>
                <li>출석</li>
            </ul>
            <ul class="point_history_data">
                <li>2020. 12. 11(금) 17:21</li>
                <li>52p</li>
                <li>출석</li>
            </ul>
        </div><!--#pointHistoryUse end-->

        <div class="paginate_box">
            <ul class="paginate">
                <a href="" class="link_page link_prev"><</a>

                <a href="" class="link_page on"><span>1</span></a>
                <a href="" class="link_page"><span>2</span></a>
                <a href="" class="link_page"><span>3</span></a>
                <a href="" class="link_page"><span>4</span></a>
                <a href="" class="link_page"><span>5</span></a>
                <a href="" class="link_page"><span>6</span></a>
                <a href="" class="link_page"><span>7</span></a>
                <a href="" class="link_page"><span>8</span></a>
                <a href="" class="link_page"><span>9</span></a>
                <a href="" class="link_page"><span>10</span></a>

                <a href="" class="link_page link_next ">> </a>
            </ul>
        </div>

    </div><!--point_history_wrap end-->
</div><!--point_history end-->

<!--■■■■■■■■■■■■■■■■■■■■■■■■■포인트내역박스 end■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■-->


<!--■■■■■■■■■■■■■■■■■■■■■■■■■서비스 지수 박스■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■-->

<!-- 마켓 정보가 있고 서비스 지수가 있으면 서비스 지수 박스 보여줌 -->
                	
<div class="service_idx_box box hidden">
    <div class="close_btn"><i class="fas fa-times"></i></div>
    <div class="profile_wrap">
        <div class="profile_img">
            <img class="profile_img_big" src="resources/static/img/profileImg/profile.jpg"/>
        </div>
        <div class="user_info_wrap">

            <div class="user_info">
                <h4 class="space marketkeeper">장터지기</h4>
                <em class="user_nickname"></em>
            </div><!--nickname end-->
            <span class="service_idx"></span>




        </div><!--userinfo_wrap end-->
    </div><!--profile_wrap-->

    <div class="market_card">
        <a href="" class="market_card_a">
            <img class="market_card_img" src="resources/static/img/marketImg/market.jpg" />
            <div class="market_info">
                <div class="market_card_name"></div>
                <div class="market_card_market_time">                
                 </div>
            </div>
        </a><!--.market_card_a end-->
    </div><!-- .market_card end-->

    <div class="star_rate_wrap">
        <div class="star_rate_lv5">
            <div class="grade_star star_rate">
                <div class="inner_star star_rate" style="width: 100%" ></div>
            </div>
        </div>

        <div class="star_rate_lv4">
            <div class="grade_star star_rate">
                <div class="inner_star star_rate" style="width: 80%"></div>
            </div>
        </div>

        <div class="star_rate_lv3">
            <div class="grade_star star_rate">
                <div class="inner_star star_rate" style="width: 60%"></div>
            </div>
        </div>

        <div class="star_rate_lv2">
            <div class="grade_star star_rate">
                <div class="inner_star star_rate" style="width: 40%"></div>
            </div>
        </div>

        <div class="star_rate_lv1">
            <div class="grade_star star_rate">
                <div class="inner_star star_rate" style="width: 20%"></div>
            </div>
        </div>
    </div><!--star_rate_wrap end-->

    <div class="star_idx_wrap">
        <div class="star_idx_lv5 star_idx">
            <div class="star_graph" style="width: 20%"></div>
            <span class="star_idx_num">88</span>
        </div>
        <div class="star_idx_lv4 star_idx">
            <div class="star_graph"></div>
            <span class="star_idx_num">62</span>
        </div>
        <div class="star_idx_lv3 star_idx">
            <div class="star_graph"></div>
            <span class="star_idx_num">67</span>
        </div>
        <div class="star_idx_lv2 star_idx">
            <div class="star_graph"></div>
            <span class="star_idx_num">22</span>
        </div>
        <div class="star_idx_lv1 star_idx">
            <div class="star_graph"></div>
            <span class="star_idx_num">3</span>
        </div>
    </div>

</div><!--star_rate_box end-->


<!--mypage content 영억 start-->


<div id="mypageContent">
    <div id="mypageReceipt" class="hidden"></div>
    <div id="mypageMarket" class="hidden"></div>
    <div id="mypageReview" class="hidden"> </div>
    <div id="mypageQuestion" class="hidden"> </div>

</div><!--mypageContent end-->


<div id=layerWrap class=hidden></div>

<div id="mypageRecipeWrap">
    <!--탭 안의 탭-->
    <div id="mypageRecipeTabNavbar">
        <ul>
            <li class="myrecipe tab_list on">
                <div>
                    <span class="tab_icon"><i class="far fa-pause-circle"></i></span>
                    <span class="tab_title">내가 쓴 레시피</span>
                    <span class="trade_wait_num tab_num">10</span>
                </div>
            </li>
            <li class="tmp_recipe tab_list">
                <div>
                    <span class="tab_icon"><i class="far fa-check-circle"></i></span>
                    <span class="tab_title">임시저장 레시피</span>
                    <span class="trade_complete_num tab_num">1</span>
                </div>
            </li>
            <li class="open_recipe tab_list">
                <div>
                    <span class="tab_icon"><i class="far fa-laugh"></i></span>
                    <span class="tab_title">열람 레시피</span>
                    <span class="confirm_wait tab_num">10</span>
                </div>
            </li>
            <li class="saved_recipe tab_list">
                <div>
                    <span class="tab_icon"><i class="far fa-laugh"></i></span>
                    <span class="tab_title">저장한 레시피</span>
                    <span class="confirm_wait tab_num">10</span>
                </div>
            </li>
        </ul>
    </div> <!--#tabNavbar end-->

    <div class="mypage_recipe_tab_header">
        <div class="search_form">
            <input class="search_input" type="text" name="mypageRecipeSearch"/>
        </div>
        <div class="align_filter_area">
            <input id="alignFilterByTime" type="radio" name="alignFilter" value="alignFilterByTime" /><label class="filter_by_time" for="alignFilterByTime">최신순</label>
            <input id="alignFilterByView" type="radio" name="alignFilter" value="alignFilterByView"/><label class="filter_by_view" for="alignFilterByView">조회순</label>
        </div>
    </div><!--myrecipe_tab_header-->

    <div class="mypage_recipe_tab_contents">

        <div class="mypage_myrecipe_tab tab_wrap on">
            <ul>
    
            </ul>

            <div class="view_more_btn_area">
                <button class="view_more_btn"type="button">더보기<i class="fas fa-caret-down"></i></button>
            </div>
        </div><!--mypage_myrecipe_tab tab_wrap-->

        <div class="mypage_tmp_recipe_tab tab_wrap">
            <ul>
            	
			</ul>

            <div class="view_more_btn_area">
                <button class="view_more_btn"type="button">더보기<i class="fas fa-caret-down"></i></button>
            </div>
        </div><!--mypage_tmp_recipe-->

        <div class="mypage_opened_recipe_tab tab_wrap">
            <ul>
                
            </ul>
            <div class="view_more_btn_area">
                <button class="view_more_btn"type="button">더보기<i class="fas fa-caret-down"></i></button>
            </div>
        </div><!--mypage_opened_recipe-->

        <div class="mypage_saved_recipe_tab tab_wrap">
            <ul>

            </ul>
            <div class="view_more_btn_area">
                <button class="view_more_btn"type="button">더보기<i class="fas fa-caret-down"></i></button>
            </div>
        </div><!--mypage_saved_recipe-->

    </div><!--mypage_recipe_tab_contents-->


<!--=============================== 내가쓴 레시피 템플릿=============================  -->
<script type="text/template" id="myRcpsTmpl">
<@_.each(jsons, function(myRcp) { @>
                   	 <li>
                        <div class="box">
                            <a href="/recipeDetailPage?no=<@=myRcp.no@>">
                                <img src="resources/static/img/recipes/recipes/<@=myRcp.img@>" />
                                <div class="title_rate">
                                    <div class="title_area">
                                            <p class="subtitle">12900p로 만드는</p>
                                            <h3 class="title"><@=myRcp.title@></h3>
                                    </div> <!--title_area end-->
							</a>
                                    <div class="rate_area">
                                        <img src="resources/static/img/recipes/golden_egg.png" />
                                        <span class="rate_point"> 72%</span>
                                    </div> <!--rate_area end-->
                                </div> <!--title_rate end-->

                                    <div class="profile">
                                        <div class="profile_area">
                                            <img src="resources/static/img/members/profile/<@=myRcp.profileImg@>" class="profile_img">
                                            <span class="profile_name"><@=myRcp.nickname@></span>
                                        </div><!-- profile_area end -->
                                        <div class="recipe_area">
                                            <i class="fas fa-eye"></i><span> 5.8k</span>
                                            <i class="fas fa-comment-alt"></i><span> 136</span>
                                        </div><!-- recipe_area end -->
                                    </div><!-- profile end -->
                                    </a>
                                    <div class="search_result">
                                        <span class="result_area">돼지고기</span>
                                        <span class="result_area">김치찌개</span>
                                    </div><!-- search_result end -->
                        </div><!-- box end -->
                    </li>
<@ }) @>
</script>

<!-- ==================================== 임저레 ==================================  -->
<script type="text/template" id="tmpRcpsTmpl">

<@_.each(tmpRcps, function(tmpRcp){ @>
				<li>
						<div class="box">
							<input type="hidden" class="tmp_rcp_no" name="tmpRcpNo" value="<@=tmpRcp.no@>" />
							<img src="resources/static/img/recipes/recipes/<@=tmpRcp.img@>" />
								<div class="title_rate">
									<div class="title_area">
										<p class="subtitle"></p>
										<h3 class="title"><@=tmpRcp.title@></h3>
									</div>
									<!--title_area end-->

									<div class="rate_area">
										<img src="resources/static/img/recipes/golden_egg.png" /> <span class="rate_point"></span>
									</div>
									<!--rate_area end-->
								</div> <!--title_rate end-->
							<div class="profile">
								<div class="profile_area">

									<span class="profile_name"></span>
								</div>
								<!-- profile_area end -->
								<!-- recipe_area end -->
							</div>
							<!-- profile end -->
							<div class="edit_btn_area">
								<div class="btn_wrap">
									<a href="/recipeRegisterPage.jsp">
									<button type="button" class="edit_btn">
										수정
									</button>
									</a>		
									<button type="button" class="delete_btn">삭제</button>
								</div>
							</div>
							<!-- search_result end -->
						</div>
						<!-- box end -->
					</li>
<@ }) @>
</script>

<!-- ==================================== 열람레시피 ==================================  -->
<script type="text/template" id="openedRcpsTmpl">
<@_.each(openedRcps, function(openedRcp){ @>
			<li>
                    <div class="box">
						<input type="hidden" class="opened_rcp_no" name="openedRcpNo" value="<@=openedRcp.rcpNo@>"
                        <a href="/recipeDetailPage?no=<@=openedRcp.rcpNo@>">
                            <img src="/img/recipeImg/<@=openedRcp.rcpImg@>" width="230" height="180"/>
                            <div class="title_rate">
                                <div class="title_area">
                                    <p class="subtitle">12900p로 만드는</p>
                                    <h3 class="title"><@=openedRcp.rcpTitle@></h3>
                                </div> <!--title_area end-->
                                <div class="rate_area">
                                    <img src="img/golden_egg.png" />
                                    <span class="rate_point"> 72%</span>
                                </div> <!--rate_area end-->
                            </div> <!--title_rate end-->

                            <a href="">
                                <div class="profile">
                                    <div class="profile_area">
                                        <img src="/img/<@=openedRcp.writerProfileImg@>" class="profile_img">
                                        <span class="profile_name"><@=openedRcp.writerNickname@></span>
                                    </div><!-- profile_area end -->
                                    <div class="recipe_area">
                                        <i class="fas fa-eye"></i><span> 5.8k</span>
                                        <i class="fas fa-comment-alt"></i><span> 136</span>
                                    </div><!-- recipe_area end -->
                                </div><!-- profile end -->
                            </a>
                            <div class="recipe_review_area">
                                <button type="button" class="recipe_review_btn">요리후기작성</button>
                            </div><!-- recipe_review_area -->
                        </a>
                    </div><!-- box end -->
                </li>
<@}) @>
</script>

<!-- =============================== 요리후기 작성 템플릿 =================================== -->
    
<script type="text/template" id="rvFormTmpl">
    <div class="review_popup_overlay">
        <div class="review_popup_wrap">
            <div><button type="button" class="review_popup_close_btn"><i class="fas fa-times"></i></button></div>
            <div class="review_popup_header">
                <div class="recipe_info">
                    <a href="">
                        <img src="/img/recipeImg/<@=openedRcp.rcpImg@>" width="90" height="70"/>
                        <div class="recipe_info_text">
                            <p>5,900원으로 만드는</p>
                            <p><span><@=openedRcp.rcpTitle@></span></p>
                            <div class="review_profile_area">
                                <a href="">
                                    <img class="profile_img" src="/img/profileImg/<@=openedRcp.writerProfileImg@>" /> <span><@=openedRcp.writerNickname@></span>
                                </a>
                            </div><!--review_profile_area-->
                        </div> <!--recipe_info_text-->
                    </a>
                </div>
                <div class="like_dislike_area">
                    <div class="like_area">
						<input id="rvRateLike" type="radio" name="rate" value='l' />
						<label for="rvRateLike" class="rv_rate_label">
                        	<img src="/img/golden_egg.png" width="65" height="55" />
                        	<p>좋아요</p>
						</label>
                    </div>
                    <div class="dislike_area">
						<input id="rvRateDislike" type="radio" name="rate" value='d' />
						<label for="rvRateDislike" class="rv_rate_label">
                        	<img src="/img/broken_egg.png" width="65" height="55" />
                        	<p>싫어요</p>
						</label>
                    </div>
                </div>
            </div> <!--review_popup_header-->
            <div class="review_popup_content">
                <div class="img_upload_area">
                    <div class="review_img_upload_btn">
						<label class="rv_img_upload_label">
							<input class="rv_img_upload" type="file" accept="image/*" />
							<input class="rv_img_upload_input" name="rvImg" type="hidden" />
							<i class="fas fa-plus"></i>
						</label>
                    </div>
                </div><!--upload_area-->
                <textarea class="review_content" maxlength="50" name="reviewContent" placeholder="솔직한 후기를 작성해주세요"></textarea>
				<span class="rv_txt"><span class="rv_txt_count">0</span>/50자</span>
            </div>
            <div class="review_popup_footer">
                <button class="review_submit_btn">등록</button>
                <button class="review_close_btn">취소</button>
            </div>
        </div> <!--review_popup_wrap-->
    </div><!--review_popup_overlay-->
</div><!--#pageWrap end-->
</script>

<!-- =============================== 스크랩한 레시피 목록 =================================== -->
<script type="text/template" id="savedRcpTmpl">
	<@ _.each (savedRcps, function(savedRcp){ @>
                <li>
                    <div class="box">
						<input type="hidden" class="saved_rcp_no" name="savedRcpNo" value="<@=savedRcp.no@>"/>
                        <a href="recipeDetail?no=<@=savedRcp.no@>">
                            <img src="img/<@=savedRcp.img@>" />
                            <div class="title_rate">
                                <div class="title_area">
                                    <p class="subtitle">12900원으로 만드는</p>
                                    <h3 class="title"><@=savedRcp.title@></h3>
                                </div> <!--title_area end-->
                                <div class="rate_area">
                                    <img src="img/golden_egg.png" />
                                    <span class="rate_point"> <@=savedRcp.rcpsAvg@></span>
                                </div> <!--rate_area end-->
                            </div> <!--title_rate end-->

                            <a href="">
                                <div class="profile">
                                    <div class="profile_area">
                                        <img src="img/<@=savedRcp.profileImg@>" class="profile_img">
                                        <span class="profile_name"><@=savedRcp.nickname@></span>
                                    </div><!-- profile_area end -->
                                    <div class="recipe_area">
                                        <i class="fas fa-eye"></i><span> <@=savedRcp.viewCount@></span>
                                        <i class="fas fa-comment-alt"></i><span> <@=savedRcp.scrapCount@></span>
                                    </div><!-- recipe_area end -->
                                </div><!-- profile end -->
                            </a>
                            <div class="saved_recipe_delete_area">
                                <button type="button" class="saved_recipe_delete_btn">삭제</button>
                            </div><!--  saved_recipe_delete_btn end -->
                        </a>
                    </div><!-- box end -->
                </li>
	<@ }) @>
</script>


<script src="resources/static/js/jquery.js"></script>
<script src="resources/static/js/underscore-min.js"></script>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9d5ced193edd27557afd086a710c1ebd&libraries=services"></script>
<script src="resources/static/js/header.js"></script>
<script src="resources/static/js/mypage.js"></script>
<script src="resources/static/js/mypage_recipe.js"></script>
</body>
</html>