var $popup_containerContractProgressive = $(".popup.containerContractProgressive");


//실제 내용 박스 모델 이외 클릭 일때 팝업 close
var $containerContractProgressive = $("#containerContractProgressive");
var $scoreByGiver = $("#scoreByGiver");
var $scoreByTaker = $("#scoreByTaker");

var $popup_scoreByGiver = $(".popup.scoreByGiver");
$('button.score').click(function () {
    $popup_scoreByGiver.show();
    //$('body').css('overflow', 'hidden');
    //$('body').css('padding-right', '18px');
});

var $popup_scoreByTaker = $(".popup.scoreByTaker");
//임시방편, 원래는 로그인한 유저에 따라서 평점 남기기 클릭시 기버 팝업이 뜨던지, 테이커 팝업이 뜨던지
$('.btn_group>button').eq(0).click(function () {
    $popup_scoreByTaker.show();
    //$('body').css('overflow', 'hidden');
    //$('body').css('padding-right', '18px');
});

$('#scoreByGiver input.cancel,#scoreByTaker input.cancel').click(function () {
	//console.log('a');
    $popup_scoreByGiver.hide();
    $popup_scoreByTaker.hide();
    //$('body').css('overflow', 'visible');
    //$('body').css('padding-right', '0');
});

//$("#content").click(function (evt) {
//
//    //마이페이지 드랍 박스 이외의 클릭 일때 드랍 박스를 감춘다
//    var popupLength_containerContractProgressive = $(evt.target).closest($containerContractProgressive).length;
//    var popupLength_scoreByGiver = $(evt.target).closest($scoreByGiver).length;
//    var popupLength_scoreByTaker = $(evt.target).closest($scoreByTaker).length;
//
//    console.log(popupLength_containerContractProgressive);
//    console.log(popupLength_scoreByGiver);
//    console.log(popupLength_scoreByTaker);
//
//    if (popupLength_containerContractProgressive === 0 && popupLength_scoreByGiver === 0 && popupLength_scoreByTaker === 0) {
//        if($scoreByGiver.is(":visible")) {
//        	//console.log('score g');
//            $popup_scoreByGiver.hide();
//        } else if ($scoreByTaker.is(":visible")) {
//        	//console.log('score t');
//            $popup_scoreByTaker.hide();
//        } else {
//        	//console.log('pro');
//            $popup_containerContractProgressive.hide();
//        }
//    }
//});