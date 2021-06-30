<#macro credentials>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Username:</label>
        <div class="col-sm-6">
            <input type="text" name="username" class="form-control" placeholder="Username" value="${username!''}"/>
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Password:</label>
        <div class="col-sm-6">
            <input type="password" name="password" class="form-control" placeholder="Password"/>
        </div>
    </div>
    <#nested>
</#macro>

<#macro details>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">First name:</label>
        <div class="col-sm-6">
            <input type="text" name="firstName" class="form-control" placeholder="First name" value="${firstName!''}"/>
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Last name:</label>
        <div class="col-sm-6">
            <input type="text" name="lastName" class="form-control" placeholder="Last name" value="${lastName!''}"/>
        </div>
    </div>
</#macro>

<#macro status>
    <div class="form-group row">
        <label for="status" id="status" class="col-sm-2 col-form-label">
            Status:
            <select name="status">
                <option value="INACTIVE">Lock</option>
                <option value="ACTIVE">Unlock</option>
            </select>
        </label>
    </div>
</#macro>

<#macro role>
    <div class="form-group row">
        <label for="role" id="role" class="col-sm-2 col-form-label">
            Role:
            <select name="role">
                <option value="USER">User</option>
                <option value="ADMIN">Admin</option>
            </select>
        </label>
    </div>
</#macro>
<#macro edit path>
    <form action="${path}" method="get">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <input type="submit" value="Edit"/>
    </form>
</#macro>