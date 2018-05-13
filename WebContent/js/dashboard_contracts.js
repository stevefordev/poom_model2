//계약 탭 활성화
$left_side_tab_li.removeClass("on").eq(0).addClass("on");

// 위에 탭 클릭
var $top_tab_li = $('.top_tab li');

$top_tab_li
		.click(function() {

			$top_tab_li.removeClass('on');
			$(this).toggleClass('on');

			var status = $(this).data("status");

			$('.box_contract').removeClass('on');
			$('#' + status).addClass('on');
			;

			// 위에 큰 탭 클릭했을 때 해당 탭의 중간 탭 중 등록한 서비스 활성화
			var topTapIndex = $(this).index();
			$(".box_contract").eq(topTapIndex).find(".btn_service")
					.removeClass("on");
			$(".box_contract").eq(topTapIndex).find(".btn_myservice").addClass(
					"on");

			// 위에 큰 탭 클릭했을 때 중간 탭에 맞게 등록한 서비스 ul 띄우기
			$(".contract_service").eq(topTapIndex).find("ul").removeClass("on");
			$(".contract_service").eq(topTapIndex).find(".contract_myservice")
					.addClass("on");

			// 위에 큰 탭 클릭했을 때 서비스들의 contract list 펼쳐진거 접기
			$(".contract_list").empty();

			// 해당 탭 활성화 시 해당 탭의  카드 리스트 호출하고 slick 작동
	    cardUtil.dataset = {
        "level" : 2,
        "count": 6,
        "pageNum": 1,
        "contractStatus" : 1
      };
			cardUtil.getCardList("ajax/contractCardList.json", $(".contract_service>ul.on"), '.box_contract.on .profileserviceimg_wrap');

		})// $top_tab_li.click() end

// 중간 탭 클릭
$(".btn_service").click(function() {

	// 해당 큰 탭에서의 중간 탭들만 변화 일어나도록
	$(this).parent().children().removeClass("on");
	$(this).addClass("on");

	var index = $(this).index();

	$(this).parent().next().children().removeClass("on");
	$(this).parent().next().children().eq(index).addClass("on");
	

	// 해당 탭 활성화 시 해당 탭의 giver 카드 slick 작동
  // 해당 탭 활성화 시 해당 탭의  카드 리스트 호출하고 slick 작동
  cardUtil.dataset = {
    "level" : 2,
    "count": 6,
    "pageNum": 1,
    "contractStatus" : 1
  };
  cardUtil.getCardList("ajax/contractCardList.json", $(".contract_service>ul.on"), '.box_contract.on .profileserviceimg_wrap');

});

// "계약목록" 버튼 누를 때 아래 리스트 나오기
var $contractListTmpl3 = $("#contractListTmpl3"), $contractListTmpl2 = $("#contractListTmpl2"), $contractListTmpl1 = $("#contractListTmpl1");

var contractListTmpl3HTML = $contractListTmpl3.html(), contractListTmpl2HTML = $contractListTmpl2
		.html(), contractListTmpl1HTML = $contractListTmpl1.html();

var contractListTmpl3 = _.template(contractListTmpl3HTML), contractListTmpl2 = _
		.template(contractListTmpl2HTML), contractListTmpl1 = _
		.template(contractListTmpl1HTML);

$(".contract_service").on(
		"click",
		".btn_contract_list",
		function() {
			// 해당 card가 포함된 .box_contract의 index 받기
			var contractBoxIndex = $(this).parents(".box_contract").index();

			// .contract_service의 자식인 ul 요소의 index 받기
			var contractServiceUlIndex = $(this)
					.parents(".contract_service ul").index();

			var $contractList = $(this).parents("li").find(".contract_list");

			$contractList.empty();

			var markup;

			// 위에서 받은 index번호에 따라서 붙는
			// contractListTmpl의 종류 다름
			if (contractBoxIndex == 1 && contractServiceUlIndex != 1) {
				markup = contractListTmpl1();
			} else if (contractBoxIndex == 1 && contractServiceUlIndex == 1) {
				markup = contractListTmpl2();
			} else {
				markup = contractListTmpl3();
			}

			if (!($(this).hasClass("on"))) {
				$contractList.append(markup);
			}

			$(this).toggleClass("on");

		});// $(".contract_service").on() end

// 대기중인 계약 탭에서 상세버튼 누르면 계약서 띄우기
$(".contract_service").on("click", ".btn_contract_detail", function() {
	var contractBoxIndex = $(this).parents(".box_contract").index();
	if (contractBoxIndex == 1) {
		$(".popup_bg").css("display", "block");
	} else {
		$(".popup.containerContractProgressive").css("display", "block");
	}
});

// 팝업창 이외의 곳 클릭하면 팝업창 사라짐
$(".popup_bg").click(function(evt) {
	if ($(evt.target).closest("#detailContractPopup").length === 0) {
		$(".popup_bg").css("display", "none");
	}
});
$(".popup.containerContractProgressive").click(function(evt) {
	if ($(evt.target).closest("#containerContractProgressive").length === 0) {
		$(".popup.containerContractProgressive").css("display", "none");
	}
});

// 수락, 거절 버튼 눌렀을 때
// 계약목록에서
$(".contract_service").on("click", ".btn_contract_accept", function() {
	if (confirm("해당 계약을 수락하시겠습니까?")) {
		// 예 버튼 눌렀을 때 해당 계약 사라지게 함
		$(this).parents(".contract_list li").remove();
	}
});
$(".contract_service").on("click", ".btn_contract_reject", function() {
	if (confirm("해당 계약을 거절하시겠습니까?")) {
		// 예 버튼 눌렀을 때 해당 계약 사라지게 함
		$(this).parents(".contract_list li").remove();
	}
});

// 계약 상세팝업에서
$("#detailokBtn").click(function() {
	if (confirm("해당 계약을 수락하시겠습니까?")) {
		// 예 버튼 눌렀을 때 팝업 사라지면서 해당 계약(첫 번째) 사라지게 함
		$(".popup_bg").css("display", "none");
		$(this).parents(".contract_list li").remove();
	}
});
$("#detailnoBtn").click(function() {
	if (confirm("해당 계약을 거절하시겠습니까?")) {
		// 예 버튼 눌렀을 때 팝업 사라지면서 해당 계약(첫 번째) 사라지게 함
		$(".popup_bg").css("display", "none");
		$(this).parents(".contract_list li").remove();
	}
});

// 취소 버튼 눌렀을 때
$(".contract_list").on("click", ".btn_contract_cancel", function() {
	if (confirm("해당 계약을 취소하시겠습니까?")) {
		// 예 버튼 눌렀을 때 해당 계약 사라지게 함
		$(this).parents(".contract_list li").remove();
	}
});
