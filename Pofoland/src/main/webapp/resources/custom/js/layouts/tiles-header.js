//회원가입 버튼 클릭
$(document).ready(function(){
	$('#signUpBtn').click(function(){
		location.href="/join/step1";
	});
});

function loginValidate() {
	var userId = $('#inputId').val();
	var userPw = $('#inputPassword').val();
	
	if (userId == null || userId == '' || userId == undefined || userId == 'undefined') {
		alert("아이디를 입력하여 주세요.");
		return false;
	} else if (userPw == null || userPw == '' || userPw == undefined || userPw == 'undefined') {
		alert("비밀번호를 입력하여 주세요.");
		return false;
	}
	
	return true;
}