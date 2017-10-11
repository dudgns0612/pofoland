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
                    <h1>나의 패스워드 수정</h1>
                </div>
            </div>
        </div>
    </div>
</section>

<div style="height: 620px; padding-top: 40px;">
	<!-- BODY -->
	<div class="container">
		<div class="row">
			<div class="col-md-12 margin20">
				<h2 class="heading">MY INFOMATION</h2>
				<p class="font-kr t_justify">비밀번호를 변경합니다. 현재 비밀번호와 변경 비밀번호를 입력하세요.</p>
				<hr>
				<h5>현재 비밀번호 &nbsp;&nbsp;:&nbsp;&nbsp; <input type="password" id="oriModifyPw" class="form-control" style="display:inline; width:27%; "/></h5>
				<h5>변경 비밀번호 &nbsp;&nbsp;:&nbsp;&nbsp; <input type="password" id="newModifyPw" class="form-control" style="display:inline; width:27%; "/></h5>
				<h5>변경 비밀번호 확인 &nbsp;&nbsp;:&nbsp;&nbsp; <input type="password" id="newModifyPwCheck" class="form-control" style="display:inline; width:27%; "/></h5>
			</div>	
			<div class="col-md-12">
				<div class="bottom">
					<div style="margin-top:130px;">
						<input id="pwModifyBtn" class="btn btn-warning" type="button" value="수정완료" style="width: 48.50%; height: 40px;" />
						<input id="pwModifyCancelBtn" class="btn btn-warning" type="button" value="수정취소" style="width: 48.50%; height: 40px;" />
					</div>
				</div>
			</div>
		</div>
	</div>
</div>		