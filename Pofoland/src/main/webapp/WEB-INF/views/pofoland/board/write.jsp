<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<link rel="stylesheet" href="${contextPath}/resources/assets/summernote/summernote.css">
<script type="text/javascript" src="${contextPath}/resources/assets/summernote/summernote.js"></script>
<script type="text/javascript" src="${contextPath}/resources/assets/js/jquery.form.js"></script>
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
        <form:form commandName="writeForm" action="/api/board" method="POST" onsubmit="return writeBoard()" cssClass="form-inline" enctype="multipart/form-data">
            <div class="conditions" style="margin-bottom: 15px; margin-top: 15px;">
                <form:hidden path="userSeq"/>
                <form:hidden path="boardContent"/>
                <div class="pull-left">
                    <form:label path="boardTitle">제목 : </form:label>
                    <form:input path="boardTitle" cssClass="form-control" style="width: 500px; margin-right:15px;"/>
                </div>
                <div class="pull-right">
                    <form:label path="boardCategory">카테고리 : </form:label>
                    <form:select path="boardCategory" cssClass="form-control">
                        <c:forEach items="${boardCategories}" varStatus="idx" var="categoryItem">
                            <c:if test="${idx.index != 0}">
                                <form:option value="${categoryItem.code}">${categoryItem.codeName}</form:option>    
                            </c:if>
                        </c:forEach>
                    </form:select>
                    <form:label path="jobCategory">직업군 : </form:label>
                    <form:select path="jobCategory" cssClass="form-control">
                        <c:forEach items="${jobCategories}" var="categoryItem">
                            <form:option value="${categoryItem.code}">${categoryItem.codeName}</form:option>
                        </c:forEach>
                    </form:select>
                </div>
                <div class="clearfix"></div>
            </div>
            <label for="editor">본문</label>
            <div id="editor"></div>
            <div id="fileUploader"></div>
            <div class="pull-right" style="margin-bottom: 15px;">
                <form:button type="submit" class="btn btn-send">작성</form:button> 
                <a href="${contextPath}/board/main" class="btn btn-send">취소</a>
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
			    ['insert', ['picture', 'link', 'video', 'table', 'hr']],
			    ['view', ['fullscreen', 'codeview']]
			],
			callbacks: {
			    onImageUpload: temporaryImageUpload
			}
		});
	}
	
	function temporaryImageUpload(files) {
        var formData = new FormData();
        formData.append('uploadImage', files[0]);
        
        $.ajax({
            url: '${contextPath}/file/tempImageUpload',
            type: "post",
            data: formData,
	        cache: false,
	        contentType: false,
	        enctype: 'multipart/form-data',
	        processData: false,
	        success: function(uploadedUrl) {
	            console.log(uploadedUrl)
	            $('#editor').summernote('insertImage', '${contextPath}/file/view/'+uploadedUrl);
	        }
	     });
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
