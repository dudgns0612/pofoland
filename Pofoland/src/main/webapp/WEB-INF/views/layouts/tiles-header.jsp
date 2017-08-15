<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="logo" href="index.html"><img src="${contextPath}/resources/assets/images/logo.png" alt=""></a>
            </div>

            <div class="collapse navbar-collapse" id="st-navbar-collapse">
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="#header">Home</a></li>
                    <li><a href="#services">Services</a></li>
                    <li><a href="#our-works">Works</a></li>
                    <li><a href="#pricing">Pricing</a></li>
                    <li><a href="#our-team">Team</a></li>
                    <li><a href="#contact">Contact</a></li>
                    <li><a href="blog.html">Blog</a></li>
                    <li class="dropdown">
                    	<a href="#" class="dropdown-toggle" data-toggle="dropdown">LOGIN</a>
                    	<ul id="dropdown-dp" class="dropdown-menu">
							<div class="row" style="width: 300px;">
									<li>
										<div class="col-xs-1"></div>
											<div class="col-xs-10">
												LOGIN
												<form class="form" role="form" method="post"
													action="/user/login" accept-charset="UTF-8" id="login-nav">
													<div class="form-group">
														<label class="sr-only" for="InputId">ID</label> <input
															type="text" class="form-control" id="InputId"
															placeholder="ID" name="m_id">
													</div>
													<div class="form-group">
														<label class="sr-only" for="InputPassword">Password</label>
														<input type="password" class="form-control"
															id="InputPassword" placeholder="Password" name="m_pw">
														<div class="help-block text-center">
															<a href="#" style="font-size:12px;">Forget the password?</a>
														</div>
													</div>
													<div class="form-group">
														<button type="submit" class="btn btn-block btn-default">Sign in</button>
													</div>
													<div class="form-group" style="margin-top : -10px;">
														<button type="button" id="signUpBtn" class="btn btn-block btn-default">Sign up</button>
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
                    <li><a href="${contextPath}/board/main">커뮤니티</a></li>
                </ul>
                 
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container -->
    </nav>
</header>
<!-- /HEADER -->