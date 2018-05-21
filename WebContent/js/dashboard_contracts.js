//계약 탭 활성화
$left_side_tab_li.removeClass("on").eq(0).addClass("on");

var $contractServiceBox = $(".contract_service"),
	$body = $("body");

//현재 moment객체
var now = moment();

//contractStatus, contractType (data-xx속성에 있는 값) 받아오는 함수
function getContractClassificationCriteria() {
	
	var contractStatus = $("div.top_tab>ul>li.on").data("status"),
		contractType = $(".btn_service.on").data("type");
	
	return {
			"contractStatus" : contractStatus,
			"contractType" : contractType
			}
	
}//getContractClassificationCriteria() end

//서비스카드 불러오는 함수
function getServiceCardList() {
	
	var criteria = getContractClassificationCriteria();
	
	cardUtil.dataset = {
		      "level" : 2,
		      "count": 6,
		      "pageNum": 1,
		      
		      //호출될 때 활성화(class="on") 되어있는 탭에서 
		      //data받아와서 parameter로 넘겨줌
		      "contractStatus" : criteria.contractStatus,
		      "contractType": criteria.contractType
		    };
	
	cardUtil.getCardList("ajax/contractServiceCardList.json", 
					 	 $(".contract_service>ul"), 
					 	 '.box_contract .profileserviceimg_wrap');

	//console.log($("div.top_tab>ul>li.on").data("status"));
	//console.log($(".btn_service.on").data("type"));
	
}//getServiceCardList() end

//페이지 로딩 시 호출
getServiceCardList();

// 위에 탭 클릭
var $top_tab_li = $('.top_tab li'),
    $btn_service = $(".btn_service");

$top_tab_li.click(function() {
	
	var $this = $(this);
	//console.log($this.data("status"));

	$top_tab_li.removeClass('on');
	$this.toggleClass('on');

	// 위에 큰 탭 클릭했을 때 해당 탭의 중간 탭 중 '등록한 서비스'탭 활성화
	$btn_service.removeClass("on");
	$btn_service.eq(0).addClass("on");

	// 위에 큰 탭 클릭했을 때 서비스들의 contract list 펼쳐진 거 접기
	$(".contract_list").empty();

	//클릭할 때마다 해당 탭에 맞는 카드리스트 불러오는 함수 호출
	getServiceCardList();
	
})//$top_tab_li.click() end


// 중간 탭 클릭
$btn_service.click(function() {
	
	var $this = $(this);
	//console.log($this.data("type"));
	
	// 해당 큰 탭에서의 중간 탭들만 변화 일어나도록
	$this.parent().children().removeClass("on");
	$this.addClass("on");
	
	//클릭할 때마다 해당 탭에 맞는 카드리스트 불러오는 함수 호출
	getServiceCardList();

});


// "계약목록" 버튼 누를 때 각 서비스의 계약 받아와서 목록에 띄우기
var $contractListTmpl3 = $("#contractListTmpl3"), 
	$contractListTmpl2 = $("#contractListTmpl2"),
	$contractListTmpl1 = $("#contractListTmpl1");

var contractListTmpl3HTML = $contractListTmpl3.html(), 
	contractListTmpl2HTML = $contractListTmpl2.html(), 
	contractListTmpl1HTML = $contractListTmpl1.html();

var contractListTmpl3 = _.template(contractListTmpl3HTML), 
	contractListTmpl2 = _.template(contractListTmpl2HTML), 
	contractListTmpl1 = _.template(contractListTmpl1HTML);

//계약 목록 펼쳐질 때 받아오는 계약 상세 정보들(List로)
//그때 그때 담길 전역변수!
var detailList = null;

$contractServiceBox.on("click", ".btn_contract_list", function() {
	
	//console.log(".btn_contract_list 클릭됨");
	
	var $contractList = $(this).parents(".contract_service li").find(".contract_list");
	
	//계약 목록 클릭함에 따라 계약 목록(그 카드 혹은 다른 카드) 접기
	if($contractList.children().length != 0) {
		//클릭한 그 카드의 카드 목록이 펼쳐져 있었다면
		
		//일단 모든 계약 목록 다 비우고
		$(".contract_list").empty();
		//끝
		return false;
		
	} else {
		//클릭한 그 카드의 카드 목록이 펼쳐져 있지 않았다면
		
		//일단 모든 계약 목록 다 비우고
		$(".contract_list").empty();
		//그 뒤로 진행
				
	}//if~else end

	var markup;
	
	var criteria = getContractClassificationCriteria();
	
	//카드번호 = 서비스 번호
	var cardNo = $(this).parents("li").find(".box_heart").data("no");
	
	$.ajax({
		url: "/ajax/contractList.json",
		data: {
			"contractStatus": criteria.contractStatus,
			"cardNo": cardNo
		},
		dataType: "json",
		type: "post",
		error: function (xhr, err, code) {
			console.log(err);
		},
		success: function (data) {
			
			//db에 받아온 후 전역변수 detailList에 담음
			detailList = data;
			
			console.log(data);
			
			if(data != null) {
				
				if(criteria.contractStatus == 0 && criteria.contractType == 'receive') {
					//대기 중인 계약, 등록한 서비스일 때 1번 템플릿
					markup = contractListTmpl1({"data" : data.contractList});
				} else if(criteria.contractStatus == 0 && criteria.contractType == 'send') {
					//대기 중인 계약, 계약서 보낸 서비스일 때 2번 템플릿
					markup = contractListTmpl2({"data" : data.contractList});
				} else {
					//나머지는 3번 템플릿
					markup = contractListTmpl3({
										"data" : data.contractList,
										"contractType" : criteria.contractType
									});
				}//if~else if end
				
				$contractList.html(markup);
				
			}//if end
		}//success end
	});//$.ajax() end

});//$(".contract_service").on() end

var $waitContractDetailPopupTmp = $("#waitContractDetailPopupTmp"),
	waitContractDetailPopupTmpHTML = $waitContractDetailPopupTmp.html(),
	waitContractDetailPopupTmp = _.template(waitContractDetailPopupTmpHTML),
	$progressContractDetailPopupTmp = $("#progressContractDetailPopupTmp"),
	progressContractDetailPopupTmpHTML = $progressContractDetailPopupTmp.html(),
	progressContractDetailPopupTmp = _.template(progressContractDetailPopupTmpHTML);

//상세버튼 누르면 계약서 팝업창 띄우기
$contractServiceBox.on("click", ".btn_contract_detail", function() {
	
	var $this = $(this);
	
	//해당 계약의 index
	var idx = $this.data("idx");

	var criteria = getContractClassificationCriteria();
	
	
	var markup = null;
	
	//탭에 따라 다른 popup창 띄우기 
	if(criteria.contractStatus == 0) {
		//대기 중인 계약일 때
		markup = waitContractDetailPopupTmp({
								"contractDetailInfo" : detailList.contractList[idx], //계약 목록 펼칠 때 받아온 계약 상세정보 
								"contractType" : criteria.contractType, //팝업창 안에 버튼 다르게 하기 위해 (수락,거절 / 취소)
								"contractIdx" : idx
								});
	} else {
		//진행, 완료된 계약일 때
		
		//진행 계약의 진행 횟수 계산
		var scheduleCheckCount = 0;
		
		_.each(detailList.contractList[idx].contractScheduleList, function (contractSchedule) {
			
			//console.log(moment(contractSchedule.serviceDate).format("YYYY년 MM월 DD일"));
			
			//스케쥴 일정이 현재 시간보다 이전이면 count
			if(moment(contractSchedule.serviceDate).isBefore(now)) {
				++scheduleCheckCount;
			}//if end
			
		});//_.each() end
		
		//console.log(scheduleCheckCount);
		
		//평점 남기기 버튼 활성화 여부
		//스케쥴 일정 다 채우고 평점 남기기 전에만 활성화 다른 경우는 모두 비활성화
		var isActiveScoreBtn = false;
		
		var isActiveScoreBtnCondition1 = (scheduleCheckCount == detailList.contractList[idx].contractScheduleList.length);
		
		var isActiveScoreBtnCondition2 = 0;
		if(loginUserNo == detailList.contractList[idx].giverNo) {
			isActiveScoreBtnCondition2 = (detailList.contractList[idx].scoreUser == 0);
		} else {
			isActiveScoreBtnCondition2 = (detailList.contractList[idx].scorePrice == 0);
		}
		
		if(isActiveScoreBtnCondition1 && isActiveScoreBtnCondition2) {
			isActiveScoreBtn = true;
		}//if end
		
		markup = progressContractDetailPopupTmp({
								"contractDetailInfo" : detailList.contractList[idx],
								"contractType" : criteria.contractType,
								"contractIdx" : idx,
								"fullAddress" : detailList.serviceWithFullAddress,
								"scheduleCheckCount" : scheduleCheckCount,
								"isActiveScoreBtn" : isActiveScoreBtn
							});
		
	}//if~else() end



	
	$this.parents(".contract_list li").prepend(markup);
	
	//팝업창에서 각 dd 높이와 같게 dt 높이 setting (line-height도)
	var $schedule = $("#detailContractPopup #schedule, #containerContractProgressive dd.info.schedule"),
		$content = $("#detailContractPopup #content, #containerContractProgressive dd.info.msg")
		scheduleHeight = $schedule.height()+"px",
		contentHeight = $content.height()+"px";
	
	//console.log("scheduleHeight: "+scheduleHeight+" / contentHeight : "+contentHeight);
	
	$schedule.prev().css({
				"height": scheduleHeight,
				"line-height": scheduleHeight
				});
	$content.prev().css({
				"height": contentHeight,
				"line-height": contentHeight
				});
	
});//$btn_contract_detail.click() end

// 팝업창 이외의 곳 클릭하면 팝업창 사라짐
$contractServiceBox.on("click", ".popup_bg", function(evt) {
	if ($(evt.target).closest("#detailContractPopup").length === 0) {
		$(".popup_bg").remove();
	}
});
$contractServiceBox.on("click", ".container_contract_progressive", function(evt) {
	if ($(evt.target).closest("#containerContractProgressive").length === 0) {
		$(".container_contract_progressive").remove();
	}
});

$contractServiceBox.on("click", ".btn_contract_accept, .btn_contract_reject, .btn_contract_cancel, " +
				  ".popup_bg #acceptBtn, .popup_bg #rejectBtn, .popup_bg #cancelBtn", function() {
	
	//alert("하하");
	
	var $this = $(this),
		btnLocation = $this.data("location");
	
	//해당 계약 번호, Index 얻어옴
	var contractNo = 0,
		contractIdx = 0;
	
	if(btnLocation=="list") {
		contractNo = $this.parents(".contract_list li").data("no");
		contractIdx = $this.parents(".contract_list li").data("idx");
	} else {
		contractNo = $this.parents(".popup_bg #detailContractPopup").data("no");
		contractIdx = $this.parents(".popup_bg #detailContractPopup").data("idx");
	}
	console.log("계약 번호 : "+contractNo);
	console.log("계약 index : "+contractIdx);
	
	var btnType = $this.data("type");
	console.log(btnType);
	
	var btnString = $this.text();
	//수락, 거절, 취소 text 얻어오기
	
	if(confirm("해당 계약을 "+btnString+"하시겠습니까?")) {
		// 예 버튼 눌렀을 때
		// 계약 상태 업데이트
		$.ajax({
			url: "/ajax/updateContractStatus.json",
			datatype: "json",
			type: "post",
			data: {
				"contractNo": contractNo,
				"btnType": btnType
			},
			error: function (xhr, err, code) {
				console.log(err);
			},
			success: function (data) {
				
				console.log(data);
				
				if(data) {
					
					//alert("해당 계약을 "+btnString+"하였습니다.");
					
					//대기 중인 계약 목록에서 안 보이게 삭제
					$this.parents(".contract_list li").eq(contractIdx).remove();
					
					//계약 목록 지우고 나서 해당 서비스 카드에 남은 계약이 없을 때
					//서비스 카드도 함께 삭제
					
					alert($this.parents(".contract_list").length);
					
					if($this.parents(".contract_list").children().length==0) {
						$(".contract_sevice>li").remove();//수정 필요
						//alert("비었음");
					}//if end
					
					if(btnLocation=="popup") {
						//팝업창 꺼지게
						$(".popup_bg").remove();
					}//if end
					
				}//if end
				
			}//success end
		});//$.ajax()end
	}//if end
	
});//click() end



//평점 팝업창 띄우기
var $popupScoreByGiverTmp = $("#popupScoreByGiverTmp"),
	$popupScoreByTakerTmp = $("#popupScoreByTakerTmp");
var $popupScoreByGiverTmpHTML = $popupScoreByGiverTmp.html(),
	$popupScoreByTakerTmpHTML = $popupScoreByTakerTmp.html();
var popupScoreByGiverTmp = _.template($popupScoreByGiverTmpHTML),
	popupScoreByTakerTmp = _.template($popupScoreByTakerTmpHTML);

$contractServiceBox.on("click", ".container_contract_progressive .btn_group>button.score", function () {
	
	var $this = $(this);
	
	//계약번호와 계약index받아옴
	var contractNo = $this.parents(".container_contract_progressive #containerContractProgressive").data("no"),
		contractIdx = $this.parents(".container_contract_progressive #containerContractProgressive").data("idx");

	
	//alert("하하");
	
	var markup = null;
	
	//off class 없을 때! (즉, 진행 횟수가 다 찼을 때만)
	if(!($this.hasClass("off"))) {
		if(loginUserNo == detailList.contractList[contractIdx].giverNo) {
			//로그인한 유저가 giver라면
			markup = popupScoreByGiverTmp({
							"contractNo" : contractNo,
							"contractIdx" : contractIdx
						});
		} else {
			//로그인한 유저가 taker라면
			markup = popupScoreByTakerTmp({
							"contractNo" : contractNo,
							"contractIdx" : contractIdx
						});
		}//if~else end
	}//if end
		
	//console.log(markup);
	
	$this.parents(".contract_list li").prepend(markup);
	
});//$contractServiceBox.on("click") end

//취소 버튼 클릭 시 평점 팝업창 없애기
$contractServiceBox.on("click", "#scoreByGiver .btn_group .cancel, #scoreByTaker .btn_group .cancel", function () {
	
	$(this).parents(".scoreByGiver, .scoreByTaker").remove();
	
})//click() end




