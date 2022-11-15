<%@page import="com.jqueryAddress.AddressVO"%>
<%@page import="com.jqueryAddress.JAddressDAOImpl"%>
<%@page import="com.jqueryAddress.JAddressDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<%
	request.setCharacterEncoding("utf-8");
   int num =Integer.parseInt(request.getParameter("num"));
   JAddressDAO dao = JAddressDAOImpl.getInstance();
   AddressVO  avo = dao.findByNum(num);
%>

<script>
function zipRead(){
	window.open("zipCheck.jsp","","width=700 height=400")
}
function del(){
	if(confirm('정말 삭제할까요?')){
		location.href='deleteProcess.jsp?num=<%=num%>'
	}
	
}
</script>
</head>
<body>
<h3>상세보기</h3>
<form action="updateProcess.jsp" method="post">
	<table>
		<tr>
			<th>번호</th>
			<td><input type="text" name="num" value="<%=avo.getNum() %>" 
			readonly="readonly"> </td>
		</tr>
		<tr>
			<th>이름</th>
			<td><input type="text" name="name" value="<%=avo.getName() %>"> </td>
		</tr>
			<tr>
			<th>우편번호</th>
			<td><input type="text" name="zipcode"  id="zipcode" value="<%=avo.getZipcode() %>"  size=7>
				<input type="button" value="검색" onclick="zipRead()" />
			</td>
		</tr>
			<tr>
			<th>주소</th>
			<td><input type="text" name="addr" id="addr" value="<%=avo.getAddr() %>" size=50> </td>
		</tr>
			<tr>
			<th>전화번호</th>
			<td><input type="text" name="tel" value="<%=avo.getTel() %>"></td>
		</tr>
		<tr>
		<td colspan="2">
			<input type="submit"  value="수정">
			<input type="button"  value="삭제" onclick="del()">
			<input type="reset"  value="취소">
			<input type="button"  value="전체보기typebutton" onclick="location.href='addrList.jsp'">
			<button type="button">전체보기</button>
		</td>
		</tr>
	</table>
</form>
</body>
</html>

