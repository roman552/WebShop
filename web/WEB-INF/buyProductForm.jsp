<%-- 
    Document   : buyProductForm
    Created on : 10.12.2020, 14:31:48
    Author     : A
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Купить компьютер</title>
    </head>
    <body>
        <h1>Купить компьютер</h1>
        <p>${info}</p>
        <form action="buyProduct" method="POST">
            <h2>Список компьютеров</h2>
            <select name="productId" multiple="true">
                <option value="">выберите компьюпер</option>
                <c:forEach var="product" items="${listProducts}">
                    <option value="${product.id}">${product.id}. Название: ${product.name}. Цена: ${product.price}$. Количество: ${product.quantity}. Видеокарта: ${product.videocard}.
                    CPU: ${product.cpu}.
                    Оперативная память: ${product.ram}.</option>
                </c:forEach>
            </select>
            <h2>Список покупателей</h2>
            <select name="consumerId" multiple="true">
                <option value="">выберите покупателя</option>
                <c:forEach var="consumer" items="${listConsumers}">
                    <option value="${consumer.id}">${consumer.id}. Имя: ${consumer.firstName} ${consumer.lastName}. Деньги: ${consumer.cash}$</option>
                </c:forEach>
            </select>
            <h2>Количество</h2>
            <input type="number" name="count" min="1" placeholder="количество компьютеров">

            <input type="submit" value="купить">
        </form>
    </body>
</html>
