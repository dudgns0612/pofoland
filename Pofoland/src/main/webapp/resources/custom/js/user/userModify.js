// modifyFileCheck
// 0 : 프로필변경x
// 1 : 프로필변경
// 2 : 기본이미지프로필변경

var modifyFileCheck = 0;
var modifyNickCheck = false;
var userOriNick = '';
var tempNick = '';

$(document).ready(function(){
	//공개여부에 따른 SELECTED설정
	var element = '';
	if (userPublicYn == 'Y') {
		element += '<option value="Y" selected="selected">공개</option>';
		element += '<option value="N">비공개</option>';
	} else {
		element += '<option value="Y">공개</option>';
		element += '<option value="N"  selected="selected">비공개</option>';
	}
	$('#publicYnSelect').html(element);
	
	userOriNick = $('#modifyNickname').val();
});

//닉네임 중복체크
$(document).on('click','#modifyNickCheckBtn',function() {
	var userNick = $('#modifyNickname').val();
	
	console.log(userNick+"/"+userOriNick);
	
	if (userNick == userOriNick) {
		alert("닉네임이 변경되지 않았습니다.");
		return;
	} else if(userNick.length < 2 || userNick.length > 10) {
		alert("닉네임은 2~10자사이여야 합니다.")
		return;
	}
	
	$.ajax({
		type : 'GET',
		url : contextPath + '/user/checknick/' + userNick,
		dataType : 'JSON',
		success : function(response) {
			if (response.code) {
				alert("이미 사용중인 닉네임입니다.");
				modifyNickCheck = false;
			} else {
				alert("사용이 가능한 닉네임입니다.");
				tempNick = userNick;
				modifyNickCheck = true;
			}
		},
		error : function(error) {
			console.log(error);
		}
		
	});
	
});

//회원정보 수정
$(document).on('click','#modifySuccessBtn',function() {
	var userNick = $('#modifyNickname').val();
	
	if (userNick != userOriNick) {
		if (modifyNickCheck) {
			if (userNick != tempNick) {
				alert("중복확인을 해주세요.");
				modifyNickCheck = false;
				return;
			} else {
				$.ajax({
					type : 'GET',
					url : contextPath + '/user/checknick/' + userNick,
					dataType : 'JSON',
					success : function(response) {
						if (response.code) {
							alert("죄송합니다. 한번 더 닉네임 중복확인을 해주세요.");
							modifyNickCheck = false;
							return;
						}
					},
					error : function(error) {
						console.log(error);
					}
				});
			}
		} else {
			alert("중복확인을 해주세요.");
			return;
		}
	} else {
		modifyNickCheck = true;
	}
	
	if (modifyNickCheck) {
		var formData = new FormData();
		var userProfile = $('#modifyImage')[0].files[0];
		var userPublicYn = $('#publicYnSelect option:selected').val();
		formData.append('userSeq',userSeq);
		formData.append('modifyFileCheck',modifyFileCheck);
		formData.append('userNick',userNick);
		formData.append('userPublicYn',userPublicYn);

		if (modifyFileCheck == 1) {
			formData.append('userProfile',userProfile);
		}
		
		$.ajax({
			type : 'POST',
			url : contextPath+'/user/modify',
			data : formData,
			dataType : 'JSON',
			contentType : false,
			processData : false,
			success : function(response) {
				if (response.code) {
					alert("정보가 수정되었습니다.");
					location.href='/user/modify';
				} else {
					alert("죄송합니다 수정에 실패하였습니다. 잠시후 다시 시도하여 주세요.");
					location.href='/user/modify';
				}
			},
			error : function(error) {
				console.log(error)
			}
		});
	}
});


//기본이미지 버튼 클릭
$(document).on('click','#defaultImageBtn',function() {
	$('#userInfoImage').attr('src',contextPath+'/resources/custom/images/user/default_profile.jpg');
	modifyFileCheck = 2;
});

//이미지 변경
$(document).on('change','#modifyImage',function(){
	var reader = new FileReader;
	var file = $('#modifyImage')[0].files[0]; 
	
	reader.onload = function(event){
		$('#userInfoImage').attr('src', event.target.result);
	}
	if (file){
		reader.readAsDataURL(file);	
		modifyFileCheck = 1;
	} else {
		modifyFileCheck = 0;
	}
})

$(document).on('click','#modifyCencleBtn',function(){
	var isCancel = confirm("수정을 취소하시겠습니까?");
	
	if (isCancel) {
		location.href = contextPath + "/home";
	}
});

$(document).on('click','#userDropBtn',function(){
	var isDrop = confirm("포폴랜드를 정말 탈퇴하시겠습니까?");
	
	if (isDrop) {
		$.ajax({
			type : 'GET',
			url : contextPath + '/user/drop',
			data : {'userSeq' : userSeq},
			dataType : 'JSON',
			success : function(response){
				if (response.code) {
					alert("회원탈퇴 처리가 정상적으로 수행되었습니다. 감사합니다.");
					location.href = contextPath+'/home';
				}
			},
			error : function(error) {
				console.log(error);
			}
		});
	}
	
});

//$(document).on('click',"#pwModifyBtn",function(){
//	
//});

function modifyProfile() {
	var fileBtn = $('#modifyImage');
	
	fileBtn.click();
}

