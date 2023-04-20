package bbs;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/VisitList") //web.xml에 출생신고처럼 하는 친구
public class VisitList extends HttpServlet { //httpservlet을 상속받음
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8"); //utf-8을 쓰겠다
		PrintWriter out = response.getWriter(); //클라이언트로부터 요청이 들어오면 파악 후 응답을 html형태로 보내겠다
		try {
			out.println("<html>");
			out.println("<head><title>방명록 리스트</title></head>");
			out.println("<body>");
			StringBuffer sql = new StringBuffer();//idon'tknow
			sql.append("select no, writer, memo, regdate "); //sql 쿼리문을 어팬드로 붙여서 사용하겠다.
			sql.append("from visit ");
			sql.append("order by no desc ");
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "mytest", "mytest");
				pstmt = con.prepareStatement(sql.toString());
				rs = pstmt.executeQuery();
				while (rs.next()) {	//데이터 0 or 1개라면 if / 복수의 데이터가 나올 가능성이라면 while 
					int no = rs.getInt("no");
					String writer = rs.getString("writer");
					String memo = rs.getString("memo");
					java.sql.Date regdate = rs.getDate("regdate");
					out.println("<table align=center width=500 border=1>");
					out.println("<tr>");
					out.println("<th width=50>번호</th>");
					out.println("<td width=50 align=center>" + no + "</td>");
					out.println("<th width=70>작성자</th>");
					out.println("<td width=180 align=center>" + writer + "</td>");
					out.println("<th width=50>날짜</th>");
					out.println("<td width=100 align=center>" + regdate + "</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<th width=50>내용</th>");
					out.println("<td colspan=5>&nbsp; <textarea rows=3 cols=50>" + memo + "</textarea></td>");
					out.println("</tr>");
					out.println("</table>");
//					out.println("<p align=center> <input type=\"button\" onclick=\"/myWeb/VisitDelete\" value=\"삭제하기\"></p>");
					out.println("<p>");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
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
				try {
					if (rs != null)
						rs.close();
				} catch (SQLException e) {
				}
			}
			out.println("<p align=center> <a href=/myWeb/bbs/write.html>글쓰기</a></p>");
			out.println("</body>");
			out.println("</html>");
		} finally {
			out.close();
		}
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
