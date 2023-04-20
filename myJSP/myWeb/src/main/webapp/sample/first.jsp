<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
<title>JSP File</title>
</head>
<body>
	<h2>JSP Script 예제</h2>
	<%// Scriptlet - 연산, 처리
	String scriptlet = "스크립트릿 입니다.";
	String comment = "주석문 입니다";
	out.println("내장객체를 이용한 출력 : " + declation + "<br></br>");
	%>
	선언문 출력하기(변수) : <%=declation%><br></br>
	선언문 출력하기(메소드) : <%=declationMethod()%> <br></br>
	스크립트릿 출력하기 : <%=scriptlet%><br></br>
	<!--JSP에서 사용하는 HTML 주석부분 -->
	<!-- HTML 주석 : <%=comment%> --> <br></br> <%--//html주석으로는 자바코드를 막을 ㅅ ㅜ없다-->
	<%-- JSP 주석 : <%=comment%> --%> <br></br>
	<%
	//자바주석
	/*
	여러줄 주석
	*/
	%>
	<%!// declation = 변수 선언
	String declation = "선언문 입니다.";//전역변수 %> 
	<%!// delcation =메소드 선언
public String declationMethod() {
	return declation;
}%>
</body>
</html>
