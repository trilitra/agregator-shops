<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
    <div class="mb-5"><h5> Регистрация нового пользователя </h5></div>
    ${message?ifExists}
    <@l.login "/registration" true />
</@c.page>