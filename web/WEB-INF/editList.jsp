<%-- 
    Document   : listProducts
    Created on : 10.12.2020, 14:19:26
    Author     : A
--%>


<h1>Выберите компьютер для изменения</h1>
                        <hr>
                                    <div class="row d-flex justify-content-center">
                                        <p>${info}</p>
                                        <form method="POST" action="editProductForm" class="form-block">
                                            <select name="productId" multiple="true">
                                                <c:forEach var="product" items="${listProducts}">
                                                    <option value="${product.id}">${product.id}. Имя: ${product.name}. стоимость: ${product.price}$</option>
                                                </c:forEach>
                                            </select>
                                            <input type="submit" value="Выбрать">    
                                        </form>
                                    </div>          
                    </div>
                </div>	
            </div>
        </main>