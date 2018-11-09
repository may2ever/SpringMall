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
		<table class="table table-hover" style="margin: 0 auto">
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
						<td><a href="/sample/removeSample?sampleNo=${sample.sampleNo}&currentPage=${pagingInfo.currentPage}">DELETE</a></td>
						<td><a href="/sample/modifySample?sampleNo=${sample.sampleNo}">UPDATE</a></td>
					</tr>	
				</c:forEach>
				<tr>
					<td colspan = "5" align="center" style=": 10px">
						<ul class="pagination" style="margin: 0 auto">
							<c:if test="${pagingInfo.currentScreen > 1}">
								<li class="page-item"><a href="/sample/sampleList?currentPage=${(pagingInfo.currentScreen - 1) * pagingInfo.pagePerScreen}&searchQuery=${searchQuery}&searchType=${searchType}"><</a></li>
							</c:if>
							<c:forEach var="i" begin="${pagingInfo.startScreenPage}" end="${pagingInfo.startScreenPage + pagingInfo.currentScreenPage - 1}" step="1">
								<li class="page-item" id = "pageItem${i}"><a href="/sample/sampleList?currentPage=${i}&searchQuery=${searchQuery}&searchType=${searchType}">${i}</a></li>
							</c:forEach>
							<c:if test="${pagingInfo.currentScreen <  pagingInfo.lastScreen}">
								<li class="page-item"><a href="/sample/sampleList?currentPage=${pagingInfo.currentScreen * pagingInfo.pagePerScreen + 1}&searchQuery=${searchQuery}&searchType=${searchType}">></a></li>
							</c:if>
						</ul>
					</td>
				</tr>
			</tbody>
		</table>
		<div>
			<form action = "/sample/sampleList" method = "post" id = "searchForm" class="form-inline">
				<select name = "searchType" id ="searchType" class="form-control input-sm">
					<option value="sample_no">SAMPLENO</option>
					<option value="sample_id">SAMPLEID</option>
					<option value="sample_pw">SAMPLEPW</option>
				</select>
				<div class="input-group">
					<input type="text" name="searchQuery" value="${searchQuery}" class="form-control input-sm">
					<div class="input-group-btn">
					<button type="submit" class="btn btn-default input-sm"><i class="glyphicon glyphicon-search"></i></button>
				</div>
				</div>
			</form>
		</div>
	</div>
<script>
	$(document).ready(()=>{
		$('#pageItem'+${pagingInfo.currentPage}).addClass('active');
		if('${searchType}' == ''){
			$('#searchType').val('sample_no');
		}
		else {
			$('#searchType').val('${searchType}');
		}
		
	});
</script>
</body>
</html>