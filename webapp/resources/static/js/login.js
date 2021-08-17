/**
 * 
 */
 $("#id").on("blur",function (){
        idCheck();
    })

    $("#password").on("blur",function (){
        passCheck();
    })
    function idCheck() {
        const id = $("#id").val();
        if (id == "") {
            $(".check_id").text("아이디을 입력하세요.");            
            $("#logIn").attr("disabled",true);
        }
        else{
            $(".check_id").text("");
            $("#logIn").attr("disabled",false);
        }
    }
    function passCheck() {
        const password = $("#password").val();
        if (password == "") {
            $(".check_pass").html("비밀번호를 입력해주세요")            
            $("#logIn").disabled = true;
        }
        else{
            $(".check_pass").html("")
            $("#logIn").disabled = false;
        }
    }