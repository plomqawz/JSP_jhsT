<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="com.board.dto.CommentDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.board.dao.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
    int bnum = Integer.parseInt(request.getParameter("bnum"));
    BoardDAO dao = BoardDAO.getInstance();
    ArrayList<CommentDTO>  carr = dao.commentList(bnum);
    // carr(java)  ==> json
    		
    JSONArray jarr = new JSONArray();
    for(CommentDTO comm : carr){
    	JSONObject obj = new JSONObject();
    	obj.put("userid", comm.getUserid());
    	obj.put("cnum", comm.getCnum());
    	obj.put("bnum",comm.getBnum());
    	obj.put("regdate",comm.getRegdate());
    	obj.put("msg",comm.getMsg());
    	jarr.add(obj);
    	
    }
    out.println(jarr.toString());
%>







