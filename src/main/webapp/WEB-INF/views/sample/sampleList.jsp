<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>sampleList</title>
</head>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!-- jquery CDN -->
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<body>
	<div class="container" align = "center">
		<h1>SampleList</h1>
		<div align="left">
			<a href="/sample/addSample"><button class = "btn btn-primary" >샘플 추가</button></a>
		</div>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>SAMPLE NO</th>
					<th>SAMPLE ID</th>
					<th>SAMPLE PW</th>
					<th>DELETE</th>
					<th>UPDATE</th>
				</tr>
			</thead>
			<tbody>
				<!--model안의 sampleList 가져와서 사용  -->
				<c:forEach var="sample" items="${sampleList}">
					<tr>
						<td>${sample.sampleNo}</td>
						<td>${sample.sampleId}</td>
						<td>${sample.samplePw}</td>
						<td><a href="/sample/removeSample?sampleNo=${sample.sampleNo}&currentPage=${pageInfo.currentPage}">DELETE</a></td>
						<td><a href="/sample/modifySample?sampleNo=${sample.sampleNo}">UPDATE</a></td>
					</tr>	
				</c:forEach>
				<tr >
					<td colspan = "5" align="center">
						<ul class="pagination">
							<c:if test="${pagingInfo.currentScreen > 1}">
								<li class="page-item"><a href="/sample/sampleList?currentPage=${(pagingInfo.currentScreen - 1) * pagingInfo.pagePerScreen}"><</a></li>
							</c:if>
							<c:forEach var="i" begin="${pagingInfo.startScreenPage}" end="${pagingInfo.startScreenPage + pagingInfo.currentScreenPage - 1}" step="1">
								<li class="page-item" id = "pageItem${i}"><a href="/sample/sampleList?currentPage=${i}">${i}</a></li>
							</c:forEach>
							<c:if test="${pagingInfo.currentScreen <  pagingInfo.lastScreen}">
								<li class="page-item"><a href="/sample/sampleList?currentPage=${pagingInfo.currentScreen * pagingInfo.pagePerScreen + 1}">></a></li>
							</c:if>
						</ul>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
<script>
	$(document).ready(()=>{
		$('#pageItem'+${pagingInfo.currentPage}).addClass('active');
	});
</script>
</body>
</html>