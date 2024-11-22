<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>User</title>
</head>
<body>
<form method="POST">
    <label>Введите город</label>
    <input type="text" name="city" required>
    <button type="submit">Узнать погоду</button>

    <c:if test="${not empty city}">
        <p>Город: ${city}</p>
    </c:if>
    <c:if test="${not empty temp}">
        <p>Температура в цельсиях: ${temp}</p>
    </c:if>
</form>
</body>
</html>
