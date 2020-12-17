<%-- 
    Document   : addMoneyToConsumer
    Created on : 10.12.2020, 15:13:35
    Author     : A
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Пополнить кошелек покупателя</title>
        
    </head>
    <body>
        <h1>Пополнить кошелек покупателя</h1>
        <p>${info}</p>
        <form action="addMoney" method="POST">
        <h2>Список покупателей</h2>
        <select name="consumerId" multiple="true">
            <option value="">выберите покупателя</option>
            <c:forEach var="consumer" items="${listConsumers}">
                <option value="${consumer.id}">${consumer.id}. Имя: ${consumer.firstName} ${consumer.lastName}. Деньги: ${consumer.cash}$</option>
            </c:forEach>
        </select><br>
        Сколько денег($): <input type="number" name="money" min="1" placeholder="количество">
        <input type="submit" value="пополнить кошелек">
        </form>
    </body>
</html>
