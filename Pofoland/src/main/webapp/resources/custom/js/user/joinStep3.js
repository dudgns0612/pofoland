var nickCheck = false;

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
						max : 15,
						message : '2~15자 닉네임만 가입 가능합니다.'
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
			type : "GET",
			url : contextPath + "/user/checknick/" + userNick,
			dataType : "JSON",
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
	    var userSeq = $('#userSeq').val();
		
		$('input[type="checkbox"]:checked').each(function(){
			attentionArray.push($(this).val());
		});
		
		formData.append('userNick',userNick);
		formData.append('jobCate',attentionArray);
		formData.append('userSeq',userSeq);
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
			url : contextPath+'/user/addinfo',
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
});
