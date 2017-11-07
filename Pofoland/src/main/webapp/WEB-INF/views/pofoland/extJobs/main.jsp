<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script type="text/javascript">
	var contextPath = '${contextPath}';
</script>
<!-- 검색조건 입력  -->
<section id="page-header">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="section-title">
					포폴랜드 구인구직
				</div>
			</div>
		</div>
	</div>
</section>
<section id="blog">
	<div class="container">
	<form id="extJobForm" method="POST" accept-charset="utf-8" action="${contextPath }/extJobs/search">
		<div class="row">
			<div class="col-xs-11">
					<div class="col-xs-2"></div>
					<div class="col-xs-1">
						검색어
					</div>
					<div class="col-xs-6">
						<input type="text" id="keyword" name="keyword" size="70"/>
					</div>
					<div class="col-xs-1">
						<input type="submit" id="searchJobs" class="btn btn-block btn-default" value="검색" />
					</div>
					<div class="col-xs-2"></div>
			</div>
			<div class="col-xs-1"></div>

		</div>
	</form>
		<div class="row">
				<div class="col-md-9">
				<div class="single-blog">
					<article>
					<c:choose>
					<c:when test="${extJobsList != null }">
						<c:forEach items="${extJobsList}" var="extJobsVO">
									<h4 class="post-title" style="margin-bottom: 15px;">
										${extJobsVO.title }
									</h4>
									<div class="row">
										<div class="col-md-3">
											<img class="image-circle-header" src="/user/94/image" alt="" ></img>
										</div>
										<div class="col-md-6">
											<div class="post-meta text-uppercase">
												<span>CompanyName : ${extJobsVO.company }</span><br/>
												<span>Salary : ${extJobsVO.salary }</span><br/>
												<span>Keyword : ${extJobsVO.keyword }</span>
												${extJobsVO.title }
											</div>
										</div>
									</div>
						</c:forEach>
					</c:when>
					</c:choose>
					</article>
				</div>
				<hr>
				<ul class="pagination">
					<li class="active"><a href="#">1</a></li>

				</ul>
			</div>
			
		</div>
	</div>
</section>
<!-- 리스트 출력 -->