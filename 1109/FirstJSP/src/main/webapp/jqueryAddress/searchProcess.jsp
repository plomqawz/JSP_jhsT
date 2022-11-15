<%@page import="org.json.simple.JSONArray"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="com.jqueryAddress.AddressVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.jqueryAddress.JAddressDAOImpl"%>
<%@page import="com.jqueryAddress.JAddressDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String field = request.getParameter("field");
	String word = request.getParameter("word");
	JAddressDAO dao = JAddressDAOImpl.getInstance();
	ArrayList<AddressVO> sarr = dao.searchList(field, word);
	int count = dao.getCount(field, word);
	
       // sarr ==> json
	   //count ==> json
	   
	   //mainObj(루트)
	   JSONObject mainObj = new JSONObject();
	   
	   //countObj(갯수)
	   JSONObject countObj = new JSONObject();
	   countObj.put("count",count);
	   
	   // sarr ==> json
		JSONArray jarr = new JSONArray();
	   for(AddressVO  avo: sarr){
		   JSONObject obj = new JSONObject();
		   obj.put("num" , avo.getNum());
		   obj.put("name", avo.getName());
		   obj.put("addr",avo.getAddr());
		   obj.put("zipcode", avo.getZipcode());
		   obj.put("tel", avo.getTel());
		   jarr.add(obj);
	   }
	   mainObj.put("countObj" , countObj);
	   mainObj.put("jarrObj",jarr);
	   out.print(mainObj.toString());
  
%>







