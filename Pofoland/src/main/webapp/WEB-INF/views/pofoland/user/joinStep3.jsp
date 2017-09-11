<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<input type="hidden" id="userSeq" value=<c:if test="${userSeq != '' && userSeq != null}">"${userSeq}"</c:if> ></input>
<input type="hidden" id="userId" value=<c:if test="${userId != '' && userId != null}">"${userId}"</c:if> ></input>
<div style="height: 850px; padding-top: 120px; overflow: hidden;">
   <div class="row">
      <div class="col-xs-12">
         <div class="col-xs-2"></div>
         <div class="col-xs-8 text-center">
            <img src="${contextPath}/resources/custom/images/user/joinStep3.png" style="text-align: center;"/>
            <form id="step3Form" class="form-horizontal" method="POST">
               <hr/>
               <br/>
               <br/>
               <fieldset>
                  <!-- 닉네임 필드 -->
                  <div class="form-group has-feedback">
                     <label class="col-md-3 control-label" for="idinput">
                        <img src="${contextPath}/resources/custom/images/user/nickname.png" />
                     </label>
                     <div class="col-md-6 inputGroupContainer">
                        <div class="input-group">
                           <input id="nickInput" name="userNick" placeholder="닉네임을 입력하여 주세요." class="form-control input-md" type="text" style="width:550px;" /> 
                        </div>    
                     </div>
                     <div class="col-md-1">
                        <input type='button' class="btn btn-default" value="중복확인" name="nickCheckBtn" style="margin-left:-12px;" disabled="disabled">
                     </div>
                  </div>
                  <br/>
            
                  <!-- 패스워드 확인폼 -->
                  <!-- m_pw input-->
            
                  <div class="form-group has-feedback">
                     <label class="col-md-3 control-label" for="inputPw">
                        <img src="${contextPath}/resources/custom/images/user/checking.png" />
                     </label>
                     <div class="col-md-6 inputGroupContainer">
                        <div class="input-group" style="margin-top:-10px;">
                           <h3 style="margin-bottom:30px;">관심 분야를 선택하여 주세요.</h3>
                           <div id="attentionBox" style="margin-left: 35px;">
                              <c:forEach items="${jobList}" var="job">
                                 <div class="checks" style="float:left; margin-top:5px; margin-right:20px;">
                                   <input type="checkbox" name="jabCate" id="${job.cateSeq}" value="${job.cateSeq}"> 
                                   <label class="cfont-weight" for="${job.cateSeq}">${job.cateName}</label> 
                                 </div>
                              </c:forEach>
                           </div>
                        </div>
                     </div>
                  </div>
                  <br/>
            
                  <div class="form-group col-md-9">
                     <div class="col-sm-offset-7 col-sm-3">
                        <button type="button" id="step3Btn" class="btn btn-default">Sign in</button>
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
