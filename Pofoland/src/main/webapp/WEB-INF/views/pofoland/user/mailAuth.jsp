<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div style="height: 750px; padding-top: 120px; overflow: hidden;">
	<input type="hidden" id="seqHidden" user-seq="${userSeq}" />
	<div class="row">
		<div class="col-xs-12 text-center">
			<br/>
			<br/>
			<br/>
			<h3>해당 E-mail 로 인증메일이 전송되었습니다.</h3>
			<h5>인증을 하지 않은 아이디는 자동으로 한달 후 삭제됩니다.</h5>
			<button id="authCheckBtn" class="btn btn-primary btn-lg" type=button style="width:300px; margin-top:200px;">
				<span style="font-weight: bold;">인증 확인</span>
			</button>
		</div>
	</div>
</div>
<script>
	
	$(document).ready(function(){
		
		$('#authCheckBtn').click(function(){
			var userSeq = $('#seqHidden').attr('user-seq');
			
			//이메일 인증 확인
			$.ajax({
				type : 'GET',
				url : '/user/checkauth/'+userSeq,
				success : function(response){
					if(response.code){
						alert("인증이 완료되었습니다.");
					} else {
						alert("인증이 완료되지 않았습니다. 다시 확인하여 주세요.");
					}
				},
				error : function(e){
					console.log(e);
				}
				
			});
		});
	});

</script>


