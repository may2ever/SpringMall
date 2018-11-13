<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modify Sample</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!-- jquery CDN -->
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
</head>
<script>
$(document).ready(()=>{
	$('#sampleId').keyup(()=>{
		let sampleId = $('#sampleId').val();
		if(sampleId.length >= 5) {
			$('#sampleIdtext').text("");
		}
		else {
			$('#sampleIdtext').text("아이디를 5글자 이상 입력해 주세요");
		}
	});
	$('#samplePw').keyup(()=>{
		let sampleId = $('#samplePw').val();
		if(sampleId.length >= 5) {
			$('#samplePwtext').text("");
		}
		else {
			$('#samplePwtext').text("패스워드를 5글자 이상 입력해 주세요");
		}
	});
	$('#updateBtn').click(()=>{
		let sampleId = $('#sampleId').val();
		
		let samplePw = $('#samplePw').val();
		if(sampleId.length >= 5 && samplePw.length >= 5) {
			$('#updateForm').submit();
		}
	});
});
</script>
<body>
	<div class="container" align="center">
	<h1>Modify Sample</h1>
		<form action="/sample/modifySample" method="post" id = "updateForm" enctype="multipart/form-data">
			<table class="table " style="max-width: 50%">
				<tr>
					<td style="text-align: center;width: 130px"><h4>SampleNo</h4></td>
					<td><input type="text" class = "form-control" name = "sampleNo" value="${sample.sampleNo}" readonly></td>
				</tr>
				<tr>
					<td style="text-align: center;width: 130px"><h4>SampleId</h4></td>
					<td>
						<input type="text" class = "form-control" name = "sampleId" id = "sampleId" value="${sample.sampleId}">
						<span id="sampleIdtext"></span>
					</td>
				</tr>
				<tr>
					<td style="text-align: center;width: 130px"><h4>SamplePw</h4></td>
					<td>
						<input type="text" class = "form-control" name = "samplePw" id = "samplePw" value="${sample.samplePw}">
						<span id="samplePwtext"></span>
					</td>
				</tr>
				<tr>
					<td style="text-align: center;width: 130px"><h4>SampleFile</h4></td>
					<td>
						<input type="file" name ="multipartFile" class="form-control">
					</td>
				</tr>
				<tr>
					<td colspan = "2" style="text-align: right"><button type = "button" class="btn btn-primary" id="updateBtn" >수정</button></td>
				</tr>
			</table>
		</form>	
	</div>
</body>
</html>