<%-- 
    Document   : adminPanel
    Created on : 01.03.2021, 18:06:41
    Author     : A
--%>
<h1>Регистрация</h1>
                        <hr>
                                    <div class="row d-flex justify-content-center">
                                        <p>${info}</p>
                                        <form action="setRoleToUser" method="POST" autocomplete="off" class="form-block">
                                            <select name="userId" multiple="true">
                                                <c:forEach var="user" items="${listUsers}">
                                                    <option value="${user.getId()}">${user.getId()}. ${user.getUser().getFirstName()} ${user.getUser().getLastName()}</option>
                                                </c:forEach>
                                            </select>
                                            <select name="roleId" multiple="true">
                                                <c:forEach var="role" items="${listRoles}">
                                                    <option value="${role.getId()}">${role.getId()}. ${role.getRoleName()}</option>
                                                    
                                                </c:forEach>
                                            </select>
                                            <input type="submit" class="form-input" value="Назначить">
                                            
                                        </form>
                                    </div>          
                    </div>
                </div>	
            </div>
        </main>

