<#import "modules/common.ftlh" as c>
<#import "/spring.ftl" as spring>

<@c.page>
<form action="/user/${user.id}/edit" method="post">
    <table>
        <tr>
            <th>Username:</th>
            <td>
                <div class="col-sm-6">
                    <input type="hidden" name="username" value="${user.username}"/>
                    ${user.username}
                </div>
            </td>
        </tr>
        <tr>
            <th>Password:</th>
            <th>
                <div class="col-sm-6">
                    <@spring.formPasswordInput "user.password"
                    "pattern=\"^(?=.*[0-9])(?=.*[a-zA-Z])([a-zA-Z0-9]{3,16})$\"
                    placeholder=\"Latin letters and numbers\""/><br>
                    <br>
                    <@spring.showErrors "<br>"/>
                </div>
            </th>
        </tr>
        <tr>
            <th>First name:</th>
            <td>
                <div class="col-sm-6">
                    <@spring.formInput "user.firstName" "placeholder=${user.firstName}"/><br>
                    <@spring.showErrors "<br>"/>
                </div>
            </td>
        </tr>
        <tr>
            <th>Last name:</th>
            <td>
                <div class="col-sm-6">
                    <@spring.formInput "user.lastName" "placeholder=${user.lastName}"/><br>
                    <@spring.showErrors "<br>"/>
                </div>
            </td>
        </tr>
        <tr>
            <th>Role:</th>
            <td>${user.role}
                <label for="role" id="role">
                    <select name="role">
                        <#if "${user.role}"=="ADMIN">
                            <option value="ADMIN" selected>Admin</option>
                            <option value="USER">User</option>
                        <#else>
                            <option value="USER" selected>User</option>
                            <option value="ADMIN">Admin</option>
                        </#if>
                    </select>
                </label>
            </td>
        </tr>
        <tr>
            <th>Status:</th>
            <td>${user.status}
                <label for="status" id="status">
                    <select name="status">
                        <#if "${user.status}"=="ACTIVE">
                            <option value="ACTIVE" selected>Unlock</option>
                            <option value="INACTIVE">Lock</option>
                        <#else>
                            <option value="INACTIVE" selected>Lock</option>
                            <option value="ACTIVE">Unlock</option>
                        </#if>
                    </select>
                </label>
            </td>
        </tr>
    </table>
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <div><input type="submit" value="Submit"/></div>
</@c.page>