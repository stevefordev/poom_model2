<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

</div>
<div id="footer">
	<div id="footerContent">
		<div id="footerContentBox">
			<ul id="introduce">
				<li>소개</li>
				<li>코딩턴팀</li>
			</ul>
			<ul id="policy">
				<li>정책</li>
				<li>이용약관</li>
				<li>개인정보취급방침</li>
			</ul>
			<ul id="serviceCenter">
				<li>고객센터</li>
				<li>FAQ</li>
				<li>QnA</li>
			</ul>
		</div>
	</div>
	<div id="footerLogo"></div>
	<div class="sns">
		<a href="" class="facebook" target="_blank"> 페이스북 </a> <span>·</span>

		<a href="" class="instagram" target="_blank"> 인스타그램 </a> <span>·</span>

		<a href="" class="naver" target="_blank"> 블로그 </a> <span>·</span> <a
			href="" class="kakaostory" target="_blank"> 카카오스토리 </a>
	</div>
</div>

<!-- 이름 변경 수정 필요-->
<script type="text/template" id="newsTemp">
    <@ _.each(newsList,function(news){ @>
    <li data-viewname="DNewsItemView" class="-unread">
        <a href="#" class="_newsItem">
                                <span class="item -thum">
                                <img src="https://ssl.pstatic.net/cmstatic/webclient/dres/20180322173509/images/template/profile_60x60.png" width="40" height="40" alt="">
                                </span>
            <dl class="item -main">
                <dt class="gSrOnly">새소식</dt>
                <dd class="tit"> <strong> <span>싱숭샌&nbsp;</span>  <em> 댓글 <span class="comment">1</span>  </em></strong></dd>
                <dt class="gSrOnly">내용 요약</dt>
                <dd class="body "> [찜목록 위치] 내프로필로 이동합니다.</dd>
                <dt class="gSrOnly">댓글</dt>
                <dd class="comment">투표를 시작했습니다.</dd>
                <dt class="gSrOnly">밴드명 및 글 작성 시간</dt>
                <dd class="origin"><span>코딩턴 - 코딩의 여행을 떠나보자</span>13시간 전 </dd>
            </dl>

        </a>
    </li>
    <@})@>
</script>