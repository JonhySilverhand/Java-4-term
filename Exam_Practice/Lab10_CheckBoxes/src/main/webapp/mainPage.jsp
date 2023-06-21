<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Lab10</title>
</head>
<body>
<form action="ResultsServlet" method="get">
    <c:forEach items="${candidates}" var="cand">
        <label>${cand.name}
        <input type="checkbox" name="checkboxes" value="${cand.id}"/>
        </label>
        <br/>
    </c:forEach>
    <button type="submit">Отправить голоса</button>
</form>
<a href="index.jsp">Назад</a>
</body>
</html>
