<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>uploadList</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!-- jquery CDN -->
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
</head>
<body>
<script>
$(document).ready(()=>{

/* 	$('#updateBtn').click(()=>{
		$('#updateForm').submit();
	}); */


	
});	

</script>
	<div class="container" align="center">
		<h1>uploadList</h1>
		<table class="table table-striped">
			<thead>
				<tr>
					<th style="text-align: center; width: 100px">FileNo</th>
					<th style="text-align: center; width: 100px">Image</th>
					<th style="text-align: center">Name</th>
					<th style="text-align: center">Type</th>
					<th style="text-align: center">Size</th>
					<th style="text-align: center">Date</th>
					<th style="text-align: center">Delete</th>
					<th style="text-align: center">Update</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${sampleFileList == null}">
					<tr>
						<td colspan="8" rowspan="8" style="text-align: center; vertical-align: middle">
							<h4>업로드한 파일이 없습니다</h4>
						</td>
					</tr>
				</c:if>
				<c:forEach var ="sampleFile" items = "${sampleFileList}">
					<tr style="margin: 0 auto">
						<td style="text-align: center; vertical-align: middle;margin"><h4>${sampleFile.sampleFileNo}</h4></td>
						<td style="text-align: center; vertical-align: middle;height: 100px; width: 100px">
							<c:if test="${fn:contains(sampleFile.sampleFileType,'image')}">
								<img src="/uploads/${sampleFile.sampleFileRealName}.${sampleFile.sampleFileExt}" class="img-rounded" style="width: 80px;height: 80px">
							</c:if>
							<c:if test="${!fn:contains(sampleFile.sampleFileType,'image')}">
								<span class="glyphicon glyphicon-remove"></span>
							</c:if>
						</td>
						<td style="text-align: center; vertical-align: middle;">
							<a href="/sample/downloadFile?sampleFileNo=${sampleFile.sampleFileNo}">${sampleFile.sampleFileName}.${sampleFile.sampleFileExt}</a>
						</td>
						<td style="text-align: center; vertical-align: middle;">
							${sampleFile.sampleFileType}
						</td>
						<td style="text-align: center; vertical-align: middle;">
							${sampleFile.sampleFileSize}KB
						</td>
						<td style="text-align: center; vertical-align: middle;">
							${sampleFile.sampleFileDate}
						</td>
						<td style="text-align: center; vertical-align: middle;">
							<a href="/sample/deleteFile?sampleFileNo=${sampleFile.sampleFileNo}&sampleNo=${sampleFile.sampleNo}">Delete</a>
						</td>
						<td style="text-align: center; vertical-align: middle;">
							<form action="/sample/updateFile" method="post" class="form-inline" id="updateForm" enctype="multipart/form-data">
								<input type="hidden" name="sampleNo" value="${sampleFile.sampleNo}">
								<input type="hidden" name="sampleFileNo" value="${sampleFile.sampleFileNo}">
								<input type="file" class="form-control" name ="multipartFile" style="width: 220px">
								<input type = "submit" value="수정" class="btn btn-primary">
							</form>
						</td>						
					</tr>
				</c:forEach>
			</tbody>	
		</table>
	</div>
</body>
</html>