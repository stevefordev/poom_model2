
var $scoreByGiverRegisterForm = $('#scoreByGiverRegisterForm');
$scoreByGiverRegisterForm.on('click', '.score_good',function () {
    $(this).parent('.score').removeClass('on');
    // $score.removeClass('on');
})

$scoreByGiverRegisterForm.on('click', '.score_bad',function () {
    $(this).parent('.score').addClass('on');
    //$score.addClass('on');
})

//image/ 인지 알아내는 정규표현식 객체
var reg = /^image\//,
    $canvas = $("#canvas"), //canvas 제이쿼리 객체
    canvas = $canvas.get(0), // canvas 객체
    ctx = canvas.getContext("2d"),//context 객체
    reader = new FileReader();//파일리더 객체

$("#servicePhotoInput").on("change",function () {
    console.log('a');
    //input type=file요소는 files배열을 가지고 있음
    //각 번지에는 File객체가 들어가 있음
    var file = this.files[0];
    //File객체의 속성
    // - name : 파일의 이름
    // - size : 파일의 사이즈(바이트)
    // - lastModified : 최종수정일(UNIX타임)
    // - type : 파일타입(MIME타입)
    // console.log(file.name);
    // console.log(file.size);
    // console.log(file.lastModified);
    // console.log(file.type);
    //1) 제대로된 파일인지 확인
    if(file.size<=0) {
        alert("제대로 된 파일을 선택하세요!");
        return;
    }//if end
    //2) 이미지 파일만 가능하게 확인
    if(!reg.test(file.type)) {
        alert("이미지를 선택해주세요!!");
        return;
    }//if end
    //여기까지 왔다는 것은
    //파일사이즈가 0이 아니고(제대로 된 파일)
    //이미지파일만
    //3) FileReader객체를 통해서 읽어옵니다.
    reader.readAsDataURL(file);
    //4) 다 읽어와서 로딩되면
    reader.onload = function () {
        //alert("다 읽어왔습니다!");
        //5) 다 읽은 결과물(base64인코딩)을 얻음
        //var result =  reader.result;
        var result =  this.result;
        //console.log(result);
        //6) 이미지 객체 생성
        var img = new Image();
        //7) 이미지객체의 src속성에 대입
        img.src = result;
        //8) 이미지가 로딩되면
        img.onload = function () {
            if (this.width<canvas.width || this.height < canvas.height) {
                alert("이미지가 너무 작습니다. 가로 세로 400px 이상으로 선택 바랍니다.");
                return;
            }
            //$("body").append(img);
            //9) 캔버스에 그림을 그림
            ctx.clearRect(0, 0, canvas.width, canvas.height);
            ctx.drawImage(this,0,0,this.width,this.height,0,0,canvas.width ,canvas.height );

        }//onload 이벤트핸들러
    }//onload 이벤트핸들러
});//change() end


$("#scoreByGiverRegisterForm").submit(function(e) {

});

