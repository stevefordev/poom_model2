
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

	var $this = $(this);
	
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
	
	//상대방(giver) 유저번호
	var giverNo = detailList.contractList[idx].giverNo;
	//console.log("giverNo : "+giverNo);
	
	var contractNo = $('input[name=no]').val();
	//console.log(contractNo);
	
	var reviewContent = $("#reviewContent").val();
	//console.log(reviewContent);
	
	var coinAmount = $(".container_contract_progressive>#containerContractProgressive dl dd.info.price #coin").text();
	//console.log(coinAmount);
	
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
			serviceNo: detailList.contractList[0].serviceNo,
			coinAmount: coinAmount,
			giverNo: giverNo
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
			
			if(scoreUser != 0) {
				//둘 다 평점 남겨서 계약 완료 시
				alert("해당 계약이 완료되었습니다.");
				//계약 목록에서 삭제
				$(".contract_list").children().eq(idx).remove();
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

	

});//submit() end

