<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>계약 대시보드</title>
<%@ include file="/WEB-INF/view/templates/link.jsp"%>
<link rel="stylesheet" href="css/dashboard_template.css" />
<link rel="stylesheet" href="css/dashboard_contract.css?date=2018051803" />
<link rel="stylesheet" href="css/slick/slick.css" />
<link rel="stylesheet" href="css/slick/slick-theme.css" />
<link rel="stylesheet" href="css/card_giver_level_second.css?date=2018051601" />
<link rel="stylesheet" href="css/card_taker_level_second.css?date=2018051601" />
<link rel="stylesheet" href="css/popup_detailcontract.css?date=2018051802" />
<link rel="stylesheet" href="css/popup_contract_progressive.css?date=2018051805" />
<link rel="stylesheet" href="css/popup_score_taker.css?date=20180520023" />
<link rel="stylesheet" href="css/popup_score_giver.css?date=2018052002" />
<style>
</style>
</head>
<body>
	<%@ include file="/WEB-INF/view/templates/header.jsp"%>
	<%@ include file="/WEB-INF/view/templates/dashboard_template.jsp"%>
	
	<div class="wrap_dashboard">
		<div class="top_tab">
			<ul>
				<li class="on" data-status="0">대기 중인 계약</li>
				<li data-status="1">진행 중인 계약</li>
				<li data-status="9">완료된 계약</li>
			</ul>
		</div>
		<div class="box_contract">
			<div class="box_content">
				<div class="box_btn_service">
					<button class="btn_service btn_myservice on" data-type="receive">등록한 서비스</button>
					<button class="btn_service btn_otherservice" data-type="send">계약서 보낸 서비스</button>
				</div>
				<div class="contract_service">
					<ul>						
						 
					</ul>
				</div>
			</div>
			<!-- //.box_content  -->
		</div>
		<!-- //#contractWait  -->		
	</div>
	
	
	
	<script type="text/template" id="waitContractDetailPopupTmp">
	<div class="popup_bg">
		<div id="detailContractPopup" data-no="<@=contractDetailInfo.no @>" data-idx="<@=contractIdx @>">
			<dl>
				<dt>품 주는 사람</dt>
				<dd class="person giver">
					<img class="img_profile giver" 
						 src="/img/profile/<@=contractDetailInfo.giverProfile @>" 
						 alt="<@=contractDetailInfo.giverNickname@>" />
					<span class="nickname giver">
						<@=contractDetailInfo.giverNickname@>
						<@if(${loginUser.no} == contractDetailInfo.giverNo) {@> (나) <@}@>
					</span>
					
				</dd>
				<dt>품 받는 사람</dt>
				<dd class="person taker">
					<img class="img_profile taker" 
						 src="/img/profile/<@=contractDetailInfo.takerProfile @>" 
						 alt="<@=contractDetailInfo.takerNickname@>" />
					<span class="nickname taker">
						<@=contractDetailInfo.takerNickname@> 
						<@if(${loginUser.no} == contractDetailInfo.takerNo) {@> (나) <@}@>
					</span>
					
				</dd>
				<dt>지 역</dt>
				<dd id="area">
					
					<span id="area1"><@=contractDetailInfo.area1 @></span> 
					<span id="area2"><@=contractDetailInfo.area2 @></span>
				
				</dd>
				<dt class="info_schedule">일 정</dt>
				<dd class="info_schedule" id="schedule">
					
					<@_.each(contractDetailInfo.contractScheduleList, function (contractSchedule) {@>
						<div><@=contractSchedule.serviceDateMap.date @> <@=contractSchedule.serviceDateMap.fullHour @> </div>
					<@}) @>
					
				</dd>
				<dt class="info price">가 격</dt>
				<dd class="info price" id="price">
					
					<div id="coin"><@=(contractDetailInfo.poom*contractDetailInfo.contractScheduleList.length) @> 코인</div> 
					<div>(<@=contractDetailInfo.poom @>품 X <@=contractDetailInfo.contractScheduleList.length @>시간)</div>
					
				</dd>
				<dt>할 말</dt>
				<dd id="content">
					<div><@=contractDetailInfo.content @></div>
				</dd>
			</dl>
			<@if(contractType == "receive") {@>
			    <button class="btn_handle_contract" id="rejectBtn" data-type="4" data-location="popup">거절</button>
				<button class="btn_handle_contract" id="acceptBtn" data-type="1" data-location="popup">수락</button>
			<@} else {@>
			    <button class="btn_handle_contract" id="cancelBtn" data-type="100" data-location="popup">취소</button>
			<@}@>
		</div>
	</div>
	</script>
	<script type="text/template" id="progressContractDetailPopupTmp">
	<div class="popup container_contract_progressive">
		<div id="containerContractProgressive" data-no="<@=contractDetailInfo.no @>" data-idx="<@=contractIdx @>">
			<ul class="progressbar">
				<li class="start active "></li>
				<li class="middle active">
					<span>
						(<@=scheduleCheckCount @>회 / <@=contractDetailInfo.contractScheduleList.length @>회)
					</span>
				</li>
				<li class="end <@if(scheduleCheckCount == contractDetailInfo.contractScheduleList.length) {@> active <@}@>"></li>
			</ul>
			<div style="clear: both"></div> 
			<dl>
				<dt class="user giver <@if(contractDetailInfo.scoreUser != 0) {@> on <@}@>">품 주는 사람</dt>
				<dd class="user giver">
					<img src="/img/profile/<@=contractDetailInfo.giverProfile @>" class="profile" /> 
					<span>
						<@=contractDetailInfo.giverNickname@>
						<@if(${loginUser.no} == contractDetailInfo.giverNo) {@> (나) <@}@>
					</span>
				</dd>
				<dt class="user taker <@if(contractDetailInfo.scorePrice != 0) {@>on<@}@> ">품 받는 사람</dt>
				<dd class="user taker">
					<img src="/img/profile/<@=contractDetailInfo.takerProfile @>" class="profile" /> 
					<span>
						<@=contractDetailInfo.takerNickname@>
						<@if(${loginUser.no} == contractDetailInfo.takerNo) {@> (나) <@}@>
					</span>
				</dd>
				<dt class="info region"><@=contractDetailInfo.giverNickname@></dt>
				<dd class="info region">
					<div>
						<span>
							<@if(fullAddress.userNo == contractDetailInfo.giverNo) {@>
								<@=fullAddress.area1 @> <@=fullAddress.area2 @>
								<@=fullAddress.detailAddress1 @> <@=fullAddress.detailAddress2 @>
							 <@} else {@>
								<@=contractDetailInfo.area1 @> <@=contractDetailInfo.area2 @>
								<@=contractDetailInfo.detailAddress1 @> <@=contractDetailInfo.detailAddress2 @>
							<@} @>
						</span>
					</div>
				</dd>
				<dt class="info region"><@=contractDetailInfo.takerNickname@></dt>
				<dd class="info region">
					<div>
						<span>
							<@if(fullAddress.userNo == contractDetailInfo.takerNo) {@>
								<@=fullAddress.area1 @> <@=fullAddress.area2 @>
								<@=fullAddress.detailAddress1 @> <@=fullAddress.detailAddress2 @>
							 <@} else {@>
								<@=contractDetailInfo.area1 @> <@=contractDetailInfo.area2 @>
								<@=contractDetailInfo.detailAddress1 @> <@=contractDetailInfo.detailAddress2 @>
							<@} @>
						</span> 
					</div>
				</dd>
				<dt class="info schedule">일 정</dt>
				<dd class="info schedule">
					<@_.each(contractDetailInfo.contractScheduleList, function (contractSchedule) {@>
						<div class="<@if(moment(contractSchedule.serviceDate).isBefore(now)) {@> checked <@} @>">
							<@=contractSchedule.serviceDateMap.date @> 
							<@=contractSchedule.serviceDateMap.fullHour @> 
						</div>
					<@}) @>
				</dd>
				<dt class="info price">가 격</dt>
				<dd class="info price">
					<span id="coin"><@=(contractDetailInfo.poom*contractDetailInfo.contractScheduleList.length) @></span> 코인 
					<span>(<@=contractDetailInfo.poom @>품 X <@=contractDetailInfo.contractScheduleList.length @>시간)</span>
				</dd>
				<dt class="info msg">할 말</dt>
				<dd class="info msg">
					<p><@=contractDetailInfo.content@></p>
				</dd>
			</dl>
			<div class="btn_group">
				<button class="report">신고하기</button>
				<button class="termination">계약 중지</button>
				<button class="score <@if(!(isActiveScoreBtn)) {@> off <@} @> ">평점 남기기</button>
			</div>
		</div>
	</div>
	</script>
	
	<script type="text/template" id="popupScoreByGiverTmp">
	<div class="popup scoreByGiver">
	<div id="scoreByGiver" data-no="<@=contractNo @>" data-idx="<@=contractIdx @>">
		<form id="scoreByGiverRegisterForm">
			<input type="hidden" name="no" value="<@=contractNo @>"/>
			<div class="score">

				<div>
					<span>평점</span>
				</div>
				<div class="score_good check"></div>
				<div class="score_bad check"></div>
			</div>
			<div class="photo_register">
				<div>
					<label for="servicePhotoInput">사진등록</label> <input
						id="servicePhotoInput" type="file"> <span>사진등록까지
						해야 입금이 완료됩니다.</span>
				</div>
				<div class="photo">
					<img src="/img/blank_image.jpg"></img>
				</div>
			</div>
			<div class="btn_group">
				<button type="submit" class="submit">등록</button> 
				<button type="button" class="cancel">취소</button>
			</div>
		</form>
	</div>
	</div>
	</script>
	
	<script type="text/template" id="popupScoreByTakerTmp">
	<div class="popup scoreByTaker">
	<div id="scoreByTaker" data-no="<@=contractNo @>" data-idx="<@=contractIdx @>">
		<form  id="scoreByTakerRegisterForm">
			<input type="hidden" name="no" value="<@=contractNo @>"/>
			
			<dl>
				<dt class="score">
					<span>평점</span>
				</dt>
				<dd class="score">
					<div class="price">
						<div>가격</div>
						<label for='scoreGoodPrice' class="good"></label> 
                        <input id='scoreGoodPrice' data-score='good' type="radio" name="scorePrice" value="1" checked> 
                        <label for='scoreBadPrice' class="bad"></label>
      		            <input id='scoreBadPrice' data-score='bad' type="radio"	name="scorePrice" value="2">
					</div>
				
					<div class="kind">
						<div>친절</div>
						<label for='scoreGoodKind' class="good"></label> 
						<input id='scoreGoodKind' data-score='good' type="radio" name="scoreKind" value="1" checked> 
						<label for='scoreBadKind' class="bad"></label>
						<input id='scoreBadKind' data-score='bad' type="radio" name="scoreKind" value="2">
					</div>
					<div class="know_how">
						<div>숙련</div>
						<label for='scoreGoodKnowHow' class="good"></label> 
						<input id='scoreGoodKnowHow' data-score='good' type="radio" name="scoreKnowhow" value="1" checked> 
						<label for='scoreBadKnowHow' class="bad"></label> 
						<input id='scoreBadKnowHow' data-score='bad' type="radio" name="scoreKnowhow" value="2">
					</div>
					<div class="honest">
						<div>성실</div>
						<label for='scoreGoodHonest' class="good"></label> 
						<input id='scoreGoodHonest' data-score='good' type="radio" name="scoreHonest" value="1" checked> 
						<label for='scoreBadHonest' class="bad"></label> 
						<input id='scoreBadHonest' data-score='bad' type="radio" name="scoreHonest" value="2">
					</div>
				</dd>

			</dl>
			<div class="review">
				<dl>
				<dt>리뷰</dt>
				<dd>
					<textarea id="reviewContent" name="content" maxlength="100"></textarea>
				</dd>
				</dl>
			</div>
			<div class="btn_group">
				<button type="submit" class="submit">등록</button> 
				<button type="button" class="cancel">취소</button>
			</div>
		</form>
	</div>
	</div>
	</script>
	
	<script type="text/template" id="contractListTmpl1">
	<@_.each(data, function (contract, index) {@>
	<li data-no="<@=contract.no @>" data-idx="<@=index@>">
		<div class="contract_img" 
		     style="background-image : url('/img/profile/<@if(${loginUser.no} != contract.giverNo) {@><@=contract.giverProfile @><@} else {@><@=contract.takerProfile @><@}@>')">
		</div>
		<span>
			<@if(${loginUser.no} != contract.giverNo) @>
				<@=contract.giverNickname @>
			<@if(${loginUser.no} != contract.takerNo) @>
				<@=contract.takerNickname @>
		</span>
    	<div class="box_schedule">
			<@=contract.contractScheduleList[0].serviceDateMap.date @> 
			<p>
				<@=contract.contractScheduleList[0].serviceDateMap.fullHour @> 외 
				<@=(contract.contractScheduleList.length-1)@>개
			</p>
	    </div>
	    <strong><@=contract.poom @>품</strong>
	    <button class="btn_contract btn_contract_accept" data-type="1" data-location="list">수락</button>
        <button class="btn_contract btn_contract_reject" data-type="4" data-location="list">거절</button>
        <button data-idx="<@=index@>" class="btn_contract btn_contract_detail" >상세</button>
 	</li>
	<@}) @>
	</script>

	<script type="text/template" id="contractListTmpl2">
	<@_.each(data, function (contract, index) {@>
	<li data-no="<@=contract.no @>" data-idx="<@=index@>">
		<div class="contract_img" 
		     style="background-image : url('/img/profile/${loginUser.photoUrl }')">
		</div>
		<span>${loginUser.nickName}</span>
    	<div class="box_schedule">
			<@=contract.contractScheduleList[0].serviceDateMap.date @> 
			<p>
				<@=contract.contractScheduleList[0].serviceDateMap.fullHour @> 외 
				<@=(contract.contractScheduleList.length-1)@>개
			</p>	
	    </div>
	    <strong><@=contract.poom @>품</strong>
        <button class="btn_contract btn_contract_cancel" data-type="100" data-location="list">취소</button>
        <button data-idx="<@=index @>" class="btn_contract btn_contract_detail">상세</button>
 	</li>
	<@}) @>
	</script>

	<script type="text/template" id="contractListTmpl3">
	<@_.each(data, function (contract, index) {@>
	<li>
		<div class="contract_img" 
			 style="background-image : url('/img/profile/<@if(contractType == "receive") {@><@if(${loginUser.no} != contract.giverNo) {@><@=contract.giverProfile @><@} else {@><@=contract.takerProfile @><@} @><@} else {@>${loginUser.photoUrl}<@}@>')">
	    </div>
		<span>
			<@if(contractType == "receive") {@>
				<@if(${loginUser.no} != contract.giverNo) {@>
					<@=contract.giverNickname @>
				<@} else {@>
					<@=contract.takerNickname @>
				<@} @>
			<@} else {@>
				${loginUser.nickName}
			<@}@>
		</span>
    	<div class="box_schedule">
			<@=contract.contractScheduleList[0].serviceDateMap.date @> 
			<p>
				<@=contract.contractScheduleList[0].serviceDateMap.fullHour @> 외 
				<@=(contract.contractScheduleList.length-1)@>개
			</p>
	    </div>
	    <strong><@=contract.poom @>품</strong>
        <button data-idx="<@=index @>" class="btn_contract btn_contract_detail">상세</button>
 	</li>
	<@}) @>
	</script>

	<%@ include file="/WEB-INF/view/templates/footer.jsp"%>
	<%@ include file="/WEB-INF/view/templates/card_level_second_for_contract.jsp"%>
	<%@ include file="/WEB-INF/view/templates/js.jsp"%>
	<script src="/js/calendar/moment-with-locales.min.js"></script>
	<script>
		var loginUserNo = ${loginUser.no};
		
		//console.log(loginUserNo);
	</script>
	<script src="/js/dashboard_template.js"></script>
	<script src="/js/card_giver_level_second.js"></script>
	<script src="/js/card_taker_level_second.js"></script>
	<script src="/js/popup_contract_progressive.js"></script>
	<script src="/js/popup_score_giver.js?date=201805210111"></script>
	<script src="/js/popup_score_taker.js?date=201805210411"></script>
	<script src="/js/card_util.js?date=201804283"></script>
	<script src="/js/slick/slick.min.js"></script>
	<script src="/js/slick/slick_helper.js?date=201804291"></script>
	<script src="/js/dashboard_contracts.js?date=2018052011"></script>


	<script>
    
  	</script>
  	
</body>
</html>