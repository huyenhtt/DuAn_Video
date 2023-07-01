<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Online Entertainment</title>
<link rel="stylesheet" href="/AssUI/template/user/css/bootstrap.min.css">
<link rel="stylesheet"
	href="/AssUI/template/user/fontawesome/css/all.min.css">
<link rel="stylesheet"
	href="/AssUI/template/user/css/templatemo-style.css">
</head>
<body>
	<%@ include file="/common/header.jsp"%>

	<div class="container-fluid tm-container-content tm-mt-60">
		<div class="row mb-4">
			<h2 class="col-6 tm-text-primary">List Videos</h2>
			<div class="col-6 d-flex justify-content-end align-items-center">
				<form action="" class="tm-text-primary">
					Page <input type="text" value="1" size="1"
						class="tm-input-paging tm-text-primary"> of 200
				</form>
			</div>
		</div>
		<div class="row tm-mb-90 tm-gallery">
			<c:forEach items="${list}" var="video">
				<div class="col-xl-3 col-lg-4 col-md-6 col-sm-6 col-12 mb-5">
					<h5 class="tm-text-secondary"
						style="white-space: nowrap; overflow: hidden;">
						${video.title}</h5>
					<figure class="effect-ming tm-video-item">
						<img src="${video.poster}" alt="Image"
							class="img-fluid">
						<figcaption
							class="d-flex align-items-center justify-content-center">
							<h2>View more</h2>
							<a href="/AssUI/video?action=watch&id=${video.href}"></a>

						</figcaption>
					</figure>
					<div class="d-flex justify-content-between tm-text-gray">
						<span class="tm-text-gray-light">${video.shares} Share</span> <span
							class="tm-text-gray-light">${video.views} Views</span>
					</div>
				</div>
			</c:forEach>
		</div>
		<!--row -->
		<div class="row tm-mb-90">
			<div
				class="col-12 d-flex justify-content-between align-items-center tm-paging-col">
				<c:if test="${curentPage==1}">
					<a href="javascript:void(0);"
						class="btn btn-primary tm-btn-prev mb-2 disabled">Previous</a>
				</c:if>
				<c:if test="${curentPage>1}">
					<a href="index?page=${curentPage-1}"
						class="btn btn-primary tm-btn-prev mb-2 ">Previous</a>
				</c:if>
				<div class="tm-paging d-flex">
					<c:forEach varStatus="i" begin="1" end="${maxPage}">

						<a href="index?page=${i.index}"
							class="tm-paging-link ${curentPage == i.index ? "active":""}">${i.index}</a>
					</c:forEach>
				</div>
				<c:if test="${curentPage==maxPage}">
					<a href="javascript:void(0);"
						class="btn btn-primary tm-btn-prev mb-2 disabled">Next</a>
				</c:if>
				<c:if test="${curentPage<maxPage}">
					<a href="index?page=${curentPage+1}"
						class="btn btn-primary tm-btn-prev mb-2 ">Next</a>
				</c:if>
				
			</div>
		</div>
	</div>
	<!-- container-fluid, tm-container-content -->

	<%@ include file="/common/footer.jsp"%>
</body>
</html>