<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>mysite</title>
	<meta http-equiv="content-type" content="text/html; charset=utf-8">
	<link href="${pageContext.request.contextPath}/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">

		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		
		<c:import url="/WEB-INF/views/includes/navigation.jsp"></c:import>
		
		<div id="content">
			<div id="board" class="board-form">
				<table class="tbl-ex">
					<tr>
						<th colspan="2">글보기</th>
					</tr>
					<tr>
						<td class="label">제목</td>
						<td>${BoardVo.title}</td>
					</tr>
					<tr>
						<td class="label">내용</td>
						<td>
							<div class="view-content">
								${BoardVo.content}
							</div>
						</td>
					</tr>
				</table>
				<div class="bottom">
					
					<a href="${pageContext.request.contextPath}/board/list">글목록</a>
					<c:if test="${sessionScope.authUser.no == BoardVo.user_no}">				
						<a href="${pageContext.request.contextPath}/board/modifyform?no=${BoardVo.no}">글수정</a>
					</c:if>	
				</div>
			</div>
		</div>

		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
	</div>
</body>
</html>