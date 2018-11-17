<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link href="${pageContext.request.contextPath}/resources/board.css"
	rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>게시판_지원자 김현이</title>
</head>
<body>
<div class="container">
		<h2>Hover Rows</h2>
		<p>The .table-hover class enables a hover state on table rows:</p>
		<!-- <form action="" method=""> -->
			
				<table class="boardTable">
					<tr>
						<td>글제목</td>
						<td>${dto.title}</td>
					</tr>
					<tr>
						<td>작성자</td>
						<td>${dto.writer}</td>
					</tr>
					<tr>
						<td>글내용</td>
						<td>${dto.content}</td>
					</tr>
				</table>
			<button type="button" class="btn btn-default" id="">목록 보기</button>
			<button type="button" class="btn btn-default" id="">수정</button>
			<button type="button" class="btn btn-default" id="">삭제</button>
		<!-- </form> -->
	</div>
</body>
</html>