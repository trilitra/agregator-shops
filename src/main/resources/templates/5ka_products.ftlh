<#import "parts/common.ftlh" as index>

<@index.page>
<table class="table">
    <thead>
    <tr>
        <th scope="row">NAME</th>
        <th scope="row">PRICE</th>
        <th scope="row">STORE</th>
        <th scope="row">MEASURE</th>
    </tr>
    </thead>
    <tbody>
    <#assign productCount = 0>
    <#list products_5ka_for_promo as item>
        <#list item.products as product>
            <tr>
                <#if productCount == 0>
                    <td rowspan="${item.products?size}">${item.name}</td>
                </#if>
                <td>${product.price}</td>
                <td>Магазин "${product.store.name}" по адресу "${product.store.address}"</td>
                <#if productCount == 0>
                    <td>${item.measure.value}</td>
                </#if>
            </tr>
            <#assign productCount = productCount + 1>
        </#list>
        <#assign productCount = 0>
    <#else>
        <tr>
            <td colspan="100%">No products</td>
        </tr>
    </#list>
    </tbody>
</table>
</@index.page>