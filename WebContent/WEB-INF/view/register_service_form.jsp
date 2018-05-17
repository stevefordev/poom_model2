<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>품등록</title>
<%@ include file="/WEB-INF/view/templates/link.jsp"%>
<link rel="stylesheet" href="/css/common/jquery.tag-editor.css">
<link rel="stylesheet" href="/css/common/jquery-ui.min.css">
<link rel="stylesheet" href="/css/slick/slick.css">
<link rel="stylesheet" href="/css/slick/slick-theme.css">
<link rel="stylesheet" href="/css/calendar/tui-date-picker.css">
<link rel="stylesheet" href="/css/register_service.css" />

<style>
#content {
	font-size: 19px;
}

.section {
	width: 980px;
	min-height: 70px;
}

dl.section {
	margin: auto;
	padding-top: 10px;
}

dl.section dt {
	line-height: 70px;
	height: 70px;
	width: 70px;
	padding: 0 0px 0 0px;
	text-align: center;
	float: left;
	clear: left;
}

dl.section dd {
	/*background: red;*/
	width: 880px;
	margin-left: 20px;
	min-height: 70px;
	position: relative;
	float: left;
}

#registerService input[type=text], input[type=number] {
	padding: 10px 20px;
	margin: 0px 0px;
	font-size: 16px;
	display: inline-block;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
	height: 50px;
}

#registerService .requiredMsg {
	color: red;
}

#registerService input[type=radio] {
	width: 1.5em;
	height: 1.5em;
}

/******************** start role 역할 ********************/
dl.role dd {
	line-height: 70px;
}

dl.role dd label {
	font-weight: bold;
	font-size: 24px;
	margin-right: 20px;
	cursor: pointer;
}

#registerService dl dd input.error {
	border: 1px solid #f00;
}

/******************** start title 제목 ********************/
#registerService dl.title dd input {
	width: 100%;
	margin-top: 10px;
}

/******************** start region 지역********************/
dl.region {
	height: 540px;
}

dl.region dd {
	
}
/*지역 인풋 박스 그룹*/
dl.region dd>div {
	width: 100%;
	padding-bottom: 10px;
}

dl.region dd>div>input {
	width: 100%;
	margin-top: 10px;
}

dl.region dd>div:nth-child(1)>input {
	width: 780px;
	margin-right: 20px;
	cursor: pointer;
}

dl.region dd>div:nth-child(1)>button {
	width: 80px;
	height: 50px;
	outline: none;
	border: none;
	border-radius: 10px;
	background: #828282;
	color: white;
	font-weight: bold;
	position: absolute;
	right: 0px;
	cursor: pointer;
}

dl.region dd .region_map {
	
}

#map {
	margin-top: 10px;
	width: 880px;
	height: 400px;
	border-radius: 6px;
	box-shadow: 2px 2px 10px 0 rgba(0, 0, 0, 0.3);
}
#regionMainList {
    width: 310px;
    height: 128px;
    background: #424242;
}

#regionSubList {
    width: 310px;
    height: 128px;
    background: #b4b4b4;
}
/*end region*/

/******************** start service 서비스 분야 ********************/
dl.service dd {
	
}

dl.service dd button {
	margin-top: 10px;
	display: inline-block;
	background-color: #828282;
	width:92px;
	border: none;
	color: white;
	padding: 14px 20px;
	text-align: center;
	text-decoration: none;
	font-size: 16px;
	border-radius: 2px;
	font-weight: bold;
	outline: none;
	cursor: pointer;
}

dl.service dd>button.on {
	background-color: #1f2b43;
}

/******************** section tag 태그********************/
/*마크업 인풋 박스는 삭제하고 tagEditor 를 삽입*/
dl.tag {
	overflow: hidden;
}
dl.tag dd>input {
	display: none;
}
/*tagEditor 영역*/
dl.tag dd>ul {
	margin-top: 10px;
	height: 50px;
}

dl.tag dd>ul>li {
	border-radius: 0px 10px 10px 0px;
	margin: 5px 0;
}
/*입력된 태그 개별*/
dl.tag dd>ul>li>div {
	height: 40px;
	font-size: 16px;
	line-height: 40px;
}
/*입력된 태그 개별(x버튼)*/
dl.tag dd>ul>li>div.tag-editor-tag {
	border-radius: 10px 0 0 10px;
}
/*새로 입력되는 인풋박스*/
#registerService dl.tag dd>ul>li input {
	margin: 5px 0;
	height: 38px;
}
/*자동완성 영역*/
.ui-menu.ui-autocomplete {
	border-radius: 10px;
	border: 1px solid rgba(0, 0, 0, 0.2);
	box-shadow: 2px 2px 2px rgba(0, 0, 0, 0.2);
}

.ui-menu .ui-menu-item {
	height: 40px;
	font-size: 20px;
	line-height: 40px;
}

.ui-state-active, .ui-widget-content .ui-state-active, .ui-widget-header .ui-state-active,
	a.ui-button:active, .ui-button:active, .ui-state-active.ui-button:hover
	{
	height: 40px;
	background-color: #828282;
	border: none;
	border-radius: 10px;
}

dl.section.tag>dd.section_info {
	margin-left:90px;
	color : #626262;
	font-size: 16px;
	height:30px;
	min-height:0px;
}
/******************** section photo 사진********************/
dl.photo {
	height: 350px;
}

dl.photo dd {
	width: 880px;
	height: 330px;
	background: #3f475a;
	position: relative;
	margin-top: 10px;
}

#imageBox {
	width: 720px;
	height: 240px;
	top: 40px;
	position: relative;
	margin: auto;
	visibility: hidden; /*slick 이 로딩 될때까지 보이지 않게 설정*/
}

#imageBox div.slick-slide {
	position: relative;
	margin: 0px 10px;
	cursor: pointer;
}
/*기본 체크박스 마크업은 삭제*/
#imageBox div.slick-slide input[type=checkbox] {
	display: none;
}
/*기본 체크 박스 UI 를 이미지로 대체*/
#imageBox div.slick-slide input[type=checkbox]+label {
	position: absolute;
	top: 12px;
	right: 12px;
	display: inline-block;
	color: #f2f2f2;
	width: 19px;
	height: 19px;
	vertical-align: middle;
	background: url(/img/check_radio_sheet.png) left top no-repeat;
	cursor: pointer;
}

#imageBox div.slick-slide input[type=checkbox]:checked+label {
	background: url(/img/check_radio_sheet.png) -19px top no-repeat;
}

#imageBox img {
	float: left;
	height: 200px;
	outline: none;
}

/******************** section poom set 품 설정********************/
dl.poom_set dd {
	
}

dl.poom_set dd>input {
	margin-top: 10px;
	font-size: 20px;
	padding-right: 2px;
}

dl.poom_set dd>i {
	padding: 0px 10px;
	cursor: help;
}

.ui-tooltip {
	width: 200px;
	text-align: center;
	box-shadow: none;
	padding: 0;
	background: #828282;
	color: #fff;
	font-weight: bold;
	left: 50px;
}

.ui-tooltip-content {
	position: relative;
	padding: 1em;
}

.ui-tooltip-content::before {
	content: "";
	position: absolute;
	border-style: solid;
	display: block;
	left: 20px;
}

.bottom .ui-tooltip-content::before {
	bottom: -10px;
	border-color: #828282 transparent;
	border-width: 10px 10px 0;
}

/******************** start schedule 일정 ********************/
dl.schedule {
	min-height: 580px;
	overflow: hidden;
}

dl.schedule dd .schedule_set {
	float: left;
}
/*날짜별,요일별 탭*/
dl.schedule dd .schedule_tab {
	margin-top: 10px;
	color: #fff;
	cursor: pointer;
}

dl.schedule dd .schedule_tab>span {
	display: inline-block;
	width: 198px;
	height: 50px;
	line-height: 50px;
	text-align: center;
	border-radius: 25px 25px 0 0;
	font-weight: bold;
}
/*날짜별 탭을 각각 클릭시 배경색변경*/
dl.schedule dd .dates_tab, dl.schedule dd .schedule_set.on .weeks_tab {
	background: #3f475a;
}

dl.schedule dd .schedule_set.on .dates_tab, dl.schedule dd .weeks_tab {
	background: #828282;
}

/*날짜별 내용 박스모델*/
dl.schedule dd .dates {
	width: 400px;
	min-height: 360px;
}

#calendar {
	height: 360px;
}

#calendar .tui-datepicker th, #calendar .tui-datepicker td {
	vertical-align: middle;
}

dl.schedule dd .dates>.time_wrap {
	padding-top: 10px;
	min-height: 100px;
	text-align: center;
	font-size: 14px;
	cursor: pointer;
	border-collapse: collapse;
}

dl.schedule dd .time_wrap>ul {
	
}

/*날짜별 ampm 탭*/
dl.schedule dd .dates .ampm {
	width: 55px;
	height: 84px;
	float: left;
}

dl.schedule dd .dates .ampm>div {
	padding: 1px;
	height: 40px;
	line-height: 40px;
}
/*날짜별 각각의 시간*/
dl.schedule dd .dates div>.time>li {
	width: 55px;
	height: 40px;
	line-height: 40px;
	float: left;
	padding: 1px;
	background: #fff;
	color: #000;
	outline: 1px solid #ccc;
}

/*각각의 시간 호버시*/
dl.schedule dd .schedule_set .time>li:hover {
	background: #edf4fc
}
/*각각 시간 클릭됬을때*/
dl.schedule dd .schedule_set .time>li.on {
	background: #3f475a;
	color: white;
}

dl.schedule dd .dates .ampm {
	outline: 1px solid #aaa;
	color: white;
}
/*am pm 탭 각각 클릭 했을때*/
dl.schedule dd .time_wrap .am, dl.schedule dd .time_wrap.on .pm {
	background: #3f475a;
}

dl.schedule dd .time_wrap .pm, dl.schedule dd .time_wrap.on .am {
	background: #828282;
}

/*날짜별 탭*/
dl.schedule dd .dates, /*요일별 탭 클릭시*/ 
dl.schedule dd .schedule_set.on .weeks,
	/*날짜별 탭에서 am 시간*/ 
dl.schedule dd .dates .time_wrap div>.am.time,
	/*날짜별 탭에서 pm 클릭시 */ 
dl.schedule dd .dates .time_wrap.on div>.pm.time,
	/*요일별 탭에서 am 클릭시*/ 
dl.schedule dd .weeks .time_wrap div>.am_time,
	/*요일별 탭에서 pm 클릭시*/ 
dl.schedule dd .weeks .time_wrap.on div>.pm_time {
	display: block;
}

dl.schedule dd .weeks, dl.schedule dd .schedule_set.on .dates, dl.schedule dd .dates .time_wrap div>.pm.time,
	dl.schedule dd .dates .time_wrap.on div>.am.time, /*요일별 탭에서 pm 클릭시 */
	dl.schedule dd .weeks .time_wrap.on div>.am_time, /*요일별 탭에서 am 클릭시 */
	dl.schedule dd .weeks .time_wrap div>.pm_time {
	display: none;
}

#calendar {
	
}

/* 날짜별에 나오는 데이트피커 설정*/
#calendar .tui-datepicker {
	
}

#calendar .tui-datepicker, #calendar .tui-datepicker .tui-calendar {
	width: 398px;
}
/* 요일별에 나오는 데이트피커 설정*/
#scheduleStart .tui-datepicker, #scheduleStart .tui-datepicker .tui-calendar
	{
	width: 400px;
	height: 360px;
	line-height: 40px;
}

#scheduleStart .tui-is-selected, #calendar .tui-is-selected {
	background: #3f475a;
}

/*요일별 시작일*/
dl.schedule dd .schedule_start>div {
	height: 70px;
	line-height: 70px;
	text-align: center;
}

dl.schedule dd .schedule_start>div:nth-child(1) {
	width: 120px;
	float: left;
}

dl.schedule dd .schedule_start input {
	width: 280px;
}
/* 요일별 시작 날짜 인풋  */
#scheduleStart>#datepickerStartdateInput {
	margin-top: 10px;
}
/*요일별 한 주간 시간*/
dl.schedule dd .weeks {
	width: 400px;
	height: 700px;
}

/**/
dl.schedule dd .weeks .time_wrap .ampm>div {
	float: left;
	width: 200px;
	text-align: center;
	height: 30px;
	line-height: 30px;
	border-radius: 4px;
	color: #fff;
	cursor: pointer;
}
/*요일별 시간 영역(월화수목금 + 시간)*/
dl.schedule dd .weeks div>div>ol {
	width: 400px;
	float: left;
}
/*요일별에서 월화수목금토일*/
dl.schedule dd .weeks ol>li.week span {
	display: block;
	width: 55px;
	height: 83px;
	float: left;
	text-align: center;
	line-height: 84px;
	border-right: 1px solid #ccc;
	border-left: 1px solid #ccc;
	border-bottom: 1px solid #ccc;
}

/*요일별 탭에 나오는 요일, 시간 */
dl.schedule dd .weeks ol li {
	float: left;
}
/*요일별 탭에 나오는 각각 시간 */
dl.schedule dd .weeks ol.time li {
	width: 55px;
	height: 40px;
	padding: 1px 0px 0px 1px;
	line-height: 40px;
	font-size: 14px;
	border-bottom: 1px solid #ccc;
	border-right: 1px solid #ccc;
	text-align: center;
	cursor: pointer;
}

dl.schedule div>button.schedule_add {
	background: #3f475a;
	outline: none;
	border: none;
	color: white;
	width: 400px;
	height: 50px;
	font-size: 20px;
	border-radius: 10px;
	box-shadow: 2px 2px 2px rgba(0, 0, 0, 0.5);
	cursor: pointer;
	clear: both;
}

/*선택된 일정 목록 테이블*/
dl.schedule dd .schedule_view {
	width: 448px;
	min-height: 570px;
	float: left;
	margin-left: 30px;
}

dl.schedule dd .schedule_view>table {
	border-radius: 25px;
	margin-top: 10px;
	width: 450px;
	background: #2e7965;
}

dl.schedule dd .schedule_view>table th {
	color: white;
	font-weight: bold;
}

dl.schedule dd .schedule_view>table, dl.schedule dd .schedule_view>table th,
	dl.schedule dd .schedule_view>table td {
	height: 50px;
	line-height: 50px;
}

dl.schedule dd .schedule_view>table td {
	padding-left: 20px;
	background: #ccc;
	border-top: 1px solid #1f2b43;
	border-bottom: 1px solid #1f2b43;
	position: relative;
}

dl.schedule dd .schedule_view>table td>button {
	display: block;
	width: 30px;
	height: 30px;
	position: absolute;
	right: 10px;
	top: 10px;
	cursor: pointer;
	color: #fff;
	background: rgba(256, 0, 0, 0.5);
	border: none;
	border-radius: 2px;
	outline: none;
	font-weight: bold;
}

dl.schedule dd .schedule_view>table td:hover button {
	background: rgba(256, 0, 0, 1);
}
/******************** start contents ********************/
.section.contents {
	min-height: 280px;
	overflow: hidden;
}

.section.contents>dd {
	margin-top: 10px;
}

.section.contents textarea {
	width: 880px;
	padding: 30px 30px;
	margin: 0px 0px;
	display: inline-block;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
	height: 280px;
	font-size: 24px;
	background: #ccc;
	outline: none;
	resize: none;
	font-family: "Noto Sans KR", serif;
}
/******************** start submit btn ********************/
.section.submit {
	position: relative;
	margin: auto;
}

.section.submit div {
	padding-top: 10px;
	position: absolute;
	right: 10px;
	display: inline-block;
}

.section.submit input {
	background: #3f475a;
	outline: none;
	border: none;
	color: white;
	width: 80px;
	height: 50px;
	font-size: 20px;
	border-radius: 2px;
	box-shadow: 2px 2px 2px rgba(0, 0, 0, 0.5);
	cursor: pointer;
}

.section.submit input.cancel {
	background: #828282;
}
</style>
</head>
<body>
	<%@ include file="/WEB-INF/view/templates/header.jsp"%>
	<form id="registerService" method="post" action="/service.poom">
		<input type="hidden" name='area1' id='area1' /> <input type="hidden"
			name='area2' id='area2' /> <input type="hidden" name='latitude'
			id='latitude' /> <input type="hidden" name='longitude'
			id='longitude' /> <input type="hidden" name='photo' /> <input type="hidden" name='categoryEng' />
<input type="hidden" name='scheduleList' /> 
		<dl class="section role">
			<dt class="section_title">역할</dt>
			<dd class="section_detail">
				<input type="radio" id="roleGiver" name="role" value="1" checked>
				<label for="roleGiver">품을 주고 싶어요</label> <input type="radio"
					id="roleTaker" name="role" value="2"> <label
					for="roleTaker">품을 받고 싶어요</label>
			</dd>
		</dl>
		<dl class="section title">
			<dt class="section_title">제목</dt>
			<dd class="section_detail">
				<input class="title" type="text" name="title"
					placeholder="내용을 입력해주세요" required>
			</dd>
		</dl>
		<dl class="section region">
			<dt class="section_title">지역</dt>
			<dd class="section_detail ">
				<div>
					<input id="detailAddress1" type="text" name="detailAddress1"
						placeholder="특별시, 도, 군, 구, 동" readonly="readonly">
					<button type="button" class="addr_search">주소검색</button>
				</div>

				<div>
					<input id="detailAddress2" type="text" name="detailAddress2"
						placeholder="상세 주소를 입력하세요. ex) 102동 1135호">
				</div>
				<div class="region_map">
					<div id="map"></div>
				</div>
			</dd>
		</dl>
		<dl class="section service">
			<dt class="section_title">분야</dt>
			<dd class="section_detail">
				<button type="button" data-category='edu' data-tagId=62 class="on">교육</button>
				<!--
            -->
				<button type="button" data-category='house' data-tagId=60>가사</button>
				<!--
            -->
				<button type="button" data-category='delivery' data-tagId=61>심부름</button>
			</dd>
		</dl>
		<dl class="section tag">
			<dt class="section_title">태그</dt>
			<dd class="section_detail">
				<input type="text" name='tag' required>
			</dd>
			<dd class="section_info">
				<span>※ 태그는 최대 5개까지 입력 가능합니다.</span>
			</dd>
		</dl>
		<dl class="section photo">
			<dt class="section_title">사진</dt>
			<dd class="section_detail">
				<div id="imageBox"></div>
				<!--//#imageBox -->
			</dd>
		</dl>
		<dl class="section poom_set">
			<dt class="section_title">품</dt>
			<dd class="section_detail">
				<input type="number" maxlength="5" min="1" max="65535"
					placeholder="10" value="10" name='poom' required> <i
					class="fas fa-question-circle"
					title="품이란 시간당 기본 가중치 입니다. 10품 X 2시간  = 20 코인"></i>
			</dd>
		</dl>
		<dl class="section schedule">
			<dt class="section_title">일정</dt>
			<dd class="section_detail">
				<div class="schedule_set">
					<div class="schedule_tab">
						<span class="dates_tab">날짜별</span>
						<!--
                     -->
						<span class="weeks_tab">요일별</span>
					</div>
					<div class='dates'>
						<div id="calendar"></div>
						<div class="time_wrap">
							<div class="ampm">
								<div class="am">AM</div>
								<div class="pm">PM</div>
							</div>
							<div class="time">
								<ul class="am time">
									<li data-time='0' class="">00-01시</li>
									<li data-time='1' class="">01-02시</li>
									<li data-time='2' class="">02-03시</li>
									<li data-time='3' class="">03-04시</li>
									<li data-time='4' class="">04-05시</li>
									<li data-time='5' class="">05-06시</li>
									<li data-time='6' class="">06-07시</li>
									<li data-time='7' class="">07-08시</li>
									<li data-time='8' class="">08-09시</li>
									<li data-time='9' class="">09-10시</li>
									<li data-time='10' class="">10-11시</li>
									<li data-time='11' class="">11-12시</li>
								</ul>
								<ul class="pm time">
									<li data-time='0' class="">00-01시</li>
									<li data-time='1' class="">01-02시</li>
									<li data-time='2' class="">02-03시</li>
									<li data-time='3' class="">03-04시</li>
									<li data-time='4' class="">04-05시</li>
									<li data-time='5' class="">05-06시</li>
									<li data-time='6' class="">06-07시</li>
									<li data-time='7' class="">07-08시</li>
									<li data-time='8' class="">08-09시</li>
									<li data-time='9' class="">09-10시</li>
									<li data-time='10' class="">10-11시</li>
									<li data-time='11' class="">11-12시</li>
								</ul>
							</div>
						</div>
					</div>
					<div class='weeks'>
						<div class="schedule_start">
							<div>
								<label for="datepickerStartdateInput">시작일</label>
							</div>
							<div id="scheduleStart">
								<input id="datepickerStartdateInput" name="startDate"
									type="text">
							</div>
						</div>
						<div class="time_wrap">
							<div class="ampm">
								<div class="am">AM</div>
								<div class="pm">PM</div>
							</div>
							<div>
								<div class="am_time">
									<ol>

									</ol>
								</div>
								<div class="pm_time">
									<ol>

									</ol>
								</div>
							</div>
						</div>
					</div>
					<div>
						<button type="button" class="schedule_add">일정추가</button>
					</div>
				</div>
				<!--//end schedule_set -->
				<div class="schedule_view">
					<table border="1">
						<thead>
							<tr>
								<th>선택된 날짜</th>
							</tr>
						</thead>
						<tbody>

						</tbody>
					</table>
				</div>
				<!--//end schedule_view -->
			</dd>
			<!--//end section.schedule -->
		</dl>
		<dl class="section contents">
			<dt class="section_title">내용</dt>
			<dd class="section_detail">
				<textarea id='contents' name="content" placeholder="내용을 입력하세요~"
					required="required"></textarea>
			</dd>
		</dl>
		<div class="section submit">
			<div>
				<input type="submit" value="입력" /> <input class="cancel"
					type="reset" value="취소" />
			</div>
		</div>
	</form>
	<%@ include file="/WEB-INF/view/templates/footer.jsp"%>
	<!-- 알림 탬플릿 -->

	<script type="text/template" id="serviceImgTemp">
    <@ _.each(imageInfo.images,function(image, key){ @>
    <div>
        <img src="/img/<@=imageInfo.category@>/<@=image@>"/>
        <input type="checkbox" name="images[]" value="<@=image@>" id="image<@=key@>" />
        <label for="image<@=key@>"></label>
    </div>
    <@})@>
</script>
	<!--요일별에서 시간 리스트-->
	<script type="text/template" id="weeksTemp">
    <@ _.each(weeks, function(week){ @>
    <li class="week">
        <span><@=week.ko@></span>
        <ol data-week=<@=week.en@> class="time">
            <li data-time="0">00-01시</li>
            <li data-time="1">01-02시</li>
            <li data-time="2">02-03시</li>
            <li data-time="3">03-04시</li>
            <li data-time="4">04-05시</li>
            <li data-time="5">05-06시</li>
            <li data-time="6">06-07시</li>
            <li data-time="7">07-08시</li>
            <li data-time="8">08-09시</li>
            <li data-time="9">09-10시</li>
            <li data-time="10">10-11시</li>
            <li data-time="11">11-12시</li>
        </ol>
    </li>
    <@})@>
</script>
	<script type="text/template" id="selectedScheduleTemp">
    <@ _.each(scheduleList.repeatDates,function(schedule, key){@> 
    <@ if(schedule.times.length>0) { console.log(schedule.times)@>
    <@ _.each(schedule.times,function(time){ @>
    <tr>
        <td data-type='repeatDates' data-week=<@=key@> data-time=<@=time@>> [반복] 매주 <@=schedule.kor@> / <@=('0' + time).slice(-2)@>-<@=('0' + (time+1)).slice(-2)@> 시<button type="button">X</button></td>
    </tr>
    <@})@>
    <@} }) @>

    <@ _.each(scheduleList.singleDates, function(singleDate){ @>
    <@ _.each(singleDate.times,function(time){ @>
    <tr>
        <td data-type='singleDates' data-date=<@=singleDate.date@> data-time=<@=time@>>[단일] <@=singleDate.date@> / <@=('0' + time).slice(-2)@>-<@=('0' + (time+1)).slice(-2)@> 시<button type="button">X</button></td>
    </tr>
    	<@})@>
    <@})@>
</script>

	<%@ include file="/WEB-INF/view/templates/js.jsp"%>
	<script src="/js/calendar/moment-with-locales.min.js"></script>
	<script
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=555baa134395660cb83af73dbe87d218&libraries=services,clusterer,drawing"></script>
	<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
	<script src="/js/common/jquery.caret.min.js"></script>
	<script src="/js/common/jquery.tag-editor.min.js"></script>
	<script src="/js/common/jquery-ui.min.js"></script>
	<script src="/js/slick/slick.min.js"></script>
	<script src="/js/calendar/tui-code-snippet.min.js"></script>
	<script src="/js/calendar/tui-date-picker.min.js"></script>
	<script src="/js/ckeditor/ckeditor.js?a=3"></script>
	<script src="/js/ckeditor/config.js?a=4"></script>
	<script src="/js/register_service_form.js?date=1805175"></script>
	<script>
    $(function() {

    }); //end $(function())
  </script>
</body>
</html>
