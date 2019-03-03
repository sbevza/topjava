<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Редактирование еды</title>
</head>
<body>
<h1>Редактирование еды</h1>
<form method="post" action='meals'>
    <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.Meal" scope="request"/>
    <input type="hidden" name="id" value="<c:out value="${meal.id}" />"/>
    Дата/Время : <input
        type="datetime-local" name="dateTime"
        value="${meal.dateTime}"/> <br/>
    Описание : <input
        type="text" name="description"
        value="<c:out value="${meal.description}" />"/> <br/>
    Калории : <input
        type="number" name="calories"
        value="<c:out value="${meal.calories}" />"/> <br/>
    <button type="submit">Записать</button>
    <button type="button" onclick="window.history.back()">Отмена</button>
</form>
</body>
</html>
