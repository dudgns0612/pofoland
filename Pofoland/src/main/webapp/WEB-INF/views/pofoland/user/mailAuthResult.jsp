<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath}" var="contextPath" scope="application"/>
<html>
	<head>
		<script>
			var contextPath = "${contextPath}";
		</script>
	    <link rel="stylesheet" href="${contextPath}/resources/assets/css/bootstrap.min.css" />
	</head>
	<body>
		<div style="height: 750px; padding-top: 150px;">
			<div class="row">
				<div class="col-xs-12 text-center">
					<br/>
					<br/>
					<br/>
					<h2>${resultMsg}</h2>
					<c:if test="${code} == 0">
					<!-- 보류 메일 다시보내는 api 만들어야함 -->
						<button type="button">
							메일 다시 보내기
						</button>
					</c:if>
				</div>
			</div>
		</div>
	<body>
</html>