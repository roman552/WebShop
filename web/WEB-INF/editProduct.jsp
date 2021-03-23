<h1>Изменить данные о компьютере</h1>
                        <hr>
                                    <div class="row d-flex justify-content-center">
                                        <p>${info}</p>
                                        <a class="btn btn-primary my-2" href="uploadForm">Загрузить обложку книги</a>
                                        <form action="editProduct" method="POST" autocomplete="off" class="form-block">
                                            <input class="d-none" name="productId" value="${productId}">
                                            Название компьютера: <input class="form-input" type="text" name="name" value="${name}"><br>
                                            Цена($): <input class="form-input" type="text" name="price" value="${price}"><br>
                                            Видеокарта: <input class="form-input" type="text" name="videocard" value="${videocard}"><br>
                                            Оперативная память: <input class="form-input" type="text" name="ram" value="${ram}"><br>
                                            CPU: <input class="form-input" type="text" name="cpu" value="${cpu}"><br>
                                            Количество: <input class="form-input" type="text" name="quantity" value="${quantity}"><br>
                                            <select class="form-select" name="coverId">
                                                <option value="">Выберите обложку</option>
                                                <c:forEach var="cover" items="${listCovers}">
                                                    <option value="${cover.id}">${cover.description}</option>
                                                </c:forEach>
                                            </select>
                                            <input class="form-input" type="submit" value="Изменить">
                                        </form>
                                    </div>          
                    </div>
                </div>	
            </div>
        </main>