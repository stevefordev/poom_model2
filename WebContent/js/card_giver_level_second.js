//2단카드 이미지 슬라이드
$(document).ready(function () {
//    $('.profileserviceimg_wrap').each(function () {
//        var profileserviceimg_wrap = $(this);
//        profileserviceimg_wrap.slick({
//            adaptiveHeight: true,
//            prevArrow: profileserviceimg_wrap.parent().find(".left_cursor"),
//            nextArrow: profileserviceimg_wrap.parent().find(".right_cursor"),
//            dots: true
//        });
//    });//each() end
});//$(document).ready() end


  
// //하트 클릭 시
// $(".box_heart").click(function () {
// if ($(this).children("i").hasClass("far")) {
// $(this).children("i").attr("class", "fas fa-heart");
// } else {
// $(this).children("i").attr("class", "far fa-heart");
// }//if~else end
// });//$("#content").on() end

// template으로 붙일 때 부모(조상)에 on()를 붙여야 해당 event리스너 작동
// 하트 클릭 시
$("#content").on("click", ".box_heart", function() {
	if ($(this).children("i").hasClass("far")) {
		$(this).children("i").attr("class", "fas fa-heart");
	} else {
		$(this).children("i").attr("class", "far fa-heart");
	}// if~else end
});// $("#content").on() end

