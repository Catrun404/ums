<#macro basic>
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
        <#nested />
        <tr>
            <th>Creation date:</th>
            <td>${user.createdAt}</td>
        </tr>
    </table>
</#macro>
<#macro ext></#macro>