//하트 클릭 시
$(".box_heart").click(function () {
    if ($(this).children("i").hasClass("far")) {
        $(this).children("i").attr("class", "fas fa-heart");
    } else {
        $(this).children("i").attr("class", "far fa-heart");
    }//if~else end
});//$("#content").on() end

//template으로 붙일 때 부모(조상)에 on()를 붙여야 해당 event리스너 작동
//하트 클릭 시
$("body").on("click", ".box_heart", function () {
    if ($(this).children("i").hasClass("far")) {
        $(this).children("i").attr("class", "fas fa-heart");
    } else {
        $(this).children("i").attr("class", "far fa-heart");
    }//if~else end
});//$("#content").on() end

