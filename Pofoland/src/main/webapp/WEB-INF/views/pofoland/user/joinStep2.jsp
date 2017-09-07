<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div style="height: 900px; padding-top: 120px; overflow: hidden;">
	<div class="row">
		<div class="col-xs-12">
			<div class="col-xs-2"></div>
			<div class="col-xs-8 text-center">
				<img src="${contextPath}/resources/assets/images/join/joinStep1.png" style="text-align: center;"/>
				<form id="step2Form" class="form-horizontal" method="POST" action="${contextPath}/user">
					<hr/>
					<br/>
					<br/>
					<fieldset>
						<!-- user_id input-->
						<div class="form-group has-feedback">
							<label class="col-md-3 control-label" for="idinput">
								<img src="${contextPath}/resources/common/images/user/id.png" />
							</label>
							<div class="col-md-6 inputGroupContainer">
								<div class="input-group">
									<input id="idInput" name="userId" placeholder="아이디를 입력하여 주세요." class="form-control input-md" type="text" pattern="^[_A-z0-9]{1,}$" style="width:550px;" /> 
								</div>    
							</div>
							<div class="col-md-1">
								<input type='button' class="btn btn-default" value="중복확인" name="idcheckbtn" style="margin-left:-12px;" disabled="disabled">
							</div>
						</div>
						<br/>
				
						<!-- 패스워드 확인폼 -->
						<!-- m_pw input-->
				
						<div class="form-group has-feedback">
							<label class="col-md-3 control-label" for="inputPw">
								<img src="${contextPath}/resources/common/images/user/password.png" />
							</label>
							<div class="col-md-6 inputGroupContainer">
								<div class="input-group">
									<input type="password" class="form-control input-md" id="inputPw" placeholder="비밀번호를 입력하여 주세요." name="userPw" style="width:550px;">
								</div>
							</div>
						</div>
						<br/>
				
						<!-- password check input-->
						<div class="form-group has-feedback">
							<label class="col-md-3 control-label" for="inputPwConfirm">
								<img src="${contextPath}/resources/common/images/user/password-confirm.png" />
							</label>
							<div class="col-md-6 inputGroupContainer">
								<div class="input-group">
										<input type="password" class="form-control input-md" id="inputPwConfirm" placeholder="비밀번호 한번 더 입력하여 주세요." name="remPw" style="width:550px;" />
									<div class="help-block with-errors"></div>
								</div>
							</div>
						</div>
						<br/>
				
						<!-- userEmail input-->
						<div class="form-group">
							<label class="col-md-3 control-label" for="emailinput">
								<img src="${contextPath}/resources/common/images/user/email.png" />
							</label>
							<div class="col-md-6 inputGroupContainer">
								<div class="input-group">
									<input type="text" class="form-control input-md" id="emailInput" placeholder="ex) admin@hst.com" name="userEmail" style="width:550px;" />
								</div>
							</div>
							<div class="col-md-1">
								<input type='button' class="btn btn-default" value="중복확인" name="emailcheckbtn" style="margin-left:-12px;" disabled="disabled">
							</div>
						</div>
						<br/>
				
						<div class="form-group col-md-9">
							<div class="col-sm-offset-7 col-sm-3">
								<button type="button" id="step2Btn" class="btn btn-default">Sign in</button>
								<button type="button" class="btn btn-default" onclick="location.href='#'">Cancel</button>
							</div>
						</div>
					</fieldset>
				</form>
				</div>
			<div class="col-xs-2"></div>
		</div>
	</div>
</div>
