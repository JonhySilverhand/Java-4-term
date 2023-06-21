<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Введите значения</title>
</head>
<body>
<h1>Введите значения</h1>
<form action="calculationServlet" method="post">
    <label for="a">Значение a:</label>
    <input type="text" name="a" id="a"><br>

    <label for="b">Значение b:</label>
    <input type="text" name="b" id="b"><br>

    <label for="c">Значение c:</label>
    <input type="text" name="c" id="c"><br>

    <label for="x">Значение x:</label>
    <input type="text" name="x" id="x"><br>

    <input type="submit" value="Рассчитать">
</form>
</body>
</html>
