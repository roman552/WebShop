<%-- 
    Document   : addConsumer
    Created on : 10.12.2020, 13:29:46
    Author     : A
--%>


<h1>Регистрация</h1>
                        <hr>
                                    <div class="row d-flex justify-content-center">
                                        <p>${info}</p>
                                        <form action="createConsumer" method="POST" autocomplete="off" class="form-block">
                                            Имя: <input type="text" name="firstname" class="form-input" value="${firstname}"><br>
                                            Фамилия: <input type="text" name="lastname" class="form-input" value="${lastname}"><br>
                                            
                                            Логин: <input type="text" name="login" class="form-input" value="${login}"><br>
                                            Пароль: <input type="password" name="password" class="form-input" value=""><br>
                                            <input type="submit" class="form-input" value="добавить">
                                            
                                        </form>
                                    </div>          
                    </div>
                </div>	
            </div>
        </main>
