<#include "security.ftlh">
<#import "logreg.ftlh" as l>

<nav class="navbar navbar-expand-lg bg-body-tertiary" style="background-color: #e3f2fd;">

        <a class="navbar-brand" href="#">Журнал контактной работы</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/">Главная</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/main">Вход в систему</a>
                </li>
                <#if isAdmin>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/user">Список пользователей</a>
                </li>
                </#if>
            </ul>
            <div class="navbar-text mr-3">${name}</div>
        </div>
        <@l.logout/>

</nav>
