/**
 * 
 */

//유저 프로필을 누르면 메뉴 나오기

    $(".circle.user").on("click",function (){
        $(".user_menu").toggleClass("hidden");
    })//click end

    //메뉴 밖을 누르면 메뉴 사라지기
    $("body").on("click",function (e){
        let $tgPoint = $(e.target);
        const $popCallBtn = $tgPoint.hasClass('circle_user_img');
        const $popArea = $tgPoint.hasClass('user_menu');
        const $popArea2 = $tgPoint.hasClass('user_menu_item');

/*
        console.log(!$popCallBtn && !$popArea)
        console.log("btn : "+$popCallBtn)
        console.log("div : "+$popArea)
        console.log("div : "+$popArea2)
*/
    ``  
         if ( !$popCallBtn && !$popArea && !$popArea2 ) {
             $(".user_menu").addClass('hidden');

         }
     })//click end