<#import "parts/common.ftlh" as index>


<@index.page>

    <h5> Редактирование пользователя </h5>
    <table class="table-bordered">
        <thead>
        <tr>
            <th>ID</th>
            <th>ACTIVE
            </td>
            <th>USERNAME
            </td>
            <th>PASSWORD
            </td>
            <th>EMAIL
            </td>
            <th>ROLE
            </td>
        </tr>
        </thead>

        <tr>
            <td>${users.id}</td>
            <td>${users.active?string('yes','no')}</td>
            <td>${users.username}</td>
            <td>${users.password}</td>
            <td>${users.email}</td>
            <td>${users.role.name}</td>

        </tr>
        </thead>
    </table>

    <form class="mt-5" action="${'/edit-user'}" method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Active:</label>
            <input class="col-sm-4" id='select_active' name="active" value="${users.active?string('yes','no')}">
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">USERNAME:</label>
            <input class="col-sm-4" id='select_username' name="username" value="${users.username}">
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">PASSWORD:</label>
            <input class="col-sm-4" id='select_password' name="password" value="${users.password}">
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">ROLE:</label>
            <select class="col-sm-4" id='select_role' name="roleId">
                <option disabled>Выберите ROLE</option>
                <#list roles as role>
                    <option value="${role.id}">${role.name}</option>
                </#list>
            </select>
        </div>
        <input type="hidden" name="id" value="${users.id}">
        <input type="hidden" value="${_csrf.token}" name="_csrf">
        <button type="submit" class="btn btn-primary  mt-5" value="save">Сохранить</button>

    </form>

</@index.page>
