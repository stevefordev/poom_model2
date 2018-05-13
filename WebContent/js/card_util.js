var cardUtil = {

  /**
   * <pre>
   * url: 카드 리스 요청 ajax non view url 
   * level: 1 단 or 2단; 
   * $cardBox: 카드 리스트가 들어갈 박스
   * imgBox: 이미지 리스트가 들어갈 박스
   * </pre>
   */
  dataset : {}, 
  getCardList: function(url, $cardBox, imgBox) {

    /* 카드 호출 */
    $.ajax({
      url: url,
      dataType: "json",
      data: this.dataset,
      success: function(data) {
        console.log(data);

        var tmpName = cardUtil.dataset.level === 1 ? "#cardLevelFirstTmp"
                : "#cardLevelSecondTmp";
        var tmp = _.template($(tmpName).html());

        // 탬플릿 생성
        var markup = tmp({
          "list": data
        });
        $cardBox.html(markup);

        // slick 이미지 슬라이드 적용
        $(imgBox).imageSlide();
      },
      error: function(jqXHR, textStatus, errorThrown) {
        console.log(textStatus);
      }
    });// end .ajax

  }// end getCardList function
}// end cardUtil


// 팔로잉 버튼 선택시 색 변경
$("#cardBox, .contract_service, .pay_contents").on('click', '.heart, .box_heart', function() {
  console.log('.heart click');
  if ($(this).children("i").hasClass("far")) {
    $(this).children("i").attr("class", "fas fa-heart");
  } else {
    $(this).children("i").attr("class", "far fa-heart");
  }
}); // end $cardBox.on click