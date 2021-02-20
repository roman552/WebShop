<%-- 
    Document   : addConsumer
    Created on : 10.12.2020, 13:29:46
    Author     : A
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Добавить покупателя</title>
    </head>
    <body>
        <h1>Добавить покупателя</h1>
        <p>${info}</p>
        <form action="createConsumer" method="POST" autocomplete="off">
            Имя: <input type="text" name="firstname" value="${firstname}"><br>
            Фамилия: <input type="text" name="lastname" value="${lastname}"><br>
            Деньги: <input type="text" name="cash" value="${cash}"><br>
            Логин: <input type="text" name="login" value="${login}"><br>
            Пароль: <input type="password" name="password" value=""><br>
            <input type="submit" value="добавить">
        </form>
    </body>
</html>
