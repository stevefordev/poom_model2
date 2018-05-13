//차단목록 탭 활성화
$left_side_tab_li.removeClass("on").eq(3).addClass("on");

//x버튼 클릭 시 차단 목록에서 삭제
var $blocklistContent = $(".content_blocklist ul");

$blocklistContent.on("click", ".btn_exit", function () {
	
	var userName = $(this).prev().text();
	
    if(confirm(userName+"님을 차단 해제하시겠습니까?")) {
        //'예' 클릭한 경우
        $(this).parent().remove();
    }
});

//ul template 띄우기
var $blocklistTmp =$("#blocklistTmp"),
    blocklistTmp = $blocklistTmp.html(),
    tmp=_.template(blocklistTmp);

function createBlocklist() {
    var markup = tmp();

    $blocklistContent.prepend(markup);
}//createBlocklist() end

createBlocklist();