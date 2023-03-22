<#import "parts/common.ftlh" as c>

<@c.page>
    Добавить нового пользователя
    <#if message??> ${message}</#if>
    <form action="/registration" method="post">
        <div><label>Имя пользователя: <input type="text" name="username"></label></div>
        <div><label>Пароль: <input type="password" name="password"></label></div>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <div><input type="submit" value="Принять"></div>
    </form>
</@c.page>
