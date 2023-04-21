package sample;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/LifeCycle")
public class LifeCycle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String company;
	private String manager;
	private String tel;
	private String email;

	public void init() throws ServletException {
		System.out.println("초기화 메소드 수행됨");
		// ServletContext의 초기 파라미터 값 읽기
		company = getServletContext().getInitParameter("company");
		manager = getServletContext().getInitParameter("manager");
		// ServletConfig의 초기 파라미터 값 읽기
		tel = getServletConfig().getInitParameter("tel");
		email = getServletConfig().getInitParameter("email");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			out.println("<html>");
			out.println("<body>");
			out.println("<li>회사명 : " + company + "</li>");
			out.println("<li>담당자 : " + manager + "</li>");
			out.println("<li>전화번호 : " + tel + "</li>");
			out.println("<li>이메일 : " + email + "</li>");
			out.println("</body>");
			out.println("</html>");
		} finally {
			out.close();
		}
	}

	public LifeCycle() {
		System.out.println("LifeServlet 생성자 호출");

	}

	@Override
	public void destroy() {
		System.out.println("destroy() 호출됨");
	}

//	@Override
//	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("service() 호출됨...");
//	}

}
