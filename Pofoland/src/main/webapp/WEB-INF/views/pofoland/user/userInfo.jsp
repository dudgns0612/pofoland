<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://egovframework.gov/ctl/ui" prefix="ui" %>

<!-- PAGE HEADER -->
<section id="page-header">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="section-title">
                    <h1>포폴랜드 회원정보</h1>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- /PAGE HEADER -->

<!-- USER INFO -->
<section id="blog">
	<div class="container">
		<div class="clearfix"></div>
		<div class="row">
			<div class="col-md-9">
				<div class="single-blog">
					<article>
						<c:choose>
							<c:when test="${not empty userInfoList}">
								<c:forEach items="${userInfoList}" var="user" varStatus="status">
									<h4 class="post-title" style="margin-bottom: 15px;">
										${condition.totalRecordCount - (condition.totalRecordCount - ((condition.currentPageNo-1) * condition.recordCountPerPage + status.index)-1)}. ${user.userNick}
									</h4>
									<div class="row">
										<div class="col-md-3">
											<img class="image-circle-header" src="${contextPath}/user/${user.userSeq}/image" alt="" ></img>
										</div>
										<div class="col-md-6">
											<div class="post-meta text-uppercase">
												<span>이메일 : ${user.userEmail}</span><br/>
												<span>활동점수 : ${user.userScore}</span><br/>
												<span>포트폴리오 : </span>
											</div>
										</div>
									</div>
								</c:forEach>
							</c:when>
							<c:otherwise>
                                        회원정보가 존재하지 않습니다.
                    		</c:otherwise>
						</c:choose>
					</article>
				</div>
				<hr>
				<ul class="pagination">
					<ui:pagination paginationInfo="${condition.paginationInfo}" jsFunction="goPage" type="text" />
				</ul>
			</div>
			<div class="col-md-3">
				<div class="sidebar-widget">
					<h5>회원 검색하기</h5>
					<div class="blog-search">
						<form:form commandName="condition" method="GET">
							<form:input path="search" />
							<form:input type="hidden" path="searchType" value="userNick"/>
							<span> 
								<form:button id="submit_btn" class="search-submit" type="submit">
									<i class="fa fa-search"></i>
								</form:button>
							</span>
						</form:form>
					</div>
				</div>
			</div>
		</div>
</section>
<!-- /USER INFO -->

<script type="text/javascript">
	function goPage(currentPageNo) {
		var form = $('#condition');
		form.append('<input type="hidden" name="currentPageNo" value="' + currentPageNo + '"/>');
		form.submit();
	}
</script>