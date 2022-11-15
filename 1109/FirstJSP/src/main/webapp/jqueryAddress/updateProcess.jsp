<%@page import="com.jqueryAddress.JAddressDAOImpl"%>
<%@page import="com.jqueryAddress.JAddressDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
	request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="avo" class="com.jqueryAddress.AddressVO"></jsp:useBean>
<jsp:setProperty property="*" name="avo"/>
<%
  JAddressDAO dao = JAddressDAOImpl.getInstance();
 dao.update(avo);
 response.sendRedirect("addrList.jsp");
%>







