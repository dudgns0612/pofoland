$(document).ready(function(){
    
    
})

function addReply(boardSeq, userSeq) {
    var requestData = $("#replyForm");
    
    $.ajax({
        url: contextPath + '/api/board/reply',
        type: 'POST',
        data: requestData.serialize(),
        dataType: 'JSON',
        success: function(response) {
            alert(response.message);
            location.reload();
        }
    });
}