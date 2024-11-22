<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Вход</title>
</head>
<body>
<h2>Вход</h2>
<form method="POST">
    <label>Логин</label>
    <input type="text" name="login" required>

    <label>Пароль</label>
    <input type="password" name="password" required>

    <button type="submit">Войти</button>
</form>

<a href="<c:url value="/reg"/>">Регистрация</a>
</body>
</html>
