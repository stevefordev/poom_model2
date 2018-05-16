var dateObjectArr1 = new Array(), dateObjectArr2 = new Array();

// DB에서 받아 올 스케줄 리스트 (json형태)
var scheduleListForCalendar = []

// calendarBoxTmp 띄우기
var $calendarWrap = $("#calendarWrap"), $calendarBoxTmp = $("#calendarBoxTmp"), calendarBoxTmp = $calendarBoxTmp
        .html(), tmp = _.template(calendarBoxTmp);

var markup;
var changeDate = 0;

var selectedScheduleObjectArr = new Array();

function getScheduleCalendar(scheduleListForCalendar) {
  console.log('changeDate', changeDate);
  for (var i = 0; i <= 6; i++) {
    dateObjectArr1[i] = moment().add(i + changeDate, "days");
  }
  for (var i = 7; i <= 13; i++) {
    dateObjectArr2[(i - 7)] = moment().add(i + changeDate, "days");
  }

  console.log(dateObjectArr1);
  console.log(dateObjectArr2);
  console.log(scheduleListForCalendar);
  console.log(selectedScheduleObjectArr);
  console.log(tmp);

  markup = tmp({
    dateObjectArr1: dateObjectArr1,
    dateObjectArr2: dateObjectArr2,
    scheduleListForCalendar: scheduleListForCalendar,
    selectedScheduleObjectArr: selectedScheduleObjectArr
  });

  $(".box_calendar").remove();
  $calendarWrap.append(markup);

}// getScheduleCalendar() end

// getScheduleCalendar();

// 화살표 클릭 시 14, 28일 간격으로 이동

// $calendarSettingBtn jquery객체가 배열형태이므로 each문
$("#calendarWrap").on("click", '#calendarSettingBox li', function(evt) {
  var $calendarSettingBtn = $("#calendarSettingBox li");
  var index = $calendarSettingBtn.index(this);
  console.log("index:", $calendarSettingBtn.index(this));
  // 만일 클릭한 버튼에 disabled클래스가 있다면
  if ($(this).hasClass("disabled")) {
    // changeDate값이 변하지 않게 하고 종료
    return false;
  }
  console.log("index:", index);

  // 버튼 index에 따라 넘어가는 일수 다르게
  if (index != 2) {
    changeDate += (14 * index - 28);
  } else {
    // '+= 0'는 없으니 '= 0'으로 따로 빼줌
    changeDate = 0;
  }
  getScheduleCalendar(scheduleListForCalendar);

  console.log("changeDate:", changeDate);
  // 오늘 날짜로부터 56일 뒤까지만 일정 볼 수 있게 버튼 설정
  // 오늘보다 이전 날짜, 56일 뒤 일정은 볼 수 없음
  var $calendarSettingBtn = $("#calendarSettingBox li");
  if (changeDate == 0) {
    console.log("changeDate:", 1);
    $calendarSettingBtn.eq(0).addClass("disabled");
    $calendarSettingBtn.eq(1).addClass("disabled");
    $calendarSettingBtn.eq(2).addClass("disabled");
    $calendarSettingBtn.eq(3).removeClass("disabled");
    $calendarSettingBtn.eq(4).removeClass("disabled");
  } else if (changeDate == 14) {
    console.log("changeDate:", 2);
    $calendarSettingBtn.eq(0).addClass("disabled");
    $calendarSettingBtn.eq(1).removeClass("disabled");
    $calendarSettingBtn.eq(2).removeClass("disabled");
    $calendarSettingBtn.eq(3).removeClass("disabled");
    $calendarSettingBtn.eq(4).removeClass("disabled");
  } else if (changeDate == 28) {
    console.log("changeDate:", 3);
    $calendarSettingBtn.eq(0).removeClass("disabled");
    $calendarSettingBtn.eq(1).removeClass("disabled");
    $calendarSettingBtn.eq(2).removeClass("disabled");
    $calendarSettingBtn.eq(3).removeClass("disabled");
    $calendarSettingBtn.eq(4).removeClass("disabled");
  } else if (changeDate == 42) {
    console.log("changeDate:", 4);
    $calendarSettingBtn.eq(0).removeClass("disabled");
    $calendarSettingBtn.eq(1).removeClass("disabled");
    $calendarSettingBtn.eq(2).removeClass("disabled");
    $calendarSettingBtn.eq(3).removeClass("disabled");
    $calendarSettingBtn.eq(4).addClass("disabled");
  } else if (changeDate == 56) {
    console.log("changeDate:", 5);
    $calendarSettingBtn.eq(0).removeClass("disabled");
    $calendarSettingBtn.eq(1).removeClass("disabled");
    $calendarSettingBtn.eq(2).removeClass("disabled");
    $calendarSettingBtn.eq(3).addClass("disabled");
    $calendarSettingBtn.eq(4).addClass("disabled");
  } else {
    console.log("changeDate:", 6);
  }
  // if~else if end

});// $(this).on("click") end;

// 캘린더 시간 클릭 시
var $calendarWrap = $("#calendarWrap");
// ".schedule_hour"는 새로 생기는 요소이므로 on 메소드를 이용
$calendarWrap
        .on(
                "click",
                ".schedule_hour",
                function(evt) {
                  // a요소 기본기능 제한
                  evt.preventDefault();

                  // 해당 클릭 시간, 날짜 얻기
                  // data-schedule 끝 시간 숫자는 moment정렬을 위해 필요한 것이므로
                  // 잘라내고 text로 얻어온 시간 표현식을 붙인다.
                  var selectedDate = $(this).attr("data-schedule").slice(0, -2), selectedHour = $(
                          this).text();
                  
                  var secheduleNo = $(this).attr("data-schedule_no");
                  // 앞서 얻어 온 정보를 문자열로 정리
                  var selectedSchedule = "선택 일정 : " + selectedDate + " / "
                          + selectedHour;

                  // 선택되었던 시간이 아니라면
                  if (!($(this).hasClass("selected"))) {
                    // 해당 클릭 시간 정보를 담아 객체 생성
                    var selectedScheduleObject = {
                      // 나중에 배열에서 시간순으로 정렬하기 위해 moment()로 저장
                      selectedMoment: moment($(this).attr("data-schedule")),
                      expression: selectedSchedule,
                      no:secheduleNo
                    };

                    // 일정 선택 10개까지만 가능
                    if (selectedScheduleObjectArr.length < 10) {
                      // 위에서 만든 객체를 selectedScheduleObjectArr 배열에 삽입
                      selectedScheduleObjectArr.push(selectedScheduleObject);

                      // 배열 안에 있는 moment객체들을 이용해 시간순으로 정렬
                      selectedScheduleObjectArr.sort(function(a, b) {

                        return a.selectedMoment < b.selectedMoment ? -1
                                : a.selectedMoment > b.selectedMoment ? 1 : 0;

                      });

                      // append하기 전에 ul을 비움
                      $("#selectedScheduleBox ul").empty();

                      for (var i = 0; i < selectedScheduleObjectArr.length; i++) {
                        // 시간 순으로 정렬된 객체에서 시간 표현식(expression)을 얻어와 text로 표시
                        var $li = $("<li>").text(
                                selectedScheduleObjectArr[i].expression)
                                .addClass("schedule_selected");

                        $li.appendTo("#selectedScheduleBox ul");

                      }// for end

                      // 해당 시간 칸에 "selected" 클래스 toggle
                      $(this).toggleClass("selected");

                    } else {
                      // 10개가 넘어가면 alert창
                      alert("10개의 일정만 선택 가능합니다.");
                    }// if~else end

                  } else {// 선택된 시간을 다시 클릭했다면

                    for (var i = 0; i < $(".schedule_selected").length; i++) {
                      // 클릭 시 만든 문자열과 선택된 일정의 표현식을 비교
                      if (selectedSchedule == $(
                              ".schedule_selected:eq(" + i + ")").text()) {
                        // 같다면 해당 li를 제거하고
                        // selectedScheduleObjectArr 배열에서도 제거
                        $(".schedule_selected:eq(" + i + ")").remove();
                        selectedScheduleObjectArr.splice(i, 1);
                      }

                    }// for end

                    // 해당 시간 칸에 "selected" 클래스 toggle
                    $(this).toggleClass("selected");

                  }// if~else end

                });// $calendarWrap.on("click") end

// 하트버튼 클릭 시
var $likeBtn = $("#likeBtn");

$likeBtn.on("click", function() {
  var heart = $(this).children("i");

  if (heart.hasClass("far")) {
    heart.attr("class", "fas fa-heart");
  } else {
    heart.attr("class", "far fa-heart");
  }// if~else end
});// $likeBtn.on("click") end

// '계약서 보내기' 버튼 클릭 시 팝업
var $sendContractBtn = $("#sendContractBtn"), $detailsContractPopupWrap = $("#detailsContractPopupWrap"), $detailsContractPopup = $("#detailsContractPopup");
var $detailsSendContractBtn = $("#detailsSendContractBtn");

var $body = $("body");

$sendContractBtn.click(function() {
  $detailsContractPopupWrap.show();
  var scheduleElement = "";
  _.each(selectedScheduleObjectArr, function(schedule) {
    scheduleElement += "<span>" + schedule.expression.replace("선택 일정 :", "") + "</span>";
  });
  
  $("dd.schedule").html(scheduleElement);

  $detailsContractPopup.show();
});// $sendContractBtn.click() end

$detailsContractPopupWrap.click(function(evt) {
  if ($(evt.target).closest($detailsContractPopup).length === 0) {
    $detailsContractPopupWrap.hide();
    $detailsContractPopup.hide();
  }
});// $detailsContractPopupWrap.click() end

// 계약 보내기
$detailsSendContractBtn.click(function() {
  console.log(selectedScheduleObjectArr);
  var dataset = {
          "giverNo" : giverNo,
          "takerNo" : takerNo,
          "serviceNo" : serviceNo,
          "poom" : poom,
          "content" : $("#comment>textarea").val()
  };
  console.log(dataset);
  $.ajax({
    url: "/ajax/contract/register.poom",
    type: 'POST',
    dataType: "json",
    data: dataset,
    success: function(data) {
      console.log(data);
    },
    error: function(jqXHR, textStatus, errorThrown) {
      console.log(textStatus);
    },
    complete: function() {
      $detailsContractPopupWrap.hide();
    }
  });// end .ajax
  
})
// 게시판 탭 클릭 시
var $boardGnb = $("#detailsBoardGnb .board_gnb");

$boardGnb.click(function(evt) {
  evt.preventDefault();

  // on클래스 있는 요소들(탭, 내용)에서 on클래스 한번에 제거
  $("#detailsBoardWrap .on").removeClass("on");
  $(this).addClass("on");

  // a요소의 부모 li의 index
  var index = $(this).parent().index();

  // 클릭 li에 맞는 .box_board에 on클래스 추가
  $("#detailsBoardBox .box_board:eq(" + index + ")").addClass("on");

  var container = document.getElementById('chartArea');

  var options = {
    chart: {
      width: 333,
      height: 300
    },
    series: {
      showDot: false,
      showArea: false
    },
    plot: {
      type: 'circle'
    },
    legend: {
      visible: false
    },
    chartExportMenu: {
      visible: false
    }
  };

  var chart = tui.chart.radialChart(container, data, options);

});// $boardGnb.click() end

// '답변하기' 클릭 시 팝업
$(".contents_board").on("click", ".btn_reply", function() {
  $(this).next().show();
});

// '답변' 팝업창 이외의 곳 클릭하면 팝업창 사라짐
$(".contents_board").on("click", ".wrap_popup_reply", function(evt) {
  if ($(evt.target).closest(".popup_reply").length === 0) {
    $(".wrap_popup_reply").hide();
  }
});

// '답변' 팝업에서 등록 누르면 팝업창 사라지면서
// 답변 div 추가 & 답변하기 버튼 사라짐
var $replyTmp = $("#replyTmp"), replyTmp = $replyTmp.html(), tmp1 = _
        .template(replyTmp);

$(".contents_board").on("click", ".wrap_popup_reply .btn_reply_register",
        function() {
          // 팝업창 사라짐
          $(".wrap_popup_reply").hide();
          // 해당 버튼이 포함된 리뷰박스에 답변 div 추가
          var markup = tmp1();
          $(this).parents(".contents_board dl").append(markup);
          // '답변하기' 버튼 사라짐
          $(this).parents(".box_contents").children(".btn_reply").remove();
        });

// '문의하기' 팝업에서 등록 누르면 팝업창 사라지면서
// 문의 div 추가
var $questionTmp = $("#questionTmp"), questionTmp = $questionTmp.html(), tmp2 = _
        .template(questionTmp);

$(".contents_board").on("click",
        "#detailsQuestionPopupWrap .btn_question_register ", function() {
          // 팝업창 사라짐
          $("#detailsQuestionPopupWrap").hide();
          // 해당 버튼이 포함된 리뷰박스에 답변 div 추가
          var markup = tmp2();
          $(this).parents(".contents_board").children("ul").prepend(markup);
        });

function registerQuestion(serviceNo, userNo, content) {

  var dataset = {
    "serviceNo": serviceNo,
    "userNo": userNo,
    "content": content,
  };

  $.ajax({
    url: "/ajax/question/register.poom",
    type: 'POST',
    dataType: "json",
    data: dataset,
    success: function(data) {
      console.log(data);

      var tmp = _.template($("#questionTmp").html());

      // 탬플릿 생성
      var markup = tmp({
        "questions": data
      });
      $('#questionsWrap').html(markup);

    },
    error: function(jqXHR, textStatus, errorThrown) {
      console.log(textStatus);
    }
  });// end .ajax
}
