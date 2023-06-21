<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Lab10</title>
</head>
<body>

<c:forEach items="${chosenCandidates}" var="cand">
    <c:set var="vote" value="${cand.numberOfVotes / allVotes * 100}"/>
    <span>${cand.name} - </span>
    <fmt:formatNumber value="${vote}" maxFractionDigits="2"/>
    %
    <br/>
</c:forEach>
<a href="MainServlet">Назад</a>
</body>
</html>
