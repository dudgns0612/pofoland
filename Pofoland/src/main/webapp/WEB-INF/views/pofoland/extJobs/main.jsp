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
		<div class="row">
			<div class="col-xs-11">
					<div class="col-xs-2"></div>
					<div class="col-xs-1">
						검색어
					</div>
					<div class="col-xs-6">
						<input type="text" id="keywords" size="70"/>
					</div>
					<div class="col-xs-1">
						<input type="button" id="searchJobs" value="검색" />
					</div>
					<div class="col-xs-2"></div>
			</div>
			<div class="col-xs-1"></div>

		</div>
	</div>
</section>
<!-- 리스트 출력 -->