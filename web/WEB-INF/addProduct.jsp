<%-- 
    Document   : addProduct
    Created on : 09.12.2020, 18:12:45
    Author     : A
--%>
<!--
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Добавить компьютер</title>
    </head>
    <body>
        <h1>Добавить компьютер</h1>
        <p>${info}</p>
        <form action="createProduct" method="POST" autocomplete="off">
            Название компьютера: <input type="text" name="name" value="${name}"><br>
            Цена($): <input type="text" name="price" value="${price}"><br>
            Видеокарта: <input type="text" name="videocard" value="${videocard}"><br>
            Оперативная память: <input type="text" name="ram" value="${ram}"><br>
            CPU: <input type="text" name="cpu" value="${cpu}"><br>
            Количество: <input type="text" name="quantity" value="${quantity}"><br>
            <input type="submit" value="Добавить">
        </form>
    </body>
</html>-->

<h1>Добавить компьютер</h1>
                        <hr>
                                    <div class="row d-flex justify-content-center">
                                        <p>${info}</p>
                                        <form action="createProduct" method="POST" autocomplete="off" class="form-block">
                                            Название компьютера: <input class="form-input" type="text" name="name" value="${name}"><br>
                                            Цена($): <input class="form-input" type="text" name="price" value="${price}"><br>
                                            Видеокарта: <input class="form-input" type="text" name="videocard" value="${videocard}"><br>
                                            Оперативная память: <input class="form-input" type="text" name="ram" value="${ram}"><br>
                                            CPU: <input class="form-input" type="text" name="cpu" value="${cpu}"><br>
                                            Количество: <input class="form-input" type="text" name="quantity" value="${quantity}"><br>
                                            <input class="form-input" type="submit" value="Добавить">
                                        </form>
                                    </div>          
                    </div>
                </div>	
            </div>
        </main>