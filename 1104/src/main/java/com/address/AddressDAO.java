package com.address;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException; // �־ �ǰ� ��� ��.
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class AddressDAO {

	private static AddressDAO instance = new AddressDAO();
	public static AddressDAO getInstance() {
		return instance;
	}
	// DB����
	private Connection getConnection() throws Exception{
		Context initCtx=new InitialContext();
		Context envCtx = (Context)initCtx.lookup("java:comp/env"); //������ �������� ã�ƿ�.
		DataSource ds = (DataSource)envCtx.lookup("jdbc/jsp"); // ���� �̸�����ؼ� ã��.
		//  inf�� context.xml ����.
		return ds.getConnection();
	}
	
	// �߰�
	public void addrInsert(Address ad) { // ����ó�� �� ���� ������ ������ ����ó�� �ؾ���.
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = getConnection();
			// ��xx, 111, �λ�, 010-1111-1111
			String sql = "insert into ADDRESS values (address_seq.nextval,?,?,?,?)";
			ps = con.prepareStatement(sql);
			/* ps.setString(1, ��xx); */
			ps.setString(1, ad.getName());
			ps.setString(2, ad.getZipcode());
			ps.setString(3, ad.getAddr());
			ps.setString(4, ad.getTel());
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally { // try�� catch�� ����Ǵ� ����. ������ ����� 00�� �ݾ������ : �Լ��� ����.
			closeConnection(con,ps,null,null);
		
		} 
	}
	
	// ��ü����
	public ArrayList<Address> addressList() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<Address> arr = new ArrayList<Address>(); // <E>:element , ����Ʈ�� object(�ֻ���Ŭ����)�̹Ƿ� ����.
		
		try {
			con=getConnection();
			String sql="select * from address";
			st=con.createStatement();
			rs=st.executeQuery(sql); // rs ������ �����ؼ� ����Ʈ���� ����� ����Ʈ�¿� ����. jsp �� �Ѹ��¹� 2�� 1)jsp�� �˾Ƽ��ϰ� 2)�ڹٿ��� ó���ϰ� �ѱ� �� ����.
			while(rs.next()) {
				Address ad = new Address();
				ad.setNum(rs.getInt("num"));
				ad.setAddr(rs.getString("addr"));
				ad.setName(rs.getString("name"));
				ad.setTel(rs.getString("tel"));
				ad.setZipcode(rs.getString("zipcode"));
				// ���Ϲ� ���� ad�� ������� �ʰ� ������� �ݺ���.
				// �����ؾ��ϴµ� �迭�� ���� : ����Ÿ��, �̸� ũ�����ؾ���.
				// arrayList�� ���� �̿��ؼ� ��Ƶ�.
				arr.add(ad);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con, null, st, rs);
		}
		return arr; // arr�� ���� ��巹��(Ÿ��)�� ��ȯ��.
	}
	
	// ����
	
	// ����
	
	private void closeConnection(Connection con, PreparedStatement ps,
			Statement st, ResultSet rs) {
		try {
			if(con !=null) 			con.close();
			if(ps !=null) 			ps.close();
			if(st !=null) 			st.close();
			if(rs !=null) 			rs.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
}
