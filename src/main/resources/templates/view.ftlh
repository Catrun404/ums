<#import "modules/common.ftlh" as c>
<#import "modules/auth.ftlh" as a>
<#import "modules/profile.ftlh" as p>

<@c.page>
    <@a.logout/><@p.edit "/user/${user.id}/edit"/>
    View
    <h3>${user.username}</h3>
    <table>
        <tr>
            <th>First name:</th>
            <td>${user.firstName}</td>
        </tr>
        <tr>
            <th>Last name:</th>
            <td>${user.lastName}</td>
        </tr>
        <tr>
            <th>Role:</th>
            <td>${user.role}</td>
        </tr>
        <tr>
            <th>Status:</th>
            <td>${user.status}</td>
            <td>
                <form action="/user/${user.id}/edit" method="post">
                    <label for="status" id="status">
                        <select name="status" onchange="this.form.submit()">
                            <#if "${user.status}"=="ACTIVE">
                                <option value="ACTIVE">Unlock</option>
                                <option value="INACTIVE">Lock</option>
                            <#else>
                                <option value="INACTIVE">Lock</option>
                                <option value="ACTIVE">Unlock</option>
                            </#if>
                        </select>
                    </label>
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                </form>
            </td>
        </tr>
        <tr>
            <th>Creation date:</th>
            <td>${user.createdAt}</td>
        </tr>
    </table>


</@c.page>