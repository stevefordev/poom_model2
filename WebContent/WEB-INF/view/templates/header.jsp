<%@page import="com.coddington.poom.vo.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1"%>
<div id="header">
	<div class="logo">
		<a href="/index.poom"> <img src="/img/poom_logo.png">
		</a>
	</div>
	<div class="search">
		<form action="/search.poom" method="get" id="searchForm">
			<input type="text" name="query" class="search" value="${param.query }" placeholder="검색" /><i
				class="fas fa-search fa-2x"></i><input class="submit" type="submit" />
		</form>
	</div>
	<div id="loginBox" class="on">

		<c:choose>
			<c:when test="${loginUser!=null }">

				<div class="bell link">
					<i class="fas fa-bell fa-1x"></i>
					<article class="cRecentList news">
						<header>
							<h1>새소식</h1>
						</header>
						<div class="main _newsRegion">
							<ul data-viewname="DNewsListView" class="news_list">

							</ul>
						</div>
					</article>
				</div>

				<a href="/service/registerForm.poom">품 요청/등록</a>
				<a href="/profile.poom?no=${loginUser.no }">내프로필</a>
				<a href="" class="mypage"> <img
					src="/img/profile/${loginUser.photoUrl }" class="profile"
					onerror="this.src='/img/profile/profile_img.png'" /> <span>${loginUser.nickName } <i
						class="fas fa-angle-down"></i></span>
				</a>
				<ul class="mypage_drop">
					<li><a href="/dashboard_contract.poom">계약</a></li>
					<li><a href="/dashboard_coin.poom">코인</a></li>
					<li><a href="/dashboard_like_service.poom">찜목록</a></li>
					<li><a href="/dashboard_block.poom">차단 목록</a></li>
					<li><a href="/logout.poom">로그아웃</a></li>
				</ul>
			</c:when>
			<c:otherwise>
				<div id="linkBox" class="on">

					<!--giver와 taker들이 서비스 등록, 요청페이지로 넘어갈수있는 링크-->
					<a id="heroJoinBtn" href="">회원가입</a> <a id="heroLoginBtn" href="">로그인</a>
					<!--회원가입 로그인 팝업링크-->
					<div id="popupBackground">
						<!--팝업창 뒷배경-->
						<div id="loginJoinWrap">
							<div id="tabWrap">
								<!--회원가입 로그인 팝업창 2개 탭 div-->
								<div class="tab" id="joinTab">
									<h1>회원가입</h1>
								</div>
								<!-- -->
								<div class="tab on" id="loginTab">
									<!--디스플레이 인라인플록으로 수평정렬을 해서 주석처리로 탭사이 간격을 줄임-->
									<h1>로그인</h1>
								</div>
								<div style="clear: both"></div>
							</div>

							<!--회원가입 입력폼 시작-->
							<div id="joinInput">
								<div id="joinText">
									<h1>"품"에 오신것을 환영해요!</h1>
									<h2>
										회원으로 가입하시면</br> 품의 모든 서비스를 이용하실 수 있습니다
									</h2>
								</div>
								<form id="joinContent" method="post" action="/signup.poom">
									<div class="input_box" id="nameBox">
										<label for="name">이름</label>
										<div class="icon_box">
											<i class="fas fa-user-circle"></i>
										</div>
										<!--인풋박스 옆에 아이콘이 들어가는 박스-->
										<input id="name" autofocus required type="text" name="name"
											placeholder="이름입력" title="이름을 입력해주세요">
										<div class="alert_box" id="nameAlert">
											<span class="msg good">완벽한 이름이네요!</span> <span
												class="msg bad">한글과 영문 대 소문자를 2~15글자 이내로 사용하세요.</span>
										</div>
										<!--유효성검사할때 옳거나 잘못된입력을 했을때 해당하는 문구가 나오는 박스-->
									</div>
									<div class="input_box" id="phoneNumBox">
										<label for="phoneNum">전화번호</label>
										<div class="icon_box">
											<i class="fas fa-phone"></i>
										</div>
										<input id="phoneNum" required type="text" name="phone"
											placeholder="-을 제외한 전화번호를 입력하세요">
										<button id="phoneBtn" type="button" class="formBtn">전송</button>
										<div class="alert_box" id="phoneNumAlert">
											<span class="msg good">전화번호 입력완료</span> <span class="msg bad">정확한
												전화번호를 입력하세요</span>
										</div>
									</div>
									<div class="input_box" id="certificateBox">
										<input id="certificate" disabled required type="text"
											name="certificate">
										<button id="certificateBtn" type="button" class="formBtn">인증</button>
										<div class="alert_box" id="certificateAlert">
											<span class="msg good">입력완료</span> <span class="msg bad">4개의
												숫자를 입력하세요</span>
										</div>
									</div>
									<div class="input_box" id="nicknameBox">
										<label for="nickname">닉네임</label>
										<div class="icon_box">
											<i class="fas fa-user-circle"></i>
										</div>
										<input id="nickname" type="text" name="nickName" required
											placeholder="닉네임 입력" title="별명을 입력해주세요">
										<div class="alert_box" id="nicknameAlert">
											<span class="msg good">별명 입력완료</span> <span class="msg bad">한글과
												영문 대 소문자를 사용해서 1~4글자를 입력해주세요.</span>
										</div>
									</div>
									<div class="input_box" id="mailBox">
										<label for="mail">E-mail</label>
										<div class="icon_box">
											<i class="fas fa-envelope"></i>
										</div>
										<input id="mail" required type="text" name="email"
											placeholder="이메일">
										<div class="alert_box" id="mailAlert">
											<span class="msg good">이메일 입력완료</span> <span class="msg bad">정확한
												이메일을 기입해주세요</span>
										</div>
									</div>
									<div class="input_box" id="passwordBox">
										<label for="password">Password</label>
										<div class="icon_box">
											<i class="fas fa-key"></i>
										</div>
										<input id="password" required type="password" name="password"
											placeholder="비밀번호">
										<div class="alert_box" id="passwordAlert">
											<span class="msg good">좋은 비밀번호입니다.</span> <span
												class="msg bad">영문,숫자,특수문자를 혼용하여 8~16자를 입력해주세요.</span>
										</div>
									</div>
									<div class="input_box" id="recapchaBox">

										<div class="g-recaptcha"
											data-sitekey="6Lc0h1AUAAAAAHKNXTbxaJIhhnJb8YDjbNhwSPDR"
											data-callback="verifyCallback"></div>

									</div>
									<button id="joinBtn">회원가입</button>
								</form>
							</div>
							<!--회원가입 입력폼 끝-->
							<!--로그인 입력폼 시작-->
							<div id="loginInput" class="on">
								<form action="/login.poom" method="post">
									<div id="loginText">
										<h1>"품"에 오신것을 환영해요!</h1>
										<h2>
											"품"은 서로 간 시간을 거래할 수 있도록 하여</br> 모두의 경쟁력을 높이는 플랫폼 서비스입니다.
										</h2>
									</div>
									<div id="logininput_box">
										<div class="input_box" id="loginEmailBox">
											<label for="mail">E-mail</label>
											<div class="icon_box">
												<i class="fas fa-envelope"></i>
											</div>
											<input id="loginMail" autofocus required type="text"
												name="email" placeholder="이메일">
										</div>
										<div class="input_box" id="loginPasswordBox">
											<label for="password">Password</label>
											<div class="icon_box">
												<i class="fas fa-key"></i>
											</div>
											<input id="loginPassword" required type="password"
												name="password" placeholder="비밀번호">
										</div>
									</div>
									<button id="loginBtn">로그인</button>
								</form>
							</div>
						</div>
					</div>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
</div>
<div id="content">