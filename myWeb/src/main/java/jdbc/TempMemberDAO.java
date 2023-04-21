package jdbc;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
//import jdbc.TempMemberVO;

public class TempMemberDAO {
//	private final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
//	private final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521/XEPBD1";
//	private final String USER = "mytest";
//	private final String PASS = "mytest";

//	private ConnectionPool pool = null;
	
	public TempMemberDAO() {
//		try {
////			Class.forName(JDBC_DRIVER);
//			pool = ConnectionPool.getInstance();
//		} catch (Exception e) {
//			System.out.println("Error : JDBC 드라이버 로딩 실패");
//		}
	}

	private Connection getConnection(){
		Connection conn = null;
		try{
		 Context init = new InitialContext();
		 DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/myOracle");
		 conn = ds.getConnection();
		 
	}catch(NamingException ne) {
		System.out.println("자원 이름이 틀려요");
	}catch(SQLException sqle) {
		sqle.printStackTrace();
	}
		return conn;
	}
	//필요한 메서드 하나 하나를 구현 - 기능
	public Vector<TempMemberVO> getMemberList() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		Vector<TempMemberVO> vecList = new Vector<TempMemberVO>();
		try {
//			conn = DriverManager.getConnection(JDBC_URL, USER, PASS);
//			conn = pool.getConnection();
			conn = getConnection();
			String strQuery = "select * from tempMember";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(strQuery);
			while (rs.next()) {
				TempMemberVO vo = new TempMemberVO();
				vo.setId(rs.getString("id"));
				vo.setPasswd(rs.getString("passwd"));
				vo.setName(rs.getString("name"));
				vo.setMem_num1(rs.getString("mem_num1"));
				vo.setMem_num2(rs.getString("mem_num2"));
				vo.setEmail(rs.getString("e_mail"));
				vo.setPhone(rs.getString("phone"));
				vo.setZipcode(rs.getString("zipcode"));
				vo.setAddress(rs.getString("address"));
				vo.setJob(rs.getString("job"));
				vecList.add(vo);
			}
		} catch (Exception ex) {
			System.out.println("Exception" + ex);
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
//					pool.releaseConnection(conn);
					
				} catch (SQLException e) {
				}
		}
		return vecList;
	}
}
