<%@page import="com.coddington.poom.vo.Relationship"%>
<%@page import="com.coddington.poom.dao.RelationshipsDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<!DOCTYPE html>
<html >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>차단 대시보드</title>
<%@ include file="/WEB-INF/view/templates/link.jsp"%>
<link rel="stylesheet" href="css/dashboard_template.css"/>
<link rel="stylesheet" href="css/dashboard_block.css"/>
<style>

</style>
</head>
<body>
<%@ include file="/WEB-INF/view/templates/header.jsp"%>
<%@ include file="/WEB-INF/view/templates/dashboard_template.jsp" %>


<div class="wrap_blocklist">
    <h2 class="screen_out">차단 목록</h2>
    <div class="content_blocklist">
        <ul>
        	
        </ul>
    </div><!--//.content_blocklist-->
</div><!--//.wrap_blocklist-->

<%@ include file="/WEB-INF/view/templates/footer.jsp"%>


<!--차단 목록 템플릿-->
<script type="text/template" id="blocklistTmp">
   <@_.each(blockList, function (block) {@>
		<li>
			<div class="profile_img" style="background-image : url('/img/profile/<@=block.photoUrl @>')"></div>
			<span class="user_nickname"><@=block.nickName@></span>
			<button class="btn_exit" data-no="<@=block.no @>">X</button>
		</li>
   <@}); @>
</script>

<%@ include file="/WEB-INF/view/templates/js.jsp"%>
<script src="/js/dashboard_template.js"></script>

<script src="/js/dashboard_block.js"></script>

<script>

	

</script>
</body>
</html>