<#import "modules/common.ftlh" as c>
<#import "modules/auth.ftlh" as a>
<#import "/spring.ftl" as spring />

<@c.page>
    <div class="form-row">
        <div class="form-group col-md-6">
            <form action="/user/new" method="post" class="form-table">
                <div class="form-group row">
                    <label>Username:</label>${message!}
                    <@spring.formInput "user.username"/><br>
                    <@spring.showErrors "<br>"/>
                </div>
                <br>
                <div class="form-group row">
                    <label>Password:</label>
                    <@spring.formPasswordInput "user.password"
                    "pattern=\"^(?=.*[0-9])(?=.*[a-zA-Z])([a-zA-Z0-9]{3,16})$\"
                    placeholder=\"Latin letters and numbers\""/>
                    <br>
                    <@spring.showErrors "<br>"/>
                </div>
                <@spring.showErrors "<br>"/>
                <br>
                <div class="form-group row">
                    <label>First name:</label>
                    <@spring.formInput "user.firstName"/><br>
                </div>
                <@spring.showErrors "<br>"/>
                <br>
                <div class="form-group row">
                    <label>Last name:</label>
                    <@spring.formInput "user.lastName"/><br>
                </div>
                <@spring.showErrors "<br>"/>
                <br>
                <div class="form-group row">
                    <label>Role:</label>
                    <@spring.formSingleSelect "user.role" roles />
                </div>
                <br>
                <div class="form-group row">
                    <label>Status:</label>
                    <@spring.formSingleSelect "user.status" statuses/>
                </div>
                <br>
                <div>
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <button type="submit" class="btn btn-primary ml-2">Submit</button>
                </div>
            </form>
        </div>
    </div>
</@c.page>