//찜 목록 탭 활성화
$left_side_tab_li.removeClass("on").eq(2).addClass("on");

//찜버튼 (하트버튼) 클릭 시 찜 목록에서 삭제
var $likelistContent_ul = $(".content_likelist ul");

$likelistContent_ul.on("click", ".box_heart", function () {
    if(confirm("해당 품을 찜 해제하시겠습니까?")) {
        //'예' 클릭한 경우
        $(this).children("i").attr("class", "far fa-heart");
        $(this).parents(".content_likelist li").remove();
    }
});

// 카드 리스트 호출
cardUtil.dataset = {
        "level": 2,
        "count": 5,
        "pageNum": 1
      };
cardUtil.getCardList("ajax/contractCardList.json",
        $(".content_likelist>ul"), '.profileserviceimg_wrap');

