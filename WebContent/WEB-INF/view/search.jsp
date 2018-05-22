<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>검색페이지</title>
<%@ include file="/WEB-INF/view/templates/link.jsp"%>
<link rel="stylesheet" href="/css/common/paginate.css" />
<link rel="stylesheet" href="/css/calendar/tui-date-picker.css">
<link rel="stylesheet" href="/css/calendar/tui-time-picker.css" />
<link rel="stylesheet" href="/css/slick/slick.css" />
<link rel="stylesheet" href="/css/slick/slick-theme.css" />
<link rel="stylesheet" href="/css/search.css?date=2018051801" />
<link rel="stylesheet"
	href="/css/card_giver_level_first.css?date=201804191" />
<link rel="stylesheet"
	href="/css/card_taker_level_first.css?date=201802344" />
<style>
#cardBox {
	width: 1200px;
	min-height: 400px;
	margin: auto;
	/*background-color: #4b96e6;*/
	padding: 20px;
}

#cardBox .card, .taker_card {
	float: left;
	margin-left: 35px;
	margin-bottom: 35px;
} 
</style>
</head>
<body>
	<%@ include file="/WEB-INF/view/templates/header.jsp"%> 
	<div id="menuContents">
		<div id="menuFileterWrap">
			<div id="menuFilter">
				<!-- 역할 부분 입니다-->
				<div class="filter_role">
					<h3>역 할</h3>
					<!--기버 테이커 버튼-->
					<button data-role="2" class="btn_role">품을 주고 싶어요</button>
					<button data-role="1" class="btn_role">품을 받고 싶어요</button>

				</div>
				<!--제공분야-->
				<div class="filter_providing">
					<h3>제공분야</h3>
					<!--가사 교육 심부름 버튼 -->
					<button data-category="2" class="btn_providing on">가사</button>
					<button data-category="1" class="btn_providing">교육</button>
					<button data-category="3" class="btn_providing">심부름</button>

				</div>
				<!--날짜일정-->
				<div class="filter_schedule">
					<h3>날짜일정</h3>
					<!--강사님이 올려주신거라서 css는 제가 만든거 아니니까 수정하지 마세여 ㅎㅎ-->
					<div class="row">

						<div class="tui-datepicker-input tui-datetime-input tui-has-focus">
							<input type="text" id="datepicker-input2" aria-label="Date-Time">
							<span class="tui-ico-date"></span>
						</div>
						<div id="wrapper2" style="margin-top: -1px;"></div>
						<!--여기까지 강사님 캘린더 부분입니다.-->
					</div>

					<button data-term="1" class="btn_date">1주</button>
					<button data-term="2" class="btn_date">2주</button>
					<button data-term="3" class="btn_date">1개월</button>
				</div>
				<!--지역 필터-->
				<div class="filter_area">
					<div class="box_area">
						<h3>지역</h3>
						<a href="" class="address1"> <strong>시</strong> <i
							class="fas fa-sort-down"></i> <i class="fas fa-sort-up"></i>
						</a>
						<ul class="list_address1">
							<li class="item_area"><a href="">서울</a></li>
							<li class="item_area"><a href="">인천</a></li>
							<li class="item_area"><a href="">부산</a></li>
							<li class="item_area"><a href="">대구</a></li>
							<li class="item_area"><a href="">광구</a></li>
							<li class="item_area"><a href="">울산</a></li>
							<li class="item_area"><a href="">대전</a></li>
						</ul>
						<a href="" class="address2"> <strong>군/구</strong> <i
							class="fas fa-sort-down"></i> <i class="fas fa-sort-up"></i>
						</a>
						<ul class="list_address2">
							<li class="item_area"><a href="">강남구</a></li>
							<li class="item_area"><a href="">강동구</a></li>
							<li class="item_area"><a href="">강북구</a></li>
							<li class="item_area"><a href="">강서구</a></li>
							<li class="item_area"><a href="">관악구</a></li>
							<li class="item_area"><a href="">광진구</a></li>
							<li class="item_area"><a href="">구로구</a></li>
							<li class="item_area"><a href="">금천구</a></li>
							<li class="item_area"><a href="">노원구</a></li>
							<li class="item_area"><a href="">도봉구</a></li>
							<li class="item_area"><a href="">동대문구</a></li>
							<li class="item_area"><a href="">동작구</a></li>
							<li class="item_area"><a href="">마포구</a></li>
							<li class="item_area"><a href="">서대문구</a></li>
							<li class="item_area"><a href="">서초구</a></li>
							<li class="item_area"><a href="">성동구</a></li>
							<li class="item_area"><a href="">성북구</a></li>
							<li class="item_area"><a href="">송파구</a></li>
							<li class="item_area"><a href="">양천구</a></li>
							<li class="item_area"><a href="">영등포구</a></li>
							<li class="item_area"><a href="">용산구</a></li>
							<li class="item_area"><a href="">은평구</a></li>
							<li class="item_area"><a href="">종로구</a></li>
							<li class="item_area"><a href="">중구</a></li>
							<li class="item_area"><a href="">중랑구</a></li>
						</ul>
					</div>

				</div>
				<!--상세필터-->
				<div class="filter_detail">

					<button class="btn_detail">
						상세필터 <i class="fas fa-minus"></i> <i class="fas fa-plus"></i>
					</button>
					<div class="list_detail">
						<h3>평점</h3>
						<div class="item_detail1">
							<select class="score">
								<option value="90">90%이상</option>
								<option value="80">80%이상</option>
								<option value="60">60%이상</option>
								<option value="50">50%이상</option>
								<option value="40">40%이상</option>
								<option value="30">30%이상</option>
							</select> <i class="fas fa-sort-down"></i> <i class="fas fa-sort-up"></i>
						</div>
						<h3>품</h3>
						<div class="item_detail2">
							<select class="poom">
								<option value="30">30품이상</option>
								<option value="20">20품이상</option>
							</select> <i class="fas fa-sort-down"></i> <i class="fas fa-sort-up"></i>
						</div>

					</div>

				</div>


			</div>
			<!--//end menuFilter -->
		</div>
		<!--//end menuFileterWrap  -->
		<div id="menucard">
			<div class="box_sort">

				<select class="order">
					<option value="P">인기도순</option>
					<option value="H">품높은순</option>
					<option value="L">품낮은순</option>
					<option value="R">리뷰순</option>
				</select> <i class="fas fa-sort-down"></i> <i class="fas fa-sort-up"></i>
			</div>
			<!-- box_select-->
			<div id="cardBox">
				<!--1차 박스-->

			</div>
			<!-- box_card-->
	

			<!--box_paginate -->
 
		</div>
		<!--menucard -->
	</div>
	<!--menuContents -->
	<%@ include file="/WEB-INF/view/templates/footer.jsp"%>
	<%@ include file="/WEB-INF/view/templates/card_level_first.jsp"%>
	<%@ include file="/WEB-INF/view/templates/js.jsp"%>
	<script src="/js/calendar/tui-code-snippet.min.js"></script>
	<script src="/js/calendar/tui-time-picker.min.js"></script>
	<script src="/js/calendar/moment-with-locales.min.js"></script>
	<script src="/js/calendar/tui-date-picker.min.js"></script>
	<script src="/js/slick/slick.min.js"></script>
	<script src="/js/slick/slick_helper.js?date=201804281"></script>
	<script src="/js/card_util.js?date=201804191"></script>

	<script>
		//역할 버튼 제이커리
		$(".filter_role .btn_role:eq(0)").click(function(e) {
			$(".filter_role").removeClass("on");
			e.preventDefault();
		});
		//역할 버튼 제이커리
		$(".filter_role .btn_role:eq(1)").click(function(e) {
			$(".filter_role").addClass("on");
			e.preventDefault();
		});

		//제공분야 버튼 제이커리
		var category = 2;
		var $category = $(".filter_providing  .btn_providing");
		$category.click(function(e) {
			$category.removeClass("on");
			var $this = $(this);
			$this.addClass("on");
			category = $this.attr("data-category");

			search();
			e.preventDefault();
		});

		//var category1 = null;
		/*
		var category1 = null;
		$(".filter_providing  .btn_providing:eq(0)").click(function(e) {
			$(this).toggleClass("on");
			//console.log($(this).attr("data-category"));
			//category1 = $(this).attr("data-category");
			//search();
			if($(".filter_providing  .btn_providing:eq(0)").hasClass("on")){
				category1 = $(".filter_providing  .btn_providing:eq(0)").attr("data-category");
				console.log(category1)
			}else{
				category1 = null;
			}
			search();
			e.preventDefault();
		});

		var category2 = null;
		$(".filter_providing  .btn_providing:eq(1)").click(function(e) {
			$(this).toggleClass("on");
			if($(".filter_providing  .btn_providing:eq(1)").hasClass("on")){
				category2 = $(".filter_providing  .btn_providing:eq(1)").attr("data-category");
				console.log(category1)
			}else{
				category2 = null;
			}
			search();
			e.preventDefault();
		});

		var category3 = null;
		$(".filter_providing  .btn_providing:eq(2)").click(function(e) {
			$(this).toggleClass("on");
			if($(".filter_providing  .btn_providing:eq(2)").hasClass("on")){
				category3 = $(".filter_providing  .btn_providing:eq(2)").attr("data-category");
				console.log(category1)
			}else{
				category3 = null;
			}
			search();
			e.preventDefault();
		});
		 */

		//날짜일정 버튼 제이커리
		$(".filter_schedule>.btn_date").click(function(e) {
			$('.filter_schedule>.btn_date').removeClass('on');
			$(this).addClass("on");

		});

		//상세필터 .item_detail1  select를 클릭했을때 화살표 변화
		$(".item_detail1").click(function(e) {

			$(".item_detail1 .fa-sort-up").toggleClass("on");
			$(".item_detail1 .fa-sort-down").toggleClass("on");
			e.preventDefault();
			e.stopImmediatePropagation();
		});

		//상세필터 .item_detail2  select를 클릭했을때 화살표 변화
		$(".item_detail2 ").click(function(e) {

			$(".item_detail2 .fa-sort-up").toggleClass("on");
			$(".item_detail2 .fa-sort-down").toggleClass("on");
			e.preventDefault();
			e.stopImmediatePropagation();
		});
		//box_sort select를 클릭했으때 화살표 변화
		$(".box_sort select").click(function(e) {

			$(".box_sort .fa-sort-up").toggleClass("on");
			$(".box_sort .fa-sort-down").toggleClass("on");

			e.preventDefault();
			e.stopImmediatePropagation();

		});

		//////////////////////////////////////////////////////
		//지역 부분
		//다른 부분을 클리했을때 이벤트를 종료 시켜서 클릭햇을때 나왔던 리스트가 사라집니다

		$("body").click(function() {

			$(".item_detail2 .fa-sort-up").removeClass("on");
			$(".item_detail2 .fa-sort-down").removeClass("on");
			$(".item_detail1 .fa-sort-up").removeClass("on");
			$(".item_detail1 .fa-sort-down").removeClass("on");
			$(".box_sort .fa-sort-up").removeClass("on");
			$(".box_sort .fa-sort-down").removeClass("on");
			$(".address2").removeClass("on");
			$(".address1").removeClass("on");
			$(".list_address2").removeClass("on");
			$(".list_address1").removeClass("on");
		});
		//도시 부분 제이커리 입니다
		//city를 클릭했을 때 city에 on이 추가 되면 리스트가 하단에 출현하고 on이 제거 되었을때 리스트가 닫힘니다
		$(".address1").click(function(e) {

			$(".address1").toggleClass("on");
			$(".list_address2").removeClass("on");
			$(".list_address1").toggleClass("on");
			e.preventDefault();
			//이벤트를 막아주는 이벤트입니다 (전체 화면에 어디를 클릭해도 리스트를 사라지게 만들수 있게 해줍니다
			e.stopImmediatePropagation();
		});
		//지역(군/구) 부분 제이커리 입니다
		//address를 클릭했을 때 address에 on이 추가 되면 리스트가 하단에 출현하고 on이 제거 되었을때 리스트가 닫힘니다
		$(".address2").click(function(e) {
			$(".address2").toggleClass("on");
			$(".list_address1").removeClass("on");
			$(".list_address2").toggleClass("on");
			e.preventDefault();
			//이벤트를 막아주는 이벤트입니다 (전체 화면에 어디를 클릭해도 리스트를 사라지게 만들수 있게 해줍니다
			e.stopImmediatePropagation();
		});

		//도시 리스트 div 부분 제이커리입니다
		//list_city를 클릭했을 때 list_city에 on이 추가 되면 리스트가 안에 있는 링크를 클릭했을때 list_address가 실행되어 출현합니다
		var area1 = '';

		$(".list_address1 .item_area a").click(function(e) {
			$(".address1").removeClass("on");
			$(".address1 strong").text($(this).text());
			$(".list_address1").removeClass("on");
			$(".address2").toggleClass("on");
			$(".list_address2").toggleClass("on");
			console.log($(this).text());
			area1 = $(this).text();

			e.preventDefault();
			//이벤트를 막아주는 이벤트입니다 (전체 화면에 어디를 클릭해도 리스트를 사라지게 만들수 있게 해줍니다
			e.stopImmediatePropagation();

		});
		//군/구 리스트 div 부분 제이커리입니다
		//list_address를 클릭했을 때 list_address에 on이 추가 되면 리스트가 안에 있는 링크를 클릭했을때 list_address가 닫힙니다
		var area2 = '';

		$(".list_address2 .item_area a").click(function(e) {
			$(".address2").removeClass("on");
			$(".address2 strong").text($(this).text());
			$(".list_address2").removeClass("on");
			console.log($(this).text());
			area2 = $(this).text();

			e.preventDefault();
			//이벤트를 막아주는 이벤트입니다 (전체 화면에 어디를 클릭해도 리스트를 사라지게 만들수 있게 해줍니다
			e.stopImmediatePropagation();

		});

		//캘린더
		var datepicker2 = new tui.DatePicker('#wrapper2', {
			date : new Date(),
			input : {
				element : '#datepicker-input2',
				format : 'yyyy-MM-dd'
			},
			language : 'ko',
			timepicker : false
		});
		//상세필터
		$(".btn_detail").click(function() {
			$(".filter_detail").toggleClass("on");

		});

		//여기서부터 ajax

		var role = 0;
		$(".btn_role").click(function() {
			role = $(this).attr("data-role");
			console.log(role);
			search()
		});

		var serviceDate = '';
		datepicker2.on('change', function() {
			serviceDate = moment(datepicker2.getDate()).format('YYYY-MM-DD')
			console.log(serviceDate);
			search();
		})

		var term = 0;
		$(".btn_date").click(function() {
			term = $(this).attr("data-term");
			console.log(term);
			search()
		})

		var score = 0;
		$(".score").on('change', function() {
			score = $(this).val();
			console.log(score);
			search()
		})

		var poom = 0;
		$(".poom").on('change', function() {
			poom = $(this).val();
			console.log(poom);
			search()
		})

		var order = 'P';
		$(".order").on('change', function() {
			order = $(this).val();
			console.log(order);
			search()
		})

		function search() {
			var title = $("input[name='query']").val();
			var allFilter = {
				"title" : title,
				"role" : parseInt(role),
				"category" : parseInt(category),
				"serviceDate" : moment(datepicker2.getDate()).format(
						'YYYY-MM-DD'),
				"term" : parseInt(term),
				"area1" : area1,
				"area2" : area2,
				"score" : parseInt(score),
				"poom" : parseInt(poom),
				"order" : order,
				"level" : 1
			};
			console.log(allFilter);
			
			cardUtil.dataset = allFilter;
			cardUtil.getCardList("/ajax/service/search.poom", $("#cardBox"),
					'.img_box');
		}
		if ('${param.query}'.length > 0) {
			search();
		}
		
		$('#searchForm').submit(function (e) {
			console.log('searchForm');
			search();
			e.preventDefault();
		})
	</script>

</body>
</html>