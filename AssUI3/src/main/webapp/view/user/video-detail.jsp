<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${video.title}</title>
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
			<h2 class="col-12 tm-text-primary">${video.title}</h2>
		</div>
		<div class="row tm-mb-90">
			<div class="col-xl-8 col-lg-8 col-md-6 col-sm-12">
				<iframe id="tm-video"
					src="https://www.youtube.com/embed/${video.href}"> </iframe>
			</div>
			<div class="col-xl-4 col-lg-4 col-md-6 col-sm-12"
				style="min-height: 500px !important">
				<div class="tm-bg-gray tm-video-details">
					<c:if test="${not empty sessionScope.curentUser}">
						<div class="text-center mb-5">
						<a class="btn btn-primary tm-btn-big" href='<c:url value='/video?action=like&id=${video.href}'></c:url>'>
							<!--  <button id="likeOrUnlikeBtn" class="btn btn-primary tm-btn-big">-->
                    <c:choose>
                   <c:when test="${flagLikeBtn == true}">Unlike</c:when>
                   <c:otherwise>Like</c:otherwise>
                    </c:choose>
                            </a>
						</div>
						<div class="text-center mb-5">
							<a href='#' class="btn btn-primary tm-btn-big">Share</a>
						</div>
					</c:if>

					<div class="mb-4">
						<h3 class="tm-text-gray-dark mb-3">Description</h3>
						<p>${video.descriptions}</p>
					</div>
					
                     <input id="videoIdH" type="hidden" value="${video.href}">
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/common/footer.jsp"%>
	<!--  <script >
	$('#likeOrUnlikeBtn').click(function(){
		var videoId=$('#videoIdH').val();
		$.ajax({
			url: 'video?action=like&id='+videoId;
		alert(videoId);
		}).then(function(data){
			var text=$('#likeOrUnlikeBtn').text();
			if (text.indexOf('Like')!=-1)) {
			$('#likeOrUnlikeBtn').text('Unlike');
			}else{
				$('#likeOrUnlikeBtn').text('Like');
			}
		}).fail(function(error){
			alert("oh!! Please try again!");
		})
	});
	
	</script> -->
</body>
</html>