package com.board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.util.DBConnection;

public class SBoardDAOImpl implements SBoardDAO {
	private static SBoardDAO instance = new SBoardDAOImpl();
	public static SBoardDAO getInstance() {
		return instance;
	}

	@Override
	public void boardInsert(BoardDTO board) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con =DBConnection.getConnection();
			String sql = "insert into simpleboard "
					+ " values(simpleboard_seq.nextval,?,?,?,0,?, sysdate)";
			ps =con.prepareStatement(sql);
			ps.setString(1, board.getUserid());
			ps.setString(2, board.getSubject());
			ps.setString(3, board.getEmail());
			ps.setString(4, board.getContent());
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			 closeConnection(con, ps, null, null);
		}
		
	}

	@Override
	public void boardUpdate(BoardDTO board) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<BoardDTO> boardList() {
		Connection con = null;
		Statement st = null;
		ResultSet rs= null;
		ArrayList<BoardDTO> arr= new ArrayList<BoardDTO>();
		
		try {
			con = DBConnection.getConnection();
		    st = con.createStatement();
		    rs = st.executeQuery("select * from simpleboard");
		    while(rs.next()) {
		    	BoardDTO board= new BoardDTO();
		    	board.setContent(rs.getString("content"));
		    	board.setEmail(rs.getString("email"));
		    	board.setNum(rs.getInt("num"));
		    	board.setReadcount(rs.getInt("readcount"));
		    	board.setRegdate(rs.getString("regdate"));
		    	board.setSubject(rs.getString("subject"));
		    	board.setUserid(rs.getString("userid"));
		    	arr.add(board);
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con, null, st, rs);
		}
		return arr;
	}

	@Override
	public ArrayList<BoardDTO> boardList(int startRow, int endRow, String field, String word) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void boardDelete(int num) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int boardCount(String field, String word) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BoardDTO findByNum(int num) {
		Connection con = null;
		Statement st = null;
		ResultSet rs= null;
		BoardDTO board=null;
		
		try {
			con = DBConnection.getConnection();
		    st = con.createStatement();
		    rs = st.executeQuery("select * from simpleboard where num="+num);
		    if(rs.next()) {
		    	 board= new BoardDTO();
		    	board.setContent(rs.getString("content"));
		    	board.setEmail(rs.getString("email"));
		    	board.setNum(rs.getInt("num"));
		    	board.setReadcount(rs.getInt("readcount"));
		    	board.setRegdate(rs.getString("regdate"));
		    	board.setSubject(rs.getString("subject"));
		    	board.setUserid(rs.getString("userid"));
		   
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con, null, st, rs);
		}
		return board;
	}

	@Override
	public void commentInsert(CommentDTO comment) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<CommentDTO> findAllComment(int bnum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int commentCount(int bnum) {
		// TODO Auto-generated method stub
		return 0;
	}
	//�ݱ�
	private void closeConnection(Connection con, 
			PreparedStatement ps, 	Statement st, ResultSet rs) {
		try {
			 if(rs!=null) rs.close();
			 if(st!=null) st.close();
			 if(ps!=null) ps.close();
			 if(con!=null) con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
}
