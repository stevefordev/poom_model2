/*******************************************************************************
 * 서비스 카드의 이미지 slick 작동
 */
$.fn.imageSlide = function() {
  'use strict';
  $(this).each(function() {

    var $card_img_wrap = $(this);

    $card_img_wrap.css({
      visibility: 'hidden'
    });

    try {
      // slick 이 실행된 경우라면 제거후
      $card_img_wrap.slick('unslick');
    } catch (e) {
      // TODO: handle exception
    }

    setTimeout(function() {
      $card_img_wrap.on('init', function() {
        $card_img_wrap.css({
          visibility: 'visible'
        });
      });
      $card_img_wrap.slick({
        dots: true,
        infinite: true,
        speed: 300,
        slidesToShow: 2,
        variableWidth: true,
        draggable: false,
        prevArrow: $card_img_wrap.parent().find(".left_cursor"),
        nextArrow: $card_img_wrap.parent().find(".right_cursor")
      });
    }, 100);
  });// each() end

};