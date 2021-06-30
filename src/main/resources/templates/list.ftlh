<#import "modules/common.ftlh" as c>
<#import "modules/auth.ftlh" as a>

<@c.page>
    <@a.logout/>
    List
    <table>
        <thead>
        <tr>
            <th>Username</th>
            <th>Role</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <#list users as user>
            <tr>
            <td>${user.username}</td>
            <td>${user.role}</td>
            <td><a href="/user/${user.id}">View</a></td>
            </tr>
        </#list>
        </tbody>
    </table>
</@c.page>