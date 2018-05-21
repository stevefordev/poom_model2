//차단목록 탭 활성화
$left_side_tab_li.removeClass("on").eq(3).addClass("on");


var $blocklistContent = $(".content_blocklist ul");

//ul template 띄우기
var $blocklistTmp =$("#blocklistTmp"),
    blocklistTmp = $blocklistTmp.html(),
    tmp=_.template(blocklistTmp);

function createBlocklist() {
	
	$.ajax({
		url: "/ajax/getBlockList.json",
		datatype: "json",
		type: "post",
		error: function (xhr, err, code) {
			alert(err);
		},
		success: function (json) {
			
			//console.log(json);
			
			$blocklistContent.empty();
			
			var markup = tmp({
				"blockList": json
			});

		    $blocklistContent.prepend(markup);
		    
		}//success end
		
	});//$.ajax() end
	
   
}//createBlocklist() end

createBlocklist();


//x버튼 클릭 시 차단 목록에서 삭제

$blocklistContent.on("click", ".btn_exit", function () {
	
	var userName = $(this).prev().text();
	
    if(confirm(userName+"님을 차단 해제하시겠습니까?")) {
        //'예' 클릭한 경우
    	
    	//data-no속성에 담은 해당 relationship 번호값 출력
    	//console.log($(this).data("no"));
        
    	$.ajax({
    		url: "/ajax/deleteBlock.json",
    		datatype: "json",
    		data: {"no" : $(this).data("no")},
    		type: "post",
    		error: function (xhr, err, code) {
    			alert(err);
    		},
    		success: function (json) {
    			
    			//console.log(json);
    			
    			//성공적으로 차단해제되면
    			if(json) {
    				//console.log("차단 해제 성공");
    				//차단목록 다시 불러오기
    				createBlocklist();
    			}//if end 
    			
    		}//success end
    		
    	});//$.ajax() end
    	
    }//if end
    
});//$blocklistContent.on() end

