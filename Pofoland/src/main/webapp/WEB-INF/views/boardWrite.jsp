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
        <form:form commandName="writeForm" action="/board" method="POST" onsubmit="return writeBoard()" cssClass="form-inline">
            <div class="conditions" style="margin-bottom: 15px; margin-top: 15px;">
                <form:hidden path="userSeq"/>
                <form:hidden path="boardContent"/>
                <div class="pull-left">
                    <form:label path="boardTitle">제목 : </form:label>
                    <form:input path="boardTitle" cssClass="form-control" style="width: 500px; margin-right:15px;"/>
                </div>
                <div class="pull-right">
                    <form:label path="boardCateSeq">카테고리 : </form:label>
                    <form:select path="boardCateSeq" cssClass="form-control">
                        <c:forEach items="${boardCategories}" varStatus="idx" var="categoryItem">
                            <c:if test="${idx.index != 0}">
                                <form:option value="${categoryItem.cateSeq}">${categoryItem.cateName}</form:option>
                            </c:if>
                        </c:forEach>
                    </form:select>
                    <form:label path="jobCateSeq">직업군 : </form:label>
                    <form:select path="jobCateSeq" cssClass="form-control">
                        <c:forEach items="${jobCategories}" var="categoryItem">
                            <form:option value="${categoryItem.cateSeq}">${categoryItem.cateName}</form:option>
                        </c:forEach>
                    </form:select>
                </div>
                <div class="clearfix"></div>
            </div>
            <div id="editor"></div>
            <div class="pull-right" style="margin-bottom: 15px;">
                <form:button type="submit" class="btn btn-default">작성</form:button>
                <div class="clearfix"></div>
            </div>
        </form:form>
    </div>
</section>

<script type="text/javascript">
	$(document).ready(function(){
	    initializeEditor(500);
	})
	
	function initializeEditor(editorHeight) {
	    $('#editor').summernote({
			height: editorHeight,
			minHeight: null,
			maxHeight: null,
			focus: true,
			toolbar: [
			    ['style', ['bold', 'italic', 'underline', 'clear']],
			    ['font', ['strikethrough', 'superscript', 'subscript']],
			    ['fontsize', ['fontsize']],
			    ['color', ['color']],
			    ['para', ['ul', 'ol', 'paragraph']],
			    ['height', ['height']],
			    ['insert', ['picture', 'link', 'video', 'table', 'hr']]
			],
			callbacks: {
			    onImageUpload: temporaryImageUpload
			}
		});
	}
	
	function temporaryImageUpload(files) {
	    for(var i = 0; i < files.length; i++) {
	        var form = new FormData();
	        form.append('file', files[i]);
	        $.ajax({
	          data: form,
	          type: "POST",
	          url: '${contextPath}/file/tempImageUpload',
	          cache: false,
	          contentType: false,
	          enctype: 'multipart/form-data',
	          processData: false,
	          success: function(uploadedUrl) {
	            $('#editor').summernote('editor.insertImage', uploadedUrl);
	          }
	        });

	    }
	}
	
	function writeBoard() {
	    var isValid = true;
	    var editor = $('#editor');
	    
	    $('#writeForm').find('#boardContent').val(editor.summernote('code'));
	    
	    /*
	     * form validation 작업
	     */
	    
	    return isValid;
	}
</script>