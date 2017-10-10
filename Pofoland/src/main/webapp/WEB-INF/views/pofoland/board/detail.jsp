<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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
		${board.userNick}(${board.userId})
		<div class="clearfix"></div>
		<div style="margin-top: 20px; margin-bottom: 20px;">${board.boardContent}</div>
		<div>댓글:60개  | 조회수:${board.boardHitCnt}</div>
		<div class="panel panel-default">
		  <div class="panel-body">
		 	<div>
		 		<h6>댓글</h6>
		    </div>
		    <div>
		    	<form class="form-inline">
		    		<textarea style="height:80px; width:600px; resize: none;" class="form-control"></textarea><input type="button" value="댓글등록" style="height:80px; width: 100px" class="form-control"/>
		    	</form>
		    </div>
		  </div>
		  
		</div>
	</div>		
	
</section>