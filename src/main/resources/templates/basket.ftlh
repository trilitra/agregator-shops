<#import "parts/common.ftlh" as main>
<@main.page>
    <form action="${'/summa'}" method="POST">
        <input type="hidden" value="${_csrf.token}" name="_csrf">
        <button name="summa" type="submit" class="btn btn-primary btn-sm btn-block" >Сумма ваших покупок составит </button>
    </form>
<div class="form-row mt-5">
    <h5> Продукты в корзине </h5>
</div>

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
            <#list basket as basket>
                <tr>
                    <td>${basket.productName}</td>
                    <td>${basket.store.getName()}</td>
                    <td>${basket.getValue()}</td>
                    <td>${basket.price}</td>
                    <td>
                        <form action="${'/deleteInBasket'}" method="POST">
                            <input type="hidden" value="${_csrf.token}" name="_csrf">
                            <button name="productId" type="submit" class="btn btn-primary btn-sm btn-block" value="${basket.productId}">Удалить из корзины</button>
                        </form>
                    </td>
                </tr>
            </#list>
        </tbody.table-hover>
    </table>
</@main.page>
