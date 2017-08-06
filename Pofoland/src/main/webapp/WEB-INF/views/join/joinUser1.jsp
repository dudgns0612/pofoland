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
		<!-- m_id input-->
		<div class="form-group has-feedback">
			<label class="col-md-3 control-label" for="idinput">아이디</label>
			<div class="col-md-6 inputGroupContainer">
				<div class="input-group">
					<span class="input-group-addon"> <i
						class="glyphicon glyphicon-info-sign"></i></span> <input id="idinput"
						name="m_id" placeholder="아이디" class="form-control input-md"
						type="text" pattern="^[_A-z0-9]{1,}$" /> <input type="hidden"
						name="m_idcheck" value="">
				</div>    
			</div>
			<div class="col-md-1">
				<input type='button' class="btn btn-default" value="중복확인"
					name="idcheckbtn">
			</div>
		</div>
		<br/>

		<!-- m_nick input-->
		<!-- <div class="form-group">
			<label class="col-md-3 control-label" for="nickinput">닉네임</label>
			<div class="col-md-6 inputGroupContainer">
				<div class="input-group">
					<span class="input-group-addon"><i
						class="glyphicon glyphicon-tag"></i></span> <input type="text"
						class="form-control input-md" id="nickinput" placeholder="닉네임"
						name="m_nick"> <input type="hidden" name="m_nickcheck"
						value="">
				</div>
			</div>
			<div class="col-md-1">
				<input type='button' class="btn btn-default" value="중복확인"
					name="nickcheckbtn">
			</div>
		</div>
		<br/> -->

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

		<!-- m_nameinput-->
		<!-- <div class="form-group has-feedback">
			<label class="col-md-3 control-label" for="textinput">이름</label>
			<div class="col-md-6 inputGroupContainer">
				<div class="input-group">
					<span class="input-group-addon"><i
						class="glyphicon glyphicon-user"></i></span> <input id="textinput"
						name="m_name" placeholder="이름" class="form-control input-md"
						type="text">
				</div>
			</div>
		</div>
		<br/> -->
		
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

		<!-- m_phone input-->
		<!-- <div class="form-group">
			<label class="col-md-3 control-label" for="phoneinput">전화번호</label>
			<div class="col-md-6 inputGroupContainer">
				<div class="input-group">
					<span class="input-group-addon"><i
						class="glyphicon glyphicon-earphone"></i></span> <input type="text"
						class="form-control input-md" id="phoneinput"
						placeholder="ex) 010-1234-5678" name="m_phone"> <input
						type="hidden" name="m_phonecheck" value="">
				</div>
			</div>
			<div class="col-md-1">
				<input type='button' class="btn btn-default" value="중복확인"
					name="phonecheckbtn">
			</div>
		</div>
		<br/> -->

		<div class="form-group col-md-8">
			<div class="col-sm-offset-7 col-sm-3">
				<button type="submit" class="btn btn-default">Sign in</button>
				<button type="button" class="btn btn-default"
					onclick="location.href='../home.do'">Cancel</button>
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
												m_id : {
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
															field : 'm_idcheck', 
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
															field : 'm_id',
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
																		.toLowerCase()) {
																	return {
																		valid : false,
																		message : '대문자를 하나라도 포함하셔야 합니다.'
																	}
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
												m_name : {
													validators : {
														notEmpty : {
															message : '이름을 입력하세요.'
														},
														stringLength : {
															min : 2,
															max : 10,
															message : '이름을 제대로입력하세요.'
														}
													}
												},
												m_nick : {
													validators : {
														notEmpty : {
															message : '닉네임을 입력하세요.'
														},
														stringLength : {
															min : 2,
															max : 6,
															message : '닉네임은 2~6 글자입니다.'
														},
														 different : {
																field : 'm_nickcheck', 
																message : '이미 사용중인 닉네임입니다.'
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
												m_phone : {
													validators : {
														notEmpty : {
															message : '전화번호를 입력하세요'
														},
														 different : {
																field : 'm_phonecheck', 
																message : '이미 사용중인 전화번호입니다.'
															}  
													}
												},
												m_level : {
													validators : {
														notEmpty : {
															message : '가입유형을 선택하세요'
														}
													}
												},
												m_job : {
													validators : {
														notEmpty : {
															message : '직업을 선택하세요'
														}
													}
												},
												m_lang : {
													validators : {
														notEmpty : {
															message : '관심 분야를 선택하세요'
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
												nickcheckbtn : {
													validators : {
														notEmpty : {
															message : '닉네임 중복확인하세요!'
														}
													}
												},
												emailcheckbtn : {
													validators : {
														notEmpty : {
															message : '이메일 중복확인하세요!'
														}
													}
												},
												phonecheckbtn : {
													validators : {
														notEmpty : {
															message : '전화번호 중복확인하세요!'
														}
													}
												}
												

											}
										});
						
						  
						    $('input[name="idcheckbtn"]').click(function(){  
						        	var m_id=$('#idinput').val(); 
						            // ajax 실행
						        	 $.ajax({ 	 
									        type: "POST",
									        url: "./idCheck.do", //이페이지에서 중복체크를 한다
									        data: "m_id="+ m_id ,//idCheck.do에 id 값을 보낸다`
									        datatype : "JSON",
									        success: function(checkInfo){
									            if(checkInfo.count==1){  
									            	$('input[name="m_idcheck"]').attr("value",m_id);
									            	alert("이미 사용중인 아이디입니다! 다른 아이디를 사용하세요");
									            	$('#idinput').focus();
									            	$('#idinput').val("");
									           	}//중복된 아이디 결과넣고 validator 수행 
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
						    
						    
						    $('input[name="nickcheckbtn"]').click(function(){
					        	var m_nick=$('#nickinput').val(); 
					            // ajax 실행   
					        	 $.ajax({ 	 
								        type: "POST",
								        url: "./nickCheck.do", //이페이지에서 중복체크를 한다
								        data: "m_nick="+ m_nick ,//nickCheck.do에 m_nick 값을 보낸다`
								        datatype : "JSON",
								        success: function(checkInfo){
								        	  if(checkInfo.count==1){  
									            	$('input[name="m_nickcheck"]').attr("value",m_nick);
									            	alert("이미 사용중인 닉네임 입니다! 다른 닉네임을 사용하세요");
									            	$('#nickinput').focus();
									            	$('#nickinput').val("");
									           	}//중복된 아이디 결과넣고 validator 수행 
									           	else{
									           		alert("사용가능한 닉네임입니다.")
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
					            // ajax 실행
					        	 $.ajax({ 	 
								        type: "POST",
								        url: "./emailCheck.do", //이페이지에서 중복체크를 한다
								        data: "m_email="+ m_email ,//emailCheck.do에 m_email 값을 보낸다`
								        datatype : "JSON",
								        success: function(checkInfo){
								        	  if(checkInfo.count==1){  
									            	$('input[name="m_emailcheck"]').attr("value",m_email);
									            	alert("이미사용중인 이메일입니다! 다른 이메일을 사용하세요");
									            	$('#emailinput').focus();
									            	$('#emailinput').val("");
									           	}//중복된 아이디 결과넣고 validator 수행 
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
					    
					    $('input[name="phonecheckbtn"]').click(function(){
				        	var m_phone=$('#phoneinput').val(); 
				            // ajax 실행
				        	 $.ajax({ 	 
							        type: "POST",
							        url: "./phoneCheck.do", //이페이지에서 중복체크를 한다
							        data: "m_phone="+ m_phone ,//phoneCheck.do에 m_phone 값을 보낸다`
							        datatype : "JSON",
							        success: function(checkInfo){
							        	  if(checkInfo.count==1){  
								            	$('input[name="m_phonecheck"]').attr("value",m_phone);
								            	alert("이미 등록된 전화번호 입니다. 다른 전화번호를 사용하세요");
								            	$('#phoneinput').focus();
								            	$('#phoneinput').val("");
								           	}//중복된 아이디 결과넣고 validator 수행 
								           	else{
								           		alert("사용가능한 전화번호입니다.")
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