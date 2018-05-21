<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/template" id="cardLevelSecondTmp">
    <@ _.each(list,function(card){ @>
		<@ if(card.role == 1){ @>
			<li>
				<div class="profile_card card">
					<div class="profileserviceimg_box">
						<div class="box_heart" data-no=<@=card.no @>>
							<i class="<@=(card.isLike==true)?'fas':'far'@> fa-heart on"></i> 
							<span class="heart_count"><@=card.countLike@></span>
						</div>
						<button class="left_cursor cursor">
							<i class="fas fa-angle-left"></i>
						</button>
						<button class="right_cursor cursor">
							<i class="fas fa-angle-right"></i>
						</button>
						<div class="profileserviceimg_wrap">					
							<@ _.each(card.photos,function(photo) { @>
							<div class="profileservice_img">
								<img src="/img/service/<@=photo @>">
							</div>
							<@ })@>
						</div>
					</div>
					<!--//.profileserviceimg_box-->
					<a href="/service/details.poom?no=<@=card.no @>">
						<div class="service_inform">
							<ul>
								<li><@=card.title@></li>
								<li>
									<span class="icon_small <@=card.icon@>"></span>
									&nbsp;<@=card.scoreTotal@>%(<@=card.countScore@>)
									&nbsp;|&nbsp;<@=card.area1@>&nbsp;<@=card.area2@>
								</li>
								<li><@=card.schedule@></li>
								<li><@=card.contract@></li>
								<li><@=card.tags@></li>
								<li><@=card.poom@>품</li>
							</ul>
							<div class="profile_img" style="background-image : url('/img/profile/<@=card.profilePic @>')"></div>
							<div class="profile_name"><@=card.nickname@></div>
						</div> <!--//.service_inform-->
					</a>
					<button class="btn_contract_list">계약 목록</button>
				</div> <!--//.profile_card-->
				<ul class="contract_list">

				</ul>
			</li>
		<@ } else { @>
			<li>
				<div class="taker_profile_card card">
					<div class="taker_box">
						<div class="box_heart" data-no=<@=card.no @>>
							<i class="<@=(card.isLike==true)?'fas':'far'@> fa-heart on"></i> <span class="heart_count"><@=card.countLike@></span>
						</div>
						<div class="taker_img"></div>
						<div class="taker_name"><@=card.nickname@></div>
					</div>
					<!--//.taker_box-->
					<a href="/detail.poom?no=<@=card.no @>">
						<div class="taker_service_inform">
							<ul>
							<li><@=card.title@></li>
							<li><span class="icon_small <@=card.icon@>"></span>&nbsp;<@=card.scoreTotal@>%(<@=card.countScore@>)&nbsp;|&nbsp;<@=card.area1@>&nbsp;<@=card.area2@></li>
							<li><@=card.schedule@></li>
							<li><@=card.contract@></li>
							<li><@=card.tags@></li>
							<li><@=card.poom@>품</li>
							</ul>
						</div> <!--//.taker_service_inform-->
					</a>
					<button class="btn_contract_list">계약 목록</button>
				</div> <!--//.taker_profile_card-->
				<ul class="contract_list">

				</ul>
			</li>
		<@ } @>
    <@ }) @>
</script>
