<#import "parts/common.ftlh" as c>
<#import "parts/logreg.ftlh" as l>

<@c.page>
    Добавить нового пользователя
    <#if message??> ${message}</#if>
    <@l.login "/registration"/>
</@c.page>
