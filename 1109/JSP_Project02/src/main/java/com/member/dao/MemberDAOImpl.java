package com.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.member.dto.MemberDTO;

public class MemberDAOImpl  implements MemberDAO{

	@Override
	public void memberInsert(MemberDTO member) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<MemberDTO> memberList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void memberUpdate(MemberDTO member) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void memberDelete(String userid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MemberDTO findById(String userid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String idCheck(String userid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int loginCheck(String userid, String pwd) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	// ´Ý±â
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
