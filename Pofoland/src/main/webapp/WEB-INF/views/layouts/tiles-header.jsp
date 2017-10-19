<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<!-- PRELOADER -->
<div id="st-preloader">
    <div id="pre-status">
        <div class="preload-placeholder"></div>
    </div>
</div>
<!-- /PRELOADER -->

<!-- HEADER -->
<header id="header">
	<nav class="navbar st-navbar navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#st-navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
					<span class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
				<a class="logo" href="${contextPath}/home">
					<img src="${contextPath}/resources/assets/images/logo.png" alt="">
				</a>
			</div>

			<div class="collapse navbar-collapse" id="st-navbar-collapse">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="/home">홈</a></li>
					<li><a href="${contextPath}/pofoland/info">포폴랜드</a></li>
					<li><a href="${contextPath}/user/info">회원정보</a></li>
					<li><a href="blog.html">포트폴리오</a></li>
					<li><a href="${contextPath}/board/main">커뮤니티</a></li>
					<li><a href="${contextPath}/extJobs/main">구인정보</a></li>
					<li><a href="">QnA</a></li>
					<c:choose>
						<c:when test="${user.userSeq != null}">
							<li class="dropdown">
								<a href="#" class="dropdown-toggle" data-toggle="dropdown">내정보</a>
								<ul id="userInfo" class="dropdown-menu" style="min-height:260px; height:263px;">
									<div class="row" style="width: 470px;">
										<li>
											<div class="col-xs-1"></div>
											<div class="col-xs-10">
												<h3>Pofoland</h3>
												<c:choose>
													<c:when test="${user.userProfileUrl != null && user.userProfileUrl != ''}">
														<img class="image-circle-header" src="${contextPath}/user/${user.userSeq}/image" />
													</c:when>
													<c:otherwise>
														<img class="image-circle-header" src="${contextPath}/resources/custom/images/user/default_profile.jpg" />
													</c:otherwise>
												</c:choose>
												<div style="float:right; max-width: 200px;">
													<h5>${user.userNick} 환영합니다.</h5>
													<h5>활동점수 : ${user.userScore}</h5>
													<h6>email </h6>
													<h6>${user.userEmail}</h6>
													<br />
												</div>
											</div>
											<div class="col-xs-1"></div>
										</li>
									</div>
									<div class="bottom">
										<div style="margin-top:17px;">
											<input id="userPofoBtn" class="btn btn-warning" type="button" value="내 포트폴리오" style="width: 32.90%; height: 40px;" />
											<input id="userModifyBtn" class="btn btn-warning" type="button" onclick="location.href='${contextPath}/user/modify'" value="정보수정" style="width: 32.45%; height: 40px;" />
											<input id="userLogoutBtn" class="btn btn-warning" type="button" value="로그아웃" onclick="location.href='${contextPath}/user/logout'" style="width: 32.45%; height: 40px;" />
										</div>
									</div>
								</ul>
							</li>
						</c:when>
						<c:otherwise>
							<li class="dropdown">
								<a href="#" class="dropdown-toggle" data-toggle="dropdown">로그인</a>
								<ul id="login-dp" class="dropdown-menu">
									<div class="row" style="width: 350px;">
										<li>
											<div class="col-xs-1"></div>
											<div class="col-xs-10">
												<img src="${contextPath}/resources/custom/images/layouts/login.png" style="width: 70px; height: 30px; margin-top: 10px;" />
												<br />
												<h6>포폴랜드에 오신것을 환영합니다.</h6>
												<form class="form" role="form" method="post" action="/j_spring_security_check" accept-charset="UTF-8" 
														id="login-nav" onsubmit="return loginValidate();">
													<div class="form-group">
														<label class="sr-only" for="InputId">ID</label> 
														<input type="text" class="form-control" id="inputId" name="userId" placeholder="ID" name="m_id" />
													</div>
													<div class="form-group">
														<label class="sr-only" for="InputPassword">Password</label>
														<input type="password" class="form-control" id="inputPassword" name="userPw" placeholder="Password" name="m_pw" />
														<a href="${contextPath}/user/find/info" style="font-size: 12px;">아이디/비밀번호 찾기</a>
													</div>
													<div class="form-group">
														<button type="submit" class="btn btn-block btn-warning">로그인</button>
													</div>
													<div class="form-group" style="margin-top: -10px;">
														<button type="button" id="signUpBtn" class="btn btn-block btn-warning">회원가입</button>
													</div>
													<div class="form-group">
														<a href="/google/login">
															<img class="btn-block" src="${contextPath}/resources/custom/images/layouts/googleLogin1.png" style="cursor: pointer; height: 37px; border-radius: 5px; margin-top: -10px;" />
														</a>
													</div>
													<div class="form-group">
														<img id="naverLoginBtn" class="btn-block" src="${contextPath}/resources/custom/images/layouts/naverLogin.png" style="cursor: pointer; height: 37px; border-radius: 5px; margin-top: -10px;" />
													</div>
													<div class="checkbox">
														<label> 
															<input type="checkbox">keep me logged-in
														</label>
													</div>
												</form>
											</div>
											<div class="col-xs-1"></div>
										</li>
									</div>
								</ul>
							</li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container -->
	</nav>
</header>

<!-- default popup -->
<div id="popupDialog" title="네이버 가입 시 중요사항">
	<div class="popup_contents"></div>	
</div>
<!-- /default popup -->
<!-- /HEADER -->