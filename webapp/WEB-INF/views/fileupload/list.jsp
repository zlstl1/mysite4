<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/gallery.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>
<script type="text/javascript"src="${pageContext.request.contextPath }/assets/bootstrap/js/bootstrap.min.js"></script>

<style>


	.card {
		border: 1px solid black; 
		float: left; 
		
		margin-right: 5px; 
		margin-bottom: 5px; 
	}
	
	
	.cardImg {
		width: 100%; 
		max-width:128px; 
		vertical-align: middle"
	} 
</style>

<title>Mysite</title>
</head>
<body>
	
	<div id="container">
		
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<c:import url="/WEB-INF/views/includes/navigation.jsp"></c:import>
		
		<div id="wrapper">
			<div id="content">
				
				<div id="gallery">
					<form method="post" action="${pageContext.request.contextPath }/ga/upload" enctype="multipart/form-data">
						<table>
							<tr>
								<td>첨부파일</td>
								<td><input type="file" name="file"></td>
								<td><input type="submit" value="파일업로드"></td>
							</tr>
						</table>
					</form>
					
					
					<ul id="gallery-list">
						<c:forEach items="${fileList }" var="fileVo">
							<li>
							<a href="${pageContext.request.contextPath }/ga/delete?no=${fileVo.no}">삭제 / </a>
							<a href="#" class="sizeUp" id="${pageContext.request.contextPath }/upload/${fileVo.saveName}">크게보기</a>
								<div>
									<img  src="${pageContext.request.contextPath }/upload/${fileVo.saveName}" >
								</div>
							</li>
						</c:forEach>
					</ul>
				</div>
				
				
				
			</div><!-- /content -->
		</div><!-- /wrapper -->
		
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
		
	</div><!-- /container -->
	
	
	
	<div class="modal fade" id="img-sizeUp">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
				</div>
				<div class="modal-body">
					<img id="sizeUp-img" src="" width="400" height="400">
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	
	
	
</body>

<script type="text/javascript">
	
	$(document).ready(function() {
		$("#img-sizeUp").modal("hide");
	});

	$("#gallery-list").on("click",".sizeUp",function(){
		console.log("!");
		$("#img-sizeUp").modal();
		var $this = $(this);
		console.log($this.attr("id"));
		$("#sizeUp-img").attr("src",($this.attr("id")));
	});

</script>


</html>		
		
