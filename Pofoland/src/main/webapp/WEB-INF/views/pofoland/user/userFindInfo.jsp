<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="container process">
	<div class="row" style="margin-top : 195px; margin-bottom:265px;">
		<div class="col-md-6 margin20">
			<h2 class="heading">아이디 찾기</h2>
			<hr>
			<div style="min-height:170px;">
				<h5 style="padding-top: 20px;">이메일 &nbsp;&nbsp;:&nbsp;&nbsp; 
					<input id="idFindEmail" class="form-control" type="text" value="${user.userNick}" style="display:inline; width:50%; "/>
				</h5>
			</div>
			<input id="findIdBtn" type="button" class="btn btn-warning btn-lg" value="찾 기" style="margin-top: -5px;"/>
		</div>
		<div class="col-md-6">
			<h2 class="heading">비밀번호 찾기</h2>
			<hr>
			<div style="min-height:170px;">
				<h5 style="padding-top: 20px;">아이디 &nbsp;&nbsp;:&nbsp;&nbsp; 
					<input id="pwFindId" class="form-control" type="text" value="${user.userNick}" style="display:inline; width:50%; "/>
				</h5>
				<h5 style="padding-top: 20px;">이메일 &nbsp;&nbsp;:&nbsp;&nbsp; 
					<input id="pwFindEmail" class="form-control" type="text" value="${user.userNick}" style="display:inline; width:50%; "/>
				</h5>
			</div>
			<input id="findPwBtn" type="button" class="btn btn-warning btn-lg" value="찾 기" style="margin-top: -5px;"/>
		</div>
	</div>
</div>
<script>
	$(document).on('click','#findIdBtn',function(){
		var idFindEmail = $('#idFindEmail').val();
		
		if (idFindEmail == "") {
			alert("메일을 입력하여 주세요.");
			return;
		}
		
		//아이디 수정요청
		$.ajax({
			url : contextPath + '/user/find/id',
			type : 'GET',
			data : {'userEmail' : idFindEmail},
			dataType : 'JSON',
			success : function(response) {
				if (response.code) {
					alert("가입하신 메일로 비밀번호를 전송하였습니다.");
					$('#idFindEmail').val("");
				} else {
					alert("존재하지 않는 메일입니다.");
					$('#idFindEmail').val("");
					$('#idFindEmail').focus();
				}
			},
			error : function(error) {
				console.log(error);
			}
		})
	});
	
	$(document).on('click','#findPwBtn',function(){
		var pwFindId = $('#pwFindId').val();
		var pwFindEmail = $('#pwFindEmail').val();
		
		if (pwFindId == "") {
			alert("아이디를 입력하여 주세요.");
			return;
		}
		if (pwFindEmail == "") {
			alert("메일을 입력하여 주세요.");
			return;
		}
		
		//비밀번호 수정 요청
		$.ajax({
			url : contextPath + '/user/find/password',
			type : 'GET',
			data : {'userId' : pwFindId , 'userEmail' : pwFindEmail},
			dataType : 'JSON',
			success : function(response) {
				if (response.code) {
					alert("가입하신 메일로 아이디를 전송하였습니다.");
					$('#idFindEmail').val("");
				} else {
					alert("입력하신 정보가 맞지 않습니다. 아이디와 이메일을 확인하여주세요.");
					$('#pwFindEmail').val("");
					$('#pwFindId').focus();
				}
			},
			error : function(error) {
				console.log(error);
			}
		})
	});
	
</script>