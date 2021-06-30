<#import "modules/auth.ftlh" as a>
<#import "modules/common.ftlh" as c>
<#import "modules/profile.ftlh" as p>

<@a.logout/>
<@c.page>
    <h4>Edit</h4>
    <form action="/user/${user.id}/edit" method="post">
        <@p.credentials/>
        <@p.details/>
        <@p.role/>
        <@p.status/>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <div><input type="submit" value="Submit"/></div>
    </form>
</@c.page>