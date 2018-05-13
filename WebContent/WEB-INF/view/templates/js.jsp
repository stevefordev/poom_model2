<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script src="js/common/jquery.js"></script>
<script src="js/common/underscore-min.js"></script>
<script>
	_.templateSettings = {
		interpolate : /\<\@\=(.+?)\@\>/gim,
		evaluate : /\<\@(.+?)\@\>/gim,
		escape : /\<\@\-(.+?)\@\>/gim
	};
</script>
<script src="js/header.js"></script>
<script src="js/popup_login_join.js?a=201804162"></script>
<script src='https://www.google.com/recaptcha/api.js?onload=onloadCallback'></script>