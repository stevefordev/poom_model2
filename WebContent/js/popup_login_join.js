// 팝업시에 브라우저 스크롤 width 계산
/*
 * 추가
 * $("body").addClass("noscroll").css("margin-right", getScrollBarWidth()+"px");
 * 제거
 * $("body").removeClass("noscroll").css("margin-right", "0px");
 * @returns
 */
function getScrollBarWidth () {
    var $outer = $('<div>').css({visibility: 'hidden', width: 100, overflow: 'scroll'}).appendTo('body'),
        widthWithScroll = $('<div>').css({width: '100%'}).appendTo($outer).outerWidth();
    $outer.remove();
    return 100 - widthWithScroll;
};


//회원가입 로그인 팝업 javascript 시작

//리켑차 자바스크립트
var onloadCallback = function() {
    // alert("grecaptcha is ready!");
};

var verifyCallback = function (callback) {
    console.log(callback);
};

//회원가입 로그인 탭 on클래스
$("#loginTab").on("click",function (e) {
    //로그인 입력창으로 변환
    $("#joinInput").removeClass("on");
    $("#loginInput").addClass("on");

    //로그인탭 클릭할시 탭색깔 밝은색으로 변경
    $("#loginTab").addClass("on");
    $("#joinTab").removeClass("on"); 
});
$("#joinTab").on("click",function (e) {
    $("#joinInput").addClass("on");
    $("#loginInput").removeClass("on");
    $("#loginTab").removeClass("on");
    $("#joinTab").addClass("on");
});


//회원가입 유효성검사 시작
var $name = $("#name"),
    $nameAlert = $("#nameAlert"),
    regname = /^[A-Z|a-z|ㄱ-힣\-]{2,15}$/;

$nickname = $("#nickname"),
    $nicknameAlert = $("#nicknameAlert"),
    regnickname = /^[A-Z|a-z|ㄱ-힣|0-9\-]{1,4}$/;

$mail = $("#mail"),
    $mailAlert = $("#mailAlert"),
    regmail = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;

$phoneNum = $("#phoneNum"),
    $phoneNumAlert = $("#phoneNumAlert"),
    regphoneNum = /^((01[1|6|7|8|9])[1-9]+[0-9]{6,7})|(010[1-9][0-9]{7})$/;

$certificate = $("#certificate"),
    $certificateAlert = $("#certificateAlert"),
    regcertificate = /^[0-9]{4}$/;

$password = $("#password"),
    $passwordAlert = $("#passwordAlert"),
    regpassword = /([a-zA-Z0-9].*[!,@,#,$,%,^,&,*,?,_,~,-])|([!,@,#,$,%,^,&,*,?,_,~,-].*[a-zA-Z0-9])/;


function validateName() {
    var val = $name.val();
    if(regname.test(val)){
        $nameAlert.attr("class","good");
        return true
    }else{
        $nameAlert.attr("class","bad");
        return false
    }
}
$name.keyup(validateName);

function validateNickname() {
    var val = $nickname.val();
    if(regnickname.test(val)){
        $nicknameAlert.attr("class","good");
        return true
    }else{
        $nicknameAlert.attr("class","bad");
        return false
    }
}
$nickname.keyup(validateNickname);

function validatePhoneNum() {
    var val = $phoneNum.val();
    if(regphoneNum.test(val)){
        $phoneNumAlert.attr("class","good");
        return true
    }else{
        $phoneNumAlert.attr("class","bad");
        return false
    }
}
$phoneNum.keyup(validatePhoneNum);

function validateCertificate() {
    var val = $certificate.val();
    if(regcertificate.test(val)){
        $certificateAlert.attr("class","good");
        return true
    }else{
        $certificateAlert.attr("class","bad");
        return false
    }
}
$certificate.keyup(validateCertificate);

function validateMail() {
    var val = $mail.val();
    if(regmail.test(val)){
        $mailAlert.attr("class","good");
        return true
    }else{
        $mailAlert.attr("class","bad");
        return false
    }
}
$mail.keyup(validateMail);

function validatePassword() {
    var val = $password.val();
    if(regpassword.test(val)){
        $passwordAlert.attr("class","good");
        return true
    }else{
        $passwordAlert.attr("class","bad");
        return false
    }
}
$password.keyup(validatePassword);

$("#joinContent").submit(function () {

    if(!validateName()){
        return false;
    }
    if(!validateNickname()){
        return false;
    }
    if(!validatePhoneNum()){
        return false;
    }
    if(!validateCertificate()){
        return false;
    }
    if(!validateMail()){
        return false;
    }
    if(!validatePassword()){
        return false;
    }
});
// 회원가입 유효성검사 javascript 끝

//popup javascript 시작
$("#heroJoinBtn").click(function (e) {
    //a요소 클릭시 새로고침되는것을 막기위해 preventDefault사용
    e.preventDefault();
    console.log("heroJoinBtn");
    //a요소중 회원가입클릭시 회원가입이 활성화된상태로 팝업이뜨도록하기위해
    //addclass removeclass사용
    $("#joinInput").addClass("on");
    $("#loginInput").removeClass("on");
    $("#loginTab").removeClass("on");
    $("#joinTab").addClass("on");

    $("body").addClass("noscroll").css("margin-right", getScrollBarWidth()+"px");
    
    //팝업창 띄우기
    $("#popupBackground").show();
});
$("#heroLoginBtn").click(function (e) {
    e.preventDefault();
    console.log("heroLoginBtn");
    $("#joinInput").removeClass("on");
    $("#loginInput").addClass("on");
    $("#loginTab").addClass("on");
    $("#joinTab").removeClass("on");
    
    $("body").addClass("noscroll").css("margin-right", getScrollBarWidth()+"px");
    
    $("#popupBackground").show();
});

//popup을 클릭하면 popup이 사라짐
$("#popupBackground").click(function () {
    $("#popupBackground").hide();
    $("body").removeClass("noscroll").css("margin-right", "0px");
});
//팝업창을 제외한 뒷배경을 클릭했을때 팝업창이 사라지게 하기위해서 이벤트전파를 막음
$("#loginJoinWrap").click(function (e) {
    e.stopPropagation();
});

$("#phoneBtn").click(function () {
    $("#certificate").attr('disabled',false);
});

$("#certificateBtn").click(function () {
    $("#nickname").attr('disabled',false);
    $("#mail").attr('disabled',false);
    $("#password").attr('disabled',false);
});

