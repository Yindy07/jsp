package sample;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
@WebServlet("/Source")
public class Source extends HttpServlet {
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Source Start");
		// 페이지 이동
		// 1. forward 방식으로 이동
		RequestDispatcher view = request.getRequestDispatcher("Destination");
		request.setAttribute("myname", "yhy");
		request.setAttribute("myage", "27");
		view.forward(request, response);	
		// 2. redirect 방식으로 이동
//		 response.sendRedirect("Destination");
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