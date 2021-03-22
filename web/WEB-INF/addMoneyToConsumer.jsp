<%-- 
    Document   : addMoneyToConsumer
    Created on : 10.12.2020, 15:13:35
    Author     : A
--%>


<h1>Пополнить кошелек</h1>
                        <hr>
                        <p>${info}</p>
                        <div class="row d-flex justify-content-center">
                            <form action="addMoney" class="form-block" method="POST">
                                <h2>Список покупателей</h2>
                                <select name="id" multiple="true">
                                    <option value="">выберите покупателя</option>

                                    <option value="${id}">${id}. Имя: ${name}. Деньги: ${cash}$</option>

                                </select><br>
                                Сколько денег($): <input type="number" name="money" min="1" placeholder="количество">
                                <input type="submit" value="пополнить кошелек">
                            </form>
                        </div>  
                                    
                    </div>
                </div>	
            </div>
        </main>