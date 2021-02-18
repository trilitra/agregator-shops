
<#include "security.ftlh">
<#import "login.ftlh" as l>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/">Agregator-Shops</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/main">Главная</a>
            </li>
            <#if isAdmin>
                <li class="nav-item">
                    <a class="nav-link" href="/users">Список пользователей</a>
                </li>
            </#if>
            <#if user??>
            <li class="nav-item">
                <a class="nav-link" href="/users/profile">Личный кабинет</a>
            </li>
            </#if>
            <#if user??>
                <li class="nav-item">
                    <a class="nav-link" href="/productAshan">Покупки в Ашане</a>
                </li>
            </#if>
            <#if user??>
                <li class="nav-item">
                    <a class="nav-link" href="/productSemya">Покупки в Семье</a>
                </li>
            </#if>
            <#if user??>
            <li class="nav-item">
                <a class="nav-link" href="/basket">Корзина</a>
            </li>
            </#if>
        </ul>

        <div class="navbar-text mr-5"><h6>${name}</h6></div>
        <@l.logout />
    </div>
</nav>