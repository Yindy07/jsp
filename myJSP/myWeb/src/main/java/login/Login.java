package login;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/Login")
public class Login extends HttpServlet {
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			HttpSession session = request.getSession(false);// 세션 없으면 안만듦
			if (session != null) {// 널이 아니면 만듦
				String sessionId = session.getId();
				
				System.out.println("세션 아이디 : " + sessionId);
				String user = (String) session.getAttribute("user");
				out.println("<html>");
				out.println("<body>");
				out.println("<table border='1' width='300'>");
				out.println("<tr>");
				out.println("<td width='300' align='center'>" + user + " 님 로그인 되었습니다.</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td align='center'>");
				out.println("<a href='#'>회원정보</a>");
				out.println("<a href='Logout'>로그아웃</a>");
				out.println("</td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("</body>");
				out.println("</html>");
			} else {
				out.println("<html>");
				out.println("<body>");
				out.print("<form method='post' action='LoginCheck'>");
				out.println("<table border='1' width='300'>");
				out.println("<tr>");
				out.println("<th width='100'>아이디</th>");
				out.println("<td width='200'>&nbsp; <input type='text' name='id'></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<th width='100'>비번</th>");
				out.println("<td width='200'>&nbsp; <input type='password' name='pwd'></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td align='center' colspan='2'>");
				out.println("<input type='button' value='회원가입'>");
				out.println("<input type='submit' value='로그인'>");
				out.println("</td>");
				out.println("</tr>");
				out.println("</form>");
				out.println("</table>");
				out.println("</body>");
				out.println("</html>");
			}
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
