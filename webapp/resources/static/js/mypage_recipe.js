/**
 * 
 */

const $openedRcpUl =$(".mypage_opened_recipe_tab.tab_wrap ul");

const $myRcpsUl = $(".mypage_myrecipe_tab.tab_wrap ul");

const $tmpRcpsUl = $(".mypage_tmp_recipe_tab.tab_wrap ul");


	
let openedRcpNo =0;
let rcpsOpenNo = 0;

const mypageRecipe = {
	init : function(){
		
		const _this = this;
		_.templateSettings = {interpolate: /\<\@\=(.+?)\@\>/gim,evaluate: /\<\@([\s\S]+?)\@\>/gim,escape: /\<\@\-(.+?)\@\>/gim};
		
		_this.getMyRcps();
		
		// tmp recipes
		$(".tmp_recipe").on('click', function(){
			_this.getTmpRcps();
		});
		
		// delete tmp recipes
		$(".mypage_tmp_recipe_tab.tab_wrap ul").on('click', '.delete_btn', function(){
			
			const $this = $(this);
			const param = $this.closest("div.box").find(".tmp_rcp_no").val();
			
			console.log(param);
			
			_this.deleteTmpRecipe(param);
		}) ;
		
		// get opened recipes
		$(".open_recipe").on('click', function(){
			_this.getOpenedRecipes();
		});
		
		// review template 
		$openedRcpUl.on("click", ".recipe_review_btn", function(){
			const $this = $(this);
			//팝업 시 레시피 정보 보여주기 위해 레시피 번호 얻어옴
        	openedRcpNo = $this.closest("div.box").find(".opened_rcp_no").val();
			_this.popupReviewTemplateForRecipe();
		});
		
	},
	
	getMyRcps : function(){

		const myRcpsTmpl = _.template($("#myRcpsTmpl").html());
		
		$.ajax({
			url: '/mypage/ajax/myrecipes',
			type: 'GET',
			dataType:'json',
			error: function(){ alert('something went wrong')},
			success: function(json){
				console.log(json);
				$myRcpsUl.html(myRcpsTmpl({jsons:json}));
			}
			
		});
	},
	
	getTmpRcps : function(){

		const tmpRcps = _.template($("#tmpRcpsTmpl").html());
		
		$.ajax({
			url: "/mypage/ajax/tmp_recipes",	
			type: "GET",
			dataType: "json",
			error: function(){alert("서버점검중. 나중에 시도해주세요")},
			success: function(json){
				console.log(json);
				$tmpRcpsUl.html(tmpRcps({tmpRcps:json}));
			}
		}); 
	},
	
	deleteTmpRecipe: function(param){
		
		const c = confirm("저장된 레시피를 삭제하시겠습니까?");
		
		if(c==true){
			
			$.ajax({
				url: "/mypage/ajax/delete_tmp_rcp/"+ param,
				type: "DELETE",
				dataType: "json",
				error: function(){alert('something went wrong')},
				success: function(json){
					alert(json);
					
					// call after having deleted recipe
					mypageRecipe.getTmpRcps();
				}
				
			});
		}
	},
	
	getOpenedRecipes : function(){

		
		const openedRcpTmpl = _.template($("#openedRcpsTmpl").html());
		
		$.ajax({
			url:"/mypage/ajax/opened_recipes",
			type:"GET",
			dataType: "JSON",
			error:function(){alert("something went wrong");	},
			success: function(json){
				console.log(json);
				$openedRcpUl.html(openedRcpTmpl({openedRcps:json}));
			}
		}); 
	},
	
	popupReviewTemplateForRecipe : function(){
		
		const rvFormTmpl = _.template($("#rvFormTmpl").html());
		
        $.ajax({
			url:"/mypage/ajax/recipes_for_review/"+openedRcpNo,
			type:"GET",
			dataType: "JSON",
			error:function(){alert("no");	},
			success: function(json){
				
				rcpsOpenNo = json.rcpsOpenNo;
				$("body").html(rvFormTmpl({openedRcp: json}));
				
				// append template to body before popup class is appended to it  
				$(".review_popup_overlay").addClass('show_popup');
			}
		}); 
	}
	
	
}
    
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

mypageRecipe.init();