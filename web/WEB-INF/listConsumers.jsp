<%-- 
    Document   : listProducts
    Created on : 10.12.2020, 14:19:26
    Author     : A
--%>


<h1>Список покупателей</h1>
                        <hr>
                                    <div class="row d-flex justify-content-center">
                                        <p>${info}</p>
                                        <select name="consumerId" multiple="true">
                                            <c:forEach var="consumer" items="${listConsumers}">
                                                <option>${consumer.id}. Имя: ${consumer.firstName} ${consumer.lastName}. Деньги: ${consumer.cash}$</option>
                                            </c:forEach>
                                        </select>
                                    </div>          
                    </div>
                </div>	
            </div>
        </main>