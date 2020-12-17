<%-- 
    Document   : listProducts
    Created on : 10.12.2020, 14:19:26
    Author     : A
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Список покупателей</title>
    </head>
    <body>
        <h1>Список покупателей</h1>
        <select name="consumerId" multiple="true">
            <c:forEach var="consumer" items="${listConsumers}">
                <option>${consumer.id}. Имя: ${consumer.firstName} ${consumer.lastName}. Деньги: ${consumer.cash}$</option>
            </c:forEach>
        </select>
    </body>
</html>
