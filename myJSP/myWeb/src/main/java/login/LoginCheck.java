package login;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/LoginCheck")
public class LoginCheck extends HttpServlet {
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id"); // 입력 id
		String pwd = request.getParameter("pwd");// 입력 pw
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 로딩 성공");
		} catch (ClassNotFoundException cnfe) {
			System.err.println("드라이버 로딩실패");
			cnfe.printStackTrace();
		}

		StringBuffer sql = new StringBuffer();// idon'tknow
		sql.append("select id, pass "); // sql 쿼리문을 어팬드로 붙여서 사용하겠다.
		sql.append("from login ");
		sql.append("where id=? ");
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String iid = "", ppw = "";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			try {
				con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "mytest", "mytest");
				System.out.println("db 연결 성공");
			} catch (SQLException sqle) {
				System.out.println("데이터베이스 연결 실패");
				sqle.printStackTrace(); // 보안문제로 실 사용은 안됨
			}
			try {
				pstmt = con.prepareStatement(sql.toString());
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					iid = rs.getString("id");
					ppw = rs.getString("pass");
					System.out.println("iid : " + iid);
					System.out.println("ppw : " + ppw);
				}
			} catch (SQLException query) {
				System.out.println("쿼리문 오류");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
			}
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
			}
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
		if (iid.equals(""))
			response.sendRedirect("Login");
		else {
			if (iid.equals(id) && ppw.equals(pwd)) {

				// HttpSession객체 얻기
				HttpSession session = request.getSession();
				// 클라이언트의 정보를 HttpSession객체에 저장
				session.setAttribute("user", id);
			}
			response.sendRedirect("Login");
		}
		// db에서 사용자 정보 조회.... 이부분의 코딩을 수정해서 만들어 볼것
		// db에서 조회한 사용자의 아이디 비번이 일치하는 경우
		// 클라이언트의 정보를 HttpSession객체에 유지 시킨다.
//		String dbID = "admin";
//		String dbPWD = "1234";
//		if (dbID.equals(id) && dbPWD.equals(pwd)) {
//			// HttpSession객체 얻기
//			HttpSession session = request.getSession();
//			// 클라이언트의 정보를 HttpSession객체에 저장
//			session.setAttribute("user", id);
//		}
//		response.sendRedirect("Login");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

}