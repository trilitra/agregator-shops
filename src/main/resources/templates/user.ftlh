<#import "parts/common.ftlh" as index>

<@index.page>
    <h5> Список пользователей </h5>

    <table class="table table-hover">
        <thead>
        <tr>
            <th>ID</th>
            <th>ACTIVE</th>
            <th>USERNAME</th>
            <th>PASSWORD</th>
            <th>ROLE</th>
            <th>EMAIL</th>
            <th>ACTION</th>
        </tr>
        </thead>
        <tbody.table-hover>
        <#list users as user>
            <tr>
                <td>${user.id}</td>
                <td>${user.active?string('yes','no')}</td>
                <td>${user.username}</td>
                <td>${user.password}</td>
                <td>${user.role.name}</td>
                <td>${user.email}</td>
                <td>
                    <form action="${'/users'}" method="POST">
                        <input type="hidden" value="${_csrf.token}" name="_csrf">
                        <button name="id" type="submit" class="btn btn-primary btn-sm btn-block" value="${user.id}">Удалить</button>
                    </form>

                    <form action="${'/edit-user/' + user.id}" method="POST">
                        <input type="hidden" value="${_csrf.token}" name="_csrf">
                        <button name="id" type="submit" class="btn btn-primary btn-sm btn-block mt-1" value="${user.id}">Редактировать</button>
                    </form>
                </td>
            </tr>
        </tbody.table-hover>
        <#else>
            <tr class="standard_table">
                <td class="standard_table" colspan="100%">No user</td>
            </tr>
        </#list>
    </table>
</@index.page>
