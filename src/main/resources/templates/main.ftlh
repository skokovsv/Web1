<#import "parts/common.ftlh" as c>

<@c.page>
    <div>
        <form method="post">
            <input type="text" name="text" placeholder="Введите ФИО"/>
            <input type="text" name="groupp" placeholder="Введите номер группы"/>
            <button type="submit">Добавить</button>
        </form>
    </div>

    <div>Список студентов</div>
    <form  method="post" action="filter">
        <input type="text" name="filter">
        <button type="submit">Найти</button>
    </form>
    <#list students as student>
        <div>
            <b>${student.getGroupp()}</b>
            <span>${student.getName()}</span>
        </div>
        <#else>
            Список пуст
    </#list>
</@c.page>



