<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Погодный брокер</title>
</head>
<body>
<h1>Погодный брокер</h1>
<h2>Введите название города</h2>
<form action="index" method="get" modelAttribute="city">
    <input type="text" name="city">
    <input type="submit">
</form>
</body>
</html>
