/**
 * 
 */
var $searchForm = $('#searchForm');
$('#header #searchForm i').on('click', function () {

    $searchForm.submit();
})

var newsList = [
    {"poster":"poster1.jpg","name":"test1"},
    {"poster":"poster2.jpg","name":"test2"},
    {"poster":"poster3.jpg","name":"test3"},
    {"poster":"poster4.jpg","name":"test4"},
    {"poster":"poster5.jpg","name":"test5"},
    {"poster":"poster6.jpg","name":"test6"},
    {"poster":"poster7.jpg","name":"test7"}
];

var tmp = _.template($("#newsTemp").html());
var markup = tmp({"newsList":newsList});
$(".news_list").append(markup);


var $cRecentList = $(".bell .cRecentList");
$(".bell").click(function () {
    $cRecentList.toggleClass("show");
})

var $mypage_drop = $(".mypage_drop");
var $mypage = $(".mypage");
$mypage.click(function (e) {
	console.log('show');
	$mypage_drop.toggleClass("show");
    e.preventDefault();
    
})

var $bell = $(".bell");
$("body").click(function (evt) {

    //알림 이외의 클릭 일때 알림 화면을 감춘다
    var cRecentListLength = $(evt.target).closest($bell).length;
    if (cRecentListLength === 0 ) {
        $cRecentList.removeClass("show");
    }

    //마이페이지 드랍 박스 이외의 클릭 일때 드랍 박스를 감춘다
    var myPageDropLength = $(evt.target).closest($mypage).length;
    if (myPageDropLength === 0 ) {
        $mypage_drop.removeClass("show");
    }
})