//찜 목록 탭 활성화
$left_side_tab_li.removeClass("on").eq(2).addClass("on");

//찜버튼 (하트버튼) 클릭 시 찜 목록에서 삭제
var $likelistContent_ul = $(".content_likelist ul");

$likelistContent_ul.on("click", ".box_heart", function () {
	
	//ajax 작동한 이후로는 $(this)가 작동이 안되므로 
	//변수에 받아서 처리
	var $this = $(this);
	
	//console.log($this.data("no"));
	
    if(confirm("해당 품을 찜 해제하시겠습니까?")) {
        //'예' 클릭한 경우
    	
    	$.ajax({
    		url: "/ajax/deleteLikeService.json",
    		dataType: "json",
    		data: {
    			"userNo": userNo,
    			"serviceNo": $this.data("no"), 
    		},
    		type: "post",
    		error: function (xhr, err, code) {
    			alert(err);
    		},
    		success: function (data) {
    			
    			//console.log(data);
    			
    			if(data == true) {
    				
    				//console.log($this.data("no"));
    				
	    	        $this.parents(".content_likelist li").remove();
	    	        
    			}//if end
    	        
    		}//success end
    		
    	});//$.ajax() end
    	
    }//if() end
    
});//clikc() end

// 카드 리스트 호출
cardUtil.dataset = {
        "level": 2,
        "count": 5,
        "pageNum": 1
      };
cardUtil.getCardList("/ajax/getLikeService.json",
        $(".content_likelist>ul"), '.profileserviceimg_wrap');

