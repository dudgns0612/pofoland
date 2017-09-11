<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<link rel="stylesheet" href="${contextPath}/resources/custom/css/user/login.css">

<div style="height: 800px; padding-top: 80px; background: url('/resources/custom/images/login_page_background.jpg') fixed; background-size: cover; margin: 0;">
    <div class="row">
        <div class="col-md-12">
            <div class="pr-wrap">
                <div class="pass-reset">
                    <label>
                        Enter the email you signed up with</label>
                    <input type="email" placeholder="Email" />
                    <input type="submit" value="Submit" class="pass-reset-submit btn btn-success btn-sm" />
                </div>
            </div>
            <div class="wrap">
                <p class="form-title">Sign In</p>
                <form class="login" action="/j_spring_security_check" method="post">
                <input type="text" name="userId" placeholder="Username" />
                <input type="password" name="userPw" placeholder="Password" />
                <input type="submit" value="Sign In" class="btn btn-success btn-sm" />
                <input type="button" value="Sign Up" class="btn btn-success btn-sm" onclick="location.href='${contextPath}/join/step1';"/>
                <a href="/google/login"><img class="btn-block" src="${contextPath}/resources/custom/images/layouts/googlelogin.png" style="cursor:pointer; height:40px;"/></a>
                <div class="remember-forgot">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" />
                                    Remember Me
                                </label>
                            </div>
                        </div>
                        <div class="col-md-6 forgot-pass-content">
                            <a href="javascript:void(0)" class="forgot-pass">Sign Up</a>
                        </div>
                    </div>
                </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script>
	
	$(document).ready(function(){
	    $('.forgot-pass').click(function(event) {
	        $(".pr-wrap").toggleClass("show-pass-reset");
	    }); 
	      
        $('.pass-reset-submit').click(function(event) {
       		$(".pr-wrap").removeClass("show-pass-reset");
        }); 

	});

</script>
