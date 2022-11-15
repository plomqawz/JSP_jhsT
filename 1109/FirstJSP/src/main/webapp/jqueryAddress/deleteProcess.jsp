<%@page import="com.jqueryAddress.JAddressDAOImpl"%>
<%@page import="com.jqueryAddress.JAddressDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("utf-8");
    int num = Integer.parseInt(request.getParameter("num"));
    JAddressDAO dao = JAddressDAOImpl.getInstance();
    dao.delete(num);
    response.sendRedirect("addrList.jsp");

%>