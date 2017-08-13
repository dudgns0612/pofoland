function agreeValid() {
		if(!$("input:checkbox[name='agree']").is(":checked")) {
			alert('약관에 동의해주세요');
			return ;
		}
		return true;
} // agreeValid() end