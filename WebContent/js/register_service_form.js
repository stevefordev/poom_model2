/*******************************************************************************
 * 
 * 
 */
var $registerService = $("#registerService");
var $role = $("input[name=role]");
var $title = $("input[name=title]");
var $title = $("input[name=title]");
var $area1 = $("input[name=area1]");
var $area2 = $("input[name=area2]");
var $detailAddress1 = $("#detailAddress1");
var $detailAddress2 = $("#detailAddress2");
var $latitude = $("input[name=latitude]");
var $longitude = $("input[name=longitude]");
var $category = $("input[name=categoryEng]");
var $tag = $("input[name=tag]"); // 콤마 형태로 스트링
var $poom = $("input[name=poom]");
var $photo = $("input[name=photo]");
var $contents = $("#contents");
var $scheduleList = $("input[name=scheduleList]");

/**
 * 지도 영역 kakao api http://apis.map.daum.net/web/5documentation/
 */

// 카카오 맵이 표시된 요소를 찾는다
var container = document.getElementById('map');

// 카카오 맵 표시전 옵션 설정
var options = {
  center: new daum.maps.LatLng(37.48131159886463, 126.95286468301894),
  level: 3
};

// 카카오 맵 표시
var map = new daum.maps.Map(container, options);

// 현재 접속 중인 지역으로 중심 설정
getMyLocation();

// 주소-좌표 변환 객체를 생성합니다
var geocoder = new daum.maps.services.Geocoder();

// 지도를 클릭한 위치에 표출할 마커입니다
var marker = new daum.maps.Marker({
  // 지도 중심좌표에 마커를 생성합니다
  position: map.getCenter()
});

// 지도에 마커를 표시합니다
marker.setMap(map);

// 현재 지도 중심좌표로 주소를 검색해서 표시합니다
// searchAddrFromCoords(map.getCenter(), displayAddrInfo);
searchDetailAddrFromCoords(map.getCenter(), displayAddrDetailInfo);
// 맵이 움직일때마다 주소 검색
daum.maps.event.addListener(map, 'idle', function() {
  searchDetailAddrFromCoords(map.getCenter(), displayAddrDetailInfo);
});

function searchDetailAddrFromCoords(coords, callback) {
  // 좌표로 법정동 상세 주소 정보를 요청합니다
  geocoder.coord2Address(coords.getLng(), coords.getLat(), callback);
}
function searchAddrFromCoords(coords, callback) {
  // 좌표로 행정동 주소 정보를 요청합니다
  geocoder.coord2RegionCode(coords.getLng(), coords.getLat(), callback);
}

function setLocationFromAddress(address) {
  geocoder.addressSearch(address, function(result, status) {

    // 정상적으로 검색이 완료됐으면
    if (status === daum.maps.services.Status.OK) {

      var coords = new daum.maps.LatLng(result[0].y, result[0].x);

      // 인포윈도우로 장소에 대한 설명을 표시합니다
      /*
       * var infowindow = new daum.maps.InfoWindow({ content: '<div
       * style="min-width:150px;text-align:center;padding:6px;">'+address+'</div>'
       * }); infowindow.open(map, marker);
       */
      // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
      map.setCenter(coords);
    }
  });
}

function displayAddrInfo(result, status) {
  if (status === daum.maps.services.Status.OK) {
    console.log(result);
    for (var i = 0; i < result.length; i++) {
      // region_type 값은 B or H
      console.log(result);
      if (result[i].region_type === 'B') {
        console.log(result[i].address_name);
        console.log(result[i].region_1depth_name);
        console.log(result[i].region_2depth_name);

        $area1.val(result[i].region_1depth_name);
        $area2.val(result[i].region_2depth_name);
        // $detailAddress2.val(result[i].region_2depth_name);
        break;
      }
    }
    var center = map.getCenter();
    $latitude.val(center.getLat());
    $longitude.val(center.getLng());
    marker.setPosition(center);
  }
}// end displayAddrInfo()

// 지도 상세 주소
function displayAddrDetailInfo(result, status) {
  if (status === daum.maps.services.Status.OK) {
    console.log(result);

    console.log(result[0].address);

    $detailAddress1.val(result[0].address.address_name);
    $area1.val(result[0].address.region_1depth_name);
    $area2.val(result[0].address.region_2depth_name);

    var center = map.getCenter();
    $latitude.val(center.getLat());
    $longitude.val(center.getLng());
    marker.setPosition(center);
  }
}// end displayAddrDetailInfo()

// pc 에서 접속 중인 현재 위치를 알아낸다
function getMyLocation() {
  if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(function(position) {
      console.log(position.coords.latitude);
      console.log(position.coords.longitude);

      // 알아낸 현재 위치로 카카오 맵 중심 설정
      setLocation(position.coords.latitude, position.coords.longitude);
    });
  } else {
    console.log("Geolocation is not supported by this browser.");
  }
}

// 입력 받은 위치로 카카오맵 중심 설정
function setLocation(lat, lng) {
  map.setCenter(new daum.maps.LatLng(lat, lng));
}

$("#detailAddress1,.addr_search").click(
        function() {
          new daum.Postcode({
            oncomplete: function(data) {
              // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

              // 각 주소의 노출 규칙에 따라 주소를 조합한다.
              // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
              var fullAddr = ''; // 최종 주소 변수
              var extraAddr = ''; // 조합형 주소 변수

              // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
              if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을
                // 경우
                fullAddr = data.roadAddress;

              } else { // 사용자가 지번 주소를 선택했을 경우(J)
                fullAddr = data.jibunAddress;
              }

              // 사용자가 선택한 주소가 도로명 타입일때 조합한다.
              if (data.userSelectedType === 'R') {
                // 법정동명이 있을 경우 추가한다.
                if (data.bname !== '') {
                  extraAddr += data.bname;
                }
                // 건물명이 있을 경우 추가한다.
                if (data.buildingName !== '') {
                  extraAddr += (extraAddr !== '' ? ', ' + data.buildingName
                          : data.buildingName);
                }
                // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
                fullAddr += (extraAddr !== '' ? ' (' + extraAddr + ')' : '');
              }

              // 우편번호와 주소 정보를 해당 필드에 넣는다.
              // $postcode.val(data.zonecode); //5자리 새우편번호 사용

              // alert(fullAddr);
              $detailAddress1.val(fullAddr);
              setLocationFromAddress(fullAddr);
              // 커서를 상세주소 필드로 이동한다.
              $detailAddress2.focus();
            }
          }).open();
        }); // 주소 검색

// end map
// *******************************************************************************/

//
var $serviceButton = $(".section.service button");
$category.val('edu');
/*
 * service button
 */
$(".section.service button").click(function() {
  // 서비스 버튼의 종류에 따라 버튼 색상 변경
  $serviceButton.removeClass("on");
  $(this).addClass("on");

  // 서비스 버튼에 따라 태그 인풋 기본 입력 변경
  removeAllTags();
  $tag.tagEditor('addTag', $(this).text());
  serviceImageLoad($(this).data('category'));
  $category.val($(this).data('category'));
})
// end category
// *******************************************************************************/
/*
 * tag input https://goodies.pixabay.com/jquery/tag-editor/demo.html
 * http://api.jqueryui.com/autocomplete/#option-source
 */
$tag.tagEditor({
  initialTags: ['#교육'], // 초기 입력
  maxTags: 5,
  maxLength: 10,
  delimiter: ', ', /* space and comma */
  placeholder: '서비스 관련 태그를 입력 하세요',
  onChange: function(field, editor, tags) {
    console.log(tags);
    console.log(field);
  },
  beforeTagSave: function(field, editor, tags, tag, val) {

    // $('#response').prepend('Tag ' + val + ' saved' + (tag ? ' over ' +
    // tag : '') + '.');
    // 특수문자를 제외하고 태그를 반환 하도록
    var replace = val.replace(/[^a-z0-9ㄱ-힣]/gi, '');
    if (replace.length > 0) {
      console.log(replace);
      getTagIdOrInsert(replace);

      return '#' + replace;
    }

  },
  beforeTagDelete: function(field, editor, tags, val) {
    // 삭제된 태그는 인풋에서 제외
    $.each($('input[name=tags]'), function(idx) {
      console.log(idx, $('input[name=tags]')[idx]);
      var $thisTagInput = $($('input[name=tags]')[idx]);
      if ($thisTagInput.data('tag') == val) {
        $thisTagInput.remove();
      }
    })
  },
  autocomplete: { // 자동 완성
    delay: 500, // show suggestions immediately
    position: {
      collision: 'down'
    }, // automatic menu position up/down / flip
    source: function(request, response) {
      console.log("request",request);
      $.ajax({
        url: "/ajax/service/getTagList.poom",
        dataType: "json",
        data: {"name" : request.term},
        success: function(data) {
          console.log(data);
          // 입력한 태그가 포함된 단어를 검색하여 리턴 받고
          // 자동완성 창에 보여준다
          /*
           * var matcher = new RegExp("^" +
           * $.ui.autocomplete.escapeRegex(request.term), "i");
           * response($.grep(data, function(item) { return
           * matcher.test(item.name); }).slice(0, 10));
           */

          // 태그 테이블 리스트 중에서 네임만 골라서 자동 완성 되도록
          var tags = $.map(data, function(item) {
            console.log(item.name);
            return item.name
          });
          console.log(tags);
          response(tags);
        },
        error: function(jqXHR, textStatus, errorThrown) {
          console.log(textStatus);
        }
      });
    }, // end source
    open: function(event, ui) {
      $(this).autocomplete("widget").width("300px");

    }, // end open
    create: function() {
      // access to jQuery Autocomplete widget differs depending
      // on jQuery UI version - you can also try .data('autocomplete')
    }// end create
  }
// end autocomplete
});
//인풋태그 요소를 만든다. 태그가 추가 될때마다 계속 생성
createInputTags(62,'교육');
// $tag.tagEditor('addTag', '#교육');

var $sectionTagDetail = $('.section.tag>.section_detail');
/*
 * $sectionTagDetail.on('click', function() {
 * console.log($tag.tagEditor('getTags')[0].tags.length); if
 * ($tag.tagEditor('getTags')[0].tags.length == 5) { alert("태그는 최대 5개까지 입력
 * 가능합니다."); } })
 */
// 태그 전체 삭제
function removeAllTags() {
  var tags = $tag.tagEditor('getTags')[0].tags;
  for (i = 0; i < tags.length; i++) {
    $tag.tagEditor('removeTag', tags[i]);
  }
}// end removeAllTags

// 디비에서 태그를 검색하고 없으면 삽입하고 tagid 를 리턴 받는다
function getTagIdOrInsert(name) {
  $.ajax({
	type:"POST",
    url: "/ajax/service/getTagList.poom",
    dataType: "json",
    data: {
      'isEqual': 1,
      'name': name
    },
    success: function(data) {
      console.log(data);
      createInputTags(data.no, data.name);
    },
    error: function(jqXHR, textStatus, errorThrown) {
      console.log(textStatus);
    }
  });
}

function createInputTags(tagId, name) {
  console.log('createInputTags');
  $("<input></input>").attr({
    type: "hidden",
    name: "tags",
    value: tagId,
    'data-tag': '#' + name
  }).appendTo($registerService);
}
// end tags
// *******************************************************************************/
/*
 * photo section use slick lib 참고 http://kenwheeler.github.io/slick/
 */

var $imageBox = $("#imageBox");

function serviceImageLoad(category) {
  $imageBox.css({
    visibility: 'hidden'
  });
  $imageBox.attr('class', '');
  var imageInfo = {
    'category': category,
    'images': ['1.jpg', '2.jpg', '3.jpg', '4.jpg', '5.jpg']
  };
  var serviceImgTemp = _.template($("#serviceImgTemp").html());
  var imagesMarkup = serviceImgTemp({
    "imageInfo": imageInfo
  });
  $imageBox.html(imagesMarkup);
  // slick 이 준비되면 이미지 박스를 보여준다
  // $imageBox.on('init', function () {
  // $imageBox.css({visibility: 'visible'});
  // });

  // slick 을 바로 실행할 경우 이미지가 밀림 현상이 있음
  setTimeout(function() {
    $imageBox.on('init', function() {
      $imageBox.css({
        visibility: 'visible'
      });
    });
    $imageBox.slick({
      dots: true,
      infinite: true,
      speed: 300,
      slidesToShow: 1,
      centerMode: true,
      variableWidth: true,
      draggable: false
    });
  }, 300);
}
var category = 'edu';
serviceImageLoad(category);
imageBoxAddListener();
function imageBoxAddListener() {
  console.log('a');
  // 각 이미지 클릭시 체크 박스가 체크 되도록
  $imageBox.on('click', 'div.slick-list>div.slick-track>div.slick-slide',
          function() {
            var $inputCheckboxImges = $('input:checkbox[name="images[]"]');
            var $thisInput = $(this).children('input');
            console.log($thisInput.val());

            // 현재 누른 이미지가 이미 체크 되었는지 확인
            var isAlreadyChecked = $thisInput.prop("checked");

            // 입력된 모든 이미지 요소를 검사하여 체크를 토글 한다
            // slick 에서 무한 반복을 사용하기 위해 같은 이미지를 두번씩 사용하기때문에
            // 이 작업이 필요
            $inputCheckboxImges.map(function() {

              if ($thisInput.val() == this.value) {
                $(this).prop("checked", !isAlreadyChecked);
              }
              /*
               * else { $(this).prop("checked", false); }
               */
            })

            // 체크된 이미지를 전부 검색하여 json 형태로 보관 (여러장 선택 가능할때 더 의미가 있다)
            var vals = $inputCheckboxImges.filter(':checked').map(
                    function() {
                      if (this.id.length > 0) { return this.value; }
                    }).get();

            _.uniq(vals);
            $photo.val(vals);
            console.log(JSON.stringify(vals));
          });
} // end imageBoxAddListener

// 이미지 스크롤이 움직일때 실행된다
$imageBox.on('beforeChange', function(event, slick, currentSlide, nextSlide) {

});
// end images
// *******************************************************************************/
/** ********* start poom *********************************** */
$(".section.poom_set .fas.fa-question-circle").tooltip({
  // 참조 https://api.jqueryui.com/position/
  position: {
    my: "left-30 bottom",
    at: "center top-10",
    collision: "flip",
    using: function(position, feedback) {
      $(this).addClass(feedback.vertical).css(position);
    }
  }
});

/** ****************** schedule ******************* */

// 요일별에서 datepicker
var startDatepicker = new tui.DatePicker('#scheduleStart', {
  date: new Date(),
  language: 'ko',
  input: {
    element: '#datepickerStartdateInput',
    format: 'yyyy-MM-dd'
  }
});

// 날짜별에서 datepicker
var datepicker = new tui.DatePicker('#calendar', {
  minDate: 0,
  date: new Date(),
  language: 'ko',
  showToday: true,
  showAlways: true,
  format: 'yyyy-MM-dd'
});
startDatepicker.on('change', function() {
  var selectedDate = startDatepicker.getDate();
  // 오늘 이전 날짜 선택시 오늘 날짜로 강제 지정
  if (selectedDate < formatDate(new Date())) {
    startDatepicker.setDate(new Date());
    console.log(1);
  }

});

datepicker.on('change', function() {
  var selectedDate = datepicker.getDate();
  // 오늘 이전 날짜 선택시 오늘 날짜로 강제 지정
  if (selectedDate < formatDate(new Date())) {
    datepicker.setDate(new Date());
  }

});
// 시간을 뺀 날짜만으로 현재 날짜 만들기
function formatDate(date) {
  var d = new Date(date), month = '' + (d.getMonth() + 1), day = ''
          + d.getDate(), year = d.getFullYear();
  if (month.length < 2) month = '0' + month;
  if (day.length < 2) day = '0' + day;

  return new Date([year, month, day].join('-')).addHours(-9);
}
// date 에 시간 플러스
Date.prototype.addHours = function(h) {
  this.setHours(this.getHours() + h);
  return this;
}
var $schedule_tab = $(".schedule_tab");
$schedule_tab.click(function() {
  $('html, body').animate({
    scrollTop: $(".schedule_tab").offset().top
  }, 500);
});

var $schedule_set = $('.schedule_set');
var $dl_schedule = $('dl.schedule');
// 날짜 클릭시
$(".schedule_tab span.dates_tab").click(function() {
  $schedule_set.removeClass("on");
});
// 요일별 클릭시
$(".schedule_tab span.weeks_tab").click(function() {
  $schedule_set.addClass("on");
});

// 날짜 별에서 am pm 버튼을 누를때
var $time_wrap = $('dl.schedule .time_wrap');
$('dl.schedule .dates .pm').click(function() {
  $time_wrap.addClass("on");
});
$('dl.schedule .dates .am').click(function() {
  $time_wrap.removeClass("on");
});
// 요일 별에서 am pm 버튼을 누를때
$('dl.schedule .weeks .pm').click(function() {
  $time_wrap.addClass("on");
});
$('dl.schedule .weeks .am').click(function() {
  $time_wrap.removeClass("on");
});

// 각각 시간을 클릭할때
$('dl.schedule .time_wrap').on('click', '.time>li', function() {
  $(this).toggleClass("on");
});

var weeks = [{
  'ko': '월',
  'en': 'mon'
}, {
  'ko': '화',
  'en': 'tue'
}, {
  'ko': '수',
  'en': 'wed'
}, {
  'ko': '목',
  'en': 'thu'
}, {
  'ko': '금',
  'en': 'fri'
}, {
  'ko': '토',
  'en': 'sat'
}, {
  'ko': '일',
  'en': 'sun'
}];
var weeksTemp = _.template($("#weeksTemp").html());
var weeksMarkup = weeksTemp({
  "weeks": weeks
});
$(".am_time ol:hidden").append(weeksMarkup);
$(".pm_time ol:hidden").append(weeksMarkup);

/**
 * <pre>
 *  0. 고려해야 할 사항 
 *  1. 날짜별탭에서 현재 선택된 날짜 
 *  2. 날짜별탭에서 am pm 에서 각각 선택된(class='on') 시간 
 *  3. 요일별탭에서 시작 날짜 
 *  4. 요일별탭에서 am pm 에서 각각 선택된(class='on') 시간 
 * sample 
 * { 'repeatDate' :
 *                [ { 'week' : 'mon', 'times' : ['2:00','3:00'] }, 
 *                  { 'week' : 'tue', 'times' : ['2:00','3:00','4:00','5:00'] }, 
 *                  { 'week' : 'wed', 'times' : ['2:00','3:00'] }, 
 *                  { 'week' : 'thu', 'times' : ['1:00'] }, 
 *                  { 'week' : 'fri', 'times' : ['10:00'] }, 
 *                  { 'week' : 'sat', 'times' : ['12:00'] }, 
 *                  { 'week' : 'sun', 'times' : ['2:00','3:00'] } ], 
 * 'singleDate' : [ { 'date' : '2018-01-01', 'times' : ['2:00','3:00']}, 
 *                  { 'date' : '2018-02-09', 'times' : ['2:00','3:00'] }, 
 *                  { 'date' : '2018-07-20', 'times' : ['2:00'] }, 
 *                  { 'date' : '2018-08-01', 'times' : ['2:00','3:00','4:00','5:00'] } ] }
 * </pre>
 */

var scheduleLists = [1, 2, 3, 4, 5, 6];
var scheduleList = {
  'repeatDates': {
    'mon': {
      'kor': '월요일',
      'times': []
    },
    'tue': {
      'kor': '화요일',
      'times': []
    },
    'wed': {
      'kor': '수요일',
      'times': []
    },
    'thu': {
      'kor': '목요일',
      'times': []
    },
    'fri': {
      'kor': '금요일',
      'times': []
    },
    'sat': {
      'kor': '토요일',
      'times': []
    },
    'sun': {
      'kor': '일요일',
      'times': []
    }
  },
  'singleDates': []
};

// 일정 추가 버튼을 눌렀을때
$('.schedule_add').click(
        function() {
          // 날짜별 탭에서 선택된 date
          var selectedSingleDate = datepicker.getDate();
          var singleDate = moment(
                  [selectedSingleDate.getFullYear(),
                      selectedSingleDate.getMonth(),
                      selectedSingleDate.getDate()]).format('YYYY-MM-DD');

          // 요일별 탭에서 선택된 시작일
          var selectedStartDatepicker = startDatepicker.getDate();
          console.log(selectedSingleDate);

          // 날짜별에서 선택된 시간
          var timeArray = {};
          $.each($('.dates .time li.on'), function(index, value) {
            var $value = $(value);
            var time = $value.data('time');
            // 오전 오후인지 판단
            if ($value.parent('ul.time').hasClass('pm')) {
              time += 12;
            }
            var singleDateObject = {
              'date': singleDate,
              'times': [time]
            };

            // 이미 입력된 날짜인지 확인
            var filterSingleDate = scheduleList.singleDates.filter(function(
                    each) {
              return each.date === singleDate;
            });

            if (filterSingleDate.length > 0) {
              console.log('filterSingleDate.length > 0:'
                      + filterSingleDate.length);
              // 이미 입력된 날짜일 경우 시간만 업데이트
              filterSingleDate[0].times.push(time);
              // 시간의 중복 제거, 오름차순 정렬
              filterSingleDate[0].times = _.uniq(filterSingleDate[0].times);
              filterSingleDate[0].times.sort(function(a, b) {
                return a - b;
              });
              // 새로 받은 일정으로 배열 수정
              $.each(scheduleList.singleDates, function(index, each) {
                if (each.date === filterSingleDate.date) {
                  each.times = filterSingleDate[0].times;
                }
              });
            } else {
              // 한번도 입력되지 않는 날짜 인경우
              scheduleList.singleDates.push(singleDateObject);
            }

            // 날짜별로 오름차순 정렬
            scheduleList.singleDates.sort(function(a, b) {
              var result = a.date < b.date ? -1 : a.date > b.date ? 1 : 0;
              console.log(result);
              return result;
            });
          });

          // 요일별에서 선택된 시간
          $.each($('.weeks .time li.on'), function(index, value) {
            var $value = $(value);
            var time = $value.data('time');
            // 오전 오후인지 판단
            if ($value.parents('div').hasClass('pm_time')) {
              time += 12;
            }

            var week = $value.parents('ol').data('week');

            var startDatepicker = moment(
                    [selectedStartDatepicker.getFullYear(),
                        selectedStartDatepicker.getMonth(),
                        selectedStartDatepicker.getDate()])
                    .format('YYYY-MM-DD');

            scheduleList.repeatDates[week].times.push(time);
            scheduleList.repeatDates[week].times = _
                    .uniq(scheduleList.repeatDates[week].times);
            scheduleList.repeatDates[week].times.sort(function(a, b) {
              // console.log(a);
              // console.log(b);
              return a - b;
            });
          });

          setScheduleListForServer();
          // 선택일 정 탬플릿 입력
          var selectedScheduleTemp = _.template($("#selectedScheduleTemp")
                  .html());
          var markup = selectedScheduleTemp({
            "scheduleList": scheduleList
          });
          $(".schedule_view tbody").html(markup);
        })// end schedule_add btn

        // 서버로 넘기기 위해 배열을 다시 만든다
function setScheduleListForServer() {

  var schedules = [];
  console.log(scheduleList);
  // 반복 일정 정리
  _.each(scheduleList.repeatDates, function(schedule, key) {
    if (schedule.times.length > 0) {
      _.each(schedule.times, function(time) {
        schedules.push({
          "type": "repeat",
          "serviceStartdate": new Date($('input[name=startDate]').val() + ' 00:00:00').getTime(),
          "serviceDay": key + ('0' + time).slice(-2)
        })
      })
    }
  })
  // 단일 일정 정리
  _.each(scheduleList.singleDates, function(singleDate) {
    _.each(singleDate.times, function(time) {
      schedules.push({
        "type": "single",
        "serviceStartdate": '',
        "serviceDate": new Date(singleDate.date + ' ' + ('0' + time).slice(-2) + ':00:00').getTime()
      })
    })
  })

  console.log(JSON.stringify(schedules));
  // 서버로 넘기는 객체로 세팅
  $scheduleList.val("");
  $scheduleList.val(JSON.stringify(schedules));
}
// 선택된 날짜 삭제
$('dl.schedule dd .schedule_view> table').on('click', 'button', function() {
  // 단일 일정 관련
  var $td = $(this).parent('td');
  
  var time = $td.data('time');
  console.log($td.data('type'));
  
  if ($td.data('type') == 'singleDates') {
    var date = $td.data('date');
    
    $.each(scheduleList.singleDates, function(index, each) {
      if (each.date == date) {
        // 해당 시간을 삭제
        console.log(each.times);
        each.times = $.grep(each.times, function(v) {
          return v != time; 
        });
        console.log(each.times);
      }
      ;
    });
  } else {
    var week = $td.data('week');
    // 반복 일정 관련
    $.each(scheduleList.repeatDates, function(key, each) {
      if (key == week) {
        // 해당 시간을 삭제
        each.times = $.grep(each.times, function(v) {
          return v != time;
        });
        console.log(each.times);
      }
      ;
    });
  }  
  $td.remove();
  setScheduleListForServer();
  console.log(scheduleList);
}); // end delete btn


// end schedule
// *******************************************************************************/
/* textarea 에디터 입력 */

CKEDITOR.replace('contents', {
  on: {
    instanceReady : function(evt) {
      //evt.editor.setData("<strong>test</strong>");
    }
  }
});
// end
// editor*******************************************************************************/

/*******************************************************************************
 * <pre>
 * var $title = $(&quot;input[name=title]&quot;);
 * var $area1 = $(&quot;input[name=area1]&quot;);
 * var $area2 = $(&quot;input[name=area2]&quot;);
 * var $detailAddress1 = $(&quot;#detailAddress1&quot;);
 * var $detailAddress2 = $(&quot;#detailAddress2&quot;);
 * var $latitude = $(&quot;input[name=latitude]&quot;);
 * var $longitude = $(&quot;input[name=longitude]&quot;);
 * var $category = $(&quot;#category&quot;);
 * var $tag = $(&quot;input[name=tag]&quot;);
 * var $poom = $(&quot;input[name=poom]&quot;);
 * var $photo = $(&quot;input[name=photo]&quot;);
 * var $contents = $(&quot;input[name=contents]&quot;);
 * var $scheduleList = $(&quot;input[name=scheduleList]&quot;);
 * </pre>
 */

// 유효성 검사 */
$registerService.submit(function() {
  console.log('role:', $role.val());
  console.log('title:', $title.val());
  console.log('area1:', $area1.val());
  console.log('area2:', $area2.val());
  console.log('detailAddress1:', $detailAddress1.val());
  console.log('detailAddress2:', $detailAddress2.val());
  console.log('latitude:', $latitude.val());
  console.log('longitude:', $longitude.val());
  console.log('category:', $category.val());
  console.log('tag:', $tag.val());
  console.log('poom:', $poom.val());
  console.log('photo:', $photo.val());
  $contents.val(CKEDITOR.instances['content'].getData())
  console.log('content:', $contents.val());
  console.log('scheduleList:', JSON.stringify($scheduleList.val()));

  if (checkPhoto() == false) { return false; }
  if (checkSchedule() == false) { return false; }
  if (checkContent() == false) { return false; }

});

function checkSchedule() {
  if ($scheduleList.val().length === 0) {
    alert('최소 한개 이상의 일정을 추가하세요.');
    $('html, body').animate({
      scrollTop: $(".schedule_tab").offset().top
    }, 500);

    return false;
  }
}
function checkPhoto() {
  if ($photo.val().length === 0) {
    alert('최소 한개 이상의 사진을 선택하세요.');
    $('html, body').animate({
      scrollTop: $("dl.photo").offset().top
    }, 500);

    return false;
  }
}
function checkContent() {

  if ($contents.val().length === 0) {
    alert('내용을 입력하세요');

    return false;
  }
}
