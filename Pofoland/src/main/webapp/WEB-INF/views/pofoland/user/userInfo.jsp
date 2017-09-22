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
								<c:forEach items="${userInfoList}" var="user">
									<h4 class="post-title">
										${user.userNick}
									</h4>
									<div class="post-meta text-uppercase">
										<span>${user.userId}</span>
										<span>By <a href="/user/${user.userSeq}">${user.userNick}</a></span>
									</div>
									<div class="post-article">${user.userProfileUrl}</div>
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

	function setQuery(name, value) {
		$('#' + name).val(value);
		$('#condition').submit();
	}
</script>