<%@ page contentType="text/html; charset=EUC-KR"%>
<%!private int numOne = 0;

	public void jspInit() {//메소드 재정의
		System.out.println("jspInit() 호출됨");
	}

	public void jspDestroy() {//메소드 재정의
		System.out.println("jspDestroy() 호출됨");
	}%>
<html>
<head>
<title>JSP Life Cycle</title>
</head>
<body>
	<%
	System.out.println(session.getId());
	int numTwo = 0;
	numOne++; // 새로고침할때마다 증가
	numTwo++; // 새로고침할때마다 초기화
	%>
	<ul>
		<li>Number One : <%=numOne%></li>
		<li>Number Two : <%=numTwo%></li>
	</ul>
</body>
</html>