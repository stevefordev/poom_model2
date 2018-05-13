
/*각각의 평점 클릭시*/
$("input[type=radio]").change(function(){
    var score = $(this).data('score');

    console.log(score);
    if(score === 'good') {
        $(this).parent('div').removeClass('on');
    } else {
        $(this).parent('div').addClass('on');
    }
}); //end

$("#scoreByTakerRegiterForm").submit(function(e) {

});

