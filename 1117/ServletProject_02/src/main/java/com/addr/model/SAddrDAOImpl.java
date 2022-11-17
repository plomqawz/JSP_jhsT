package com.addr.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class SAddrDAOImpl  implements SAddrDAO{
	private static SAddrDAO instance = new SAddrDAOImpl();
	public static SAddrDAO getInstance() {
		return instance;
	}
	private Connection getConnection() throws Exception{
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource) envCtx.lookup("jdbc/jsp");
		return ds.getConnection();
	}
  //Ãß°¡
	@Override
	public void addrInsert(AddrDTO addr) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con =getConnection();
			String sql = "insert into address values(address_seq.nextval,?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, addr.getName());
			ps.setString(2, addr.getAddr());
			ps.setString(3, addr.getZipcode());
			ps.setString(4, addr.getTel());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con, ps, ps, null);
		}
		
	}

	@Override
	public ArrayList<AddrDTO> addrList() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<AddrDTO> arr = new ArrayList<AddrDTO>();
		
		try {
			con = getConnection();
			String sql = "select * from address";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				AddrDTO addr = new AddrDTO();
				addr.setAddr(rs.getString("addr"));
				addr.setName(rs.getString("name"));
				addr.setNum(rs.getInt("num"));
				addr.setTel(rs.getString("tel"));
				addr.setZipcode(rs.getString("zipcode"));
				arr.add(addr);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con, null, st, rs); 
		}
		return arr;
	}

	@Override
	public AddrDTO addrDetail(int num) {
		 Connection con =null;
		 Statement st = null;
		 ResultSet rs = null;
		 AddrDTO address = null;
		 
		 try {
			con = getConnection();
			st = con.createStatement();
			rs = st.executeQuery("select * from address where num="+num);
			if(rs.next()) {
				address = new AddrDTO();
				address.setAddr(rs.getString("addr"));
				address.setName(rs.getString("name"));
				address.setNum(rs.getInt("num"));
				address.setTel(rs.getString("tel"));
				address.setZipcode(rs.getString("zipcode"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con, null, st, rs);
		}
		return address;
	}

	@Override
	public void addrUpdate(AddrDTO addr) {
		  Connection con = null;
		   PreparedStatement ps =null;
		   
		   try {
				con =getConnection();
				String sql="update address set name=?, addr=?, tel=?, zipcode=? where num=?";
				ps= con.prepareStatement(sql);
				ps.setString(1, addr.getName());
				ps.setString(2, addr.getAddr());
				ps.setString(3, addr.getTel());
				ps.setString(4, addr.getZipcode());
				ps.setInt(5, addr.getNum());
				ps.executeUpdate();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				closeConnection(con, ps, null, null);
			}
		
	}

	@Override
	public void addrDelete(int num) {
		Connection con = null;
		Statement st = null;
		
		try {
			con =getConnection();
			st = con.createStatement();
			st.executeUpdate("delete from address where num="+num);
		} catch (Exception e) {
				e.printStackTrace();
		}finally {
			closeConnection(con, null, st, null);
		}
		
	}

	@Override
	public ArrayList<AddrDTO> addrSearchList(String field, String word) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addrCount() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		int count = 0;
		
		try {
			con =getConnection();
			String sql = "select count(*) from address";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()) {
				count =rs.getInt(1);
			}
		} catch (Exception e) {
					e.printStackTrace();
		}finally {
			closeConnection(con, null, st, rs);
		}
		return count;
	}

	@Override
	public int addrSearchCount(String field, String word) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<ZipDTO> addrZipRead(String dong) {
		Connection con = null;
		Statement st = null;
		ResultSet rs= null;
		ArrayList<ZipDTO> zarr = new ArrayList<ZipDTO>();
		
		try {
			con = getConnection();
			String sql = "select * from zipcode where dong like '%"+dong+"%'";
			//System.out.println(sql);
			st = con.createStatement();
			rs= st.executeQuery(sql);
			while(rs.next()) {
				ZipDTO zip = new ZipDTO();
				zip.setBunji(rs.getString("bunji"));
				zip.setDong(rs.getString("dong"));
				zip.setGugun(rs.getString("gugun"));
				zip.setSeq(rs.getInt("seq"));
				zip.setSido(rs.getString("sido"));
				zip.setZipcode(rs.getString("zipcode"));
				zarr.add(zip);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con, null, st, rs);
		}
		return zarr;
	}
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
