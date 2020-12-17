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
        <title>Список компьютеров</title>
    </head>
    <body>
        <h1>Список компьютеров</h1>
        <select name="productId" multiple="true">
            <c:forEach var="product" items="${listProducts}">
                <option>${product.id}.
                    Название: ${product.name}.
                    Цена: ${product.price}$. 
                    Количество: ${product.quantity}.
                    Видеокарта: ${product.videocard}.
                    CPU: ${product.cpu}.
                    Оперативная память: ${product.ram}.
                </option>
            </c:forEach>
        </select>
    </body>
</html>
