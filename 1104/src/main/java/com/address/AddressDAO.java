package com.address;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException; // 있어도 되고 없어도 됨.
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
	// DB연결
	private Connection getConnection() throws Exception{
		Context initCtx=new InitialContext();
		Context envCtx = (Context)initCtx.lookup("java:comp/env"); //가상의 폴더에서 찾아옴.
		DataSource ds = (DataSource)envCtx.lookup("jdbc/jsp"); // 임의 이름등록해서 찾음.
		//  inf에 context.xml 복붙.
		return ds.getConnection();
	}
	
	// 추가
	public void addrInsert(Address ad) { // 예외처리 된 것을 가져다 쓰려면 예외처리 해야함.
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = getConnection();
			// 김xx, 111, 부산, 010-1111-1111
			String sql = "insert into ADDRESS values (address_seq.nextval,?,?,?,?)";
			ps = con.prepareStatement(sql);
			/* ps.setString(1, 김xx); */
			ps.setString(1, ad.getName());
			ps.setString(2, ad.getZipcode());
			ps.setString(3, ad.getAddr());
			ps.setString(4, ad.getTel());
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally { // try든 catch든 실행되는 구문. 열었던 연결과 00를 닫아줘야함 : 함수로 만듬.
			closeConnection(con,ps,null,null);
		
		} 
	}
	
	// 전체보기
	public ArrayList<Address> addressList() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<Address> arr = new ArrayList<Address>(); // <E>:element , 디폴트는 object(최상위클래스)이므로 불편.
		
		try {
			con=getConnection();
			String sql="select * from address";
			st=con.createStatement();
			rs=st.executeQuery(sql); // rs 위에서 선언해서 셀렉트문의 결과를 리절트셋에 담음. jsp 에 뿌리는법 2개 1)jsp가 알아서하게 2)자바에서 처리하고 넘김 중 후자.
			while(rs.next()) {
				Address ad = new Address();
				ad.setNum(rs.getInt("num"));
				ad.setAddr(rs.getString("addr"));
				ad.setName(rs.getString("name"));
				ad.setTel(rs.getString("tel"));
				ad.setZipcode(rs.getString("zipcode"));
				// 와일문 안의 ad는 저장되지 않고 사라지며 반복함.
				// 저장해야하는데 배열은 단점 : 같은타입, 미리 크기정해야함.
				// arrayList나 벡터 이용해서 담아둠.
				arr.add(ad);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con, null, st, rs);
		}
		return arr; // arr에 담은 어드레스(타입)를 반환함.
	}
	
	// 수정
	
	// 삭제
	
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
