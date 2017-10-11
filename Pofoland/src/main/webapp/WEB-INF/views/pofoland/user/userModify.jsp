<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script type="text/javascript" src="${contextpath}/resources/custom/js/user/userModify.js"></script>
<script>
	var userPublicYn = "${user.userPublicYn}";
</script>
<!-- PAGE HEADER -->
<section id="page-header">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="section-title">
                    <h1>나의 정보수정</h1>
                </div>
            </div>
        </div>
    </div>
</section>
<div style="height: 650px; padding-top: 40px;">
	<!-- BODY -->
	<div class="container">
		<div class="row">
			<div class="col-md-8 margin20">
				<h2 class="heading">MY INFOMATION</h2>
				<p class="font-kr t_justify">회원정보를 수정합니다. 이미지변경은 이미지를 선택하여 주세요.</p>
				<hr>
				<h5>아아디 &nbsp;&nbsp;:&nbsp;&nbsp; ${user.userId}</h5><br />
				<h5>닉네임 &nbsp;&nbsp;:&nbsp;&nbsp; <input id="modifyNickname" class="form-control" type="text" value="${user.userNick}" style="display:inline; width:27%; "/>
				<input id="modifyNickCheckBtn" type="button" class="btn btn-warning" value="중복확인" style="margin-top: -5px;"/>
				</h5>
				<br />
				<h5>이메일 &nbsp;&nbsp;:&nbsp;&nbsp; ${user.userEmail}</h5><br />
				<h5>정보 공개여부</h5> 
				<select id="publicYnSelect"  class="selectpicker">
				</select>
			</div>
			<div class="col-md-4">
				<c:choose>
					<c:when test="${user.userProfileUrl != null && user.userProfileUrl != ''}">
						<img id="userInfoImage" class="user-modify-image" src="${contextPath}/user/${user.userSeq}/image" onclick="modifyProfile()"/>
					</c:when>
					<c:otherwise>
						<img id="userInfoImage" class="user-modify-image" src="${contextPath}/resources/custom/images/user/default_profile.jpg" onclick="modifyProfile()"/>
					</c:otherwise>
				</c:choose>
				<input type="file" id="modifyImage" class="upload-hidden" accept=".jpg, .png" style="display: none;" />
				<div class="col-md-12">
					<div class="col-md-4"></div>
					<div class="col-md-4">
						<input id="defaultImageBtn" class="btn btn-warning btn-xs" type="button" value="기본이미지로변경" />
					</div>
					<div class="col-md-4"></div>
				</div>
			</div>
			<div class="col-md-12">
				<div class="bottom">
					<div style="margin-top:100px;">
						<input id="modifySuccessBtn" class="btn btn-warning" type="button" value="수정완료" style="width: 24.50%; height: 40px;" />
						<input id="modifyCencleBtn" class="btn btn-warning" type="button" value="수정취소" style="width: 24.50%; height: 40px;" />
						<input id="modifyPwBtn" class="btn btn-warning" type="button" onclick="location.href='${contextPath}/user/modify/password'" value="비밀번호 수정" style="width: 24.50%; height: 40px;" />
						<input id="userDropBtn" class="btn btn-warning" type="button" value="회원탈퇴" style="width: 24.50%; height: 40px;" />
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
