<%@ page contentType="text/html; charset=EUC-KR"%>
<%!private int numOne = 0;

	public void jspInit() {//�޼ҵ� ������
		System.out.println("jspInit() ȣ���");
	}

	public void jspDestroy() {//�޼ҵ� ������
		System.out.println("jspDestroy() ȣ���");
	}%>
<html>
<head>
<title>JSP Life Cycle</title>
</head>
<body>
	<%
	System.out.println(session.getId());
	int numTwo = 0;
	numOne++; // ���ΰ�ħ�Ҷ����� ����
	numTwo++; // ���ΰ�ħ�Ҷ����� �ʱ�ȭ
	%>
	<ul>
		<li>Number One : <%=numOne%></li>
		<li>Number Two : <%=numTwo%></li>
	</ul>
</body>
</html>