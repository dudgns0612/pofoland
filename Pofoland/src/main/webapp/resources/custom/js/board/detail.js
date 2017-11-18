$(document).ready(function(){
    
    
})

// 댓글 등록
function addReply() {
    var data = $("#replyForm").serializeObject();
    
    // TODO : VALIDATION 체크
    
    $.ajax({
        url: contextPath + '/api/board/reply',
        type: 'POST',
        data: data,
        dataType: 'JSON',
        success: function(response) {
            alert(response.message);
            location.reload();
        }
    });
}

//댓글 수정
function editReply() {
    
}

//댓글 삭제
function deleteReply(boardReplySeq) {
    $.ajax({
        url: contextPath + '/api/board/reply/' + boardReplySeq,
        type : 'DELETE',
        dataType: 'JSON',
        success: function(response) {
            location.reload();
        }
    })
}
