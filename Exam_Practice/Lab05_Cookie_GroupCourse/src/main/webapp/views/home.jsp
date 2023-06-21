<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 06.06.2023
  Time: 3:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Home</title>
</head>
<body>
<div>
  <p>Курс: ${param.course}</p>
  <p>Группа: ${param.group}</p>
  <p>Специальность: ${param.specialty}</p>
</div>
<div>
  <a href="${pageContext.request.contextPath}">Назад</a>
</div>
</body>
</html>
