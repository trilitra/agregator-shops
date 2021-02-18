<#import "parts/common.ftlh" as main>
<@main.page>
    <div class="form-row">
        <div class="form-group col-md-10">
            <form method="POST" action="/filter" class="form-inline">
                <input type="hidden" value="${_csrf.token}" name="_csrf">
                <input type="text" name="filter" class="form-control col-md-6" value="${filter?ifExists}" placeholder="Поиск продукта по названию">
                <button type="submit" class="btn btn-primary ml-5">Поиск</button>
            </form>
        </div>
    </div>

    <h5> По Вашему запросу найдено: </h5>
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
            <#list productsAshan as product>
                <tr>
                    <td>${product.productName}</td>
                    <td>${product.store.getName()}</td>
                    <td>${product.getValue()}</td>
                    <td>${product.price}</td>
                    <td>
                        <form action="${'/addInBasketAshan'}" method="POST">
                            <input type="hidden" value="${_csrf.token}" name="_csrf">
                            <button name="productId" type="submit" class="btn btn-primary btn-sm btn-block" value="${product.productId}">Добавить в корзину</button>
                        </form>
                    </td>
                </tr>
            </#list>
        </tbody.table-hover>
    </table>
</@main.page>
