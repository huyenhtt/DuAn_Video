<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>My History</title>
<link rel="stylesheet" href="/AssUI/template/user/css/bootstrap.min.css">
<link rel="stylesheet"
	href="/AssUI/template/user/fontawesome/css/all.min.css">
<link rel="stylesheet"
	href="/AssUI/template/user/css/templatemo-style.css">
</head>
<body>
	<%@ include file="/common/header.jsp"%>

	<div class="x tm-container-content tm-mt-60">
		<div class="row mb-4">
			<h2 class="col-6 tm-text-primary text-center">List History</h2>
			<div class="col-6 d-flex justify-content-end align-items-center">
				<form action="" class="tm-text-primary">
					Page <input type="text" value="1" size="1"
						class="tm-input-paging tm-text-primary"> of 200
				</form>
			</div>
		</div>
		<div class="row tm-mb-90 tm-gallery">
			<c:forEach items="${videos2}" var="video">
				<div class="col-xl-3 col-lg-4 col-md-6 col-sm-6 col-12 mb-5">
				<h5  class="tm-text-secondary" style="white-space: nowrap;overflow: hidden;">
				${video.title}
				</h5>
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
					<span class="tm-text-gray-light">${video.shares} Share</span>
						<span class="tm-text-gray-light">${video.views}
							Views</span>
					</div>
				</div>
			</c:forEach>
		</div>
		<!-- row -->
		<div class="row tm-mb-90">
			<div
				class="col-12 d-flex justify-content-between align-items-center tm-paging-col">
				<a href="javascript:void(0);"
					class="btn btn-primary tm-btn-prev mb-2 disabled">Previous</a>
				<div class="tm-paging d-flex">
					<a href="javascript:void(0);" class="active tm-paging-link">1</a> <a
						href="javascript:void(0);" class="tm-paging-link">2</a> <a
						href="javascript:void(0);" class="tm-paging-link">3</a> <a
						href="javascript:void(0);" class="tm-paging-link">4</a>
				</div>
				<a href="javascript:void(0);" class="btn btn-primary tm-btn-next">Next
					Page</a>
			</div>
		</div>
	</div>
	<!-- container-fluid, tm-container-content -->

	<%@ include file="/common/footer.jsp"%>
</body>
</html>