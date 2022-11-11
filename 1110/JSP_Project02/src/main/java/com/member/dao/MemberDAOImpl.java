package com.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.member.dto.MemberDTO;

public class MemberDAOImpl  implements MemberDAO{

	private static MemberDAO instance = new MemberDAOImpl();
	 public static MemberDAO getInstance() {
		 return instance;
	 }
	 
		//디비연결
	private  Connection getConnection() throws Exception {
			Context  initCtx = new InitialContext();
			Context envCtx = (Context)initCtx.lookup("java:comp/env");
			DataSource  ds = (DataSource)envCtx.lookup("jdbc/jsp");
			return ds.getConnection();
	}
	@Override
	public void memberInsert(MemberDTO member) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = getConnection();
			String sql = "insert into memberdb values(?,?,?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, member.getName());
			ps.setString(2, member.getUserid());
			ps.setString(3, member.getPwd());
			ps.setString(4, member.getEmail());
			ps.setString(5, member.getPhone());
			ps.setInt(6, member.getAdmin());
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con, ps, ps, null);
		}
		
	}

	@Override
	public ArrayList<MemberDTO> memberList() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<MemberDTO> arr = new ArrayList<MemberDTO>();
		
		try {
			con =getConnection();
			String sql = "select * from memberdb";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				MemberDTO member = new MemberDTO();
				member.setAdmin(rs.getInt("admin"));
				member.setEmail(rs.getString("email"));
				member.setName(rs.getString("name"));
				member.setPhone(rs.getString("phone"));
				member.setPwd(rs.getString("pwd"));
				member.setUserid(rs.getString("userid"));
				arr.add(member);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con, null, st, rs);
		}
		return arr;
	}

	@Override
	public void memberUpdate(MemberDTO member) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void memberDelete(String userid) {
		Connection con = null;
		Statement st = null;
		
		try {
			con = getConnection();
			String sql = "delete from memberdb where userid ='"+userid+"'";
			st = con.createStatement();
			st.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con, null, st, null);
		}
		
		
	}

	@Override
	public MemberDTO findById(String userid) {
		// TODO Auto-generated method stub
		return null;
	}

	//아이디 중복확인
	@Override
	public String idCheck(String userid) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String flag ="yes"; //사용가능
		
		try {
			con =getConnection();
			String sql ="select * from memberdb where userid = '"+userid+"'";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()) {
				flag ="no";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con, null, st, rs);
		}
		return flag;
	}

	@Override
	public int loginCheck(String userid, String pwd) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getCount() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		int count = 0;
		
		try {
			con =getConnection();
			st = con.createStatement();
			String sql="select count(*) from memberdb";
			rs = st.executeQuery(sql);
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con, null, st, rs); 
		}
		return count;
	}
	
	// 닫기
	private void closeConnection(Connection con, PreparedStatement ps,
			Statement st, ResultSet rs) {
			try {
				if(con !=null) 			con.close();
				if(ps !=null) 				ps.close();
				if(st !=null) 				st.close();
				if(rs !=null) 				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
	}

}
