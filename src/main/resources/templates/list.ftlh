<#import "modules/common.ftlh" as c>
<#import "modules/pagination.ftlh" as p>

<@c.page>
    <@p.pp url page/>
    <br>
    <table>
        <thead>
        <tr>
            <th>Username</th>
            <th>Role</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <div class="form-row">
            <div class="form-group col-md-6">
                <form method="get" action="/user" class="form-inline">
                    <label>
                        <select name="role" class="form-select" onchange="this.form.submit()">
                            <option value="" selected disabled hidden>Role</option>
                            <option value="">ALL</option>
                            <#list roles as role>
                                <option value="${role}">${role}</option>
                            </#list>
                        </select>
                    </label>
                    <label>
                        <input type="text" name="username" class="form-control" value="${username!}"
                               placeholder="Username">
                    </label>
                    <button type="submit" class="btn btn-primary ml-2">Filter</button>
                </form>
            </div>
        </div>
        <#list page.content as user>
            <div>
                <tr>
                    <td><a href="/user/${user.id}">${user.username}</a></td>
                    <td>${user.role}</td>
                </tr>
            </div>
        </#list>
        </tbody>
    </table>
</@c.page>