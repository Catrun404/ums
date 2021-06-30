<#import "modules/common.ftlh" as c>
<#import "modules/auth.ftlh" as a>
<#import "modules/profile.ftlh" as p>

<@c.page>
    <@a.logout/>
    Add new user
    ${message!}
    <@a.login "/user/new">
        <@p.details/>
        <@p.role/>
        <@p.status/>
    </@a.login>
</@c.page>