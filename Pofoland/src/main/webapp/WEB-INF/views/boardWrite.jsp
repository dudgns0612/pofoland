<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<link rel="stylesheet" href="${contextPath}/resources/assets/summernote/summernote.css">
<script type="text/javascript" src="${contextPath}/resources/assets/summernote/summernote.js"></script>
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
    <div class="container">
        <h1>게시글 작성</h1>
        <form:form commandName="writeForm" action="/board" method="POST" cssClass="form-inline">
            <form:select path="jobCateSeq" cssClass="form-control">
                <c:forEach items="${jobCategories}" var="categoryItem">
                    <form:option value="${categoryItem.cateSeq}">${categoryItem.cateName}</form:option>
                </c:forEach>
            </form:select>
            <div id="editor"></div>
            <div class="pull-right">
                <form:button type="submit" class="btn btn-default">작성</form:button>
                <div class="clearfix"></div>
            </div>
        </form:form>
    </div>
</section>

<script type="text/javascript">
	$(document).ready(function(){
	    $('#editor').summernote({
			height: 500,
			minHeight: null,
			maxHeight: null,
			focus: true
		});
	})
</script>
