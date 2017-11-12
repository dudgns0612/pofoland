<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<section id="page-header">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="section-title">
                    <h1>포폴랜드 커뮤니티</h1>
                </div>
            </div>
        </div>
    </div>
</section>
<section class="blog">
	<div class="container" style="border:1px solid #BDBDBD;margin-bottom:10px">
		<div class="page-header" style="height: 60px; ">
			<div class="pull-left">
				<h4>[ ${board.boardTitle} ] <small>| ${board.boardCategoryName}, ${board.jobCategoryName}</small></h4>
			</div>
			<div class="pull-right" style="margin-top:28px">
				<small>${board.boardUdtDt}</small>
			</div>		  	
		</div>
		${board.userNick} (${board.userId})
		<div class="clearfix"></div>
		<div class="panel panel-default no-rounded" align="center">
            <div class="panel-body">
                ${board.boardContent}
            </div>
        </div>
        <c:if test="${user.userSeq == board.userSeq }">
            <div class="pull-right">
                <a class="btn btn-send" href="${contextPath}/board/modify?boardSeq=${board.boardSeq}">수정</a>
                <a class="btn btn-send" href="${contextPath}/board/modify?boardSeq=${board.boardSeq}">삭제</a>
            </div>
        </c:if>
        <div class="clearfix"></div>
        <hr>
		<div class="panel panel-default no-rounded">
            <div class="panel-heading">
                <small>댓글</small>
            </div>
            <div class="panel-body">
                <div>
                    <form class="form-inline">
                        <textarea rows="2" style="width: 70%" class="form-control no-rounded"></textarea> 
                        <input type="button" value="댓글등록" style="height:54px" class="btn btn-send"/>
                    </form>
                </div>
                <div style="margin-top: 10px;">
                    <ul>
                        <c:forEach items="${board.boardReplyList}" var="reply">
                            <li><h5>[${reply.userNick}] <small>${reply.boardReplyContent} - ${reply.boardReplyUdtDt}</small></h5></li>
                        </c:forEach> 
                    </ul>
                </div>
            </div>
		</div>
	</div>		
	
</section>