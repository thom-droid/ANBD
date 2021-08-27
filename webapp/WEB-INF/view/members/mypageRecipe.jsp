<%@page import="com.ktx.ddep.dao.recipe.RcpsDAO"%>
<%@page import="com.ktx.ddep.dto.recipe.Rcp"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
/* 	
	수정일자: 0203 방
	수정내역:
	요리후기 insert, 포인트 적립

	수정일자: 0201 방
	수정내역
	1. 열람레시피 요리후기 작성 탭 작동
	
	수정일자 0126 방현수
	수정내역 
	1. 마이페이지 link.jsp 처리
	2. 마이페이지 헤더 인클루드 처리 (mypageHeader.jsp)
	3. 마이페이지 공용 js 인클루드 처리 (mypageHeader.js)
	
*/

	//내 레시피 받아오기. 멤버번호 임의로 22번이라고 함
	
%>
	
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>마이페이지-레시피</title>
    
    <link rel="stylesheet" href="css/mypage.css">
    <link rel="stylesheet" href="/css/mypageRecipe.css" />
    <%@ include file="/WEB-INF/template/link.jsp" %>
    
</head>
<body>
	<%@ include file="/WEB-INF/template/header.jsp" %>

	<%@ include file="/WEB-INF/template/mypageHeader.jsp" %>
	
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
                                <img src="/img/<@=myRcp.img@>" />
                                <div class="title_rate">
                                    <div class="title_area">
                                            <p class="subtitle">12900p로 만드는</p>
                                            <h3 class="title"><@=myRcp.title@></h3>
                                    </div> <!--title_area end-->
							</a>
                                    <div class="rate_area">
                                        <img src="img/golden_egg.png" />
                                        <span class="rate_point"> 72%</span>
                                    </div> <!--rate_area end-->
                                </div> <!--title_rate end-->

                                    <div class="profile">
                                        <div class="profile_area">
                                            <img src="/img/profile/<@=myRcp.profileImg@>" class="profile_img">
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
							<img src="/img/<@=tmpRcp.img@>" />
								<div class="title_rate">
									<div class="title_area">
										<p class="subtitle"></p>
										<h3 class="title"><@=tmpRcp.title@></h3>
									</div>
									<!--title_area end-->

									<div class="rate_area">
										<img src="img/golden_egg.png" /> <span class="rate_point"></span>
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


<script src="/js/jquery.js"></script>
<script src="/js/underscore-min.js"></script>
<script>

	_.templateSettings = {interpolate: /\<\@\=(.+?)\@\>/gim,evaluate: /\<\@([\s\S]+?)\@\>/gim,escape: /\<\@\-(.+?)\@\>/gim};

	
	 

	/* ================================ 내가 쓴 레시피 ajax ========================== */

	// 레시피 템플릿 얻어오기
	const myRcpsTmpl = _.template($("#myRcpsTmpl").html());
	const $myRcpsUl = $(".mypage_myrecipe_tab.tab_wrap ul");
	
	// 일단 내가 쓴 레시피는 페이지 로딩되면 바로 실행
	// 파라미터 없으므로 GET으로 요청
	function myRcpsAjax(){
		
		$.ajax({
			url:"/ajax/mypageMyRcps.ktx",
			type: "GET",
			dataType: "json",
			error: function(){alert('아니된다');},
			success: function(json){
				// alert('된다');		
				//console.log(json);
				$myRcpsUl.html(myRcpsTmpl({jsons:json}));
			}
		}); // ajax
	}// myRcpsAjax
	myRcpsAjax();

	
	/* ================================ 임.저.레 ajax ========================== */
	
	const tmpRcps = _.template($("#tmpRcpsTmpl").html());
	const $tmpRcpsUl = $(".mypage_tmp_recipe_tab.tab_wrap ul"); 
	
	function getTmpRcpsAjax(){
		
		$.ajax({
			url: "/ajax/mypageTmpRcps.ktx",	
			type: "GET",
			dataType: "json",
			error: function(){alert("서버점검중. 나중에 시도해주세요")},
			success: function(json){
				//console.log(json);
				$tmpRcpsUl.html(tmpRcps({tmpRcps:json}));
			}
		}); // ajax
		
	} // getTmpRcpsAjax
	getTmpRcpsAjax();
	
	/* ================================ 임.저.레 삭제 =============================== */
		
	$tmpRcpsUl.on("click", ".delete_btn", function(){
		
		const c = confirm("저장된 레시피를 삭제하시겠습니까?");
		
		if(c==true){
			
			const $this = $(this);
			const param = $this.closest("div.box").find(".tmp_rcp_no").val();
			console.log(param);
			//console.log(param); // "tmpRcpNo"
			
			$.ajax({
				url: "/ajax/deleteTmpRcps.ktx",
				type: "GET",
				dataType: "json",
				data: {tmpRcpNo: param},
				error: function(){alert('아니다')},
				success: function(response){
					alert(response.msg);
					
					//성공 하고 나면 새로고침 해줘야 됨
					getTmpRcpsAjax();
				}
				
			}); // ajax
		}
	}); // click delete_btn
	
	/* ================================ 열람 레시피 =============================== */
	
	const openedRcpTmpl = _.template($("#openedRcpsTmpl").html());
	const $openedRcpUl =$(".mypage_opened_recipe_tab.tab_wrap ul");
	
	function getOpenedRcpAjax(){
		
		$.ajax({
			url:"/ajax/mypageOpenedRcps.ktx",
			type:"GET",
			dataType: "JSON",
			error:function(){alert("no");	},
			success: function(json){
				//alert("success");
				//console.log(json);
				$openedRcpUl.html(openedRcpTmpl({openedRcps:json}));
				}
		}); // ajax
	}
	getOpenedRcpAjax();
	
	/* ============================= 요리후기 팝업 ===================================== */
	const rvFormTmpl = _.template($("#rvFormTmpl").html());
	
    
    let openedRcpNo =0;
    // 제출용 파라미터
    let rcpsOpenNo = 0;
	
    // 요리후기 팝업
    $openedRcpUl.on("click", ".recipe_review_btn", function(){
    	const $this = $(this);
    	
        //팝업 시 레시피 정보 보여주기 위해 레시피 번호 얻어옴
        openedRcpNo = $this.closest("div.box").find(".opened_rcp_no").val();
        // console.log(openedRcpNo);
        $.ajax({
			url:"/ajax/rcpForRv.ktx",
			type:"GET",
			data: {openedRcpNo: openedRcpNo},
			dataType: "JSON",
			error:function(){alert("no");	},
			success: function(json){
				// alert("success");
				// console.log(openedRcp);
				
				// 레시피 열람번호 파라미터 저장
				rcpsOpenNo = json.rcpsOpenNo;
				
				// console.log("열람번호: "+ rcpsOpenNo);
				// 받아온 json을 템플릿에 값으로 추가하고 body 요소에 append
				$("body").html(rvFormTmpl({openedRcp: json}));
				
				// append한 뒤 (html에 추가된 뒤에) 클래스 붙여서 팝업 띄움
				$(".review_popup_overlay").addClass('show_popup');
			}
		}); // ajax
        
    });
    
    // 요리후기 이미지 첨부
    // 이미지 파라미터값 저장
    let rvImg = "";
    
	$("body").on("change",".rv_img_upload",function(){
		const file = this.files[0];
		const $this = $(this);
		
		if (/^image\//.test(file.type)) {
			
			//multipart 데이터 전송하기 위해 객체 생성자 호출
			const formData = new FormData();
			
			// "rvImg" 속성명을 부여, file.name을 파라미터 값으로 설정
			formData.append("rvImg",file,file.name)
			
			$.ajax({
				url:"/ajax/uploadRvImg.ktx",
				type:"POST",
				dataType:"JSON",
				processData : false,//multipart/form-data
                contentType : false,//multipart/form-data
                data : formData,//multipart/form-data
				error: function(){alert("안된다");},
				success: function(response){
					//alert('된다');
					
					// console.log("json값: " +response);
					
					const $rvImgUploadInput = $(".rv_img_upload_input");
					const $i = $this.parent().find(".fas.fa-plus");
					
					// 리사이즈한 썸네일 이미지 보여주기
					$("<img>").prop("src", "/img/reviewImgThumb/"+response.imgName)
						.appendTo($this.closest("label"));
					
					// 파라미터로 넘길 값 설정해주기
					$rvImgUploadInput.val(response.imgName);
					rvImg = response.imgName;
					// console.log("이미지파일이름: "+ rvImg);
					
					// 기존 i 요소 안보이게 하기
					$i.css("display","none");
				}
			});//ajax 
			
		} // if 
		
	}); //change
	
	// 후기 내용 글자 카운트 및 내용 저장
	
	let curTextNum = 0;
	let rvContents = "";
	$("body").on("input", ".review_content", function(){
		const $this = $(this);
		const maxLen = 50;
		const $txtCount = $(".rv_txt_count");
		rvContents = $this.val();
		curTextNum = rvContents.length;
		
		if(curTextNum > maxLen){
			alert("50자까지 쓸 수 있습니다");
		}
		$txtCount.html(curTextNum);
	}); // input 
	

	//닫기 버튼 눌렀을 때 팝업 닫힘
    $("body").on("click", ".review_close_btn", function(){
       $('.show_popup').removeClass('show_popup');
    }); // on click
	
	// x 버튼눌렀을 때 팝업 닫힘
    $("body").on("click",".review_popup_close_btn", function(){
        $('.show_popup').removeClass('show_popup');
    }); // on click 

    // 요리후기 제출
    $("body").on("click",".review_submit_btn",function(){
    	
    	if (confirm("평가를 제출하시겠어요? 한 번 제출된 후기는 수정이 불가해요!")){
    		
    		const $this = $(this);
    		const $rateValcheck = $(".like_dislike_area input:radio:checked"); 
    		const rvRate = $rateValcheck.val();
    		//console.log(rvRate);
    			
	    	//필요한 파라미터 (이미지, 내용, 평점, 레시피 열람번호) 유효성 검사
	    	
	    	// String은 값이 없을 때 falsy
	    	if(!rvImg){
	    		alert('이미지를 첨부해주세요');
	    	}
    		
	    	if(!rvContents){
	    		alert("내용을 1자 이상 입력해주세요");
	    	}
	    	if($rateValcheck.length==0){
	    		alert("평점을 선택해주세요");
	    	}
	    	//유효성 검사 모두 통과했을 때 ajax 수행
	    	if(rvImg && rvContents && $rateValcheck.length==1){
	    	
	    		// js 객체 생성
		    	const param = new Object();
		    	param.img = rvImg;
		    	param.content = rvContents;
		    	param.rate = rvRate;
		    	param.rcpsOpenNo = rcpsOpenNo;
		    	
		    	// js 객체 JSON 형태로 변환
		    	const paramJson = JSON.stringify(param);
		    	// console.log(paramJson);
		    	$.ajax({
		    		url:"/ajax/insertRvAndPoint.ktx",
		    		type:"POST",
		    		dataType:"JSON",
		    		data: {param: paramJson},
		    		error: function(){alert("error");},
		    		success: function(json){
		    			
		    			// 결과값 출력
		    			alert(json.msg2);
		    			//console.log(json.msg2);
		    			
		    			//완료 후 팝업 창 닫힘
		    			$('.show_popup').removeClass('show_popup');
		    		}
		    	}); //ajax
	    	}
    	
    	}
    	
    }); // on click

    /* =========================== 스크랩한 레시피  ================================= */
    
    const $savedRcpTmpl = _.template($("#savedRcpTmpl").html());
    const $savedRcpsUl = $(".mypage_saved_recipe_tab.tab_wrap ul");
    
    function getSavedRcpsAjax(){
    	
    	$.ajax({
    		
    		url:"/ajax/mypageSavedRcps.ktx",
    		type: "GET",
    		dataType: "JSON",
    		error: function(){alert('error');},
    		success: function(json){
    			console.log(json);
    			$savedRcpsUl.html($savedRcpTmpl({savedRcps:json}));
    			
    			
    		}
    		
    		
    	}); // ajax
    	
    } // getSavedRcpsAjax
    
    getSavedRcpsAjax();
    
    
    /* ============================== 스크랩 레시피 삭제 =========================== */
    
    $savedRcpsUl.on("click",".saved_recipe_delete_btn", function(){
    	
    	// 삭제에 필요한 레시피 번호 받아옴
    	const $this = $(this);
    	const savedRcpNo = $this.closest("div.box").find(".saved_rcp_no").val();
    	
    	if(confirm("스크랩한 레시피를 삭제하시겠습니까?")){
    		
    		$.ajax({
    			
    			url:"/ajax/deleteSavedRcps.ktx",
    			data: {savedRcpNo:savedRcpNo},
    			type:"GET",
    			dataType:"JSON",
    			error: function(){alert("error")},
    			success: function(response)	{
    				//console.log(response);
    				alert(response.msg);
    				getSavedRcpsAjax();
    			}
    			
    		});//ajax
    		
    	}//if 
    	
    });// on click
    
    
    
    
    
    
    /* =================================== 탭 작동 ================================= */
    const $tabList = $('#mypageRecipeTabNavbar li.tab_list');
    const $tabContent = $('.tab_wrap');

    $tabList.on("click", function(){
        $('.on').removeClass('on');
        $(this).addClass('on');

        let idx = $(this).index();

        $tabContent.eq(idx).addClass('on');
    });//$tabList on click



</script>
</body>
</html>