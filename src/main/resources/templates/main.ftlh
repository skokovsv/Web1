<#import "parts/common.ftlh" as c>
<#import "parts/logreg.ftlh" as l>

<@c.page>
    <@l.logout/>
    <span><a href="/user">Список пользователей</a></span>



    <div>
        <form method="post">
            <input type="text" name="text" placeholder="Введите ФИО"/>
            <input type="text" name="groupp" placeholder="Введите номер группы"/>
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button type="submit">Добавить</button>
        </form>
    </div>

    <div>Список студентов</div>
    <form  method="post" action="filter">

        <input type="text" name="filter">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit">Найти</button>
    </form>
    <#list students as student>
        <div>
            <b>${student.getGroupp()}</b>
            <span>${student.getName()}</span>
            <strong>${student.getStudentuser()}</strong>
        </div>
        <#else>
            Список пуст
    </#list>
</@c.page>



