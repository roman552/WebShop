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

<h1>Добавить картинку</h1>
                        <hr>
                            <div class="row d-flex justify-content-center">
                                <p>${info}</p>
                                <form action="uploadCover" method="POST"  enctype="multipart/form-data">
                                    
                                      <label for="file" class="form-label">Выберите локальный файл</label>
                                      <input class="form-control" type="file" name="file" id="file">
                                   
                                    
                                      <label for="description" class="form-label">Описание</label>
                                      <input class="form-control" type="text" name="description" id="description">
                                    
                                    
                                        <button type="submit" class="btn btn-primary">Загрузить файл</button>
                                   
                                </form>
                            </div>          
                    </div>
                </div>	
            </div>
        </main>