<%-- 
    Document   : index
    Created on : 09.12.2020, 18:10:50
    Author     : A
--%>


        <h1>Список компьютеров</h1>
                        <hr>
                        <p>${info}</p>
                                    <div class="row">
                                        <c:forEach var="product" items="${listProducts}">
                                        <div class="col-4 mt-3">
                                                <div class="card" style="width: 18rem;">
                                                    <img src="insertFile/${product.cover.path}" class="card-img-top" alt="...">
                                                    <div class="card-body">
                                                          <h5 class="card-title">${product.name}</h5>
                                                          <p class="card-text">
                                                          <form id="1" action="buyProduct" method="POST">    
                                                                Цена: ${product.price}$<br>
                                                                Количество: ${product.quantity}<br>
                                                                Видеокарта: ${product.videocard}<br>
                                                                CPU: ${product.cpu}<br>
                                                                Оперативная память: ${product.ram}<br>
                                                                <input class="d-none" type="text" name="productId" value="${product.id}">
                                                          </p>
                                                          <div class="d-flex justify-content-between">
                                                              <input type="submit" name="form1" class="btn btn-primary" value="Купить">
                                                          
                                                          </div>
                                                      </div>
                                                  </div>
                                            </form>
                                        </div>
                                    </c:forEach>
                                    </div>          
                    </div>
                </div>	
            </div>
        </main>
