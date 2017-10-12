var nickCheck = false;
var fileCheck = false;

$(document).ready(function(){
   $('#step3Form').bootstrapValidator({
		fields : {
			userNick : {
				validators : {
					notEmpty : {
						message : '닉네임을 입력하세요.'
					},
					stringLength : {
						min : 2,
						max : 10,
						message : '2~10자 닉네임만 가입 가능합니다.'
					},
					callback : {
						callback : function(value,validator,$field) {
							var check = true;
							
							if (value == '') {
								check = false;
							} else if (value.length < 2 || value.length > 15) {
								check = false;
							}
							if (check) {
								$('input[name="nickCheckBtn"]').removeAttr('disabled');
							} else {
								$('input[name="nickCheckBtn"]').attr('disabled','disabled');
							}
							return true;
						}
					}
				}
			}
		}
	});

	$('input[name="nickCheckBtn"]').click(function() {
		var userNick = $('#nickInput').val();
		$.ajax({
			type : 'GET',
			url : contextPath + '/user/checknick/' + userNick,
			dataType : 'JSON',
			success : function(response) {
				if (response.code == 1) {
					alert("이미 존재하는 닉네임 아이디입니다.");
					$('#idInput').focus();
					$('#idInput').val("");
				} else {
					alert("사용이 닉네임 아이디입니다.");
				}
			},
			error : function(e) {
				console.log("ERROR: ", e);
			},
			done : function(e) {
				console.log("DONE");
			}
		});
	});

	$('#step3Btn').click(function() {
		var formData = new FormData();
		var attentionArray = new Array();
		var userNick = $('#nickInput').val();
		var userJoinType = $('#userJoinType').val();
	    var userSeq = $('#userSeq').val();
	    var userId = $('#userId').val();
	    var userProfile = $('#inputImage')[0].files[0];
	    var url = '';
	    
	    //유저 프로필 추가
	    if (fileCheck) {
	    	formData.append('userProfile',userProfile);
	    } 
	    
	    if (userSeq != '' && userSeq != null && userSeq != undefined && userSeq != 'undefined') {
	    	url = contextPath+'/user/addinfo';
	    	formData.append('userSeq',userSeq);
	    } else {
	    	url = contextPath+'/user/oAuth';
	    	formData.append('userId',userId);
	    }
		
		$('input[type="checkbox"]:checked').each(function(){
			attentionArray.push($(this).val());
		});
		
		formData.append('userJoinType',userJoinType);
		formData.append('userNick',userNick);
		formData.append('interestCode',attentionArray);
		$.ajax({
			beforeSend : function (){
				var check = false;
				$.ajax({
					type : 'GET',
					url : contextPath + '/user/checknick/' + userNick,
					dataType : 'JSON',
					async : false,
					success : function(response) {
						if (response.code == 1) {
							alert("죄송합니다. 다시 한번 닉네임 체크해주세요.");
							$('#idInput').focus();
							$('#idInput').val("");
						} else {
							check = true;
						}
					},
					error : function(e) {
						console.log("ERROR: ",e);
						
					}
				});
				return check;
			},
			url : url,
			type : 'POST',
			data :  formData,
			dataType : 'JSON',
			processData: false,
			contentType: false,
			success : function(response){
				if(response.code) {
					alert("회원가입이 완료되었습니다.");
					location.href = contextPath+'/home';
				} else {
					alert("회원가입이 정상처리되지않았습니다.");
					return;
				}
			},
			error : function(error) {
				console.log(error)
			}
		});
	});
	
	$('#inputImage').change(function(e){
		var reader = new FileReader;
		var file = $('#inputImage')[0].files[0]; 
		
		reader.onload = function(event){
			$('#userProfile').attr('src', event.target.result);
		}
		if(file){
			reader.readAsDataURL(file);	
			fileCheck = true;
		}else {
			$('#userProfile').attr('src', contextPath+"/resources/custom/images/user/default_profile.jpg");
			fileCheck = false;
		}
	});
});

function fileProfile() {
	var fileBtn = $('#inputImage');
	
	fileBtn.click();
}
