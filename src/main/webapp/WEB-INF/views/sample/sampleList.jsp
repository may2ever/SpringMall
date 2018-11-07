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
<body>
	<div class="container">
		<h1>sampleList</h1>
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
						<td><a href="/sample/removeSample?sampleNo=${sample.sampleNo}&currentPage=${pageMaker.pageNum}">DELETE</a></td>
						<td><a href="/sample/modifySample?sampleNo=${sample.sampleNo}">UPDATE</a></td>
					</tr>	
				</c:forEach>
				<tr>
					<td colspan = "5" align="center">
						<ul class="pagination">
							<c:if test="${pageMaker.prevPage}">
								<li class="page-item"><a href="${pageContext.request.contextPath}/sample/sampleList?currentPage=${(pageMaker.currentBlock - 1) * pageMaker.pagePerBlock}"><</a></li>
							</c:if>
							<c:forEach var="i" begin="${pageMaker.startPage}" end="${pageMaker.endPage}" step="1">
								<c:if test="${pageMaker.pageNum == i}">
									<li class="page-item"><a href="javascript:void(0)">${i}</a></li>
								</c:if>
								<c:if test="${pageMaker.pageNum != i}">
									<li class="page-item"><a href="${pageContext.request.contextPath}/sample/sampleList?currentPage=${i}">${i}</a></li>
								</c:if>
							</c:forEach>
							
							<c:if test="${pageMaker.nextPage}">
								<li class="page-item"><a href="${pageContext.request.contextPath}/sample/sampleList?currentPage=${pageMaker.currentBlock * pageMaker.pagePerBlock + 1}">></a></li>
							</c:if>
						</ul>
					</td>
				</tr>
			</tbody>
			
		</table>
	</div>
</body>
</html>