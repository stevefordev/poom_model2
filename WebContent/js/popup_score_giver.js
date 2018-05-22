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
 
	var $this = $(this);

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
	
	//상대방(taker) 번호
	var takerNo = detailList.contractList[idx].takerNo;
	//console.log("taker 번호 : "+takerNo)

	var contractNo = $('input[name=no]').val();
	//console.log(contractNo);

	//총 코인 가격
	var coinAmount = $(".container_contract_progressive>#containerContractProgressive dl dd.info.price #coin").text();
	//console.log(coinAmount);
	  
	$.ajax({
		url: "/ajax/updateScoreFromGiver.poom",
		data: {
			scoreUser: scoreUser,
			scorePrice: scorePrice,
			no: contractNo,
			serviceNo: detailList.contractList[0].serviceNo,
			servicePhoto: servicePhoto,
			coinAmount: coinAmount,
			takerNo: takerNo
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
			
			if(scorePrice != 0) {
				//둘 다 평점 남겨서 계약 완료 시
				alert("해당 계약이 완료되었습니다.");
				//계약 목록에서 삭제
				$(".contract_list li").eq(idx).remove();
				//팝업이 해당 계약 목록 리스트 안에 마크업 되므로 
				//팝업꺼질 때 body의 class제거가 안되고 없어지므로
				//따로 해준다.
				$("body").removeClass("noscroll").css("margin-right", "0px");

				console.log("카드 삭제 필요할 수도");

				} else {
				//상대방이 평점 남겨야할 때 (완료x)
				alert("평점이 등록되었습니다. 상대방이 평점을 남기면 계약이 완료됩니다.");


				}//if~else end
			
		}//success end
	});//$.ajax() end
});

