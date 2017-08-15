$(document).ready( function(){
	$('#createForm').bootstrapValidator({ 
		fields : {
			user_id : {
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
					 different : {
						field : 'user_idcheck', 
						message : '이미 사용중인 아이디입니다.'
					}  
				}
			}, 
			m_pw : {
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
						callback : function(
								value,
								validator,
								$field) {
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
							if (value === value
									.toUpperCase()) {
								return {
									valid : false,
									message : '소문자를 하나라도 포함하셔야 합니다.'
								}
							}
	
							// The password doesn't contain any digit
							if (value
									.search(/[0-9]/) < 0) {
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
			rem_pw : {
				validators : {
					notEmpty : {
						message : '비밀번호 다시 입력하세요.'
					},  
					identical : {
						field : 'm_pw',
						message : '비밀번호가 일치하지않습니다.'
					}
				}
			},
			m_email : {
				validators : {
					notEmpty : {
						message : '이메일을 입력하세요.'
					},
					emailAddress : {
						message : '이메일을 제대로 입력하세요!!'
					},
					 different : {
							field : 'm_emailcheck', 
							message : '이미 사용중인 이메일입니다.'
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
						
						  
    $('input[name="idcheckbtn"]').click(function(){  
        	var userId=$('#idInput').val(); 
        	 $.ajax({ 	 
			        type: "GET",
			        url: "/user/checkid"+userId, 
			        datatype : "JSON",
			        success: function(response){
			        	console.log(response)
			            if(response.count==1){  
			            	$('input[name="user_idcheck"]').attr("value",userId);
			            	alert("이미 사용중인 아이디입니다! 다른 아이디를 사용하세요");
			            	$('#idinput').focus();
			            	$('#idinput').val("");
			           	}
			           	else{
			           		alert("사용가능한 아이디입니다.")
			           	}   
			           	  
			        }, 
			        error : function(e) {
			 			console.log("ERROR: ", e);
						display(e);
					},
					done : function(e) {
						console.log("DONE");
					} 
        	 }); //end ajax
    }); // end keyup

    $('input[name="emailcheckbtn"]').click(function(){
    	var m_email=$('#emailinput').val(); 
    	 $.ajax({ 	 
		        type: "POST",
		        url: "./emailCheck.do", 
		        data: "m_email="+ m_email ,
		        datatype : "JSON",
		        success: function(response){
		        	  if(response.count==1){  
			            	$('input[name="m_emailcheck"]').attr("value",m_email);
			            	alert("이미사용중인 이메일입니다! 다른 이메일을 사용하세요");
			            	$('#emailinput').focus();
			            	$('#emailinput').val("");
			           	}
			           	else{
			           		alert("사용가능한 이메일입니다.")
			           	}   
		        }, 
		        error : function(e) {
		 			console.log("ERROR: ", e);
					display(e);
				},
				done : function(e) {
					console.log("DONE");
				} 
    	 }); //end ajax
}); // end keyup
});