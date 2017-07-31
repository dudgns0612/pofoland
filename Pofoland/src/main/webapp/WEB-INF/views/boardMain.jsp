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
                    <h1>포폴랜드 커뮤니티</h1>
                    <span class="st-border"></span>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- /PAGE HEADER -->

<!-- BLOG -->
<section id="blog">
    <div class="container">
        <div class="row">
            <div class="col-md-9">
                <c:forEach items="${boardList}" var="board">
                    <div class="single-blog">
                        <article>
                            <h4 class="post-title"><a href="">${board.boardTitle}</a></h4>
                            <div class="post-meta text-uppercase">
                                <span>${board.boardRegDt}</span>
                                <span>In <a href="">${board.jobCateName}</a></span>
                                <span>By <a href="/user/${board.userSeq}">${board.userNick}</a></span>
                            </div>
                            <div class="post-article">
                                ${board.boardContent}
                            </div>
                        </article>
                    </div>
                    <hr>
                </c:forEach>
                <ul class="pagination">
                    <ui:pagination paginationInfo="${condition.paginationInfo}" jsFunction="goPage" type="text"/>
                </ul>
            </div>

            <div class="col-md-3">
                <div class="sidebar-widget">
                    <div class="blog-search">
                    <form:form commandName="condition" method="GET">
                        <form:input type="hidden" path="searchType" value="content"/>
                        <form:input path="search"/>
                        <span>
                            <form:button id="submit_btn" class="search-submit" type="submit">
                                <i class="fa fa-search"></i>
                            </form:button>
                        </span>
                    </form:form>
                    </div>
                </div>
                <div class="sidebar-widget">
                    <h4 class="sidebar-title">Categories</h4>
                    <ul>
                        <c:forEach items="${boardCategories}" var="categoryItem">
                            <li><a href="${contextPath}/boardMain?search=${categoryItem.cateSeq}&searchType=boardCategory">${categoryItem.cateName}</a></li>
                        </c:forEach>
                    </ul>
                </div>
                <div class="sidebar-widget">
                    <h4 class="sidebar-title">Flickr</h4>
                    <ul class="content-flickr">
                        <li>
                            <a href="#" title="">
                                <img class="img-responsive" src="${contextPath}/resources/assets/images/img1.png" alt="">
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <img class="img-responsive" src="${contextPath}/resources/assets/images/img2.png" alt="">
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <img class="img-responsive" src="${contextPath}/resources/assets/images/img3.png" alt="">
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <img class="img-responsive" src="${contextPath}/resources/assets/images/img4.png" alt="">
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <img class="img-responsive" src="${contextPath}/resources/assets/images/img5.png" alt="">
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <img class="img-responsive" src="${contextPath}/resources/assets/images/img6.png" alt="">
                            </a>
                        </li>
                    </ul>
                </div>
                <div class="sidebar-widget">
                    <h4 class="sidebar-title">Tags</h4>
                    <div class="tagcloud">
                        <c:forEach items="${jobCategories}" var="categoryItem">
                            <a href="${contextPath}/boardMain?search=${categoryItem.cateSeq}&searchType=jobCategory">${categoryItem.cateName}</a>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- /BLOG -->
<script type="text/javascript">
	function goPage(currentPageNo) {
		var form = $('#condition');
		form.append('<input type="hidden" name="currentPageNo" value="'+currentPageNo+'"/>');
		form.submit();
	}
</script>