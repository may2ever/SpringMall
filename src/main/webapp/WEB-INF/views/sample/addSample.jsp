<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Sample</title>
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
	$('#addBtn').click(()=>{
		let sampleId = $('#sampleId').val();
		let samplePw = $('#samplePw').val();
		if(sampleId.length >= 5 && samplePw.length >= 5) {
			$('#addForm').submit();
		}
	});
});
</script>
<body>
	<div class="container" align="center" style="max-width: 50%">
		<h1>Add Sample</h1>
		<form action="/sample/addSample" method="post" id = "addForm">
			<table class="table " >
				<tr>
					<td style="text-align: center;width: 130px"><h4>아이디</h4></td>
					<td>
						<input type="text" class = "form-control" name = "sampleId" id = "sampleId">
						<span id="sampleIdtext"></span>
					</td>
				</tr>
				<tr>
					<td style="text-align: center;width: 130px"><h4>패스워드</h4></td>
					<td>
						<input type="password" class = "form-control" name = "samplePw" id = "samplePw">
						<span id="samplePwtext"></span>
					</td>
				</tr>
				<tr>
					<td colspan = "2" style="text-align: right"><input type = "button" id = "addBtn" class="btn btn-primary" value = "추가"></td>
				</tr>
			</table>
		</form>	
	</div>
</body>
</html>