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
        <div style="margin-top: 5%; text-align: center; min-height: 500px; min-width: 500px; background-color: #282828; color: white; display: flex; align-items: center; justify-content: center; flex-direction: column">
            <h1><i>Войдите в аккаунт</i></h1>
            <p>${info}</p>
            <form action="login" method="POST" autocomplete="off">
                Логин: <input style="background-color: #282828; color: white; border: 1px solid white; border-radius: 20px" type="text" name="login" value="${login}"><br><br>
                Пароль: <input style="background-color: #282828; color: white; border: 1px solid white; border-radius: 20px" type="password" name="password" value=""><br><br>
                <input style="background-color: #282828; color: white; border: 1px solid white; width: 250px; height: 50px; margin-top: 5%;" type="submit" value="Войти">
            </form>
        </div>
    </body>
</html>
