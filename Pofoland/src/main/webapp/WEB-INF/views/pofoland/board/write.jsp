<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<link rel="stylesheet" href="${contextPath}/resources/assets/summernote/summernote.css">
<link rel="stylesheet" href="${contextPath}/resources/assets/fine-uploader/fine-uploader-new.css">
<script type="text/javascript" src="${contextPath}/resources/assets/summernote/summernote.js"></script>
<script type="text/javascript" src="${contextPath}/resources/assets/js/jquery.form.js"></script>
<script type="text/javascript" src="${contextPath}/resources/assets/fine-uploader/jquery.fine-uploader.js"></script>
<script type="text/template" id="qq-template-gallery">
    <div class="qq-uploader-selector qq-uploader qq-gallery" qq-drop-area-text="Drop files here">
        <div class="qq-total-progress-bar-container-selector qq-total-progress-bar-container">
            <div role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" class="qq-total-progress-bar-selector qq-progress-bar qq-total-progress-bar"></div>
        </div>
        <div class="qq-upload-drop-area-selector qq-upload-drop-area" qq-hide-dropzone>
            <span class="qq-upload-drop-area-text-selector"></span>
        </div>
        <div class="qq-upload-button-selector qq-upload-button">
            <div>Upload a file</div>
        </div>
        <span class="qq-drop-processing-selector qq-drop-processing">
            <span>Processing dropped files...</span>
            <span class="qq-drop-processing-spinner-selector qq-drop-processing-spinner"></span>
        </span>
        <ul class="qq-upload-list-selector qq-upload-list" role="region" aria-live="polite" aria-relevant="additions removals">
            <li>
                <span role="status" class="qq-upload-status-text-selector qq-upload-status-text"></span>
                <div class="qq-progress-bar-container-selector qq-progress-bar-container">
                	<div role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" class="qq-progress-bar-selector qq-progress-bar"></div>
                </div>
                <span class="qq-upload-spinner-selector qq-upload-spinner"></span>
                <div class="qq-thumbnail-wrapper">
                    <img class="qq-thumbnail-selector" qq-max-size="120" qq-server-scale>
                </div>
                <button type="button" class="qq-upload-cancel-selector qq-upload-cancel">X</button>
                <button type="button" class="qq-upload-retry-selector qq-upload-retry">
                    <span class="qq-btn qq-retry-icon" aria-label="Retry"></span>
                    Retry
                </button>

                <div class="qq-file-info">
                    <div class="qq-file-name">
                        <span class="qq-upload-file-selector qq-upload-file"></span>
                        <span class="qq-edit-filename-icon-selector qq-edit-filename-icon" aria-label="Edit filename"></span>
                    </div>
                    <input class="qq-edit-filename-selector qq-edit-filename" tabindex="0" type="text">
                    <span class="qq-upload-size-selector qq-upload-size"></span>
                    <button type="button" class="qq-btn qq-upload-delete-selector qq-upload-delete">
                        <span class="qq-btn qq-delete-icon" aria-label="Delete"></span>
                    </button>
                    <button type="button" class="qq-btn qq-upload-pause-selector qq-upload-pause">
                        <span class="qq-btn qq-pause-icon" aria-label="Pause"></span>
                    </button>
                    <button type="button" class="qq-btn qq-upload-continue-selector qq-upload-continue">
                        <span class="qq-btn qq-continue-icon" aria-label="Continue"></span>
                    </button>
                </div>
            </li>
        </ul>

        <dialog class="qq-alert-dialog-selector">
            <div class="qq-dialog-message-selector"></div>
            <div class="qq-dialog-buttons">
                <button type="button" class="qq-cancel-button-selector">Close</button>
            </div>
        </dialog>

        <dialog class="qq-confirm-dialog-selector">
            <div class="qq-dialog-message-selector"></div>
            <div class="qq-dialog-buttons">
                <button type="button" class="qq-cancel-button-selector">No</button>
                <button type="button" class="qq-ok-button-selector">Yes</button>
            </div>
        </dialog>

        <dialog class="qq-prompt-dialog-selector">
            <div class="qq-dialog-message-selector"></div>
            <input type="text">
            <div class="qq-dialog-buttons">
                <button type="button" class="qq-cancel-button-selector">Cancel</button>
                <button type="button" class="qq-ok-button-selector">Ok</button>
            </div>
        </dialog>
    </div>
</script>
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
        <form:form commandName="writeForm" action="/board" method="POST" onsubmit="return writeBoard()" cssClass="form-inline" enctype="multipart/form-data">
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
