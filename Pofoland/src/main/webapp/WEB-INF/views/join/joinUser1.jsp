<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div style="height: 1000px; padding-top: 120px; overflow: hidden;">
	<div class="row">
		<div class="col-xs-12">
			<div class="col-xs-2"></div>
			<div class="col-xs-8 text-center">
				<img src="${contextPath}/resources/assets/images/join/joinStep1.png" style="text-align: center;"/>
				<form id="createForm" class="form-horizontal" method="POST" action="/user/#">

	<br/><br/>
	<fieldset>
		<!-- user_id input-->
		<div class="form-group has-feedback">
			<label class="col-md-3 control-label" for="idinput">아이디</label>
			<div class="col-md-6 inputGroupContainer">
				<div class="input-group">
					<span class="input-group-addon"> <i
						class="glyphicon glyphicon-info-sign"></i></span> <input id="idinput"
						name="user_id" placeholder="아이디" class="form-control input-md"
						type="text" pattern="^[_A-z0-9]{1,}$" /> <input type="hidden"
						name="user_idcheck" value="">
				</div>    
			</div>
			<div class="col-md-1">
				<input type='button' class="btn btn-default" value="중복확인"
					name="idcheckbtn">
			</div>
		</div>
		<br/>

		<!-- 패스워드 확인폼 -->
		<!-- m_pw input-->

		<div class="form-group has-feedback">
			<label class="col-md-3 control-label" for="inputPw">비밀번호</label>
			<div class="col-md-6 inputGroupContainer">
				<div class="input-group">
					<span class="input-group-addon"><i
						class="glyphicon glyphicon-star-empty"></i></span> <input type="password"
						class="form-control input-md" id="inputPw" placeholder="비밀번호"
						name="m_pw">

				</div>
			</div>
		</div>
		<br/>

		<!-- password check input-->
		<div class="form-group has-feedback">
			<label class="col-md-3 control-label" for="inputPwConfirm">비밀번호확인</label>
			<div class="col-md-6 inputGroupContainer">
				<div class="input-group">
					<span class="input-group-addon"><i
						class="glyphicon glyphicon-star"></i></span> <input type="password"
						class="form-control input-md" id="inputPwConfirm"
						placeholder="비밀번호확인" name="rem_pw">
					<div class="help-block with-errors"></div>
				</div>
			</div>
		</div>
		<br/>

		<!-- m_email input-->
		<div class="form-group">
			<label class="col-md-3 control-label" for="emailinput">이메일</label>
			<div class="col-md-6 inputGroupContainer">
				<div class="input-group">
					<span class="input-group-addon"><i
						class="glyphicon glyphicon-envelope"></i></span> <input type="text"
						class="form-control input-md" id="emailinput"
						placeholder="ex) admin@hst.com" name="m_email"> <input
						type="hidden" name="m_emailcheck" value="">
				</div>
			</div>
			<div class="col-md-1">
				<input type='button' class="btn btn-default" value="중복확인"
					name="emailcheckbtn">
			</div>
		</div>
		<br/>

		<div class="form-group col-md-8">
			<div class="col-sm-offset-7 col-sm-3">
				<button type="submit" class="btn btn-default">Sign in</button>
				<button type="button" class="btn btn-default"
					onclick="location.href='#'">Cancel</button>
			</div>
		</div>
	</fieldset>
</form>
</div>

			<div class="col-xs-2"></div>
		</div>
	</div>
</div>


<script>
	$(document).ready( function() {
						$('#createForm').bootstrapValidator(
										{ 
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
						        	var user_id=$('#idinput').val(); 
						        	 $.ajax({ 	 
									        type: "GET",
									        url: "/user/checkid"+user_id, 
									        data: "user_id="+ user_id ,
									        datatype : "JSON",
									        success: function(response){
									            if(response.count==1){  
									            	$('input[name="user_idcheck"]').attr("value",user_id);
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
					    
						
						  
					}) 
</script>