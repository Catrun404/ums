<#import "modules/common.ftlh" as c>
<#import "modules/view.ftlh" as v>
<#include "modules/security.ftlh">

<@c.page>
    <h2>${user.username}</h2>
    <@v.basic>
        <#if isAdmin>
            <tr>
                <th>Status:</th>
                <td>${user.status}
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
        <#else>
            <tr>
                <th>Status:</th>
                <td>${user.status}</td>
            </tr>
        </#if>
    </@v.basic>
    <#if isAdmin><@c.edit "/user/${user.id}/edit"/></#if>
</@c.page>