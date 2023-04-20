package sample;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
@WebServlet("/Destination")
public class Destination extends HttpServlet {
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		try {
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Destination</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1> Destination Servlet 입니다</h1>");
//			request.setAttribute("myname", "jjw");
			out.println("<h1>이름 : " + request.getAttribute("myname") + "</h1>");
			String myage = (String)request.getAttribute("myage");
			out.println("<h1>나이 : " +myage+ "</h1>");
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
