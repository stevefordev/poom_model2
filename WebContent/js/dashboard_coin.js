//코인대시보드 탭 활성화
$left_side_tab_li.removeClass("on").eq(1).addClass("on");

var $exchangeBtn = $(".btn.exchange"),
    $coinExchangePopupWrap = $("#coinExchangePopupWrap"),
    $coinExhangePopup = $("#coinExchangePopup"),
    $coinExchangeBtn = $("#coinExchangeBtn");

//버튼 클릭시 환전 팝업 show/hide
$exchangeBtn.click(function() {
    $coinExchangePopupWrap.show();
});//show

$coinExchangePopupWrap.click(function(evt) {
    //popup창 제외한 곳 클릭할 때 hide
    if($(evt.target).closest($coinExhangePopup).length === 0) {
        $coinExchangePopupWrap.hide();
    }
});//hide

$coinExchangeBtn.click(function(evt) {
    evt.preventDefault();
});

var $chargeBtn = $(".btn.charge"),
    $coinChargePopupWrap = $("#coinChargePopupWrap"),
    $coinChargePopup = $("#coinChargePopup");

//버튼 클릭시 충전 팝업 show/hide
$chargeBtn.click(function() {
    $coinChargePopupWrap.show();
});//show

$coinChargePopupWrap.click(function(evt) {
    //popup창 제외한 곳 클릭할 때 hide
    if($(evt.target).closest($coinChargePopup).length === 0) {
        $coinChargePopupWrap.hide();
    }
});//hide

//코인 그래프 (billboard)
var chart = bb.generate({
    data: {
        xs: {
        	"income": "x1",
        	"outcome": "x1"
        },
        columns: [
            ["x1", 1, 2, 3, 4, 5, 6,7,8,9,10,11,12,13,14,15,16],
            
            ["income", 30, 200, 100, 400, 150, 250,0,0,0,0,0,0,0,0,0,0],
            ["outcome", 20, 180, 240, 100, 190,0,0,0,0,0,0,0,0,0,0]
        ]
    },
    bindto: "#MultipleXYLineChart"
});


var $historyGnb = $(".history_gnb");

//history탭 클릭 시
$historyGnb.click(function(evt) {
    evt.preventDefault();

    $("#coinHistoryWrap .on").removeClass("on");
    $(this).addClass("on");

    var index = $(this).parent("li").index();

    $("#coinHistoryTableBox table:eq("+index+")").addClass("on");
});//$historyGnb.click() end