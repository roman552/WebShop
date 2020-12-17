<%-- 
    Document   : index
    Created on : 09.12.2020, 18:10:50
    Author     : A
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Web shop</title>
    </head>
    <body>
        <h1>Магазин компьютеров</h1>
        <p>${info}</p>
        <a href="addProduct">Добавить компьютер</a><br>  
        <a href="addConsumer">Добавить покупателя</a><br>  
        <a href="listProducts">Список компьютеров</a><br>  
        <a href="listConsumers">Список покупателей</a><br>  
        <a href="buyProductForm">Купить компьютер</a><br>  
        <a href="addMoneyToConsumer">Пополнить кошелек покупателя</a><br>  
    </body>
</html>
