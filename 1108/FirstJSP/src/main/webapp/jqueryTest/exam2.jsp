<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
$(document).ready(function(){
	$("#getBtn").click(function(){
		$.get("process.jsp",
				  {
			         "id" : $("#id").val(),
			         "pwd" : $("#pwd").val(),
			         "method" : "get"
				  },
				  function(resp){  //콜백함수
					  //alert(resp)
					  $("#result").html(resp);
				  }
		  );//get
	}); //getBtn
	
  $("#postBtn").click(function(){
		$.post("process.jsp",
			{
			"id" : $("#id").val(),
			"pwd" : $("#pwd").val(),
			"method" : "post"
		 },
		function(resp){
			$("#result").html(resp);
		}); //post
	}) ;//postBtn


$("#ajaxBtn").click(function(){
	$.ajax({
		type : "get",
		url : "process.jsp",
		data : {
			id : $("#id").val(),
			pwd : $("#pwd").val(),
			method : "ajax"
		},
		success : function(resp){
			$("#result").html(resp);
		},
		error : function(e){
			alert(e + "error")
		}
	}) //ajax
}) //ajaxBtn

$("#ajaxBtn_on").on("click",function(){
	$.ajax({
		type : "get",
		url : "process.jsp",
		data : {
			id : $("#id").val(),
			pwd : $("#pwd").val(),
			method : "ajax_on"
		},
		success : function(resp){
			$("#result").html(resp);
		},
		error : function(e){
			alert(e + "error")
		}
	}) //ajax
}) //ajaxBtn_on

	
}); //document
</script>
</head>
<body>
	id : <input type="text" id="id" name="id"><br>
	pwd : <input type="password" id="pwd" name ="pwd"><br>
	<button type="button" id="getBtn">get전송</button> 
	<button type="button" id="postBtn">post전송</button> 
	<button type="button" id="ajaxBtn">ajax전송</button> 
	<button type="button" id="ajaxBtn_on">ajax_on전송</button> 
<hr/>
<div id="result"></div>
</body>
</html>