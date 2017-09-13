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
});

$('#defaultImageBtn').click(function(){
	$('#userInfoImage').attr('src',contextPath+'/resources/custom/images/user/default_profile.jpg')
});

