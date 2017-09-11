<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<c:set value="${pageContext.request.contextPath}" var="contextPath" scope="application"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<script>
		var contextPath = "${contextPath}";
		var userSeq = ${user.userSeq};
	</script>
	<title>Insert title here</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="${contextPath}/resources/assets/css/bootstrap.min.css" />
    <link rel="stylesheet" href="${contextPath}/resources/assets/css/owl.carousel.css" />
    <link rel="stylesheet" href="${contextPath}/resources/assets/css/magnific-popup.css" />
    <link rel="stylesheet" href="${contextPath}/resources/assets/css/font-awesome.css" />
    <link rel="stylesheet" href="${contextPath}/resources/assets/css/style.css" />
    <link rel="stylesheet" href="${contextPath}/resources/assets/css/responsive.css" />
    <link rel="stylesheet" href="${contextPath}/resources/custom/css/user/join.css" />
    
    <!-- Favicon -->
    <link rel="shortcut icon" href="${contextPath}/resources/assets/images/icon/favicon.png">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="${contextPath}/resources/assets/images/icon/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="${contextPath}/resources/assets/images/icon/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="${contextPath}/resources/assets/images/icon/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="${contextPath}/resources/assets/images/icon/apple-touch-icon-57-precomposed.png">
	
    <script type="text/javascript" src="${contextPath}/resources/assets/js/jquery.min.js"></script><!-- jQuery -->
    <script type="text/javascript" src="${contextPath}/resources/assets/js/bootstrap.min.js"></script><!-- Bootstrap -->
</head>
<body>
    <tiles:insertAttribute name="header" />
	<tiles:insertAttribute name="body" />
	<tiles:insertAttribute name="footer" />
</body>
</html>