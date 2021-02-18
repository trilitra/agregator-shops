<#include "parts/security.ftlh">
<#import "parts/common.ftlh" as c>


<@c.page>
    <#if !user??>
    <div class="jumbotron">
        <h1 class="display-4">Приветствую, Гость!</h1>
        <hr class="my-4">
        <p>Для использования нашего сервиса Вам необходимо войти в свой аккаунт или зарегистрироваться</p>
        <p class="lead">
            <a class="btn btn-primary btn-lg" href="/login" role="button">Войти!</a>
        </p>
        <p class="lead">
            <a class="btn btn-primary btn-lg" href="/registration" role="button">Регистрация</a>
        </p>
    </div>
    </#if>

    <#if user??>
        <div class="jumbotron">
            <h1 class="display-4">С возвращением, наш любимый пользователь</h1>
            <hr class="my-4">
            <p>Пора пройтись по магазинам</p>
            <p class="lead">
                <a class="btn btn-primary btn-lg" href="/productAshan" role="button">Покупки в Ашане</a>
            </p>
            <p class="lead">
                <a class="btn btn-primary btn-lg" href="/productSemya" role="button">Покупки в Семье</a>
            </p>
        </div>
    </#if>


</@c.page>

