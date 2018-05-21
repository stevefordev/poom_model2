<%@page import="com.coddington.poom.util.ResizeImageUtil"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.multipart.FileRenamePolicy"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	// 1) request객체(원래 있음 : JSP내장객체)

	//톰캣의 실제경로
	String root = application.getRealPath("/");

	// 2) 파일업로드 경로
	String uploadPath = root + "picEdit" + File.separator;

	// 3) 파일업로드시 최대사이즈(바이트) 
	int size = 1024 * 1024 * 100;

	// 4) 인코딩방식 지정

	// 5) fileRenamePolicy
	FileRenamePolicy frp = new DefaultFileRenamePolicy();

	//MultipartRequest객체 생성
	//(이때 파일업로드 됩니다.)
	MultipartRequest mr = new MultipartRequest(request, uploadPath, size, "UTF-8", frp);

	//프로필 사진 이름
	String profile = mr.getFilesystemName("picEdit");

	String original = mr.getOriginalFileName("picEdit");

	//upload폴더는 원본이
	//thumbs폴더에는 썸네일 이미지가

	//경로+파일이름
	String target = root + "thumbs" + File.separator + profile;
	System.out.println(uploadPath + profile);
	boolean result = ResizeImageUtil.resize(uploadPath + profile, target, 300);

	System.out.println(result);

	//해당유저의 프로필 사진을 update구문

	/*
		enctype이 multipart/form-data일경우
		우리가 지금까지 활용했던 
		request.getParameter()로 파라미터를
		얻어올 수 없습니다.
		
		그래서 cos라이브러리를 활용합니다.
		
	*/
%>
{"result":true,"src":"<%=profile%>"}

