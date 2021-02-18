<#import "parts/common.ftlh" as main>
<@main.page>
    <form action="${'/basket'}" method="GET">
        <input type="hidden" value="${_csrf.token}" name="_csrf">
        <button name="basket" type="submit" class="btn btn-primary btn-sm btn-block" >Корзина</button>
    </form>


    <h5 class="mt-5">Ашан</h5>
    <table class="table table-hover">
        <thead>
        <tr>
            <th>Название продукта</th>
            <th>Название магазина</th>
            <th>Объем</th>
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
                </tr>
            </#list>
        </tbody.table-hover>
    </table>

    <table class="table table-hover">
        <thead>
        <tr>
            <th>Итоговая цена:</th>
        </tr>
        </thead>
        <tbody.table-hover>
                    <td>${summaAshan}</td>
        </tbody.table-hover>
    </table>

    <h5 class="mt-5">Семья</h5>
    <table class="table table-hover">
        <thead>
        <tr>
            <th>Название продукта</th>
            <th>Название магазина</th>
            <th>Объем</th>
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
                </tr>
            </#list>
        </tbody.table-hover>
    </table>

    <table class="table table-hover">
        <thead>
        <tr>
            <th>Итоговая цена:</th>
        </tr>
        </thead>
        <tbody.table-hover>
            <td>${summaSemya}</td>
        </tbody.table-hover>
    </table>
</@main.page>