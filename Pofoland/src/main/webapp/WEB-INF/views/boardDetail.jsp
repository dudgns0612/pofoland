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
				<h3>${board.boardTitle} <small>| ${board.boardCateName},S{board.jobCateName}</small></h3>
			</div>
			<div class="pull-right" style="margin-top:28px">
				<small>${board.boardUdtDt}</small>
			</div>		  	
		</div>
		${board.userNick}(userId)
		<div class="clearfix"></div>
		<div style="text-align : center; ">
		   <p>${board.boardContent}</p>
		   <p>${board.boardContent}</p>
		   <p>${board.boardContent}</p>
		   <p>${board.boardContent}</p>
		   <p>${board.boardContent}</p>
		   <p>${board.boardContent}</p>
		   <p>${board.boardContent}</p>
		   <p>${board.boardContent}</p>
		   <p>${board.boardContent}</p>
		   <p>${board.boardContent}</p>
		   <p>${board.boardContent}</p>
		   <p>${board.boardContent}</p>
		   <p>${board.boardContent}</p>
		   <p>${board.boardContent}</p>
		   <p>${board.boardContent}</p>
		   <p>${board.boardContent}</p>
		   <p>${board.boardContent}</p>
		   <p>${board.boardContent}</p>
		   <p>${board.boardContent}</p>
		</div>
		<div>
			댓글:60개  | 조회수:${board.boardHitCnt}
		</div>
	</div>		

</section>