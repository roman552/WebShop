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
        <title>Вход в аккаунт</title>
    </head>
    <body>
        <h1>Вход в аккаунт</h1>
        <p>${info}</p>
        <form action="login" method="POST" autocomplete="off">
            Логин: <input type="text" name="login" value="${login}"><br>
            Пароль: <input type="password" name="password" value=""><br>
            <input type="submit" value="Войти">
        </form>
    </body>
</html>
