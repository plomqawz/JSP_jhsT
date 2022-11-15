package com.jqueryAddress;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;



public class JAddressDAOImpl  implements JAddressDAO{
	private static JAddressDAO instance = new JAddressDAOImpl();
	public static JAddressDAO getInstance() {
		return instance;
	}
	//µðºñ¿¬°á
	private  Connection getConnection() throws Exception {
		Context  initCtx = new InitialContext();
		Context envCtx = (Context)initCtx.lookup("java:comp/env");
		DataSource  ds = (DataSource)envCtx.lookup("jdbc/jsp");
		return ds.getConnection();
	}
	@Override
	public void insert(AddressVO avo) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = getConnection();
			// È«±æµ¿   111 ºÎ»ê 010-1111-2222
			String sql = "insert into address(num,name, zipcode, addr, tel) "
					+ " values(address_seq.nextval,?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, avo.getName());
			ps.setString(2,avo.getZipcode());
			ps.setString(3,  avo.getAddr());
			ps.setString(4, avo.getTel());
			ps.executeUpdate();  
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con,ps, null, null);
		}
		
	}

	@Override
	public ArrayList<AddressVO> list() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<AddressVO> arr   = new ArrayList<AddressVO>();
	
		try {
			con = getConnection();
			
			String	sql = "select * from address order by num desc"; //°Ë»ö¾Æ´Ô
			
			st = con.createStatement();
			rs= st.executeQuery(sql);
			while(rs.next()) {
				AddressVO ad = new AddressVO();
				ad.setAddr(rs.getString("addr"));
				ad.setName(rs.getString("name"));
				ad.setNum(rs.getInt("num"));
				ad.setTel(rs.getString("tel"));
				ad.setZipcode(rs.getString("zipcode"));
				arr.add(ad);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con, null, st, rs);
		}
		return arr;
	}

	@Override
	public AddressVO findByNum(int num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void update(AddressVO avo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int num) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public ArrayList<ZipCodeVO> getZipcode(String dong) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<ZipCodeVO> zarr = new ArrayList<ZipCodeVO>();
		
		 try {
			con =getConnection();
			String sql = "select * from zipcode where dong like '%"+dong+"%'";
			System.out.println(sql);
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				ZipCodeVO z = new ZipCodeVO();
				z.setBunji(rs.getString("bunji"));
				z.setDong(rs.getString("dong"));
				z.setGugun(rs.getString("gugun"));
				z.setSeq(rs.getInt("seq"));
				z.setSido(rs.getString("sido"));
				z.setZipcode(rs.getString("zipcode"));
				zarr.add(z);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con, null, st, rs);
		}
			return zarr;
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
