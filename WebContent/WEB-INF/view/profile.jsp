
<%@page import="com.coddington.poom.vo.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>템플릿</title>
<c:import url="/WEB-INF/view/templates/link.jsp"></c:import>
<link rel="stylesheet" href="css/profile_following.css?date=201805141">
<link rel="stylesheet"
	href="css/card_giver_level_second.css?date=201804191" />
<link rel="stylesheet" href="css/card_taker_level_second.css" />
<link rel="stylesheet" href="css/slick/slick.css?date=201804191" />
<link rel="stylesheet" href="css/slick/slick-theme.css" />
<link rel="stylesheet" href="css/profile.css?data=20180516" />
<link rel="stylesheet" href="css/common/paginate.css?data=20180521" />
<style>
</style>
</head>
<body>
	<c:import url="/WEB-INF/view/templates/header.jsp"></c:import>

	<div id="profilePicBox" class="profile_content">
		<!--프로필상자 전체박스-->
		<div id="profilePic">
			<!--프로필 사진과 수정버튼 닉네임을 감싸는 박스-->
			<!-- 프로필 주인이 본인 페이지로 들어왔을때 수정버튼과 팔로잉 삭제 버튼이 있어야 하고
			다른 유저가 들어왔을때 수정버튼과 팔로잉 삭제 버튼이 없어야 하기에 c:if문을 사용했습니다
			   -->
			<div id="pic"
				style="background-image:url(/img/profile/${profile.photoUrl})"></div>
			<c:if test="${loginUser.no==profile.no}">
				<label class="fas fa-cog"> <input type="file" id="picEdit"
					name="picEdit"></label>
			</c:if>
			<div id="profileNickname" class="on">${profile.nickName}</div>
			<input name="nickName" form="updateNickNameForm" maxlength="4"
				id="nicknameEditInput" autofocus type="text">
			<form id="updateNickNameForm" action="/updateNickName.poom"
				method="post">
				<input name="no" type="hidden" value="${loginUser.no }">
			</form>
			<c:if test="${loginUser.no==profile.no}">
				<button id="nicknameEdit" class="edit_btn">
					<i class="fas fa-edit"></i>
				</button>
			</c:if>
		</div>
		<!--자기소개 박스-->
		<!--prifileIntrodnForm -->

		<div id="introduceText" class="on">${profile.introduction}</div>

		<form id="updateIntroductionForm" method="post"
			action="/updateintroduction.poom">
			<input name="no" type="hidden" value="${loginUser.no }">
			<textarea name="introduction" form="updateIntroductionForm" autofocus
				id="introduceEditInput"></textarea>
		</form>
		<c:if test="${loginUser.no==profile.no}">
			<!--자기소개글 수정버튼-->
			<button id="introduceEdit" class="edit_btn">
				<i class="fas fa-edit"></i>
			</button>
		</c:if>
		<!--<button id="introduceInput" class="input_btn">수정</button>-->
		<!--차단 or 팔로우버튼 박스-->
		<c:if test="${loginUser.no!=profile.no}">
			<div id="followBox">

				<form id="removeFollowing" action="removeFollowing.poom"
					method="post">
					<input type="hidden" name="profileNo" value="${profile.no}" />
				</form>
				<form id="registerFollowing" action="/registerFollowing.poom"
					method="post">
					<input type="hidden" name="profileNo" value="${profile.no}" />
				</form>
				<c:choose>
					<c:when test="${confirmFollowing}">
						<button form="removeFollowing" id="follow" class="on">팔로잉</button>
					</c:when>
					<c:otherwise>
						<button form="registerFollowing" id="follow">팔로우</button>
					</c:otherwise>
				</c:choose>


				<form id="removeBlock" action="/removeBlock.poom" method="post">
					<input type="hidden" name="profileNo" value="${profile.no}" />
				</form>
				<form id="registerBlock" action="/registerBlock.poom" method="post">
					<input type="hidden" name="profileNo" value="${profile.no}" />
				</form>
				<c:choose>
					<c:when test="${confirmBlock}">
						<button form="removeBlock" id="block" class="on">차단해제</button>

					</c:when>
					<c:otherwise>
						<button form="registerBlock" id="block">차단</button>
					</c:otherwise>
				</c:choose>


			</div>
		</c:if>
	</div>
	 
	<div class="profile_content">
		<div id="profileStatus">
			<!--해당 프로필유저의 계약과 관련한 내용이 들어있는 박스-->
			<ul id="score">
				<li>총 평점</li>
				<li class="emphasisContent">${scoreAndCountContracts.score }%(${scoreAndCountContracts.countGcontract + scoreAndCountContracts.countTcontract })</li>
			</ul>
			<ul id="contractNum">
				<li class="emphasisContent">계약 건수</li>
				<li>준 품 ${scoreAndCountContracts.countGcontract }</li>
				<li>받은 품  ${scoreAndCountContracts.countTcontract }</li>
			</ul>
			<ul id="reviewNum">
				<li class="emphasisContent">리뷰 ${countReviews } 개</li>
			</ul>
		</div>
		<div id="profileContent">
			<!-- 작성글 준품 페이지, 받은품 페이지 , 팔로잉 페이지 3개을 파라미터를 설정한 
			부분 입니다 -->
			<div id="profileMenu"
				class="<c:if test="${param.fpage!=null }">
			on
			</c:if> ">
				<ul class="tab_exchange">
					<li><a href="">작성글</a></li>
					<li><a href="">팔로잉</a></li>
				</ul>
				<div id="myWritingBox" class="">
					<ul class="tab_service">
						<li><a href=""> 준 품</a></li>
						<li><a href=""> 받은 품</a></li>
					</ul>
					<h4 class="screen_out">내가 준 품 목록</h4>
					<div class="list_giver_pay">
						<div class="pay_contents">
							<ul>
							</ul>
						</div>
						<!--pay_contents -->
					</div>
					<!--list_giver_pay -->
					<h4 class="screen_out">내가 받은 품 목록</h4>
					<div class="list_taker_pay">
						<div class="pay_contents">
							<ul>
							</ul>
						</div>
						<!--pay_contents -->
					</div>
					<!--list_taker_pay -->
				</div>
				<!--myWritingBox  -->
				<div id="followingBox">
					<h4 class="screen_out">팔로우 목록</h4>
					<!--팔로잉 부분 요소입니다-->
					<div class="box_page">
						<ul class="list_follow">
							<c:forEach items="${followList }" var="follower">
								<li class="item_user"><a
									href="/profile.poom?no=${follower.no}"> <img
										src="/img/profile/${follower.photoUrl }" /> <span>${follower.nickName }</span>
								</a> <c:if test="${loginUser.no==profile.no}">
										<a class="follower_delete"
											href="/removeFollowingByProfileUser.poom?no=${follower.no}">
											<i class="fas fa-times-circle"></i>
										</a>
									</c:if></li>
							</c:forEach>
						</ul>
						${paginate}
					</div>
				</div>
				<!--followingBox -->
			</div>
			<!--profileMenu -->
		</div>
		<!-- profileContent-->
	</div>

	<!--profile_content -->
	<c:import url="/WEB-INF/view/templates/footer.jsp"></c:import>
	<c:import url="/WEB-INF/view/templates/card_level_second.jsp"></c:import>
	<c:import url="/WEB-INF/view/templates/js.jsp"></c:import>
	<script>
		var profileUserNo = "${param.no}";
	</script>
	<script src="/js/card_giver_level_second.js"></script>
	<script src="/js/card_taker_level_second.js"></script>
	<script src="/js/slick/slick.min.js"></script>
	<script src="/js/slick/slick_helper.js?date=201804291"></script>
	<script src="/js/card_util.js?date=201804283"></script>
	<script src="/js/profile.js?date=2018052111"></script>
	<script>
		
	// 카드 리스트 호출
	cardUtil.dataset = {
	        "level": 2,
	        "count": 5,
	        "pageNum": 1,
	        "role" : 1,
	        "profileUserNo" : profileUserNo
	      };
	cardUtil.getCardList("/ajax/service/getUserServiceList.poom",
	        $(".list_giver_pay>.pay_contents>ul"), '.profileserviceimg_wrap');
	</script>

</body>
</html>