var $contractServiceBox = $(".contract_service");

$contractServiceBox.on('click', '#scoreByGiverRegisterForm .score_good',function () {
    $(this).parent('.score').removeClass('on');
    // $score.removeClass('on');
})

$contractServiceBox.on('click', '#scoreByGiverRegisterForm .score_bad',function () {
    $(this).parent('.score').addClass('on');
    //$score.addClass('on');
})

////image/ 인지 알아내는 정규표현식 객체
//var reg = /^image\//,
//    $canvas = $("#canvas"), //canvas 제이쿼리 객체
//    canvas = $canvas.get(0), // canvas 객체
//    ctx = canvas.getContext("2d"),//context 객체
//    reader = new FileReader();//파일리더 객체
//
var servicePhoto = "blank_image.jpg";
$contractServiceBox.on("change", "#scoreByGiverRegisterForm #servicePhotoInput", function () {
    console.log('#servicePhotoInput');
    var data = new FormData();

    var file = $("#servicePhotoInput").get(0).files[0];

    data.append('upload', file);
    data.append('width', 400);
    data.append('height', 400);

    $.ajax({
      url : '/ajax/registerServicePhoto.poom',
      type : "post",
      dataType : "json",
      data : data,
      processData : false,
      contentType : false,
      success : function(json) {
        console.log(json);
 
        $('.popup.scoreByGiver div.photo>img').attr('src', '/img/service/' + json.name);
        servicePhoto = json.name;
      },
      error : function(jqXHR, textStatus, errorThrown) {

        alert(textStatus);
      }
    });
   
});//change() end


//giver가 평점 등록할 때
$contractServiceBox.on("submit", "#scoreByGiverRegisterForm", function (e) {


	//submit 이벤트 막기
	e.preventDefault();
	
	var idx = $("#scoreByGiver").data("idx");
	
	//alert("하하");
	
	//ajax로 submit하는 요청 보냄

	var scoreUser = 0;
	
	if($("#scoreByGiverRegisterForm div.score").hasClass("on")) {
		//score bad
		scoreUser = 2;
	} else {
		//score good
		scoreUser = 1;
	}//if~else end
	//console.log(scoreUser);
	
	//상대방(taker)가 점수 남겼는지 확인(scoreUser)
	var scorePrice = detailList.contractList[idx].scorePrice;
	
	var contractNo = $('input[name=no]').val();
	//console.log(contractNo);
	  
	$.ajax({
		url: "/ajax/updateScoreFromGiver.poom",
		data: {
			scoreUser: scoreUser,
			scorePrice: scorePrice,
			no: contractNo,
			serviceNo: detailList.contractList[0].serviceNo,
			servicePhoto: servicePhoto
		},
		type: "post",
		error: function (xhr, err, code) {
			alert(err);
		},
		success: function (data) {
			console.log(data);
			
			//성공했을 시 평점 팝업창 닫기
			$(".popup.scoreByGiver").remove();
			
			//계약 상세 팝업에서 닉네임 옆에 V표시하기
			$("#containerContractProgressive>dl>dt.user.giver").addClass("on");
			
			//평점 남기기 버튼 비활성화
			$("#containerContractProgressive .btn_group .score").addClass("off");
			
			//전역변수 detailList에 변경사항 반영 (계약 상세 팝업 띄울 때 업데이트 되어있어야 하므로)
			detailList.contractList[$("#containerContractProgressive").data("idx")].scoreUser = 1;
			
		}//success end
	});//$.ajax() end
});

