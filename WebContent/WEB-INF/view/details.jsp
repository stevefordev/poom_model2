<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>상세페이지_giver</title>
<c:import url="/WEB-INF/view/templates/link.jsp" />
<link rel="stylesheet" href="/css/card_giver_level_first.css" />
<link rel="stylesheet" href="/css/details_giver.css?date=201805193" />
<link rel="stylesheet" href="/css/popup_details_question.css" />
<link rel="stylesheet" href="/css/popup_details_reply.css" />
<link rel="stylesheet" href="/css/slick/slick.css" />
<link rel="stylesheet" href="/css/slick/slick-theme.css" />
<link rel="stylesheet" href="/css/common/paginate.css" />
<style>
body {
	background: #e3e3e3;
}

#cardBox>.card {
	float: left;
	margin-right: 25px;
	margin-left: 25px;
}

#likeBtn .fa-heart {
	font-size: 50px;
}

#detailsContractPopup dl dd.schedule span {
	display: block;
}
</style>
</head>
<body>
	<c:import url="/WEB-INF/view/templates/header.jsp" />

	<h2 id="detailsTitle">${service.title }</h2>
	<c:choose>
		<c:when test="${service.role == 1 }">
			<div id="detailsImgBox">
				<div class="cursor">
					<button class="left_cursor">
						<i class="fas fa-angle-left"></i>
					</button>
					<button class="right_cursor">
						<i class="fas fa-angle-right"></i>
					</button>
				</div>
				<div class="profileserviceimg_wrap">
					<c:forEach items="${photos }" var="photo">
						<div class="profileservice_img">
							<img src="/img/service/${photo.filename}" />
						</div>
					</c:forEach>
					<c:forEach items="${service.photoUrl }" var="photo">
						<div class="profileservice_img">
							<img src="/img/${service.categoryEng}/${photo}" />
						</div>
					</c:forEach>
				</div>
				<h3 class="screen_out">사진정보</h3>
				<div id="detailsImg"></div>
			</div>
		</c:when>
		<c:otherwise>
			<div id="detailsProfileBox">
				<h3 class="screen_out">프로필</h3>
				<div id="takerUserProfile" class="taker">
					<a href="/profile.poom?no=${service.userNo }"> <img
						id="takerProfileImg" src="/img/profile/${service.userPhotoUrl }" />
						<div id="userNickname">${service.userNickName }</div>
					</a>
				</div>
			</div>
		</c:otherwise>
	</c:choose>

	<!--//#detailsImg-->
	<div id="detailsInfoBox">
		<h3 class="screen_out">상세정보</h3>
		<c:if test="${service.role == 1 }">
			<div id="userProfile">
				<h4 class="screen_out">프로필</h4>
				<a href="/profile.poom?no=${service.userNo }"> <img id="giverProfileImg"
					src="/img/profile/${service.userPhotoUrl }" />
					<div id="userNickname">${service.userNickName }</div>
				</a>
			</div>
		</c:if>
		<div id="detailsInfo">
			<h4 class="screen_out">상세정보내용</h4>
			<ul>
				<li>총 평점 : <span
					class="icon_big ${scoreAndCountContract.icon }"></span> 
					<c:choose>
						<c:when test="${service.role == 1 }"> 
						<fmt:formatNumber type = "number" pattern="###" value = "${scoreAndCountContract.scoreGiver }" />		 
							</c:when>
					<c:otherwise>
						<fmt:formatNumber type = "number" pattern="###" value = "${scoreAndCountContract.scoreTaker }" />		
					</c:otherwise>
					</c:choose>
					%(${scoreAndCountContract.countDone })
				</li>
				<li>지역 : ${service.area1 } ${service.area2 }</li>
				<li>진행 중인 계약 ${scoreAndCountContract.countProgress }건</li>
				<li>완료된 계약 ${scoreAndCountContract.countDone }건</li>
				<li><c:forEach items="${tags }" var="tag">
					#${tag.name } 
				</c:forEach></li>
				<li>${service.poom }품</li>
			</ul>
		</div>
		<div id="scheduleBox">
			<h4 class="screen_out">일정</h4>
			<div id="calendarWrap">
				<h5>
					<i class="far fa-calendar-alt"></i> 일정 선택
				</h5>
			</div>
			<!--#calendarWrap-->
			<div id="selectedScheduleBox">
				<h5 class="screen_out">선택 일정</h5>
				<ul>

				</ul>
			</div>
			<!--//#selectedSchedule-->
		</div>
		<!--//#scheduleBox-->
		<div id="detailsFunction">
			<h4 class="screen_out">상세 기능</h4>
			<ul>
				<c:choose>
					<c:when
						test="${loginUser != null && service.userNo == loginUser.no }">
						<li class="update_form">
							<form id="updateForm" method="get"
								action="/service/updateForm.poom">
								<input type="hidden" name="no" value="${service.no }" />
								<button class="updateBtn">수정</button>
							</form>
						</li>
					</c:when>
					<c:otherwise>
						<li>
							<button id="likeBtn">
								<i class="far fa-heart"></i>
							</button>
						</li>
						<li>
							<button id="sendContractBtn">계약서 보내기</button>
							<div id="detailsContractPopupWrap">
								<div id="detailsContractPopup">
									<dl>
										<dt>품 주는사람</dt>
										<dd>
											<img id="detailContractProfileImg"
												src="/img/profile/${service.userPhotoUrl }"></img> <a
												id="giverName" href="">${service.userNickName}</a>
										</dd>
										<dt>품 받는사람</dt>
										<dd>
											<img id="detailContractProfileImg2"
												src="/img/profile/${loginUser.photoUrl }"></img> <a
												id="takerName" href="">${loginUser.nickName }</a>
										</dd>
										<dt>지 역</dt>
										<dd>
											<div id="area">
												<span>${service.area1}</span><span> ${service.area2}</span>
											</div>
										</dd>
										<dt>일 정</dt>
										<dd class="schedule">
											<!--
									<span>2018-04-26 / 17-18시</span> 
									<span>2018-04-26 / 20-21시</span> 
									<span>2018-04-29 / 16-17시</span> 
									<span>2018-04-29 / 17-18시</span> 
									<span>2018-05-02 / 17-18시</span>
									<span>2018-05-02 / 18-19시</span>
									  -->
										</dd>
										<dt>가 격</dt>
										<dd>
											<div id="price">
												<input id="poomvalue" type="number" placeholder="품을입력해주세요."
													value="${service.poom }" /> &nbsp;품
											</div>
										</dd>
										<dt>할 말</dt>
										<dd>
											<div id="comment">
												<textarea placeholder="희망사항을 입력해주세요."></textarea>
											</div>
										</dd>
									</dl>
									<button id="detailsSendContractBtn">계약서 보내기</button>
								</div>
								<!--//#detailsContractPopup-->
							</div> <!--//#detailsContractPopupWrap-->
						</li>

					</c:otherwise>
				</c:choose>

			</ul>
		</div>

	</div>
	<!--//#detailsInfo-->
	<div id="detailsBoardWrap">
		<h3 class="screen_out">게시판</h3>
		<div id="detailsBoardGnb">
			<ul>
				<li><a class="board_gnb on gnb_reference" href="#">참고사항</a></li>
				<li><a class="board_gnb gnb_review" href="#">리뷰(${countTotalReviews})</a></li>
				<li><a class="board_gnb gnb_question" href="#">문의(${countTotalQuestions })</a></li>
			</ul>
		</div>
		<div id="detailsBoardBox">
			<div class="box_board on box_board_reference">
				<div class="contents_board contents_board_reference">${service.content }</div>
			</div>
			<div class="box_board box_board_review">
				<div class="contents_board contents_board_review">

					<div class="box_review_dashboard">
						<div class="box_review_dashboard_area" id="gradeSymbol">
							<div id="box">
								<div id="gradeSymbolImg"
									class="icon_big ${scoreAndCountContract.icon }"></div>
								<span><c:choose>
										<c:when test="${service.role == 1 }"> ${scoreAndCountContract.scoreGiver }%(${scoreAndCountContract.countDone })</c:when>
									</c:choose></span>
							</div>
						</div>
						<div class="box_review_dashboard_area" id="reviewNum">
							<div id="box">
								<span>${countTotalReviews }</span>명이
								<p>리뷰를 남겼습니다.</p>
							</div>
						</div>
						<div class="box_review_dashboard_area" id="chartArea"></div>
					</div>

					<ul id="reviewsWrap" data-type="review">

					</ul>
				</div>
			</div>
			<div class="box_board box_board_question">
				<div class="contents_board contents_board_question">
					<div class="box_btn_question_popup">
						<textarea id="inputQuestion" placeholder="문의 내용을 입력하시오"></textarea>
						<button class="btn_question_popup">문의하기</button>
					</div>
					<ul id="questionsWrap" data-type="question">
						<c:forEach items="${questions }" var="question">
							<li>
								<div class="question_box">
									<div class="question_mark">Q.</div>
									<div class="question_1">
										<span>${question.content }</span>
									</div>
									<c:if test="${question.reply == null }">
										<button class="btn_reply btn_question_reply">답변하기</button>
									</c:if>
								</div> <c:if test="${question.reply != null }">
									<div class="question_box2">
										<div class="question_mark">A.</div>
										<div class="question_2">
											<span>${question.reply }</span>
										</div>
									</div>
								</c:if>
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>
			<!--//.box_board_question-->
		</div>
	</div>
	<!--//#detailsBoardBox-->
	<div id="recommendationsBox">
		<h3>추천 서비스</h3>

		<div id="cardBox"></div>

	</div>
	<!--//#recommendationsBox-->

	<c:import url="/WEB-INF/view/templates/footer.jsp" />

	<script type="text/template" id="calendarBoxTmp">
    <div class="box_calendar">
        <div class="box_calendar_month">
            <!--첫날과 마지막날의 month 비교해서 같으면 하나만 띄우고 다르면 둘 다 띄움-->
            <@ if(dateObjectArr1[0].month() == dateObjectArr2[6].month()) { @>
            <span class="calendar_month"><@=(dateObjectArr1[0].month()+1)+"월" @></span>
            <@} else {@>
            <span class="calendar_month"><@=(dateObjectArr1[0].month()+1)+"-"+(dateObjectArr2[6].month()+1)+"월" @></span>
            <@}@>
        </div>
		<div id="calendarSettingBox">
					<ul>
						<li class="disabled"><button>
								<i class="fas fa-angle-double-left"></i>
							</button></li>
						<li class="disabled"><button>
								<i class="fas fa-angle-left"></i>
							</button></li>
						<li class="disabled"><button>
								<i class="fas fa-square"></i>
							</button></li>
						<li><button>
								<i class="fas fa-angle-right"></i>
							</button></li>
						<li><button>
								<i class="fas fa-angle-double-right"></i>
							</button></li>
					</ul>
				</div>
				<!--#calendarSettingBox-->
        <table class="calendar_schedule">
            <thead>
            <tr>
                <!--요일 구하기-->
                <@$.each(dateObjectArr1, function () {@>
                <th class="day_of_week"><@=this.format('ddd') @></th>
                <@}); @>
            </tr>
            </thead>
            <tbody>
                <tr>
                    <@_.each(dateObjectArr1, function(dateObject) {@>
                    <td>
                        <div class="box_date">
                            <ul>
                                <!--날짜 구하기-->
                                <li class="schedule_date"><@=dateObject.date() @></li>
                                <@_.each(scheduleListForCalendar , function(scheduleForCalendar) { @>
                                       <@ if(dateObject.format("YYYY-MM-DD") == scheduleForCalendar.schedule.date) {@>
                                <li>
                                    <!--selectedScheduleArr에 있는 모멘트들과 비교해서 같은 일정 있으면
                                        selected 클래스를 줌-->
                                    <a href="#" class="schedule_hour
                                    <@ for(var i = 0; i < selectedScheduleObjectArr.length; i++) {@>

                                        <@ if(selectedScheduleObjectArr[i].selectedMoment.format('YYYY-MM-DD HH')== scheduleForCalendar.schedule.date+' '+scheduleForCalendar.schedule.hour) {@>
                                        selected
                                    <@}} @>"
									   data-schedule_no="<@=scheduleForCalendar.schedule.scheduleNo @>"
                                       data-schedule="<@=scheduleForCalendar.schedule.date+' '+scheduleForCalendar.schedule.hour @>">
                                        <!--화살표 버튼 클릭해서 .box_calendar가 다시 markup되더라도 selected되었던
                                        .schedule_hour에는 selected클래스를 주어 css효과가 유지되게 함-->
                                        <@=scheduleForCalendar.schedule.expression @>
                                    </a>
                                </li>
                                <@}}); @>
                            </ul>
                        </div>
                    </td>
                    <@}); @>
                </tr>
                <tr>
                    <@_.each(dateObjectArr2, function(dateObject) {@>
                    <td>
                        <div class="box_date">
                            <ul>
                                <li class="schedule_date"><@=dateObject.date() @></li>
                                <@_.each(scheduleListForCalendar , function(scheduleForCalendar) { @>
								<@if(dateObject.format("YYYY-MM-DD") == scheduleForCalendar.schedule.date) {@>
                                <li>
                                    <a href="#" class="schedule_hour
                                    <@for(var i = 0; i < selectedScheduleObjectArr.length; i++) {@>
									<@ if(selectedScheduleObjectArr[i].selectedMoment.format('YYYY-MM-DD HH')== scheduleForCalendar.schedule.date+' '+scheduleForCalendar.schedule.hour) {@>
									selected
                                    <@}} @>"
										data-schedule_no="<@=scheduleForCalendar.schedule.scheduleNo @>"
                                       data-schedule="<@=scheduleForCalendar.schedule.date+' '+scheduleForCalendar.schedule.hour @>">
                                        <@=scheduleForCalendar.schedule.expression @>
                                    </a>
                                </li>
                                <@}}); @>
                            </ul>
                        </div>
                    </td>
                    <@}); @>
                </tr>
            </tbody>
        </table>
    </div><!--//.box_calendar-->
</script>
	<script type="text/template" id="reviewsTmp">
<@_.each(list, function(review) {@>
<li data-no=<@=review.no @>>
	<div class="box_contents box_contents_review">
		<dl>
			<dt class="profile_img_name">
				<a href=""> <img class="profile_img" src="/img/profile/<@=review.userPhotoUrl @>"></img>
					</a><a id="profileName2" href=""><@=review.userNickName @></a>
			</dt>
			<dd class="review_1">
				<span><@=review.content @></span>
			</dd>
			
		</dl>
		<@ if(review.reply == null) { @>
			<button class="btn_reply btn_review_reply">답변하기</button>
		<@ } @>
	</div> 

	<@ if(review.reply != null) { @>	
		<div class="box_reply box_reply_review">
	 		<dl>
				<dt id="profileImgName2">
					<a href=""> <img class="profile_img" src="/img/profile/${service.userPhotoUrl }"></img>
					</a><a id="profileName3" href="">${service.userNickName }</a>
				</dt>
				<dd class="review_2">
					<span><@=review.reply @></span>
				</dd>
			</dl>
		</div>
	<@ } @>
</li>
<@ }) @>
<@=paginate @>
</script>
	<script type="text/template" id="questionsTmp">
<@_.each(list, function(question) {@>
    <li data-no=<@=question.no @>>
		<div class="question_box" data-no=<@=question.no @>>
			<div class="question_mark">Q.</div>
				<div class="question_1">
					<span><@=question.content @></span>
				</div>
				<@ if(question.reply == null) {@>
					<@ if(${service.userNo} == ${loginUser.no}) {@>
						<button class="btn_reply btn_question_reply">답변하기</button>
					<@}@>
				<@}@>
			</div>
			<@ if(question.reply != null) {@>
				<div class="question_box2">
					<div class="question_mark">A.</div>
					<div class="question_2">
						<span><@=question.reply @></span>
					</div>
				</div>
			<@}@>							
	</li>
<@})@> 
<@=paginate @>
</script>
	<script type="text/template" id="replyTmp">
		<div class="wrap_reply">
			<textarea id="inputReply" placeholder="답변 내용을 입력하시오"></textarea>
			<button class="btn_reply_confirm" data-no=<@=no @> data-boardType=<@=boardType @>>답변하기</button>
		</div>
</script>
	<c:import url="/WEB-INF/view/templates/card_level_first.jsp" />
	<c:import url="/WEB-INF/view/templates/js.jsp" />
	<script src="/js/calendar/moment-with-locales.min.js"></script>
	<script src="/js/calendar/tui-code-snippet.min.js"></script>
	<script src="/js/chart/raphael.min.js"></script>
	<script src="/js/chart/tui-chart.js"></script>
	<script src="/js/card_util.js?date=201804284"></script>
	<script src="/js/slick/slick.min.js"></script>
	<script src="/js/slick/slick_helper.js?date=20180428191011"></script>
	<script>
    var loginUserNo = '${loginUser.no}';
    var serviceNo = '${service.no}';
    var serviceUserNo = '${service.userNo}';
    var serviceRole = '${service.role}';
    var giverNo = serviceRole == 1 ? serviceUserNo : loginUserNo;
    var takerNo = serviceRole == 2 ? serviceUserNo : loginUserNo;
    var poom = '${service.poom}';

    var chartData = {
      categories: ["친절성", "성실성", "가격", "숙련도"],
      series: [{
        name: '평점',
        data: ['${scoreAndCountContract.scoreKind}',
            '${scoreAndCountContract.scoreHonest}',
            '${scoreAndCountContract.scorePrice}',
            '${scoreAndCountContract.scoreKnowhow}']
      }]
    };
  </script>
	<script src="/js/details_giver.js?date=2018052012223"></script>
	<script>
    //이미지 슬라이드 자바스크립트
    $('.profileserviceimg_wrap').imageSlide();
    /*
    cardUtil.dataset = {
    	"level" : 1,
    	"count" : 5
    };
    cardUtil.getCardList("ajax/recommendCardList.json", $("#cardBox"),
    		".img_box");
     */
    //SELECT no, service_no serviceNo, service_startdate serviceStartdate, service_day serviceDay, service_date serviceDate, regdate
    var schedules = new Array();
    var scheduleListForCalendar = new Array();

    // java list data to javascript array
    <c:forEach items="${schedules}" var="schedule">
    var json = new Object();
    json.scheduleNo = "${schedule.no}";
    json.serviceNo = "${schedule.serviceNo}";
    json.serviceStartdate = "${schedule.serviceStartdate}";
    json.serviceDay = "${schedule.serviceDay}";
    json.serviceDayOfWeek = "${schedule.serviceDayOfWeek}";
    json.serviceHour = "${schedule.serviceHour}";
    json.serviceHourExpression = "${schedule.serviceHourExpression}";
    json.serviceDate = "${schedule.serviceDate}";
    json.regdate = "${schedule.regdate}";
    schedules.push(json);
    </c:forEach>
    //console.log(schedules);

    var currDate = moment();
    var lastDate = currDate.clone().add(70, 'days');

    // 오늘 부터 두달 뒤까지 하루하루 비교하여 새로운 스케줄 배열을 만든다
    while (currDate.add(1, 'days').diff(lastDate) < 0) {

      var dayOfWeek = currDate.format('dddd').toLowerCase();
      //console.log(dayOfWeek);

      _.each(schedules, function(each) {
        // 요일별 반복 날짜 검사
        if (each.serviceDayOfWeek.length > 0) {
          if (dayOfWeek.indexOf(each.serviceDayOfWeek) == 0) {

            //{schedule : {date : "2018-04-23", hour: "08", expression: "08-09시"}},
            var schedule = {
              date: currDate.format("YYYY-MM-DD"),
              hour: each.serviceHour,
              expression: each.serviceHourExpression,
              scheduleNo: each.scheduleNo
            };
            scheduleListForCalendar.push({
              "schedule": schedule
            });
          }
        } else {
          // 단일 날짜 검사
          //console.log(each.serviceDate);
          //console.log(currDate.format("YYYY-MM-DD"));

          if (each.serviceDate.substring(0, 10) == currDate
                  .format("YYYY-MM-DD")) {
            var schedule = {
              date: currDate.format("YYYY-MM-DD"),
              hour: each.serviceHour,
              expression: each.serviceHourExpression,
              scheduleNo: each.scheduleNo
            };
            scheduleListForCalendar.push({
              "schedule": schedule
            });
          }
        }
      });
    }

    getScheduleCalendar(scheduleListForCalendar);

    //로그인 되어있으면 찜 확인
    if (loginUserNo > 0) {
      $.get("/ajax/likeService/check.poom", {
        serviceNo: serviceNo
      }).done(function(data) {
        console.log(data);
        if (data) {
          $("#likeBtn>i").attr("class", "fas fa-heart");
        }
      }).fail(function(data) {
        console.log('error', data);
      });
    }

    //'문의하기'버튼 클릭 시 
    $(".contents_board").on("click", ".btn_question_popup", function() {
      var questionContent = $("#inputQuestion").val();
      console.log(questionContent);

      if (questionContent.length > 0) {
        registerQuestion(serviceNo, 1, questionContent);
      } else {
        alert("문의 내용을 입력 하세요");
      }
    });

    // 답변하기 submit
    $(".contents_board").on("click", ".btn_reply_confirm", function() {
      var reply = $("#inputReply").val();
      $this = $(this);
      console.log('reply:', reply);
      updateReply(serviceNo, $this.data('no'), $this.data('boardtype'), reply);
    });

    cardUtil.dataset = {
      "level": 1,
      "count": 5,
      "role": 1
    };
    cardUtil.getCardList("/ajax/recommendationCardList.poom", $("#cardBox"),
            ".img_box");
  </script>
</body>
</html>