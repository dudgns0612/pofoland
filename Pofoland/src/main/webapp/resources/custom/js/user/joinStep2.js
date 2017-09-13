$(document).ready( function(){
	var idCheck = "";
	var emailCheck = "";
	var isIdCheck = false;
	var isEmailCheck = false;
	
	//부트스트랩을 이용한 회원가입 벨리데이션
	$('#step2Form').bootstrapValidator({ 
		fields : {
			userId : {
				validators : {
					notEmpty : {
						message : 'ID를 입력하세요.'
					},
					stringLength : {
						min : 6,
						max : 15,
						message : '6~15자 ID만 가입 가능합니다.'
					},
					regexp : {
						regexp : /^[a-zA-Z0-9_\.]+$/,
						message : '영문자, 숫자, ., _ 만 사용가능합니다.'
					},
					callback : {
						callback : function(value, validator, $field){
							var reg = /^[a-zA-Z0-9_\.]+$/;
							var check = true;
							
							if (value == '') {
								check = false;
							} else if (!reg.test(value)) {
								check = false;
							} else if (value.length < 6 || value.length > 15) {
								check = false;
							}
							if(check) {
								$('input[name="idcheckbtn"]').removeAttr('disabled');
							} else {
								$('input[name="idcheckbtn"]').attr('disabled','disabled');
							}
							return  true;
						}	
					}
				}
			}, 
			userPw : {
				validators : {
					notEmpty : {
						message : '비밀번호를 입력하세요.'
					},
					different : {
						field : 'user_id',
						message : '비밀번호를 ID와 똑같이 할 수 없습니다!'
					},
					callback : {
						message : '제대로 입력하세요',
						callback : function(value, validator, $field) {
							if (value === '') {
								return true;
							}
							// Check the password strength
							if (value.length < 8) {
								return {
									valid : false,
									message : '8글자 이상 사용가능합니다.'
								};
							}
							// The password doesn't contain any uppercase character
							if (value === value.toUpperCase()) {
								return {
									valid : false,
									message : '소문자를 하나라도 포함하셔야 합니다.'
								}
							}
	
							// The password doesn't contain any digit
							if (value.search(/[0-9]/) < 0) {
								return {
									valid : false,
									message : '특수문자를 하나라도 포함하셔야 합니다.'
								}
							}
							return true;
						}
					}
				}
			},
			remPw : {
				validators : {
					notEmpty : {
						message : '비밀번호 다시 입력하세요.'
					},  
					identical : {
						field : 'userPw',
						message : '비밀번호가 일치하지않습니다.'
					}
				}
			},
			userEmail : {
				validators : {
					notEmpty : {
						message : '이메일을 입력하세요.'
					},
					emailAddress : {
						message : '이메일을 제대로 입력하세요!!'
					},
					callback : {
						callback : function (value, validator){
							var check = true;
							if (value == '') {
								check = false;
							} else if (value.indexOf('@') < 0) {
								check = false;
							} else if (value.indexOf('@') > 0) {
								var strCheck = value.split('@');
								if (strCheck[1] == '') {
									check = false;
								}
							}
							
							if (check) {
								$('input[name="emailcheckbtn"]').removeAttr('disabled');
							} else {
								$('input[name="emailcheckbtn"]').attr('disabled','disabled');
							}
							return true;
						}
					}
				}
			},
			idcheckbtn : {
				validators : {
					notEmpty : {
						message : '아이디 중복확인하세요!'
					}
				}
			},
			emailcheckbtn : {
				validators : {
					notEmpty : {
						message : '이메일 중복확인하세요!'
					}
				}
			}
		}
	});
	
	//아이디 인증 체크
    $('input[name="idcheckbtn"]').click(function() {
	var userId = $('#idInput').val();
	$.ajax({
		type : "GET",
		url : contextPath + "/user/checkid/" + userId,
		dataType : "JSON",
		success : function(response) {
			if (response.code == 1) {
				isIdCheck = false;
				alert("이미 사용중인 아이디입니다.");
				$('#idInput').focus();
				$('#idInput').val("");
			} else {
				idCheck = userId;
				isIdCheck = true;
				alert("사용이 가능한 아이디입니다.");
			}
		},
		error : function(e) {
			console.log("ERROR: ", e);
		},
		done : function(e) {
			console.log("DONE");
		}
	}); // end ajax
}); // end keyup

    //이메일 인증 체크
$('input[name="emailcheckbtn"]').click(function() {
		var userEmail = $('#emailInput').val();
		$.ajax({
			type : "GET",
			url : contextPath + "/user/checkemail?userEmail="+ userEmail,
			dataType : "JSON",
			success : function(response) {
				if (response.code == 1) {
					isEmailCheck = false;
					alert("이미사용중인 이메일입니다! 다른 이메일을 사용하세요");
					$('#emailInput').focus();
					$('#emailInput').val("");
				} else {
					isEmailCheck = true;
					emailCheck = userEmail;
					alert("사용가능한 이메일입니다.");
				}
			},
			error : function(e) {
				console.log("ERROR: ",e);
			},
			done : function(e) {
				console.log("DONE");
			}
		});
	});

					    
    // 가입버튼 클릭
	$('#step2Btn').click(function() {
		var userId = $('#idInput').val();
		var userEmail = $('#emailInput').val();
		
		$.ajax({
			type : "GET",
			url : contextPath + "/user/checkid/" + userId,
			dataType : "JSON",
			async : false,
			success : function(response) {
				if (response.code == 1) {
					alert("죄송합니다.다시 한번 아이디 중복확인 해주세요.")
					isIdCheck = false;
					$('#idInput').focus();
					$('#idInput').val("");
				}
			},
			error : function(e) {
				console.log("ERROR: ",e);
			}
		});

		$.ajax({
			type : "GET",
			url : contextPath + "/user/checkemail?userEmail="+ userEmail,
			dataType : "JSON",
			async : false,
			success : function(response) {
				if (response.code == 1) {
					isEmailCheck = false;
					alert("죄송합니다. 다시 한번 이메일 중복확인을 다시헤주세요.");
					$('#emailInput').focus();
					$('#emailInput').val("");
				}
			},
			error : function(e) {
				console.log("ERROR: ",e);
			}
		}); // end ajax

		if (!isIdCheck || userId != idCheck) {
			alert("아이디 중복확인을 해주세요.");
			return;
		} else if (!isEmailCheck || userEmail != emailCheck) {
			alert("이메일 중복확인을 해주세요.");
			return;
		} else {
			var formData = new FormData(
					$('#step2Form')[0]);
			$.ajax({
				type : 'POST',
				url : contextPath
						+ '/user',
				data : formData,
				dataType : 'JSON',
				contentType : false,
				processData : false,
				success : function(response) {
					if (response.code) {
						location.href = '/user/mailauth/'+ response.data.userSeq;
					}
				},
				error : function(e) {
					console.log(e);
				}
			});
		}
	});
});
