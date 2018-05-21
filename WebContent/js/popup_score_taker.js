
var $contractServiceBox = $(".contract_service");

/*각각의 평점 클릭시*/
$contractServiceBox.on("change", "input[type=radio]", function(){
    var score = $(this).data('score');

    console.log(score);
    
    if(score === 'good') {
        $(this).parent('div').removeClass('on');
    } else {
        $(this).parent('div').addClass('on');
    }
    
}); //end

//taker가 평점 등록할 때
$contractServiceBox.on("submit", "#scoreByTakerRegisterForm", function (e) {

	//submit 이벤트 막기
	e.preventDefault();
	
	var idx = $("#scoreByTaker").data("idx");
	
	//ajax로 submit하는 요청 보냄
	//alert("하하");
	
//	console.log($('input[name=scorePrice]:checked').val());
//	console.log($('input[name=scoreKind]:checked').val());
//	console.log($('input[name=scoreKnowhow]:checked').val());
//	console.log($('input[name=scoreHonest]:checked').val());
	
	var scorePrice = $('input[name=scorePrice]:checked').val(),
		scoreKind = $('input[name=scoreKind]:checked').val(),
		scoreKnowhow = $('input[name=scoreKnowhow]:checked').val(),
		scoreHonest = $('input[name=scoreHonest]:checked').val();
	
	//상대방(giver)가 점수 남겼는지 확인(scoreUser)
	var scoreUser = detailList.contractList[idx].scoreUser;
	
	var contractNo = $('input[name=no]').val();
	//console.log(contractNo);
	
	var reviewContent = $("#reviewContent").val();
	//console.log(reviewContent);
	
	$.ajax({
		url: "/ajax/updateScoreFromTaker.poom",
		data: {
			scorePrice: scorePrice,
			scoreKind: scoreKind,
			scoreKnowhow: scoreKnowhow,
			scoreHonest: scoreHonest,
			scoreUser: scoreUser,
			no: contractNo,
			reviewContent: reviewContent,
			serviceNo: detailList.contractList[0].serviceNo
		},
		type: "post",
		error: function (xhr, err, code) {
			alert(err);
		},
		success: function (data) {
			console.log(data);
			
			//성공했을 시 평점 팝업창 닫기
			$(".popup.scoreByTaker").remove();
			
			//계약 상세 팝업에서 닉네임 옆에 V표시하기
			$("#containerContractProgressive>dl>dt.user.taker").addClass("on");
			
			//평점 남기기 버튼 비활성화
			$("#containerContractProgressive .btn_group .score").addClass("off");
			
			//전역변수 detailList에 변경사항 반영 (계약 상세 팝업 띄울 때 업데이트 되어있어야 하므로)
			detailList.contractList[$("#containerContractProgressive").data("idx")].scorePrice = 1;
			
		}//success end
	});//$.ajax() end

	

});//submit() end

