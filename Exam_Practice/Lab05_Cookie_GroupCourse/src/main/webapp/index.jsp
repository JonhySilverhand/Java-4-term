<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Course Cookies</title>
</head>
<body>
<div>
    <p>${info}</p>
    <form action="login" method="get">
        <input type="number" name="course" placeholder="Курс"/>
        </br></br>
        <input type="number" name="group" placeholder="Группа"/>
        </br></br>
        <input type="text" name="specialty" placeholder="Специальность"/>
        </br></br>
        <button type="submit">Sign in</button>
    </form>
    <div>
        <a href="${pageContext.request.contextPath}/ClearCookiesServlet">Очистить Куки</a>
    </div>
</div>
</body>
</html>