<#macro login path>
    <form action="${path}" method="post">
        <div><label>Имя пользователя: <input type="text" name="username"></label></div>
        <div><label>Пароль: <input type="password" name="password"></label></div>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <div><input type="submit" value="Принять"></div>
    </form>
</#macro>

<#macro logout>
    <div>
        <form action="/logout" method="post">
            <input type="submit" value="Покинуть">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
        </form>
    </div>
</#macro>