<%@ page contentType = "text/html; charset=euc-kr" %>
<%@ page errorPage = "/error/error.jsp" %>
<html>
<head><title>�Ķ���� ���</title></head>
<body>
name �Ķ���� �� : 
<%= request.getParameter("name").toUpperCase() %>
</body>
</html>