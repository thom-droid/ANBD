<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="mypage_wrap">
    <div id="mypage">
        <div class="profile_wrap" >

            <label for="profileImg">
                <input type="file" accept="image/*" id="profileImg" class="filter_radio" onchange="previewImage(this);" />
                <div class="profile_img">
                    <img id="profileImage" class="profile_img_big" src="img/profile_img_default.jpg"/>
                </div>
            </label>
            <div class="close_btn hidden"><i class="fas fa-times"></i></div>

            <div class="info_management">
                <h3>정보관리</h3>
            </div>
        </div><!--profile_wrap end-->


        <div class="user_info_wrap">

                <div class="user_info">
                    <h4 class="marketkeeper">장터지기</h4>
                    <em class="user_nickname">촉촉한바위</em>
                    <div class="grade_img"></div>
                    <div class="nickname_box hidden">
                        <input type="text" id="nickname"  maxlength="7">
                        <div class="nickname_regex"></div>
                    </div><!--nickname_box-->
                </div><!--nickname end-->
        </div><!--userinfo_wrap end-->


        <div class="user_point_wrap">
            <div class="user_point">
                <div class="cumulative_point">
                    <span>119,900p</span>
                </div>
                <div class="point ">
                    <span>29,000p</span>
                </div>
            </div>
        </div> <!--user_point_wrap end-->


        <div class="service_idx_wrap" class="marketkeeper" >
            <div class="service_idx">
                <span>4.1</span>
            </div>
        </div>

        <div class="user_ranking_wrap">
            <div class="user_ranking">
                <span class="ranking_weekly">381</span>
                <span class="ranking_total">7,052</span>
            </div>
        </div>


    </div><!--mypage end-->
    <div id="userAddress">
        <h3 id="roadAddress" onclick="sample4_execDaumPostcode()">서울 관악구 관악로 85 (문영학원)</h3>
    </div><!--userAddress end-->

<ul id="mypageTab">
    <li class="mypage_tab receipt_tab">
    	<a href="/mypageRecipe.jsp">
        	<i class="fas fa-receipt"></i><em class="tab_click">레시피</em><h4>33</h4>
        </a>
    </li>
    <li class="mypage_tab market_tab">
    	<a href="/mypageMarket.jsp">
        	<i class="fas fa-store"></i><em>장터</em><h4></h4>
        </a>
    </li>
    <li class="mypage_tab review_tab">
    	<a href="/mypageReview.jsp">
        	<i class="fas fa-comment-alt"></i><em>요리후기</em><h4></h4>
        </a>
    </li>
    <li class="mypage_tab question_tab">
    	<a href="/mypageQna.jsp">
        	<i class="fas fa-headset"></i><em>1:1 문의</em><h4></h4>
        </a>
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
                <img src="img/icon_royal.png" alt="royal">
            </div><!--grade_icon end-->

            <div class="grade_detail two_line">
                <p>400,001p~</p>
                <p>추가 적립 10%</p>
            </div><!-- grade_detatil end-->
        </div><!-- grade_info end-->

        <div class="grade_info">
            <div class="grade_icon">
                <img src="img/icon_heritage%20.png" alt="heritage">
            </div><!--grade_icon end-->

            <div class="grade_detail two_line">
                <p>150,001p~400,000p</p>
                <p>추가 적립 7%</p>
            </div><!-- grade_detatil end-->
        </div><!-- grade_info end-->

        <div class="grade_info">
            <div class="grade_icon">
                <img src="img/iconDiamond.png" alt="diamond">
            </div><!--grade_icon end-->

            <div class="grade_detail two_line">
                <p>70,001p ~150,000p</p>
                <p>추가 적립 5%</p>
            </div><!-- grade_detatil end-->
        </div><!-- grade_info end-->

        <div class="grade_info">
            <div class="grade_icon">
                <img src="img/icon_platinum.png" alt="platinum">
            </div><!--grade_icon end-->

            <div class="grade_detail two_line">
                <p>30,001p ~70,000p</p>
                <p>추가 적립 3%</p>
            </div><!-- grade_detatil end-->
        </div><!-- grade_info end-->

        <div class="grade_info">
            <div class="grade_icon">
                <img src="img/icon_gold.png" alt="gold">
            </div><!--grade_icon end-->

            <div class="grade_detail one_line">
                <p>10,001p ~30,000p</p>
            </div><!-- grade_detatil end-->
        </div><!-- grade_info end-->

        <div class="grade_info">
            <div class="grade_icon">
                <img src="img/icon_silver.png" alt="silver">
            </div><!--grade_icon end-->

            <div class="grade_detail one_line">
                <p>5,001p ~10,000p</p>
            </div><!-- grade_detatil end-->
        </div><!-- grade_info end-->

        <div class="grade_info">
            <div class="grade_icon">
                <img src="img/icon_family.png" alt="family">
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
        <span>2,311</span>
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
<div class="service_idx_box box hidden">
    <div class="close_btn"><i class="fas fa-times"></i></div>
    <div class="profile_wrap">
        <div class="profile_img">
            <img class="profile_img_big" src="img/profile_img_default.jpg"/>
        </div>
        <div class="user_info_wrap">

            <div class="user_info">
                <h4 class="marketkeeper">장터지기</h4>
                <em class="user_nickname">촉촉한바위</em>
            </div><!--nickname end-->
            <span class="service_idx">4.1</span>




        </div><!--userinfo_wrap end-->
    </div><!--profile_wrap-->

    <div class="market_card">
        <a href="" class="market_card_a">
            <img class="market_card_img" src="img/marketplace3.jpg" />
            <div class="market_info">
                <div class="market_card_name">봉천동 이마트</div>
                <div class="market_card_market_time">09:00 / 11:00 / 14:00 / 18:00 / 21:00</div>
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



