<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="로그인 페이지" />
<html>

<head>
	<!-- 제이쿼리 불러오기 -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	
	<!-- 테일윈드 불러오기 -->
	<!-- 노말라이즈, 라이브러리까지 한방에 해결 -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/tailwindcss/2.2.7/tailwind.min.css" />
	
	<!-- 데이지 UI -->
	<link href="https://cdn.jsdelivr.net/npm/daisyui@1.11.1/dist/full.css" rel="stylesheet" type="text/css" />
	
	<!-- 폰트어썸 불러오기 -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" />
</head>

<body >

<div class="" style="width: 100%; height: 100%; margin-left: auto; margin-right: auto; background: linear-gradient( to right, skyblue, #9de6ec); display: flex; align-items: center">
	<div style="width: 960px; height: 560px; background-color: white; border-radius: 5px; padding-top: 60px; display: flex; flex-direction: column; align-items: center" class="m-auto">
		<div class="text-3xl font-bold" style="text-align: center">ACCOUNT LOGIN</div>
		<div class="mt-14" style="display: flex; flex-direction: column; width: 622px">
			<span class="text-lg">USERNAME</span>
			<input type="text" id="loginId" class="input input-bordered input-lg" placeholder="id" name="loginId"/>
		</div>
		<div class="m-5" style="display: flex; flex-direction: column; width: 622px">
			<span class="text-lg">PASSWORD</span>
			<input type="password" id="loginPw" class="input input-bordered input-lg" placeholder="pw" name="loginPw"/>
		</div>
		<div class="m-5 " style="width: 622px">
			<input value="로그인" type="button" class="w-full btn btn-lg" style="background-color: #4286f5; color: white; border-radius: 5px" placeholder="pw" onclick="doLogin()"/>
		</div>
		
	</div>
	<div style="height: 300px;"></div>

</div>

<script>
	var param = {loginId: $("#loginId").val(), loginPw: $("#loginPw").val()};
	function doLogin(){
		param = {loginId: $("#loginId").val(), loginPw: $("#loginPw").val()};
		
		
		$.ajax({
			url: "/client/user/login",
			async: true,
			type: 'POST',
			data: param,
			dataType: 'json',
			success: function(json) {
				alert(json.msg);
				if(json.fail){
					$("#loginPw").val("");				}
				else{
					location.reload();
					
				}
			}
		})
	}
	
	

	
</script>
</body>
</html>