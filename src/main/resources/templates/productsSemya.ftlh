<#import "parts/common.ftlh" as main>
<@main.page>
    <form action="${'/basket'}" method="GET">
        <input type="hidden" value="${_csrf.token}" name="_csrf">
        <button name="busket" type="submit" class="btn btn-primary btn-sm btn-block" >Перейти в корзину</button>
    </form>

    <div class="form-row mt-5">
        <div class="form-group col-md-10">
            <form method="POST" action="/filterSemya" class="form-inline">
                <input type="hidden" value="${_csrf.token}" name="_csrf">
                <input type="text" name="filter" class="form-control col-md-6" value="${filter?ifExists}" placeholder="Поиск продукта по названию">
                <button type="submit" class="btn btn-primary ml-5">Поиск</button>
            </form>
        </div>
    </div>

    <h5> Список продуктов </h5>
    <table class="table table-hover">
        <thead>
        <tr>
            <th>Название продукта</th>
            <th>Магазин</th>
            <th>Объём</th>
            <th>Цена</th>
        </tr>
        </thead>
        <tbody.table-hover>
        <#list productsSemya as product>
            <tr>
                <td>${product.productName}</td>
                <td>${product.store.getName()}</td>
                <td>${product.getValue()}</td>
                <td>${product.price}</td>
                <td>
                <form action="${'/addInBasketSemya'}" method="POST">
                    <input type="hidden" value="${_csrf.token}" name="_csrf">
                    <button name="productId" type="submit" class="btn btn-primary btn-sm btn-block" value="${product.productId}">Добавить в корзину</button>
                </form>
                </td>
            </tr>
        </#list>
        </tbody.table-hover>
    </table>
</@main.page>
