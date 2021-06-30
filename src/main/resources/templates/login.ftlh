<#import "modules/common.ftlh" as c>
<#import "modules/auth.ftlh" as a>

<@c.page>
    <h4>Login</h4>
    <@a.login "/login" />
    <a href="/user/new">Add new user</a>
</@c.page>


