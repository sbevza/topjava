<%@ page import="ru.javawebinar.topjava.util.TimeUtil" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="resources/style.css"/>
    <title>Подсчет калорий</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Meals</h2>
<h4><a href="meals?action=add">+ Добавить</a></h4>
<table style="width: 700px;">

    <thead>
    <tr>
        <th>Дата/Время</th>
        <th>Описание</th>
        <th>Калории</th>
        <th colspan="2">Действия</th>
    </tr>
    </thead>

    <tbody>
    <c:forEach var="meal" items="${mealList}">
        <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.MealTo" />
        <tr data-mealexcess="<c:out value="${meal.excess}" />">
            <td>
                <%= TimeUtil.toString(meal.getDateTime())%>
            </td>
            <td><c:out value="${meal.description}"/></td>
            <td><c:out value="${meal.calories}"/></td>
            <td><a href="meals?action=edit&id=<c:out value="${meal.id}" />">Редактировать</a></td>
            <td><a href="meals?action=delete&id=<c:out value="${meal.id}" />">Удалить</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>