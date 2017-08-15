<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div style="height: 1000px; padding-top: 80px;">
	<form action="/j_spring_security_check" method="post">
		<table border="0">
			<tr>
				<td>ID:</td>
				<td><input type="text" name="userId"></td>
			</tr>
			<tr>
				<td>패스워드:</td>
				<td><input type="password" name="userPw"></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="Login">
				</td>
			</tr>
		</table> 
	</form>
	<button type="button" id="googleBtn">구글로 로그인하기</button>
</div>

<script>
	
	$(document).ready(function(){
		
		$('#googleBtn').click(function(){
			location.href="/google/login";
			
		});
		
		
	});

</script>
