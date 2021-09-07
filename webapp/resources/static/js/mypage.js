/**
 * 
 */

const main = {
	
	init : function(){
		
		const _this = this;
		
		$(".receipt_tab").on('click', function(){
			_this.underbar(72);
			$("#mypageMarket").removeClass("hidden");
		});
		
		$(".market_tab").on('click', function(){
			_this.underbar(322);
			$("#mypageReceipt").removeClass("hidden");
		});
		
		$(".review_tab").on('click', function(){
			_this.underbar(572);
			$("#mypageReview").removeClass("hidden");
		});
		
		$(".question_tab").on('click', function(){
			_this.underbar(822);
			$("#mypageQuestion").removeClass("hidden");
		});
		
		  
    	// profile change event
    	$('#profileImg').on("change",function(){
   
		const file = this.files[0];
		_this.updateProfileImage(file);

		});
		
	},
	
	underbar : function(x){
		$("#mypageContent").children().addClass("hidden");
		$(".tab_under_bar").css("left",x+"px")
	},
	
	updateProfileImage : function(file){
		
		if (file.size===0) {
			alert("제대로 된 파일을 선택하시오. -_-;");
			return;
		}
		
		//2) 파일의 종류가 image
		if (!file.type.includes("image/")) {
			//파일이 image가 아닐때
			alert("이미지를 선택해주시오. -_-;");
			return;
		}

		//3) FormData객체 생성
		const formData = new FormData();

		//4) formdata에 파라미터를 추가

		//파일을 append
		formData.append("profileImage", file, file.name);

		$.ajax({
			url : "/mypage/ajax/update_profile",
			type : "POST",
			processData : false,
			contentType : false,
			data : formData,
			dataType : "json",
			error : function() {
				alert("서버 점검중!")
			},
			success : function(json) {

				//img요소의 src속성을 넘어온 이미지로 변경 
				$('#profileImage').attr("src", "resources/static/img/members/profile/" + json.profileImage);
				
				//header의 이미지도 변경
				$(".circle_user_img").attr("src", "resources/static/img/members/profile/" + json.profileImage);

			}
		});
	}
}




    /*--■■■■■■■■■■■■■■■■■■■■■■■■■■■■---각종 안내---■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■--*/

    //선언
    const $user_info_grade_img=$(" .user_info .grade_img");
    const $grade_info_box=$(".grade_info_box");
    const $layerWrap=$("#layerWrap");
    const $cumulative_point=$(".cumulative_point");
    const $point=$(".point");
    const $point_history_box=$(".point_history_box");
    const $service_idx_wrap_service_idx=$(".service_idx_wrap .service_idx");
    const $service_idx_box=$(".service_idx_box");
    const $point_accumulation_tab= $(".point_accumulation_tab");
    const $point_use_tab=$(".point_use_tab");
    const $point_history_accumulation=$(".point_history_accumulation");
    const $point_history_use=$(".point_history_use");
    const $service_idx=$(".service_idx");
    
    //창 닫기를 위한 선언
    const $close_btn=$(".close_btn");
    const $box=$(".box");   
 

    //창 열기 함수 선언
    function openBox(click_name, box_name){
        click_name.on("click",function(){
            box_name.removeClass("hidden");
            $layerWrap.removeClass("hidden");
        })
    }

    //닉네임 뒤 등급 이미지 클릭시 등급 설명 보여주기
    openBox($user_info_grade_img,$grade_info_box);

    //누적 포인트 클릭시 등급 설명 보여주기
    openBox($cumulative_point,$grade_info_box);

    //포인트 클릭시 포인트 내역 창 보여주기
    openBox($point,$point_history_box);
 

    //창 닫기 함수 선언
    function closeBox(){
        $box.addClass("hidden");
        $layerWrap.addClass("hidden");
    }


    //주변 검은 부분 눌렀을 시 창 닫기
    $layerWrap.on("click",function (){
        closeBox();
    })

    //닫기 버튼 눌렀을 시 창 닫기
    $close_btn.on("click",function (){
        closeBox();
    })
   


    //서비스 지수 그래프 계산 함수
    function starGraph(keyframes, options){

        //선언, 가장 많은 평가의 길이는 170px로 고정하고, 나머지 값은 최대값과 비교한 %에 따라 그래프의 길이가 변화함
        const max_width = 170;
        let lv=[];
        let countNum=0;


        //각 평점 배열에 저장하기
        for(let i=0;i<5;i++){
            lv[i]=parseInt($(".star_idx_lv"+(i+1)+' .star_idx_num').text());
            countNum+=lv[i];
        }

        //배열 중 가장 많은 평가 값 가져오기
        const max_num=Math.max.apply(null, lv);
        let star_rate_sum=0;


        //각 평점당 평가 숫자에 따른 그래프 길이 계산하기
        for(let j=1;j<6;j++){
            let width=max_width*(lv[(j-1)]/max_num);
            $('.star_idx_lv' + j + ' .star_graph').width(width);

            //각 평점 * 평가수 의 합 계산하기
            star_rate_sum+=j*lv[j-1];
        }


        /* 210127 양 서버에서는 바로 받아 올수 있음으로 주석처리 함
        //소수점 1자리수까지 평점 계산
        $service_idx.text((star_rate_sum/countNum).toFixed(1)); */

    }

    //서비스 지수 함수 호출, 서버에 값 저장시 바로 받아와서 보여줌
    starGraph();

    //서비스 지수 클릭시 서비스 지수 창 보여주기
    $service_idx_wrap_service_idx.on("click",function(){
        $service_idx_box.removeClass("hidden");
        $layerWrap.removeClass("hidden");
        starGraph();
    })
    
    
    //주소 관련 선언
	$roadAddress = $("#roadAddress");
	
	let sido="";
	let gugun="";
	let dong="";
	let lat="";
	let lng="";
	
	//주소-좌표 변환 객체를 생성
	var geocoder = new kakao.maps.services.Geocoder();
	
	//좌표를 통해 새주소로 변경 함수
	function callAdress(newLat,newLng){
		var coord = new kakao.maps.LatLng(newLat, newLng);
		var callback = function(result, status) {
		    if (status === kakao.maps.services.Status.OK) {
		    	if(result[0].road_address!==null){
		    		$roadAddress.text(result[0].road_address.address_name);
		    	}else{
		    		$roadAddress.text(result[0].address.address_name);
		    	}//if ~ else end
		    }
		        //console.log('그런 너를 마주칠까 ' + result[0].road_address.address_name  + '을 못가');		   
		}
		geocoder.coord2Address(coord.getLng(), coord.getLat(), callback);
    }
    
	//새주소로 변경 실행
    callAdress();


   
    
    /* □■□■□■□■ 로그인 멤버==마이페이지 일시 작동 ■□■□■□■□ */
    
    /*--■■■■■■■■■■■■■■■■■■■■■■■■■■■닉네임 변경■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■--*/

    //선언
    const $nickname=$("#nickname");
    const $nickname_regex=$(".nickname_regex");
    const $nickname_box=$(".nickname_box");
    const $user_nickname=$(".user_nickname");
    const $mypage_user_nickname=$("#mypage .user_nickname");

    //유효성 판별을 위한 변수 선언
    let nicknameBoolean = false;

    //유효성 에러시 메세지 출력 함수
    function wrong(){
        $nickname_regex.html("한글,영어,숫자 7글자까지 가능합니다.")
        $nickname_regex.css("color","red");
    }

    //닉네임 클릭시 input창 보여주기
    


    //input focus 벗어날 시 현재 값을 지우고 숨기기
    $nickname.blur(function (){
        $(this).val('');
        $nickname_box.addClass("hidden");
    });

    //21-02-02 19:19 양 닉네임 변경
    $nickname.on("keyup",function(e){

        //새로운 닉네임 입력 시 한글,숫자,영어만 입력 가능
        const regex = /^[가-힣|\w]*$/
        const result = regex.exec($(this).val());

        //닉네임 유효성 검사
        if(result!= null){
            $nickname_regex.html("멋진 닉네임이네요.");
            $nickname_regex.css("color","#1c00db");

            nicknameBoolean=true;

            //글자수 7글자 초과시 메세지 출력
            if(($(this).val().length)>7||($(this).val().length===0)){
                wrong();
                nicknameBoolean=false;
            }//if end

        //한글,숫자,영어가 아니면 메세지 출력
        }else{
            wrong();
            nicknameBoolean=false;
        } //if-else end
        
        
        if(nicknameBoolean){
        //닉네임 중복검사
	        $.ajax({
	    		url:"/ajax/checkNickname.json",
	    		type:"get",
	    		data:{"nickname":$(this).val()},
	    		dataType:"json",
	    		error:function(xhr, error) {
	    			alert("서버 점검 중입니다.")        			
	    		}, //error end
	    		success:function(json) {
	    			if(json.result){
	    				 $nickname_regex.html("이미 사용중인 닉네임입니다.").css("color","red");
	    			} else {
	    				 $nickname_regex.html("멋진 닉네임이네요.").css("color","#1c00db");
	    				 
	    				 //엔터치면 업데이트 진행
	    				 if (e.keyCode === 13){
	    			            $.ajax({
	    			    			url : "/ajax/changeNickname.json",
	    			    			type : "get",
	    			    			data : {"nickname" : $nickname.val()},
	    			    			dataType : "json",
	    			    			error : function() {
	    			    				alert("닉네임 쪽 서버 점검중!")
	    			    			},
	    			    			success : function(json) {
	    			    				$user_nickname.text($nickname.val());
	    			    	            $nickname_box.addClass("hidden"); 
	    			    			}//success end
	    			            })//ajax end	    			            
	    				 }
	    			}//if~else end
	    		}//success end    		
	    	}); //ajax() end        
        }//if end
    }) //$nickName keyup() end

/*
        //엔터를 쳤을 떄 모든 유효성 검사 만족시 값 변경
        if (e.keyCode === 13){
            //alert(nicknameBoolean);

            $user_nickname.text($(this).val());
            $nickname_box.addClass("hidden");            
            
            $.ajax({
    			url : "/ajax/changeNickname.json",
    			type : "get",
    			data : {"nickname" : $(this).val(), "memberNo" : memberNo},
    			dataType : "json",
    			error : function() {
    				alert("닉네임 쪽 서버 점검중!")
    			},
    			success : function(json) {
    				alert("성공!");
    			}//success end
            })//ajax end
        }//if end

   
*/
    /*--■■■■■■■■■■■■■■■■■■■■■■■■---닉네임 변경 end---■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■--*/
    
    //포인트 내역 박스에서 적립 내역 선택시
    $point_accumulation_tab.on("click",function ( ) {

        $point_history_accumulation.removeClass("hidden");
        $point_history_use.addClass("hidden");

    });

    //포인트 내역 박스에서 사용 내역 선택시
    $point_use_tab.on("click",function ( ) {

        $point_history_use.removeClass("hidden");
        $point_history_accumulation.addClass("hidden");

    });

    const $mypage_profile_wrap=$("#mypage .profile_wrap");
    const $profile_wrap_close_btn=$(".profile_wrap .close_btn");
    const $profileImage=$("#profileImage");
    const $profile=$("#profileImg");


    //마우스를 프로필 이미지에 hover시 x버튼 보여주기
	
        $mypage_profile_wrap.hover(
            function () {
                if ($profileImage.attr("src")!== "/img/profileImg/profile.jpg") {
                $profile_wrap_close_btn.removeClass("hidden")
                }
            }, function () {
                $profile_wrap_close_btn.addClass("hidden")
            }
        )
     


    //x버튼 누르면 기본 이미지로 변경하기
    $profile_wrap_close_btn.on("click",function (){
    	    
        //서버에 기본 이미지로 변경
           $.ajax({
    			url : "/ajax/uploadProfileDefault.json",
    			type : "get",
    			data : {},
    			dataType : "text",
    			error : function() {
    				alert("서버 점검중!")
    			},
    			success : function(text) {
    				alert("성공!");
    				//이미지 경로 변경
    		        $profileImage.attr("src","/img/profileImg/"+text); 
    				//헤더 이미지도 변경
    		        $(".circle_user_img").attr("src", "/img/profileImg/"+text);
    		        //x버튼 숨기기
    		        $profile_wrap_close_btn.addClass("hidden");
    			}
            })
        
    });
	

	function sample4_execDaumPostcode() {
		new daum.Postcode({
			oncomplete : function(data) {
				// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

				// 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
				// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
				let roadAddr = data.roadAddress; // 도로명 주소 변수
				let extraRoadAddr = ''; // 참고 항목 변수

				// 법정동명이 있을 경우 추가한다. (법정리는 제외)
				// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
				if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
					extraRoadAddr += data.bname;
				}//if end
				// 건물명이 있고, 공동주택일 경우 추가한다.
				if (data.buildingName !== '' && data.apartment === 'Y') {
					extraRoadAddr += (extraRoadAddr !== '' ? ', '
							+ data.buildingName : data.buildingName);
				}//if end
				// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
				if (extraRoadAddr !== '') {
					extraRoadAddr = ' (' + extraRoadAddr + ')';
				}// if end
				//console.log(roadAddr);
				
				
                //Address table 에 넣기 위한 값(sido, sigungu, dong)
                sido =data.sido;
                gugun=data.sigungu;

                if(data.bname1!=null) {
                    dong=data.bname1+data.bname2;
                } else {
                    dong=data.bname2;
                }//if else end
                			

			    // 주소로 좌표를 검색
			    geocoder.addressSearch(roadAddr, function(result, status) {

			        // 정상적으로 검색이 완료시
			        if (status === kakao.maps.services.Status.OK) {

			            //좌표값 얻기
			        	var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

			            //좌표값 저장하기
			            lat = result[0].y;
                        lng = result[0].x;
                        
                        console.log("1 :"+sido);
                	    console.log(gugun);
                	    console.log(dong);
                	    console.log(lat);
                	    console.log(lng);

						const jsonData = {
							
							"sido": sido, 
							"gugun":gugun, 
							"dong":dong, 
							"lat":lat, 
							"lng":lng 
							
						};
						
                        
                        
                        //ajax로 데이터 전송
                        $.ajax({
							url:"/mypage/ajax/update_address",//주소
							type:"PUT",//방식
							data:JSON.stringify(jsonData),
							contentType: "application/json;charset=utf-8",
							dataType:"json",//응답의 자료형
							error:function(xhr,error){ //받아오기 실패시
								alert("서버 점검중!");
								console.log(error);
							},
							success:function(json){ //받아오기 성공시
								//바뀐 주소를 새주소로 변경하기 
								callAdress(lat, lng);
							}
                        })//ajax end
                        
                   /*      
                        console.log("2 :"+sido);
                	    console.log(gugun);
                	    console.log(dong);
                	    console.log(lat);
                	    console.log(lng); */

			        }//if end
			    });//addressSearch() end
			}
		}).open();
	}//sample4_execDaumPostcode() end

main.init();
