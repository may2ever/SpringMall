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
		console.log($("#fileform1").val() == "");
		
		let sampleId = $('#sampleId').val();
		let samplePw = $('#samplePw').val();
		if(sampleId.length >= 5 && samplePw.length >= 5) {
			$('#addForm').submit();
		}
	});
	let count = 1;
	$('#addFile').click(()=>{
		if(count < 3){
			count++;
			$('#fileAddtd').append("<input type='file' name ='multipartFile' id = fileform" + count +" class='form-control' style='width: 250px'>");
		}
	});
	$('#deleteFile').click(()=>{
		if(count > 1) {
			$('#fileform'+count).remove();
			count--;
		}
	});

	
});

</script>
<body>
	<div class="container" align="center" style="max-width: 40%">
		<h1>Add Sample</h1>
		<form action="/sample/addSample" method="post" id = "addForm" enctype="multipart/form-data">
			<table class="table " >
				<tr>
					<td style="text-align: center;width: 130px"><h4>SampleId</h4></td>
					<td>
						<input type="text" class = "form-control" name = "sampleId" id = "sampleId">
						<span id="sampleIdtext"></span>
					</td>
				</tr>
				<tr>
					<td style="text-align: center;width: 130px"><h4>SamplePw</h4></td>
					<td>
						<input type="password" class = "form-control" name = "samplePw" id = "samplePw">
						<span id="samplePwtext"></span>
					</td>
				</tr>
				<tr>
					<td style="text-align: center;width: 130px"><h4>SampleFile</h4></td>
					<td id = "fileAddtd" style="vertical-align: middle">
						<div class="form-inline">
							<input type="file" name ="multipartFile" id = "fileform1" class="form-control" style="width: 250px">
							<div class = "pull-right" >
								<button type="button" class="btn btn-primary btn-xs" style="width: 25px" id = "addFile">+</button>
								<button type="button" class="btn btn-danger btn-xs" style="width: 25px" id = "deleteFile">-</button>
							</div>
						</div>
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