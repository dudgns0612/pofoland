$(document).ready(function(){
    // Form객체를 Object로 Serialize
    // input, textarea 태그를 기준으로 수행
    $.fn.serializeObject = function() {
        var obj = {};
        
        $.each(this.find("input,textarea"), function(){
            if(obj[this.name]) {
                if(!(obj[this.name] instanceof Array)) {
                    var tmp = obj[this.name];
                    obj[this.name] = [tmp];
                }
                obj[this.name].push(this.value);
            } else {
                obj[this.name] = this.value;
            }
        });
        
        return obj;
    }
    
    //회원가입 버튼 클릭
	$('#signUpBtn').click(function(){
		location.href="/join/step1";
	});
});

$(document).on('click','#naverLoginBtn',function(){
	
	var element = '';
	
	element += '<div id="sub_way_area">';
	element += '	네이버 로그인 설명';
    element += '</div>';
	
	$('#popupDialog > .popup_contents').html(element);
	$('#popupDialog').dialog({
		modal : true,
		open: function(){
		    $('.ui-dialog-titlebar-close').addClass('ui-button ui-widget ui-state-default ui-corner-all ui-button-icon-only');
		    $('.ui-dialog-titlebar-close').append('<span class="ui-button-icon-primary ui-icon ui-icon-closethick"></span><span class="ui-button-text">close</span>');
		    $('.ui-dialog-titlebar').css('background','#f0ad4e');
		    $('.ui-dialog-titlebar').css('color','#FFFFFF');
		    $('.ui-dialog-buttonset > button').addClass('btn btn-block btn-warning');
		}, 
		buttons : {
			확인 : function () {
				naverLoginBtn_callback();
			}
		},
		width: 530,
		height: 230
	});
});

function naverLoginBtn_callback() {
	location.href = contextPath + "/naver/login";
}

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
