<%--
  Created by IntelliJ IDEA.
  User: nitis
  Date: 12/15/2022
  Time: 12:17 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <title>Login Success Page</title>
</head>
<body>
<h3>Hi <%= request.getAttribute("user") %>, Login Successful.</h3>
<a href="index.jsp"> Go Back </a>
</body>
</html>